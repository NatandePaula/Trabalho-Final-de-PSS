package sistema.views;
import sistema.views.TelaGerenciarProdutos;
import sistema.views.TelaRealizarVenda;
import sistema.views.TelaGerenciarClientes;
import sistema.views.TelaGerenciarCompras;
import sistema.views.TelaGerenciarEstoque;
import sistema.views.TelaCadastrarProduto;
public class TelaPrincipalForm extends javax.swing.JFrame {
    public TelaPrincipalForm() {
        initComponents();
        btnGerenciarEstoque.addActionListener(evt -> btnGerenciarEstoqueActionPerformed(evt));
        btnRealizarVenda.addActionListener(evt -> btnRealizarVendaActionPerformed(evt));
        btnGerenciarCompras.addActionListener(evt -> btnGerenciarComprasActionPerformed(evt));
        btnGerenciarClientes.addActionListener(evt -> btnGerenciarClientesActionPerformed(evt));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnGerenciarProdutos = new javax.swing.JButton();
        btnGerenciarEstoque = new javax.swing.JButton();
        btnRealizarVenda = new javax.swing.JButton();
        btnGerenciarCompras = new javax.swing.JButton();
        btnGerenciarClientes = new javax.swing.JButton();
        btnGerenciarFornecedores = new javax.swing.JButton();
        btnGerenciarUsuários = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEMA DE GESTÃO DE ESTOQUE - LOJA DE VESTUÁRIO");
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        jLabel1.setText("SISTEMA DE GESTÃO DE ESTOQUE");
        jLabel2.setText("Loja de Vestuário");
        btnGerenciarProdutos.setText("Gerenciar Prod.");
        btnGerenciarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarProdutosActionPerformed(evt);
            }
        });
        btnGerenciarEstoque.setText("Gerenciar Estoque");
        btnRealizarVenda.setText("Realizar Venda");
        btnGerenciarCompras.setText("Gerenciar Compras");
        btnGerenciarClientes.setText("Gerenciar Clientes");
        btnGerenciarFornecedores.setText("Gerenciar Fornecedores");
        btnGerenciarUsuários.setText("Gerenciar Usuários");
        btnRelatorios.setText("Relatórios");
        btnSair.setText("Sair");
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsuario.setText("Usuário: Não identificado");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGerenciarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGerenciarEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnGerenciarUsuários, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGerenciarCompras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnGerenciarClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(btnRelatorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGerenciarFornecedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRealizarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(218, 218, 218))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblUsuario)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGerenciarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGerenciarEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnRealizarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGerenciarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGerenciarCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGerenciarFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(btnRelatorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGerenciarUsuários, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(lblUsuario)
                .addGap(118, 118, 118))
        );
        pack();
    }
    private void btnGerenciarProdutosActionPerformed(java.awt.event.ActionEvent evt) {
        TelaGerenciarProdutos telaProdutos = new TelaGerenciarProdutos();
        telaProdutos.setVisible(true);
    }
    private void btnGerenciarEstoqueActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            System.out.println("=== BOTÃO GERENCIAR ESTOQUE CLICADO ===");
            System.out.println("Criando TelaGerenciarEstoque...");
            TelaGerenciarEstoque telaEstoque = new TelaGerenciarEstoque();
            telaEstoque.setVisible(true);
        } catch (Exception e) {
            System.err.println("=== ERRO AO ABRIR TELAGERENCIARESTOQUE ===");
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void btnRealizarVendaActionPerformed(java.awt.event.ActionEvent evt) {
        TelaRealizarVenda telaVenda = new TelaRealizarVenda();
        telaVenda.setVisible(true);
    }
    private void btnGerenciarComprasActionPerformed(java.awt.event.ActionEvent evt) {
        TelaGerenciarCompras telaCompras = new TelaGerenciarCompras();
        telaCompras.setVisible(true);
    }
    private void btnGerenciarClientesActionPerformed(java.awt.event.ActionEvent evt) {
        TelaGerenciarClientes telaClientes = new TelaGerenciarClientes();
        telaClientes.setVisible(true);
    }
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new TelaPrincipalForm().setVisible(true));
    }
    private javax.swing.JButton btnGerenciarClientes;
    private javax.swing.JButton btnGerenciarCompras;
    private javax.swing.JButton btnGerenciarEstoque;
    private javax.swing.JButton btnGerenciarFornecedores;
    private javax.swing.JButton btnGerenciarProdutos;
    private javax.swing.JButton btnGerenciarUsuários;
    private javax.swing.JButton btnRealizarVenda;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblUsuario;
}
