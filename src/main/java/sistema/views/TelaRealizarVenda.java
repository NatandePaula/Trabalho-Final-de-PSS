package sistema.views;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistema.models.Cliente;
import sistema.models.Produto;
import sistema.models.ItemVenda;
import sistema.models.Venda;
import sistema.daos.ClienteDAO;
import sistema.daos.ClienteDAOImpl;
import sistema.daos.ProdutoDAO;
import sistema.daos.ProdutoDAOImpl;
import sistema.daos.VendaDAO;
import sistema.daos.VendaDAOImpl;
public class TelaRealizarVenda extends javax.swing.JInternalFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaRealizarVenda.class.getName());
    private Cliente clienteAtual;
    private Produto produtoAtual;
    private List<ItemVenda> carrinho;
    private DefaultTableModel tableModel;
    private double subtotal = 0.0;
    private double desconto = 0.0;
    private double total = 0.0;
    private ClienteDAO clienteDAO;
    private ProdutoDAO produtoDAO;
    private VendaDAO vendaDAO;
    public TelaRealizarVenda() {
        initComponents();
        clienteDAO = new ClienteDAOImpl();
        produtoDAO = new ProdutoDAOImpl();
        vendaDAO = new VendaDAOImpl();
        inicializarTela();
        configurarEventos();
    }
    private void inicializarTela() {
        carrinho = new ArrayList<>();
        configurarTabela();
        limparCampos();
        atualizarResumo();
    }
    private void configurarTabela() {
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Código", "Nome", "QTD", "Preço Unit.", "SubTotal" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbCarrinho.setModel(tableModel);
        tbCarrinho.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }
    private void configurarEventos() {
        btnBuscarCliente.addActionListener(e -> buscarCliente());
        btnBuscarProduto.addActionListener(e -> buscarProduto());
        btnAdicionarQuantidade.addActionListener(e -> adicionarAoCarrinho());
        btnLimparCarrinho.addActionListener(e -> limparCarrinho());
        btnCancelar.addActionListener(e -> cancelarVenda());
        btnFinalizarVenda.addActionListener(e -> finalizarVenda());
        txtCPF.addActionListener(e -> buscarCliente());
        txtCodigo.addActionListener(e -> buscarProduto());
    }
    private void buscarCliente() {
        String cpf = txtCPF.getText().trim();
        if (cpf.isEmpty()) {
            mostrarErro("Digite o CPF do cliente!");
            return;
        }
        try {
            clienteAtual = clienteDAO.buscarPorCPF(cpf);
            if (clienteAtual != null) {
                txtNomeBuscado.setText(clienteAtual.getNome());
                txtCPFBuscado.setText(clienteAtual.getCpf());
                mostrarMensagem("Cliente encontrado: " + clienteAtual.getNome());
            } else {
                mostrarErro("Cliente não encontrado! CPF: " + cpf);
                limparCliente();
            }
        } catch (Exception e) {
            logger.severe("Erro ao buscar cliente: " + e.getMessage());
            mostrarErro("Erro ao buscar cliente: " + e.getMessage());
        }
    }
    private void buscarProduto() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.isEmpty()) {
            mostrarErro("Digite o código do produto!");
            return;
        }
        try {
            produtoAtual = produtoDAO.buscarPorCodigo(codigo);
            if (produtoAtual != null) {
                txtNomeProduto.setText(produtoAtual.getNome());
                txtPrecoProduto.setText(String.format("R$ %.2f", produtoAtual.getPrecoVenda()));
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
    private void adicionarAoCarrinho() {
        if (produtoAtual == null) {
            mostrarErro("Busque um produto primeiro!");
            return;
        }
        try {
            int quantidade = Integer.parseInt(txtQuantidade.getText().trim());
            if (quantidade <= 0) {
                mostrarErro("Quantidade deve ser maior que zero!");
                return;
            }
            for (ItemVenda item : carrinho) {
                if (item.getProduto().getCodigo().equals(produtoAtual.getCodigo())) {
                    item.setQuantidade(item.getQuantidade() + quantidade);
                    atualizarTabela();
                    atualizarResumo();
                    mostrarMensagem("Quantidade atualizada no carrinho!");
                    return;
                }
            }
            ItemVenda novoItem = new ItemVenda();
            novoItem.setProduto(produtoAtual);
            novoItem.setQuantidade(quantidade);
            novoItem.setPrecoUnitario(produtoAtual.getPrecoVenda());
            carrinho.add(novoItem);
            atualizarTabela();
            atualizarResumo();
            mostrarMensagem("Produto adicionado ao carrinho!");
            limparProduto();
        } catch (NumberFormatException e) {
            mostrarErro("Quantidade inválida!");
        } catch (Exception e) {
            logger.severe("Erro ao adicionar ao carrinho: " + e.getMessage());
            mostrarErro("Erro ao adicionar ao carrinho: " + e.getMessage());
        }
    }
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (ItemVenda item : carrinho) {
            double subtotalItem = item.getPrecoUnitario() * item.getQuantidade();
            tableModel.addRow(new Object[] {
                item.getProduto().getCodigo(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                String.format("R$ %.2f", item.getPrecoUnitario()),
                String.format("R$ %.2f", subtotalItem)
            });
        }
    }
    private void atualizarResumo() {
        subtotal = 0.0;
        for (ItemVenda item : carrinho) {
            subtotal += item.getPrecoUnitario() * item.getQuantidade();
        }
        total = subtotal - desconto;
        txtSubtotal.setText(String.format("R$ %.2f", subtotal));
        txtDesconto.setText(String.format("R$ %.2f", desconto));
        txtTotal.setText(String.format("R$ %.2f", total));
    }
    private void limparCarrinho() {
        carrinho.clear();
        atualizarTabela();
        atualizarResumo();
        mostrarMensagem("Carrinho limpo!");
    }
    private void cancelarVenda() {
        int opcao = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja cancelar a venda?",
            "Cancelar Venda",
            JOptionPane.YES_NO_OPTION
        );
        if (opcao == JOptionPane.YES_OPTION) {
            limparCampos();
            mostrarMensagem("Venda cancelada!");
        }
    }
    private void finalizarVenda() {
        if (clienteAtual == null) {
            mostrarErro("Selecione um cliente primeiro!");
            return;
        }
        if (carrinho.isEmpty()) {
            mostrarErro("Adicione produtos ao carrinho primeiro!");
            return;
        }
        try {
            Venda venda = new Venda();
            venda.setCliente(clienteAtual);
            venda.setCodigo(vendaDAO.obterProximoNumeroVenda());
            venda.setFormaPagamento("DINHEIRO");
            venda.setStatus("CONCLUIDA");
            venda.setSubtotal(subtotal);
            venda.setDesconto(desconto);
            venda.setTotal(total);
            venda.setObservacoes("Venda realizada via sistema");
            for (ItemVenda item : carrinho) {
                venda.adicionarItem(item);
            }
            if (vendaDAO.inserir(venda)) {
                String mensagem = String.format(
                    "Venda finalizada com sucesso!\n\n" +
                    "Número: %s\n" +
                    "Cliente: %s\n" +
                    "Total: R$ %.2f\n" +
                    "Itens: %d",
                    venda.getCodigo(),
                    clienteAtual.getNome(),
                    total,
                    carrinho.size()
                );
                JOptionPane.showMessageDialog(
                    this,
                    mensagem,
                    "Venda Finalizada",
                    JOptionPane.INFORMATION_MESSAGE
                );
                limparCampos();
            } else {
                mostrarErro("Erro ao salvar venda no banco de dados!");
            }
        } catch (Exception e) {
            logger.severe("Erro ao finalizar venda: " + e.getMessage());
            mostrarErro("Erro ao finalizar venda: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void limparCampos() {
        limparCliente();
        limparProduto();
        limparCarrinho();
        txtCPF.setText("");
        txtCodigo.setText("");
        txtQuantidade.setText("1");
    }
    private void limparCliente() {
        clienteAtual = null;
        txtNomeBuscado.setText("");
        txtCPFBuscado.setText("");
    }
    private void limparProduto() {
        produtoAtual = null;
        txtNomeProduto.setText("");
        txtPrecoProduto.setText("");
        txtCodigo.setText("");
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
        txtCPF = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNomeBuscado = new javax.swing.JTextField();
        txtCPFBuscado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBuscarProduto = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtNomeProduto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPrecoProduto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCarrinho = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btnAdicionarQuantidade = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtDesconto = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnLimparCarrinho = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFinalizarVenda = new javax.swing.JButton();
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        jLabel1.setText("REALIZAR VENDA");
        jLabel2.setText("CLIENTE:");
        jLabel3.setText("CPF:");
        txtCPF.setText("");
        btnBuscarCliente.setText("BUSCAR");
        jLabel4.setText("Nome:");
        jLabel5.setText("CPF:");
        txtNomeBuscado.setText("");
        txtCPFBuscado.setText("");
        jLabel6.setText("PRODUTOS:");
        jLabel7.setText("Código:");
        txtCodigo.setText("");
        btnBuscarProduto.setText("BUSCAR");
        jLabel8.setText("Nome:");
        txtNomeProduto.setText("");
        jLabel9.setText("Preço:");
        txtPrecoProduto.setText("");
        jLabel10.setText("CARRINHO:");
        tbCarrinho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "QTD", "Preço Unit.", "SubTotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbCarrinho);
        jLabel11.setText("QTD:");
        txtQuantidade = new javax.swing.JTextField();
        txtQuantidade.setText("1");
        txtQuantidade.setPreferredSize(new java.awt.Dimension(50, 20));
        btnAdicionarQuantidade.setText("+");
        jLabel12.setText("RESUMO:");
        jLabel13.setText("Subtotal:");
        jLabel14.setText("Desconto");
        jLabel15.setText("Total:");
        txtSubtotal.setText("R$ 0,00");
        txtDesconto.setText("R$ 0,00");
        txtTotal.setText("R$ 0,00");
        btnLimparCarrinho.setText("LIMPAR CARRINHO");
        btnCancelar.setText("CANCELAR");
        btnFinalizarVenda.setText("FINALIZAR VENDA");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(248, 248, 248))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeBuscado)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarCliente)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCPFBuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtNomeProduto))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarProduto)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAdicionarQuantidade))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(btnLimparCarrinho)
                        .addGap(35, 35, 35)
                        .addComponent(btnCancelar)
                        .addGap(37, 37, 37)
                        .addComponent(btnFinalizarVenda)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtNomeBuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCPFBuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionarQuantidade))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimparCarrinho)
                    .addComponent(btnCancelar)
                    .addComponent(btnFinalizarVenda))
                .addGap(32, 32, 32))
        );
        setSize(900, 700);
    }
    private javax.swing.JButton btnAdicionarQuantidade;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarProduto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFinalizarVenda;
    private javax.swing.JButton btnLimparCarrinho;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCarrinho;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtCPFBuscado;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDesconto;
    private javax.swing.JTextField txtNomeBuscado;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtPrecoProduto;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtQuantidade;
}
