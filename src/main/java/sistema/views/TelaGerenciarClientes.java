package sistema.views;
import java.util.List;
import sistema.controllers.ClienteController;
import sistema.models.Cliente;
public class TelaGerenciarClientes extends javax.swing.JInternalFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaGerenciarClientes.class.getName());
            private ClienteController clienteController;
        private Cliente clienteAtual;
        private javax.swing.table.DefaultTableModel tableModel;
    public TelaGerenciarClientes() {
        initComponents();
        inicializarTela();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPesquisaCPF = new javax.swing.JTextField();
        txtPesquisaNome = new javax.swing.JTextField();
        txtPesquisaCidade = new javax.swing.JTextField();
        btnBuscarCPF = new javax.swing.JButton();
        btnBuscarNome = new javax.swing.JButton();
        btnBuscarCidade = new javax.swing.JButton();
        btnLimparCPF = new javax.swing.JButton();
        btnLimparNome = new javax.swing.JButton();
        btnLimparCidade = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        btnNovoCliente = new javax.swing.JButton();
        btnSalvarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnExcluirCliente = new javax.swing.JButton();
        btnLimparCliente = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        btListaClientes = new javax.swing.JTable();
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        jLabel1.setText("PESQUISAR CLIENTE:");
        jLabel2.setText("CPF:");
        jLabel3.setText("Nome:");
        jLabel4.setText("Cidade:");
        txtPesquisaCPF.setText("");
        txtPesquisaNome.setText("");
        txtPesquisaCidade.setText("");
        btnBuscarCPF.setText("BUSCAR");
        btnBuscarNome.setText("BUSCAR");
        btnBuscarCidade.setText("BUSCAR");
        btnLimparCPF.setText("LIMPAR");
        btnLimparNome.setText("LIMPAR");
        btnLimparCidade.setText("LIMPAR");
        jLabel5.setText("DADOS DO CLIENTE:");
        jLabel6.setText("Nome:");
        jLabel7.setText("CPF:");
        jLabel8.setText("Email:");
        jLabel9.setText("Telefone");
        jLabel10.setText("Endereço:");
        jLabel11.setText("Cidade:");
        jLabel12.setText("Data de Nascimento:");
        jLabel13.setText("Observações:");
        jLabel14.setText("Celular:");
        jLabel15.setText("Estado:");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        btnNovoCliente.setText("NOVO CLIENTE");
        btnSalvarCliente.setText("SALVAR");
        btnEditarCliente.setText("EDITAR");
        btnExcluirCliente.setText("EXCLUIR");
        btnLimparCliente.setText("LIMPAR");
        jLabel16.setText("LISTA DE CLIENTES");
        btListaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Cidade", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(btListaClientes);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPesquisaCPF))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(219, 219, 219)))
                                .addGap(26, 26, 26)
                                .addComponent(btnBuscarCPF)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimparCPF))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel14))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPesquisaNome)
                                            .addComponent(txtPesquisaCidade))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnBuscarNome)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnLimparNome))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnBuscarCidade)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnLimparCidade))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                            .addComponent(jTextField7)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(160, 160, 160))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnNovoCliente)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvarCliente)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEditarCliente)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirCliente)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimparCliente))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPesquisaCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCPF)
                    .addComponent(btnLimparCPF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPesquisaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarNome)
                    .addComponent(btnLimparNome))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPesquisaCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCidade)
                    .addComponent(btnLimparCidade))
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel14)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoCliente)
                    .addComponent(btnSalvarCliente)
                    .addComponent(btnEditarCliente)
                    .addComponent(btnExcluirCliente)
                    .addComponent(btnLimparCliente))
                .addGap(44, 44, 44)
                .addComponent(jLabel16)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        setSize(900, 700);
    }
    private javax.swing.JTable btListaClientes;
    private javax.swing.JButton btnBuscarCPF;
    private javax.swing.JButton btnBuscarCidade;
    private javax.swing.JButton btnBuscarNome;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnExcluirCliente;
    private javax.swing.JButton btnLimparCPF;
    private javax.swing.JButton btnLimparCidade;
    private javax.swing.JButton btnLimparCliente;
    private javax.swing.JButton btnLimparNome;
    private javax.swing.JButton btnNovoCliente;
    private javax.swing.JButton btnSalvarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField txtPesquisaCPF;
    private javax.swing.JTextField txtPesquisaCidade;
    private javax.swing.JTextField txtPesquisaNome;
    private void inicializarTela() {
        try {
            clienteController = new ClienteController();
            configurarTabela();
            configurarCamposSomenteLeitura();
            configurarEventos();
            limparCampos();
            carregarClientes();
        } catch (Exception e) {
            logger.severe("Erro ao inicializar tela: " + e.getMessage());
            mostrarErro("Erro ao inicializar tela: " + e.getMessage());
        }
    }
    private void configurarTabela() {
        tableModel = new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[]{"ID", "Nome", "CPF", "Cidade", "Status"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        btListaClientes.setModel(tableModel);
        btListaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }
    private void configurarCamposSomenteLeitura() {
        setCamposEditaveis(false);
    }
    private void setCamposEditaveis(boolean editavel) {
        jTextField1.setEnabled(editavel);
        jTextField2.setEnabled(editavel);
        jTextField3.setEnabled(editavel);
        jTextField4.setEnabled(editavel);
        jTextField6.setEnabled(editavel);
        jTextField5.setEnabled(editavel);
        jTextField8.setEnabled(editavel);
        jTextField7.setEnabled(editavel);
        jTextField9.setEnabled(editavel);
        jTextField10.setEnabled(editavel);
    }
    private void configurarEventos() {
        btnBuscarCPF.addActionListener(evt -> buscarPorCPF());
        btnBuscarNome.addActionListener(evt -> buscarPorNome());
        btnBuscarCidade.addActionListener(evt -> buscarPorCidade());
        btnLimparCPF.addActionListener(evt -> limparPesquisaCPF());
        btnLimparNome.addActionListener(evt -> limparPesquisaNome());
        btnLimparCidade.addActionListener(evt -> limparPesquisaCidade());
        btnNovoCliente.addActionListener(evt -> novoCliente());
        btnSalvarCliente.addActionListener(evt -> salvarCliente());
        btnEditarCliente.addActionListener(evt -> editarCliente());
        btnExcluirCliente.addActionListener(evt -> excluirCliente());
        btnLimparCliente.addActionListener(evt -> limparCampos());
        btListaClientes.getSelectionModel().addListSelectionListener(evt -> {
            if (!evt.getValueIsAdjusting()) {
                clienteSelecionadoNaTabela();
            }
        });
    }
    private void buscarPorCPF() {
        String cpf = txtPesquisaCPF.getText().trim();
        if (cpf.isEmpty()) {
            mostrarErro("Digite um CPF para pesquisar!");
            return;
        }
        try {
            Cliente cliente = clienteController.buscarPorCPF(cpf);
            if (cliente != null) {
                carregarClienteParaEdicao(cliente);
                mostrarMensagem("Cliente encontrado: " + cliente.getNome());
            } else {
                mostrarErro("Cliente não encontrado com CPF: " + cpf);
            }
        } catch (Exception e) {
            logger.severe("Erro ao buscar cliente por CPF: " + e.getMessage());
            mostrarErro("Erro ao buscar cliente: " + e.getMessage());
        }
    }
    private void buscarPorNome() {
        String nome = txtPesquisaNome.getText().trim();
        if (nome.isEmpty()) {
            mostrarErro("Digite um nome para pesquisar!");
            return;
        }
        try {
            List<Cliente> clientes = clienteController.buscarPorNome(nome);
            if (!clientes.isEmpty()) {
                if (clientes.size() == 1) {
                    carregarClienteParaEdicao(clientes.get(0));
                    mostrarMensagem("Cliente encontrado: " + clientes.get(0).getNome());
                } else {
                    mostrarMensagem("Encontrados " + clientes.size() + " clientes. Selecione um na tabela.");
                    atualizarTabela(clientes);
                }
            } else {
                mostrarErro("Nenhum cliente encontrado com nome: " + nome);
            }
        } catch (Exception e) {
            logger.severe("Erro ao buscar cliente por nome: " + e.getMessage());
            mostrarErro("Erro ao buscar cliente: " + e.getMessage());
        }
    }
    private void buscarPorCidade() {
        String cidade = txtPesquisaCidade.getText().trim();
        if (cidade.isEmpty()) {
            mostrarErro("Digite uma cidade para pesquisar!");
            return;
        }
        try {
            List<Cliente> clientes = clienteController.buscarPorCidade(cidade);
            if (!clientes.isEmpty()) {
                mostrarMensagem("Encontrados " + clientes.size() + " clientes em " + cidade);
                atualizarTabela(clientes);
            } else {
                mostrarErro("Nenhum cliente encontrado em: " + cidade);
            }
        } catch (Exception e) {
            logger.severe("Erro ao buscar cliente por cidade: " + e.getMessage());
            mostrarErro("Erro ao buscar cliente: " + e.getMessage());
        }
    }
    private void limparPesquisaCPF() {
        txtPesquisaCPF.setText("");
        carregarClientes();
    }
    private void limparPesquisaNome() {
        txtPesquisaNome.setText("");
        carregarClientes();
    }
    private void limparPesquisaCidade() {
        txtPesquisaCidade.setText("");
        carregarClientes();
    }
    private void novoCliente() {
        clienteAtual = null;
        limparCampos();
        setCamposEditaveis(true);
        jTextField1.requestFocus();
        btnSalvarCliente.setEnabled(true);
        btnEditarCliente.setEnabled(false);
        btnExcluirCliente.setEnabled(false);
    }
    private void salvarCliente() {
        if (!validarCampos()) {
            return;
        }
        try {
            Cliente cliente = criarClienteDosCampos();
            boolean sucesso;
            if (clienteAtual == null) {
                sucesso = clienteController.inserirCliente(cliente);
                if (sucesso) {
                    mostrarMensagem("Cliente cadastrado com sucesso!");
                }
            } else {
                cliente.setId(clienteAtual.getId());
                sucesso = clienteController.atualizarCliente(cliente);
                if (sucesso) {
                    mostrarMensagem("Cliente atualizado com sucesso!");
                }
            }
            if (sucesso) {
                limparCampos();
                carregarClientes();
                configurarCamposSomenteLeitura();
                btnSalvarCliente.setEnabled(false);
                btnEditarCliente.setEnabled(true);
                btnExcluirCliente.setEnabled(true);
            }
        } catch (Exception e) {
            logger.severe("Erro ao salvar cliente: " + e.getMessage());
            mostrarErro("Erro ao salvar cliente: " + e.getMessage());
        }
    }
    private void editarCliente() {
        if (clienteAtual == null) {
            mostrarErro("Selecione um cliente para editar!");
            return;
        }
        setCamposEditaveis(true);
        btnSalvarCliente.setEnabled(true);
        btnEditarCliente.setEnabled(false);
        jTextField1.requestFocus();
    }
    private void excluirCliente() {
        if (clienteAtual == null) {
            mostrarErro("Selecione um cliente para excluir!");
            return;
        }
        int opcao = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja excluir o cliente '" + clienteAtual.getNome() + "'?",
            "Confirmar Exclusão",
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (opcao == javax.swing.JOptionPane.YES_OPTION) {
            try {
                boolean sucesso = clienteController.excluirCliente(clienteAtual.getId());
                if (sucesso) {
                    mostrarMensagem("Cliente excluído com sucesso!");
                    limparCampos();
                    carregarClientes();
                } else {
                    mostrarErro("Erro ao excluir cliente!");
                }
            } catch (Exception e) {
                logger.severe("Erro ao excluir cliente: " + e.getMessage());
                mostrarErro("Erro ao excluir cliente: " + e.getMessage());
            }
        }
    }
    private void limparCampos() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField6.setText("");
        jTextField5.setText("");
        jTextField8.setText("");
        jTextField7.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        clienteAtual = null;
        setCamposEditaveis(false);
        btnSalvarCliente.setEnabled(false);
        btnEditarCliente.setEnabled(true);
        btnExcluirCliente.setEnabled(true);
    }
    private void carregarClientes() {
        try {
            List<Cliente> clientes = clienteController.listarTodos();
            atualizarTabela(clientes);
        } catch (Exception e) {
            logger.severe("Erro ao carregar clientes: " + e.getMessage());
            mostrarErro("Erro ao carregar clientes: " + e.getMessage());
        }
    }
    private void atualizarTabela(List<Cliente> clientes) {
        tableModel.setRowCount(0);
        for (Cliente cliente : clientes) {
            tableModel.addRow(new Object[]{
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getCidade(),
                cliente.isAtivo() ? "Ativo" : "Inativo"
            });
        }
    }
    private void carregarClienteParaEdicao(Cliente cliente) {
        clienteAtual = cliente;
        jTextField1.setText(cliente.getNome());
        jTextField2.setText(cliente.getCpf());
        jTextField3.setText(cliente.getEmail() != null ? cliente.getEmail() : "");
        jTextField4.setText(cliente.getTelefone() != null ? cliente.getTelefone() : "");
        jTextField6.setText(cliente.getCelular() != null ? cliente.getCelular() : "");
        jTextField5.setText(cliente.getEndereco() != null ? cliente.getEndereco() : "");
        jTextField8.setText(cliente.getCidade() != null ? cliente.getCidade() : "");
        jTextField7.setText(cliente.getEstado() != null ? cliente.getEstado() : "");
        jTextField9.setText(cliente.getDataNascimento() != null ? cliente.getDataNascimento() : "");
        jTextField10.setText(cliente.getObservacoes() != null ? cliente.getObservacoes() : "");
        setCamposEditaveis(false);
        btnSalvarCliente.setEnabled(false);
        btnEditarCliente.setEnabled(true);
        btnExcluirCliente.setEnabled(true);
    }
    private Cliente criarClienteDosCampos() {
        return clienteController.criarCliente(
            jTextField1.getText(),
            jTextField2.getText(),
            jTextField3.getText(),
            jTextField4.getText(),
            jTextField6.getText(),
            jTextField5.getText(),
            jTextField8.getText(),
            jTextField7.getText(),
            jTextField9.getText(),
            jTextField10.getText()
        );
    }
    private boolean validarCampos() {
        if (jTextField1.getText().trim().isEmpty()) {
            mostrarErro("Nome é obrigatório!");
            jTextField1.requestFocus();
            return false;
        }
        if (jTextField2.getText().trim().isEmpty()) {
            mostrarErro("CPF é obrigatório!");
            jTextField2.requestFocus();
            return false;
        }
        if (clienteAtual == null) {
            try {
                if (clienteController.cpfExiste(jTextField2.getText().trim())) {
                    mostrarErro("CPF já cadastrado no sistema!");
                    jTextField2.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                logger.severe("Erro ao verificar CPF: " + e.getMessage());
            }
        }
        return true;
    }
    private void clienteSelecionadoNaTabela() {
        int linhaSelecionada = btListaClientes.getSelectedRow();
        if (linhaSelecionada >= 0) {
            Long id = (Long) tableModel.getValueAt(linhaSelecionada, 0);
            try {
                Cliente cliente = clienteController.buscarPorId(id);
                if (cliente != null) {
                    carregarClienteParaEdicao(cliente);
                }
            } catch (Exception e) {
                logger.severe("Erro ao carregar cliente selecionado: " + e.getMessage());
                mostrarErro("Erro ao carregar cliente: " + e.getMessage());
            }
        }
    }
    private void mostrarMensagem(String mensagem) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Sucesso",
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
}
