package sistema.daos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import sistema.models.Cliente;
import sistema.utils.DatabaseConfig;
public class ClienteDAOImpl implements ClienteDAO {
    private static final Logger logger = Logger.getLogger(ClienteDAOImpl.class.getName());
    private static final String SQL_INSERIR =
        "INSERT INTO clientes (cpf, nome, email, telefone, celular, endereco, cidade, estado, cep, data_nascimento, genero, observacoes, ativo, data_cadastro, ultima_atualizacao) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
    private static final String SQL_ATUALIZAR =
        "UPDATE clientes SET cpf=?, nome=?, email=?, telefone=?, celular=?, endereco=?, cidade=?, estado=?, cep=?, data_nascimento=?, genero=?, observacoes=?, ativo=?, ultima_atualizacao=NOW() " +
        "WHERE id=?";
    private static final String SQL_EXCLUIR = "DELETE FROM clientes WHERE id=?";
    private static final String SQL_BUSCAR_POR_ID = "SELECT * FROM clientes WHERE id=?";
    private static final String SQL_BUSCAR_POR_CPF = "SELECT * FROM clientes WHERE cpf=?";
    private static final String SQL_LISTAR_TODOS = "SELECT * FROM clientes WHERE ativo=1 ORDER BY nome";
    private static final String SQL_BUSCAR_POR_NOME = "SELECT * FROM clientes WHERE nome LIKE ? AND ativo=1 ORDER BY nome";
    private static final String SQL_BUSCAR_POR_CIDADE = "SELECT * FROM clientes WHERE cidade=? AND ativo=1 ORDER BY nome";
    private static final String SQL_CPF_EXISTE = "SELECT COUNT(*) FROM clientes WHERE cpf=?";
    @Override
    public boolean inserir(Cliente cliente) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERIR, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getCelular());
            stmt.setString(6, cliente.getEndereco());
            stmt.setString(7, cliente.getCidade());
            stmt.setString(8, cliente.getEstado());
            stmt.setString(9, cliente.getCep());
            stmt.setString(10, cliente.getDataNascimento());
            stmt.setString(11, cliente.getGenero());
            stmt.setString(12, cliente.getObservacoes());
            stmt.setBoolean(13, cliente.isAtivo());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    cliente.setId(rs.getLong(1));
                }
                logger.info("Cliente inserido com sucesso: " + cliente.getNome());
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao inserir cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean atualizar(Cliente cliente) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_ATUALIZAR)) {
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getCelular());
            stmt.setString(6, cliente.getEndereco());
            stmt.setString(7, cliente.getCidade());
            stmt.setString(8, cliente.getEstado());
            stmt.setString(9, cliente.getCep());
            stmt.setString(10, cliente.getDataNascimento());
            stmt.setString(11, cliente.getGenero());
            stmt.setString(12, cliente.getObservacoes());
            stmt.setBoolean(13, cliente.isAtivo());
            stmt.setLong(14, cliente.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Cliente atualizado com sucesso: " + cliente.getNome());
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean excluir(Long id) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_EXCLUIR)) {
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Cliente excluído com sucesso. ID: " + id);
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao excluir cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Cliente buscarPorId(Long id) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_ID)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSetParaCliente(rs);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar cliente por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Cliente buscarPorCPF(String cpf) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_CPF)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSetParaCliente(rs);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar cliente por CPF: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_LISTAR_TODOS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clientes.add(mapearResultSetParaCliente(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao listar clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }
    @Override
    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_NOME)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(mapearResultSetParaCliente(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar clientes por nome: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }
    @Override
    public List<Cliente> buscarPorCidade(String cidade) {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_CIDADE)) {
            stmt.setString(1, cidade);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(mapearResultSetParaCliente(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar clientes por cidade: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }
    @Override
    public boolean cpfExiste(String cpf) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_CPF_EXISTE)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao verificar CPF: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    private Cliente mapearResultSetParaCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setNome(rs.getString("nome"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setCelular(rs.getString("celular"));
        cliente.setEndereco(rs.getString("endereco"));
        cliente.setCidade(rs.getString("cidade"));
        cliente.setEstado(rs.getString("estado"));
        cliente.setCep(rs.getString("cep"));
        cliente.setDataNascimento(rs.getString("data_nascimento"));
        cliente.setGenero(rs.getString("genero"));
        cliente.setObservacoes(rs.getString("observacoes"));
        cliente.setAtivo(rs.getBoolean("ativo"));
        cliente.setDataCadastro(rs.getTimestamp("data_cadastro"));
        cliente.setUltimaAtualizacao(rs.getTimestamp("ultima_atualizacao"));
        return cliente;
    }
}
