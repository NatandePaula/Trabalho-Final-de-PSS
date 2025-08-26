package sistema.controllers;
import java.util.List;
import java.util.logging.Logger;
import sistema.models.Cliente;
import sistema.daos.ClienteDAO;
import sistema.daos.ClienteDAOImpl;
public class ClienteController {
    private static final Logger logger = Logger.getLogger(ClienteController.class.getName());
    private final ClienteDAO clienteDAO;
    public ClienteController() {
        this.clienteDAO = new ClienteDAOImpl();
    }
    public Cliente buscarPorCPF(String cpf) throws Exception {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio");
        }
        logger.info("Buscando cliente por CPF: " + cpf);
        return clienteDAO.buscarPorCPF(cpf.trim());
    }
    public List<Cliente> buscarPorNome(String nome) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        logger.info("Buscando clientes por nome: " + nome);
        return clienteDAO.buscarPorNome(nome.trim());
    }
    public List<Cliente> buscarPorCidade(String cidade) throws Exception {
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade não pode ser vazia");
        }
        logger.info("Buscando clientes por cidade: " + cidade);
        return clienteDAO.buscarPorCidade(cidade.trim());
    }
    public Cliente buscarPorId(Long id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        logger.info("Buscando cliente por ID: " + id);
        return clienteDAO.buscarPorId(id);
    }
    public List<Cliente> listarTodos() throws Exception {
        logger.info("Listando todos os clientes");
        return clienteDAO.listarTodos();
    }
    public boolean inserirCliente(Cliente cliente) throws Exception {
        validarCliente(cliente);
        verificarCPFDuplicado(cliente.getCpf());
        logger.info("Inserindo novo cliente: " + cliente.getNome());
        return clienteDAO.inserir(cliente);
    }
    public boolean atualizarCliente(Cliente cliente) throws Exception {
        if (cliente.getId() == null) {
            throw new IllegalArgumentException("Cliente deve ter ID para atualização");
        }
        validarCliente(cliente);
        logger.info("Atualizando cliente ID: " + cliente.getId());
        return clienteDAO.atualizar(cliente);
    }
    public boolean excluirCliente(Long id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        logger.info("Excluindo cliente ID: " + id);
        return clienteDAO.excluir(id);
    }
    public boolean cpfExiste(String cpf) throws Exception {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }
        return clienteDAO.cpfExiste(cpf.trim());
    }
    private void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório");
        }
        String cpf = cliente.getCpf().replaceAll("[^0-9]", "");
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos");
        }
        if (cliente.getEmail() != null && !cliente.getEmail().trim().isEmpty()) {
            if (!cliente.getEmail().contains("@")) {
                throw new IllegalArgumentException("Email inválido");
            }
        }
    }
    private void verificarCPFDuplicado(String cpf) throws Exception {
        if (cpfExiste(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema");
        }
    }
    public Cliente criarCliente(String nome, String cpf, String email, String telefone,
                               String celular, String endereco, String cidade, String estado,
                               String dataNascimento, String observacoes) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome != null ? nome.trim() : "");
        cliente.setCpf(cpf != null ? cpf.trim() : "");
        cliente.setEmail(email != null && !email.trim().isEmpty() ? email.trim() : null);
        cliente.setTelefone(telefone != null && !telefone.trim().isEmpty() ? telefone.trim() : null);
        cliente.setCelular(celular != null && !celular.trim().isEmpty() ? celular.trim() : null);
        cliente.setEndereco(endereco != null && !endereco.trim().isEmpty() ? endereco.trim() : null);
        cliente.setCidade(cidade != null && !cidade.trim().isEmpty() ? cidade.trim() : null);
        cliente.setEstado(estado != null && !estado.trim().isEmpty() ? estado.trim() : null);
        cliente.setDataNascimento(dataNascimento != null && !dataNascimento.trim().isEmpty() ? dataNascimento.trim() : null);
        cliente.setObservacoes(observacoes != null && !observacoes.trim().isEmpty() ? observacoes.trim() : null);
        cliente.setAtivo(true);
        return cliente;
    }
}
