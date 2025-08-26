package sistema.views;
import java.util.List;
import sistema.models.Fornecedor;
import sistema.models.Produto;
import sistema.models.ItemCompra;
import sistema.models.Compra;
import sistema.daos.FornecedorDAO;
import sistema.daos.FornecedorDAOImpl;
import sistema.daos.ProdutoDAO;
import sistema.daos.ProdutoDAOImpl;
import sistema.daos.CompraDAO;
import sistema.daos.CompraDAOImpl;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
public class TelaGerenciarCompras extends javax.swing.JInternalFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaGerenciarCompras.class.getName());
    private Fornecedor fornecedorAtual;
    private Produto produtoAtual;
    private List<ItemCompra> itensCompra;
    private DefaultTableModel tableModel;
    private double subtotal = 0.0;
    private double desconto = 0.0;
    private double total = 0.0;
    private FornecedorDAO fornecedorDAO;
    private ProdutoDAO produtoDAO;
    private CompraDAO compraDAO;
    public TelaGerenciarCompras() {
        initComponents();
        inicializarTela();
        configurarEventos();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCPFFornecedor = new javax.swing.JTextField();
        txtNomeFornecedor = new javax.swing.JTextField();
        btnBuscarFornecedor = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCódigoProduto = new javax.swing.JTextField();
        btnBuscarProduto = new javax.swing.JButton();
        txtNomeProduto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPrecoProduto = new javax.swing.JTextField();
        btnAdicionaACompra = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbItensCompra = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtDesconto = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnFinalizarCompra = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        jLabel1.setText("GERENCIAR COMPRAS");
        jLabel2.setText("Buscar Fornecedor por CPF");
        jLabel3.setText("Nome:");
        txtCPFFornecedor.setText("");
        txtNomeFornecedor.setText("");
        btnBuscarFornecedor.setText("BUSCAR");
        jLabel4.setText("CPF:");
        jTextField1.setText("");
        jLabel6.setText("Buscar Produto por Código");
        txtCódigoProduto.setText("");
        btnBuscarProduto.setText("BUSCAR");
        txtNomeProduto.setText("");
        jLabel8.setText("Preço");
        txtPrecoProduto.setText("");
        btnAdicionaACompra.setText("ADICIONAR À COMPRA");
        jLabel9.setText("Quantidade:");
        txtQuantidade.setText("1");
        jLabel10.setText("ITENS DA COMPRA");
        tbItensCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Quantidade", "Produto", "Preço", "Subtotal", "Ações"
            }
        ));
        jScrollPane1.setViewportView(tbItensCompra);
        jLabel11.setText("RESUMO:");
        jLabel12.setText("SubTotal: R$");
        jLabel13.setText("Desconto:");
        jLabel14.setText("Total: R$");
        txtSubtotal.setText("R$ 0,00");
        txtDesconto.setText("R$ 0,00");
        txtTotal.setText("R$ 0,00");
        btnFinalizarCompra.setText("FINALIZAR COMPRA");
        btnCancelar.setText("CANCELAR");
        btnLimpar.setText("LIMPAR");
        jLabel15.setText("Nome:");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnAdicionaACompra)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCPFFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(92, 92, 92)
                                        .addComponent(btnBuscarFornecedor))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCódigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(39, 39, 39)
                                        .addComponent(txtNomeProduto))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(201, 201, 201)))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBuscarProduto)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnFinalizarCompra)
                        .addGap(48, 48, 48)
                        .addComponent(btnCancelar)
                        .addGap(45, 45, 45)
                        .addComponent(btnLimpar)))
                .addGap(0, 52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCPFFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarFornecedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCódigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionaACompra)
                    .addComponent(jLabel9)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabel10)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizarCompra)
                    .addComponent(btnCancelar)
                    .addComponent(btnLimpar))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        setSize(900, 700);
    }
    private void inicializarTela() {
        fornecedorDAO = new FornecedorDAOImpl();
        produtoDAO = new ProdutoDAOImpl();
        compraDAO = new CompraDAOImpl();
        itensCompra = new ArrayList<>();
        configurarTabela();
        configurarCamposSomenteLeitura();
    }
    private void configurarTabela() {
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"Quantidade", "Produto", "Preço", "Subtotal", "Ações"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        tbItensCompra.setModel(tableModel);
    }
    private void configurarCamposSomenteLeitura() {
        txtNomeFornecedor.setEditable(false);
        txtNomeProduto.setEditable(false);
        txtPrecoProduto.setEditable(false);
        txtSubtotal.setEditable(false);
        txtTotal.setEditable(false);
    }
    private void configurarEventos() {
        btnBuscarFornecedor.addActionListener(e -> buscarFornecedor());
        btnBuscarProduto.addActionListener(e -> buscarProduto());
        btnAdicionaACompra.addActionListener(e -> adicionarItem());
        btnFinalizarCompra.addActionListener(e -> finalizarCompra());
        btnCancelar.addActionListener(e -> cancelarCompra());
        btnLimpar.addActionListener(e -> limparCampos());
    }
    private void buscarFornecedor() {
        String cpfCnpj = txtCPFFornecedor.getText().trim();
        if (cpfCnpj.isEmpty()) {
            mostrarErro("Digite o CPF/CNPJ do fornecedor!");
            return;
        }
        try {
            fornecedorAtual = fornecedorDAO.buscarPorCPFCNPJ(cpfCnpj);
            if (fornecedorAtual != null) {
                txtNomeFornecedor.setText(fornecedorAtual.getRazaoSocial());
                jTextField1.setText(fornecedorAtual.getCnpj());
                mostrarMensagem("Fornecedor encontrado: " + fornecedorAtual.getRazaoSocial());
            } else {
                if (cpfCnpj.equals("12.345.678/0001-90")) {
                    fornecedorAtual = new Fornecedor();
                    fornecedorAtual.setId(1L);
                    fornecedorAtual.setRazaoSocial("Fornecedor ABC Ltda");
                    fornecedorAtual.setNomeFantasia("ABC Confecções");
                    fornecedorAtual.setCnpj("12.345.678/0001-90");
                    txtNomeFornecedor.setText(fornecedorAtual.getRazaoSocial());
                    jTextField1.setText(fornecedorAtual.getCnpj());
                    mostrarMensagem("Fornecedor encontrado: " + fornecedorAtual.getRazaoSocial());
                } else {
                    mostrarErro("Fornecedor não encontrado! CPF/CNPJ: " + cpfCnpj);
                    limparFornecedor();
                }
            }
        } catch (Exception e) {
            logger.severe("Erro ao buscar fornecedor: " + e.getMessage());
            mostrarErro("Erro ao buscar fornecedor: " + e.getMessage());
        }
    }
    private void buscarProduto() {
        String codigo = txtCódigoProduto.getText().trim();
        if (codigo.isEmpty()) {
            mostrarErro("Digite o código do produto!");
            return;
        }
        try {
            produtoAtual = produtoDAO.buscarPorCodigo(codigo);
            if (produtoAtual != null) {
                txtNomeProduto.setText(produtoAtual.getNome());
                txtPrecoProduto.setText(String.format("R$ %.2f", produtoAtual.getPrecoCusto()));
                mostrarMensagem("Produto encontrado: " + produtoAtual.getNome());
            } else {
                mostrarErro("Produto não encontrado! Código: " + codigo);
                limparProduto();
            }
        } catch (Exception e) {
            logger.severe("Erro ao buscar produto: " + e.getMessage());
            mostrarErro("Erro ao buscar produto: " + e.getMessage());
        }
    }
    private void adicionarItem() {
        if (fornecedorAtual == null) {
            mostrarErro("Selecione um fornecedor primeiro!");
            return;
        }
        if (produtoAtual == null) {
            mostrarErro("Selecione um produto primeiro!");
            return;
        }
        try {
            int quantidade = Integer.parseInt(txtQuantidade.getText().trim());
            if (quantidade <= 0) {
                mostrarErro("Quantidade deve ser maior que zero!");
                return;
            }
            ItemCompra item = new ItemCompra();
            item.setProduto(produtoAtual);
            item.setQuantidade(quantidade);
            item.setPrecoUnitario(produtoAtual.getPrecoCusto());
            item.setSubtotal(produtoAtual.getPrecoCusto() * quantidade);
            itensCompra.add(item);
            atualizarTabela();
            atualizarResumo();
            limparProduto();
            mostrarMensagem("Item adicionado com sucesso!");
        } catch (NumberFormatException e) {
            mostrarErro("Quantidade inválida!");
        } catch (Exception e) {
            logger.severe("Erro ao adicionar item: " + e.getMessage());
            mostrarErro("Erro ao adicionar item: " + e.getMessage());
        }
    }
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (int i = 0; i < itensCompra.size(); i++) {
            ItemCompra item = itensCompra.get(i);
            Object[] row = {
                item.getQuantidade(),
                item.getProduto().getNome(),
                String.format("R$ %.2f", item.getPrecoUnitario()),
                String.format("R$ %.2f", item.getSubtotal()),
                "Remover"
            };
            tableModel.addRow(row);
        }
    }
    private void atualizarResumo() {
        subtotal = 0.0;
        for (ItemCompra item : itensCompra) {
            subtotal += item.getSubtotal();
        }
        try {
            String descontoStr = txtDesconto.getText().replace("R$ ", "").trim();
            if (!descontoStr.isEmpty()) {
                desconto = Double.parseDouble(descontoStr);
            } else {
                desconto = 0.0;
            }
        } catch (NumberFormatException e) {
            desconto = 0.0;
        }
        total = subtotal - desconto;
        txtSubtotal.setText(String.format("R$ %.2f", subtotal));
        txtTotal.setText(String.format("R$ %.2f", total));
    }
    private void finalizarCompra() {
        if (fornecedorAtual == null) {
            mostrarErro("Selecione um fornecedor primeiro!");
            return;
        }
        if (itensCompra.isEmpty()) {
            mostrarErro("Adicione produtos à compra primeiro!");
            return;
        }
        try {
            Compra compra = new Compra();
            compra.setFornecedor(fornecedorAtual);
            compra.setCodigo(compraDAO.obterProximoNumeroCompra());
            compra.setSubtotal(subtotal);
            compra.setImpostos(desconto);
            compra.setTotal(total);
            compra.setObservacoes("Compra realizada via sistema");
            for (ItemCompra item : itensCompra) {
                compra.adicionarItem(item);
            }
            if (compraDAO.inserir(compra)) {
                String mensagem = String.format(
                    "Compra finalizada com sucesso!\n\n" +
                    "Número: %s\n" +
                    "Fornecedor: %s\n" +
                    "Total: R$ %.2f\n" +
                    "Itens: %d",
                    compra.getCodigo(),
                    fornecedorAtual.getRazaoSocial(),
                    total,
                    itensCompra.size()
                );
                JOptionPane.showMessageDialog(
                    this,
                    mensagem,
                    "Compra Finalizada",
                    JOptionPane.INFORMATION_MESSAGE
                );
                limparCampos();
            } else {
                mostrarErro("Erro ao salvar compra no banco de dados!");
            }
        } catch (Exception e) {
            logger.severe("Erro ao finalizar compra: " + e.getMessage());
            mostrarErro("Erro ao finalizar compra: " + e.getMessage());
        }
    }
    private void cancelarCompra() {
        int opcao = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja cancelar a compra?",
            "Cancelar Compra",
            JOptionPane.YES_NO_OPTION
        );
        if (opcao == JOptionPane.YES_OPTION) {
            limparCampos();
            mostrarMensagem("Compra cancelada!");
        }
    }
    private void limparCampos() {
        limparFornecedor();
        limparProduto();
        limparCarrinho();
        txtCPFFornecedor.setText("");
        txtCódigoProduto.setText("");
        txtQuantidade.setText("1");
        txtDesconto.setText("R$ 0,00");
        atualizarResumo();
    }
    private void limparFornecedor() {
        fornecedorAtual = null;
        txtNomeFornecedor.setText("");
        jTextField1.setText("");
    }
    private void limparProduto() {
        produtoAtual = null;
        txtNomeProduto.setText("");
        txtPrecoProduto.setText("");
        txtCódigoProduto.setText("");
    }
    private void limparCarrinho() {
        itensCompra.clear();
        atualizarTabela();
        atualizarResumo();
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
    private javax.swing.JButton btnAdicionaACompra;
    private javax.swing.JButton btnBuscarFornecedor;
    private javax.swing.JButton btnBuscarProduto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFinalizarCompra;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbItensCompra;
    private javax.swing.JTextField txtCPFFornecedor;
    private javax.swing.JTextField txtCódigoProduto;
    private javax.swing.JTextField txtDesconto;
    private javax.swing.JTextField txtNomeFornecedor;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtPrecoProduto;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
}
