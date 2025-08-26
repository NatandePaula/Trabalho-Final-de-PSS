package sistema.views;
import sistema.models.Produto;
import sistema.models.Material;
import sistema.daos.ProdutoDAO;
import sistema.daos.ProdutoDAOImpl;
import sistema.daos.MaterialDAO;
import sistema.daos.MaterialDAOImpl;
import java.util.List;
public class TelaCadastrarProduto extends javax.swing.JInternalFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaCadastrarProduto.class.getName());
    public TelaCadastrarProduto() {
        initComponents();
        configurarEventos();
        configurarAparencia();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtCor = new javax.swing.JTextField();
        cmbMaterial = new javax.swing.JComboBox<>();
        txtPrecoCusto = new javax.swing.JTextField();
        txtPrecoVenda = new javax.swing.JTextField();
        txtQuantidadeEstoque = new javax.swing.JTextField();
        txtQuantidadeMinima = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        cmbMarca = new javax.swing.JComboBox<>();
        cmbTamanho = new javax.swing.JComboBox<>();
        cmbCategoria = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cmbNivelDefeito = new javax.swing.JComboBox<>();
        txtMassa = new javax.swing.JTextField();
        txtReciclado = new javax.swing.JTextField();
        txtVidaUtil = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtGWPEstimado = new javax.swing.JTextField();
        txtMCIEstimado = new javax.swing.JTextField();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Novo Produto");
        jLabel1.setText("Código");
        jLabel2.setText("Nome");
        jLabel3.setText("CAtegoria");
        jLabel4.setText("Marca");
        jLabel5.setText("Cor");
        jLabel6.setText("Tamanho");
        jLabel7.setText("Material");
        jLabel8.setText("Preço de Custo");
        jLabel9.setText("Preço de Venda");
        jLabel10.setText("Quantidade em Estoque");
        jLabel11.setText("Quantidade Mínima");
        jLabel12.setText("Descrição");
        btnSalvar.setText("SALVAR");
        btnCancelar.setText("CANCELAR");
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);
        cmbMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nike", "Adidas", "Puma", "Lacoste", "Calvin Klein", "Outras" }));
        cmbTamanho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PP", "P", "M", "G", "GG", "XG", "34", "36", "38", "40", "42", "44" }));
        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CAMISETA", "CALCA", "VESTIDO", "SAPATO", "ACESSORIO", "OUTRO" }));
        jLabel13.setText("SEÇÃO AMBIENTAL");
        jLabel14.setText("Massa por Unidade (kg):");
        jLabel15.setText("Material Reciclado (%):");
        jLabel16.setText("Vida Útil (anos):");
        jLabel17.setText("Nível de Defeito:");
        cmbNivelDefeito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pequeno", "Médio", "Grande" }));
        carregarMateriaisDoBanco();
        txtMassa.setText("");
        txtReciclado.setText("");
        txtVidaUtil.setText("");
        jLabel18.setText("GWP Estimado:");
        jLabel19.setText("MCI Estimado:");
        txtGWPEstimado.setText("");
        txtMCIEstimado.setText("");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQuantidadeMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cmbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtCodigo))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbMaterial))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtQuantidadeEstoque))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(btnSalvar)
                        .addGap(56, 56, 56)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jLabel13))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(txtMassa, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(txtReciclado, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtVidaUtil, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGWPEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbNivelDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMCIEstimado)))))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtQuantidadeEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtQuantidadeMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtMassa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtReciclado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtVidaUtil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cmbNivelDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtGWPEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtMCIEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addGap(14, 14, 14))
        );
        pack();
    }
    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<String> cmbMarca;
    private javax.swing.JComboBox<String> cmbNivelDefeito;
    private javax.swing.JComboBox<String> cmbTamanho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtGWPEstimado;
    private javax.swing.JTextField txtMCIEstimado;
    private javax.swing.JTextField txtMassa;
    private javax.swing.JComboBox<String> cmbMaterial;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPrecoCusto;
    private javax.swing.JTextField txtPrecoVenda;
    private javax.swing.JTextField txtQuantidadeEstoque;
    private javax.swing.JTextField txtQuantidadeMinima;
    private javax.swing.JTextField txtReciclado;
    private javax.swing.JTextField txtVidaUtil;
    private ProdutoDAO produtoDAO;
    private void configurarEventos() {
        produtoDAO = new ProdutoDAOImpl();
        btnSalvar.addActionListener(e -> salvarProduto());
        btnCancelar.addActionListener(e -> cancelar());
        txtMassa.addCaretListener(e -> calcularIndicadoresAmbientais());
        txtReciclado.addCaretListener(e -> calcularIndicadoresAmbientais());
        txtVidaUtil.addCaretListener(e -> calcularIndicadoresAmbientais());
        cmbNivelDefeito.addActionListener(e -> calcularIndicadoresAmbientais());
        cmbMaterial.addActionListener(e -> {
            calcularIndicadoresAmbientais();
            atualizarValoresPadraoMaterial();
        });
        txtCodigo.requestFocus();
    }
    private void configurarAparencia() {
    }
    private void carregarMateriaisDoBanco() {
        try {
            MaterialDAO materialDAO = new MaterialDAOImpl();
            List<Material> materiais = materialDAO.listarTodos();
            if (materiais.isEmpty()) {
                String[] materiaisPadrao = { "Algodão", "Poliester", "Lã", "Seda", "Linho" };
                cmbMaterial.setModel(new javax.swing.DefaultComboBoxModel<>(materiaisPadrao));
                logger.warning("Nenhum material encontrado no banco. Usando valores padrão.");
            } else {
                String[] nomesMateriais = new String[materiais.size()];
                for (int i = 0; i < materiais.size(); i++) {
                    nomesMateriais[i] = materiais.get(i).getNome();
                }
                cmbMaterial.setModel(new javax.swing.DefaultComboBoxModel<>(nomesMateriais));
                logger.info("Materiais carregados do banco: " + materiais.size());
            }
        } catch (Exception e) {
            logger.severe("Erro ao carregar materiais do banco: " + e.getMessage());
            String[] materiaisPadrao = { "Algodão", "Poliester", "Lã", "Seda", "Linho" };
            cmbMaterial.setModel(new javax.swing.DefaultComboBoxModel<>(materiaisPadrao));
        }
    }
    private Material obterMaterialPorNome(String nome) {
        try {
            MaterialDAO materialDAO = new MaterialDAOImpl();
            Material material = materialDAO.buscarPorNome(nome);
            if (material == null) {
                logger.warning("Material não encontrado: " + nome);
            } else {
                logger.info("Material encontrado: " + nome + " (ID: " + material.getId() + ")");
            }
            return material;
        } catch (Exception e) {
            logger.severe("Erro ao buscar material por nome '" + nome + "': " + e.getMessage());
            return null;
        }
    }
    private void calcularIndicadoresAmbientais() {
        try {
            String massaText = txtMassa.getText().trim();
            String recicladoText = txtReciclado.getText().trim();
            String vidaUtilText = txtVidaUtil.getText().trim();
            String nivelDefeito = (String) cmbNivelDefeito.getSelectedItem();
            String materialText = (String) cmbMaterial.getSelectedItem();
            if (massaText.isEmpty() || recicladoText.isEmpty() || vidaUtilText.isEmpty()) {
                txtGWPEstimado.setText("");
                txtMCIEstimado.setText("");
                return;
            }
            double massa = Double.parseDouble(massaText);
            double percentualReciclado = Double.parseDouble(recicladoText);
            int vidaUtil = Integer.parseInt(vidaUtilText);
            if (massa <= 0 || percentualReciclado < 0 || percentualReciclado > 100 || vidaUtil <= 0) {
                txtGWPEstimado.setText("Valores inválidos");
                txtMCIEstimado.setText("Valores inválidos");
                return;
            }
            String materialNome = materialText.isEmpty() ? "Algodão" : materialText;
            Material material = obterMaterialPorNome(materialNome);
            if (material != null) {
                double fatorEmissao = material.getFatorEmissao();
                double abatimento = obterAbatimentoDefeito(nivelDefeito);
                double gwp = fatorEmissao * massa * (1 - abatimento);
                double mci = calcularMCI(material, percentualReciclado, vidaUtil);
                txtGWPEstimado.setText(String.format("%.2f kg CO2e", gwp));
                txtMCIEstimado.setText(String.format("%.2f", mci));
            } else {
                txtGWPEstimado.setText("Material não encontrado");
                txtMCIEstimado.setText("Material não encontrado");
            }
        } catch (NumberFormatException e) {
            logger.warning("Erro de formato numérico: " + e.getMessage());
            txtGWPEstimado.setText("Erro nos números");
            txtMCIEstimado.setText("Erro nos números");
        } catch (Exception e) {
            logger.severe("Erro no cálculo dos indicadores ambientais: " + e.getMessage());
            txtGWPEstimado.setText("Erro no cálculo");
            txtMCIEstimado.setText("Erro no cálculo");
        }
    }
    private double calcularMCI(Material material, double percentualReciclado, int vidaUtil) {
        double reciclabilidadeMaterial = material.getReciclabilidade();
        double mci = (percentualReciclado / 100.0) * reciclabilidadeMaterial * (1.0 / Math.max(vidaUtil, 1));
        mci = Math.min(mci, 1.0);
        mci = Math.max(mci, 0.0);
        return mci;
    }
    private void atualizarValoresPadraoMaterial() {
        try {
            String materialNome = (String) cmbMaterial.getSelectedItem();
            if (materialNome != null) {
                Material material = obterMaterialPorNome(materialNome);
                if (material != null) {
                    if (txtVidaUtil.getText().trim().isEmpty()) {
                        txtVidaUtil.setText(String.valueOf(material.getVidaUtilPadrao()));
                    }
                    if (txtReciclado.getText().trim().isEmpty()) {
                        int percentualPadrao = (int) (material.getReciclabilidade() * 100);
                        txtReciclado.setText(String.valueOf(percentualPadrao));
                    }
                }
                calcularIndicadoresAmbientais();
            }
        } catch (Exception e) {
        }
    }
    private double obterAbatimentoDefeito(String nivelDefeito) {
        switch (nivelDefeito) {
            case "Pequeno": return 0.05;
            case "Médio": return 0.15;
            case "Grande": return 0.30;
            default: return 0.0;
        }
    }
    private void salvarProduto() {
        if (validarCampos()) {
            try {
                Produto novoProduto = new Produto();
                novoProduto.setCodigo(txtCodigo.getText().trim());
                novoProduto.setNome(txtNome.getText().trim());
                novoProduto.setCategoria((String) cmbCategoria.getSelectedItem());
                novoProduto.setMarca((String) cmbMarca.getSelectedItem());
                novoProduto.setCor(txtCor.getText().trim());
                novoProduto.setTamanho((String) cmbTamanho.getSelectedItem());
                String materialNome = (String) cmbMaterial.getSelectedItem();
                Material material = obterMaterialPorNome(materialNome);
                novoProduto.setMaterial(material);
                novoProduto.setPrecoCusto(Double.parseDouble(txtPrecoCusto.getText().trim()));
                novoProduto.setPrecoVenda(Double.parseDouble(txtPrecoVenda.getText().trim()));
                String qtdEstoque = txtQuantidadeEstoque.getText().trim();
                String qtdMinima = txtQuantidadeMinima.getText().trim();
                novoProduto.setQuantidadeEstoque(qtdEstoque.isEmpty() ? 0 : Integer.parseInt(qtdEstoque));
                novoProduto.setQuantidadeMinima(qtdMinima.isEmpty() ? 5 : Integer.parseInt(qtdMinima));
                novoProduto.setDescricao(txtDescricao.getText().trim());
                if (!txtMassa.getText().trim().isEmpty()) {
                    novoProduto.setMassa(Double.parseDouble(txtMassa.getText().trim()));
                }
                if (!txtReciclado.getText().trim().isEmpty()) {
                    novoProduto.setPercentualReciclado(Double.parseDouble(txtReciclado.getText().trim()));
                }
                if (!txtVidaUtil.getText().trim().isEmpty()) {
                    novoProduto.setVidaUtil(Integer.parseInt(txtVidaUtil.getText().trim()));
                }
                novoProduto.setNivelDefeito((String) cmbNivelDefeito.getSelectedItem());
                if (!txtGWPEstimado.getText().trim().isEmpty() && !txtGWPEstimado.getText().contains("Erro")) {
                    String gwpText = txtGWPEstimado.getText().replace(" kg CO2e", "");
                    gwpText = gwpText.replace(",", ".");
                    try {
                        novoProduto.setGwpEstimado(Double.parseDouble(gwpText));
                    } catch (NumberFormatException e) {
                        logger.warning("Erro ao converter GWP: " + gwpText + " - " + e.getMessage());
                    }
                }
                if (!txtMCIEstimado.getText().trim().isEmpty() && !txtMCIEstimado.getText().contains("Erro")) {
                    String mciText = txtMCIEstimado.getText().trim();
                    mciText = mciText.replace(",", ".");
                    try {
                        novoProduto.setMciEstimado(Double.parseDouble(mciText));
                    } catch (NumberFormatException e) {
                        logger.warning("Erro ao converter MCI: " + mciText + " - " + e.getMessage());
                    }
                }
                novoProduto.setFornecedor(null);
                novoProduto.setAtivo(true);
                if (produtoDAO.inserir(novoProduto)) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Produto cadastrado com sucesso!",
                        "Sucesso",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                    limparCampos();
                    txtCodigo.requestFocus();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Erro ao cadastrar produto. Tente novamente.",
                        "Erro",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Erro nos campos numéricos. Verifique os valores.",
                    "Erro de Validação",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Erro ao cadastrar produto: " + ex.getMessage(),
                    "Erro",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
        }
    }
    private boolean validarCampos() {
        if (txtCodigo.getText().trim().isEmpty()) {
            mostrarErro("Código é obrigatório", txtCodigo);
            return false;
        }
        if (txtNome.getText().trim().isEmpty()) {
            mostrarErro("Nome é obrigatório", txtNome);
            return false;
        }
        if (txtPrecoCusto.getText().trim().isEmpty()) {
            mostrarErro("Preço de custo é obrigatório", txtPrecoCusto);
            return false;
        }
        if (txtPrecoVenda.getText().trim().isEmpty()) {
            mostrarErro("Preço de venda é obrigatório", txtPrecoVenda);
            return false;
        }
        try {
            double custo = Double.parseDouble(txtPrecoCusto.getText().trim());
            double venda = Double.parseDouble(txtPrecoVenda.getText().trim());
            if (custo < 0 || venda < 0) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Preços não podem ser negativos",
                    "Erro de Validação",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
            if (venda < custo) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Preço de venda deve ser maior ou igual ao preço de custo",
                    "Erro de Validação",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Preços devem ser números válidos",
                "Erro de Validação",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        if (!txtQuantidadeEstoque.getText().trim().isEmpty()) {
            try {
                int qtd = Integer.parseInt(txtQuantidadeEstoque.getText().trim());
                if (qtd < 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Quantidade em estoque não pode ser negativa",
                        "Erro de Validação",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                    return false;
                }
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Quantidade em estoque deve ser um número inteiro válido",
                    "Erro de Validação",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        }
        if (!txtQuantidadeMinima.getText().trim().isEmpty()) {
            try {
                int qtdMin = Integer.parseInt(txtQuantidadeMinima.getText().trim());
                if (qtdMin < 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Quantidade mínima não pode ser negativa",
                        "Erro de Validação",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                    return false;
                }
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Quantidade mínima deve ser um número inteiro válido",
                    "Erro de Validação",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        }
        if (!txtMassa.getText().trim().isEmpty()) {
            try {
                double massa = Double.parseDouble(txtMassa.getText().trim());
                if (massa <= 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Massa deve ser maior que zero",
                        "Erro de Validação",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                    return false;
                }
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Massa deve ser um número válido",
                    "Erro de Validação",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        }
        if (!txtReciclado.getText().trim().isEmpty()) {
            try {
                double reciclado = Double.parseDouble(txtReciclado.getText().trim());
                if (reciclado < 0 || reciclado > 100) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Percentual reciclado deve estar entre 0 e 100",
                        "Erro de Validação",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                    return false;
                }
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Percentual reciclado deve ser um número válido",
                    "Erro de Validação",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        }
        if (!txtVidaUtil.getText().trim().isEmpty()) {
            try {
                int vidaUtil = Integer.parseInt(txtVidaUtil.getText().trim());
                if (vidaUtil <= 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Vida útil deve ser maior que zero",
                        "Erro de Validação",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                    return false;
                }
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Vida útil deve ser um número inteiro válido",
                    "Erro de Validação",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        }
        if (cmbMaterial.getSelectedItem() == null || cmbMaterial.getSelectedItem().toString().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Material é obrigatório",
                "Erro de Validação",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
            cmbMaterial.requestFocus();
            return false;
        }
        return true;
    }
    private void mostrarErro(String mensagem, javax.swing.JComponent campo) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Erro de Validação",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
        campo.requestFocus();
    }
    private void limparCampos() {
        txtCodigo.setText("");
        txtNome.setText("");
        txtCor.setText("");
        cmbMaterial.setSelectedIndex(0);
        txtPrecoCusto.setText("");
        txtPrecoVenda.setText("");
        txtQuantidadeEstoque.setText("0");
        txtQuantidadeMinima.setText("5");
        txtDescricao.setText("");
        txtMassa.setText("0.5");
        txtReciclado.setText("20");
        txtVidaUtil.setText("3");
        cmbMaterial.setSelectedIndex(0);
        cmbCategoria.setSelectedIndex(0);
        cmbMarca.setSelectedIndex(0);
        cmbTamanho.setSelectedIndex(0);
        cmbNivelDefeito.setSelectedIndex(0);
    }
    private void cancelar() {
        int opcao = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja cancelar o cadastro?",
            "Confirmar Cancelamento",
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (opcao == javax.swing.JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }
}
