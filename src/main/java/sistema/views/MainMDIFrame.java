package sistema.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MainMDIFrame extends JFrame {
    private JDesktopPane desktopPane;
    private JMenuBar menuBar;
    private int windowCount = 0;
    private static final int WINDOW_OFFSET = 30;
    private List<JInternalFrame> janelasAbertas = new ArrayList<>();
    
    public MainMDIFrame() {
        initializeComponents();
        setupMenu();
        setupLayout();
        setupWindow();
    }
    
    private void initializeComponents() {
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(240, 240, 240));
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }
    
    private void setupMenu() {
        menuBar = new JMenuBar();
        
        // Menu Arquivo
        JMenu menuArquivo = new JMenu("Arquivo");
        menuArquivo.setMnemonic(KeyEvent.VK_A);
        
        JMenuItem itemSair = new JMenuItem("Sair", KeyEvent.VK_S);
        itemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        itemSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(itemSair);
        
        // Menu Dashboard
        JMenu menuDashboard = new JMenu("Dashboard");
        menuDashboard.setMnemonic(KeyEvent.VK_D);
        
        JMenuItem itemDashboard = new JMenuItem("Dashboard", KeyEvent.VK_D);
        itemDashboard.addActionListener(e -> abrirTelaDashboard());
        menuDashboard.add(itemDashboard);
        
        // Menu Cadastros
        JMenu menuCadastros = new JMenu("Cadastros");
        menuCadastros.setMnemonic(KeyEvent.VK_C);
        
        JMenuItem itemCatalogoProdutos = new JMenuItem("Catálogo de Produtos", KeyEvent.VK_T);
        itemCatalogoProdutos.addActionListener(e -> abrirTelaCatalogoProdutos());
        
        JMenuItem itemCadastrarProduto = new JMenuItem("Cadastrar Produto", KeyEvent.VK_P);
        itemCadastrarProduto.addActionListener(e -> abrirTelaCadastrarProduto());
        
        JMenuItem itemGerenciarProdutos = new JMenuItem("Gerenciar Produtos", KeyEvent.VK_G);
        itemGerenciarProdutos.addActionListener(e -> abrirTelaGerenciarProdutos());
        
        menuCadastros.add(itemCatalogoProdutos);
        menuCadastros.add(itemCadastrarProduto);
        menuCadastros.add(itemGerenciarProdutos);
        
        // Menu Gestão
        JMenu menuGestao = new JMenu("Gestão");
        menuGestao.setMnemonic(KeyEvent.VK_G);
        
        JMenuItem itemGerenciarClientes = new JMenuItem("Gerenciar Clientes", KeyEvent.VK_L);
        itemGerenciarClientes.addActionListener(e -> abrirTelaGerenciarClientes());
        
        JMenuItem itemGerenciarEstoque = new JMenuItem("Gerenciar Estoque", KeyEvent.VK_E);
        itemGerenciarEstoque.addActionListener(e -> abrirTelaGerenciarEstoque());
        
        JMenuItem itemGerenciarCompras = new JMenuItem("Gerenciar Compras", KeyEvent.VK_O);
        itemGerenciarCompras.addActionListener(e -> abrirTelaGerenciarCompras());
        
        menuGestao.add(itemGerenciarClientes);
        menuGestao.add(itemGerenciarEstoque);
        menuGestao.add(itemGerenciarCompras);
        
        // Menu Vendas
        JMenu menuVendas = new JMenu("Vendas");
        menuVendas.setMnemonic(KeyEvent.VK_V);
        
        JMenuItem itemRealizarVenda = new JMenuItem("Realizar Venda", KeyEvent.VK_R);
        itemRealizarVenda.addActionListener(e -> abrirTelaRealizarVenda());
        
        menuVendas.add(itemRealizarVenda);
        
        // Menu Sistema
        JMenu menuSistema = new JMenu("Sistema");
        menuSistema.setMnemonic(KeyEvent.VK_S);
        
        JMenuItem itemConfiguracaoLOG = new JMenuItem("Configuração de LOG", KeyEvent.VK_L);
        itemConfiguracaoLOG.addActionListener(e -> abrirTelaConfiguracaoLOG());
        
        menuSistema.add(itemConfiguracaoLOG);
        
        // Menu Janela
        JMenu menuJanela = new JMenu("Janela");
        menuJanela.setMnemonic(KeyEvent.VK_J);
        
        JMenuItem itemCascata = new JMenuItem("Cascata", KeyEvent.VK_C);
        itemCascata.addActionListener(e -> organizarJanelasCascata());
        
        JMenuItem itemLadoALado = new JMenuItem("Lado a Lado", KeyEvent.VK_L);
        itemLadoALado.addActionListener(e -> organizarJanelasLadoALado());
        
        JMenuItem itemMinimizarTodas = new JMenuItem("Minimizar Todas", KeyEvent.VK_M);
        itemMinimizarTodas.addActionListener(e -> minimizarTodasJanelas());
        
        JMenuItem itemFecharTodas = new JMenuItem("Fechar Todas", KeyEvent.VK_F);
        itemFecharTodas.addActionListener(e -> fecharTodasJanelas());
        
        menuJanela.add(itemCascata);
        menuJanela.add(itemLadoALado);
        menuJanela.add(itemMinimizarTodas);
        menuJanela.add(itemFecharTodas);
        
        // Menu Ajuda
        JMenu menuAjuda = new JMenu("Ajuda");
        menuAjuda.setMnemonic(KeyEvent.VK_U);
        
        JMenuItem itemSobre = new JMenuItem("Sobre", KeyEvent.VK_S);
        itemSobre.addActionListener(e -> mostrarSobre());
        menuAjuda.add(itemSobre);
        
        // Adicionar menus à barra
        menuBar.add(menuArquivo);
        menuBar.add(menuDashboard);
        menuBar.add(menuCadastros);
        menuBar.add(menuGestao);
        menuBar.add(menuVendas);
        menuBar.add(menuSistema);
        menuBar.add(menuJanela);
        menuBar.add(menuAjuda);
        
        setJMenuBar(menuBar);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        add(desktopPane, BorderLayout.CENTER);
    }
    
    private void setupWindow() {
        setTitle("Sistema de Economia Circular - MDI");
        setSize(1400, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Métodos para abrir as telas
    private void abrirTelaDashboard() {
        if (telaJaEstaAberta(TelaDashboard.class)) {
            return;
        }
        TelaDashboard tela = new TelaDashboard();
        adicionarJanelaInterna(tela, "Dashboard");
    }
    
    private void abrirTelaCatalogoProdutos() {
        if (telaJaEstaAberta(TelaCatalogoProdutos.class)) {
            return;
        }
        TelaCatalogoProdutos tela = new TelaCatalogoProdutos();
        adicionarJanelaInterna(tela, "Catálogo de Produtos");
    }
    
    private void abrirTelaCadastrarProduto() {
        if (telaJaEstaAberta(TelaCadastrarProduto.class)) {
            return;
        }
        TelaCadastrarProduto tela = new TelaCadastrarProduto();
        adicionarJanelaInterna(tela, "Cadastrar Produto");
    }
    
    private void abrirTelaGerenciarProdutos() {
        if (telaJaEstaAberta(TelaGerenciarProdutos.class)) {
            return;
        }
        TelaGerenciarProdutos tela = new TelaGerenciarProdutos();
        adicionarJanelaInterna(tela, "Gerenciar Produtos");
    }
    
    private void abrirTelaGerenciarClientes() {
        if (telaJaEstaAberta(TelaGerenciarClientes.class)) {
            return;
        }
        TelaGerenciarClientes tela = new TelaGerenciarClientes();
        adicionarJanelaInterna(tela, "Gerenciar Clientes");
    }
    
    private void abrirTelaGerenciarEstoque() {
        if (telaJaEstaAberta(TelaGerenciarEstoque.class)) {
            return;
        }
        TelaGerenciarEstoque tela = new TelaGerenciarEstoque();
        adicionarJanelaInterna(tela, "Gerenciar Estoque");
    }
    
    private void abrirTelaGerenciarCompras() {
        if (telaJaEstaAberta(TelaGerenciarCompras.class)) {
            return;
        }
        TelaGerenciarCompras tela = new TelaGerenciarCompras();
        adicionarJanelaInterna(tela, "Gerenciar Compras");
    }
    
    private void abrirTelaRealizarVenda() {
        if (telaJaEstaAberta(TelaRealizarVenda.class)) {
            return;
        }
        TelaRealizarVenda tela = new TelaRealizarVenda();
        adicionarJanelaInterna(tela, "Realizar Venda");
    }
    
    private void abrirTelaConfiguracaoLOG() {
        if (telaJaEstaAberta(TelaConfiguracaoLOG.class)) {
            return;
        }
        TelaConfiguracaoLOG tela = new TelaConfiguracaoLOG();
        adicionarJanelaInterna(tela, "Configuração de LOG");
    }
    
    private boolean telaJaEstaAberta(Class<?> classeTela) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame.getClass().equals(classeTela)) {
                try {
                    frame.setSelected(true);
                    frame.toFront();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }
    
    private void adicionarJanelaInterna(JInternalFrame internalFrame, String titulo) {
        internalFrame.setTitle(titulo);
        internalFrame.setSize(800, 600);
        internalFrame.setLocation(windowCount * WINDOW_OFFSET, windowCount * WINDOW_OFFSET);
        
        // Configurar propriedades de redimensionamento
        internalFrame.setResizable(true);
        internalFrame.setClosable(true);
        internalFrame.setMaximizable(true);
        internalFrame.setIconifiable(true);
        
        // Adicionar à lista de janelas abertas
        janelasAbertas.add(internalFrame);
        
        // Adicionar ao desktop pane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
        
        // Centralizar a janela
        Dimension desktopSize = desktopPane.getSize();
        Dimension frameSize = internalFrame.getSize();
        int x = (desktopSize.width - frameSize.width) / 2;
        int y = (desktopSize.height - frameSize.height) / 2;
        try {
            internalFrame.setLocation(x, y);
        } catch (Exception e) {
            // Se não conseguir centralizar, usar posição padrão
            internalFrame.setLocation(windowCount * WINDOW_OFFSET, windowCount * WINDOW_OFFSET);
        }
        
        windowCount++;
        
        // Adicionar listener para remover da lista quando fechar
        internalFrame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                janelasAbertas.remove(internalFrame);
            }
        });
        
        // Garantir que a janela seja selecionada
        try {
            internalFrame.setSelected(true);
            internalFrame.toFront();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Métodos de organização de janelas
    private void organizarJanelasCascata() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        int x = 0, y = 0;
        for (JInternalFrame frame : frames) {
            try {
                frame.setLocation(x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
            x += WINDOW_OFFSET;
            y += WINDOW_OFFSET;
        }
    }
    
    private void organizarJanelasLadoALado() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        if (frames.length == 0) return;
        
        Dimension desktopSize = desktopPane.getSize();
        int cols = (int) Math.ceil(Math.sqrt(frames.length));
        int rows = (int) Math.ceil((double) frames.length / cols);
        
        int frameWidth = desktopSize.width / cols;
        int frameHeight = desktopSize.height / rows;
        
        for (int i = 0; i < frames.length; i++) {
            int row = i / cols;
            int col = i % cols;
            try {
                frames[i].setLocation(col * frameWidth, row * frameHeight);
                frames[i].setSize(frameWidth - 10, frameHeight - 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void minimizarTodasJanelas() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        for (JInternalFrame frame : frames) {
            try {
                frame.setIcon(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void fecharTodasJanelas() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        for (JInternalFrame frame : frames) {
            frame.dispose();
        }
        janelasAbertas.clear();
    }
    
    private void mostrarSobre() {
        JOptionPane.showMessageDialog(this,
            "Sistema de Economia Circular\n" +
            "Versão 1.0\n" +
            "Desenvolvido para gestão sustentável de produtos\n" +
            "© 2025 - Todos os direitos reservados",
            "Sobre o Sistema",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (Exception e) {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            //new MainMDIFrame().setVisible(true);
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });
    }
}
