package sistema.views;
import java.util.List;
import sistema.models.Produto;
import sistema.daos.ProdutoDAO;
import sistema.daos.ProdutoDAOImpl;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class TelaGerenciarEstoque extends javax.swing.JInternalFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaGerenciarEstoque.class.getName());
    private ProdutoDAO produtoDAO;
    private List<Produto> produtosAtuais;
    private DefaultTableModel tableModel;
    public TelaGerenciarEstoque() {
        initComponents();
        produtoDAO = new ProdutoDAOImpl();
        inicializarTela();
        configurarEventos();
    }
    private void inicializarTela() {
        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Todas", "CAMISETA", "CALCA", "VESTIDO", "SAPATO", "ACESSORIO", "OUTRO"
        }));
        cbmMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Todas", "Nike", "Adidas", "Puma", "Lacoste", "Calvin Klein", "Outras"
        }));
        cbmTamanho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Todos", "PP", "P", "M", "G", "GG", "XG", "34", "36", "38", "40", "42", "44"
        }));
        cbmEstoque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Todos", "Baixo", "Normal", "Alto"
        }));
        txtBuscar.setText("");
        txtTotalProdutos.setText("0");
        txtEstoqueBaixo.setText("0");
        txtValorTotal.setText("R$ 0,00");
        configurarTabela();
        carregarProdutos();
    }
    private void configurarTabela() {
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Código", "Nome", "Categoria", "Marca", "Tamanho", "Qtd Atual", "Qtd Mínima", "Status" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(tableModel);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }
    private void configurarEventos() {
        btnBuscar.addActionListener(e -> buscarProdutos());
        btnAdicionarEstoque.addActionListener(e -> adicionarEstoque());
        btnRemoverEstoque.addActionListener(e -> removerEstoque());
        btnHistorico.addActionListener(e -> mostrarHistorico());
        btnRelatorio.addActionListener(e -> gerarRelatorio());
        btnAtualizar.addActionListener(e -> atualizarTela());
        btnVoltar.addActionListener(e -> voltar());
        txtBuscar.addActionListener(e -> buscarProdutos());
    }
    private void carregarProdutos() {
        try {
            produtosAtuais = produtoDAO.listarTodos();
            atualizarTabela();
            atualizarResumo();
        } catch (Exception e) {
            logger.severe("Erro ao carregar produtos: " + e.getMessage());
            mostrarErro("Erro ao carregar produtos: " + e.getMessage());
        }
    }
    private void buscarProdutos() {
        try {
            String nome = txtBuscar.getText().trim();
            String categoria = (String) cmbCategoria.getSelectedItem();
            String marca = (String) cbmMarca.getSelectedItem();
            String tamanho = (String) cbmTamanho.getSelectedItem();
            String estoque = (String) cbmEstoque.getSelectedItem();
            if ("Todas".equals(categoria)) categoria = null;
            if ("Todas".equals(marca)) marca = null;
            if ("Todos".equals(tamanho)) tamanho = null;
            produtosAtuais = produtoDAO.buscarComFiltros(nome, categoria, marca, tamanho);
            if (!"Todos".equals(estoque)) {
                aplicarFiltroEstoque(estoque);
            }
            atualizarTabela();
            atualizarResumo();
        } catch (Exception e) {
            logger.severe("Erro ao buscar produtos: " + e.getMessage());
            mostrarErro("Erro ao buscar produtos: " + e.getMessage());
        }
    }
    private void aplicarFiltroEstoque(String tipoEstoque) {
        if (produtosAtuais == null) return;
        List<Produto> produtosFiltrados = new java.util.ArrayList<>();
        for (Produto produto : produtosAtuais) {
            boolean incluir = false;
            switch (tipoEstoque) {
                case "Baixo":
                    incluir = produto.getQuantidadeEstoque() <= produto.getQuantidadeMinima();
                    break;
                case "Normal":
                    incluir = produto.getQuantidadeEstoque() > produto.getQuantidadeMinima() &&
                             produto.getQuantidadeEstoque() <= produto.getQuantidadeMinima() * 3;
                    break;
                case "Alto":
                    incluir = produto.getQuantidadeEstoque() > produto.getQuantidadeMinima() * 3;
                    break;
            }
            if (incluir) {
                produtosFiltrados.add(produto);
            }
        }
        produtosAtuais = produtosFiltrados;
    }
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        if (produtosAtuais != null) {
            for (Produto produto : produtosAtuais) {
                String status = getStatusEstoque(produto);
                tableModel.addRow(new Object[] {
                    produto.getCodigo(),
                    produto.getNome(),
                    produto.getCategoria(),
                    produto.getMarca(),
                    produto.getTamanho(),
                    produto.getQuantidadeEstoque(),
                    produto.getQuantidadeMinima(),
                    status
                });
            }
        }
    }
    private String getStatusEstoque(Produto produto) {
        if (produto.getQuantidadeEstoque() <= produto.getQuantidadeMinima()) {
            return "BAIXO";
        } else if (produto.getQuantidadeEstoque() <= produto.getQuantidadeMinima() * 2) {
            return "ATENÇÃO";
        } else {
            return "NORMAL";
        }
    }
    private void atualizarResumo() {
        if (produtosAtuais == null) return;
        int totalProdutos = produtosAtuais.size();
        int estoqueBaixo = 0;
        double valorTotal = 0.0;
        for (Produto produto : produtosAtuais) {
            if (produto.getQuantidadeEstoque() <= produto.getQuantidadeMinima()) {
                estoqueBaixo++;
            }
            valorTotal += produto.getPrecoVenda() * produto.getQuantidadeEstoque();
        }
        txtTotalProdutos.setText(String.valueOf(totalProdutos));
        txtEstoqueBaixo.setText(String.valueOf(estoqueBaixo));
        txtValorTotal.setText(String.format("R$ %.2f", valorTotal));
    }
    private void adicionarEstoque() {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            mostrarErro("Selecione um produto para adicionar estoque!");
            return;
        }
        String codigo = (String) tableModel.getValueAt(row, 0);
        Produto produto = produtoDAO.buscarPorCodigo(codigo);
        if (produto != null) {
            String input = JOptionPane.showInputDialog(
                this,
                "Quantidade a adicionar ao estoque:",
                "Adicionar Estoque",
                JOptionPane.QUESTION_MESSAGE
            );
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int quantidade = Integer.parseInt(input.trim());
                    if (quantidade > 0) {
                        int novaQuantidade = produto.getQuantidadeEstoque() + quantidade;
                        if (produtoDAO.atualizarEstoque(produto.getId(), novaQuantidade)) {
                            mostrarMensagem("Estoque atualizado com sucesso!");
                            buscarProdutos();
                        } else {
                            mostrarErro("Erro ao atualizar estoque!");
                        }
                    } else {
                        mostrarErro("Quantidade deve ser maior que zero!");
                    }
                } catch (NumberFormatException e) {
                    mostrarErro("Quantidade inválida!");
                }
            }
        }
    }
    private void removerEstoque() {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            mostrarErro("Selecione um produto para remover estoque!");
            return;
        }
        String codigo = (String) tableModel.getValueAt(row, 0);
        Produto produto = produtoDAO.buscarPorCodigo(codigo);
        if (produto != null) {
            String input = JOptionPane.showInputDialog(
                this,
                "Quantidade a remover do estoque:",
                "Remover Estoque",
                JOptionPane.QUESTION_MESSAGE
            );
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int quantidade = Integer.parseInt(input.trim());
                    if (quantidade > 0) {
                        int novaQuantidade = produto.getQuantidadeEstoque() - quantidade;
                        if (novaQuantidade >= 0) {
                            if (produtoDAO.atualizarEstoque(produto.getId(), novaQuantidade)) {
                                mostrarMensagem("Estoque atualizado com sucesso!");
                                buscarProdutos();
                            } else {
                                mostrarErro("Erro ao atualizar estoque!");
                            }
                        } else {
                            mostrarErro("Quantidade insuficiente em estoque!");
                        }
                    } else {
                        mostrarErro("Quantidade deve ser maior que zero!");
                    }
                } catch (NumberFormatException e) {
                    mostrarErro("Quantidade inválida!");
                }
            }
        }
    }
    private void mostrarHistorico() {
        JOptionPane.showMessageDialog(
            this,
            "Funcionalidade de histórico será implementada em breve!",
            "Histórico",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    private void gerarRelatorio() {
        JOptionPane.showMessageDialog(
            this,
            "Funcionalidade de relatório será implementada em breve!",
            "Relatório",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    private void atualizarTela() {
        buscarProdutos();
    }
    private void voltar() {
        this.dispose();
    }
    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        cbmMarca = new javax.swing.JComboBox<>();
        cbmTamanho = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbmEstoque = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTotalProdutos = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtEstoqueBaixo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAdicionarEstoque = new javax.swing.JButton();
        btnRemoverEstoque = new javax.swing.JButton();
        btnHistorico = new javax.swing.JButton();
        btnRelatorio = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        jLabel1.setText("GERENCIAR ESTOQUE");
        jLabel2.setText("Filtros:");
        jLabel3.setText("Categoria:");
        jLabel4.setText("Marca:");
        jLabel5.setText("Tamanho:");
        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbmMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbmTamanho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLabel6.setText("Estoque:");
        cbmEstoque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLabel7.setText("Buscar:");
        txtBuscar.setText("jTextField1");
        jLabel8.setText("RESUMO DO ESTOQUE:");
        jLabel9.setText("Total de Produtos:");
        txtTotalProdutos.setText("jTextField1");
        btnBuscar.setText("BUSCAR");
        jLabel10.setText("Estoque Baixo:");
        txtEstoqueBaixo.setText("jTextField1");
        jLabel11.setText("Valor Total:");
        txtValorTotal.setText("jTextField1");
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Categoria", "Marca", "Tamanho", "Qtd"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        btnAdicionarEstoque.setText("+ESTOQUE");
        btnRemoverEstoque.setText("-ESTOQUE");
        btnHistorico.setText("HISTÓRICO");
        btnRelatorio.setText("RELATÓRIO");
        btnAtualizar.setText("ATUALIZAR");
        btnVoltar.setText("VOLTAR");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(223, 223, 223))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTotalProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtEstoqueBaixo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel8)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(57, 57, 57)
                                    .addComponent(jLabel4))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbmEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cbmMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(51, 51, 51)
                                    .addComponent(jLabel5))
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBuscar)
                                .addComponent(cbmTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRelatorio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAtualizar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdicionarEstoque)
                                .addGap(62, 62, 62)
                                .addComponent(btnRemoverEstoque)))
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbmEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTotalProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtEstoqueBaixo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarEstoque)
                    .addComponent(btnRemoverEstoque)
                    .addComponent(btnHistorico))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRelatorio)
                    .addComponent(btnAtualizar)
                    .addComponent(btnVoltar))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        setSize(900, 700);
    }
    private javax.swing.JButton btnAdicionarEstoque;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnHistorico;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton btnRemoverEstoque;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbmEstoque;
    private javax.swing.JComboBox<String> cbmMarca;
    private javax.swing.JComboBox<String> cbmTamanho;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtEstoqueBaixo;
    private javax.swing.JTextField txtTotalProdutos;
    private javax.swing.JTextField txtValorTotal;
}
