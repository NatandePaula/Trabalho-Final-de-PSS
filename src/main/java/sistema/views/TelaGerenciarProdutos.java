
package sistema.views;
import java.util.List;
import sistema.models.Produto;
import sistema.daos.ProdutoDAO;
import sistema.daos.ProdutoDAOImpl;
import java.util.ArrayList;
public class TelaGerenciarProdutos extends javax.swing.JInternalFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaGerenciarProdutos.class.getName());
    public TelaGerenciarProdutos() {
        System.out.println("DEBUG: Construtor TelaGerenciarProdutos chamado");
        initComponents();
        inicializarTela();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBuscaProduto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        cmbMarca = new javax.swing.JComboBox<>();
        cmbTamanho = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnNovoProduto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        jLabel1.setText("GERENCIAR PRODUTOS");
        jLabel2.setText("Cadastro e controle de produtos de vestuário");
        jLabel3.setText("Produto:");
        txtBuscaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaProdutoActionPerformed(evt);
            }
        });
        jLabel4.setText("Filtros");
        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Roupas", "Calçados", "Acessórios", "Lingerie", "Esportivo" }));
        cmbMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Nike", "Adidas", "Puma", "Lacoste", "Calvin Klein", "Outras" }));
        cmbTamanho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "PP", "P", "M", "G", "GG", "XG", "34", "36", "38", "40", "42", "44" }));
        jLabel5.setText("Categoria:");
        jLabel6.setText("Marca:");
        jLabel7.setText("Tamanho:");
        btnPesquisar.setText("PESQUISAR");
        btnVoltar.setText("VOLTAR");
        btnNovoProduto.setText("NOVO PRODUTO");
        tbProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Categoria", "Marca", "Cor", "Tamanho", "Preço", "Estoque", "Ações"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbProdutos);
        btnEditar.setText("EDITAR");
        btnExcluir.setText("EXCLUIR");
        btnVisualizar.setText("VISUALIZAR");
        btnAtualizar.setText("ATUALIZAR");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnVoltar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(29, 29, 29)
                                .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))))
            .addGroup(layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(btnPesquisar)
                .addGap(33, 33, 33)
                .addComponent(btnNovoProduto)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(216, 216, 216))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(157, 157, 157))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addGap(41, 41, 41)
                        .addComponent(btnExcluir)
                        .addGap(40, 40, 40)
                        .addComponent(btnVisualizar)
                        .addGap(40, 40, 40)
                        .addComponent(btnAtualizar)
                        .addGap(66, 66, 66))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBuscaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisar)
                    .addComponent(btnNovoProduto))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnVisualizar)
                    .addComponent(btnAtualizar))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        setSize(900, 700);
    }
    private void txtBuscaProdutoActionPerformed(java.awt.event.ActionEvent evt) {
        pesquisarProdutos();
    }
    private ProdutoDAO produtoDAO;
    private List<Produto> produtosAtuais;
    private void inicializarTela() {
        System.out.println("DEBUG: Inicializando TelaGerenciarProdutos");
        produtoDAO = new ProdutoDAOImpl();
        produtosAtuais = new ArrayList<>();
        configurarEventos();
        carregarProdutos();
        txtBuscaProduto.setText("");
        System.out.println("DEBUG: TelaGerenciarProdutos inicializada com sucesso");
    }
    private void configurarEventos() {
        btnPesquisar.addActionListener(e -> pesquisarProdutos());
        btnNovoProduto.addActionListener(e -> novoProduto());
        btnEditar.addActionListener(e -> editarProduto());
        btnExcluir.addActionListener(e -> excluirProduto());
        btnVisualizar.addActionListener(e -> visualizarProduto());
        btnAtualizar.addActionListener(e -> atualizarEstoque());
        btnVoltar.addActionListener(e -> voltar());
    }
    private void carregarProdutos() {
        try {
            produtosAtuais = produtoDAO.listarTodos();
            atualizarTabela(produtosAtuais);
        } catch (Exception e) {
            mostrarErro("Erro ao carregar produtos: " + e.getMessage());
        }
    }
    private void pesquisarProdutos() {
        String nome = txtBuscaProduto.getText().trim();
        String categoria = (String) cmbCategoria.getSelectedItem();
        String marca = (String) cmbMarca.getSelectedItem();
        String tamanho = (String) cmbTamanho.getSelectedItem();
        try {
            if ("Todas".equals(categoria)) categoria = null;
            if ("Todas".equals(marca)) marca = null;
            if ("Todos".equals(tamanho)) tamanho = null;
            if (nome.isEmpty() && categoria == null && marca == null && tamanho == null) {
                carregarProdutos();
            } else {
                produtosAtuais = produtoDAO.buscarComFiltros(nome, categoria, marca, tamanho);
                atualizarTabela(produtosAtuais);
            }
        } catch (Exception e) {
            mostrarErro("Erro ao pesquisar produtos: " + e.getMessage());
        }
    }
    private void aplicarFiltros() {
    }
    private void atualizarTabela(List<Produto> produtos) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tbProdutos.getModel();
        model.setRowCount(0);
        for (Produto produto : produtos) {
            Object[] row = {
                produto.getCodigo(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getMarca(),
                produto.getCor(),
                produto.getTamanho(),
                String.format("R$ %.2f", produto.getPrecoVenda()),
                produto.getQuantidadeEstoque(),
                "✏️ 🗑️ 👁️"
            };
            model.addRow(row);
        }
        atualizarContador(produtos.size());
    }
    private void atualizarContador(int quantidade) {
        setTitle("Gerenciar Produtos - Sistema de Estoque (" + quantidade + " produtos)");
    }
    private void novoProduto() {
        System.out.println("DEBUG: novoProduto() chamado!");
        mostrarMensagem("Use o menu 'Cadastros' → 'Cadastrar Produto' para adicionar novos produtos");
    }
    private void editarProduto() {
        int row = tbProdutos.getSelectedRow();
        if (row == -1) {
            mostrarMensagem("Selecione um produto para editar");
            return;
        }
        Produto produto = produtosAtuais.get(row);
        mostrarMensagem("Editando produto: " + produto.getNome());
    }
    private void excluirProduto() {
        int row = tbProdutos.getSelectedRow();
        if (row == -1) {
            mostrarMensagem("Selecione um produto para excluir");
            return;
        }
        Produto produto = produtosAtuais.get(row);
        int opcao = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja excluir o produto '" + produto.getNome() + "'?",
            "Confirmar Exclusão",
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (opcao == javax.swing.JOptionPane.YES_OPTION) {
            try {
                if (produtoDAO.excluir(produto.getId())) {
                    mostrarMensagem("Produto excluído com sucesso!");
                    carregarProdutos();
                } else {
                    mostrarErro("Erro ao excluir produto");
                }
            } catch (Exception e) {
                mostrarErro("Erro ao excluir produto: " + e.getMessage());
            }
        }
    }
    private void visualizarProduto() {
        int row = tbProdutos.getSelectedRow();
        if (row == -1) {
            mostrarMensagem("Selecione um produto para visualizar");
            return;
        }
        Produto produto = produtosAtuais.get(row);
        mostrarDetalhesProduto(produto);
    }
    private void atualizarEstoque() {
        int row = tbProdutos.getSelectedRow();
        if (row == -1) {
            mostrarMensagem("Selecione um produto para atualizar estoque");
            return;
        }
        Produto produto = produtosAtuais.get(row);
        String input = javax.swing.JOptionPane.showInputDialog(
            this,
            "Nova quantidade de estoque para '" + produto.getNome() + "':",
            "Atualizar Estoque",
            javax.swing.JOptionPane.QUESTION_MESSAGE
        );
        if (input != null && !input.trim().isEmpty()) {
            try {
                int novaQuantidade = Integer.parseInt(input.trim());
                if (produtoDAO.atualizarEstoque(produto.getId(), novaQuantidade)) {
                    mostrarMensagem("Estoque atualizado com sucesso!");
                    carregarProdutos();
                } else {
                    mostrarErro("Erro ao atualizar estoque");
                }
            } catch (NumberFormatException e) {
                mostrarErro("Quantidade inválida. Digite um número inteiro.");
            } catch (Exception e) {
                mostrarErro("Erro ao atualizar estoque: " + e.getMessage());
            }
        }
    }
    private void voltar() {
        this.dispose();
    }
    private void mostrarDetalhesProduto(Produto produto) {
        StringBuilder detalhes = new StringBuilder();
        detalhes.append("=== DETALHES DO PRODUTO ===\n\n");
        detalhes.append("Código: ").append(produto.getCodigo()).append("\n");
        detalhes.append("Nome: ").append(produto.getNome()).append("\n");
        detalhes.append("Categoria: ").append(produto.getCategoria()).append("\n");
        detalhes.append("Marca: ").append(produto.getMarca()).append("\n");
        detalhes.append("Cor: ").append(produto.getCor()).append("\n");
        detalhes.append("Tamanho: ").append(produto.getTamanho()).append("\n");
        detalhes.append("Material: ").append(produto.getMaterial()).append("\n");
        detalhes.append("Preço de Custo: R$ ").append(String.format("%.2f", produto.getPrecoCusto())).append("\n");
        detalhes.append("Preço de Venda: R$ ").append(String.format("%.2f", produto.getPrecoVenda())).append("\n");
        detalhes.append("Estoque Atual: ").append(produto.getQuantidadeEstoque()).append("\n");
        detalhes.append("Estoque Mínimo: ").append(produto.getQuantidadeMinima()).append("\n");
        detalhes.append("Status: ").append(produto.isAtivo() ? "Ativo" : "Inativo").append("\n");
        detalhes.append("Data de Cadastro: ").append(produto.getDataCadastro()).append("\n");
        detalhes.append("Última Atualização: ").append(produto.getUltimaAtualizacao()).append("\n");
        if (produto.getDescricao() != null && !produto.getDescricao().isEmpty()) {
            detalhes.append("\nDescrição: ").append(produto.getDescricao());
        }
        javax.swing.JOptionPane.showMessageDialog(
            this,
            detalhes.toString(),
            "Detalhes do Produto",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }
    private void mostrarMensagem(String mensagem) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Informação",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }
    private void mostrarErro(String mensagem) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Erro",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovoProduto;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<String> cmbMarca;
    private javax.swing.JComboBox<String> cmbTamanho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbProdutos;
    private javax.swing.JTextField txtBuscaProduto;
}
