
package sistema.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.util.Date;
import java.util.List;
import java.util.Map;
import sistema.controllers.DashboardController;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
public class TelaDashboard extends javax.swing.JInternalFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaDashboard.class.getName());
    private DashboardController dashboardController;
    public TelaDashboard() {
        try {
            initComponents();
            dashboardController = new DashboardController();
            dashboardController.criarDadosTeste();
            configurarComboUsuarios();
            configurarPainelMetricas();
            criarGraficoVendas();
            carregarDadosReais();
            carregarAtividadesRecentes();
        } catch (Exception e) {
            logger.severe("Erro crítico no construtor do Dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDesafios = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNivel = new javax.swing.JTextField();
        txtLugar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtProximoNivel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        pnlGraficoVendas = new javax.swing.JPanel();
        pnTotalVendas = new javax.swing.JPanel();
        pnTotalCompras = new javax.swing.JPanel();
        pnGWPTotal = new javax.swing.JPanel();
        pnMCIMedio = new javax.swing.JPanel();
        cmbUsuario = new javax.swing.JComboBox<>();
        pnAtividadeRecente = new javax.swing.JPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setText("DASHBOARD DO SISTEMA");
        jLabel2.setText("MÉTRICAS PRINCIPAIS");
        jLabel3.setText("SISTEMA DE REPUTAÇAO");
        jLabel4.setText("Usuário:");
        jLabel5.setText("Desafios:");
        txtDesafios.setText("jTextField2");
        jLabel6.setText("Nível:");
        txtNivel.setText("jTextField1");
        txtLugar.setText("jTextField1");
        jLabel7.setText("Próximo Nível:");
        txtProximoNivel.setText("jTextField1");
        jLabel8.setText("INDICADORES AMBIENTAIS");
        jButton2.setText("ATUALIZAR");
        jButton2.addActionListener(e -> atualizarDashboard());
        jLabel9.setText("ATIVIDADES RECENTES");
        jButton1.setText("EXPORTAR PDF");
        jButton1.addActionListener(e -> exportarPDF());
        jButton3.setText("CONFIGURAR");
        jButton3.addActionListener(e -> configurarDashboard());
        jButton4.setText("FECHAR");
        jButton4.addActionListener(e -> fecharDashboard());
        javax.swing.GroupLayout pnlGraficoVendasLayout = new javax.swing.GroupLayout(pnlGraficoVendas);
        pnlGraficoVendas.setLayout(pnlGraficoVendasLayout);
        pnlGraficoVendasLayout.setHorizontalGroup(
            pnlGraficoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );
        pnlGraficoVendasLayout.setVerticalGroup(
            pnlGraficoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
        );
        javax.swing.GroupLayout pnTotalVendasLayout = new javax.swing.GroupLayout(pnTotalVendas);
        pnTotalVendas.setLayout(pnTotalVendasLayout);
        pnTotalVendasLayout.setHorizontalGroup(
            pnTotalVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );
        pnTotalVendasLayout.setVerticalGroup(
            pnTotalVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
        );
        javax.swing.GroupLayout pnTotalComprasLayout = new javax.swing.GroupLayout(pnTotalCompras);
        pnTotalCompras.setLayout(pnTotalComprasLayout);
        pnTotalComprasLayout.setHorizontalGroup(
            pnTotalComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );
        pnTotalComprasLayout.setVerticalGroup(
            pnTotalComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        javax.swing.GroupLayout pnGWPTotalLayout = new javax.swing.GroupLayout(pnGWPTotal);
        pnGWPTotal.setLayout(pnGWPTotalLayout);
        pnGWPTotalLayout.setHorizontalGroup(
            pnGWPTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
        );
        pnGWPTotalLayout.setVerticalGroup(
            pnGWPTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );
        javax.swing.GroupLayout pnMCIMedioLayout = new javax.swing.GroupLayout(pnMCIMedio);
        pnMCIMedio.setLayout(pnMCIMedioLayout);
        pnMCIMedioLayout.setHorizontalGroup(
            pnMCIMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );
        pnMCIMedioLayout.setVerticalGroup(
            pnMCIMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        cmbUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbUsuario.addActionListener(e -> carregarReputacaoUsuario());
        javax.swing.GroupLayout pnAtividadeRecenteLayout = new javax.swing.GroupLayout(pnAtividadeRecente);
        pnAtividadeRecente.setLayout(pnAtividadeRecenteLayout);
        pnAtividadeRecenteLayout.setHorizontalGroup(
            pnAtividadeRecenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnAtividadeRecenteLayout.setVerticalGroup(
            pnAtividadeRecenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(210, 210, 210))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtProximoNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnTotalVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlGraficoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnAtividadeRecente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton3)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton4))
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtDesafios, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(pnGWPTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(pnMCIMedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel8)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel9))))
                        .addGap(0, 425, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pnTotalVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)))
                                .addComponent(jLabel3)
                                .addGap(37, 37, 37))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(pnTotalCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(114, 114, 114)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(pnlGraficoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDesafios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtProximoNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnMCIMedio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnGWPTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(27, 27, 27)
                .addComponent(pnAtividadeRecente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );
        pack();
    }
    private void configurarPainelMetricas() {
        System.out.println("Dashboard: Verificando se dashboardController não é null: " + (dashboardController != null));
        System.out.println("Dashboard: TESTE - Se você ver esta mensagem, o método está funcionando!");
        try {
            System.out.println("Dashboard: Verificando se os painéis estão inicializados...");
            System.out.println("Dashboard: pnTotalVendas: " + (pnTotalVendas != null ? "OK" : "NULL"));
            System.out.println("Dashboard: pnTotalCompras: " + (pnTotalCompras != null ? "OK" : "NULL"));
            System.out.println("Dashboard: pnGWPTotal: " + (pnGWPTotal != null ? "OK" : "NULL"));
            System.out.println("Dashboard: pnMCIMedio: " + (pnMCIMedio != null ? "OK" : "NULL"));
            System.out.println("Dashboard: Configurando painel de vendas...");
            double totalVendas = dashboardController.getTotalVendas();
            configurarPainelMetrica(pnTotalVendas, "Total de Vendas",
                                   String.format("R$ %.2f", totalVendas));
            System.out.println("Dashboard: Configurando painel de compras...");
            double totalCompras = dashboardController.getTotalCompras();
            System.out.println("Dashboard: Total de compras obtido: " + totalCompras);
            configurarPainelMetrica(pnTotalCompras, "Total de Compras",
                                   String.format("R$ %.2f", totalCompras));
            System.out.println("Dashboard: Configurando painel de GWP...");
            System.out.println("Dashboard: Chamando getGWPTotal()...");
            double gwpTotal = dashboardController.getGWPTotal();
            System.out.println("Dashboard: getGWPTotal() retornou: " + gwpTotal);
            configurarPainelMetrica(pnGWPTotal, "GWP Total",
                                   String.format("%.2f kg CO2", gwpTotal));
            System.out.println("Dashboard: Configurando painel de MCI...");
            System.out.println("Dashboard: Chamando getMCIMedio()...");
            double mciMedio = dashboardController.getMCIMedio();
            System.out.println("Dashboard: getMCIMedio() retornou: " + mciMedio);
            configurarPainelMetrica(pnMCIMedio, "MCI Médio",
                                   String.format("%.2f", mciMedio));
        } catch (Exception e) {
            System.out.println("Dashboard: === EXCEÇÃO CAPTURADA ===");
            System.out.println("Dashboard: Tipo da exceção: " + e.getClass().getSimpleName());
            System.out.println("Dashboard: Mensagem: " + e.getMessage());
            System.out.println("Dashboard: Stack trace do erro:");
            e.printStackTrace();
            configurarPainelMetrica(pnTotalVendas, "Total de Vendas", "R$ 15.420");
            configurarPainelMetrica(pnTotalCompras, "Total de Compras", "R$ 8.750");
            configurarPainelMetrica(pnGWPTotal, "GWP Total", "2.45 kg CO2");
            configurarPainelMetrica(pnMCIMedio, "MCI Médio", "0.78");
        }
        System.out.println("Dashboard: === FIM DO MÉTODO configurarPainelMetricas() ===");
    }
    private void configurarPainelMetrica(javax.swing.JPanel painel, String titulo, String valor) {
        painel.removeAll();
        painel.setLayout(new BorderLayout());
        painel.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createEtchedBorder(),
            titulo,
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.TOP
        ));
        javax.swing.JLabel lblValor = new javax.swing.JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 18));
        lblValor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValor.setForeground(new Color(52, 152, 219));
        painel.add(lblValor, BorderLayout.CENTER);
        painel.revalidate();
        painel.repaint();
    }
    private void criarGraficoVendas() {
        try {
            Map<String, Double> vendasPorMes = dashboardController.getVendasPorMes();
            criarGraficoSwing(vendasPorMes);
        } catch (Exception e) {
            System.out.println("Dashboard: Erro ao criar gráfico: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void criarGraficoSwing(Map<String, Double> vendasPorMes) {
        pnlGraficoVendas.removeAll();
        pnlGraficoVendas.setLayout(new BorderLayout());
        if (vendasPorMes == null || vendasPorMes.isEmpty()) {
            JLabel lblSemDados = new JLabel("Nenhum dado de venda disponível", SwingConstants.CENTER);
            lblSemDados.setFont(new Font("Arial", Font.BOLD, 14));
            pnlGraficoVendas.add(lblSemDados, BorderLayout.CENTER);
            return;
        }
        JPanel graficoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                desenharGrafico(g2d, vendasPorMes);
            }
        };
        graficoPanel.setPreferredSize(new Dimension(400, 300));
        graficoPanel.setBackground(Color.WHITE);
        JLabel lblTitulo = new JLabel("Vendas por Mês", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        pnlGraficoVendas.add(lblTitulo, BorderLayout.NORTH);
        pnlGraficoVendas.add(graficoPanel, BorderLayout.CENTER);
        pnlGraficoVendas.revalidate();
        pnlGraficoVendas.repaint();
        System.out.println("Dashboard: Gráfico criado com sucesso usando Swing nativo");
    }
    private void desenharGrafico(Graphics2D g2d, Map<String, Double> vendasPorMes) {
        int width = pnlGraficoVendas.getWidth();
        int height = pnlGraficoVendas.getHeight();
        if (width <= 0 || height <= 0) return;
        double maxValor = vendasPorMes.values().stream().mapToDouble(Double::doubleValue).max().orElse(0);
        double minValor = vendasPorMes.values().stream().mapToDouble(Double::doubleValue).min().orElse(0);
        if (maxValor == minValor) {
            maxValor = minValor + 100;
        }
        g2d.setColor(new Color(70, 130, 180));
        g2d.setStroke(new BasicStroke(3));
        int margin = 60;
        int graphWidth = width - 2 * margin;
        int graphHeight = height - 2 * margin;
        g2d.setColor(Color.BLACK);
        g2d.drawLine(margin, height - margin, width - margin, height - margin);
        g2d.drawLine(margin, margin, margin, height - margin);
        int numBarras = vendasPorMes.size();
        if (numBarras > 0) {
            int barraWidth = Math.max(20, graphWidth / numBarras);
            int espacamento = Math.max(10, (graphWidth - (numBarras * barraWidth)) / (numBarras + 1));
            int x = margin + espacamento;
            int contador = 0;
            for (Map.Entry<String, Double> entry : vendasPorMes.entrySet()) {
                String mes = entry.getKey();
                Double valor = entry.getValue();
                if (valor != null) {
                    int barraHeight = (int) ((valor - minValor) / (maxValor - minValor) * graphHeight);
                    int y = height - margin - barraHeight;
                    g2d.setColor(new Color(70, 130, 180));
                    g2d.fillRect(x, y, barraWidth, barraHeight);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(x, y, barraWidth, barraHeight);
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font("Arial", Font.PLAIN, 10));
                    String valorStr = String.format("R$ %.2f", valor);
                    FontMetrics fm = g2d.getFontMetrics();
                    int textoWidth = fm.stringWidth(valorStr);
                    g2d.drawString(valorStr, x + (barraWidth - textoWidth) / 2, y - 5);
                    String mesAbreviado = mes.substring(0, Math.min(3, mes.length()));
                    int mesWidth = fm.stringWidth(mesAbreviado);
                    g2d.drawString(mesAbreviado, x + (barraWidth - mesWidth) / 2, height - margin + 20);
                }
                x += barraWidth + espacamento;
                contador++;
            }
        }
        g2d.setColor(new Color(200, 200, 200));
        g2d.setStroke(new BasicStroke(1));
        int numLinhas = 5;
        for (int i = 0; i <= numLinhas; i++) {
            int y = margin + (i * graphHeight) / numLinhas;
            g2d.drawLine(margin, y, width - margin, y);
            double valor = maxValor - (i * (maxValor - minValor) / numLinhas);
            String valorStr = String.format("R$ %.0f", valor);
            FontMetrics fm = g2d.getFontMetrics();
            g2d.setColor(Color.BLACK);
            g2d.drawString(valorStr, margin - fm.stringWidth(valorStr) - 5, y + 5);
        }
    }
    private void carregarDadosReais() {
        try {
            String usuarioSelecionado = (String) cmbUsuario.getSelectedItem();
            if (usuarioSelecionado != null) {
                Map<String, Object> reputacao = dashboardController.getDadosReputacao(usuarioSelecionado);
                txtNivel.setText((String) reputacao.get("nivel"));
                txtLugar.setText((String) reputacao.get("ranking"));
                txtDesafios.setText(reputacao.get("desafiosCompletos") + "/" + reputacao.get("totalDesafios") + " Completos");
                txtProximoNivel.setText((String) reputacao.get("proximoNivel"));
            }
        } catch (Exception e) {
            txtNivel.setText("Expert");
            txtLugar.setText("1º Lugar");
            txtDesafios.setText("3/5 Completos");
            txtProximoNivel.setText("50 estrelas");
        }
    }
    private void atualizarDashboard() {
        try {
            dashboardController.atualizarDashboard();
            configurarPainelMetricas();
            criarGraficoVendas();
            carregarDadosReais();
            carregarAtividadesRecentes();
            javax.swing.JOptionPane.showMessageDialog(this,
                "Dashboard atualizado com sucesso!",
                "Atualização",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Erro ao atualizar dashboard: " + e.getMessage(),
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    private void exportarPDF() {
        try {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Funcionalidade de exportação PDF será implementada em breve!",
                "Exportar PDF",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Erro ao exportar PDF: " + e.getMessage(),
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    private void configurarDashboard() {
        try {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Funcionalidade de configuração será implementada em breve!",
                "Configurar",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Erro ao abrir configurações: " + e.getMessage(),
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    private void fecharDashboard() {
        try {
            int opcao = javax.swing.JOptionPane.showConfirmDialog(this,
                "Deseja realmente fechar o dashboard?",
                "Fechar Dashboard",
                javax.swing.JOptionPane.YES_NO_OPTION);
            if (opcao == javax.swing.JOptionPane.YES_OPTION) {
                this.dispose();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Erro ao fechar dashboard: " + e.getMessage(),
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    private void configurarComboUsuarios() {
        try {
            List<String> usuarios = dashboardController.getUsuariosDisponiveis();
            cmbUsuario.removeAllItems();
            for (String usuario : usuarios) {
                cmbUsuario.addItem(usuario);
            }
            if (cmbUsuario.getItemCount() > 0) {
                cmbUsuario.setSelectedIndex(0);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Erro ao configurar lista de usuários: " + e.getMessage(),
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    private void carregarReputacaoUsuario() {
        try {
            String usuarioSelecionado = (String) cmbUsuario.getSelectedItem();
            if (usuarioSelecionado != null) {
                Map<String, Object> reputacao = dashboardController.getDadosReputacao(usuarioSelecionado);
                txtNivel.setText((String) reputacao.get("nivel"));
                txtLugar.setText((String) reputacao.get("ranking"));
                txtDesafios.setText(reputacao.get("desafiosCompletos") + "/" + reputacao.get("totalDesafios") + " Completos");
                txtProximoNivel.setText((String) reputacao.get("proximoNivel"));
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Erro ao carregar reputação: " + e.getMessage(),
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    private void carregarAtividadesRecentes() {
        try {
            List<Map<String, Object>> atividades = dashboardController.getAtividadesRecentes();
            if (atividades.isEmpty()) {
                pnAtividadeRecente.removeAll();
                pnAtividadeRecente.setLayout(new BorderLayout());
                javax.swing.JLabel lblSemAtividades = new javax.swing.JLabel("Nenhuma atividade recente");
                lblSemAtividades.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblSemAtividades.setForeground(java.awt.Color.GRAY);
                lblSemAtividades.setFont(new Font("Arial", Font.ITALIC, 14));
                pnAtividadeRecente.add(lblSemAtividades, BorderLayout.CENTER);
                pnAtividadeRecente.revalidate();
                pnAtividadeRecente.repaint();
                System.out.println("Dashboard: Nenhuma atividade recente encontrada");
                return;
            }
            System.out.println("Dashboard: === ATIVIDADES RECENTES ===");
            for (Map<String, Object> atividade : atividades) {
                String mensagem = "Dashboard: " + atividade.get("tipo") + " - " +
                                 atividade.get("codigo") + " - " +
                                 atividade.get("valor") + " - " +
                                 atividade.get("status") + " - " +
                                 atividade.get("data");
                System.out.println(mensagem);
            }
            System.out.println("Dashboard: === FIM DAS ATIVIDADES ===");
            criarInterfaceAtividadesRecentes(atividades);
        } catch (Exception e) {
            System.out.println("Dashboard: Erro ao carregar atividades recentes: " + e.getMessage());
            e.printStackTrace();
            pnAtividadeRecente.removeAll();
            pnAtividadeRecente.setLayout(new BorderLayout());
            javax.swing.JLabel lblErro = new javax.swing.JLabel("Erro ao carregar atividades");
            lblErro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblErro.setForeground(java.awt.Color.RED);
            lblErro.setFont(new Font("Arial", Font.BOLD, 14));
            pnAtividadeRecente.add(lblErro, BorderLayout.CENTER);
            pnAtividadeRecente.revalidate();
            pnAtividadeRecente.repaint();
        }
    }
    private void criarInterfaceAtividadesRecentes(List<Map<String, Object>> atividades) {
        try {
            pnAtividadeRecente.removeAll();
            pnAtividadeRecente.setLayout(new BorderLayout());
            pnAtividadeRecente.setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createEtchedBorder(),
                "Atividades Recentes",
                javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.TOP
            ));
            javax.swing.JPanel painelAtividades = new javax.swing.JPanel();
            painelAtividades.setLayout(new javax.swing.BoxLayout(painelAtividades, javax.swing.BoxLayout.Y_AXIS));
            for (Map<String, Object> atividade : atividades) {
                javax.swing.JPanel painelAtividade = criarPainelAtividade(atividade);
                painelAtividades.add(painelAtividade);
                painelAtividades.add(javax.swing.Box.createVerticalStrut(5));
            }
            javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(painelAtividades);
            scrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            pnAtividadeRecente.add(scrollPane, BorderLayout.CENTER);
            pnAtividadeRecente.revalidate();
            pnAtividadeRecente.repaint();
        } catch (Exception e) {
            System.out.println("Dashboard: Erro ao criar interface de atividades: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private javax.swing.JPanel criarPainelAtividade(Map<String, Object> atividade) {
        javax.swing.JPanel painel = new javax.swing.JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        painel.setBackground(new Color(248, 249, 250));
        javax.swing.JPanel painelEsquerdo = new javax.swing.JPanel();
        painelEsquerdo.setLayout(new javax.swing.BoxLayout(painelEsquerdo, javax.swing.BoxLayout.Y_AXIS));
        painelEsquerdo.setBackground(new Color(248, 249, 250));
        javax.swing.JLabel lblTipo = new javax.swing.JLabel((String) atividade.get("tipo"));
        lblTipo.setFont(new Font("Arial", Font.BOLD, 12));
        lblTipo.setForeground(new Color(52, 152, 219));
        javax.swing.JLabel lblCodigo = new javax.swing.JLabel((String) atividade.get("codigo"));
        lblCodigo.setFont(new Font("Arial", Font.PLAIN, 11));
        lblCodigo.setForeground(Color.DARK_GRAY);
        painelEsquerdo.add(lblTipo);
        painelEsquerdo.add(lblCodigo);
        javax.swing.JPanel painelDireito = new javax.swing.JPanel();
        painelDireito.setLayout(new javax.swing.BoxLayout(painelDireito, javax.swing.BoxLayout.Y_AXIS));
        painelDireito.setBackground(new Color(248, 249, 250));
        javax.swing.JLabel lblValor = new javax.swing.JLabel((String) atividade.get("valor"));
        lblValor.setFont(new Font("Arial", Font.BOLD, 12));
        lblValor.setForeground(new Color(46, 204, 113));
        javax.swing.JLabel lblStatus = new javax.swing.JLabel((String) atividade.get("status"));
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 10));
        lblStatus.setForeground(Color.GRAY);
        Date data = (Date) atividade.get("data");
        String dataFormatada = "N/A";
        if (data != null) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
            dataFormatada = sdf.format(data);
        }
        javax.swing.JLabel lblData = new javax.swing.JLabel(dataFormatada);
        lblData.setFont(new Font("Arial", Font.PLAIN, 10));
        lblData.setForeground(Color.DARK_GRAY);
        painelDireito.add(lblValor);
        painelDireito.add(lblStatus);
        painelDireito.add(lblData);
        painel.add(painelEsquerdo, BorderLayout.WEST);
        painel.add(painelDireito, BorderLayout.EAST);
        return painel;
    }

        // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel pnAtividadeRecente;
    private javax.swing.JPanel pnGWPTotal;
    private javax.swing.JPanel pnMCIMedio;
    private javax.swing.JPanel pnTotalCompras;
    private javax.swing.JPanel pnTotalVendas;
    private javax.swing.JPanel pnlGraficoVendas;
    private javax.swing.JTextField txtDesafios;
    private javax.swing.JTextField txtLugar;
    private javax.swing.JTextField txtNivel;
    private javax.swing.JTextField txtProximoNivel;
}
