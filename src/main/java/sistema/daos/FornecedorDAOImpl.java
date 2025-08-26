package sistema.daos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import sistema.models.Fornecedor;
import sistema.utils.DatabaseConfig;
public class FornecedorDAOImpl implements FornecedorDAO {
    private static final Logger logger = Logger.getLogger(FornecedorDAOImpl.class.getName());
    private static final String SQL_INSERIR =
        "INSERT INTO fornecedores (cnpj, razao_social, nome_fantasia, email, telefone, celular, endereco, cidade, estado, cep, contato, observacoes, ativo, data_cadastro, ultima_atualizacao) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
    private static final String SQL_ATUALIZAR =
        "UPDATE fornecedores SET cnpj=?, razao_social=?, nome_fantasia=?, email=?, telefone=?, celular=?, endereco=?, cidade=?, estado=?, cep=?, contato=?, observacoes=?, ativo=?, ultima_atualizacao=NOW() " +
        "WHERE id=?";
    private static final String SQL_EXCLUIR = "DELETE FROM fornecedores WHERE id=?";
    private static final String SQL_BUSCAR_POR_ID = "SELECT * FROM fornecedores WHERE id=?";
    private static final String SQL_BUSCAR_POR_CNPJ = "SELECT * FROM fornecedores WHERE cnpj=?";
    private static final String SQL_LISTAR_TODOS = "SELECT * FROM fornecedores WHERE ativo=1 ORDER BY razao_social";
    private static final String SQL_BUSCAR_POR_NOME = "SELECT * FROM fornecedores WHERE (razao_social LIKE ? OR nome_fantasia LIKE ?) AND ativo=1 ORDER BY razao_social";
    private static final String SQL_CNPJ_EXISTE = "SELECT COUNT(*) FROM fornecedores WHERE cnpj=?";
    @Override
    public boolean inserir(Fornecedor fornecedor) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERIR, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getRazaoSocial());
            stmt.setString(3, fornecedor.getNomeFantasia());
            stmt.setString(4, fornecedor.getEmail());
            stmt.setString(5, fornecedor.getTelefone());
            stmt.setString(6, fornecedor.getCelular());
            stmt.setString(7, fornecedor.getEndereco());
            stmt.setString(8, fornecedor.getCidade());
            stmt.setString(9, fornecedor.getEstado());
            stmt.setString(10, fornecedor.getCep());
            stmt.setString(11, fornecedor.getContato());
            stmt.setString(12, fornecedor.getObservacoes());
            stmt.setBoolean(13, fornecedor.isAtivo());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    fornecedor.setId(rs.getLong(1));
                }
                logger.info("Fornecedor inserido com sucesso: " + fornecedor.getRazaoSocial());
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao inserir fornecedor: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean atualizar(Fornecedor fornecedor) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_ATUALIZAR)) {
            stmt.setString(1, fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getRazaoSocial());
            stmt.setString(3, fornecedor.getNomeFantasia());
            stmt.setString(4, fornecedor.getEmail());
            stmt.setString(5, fornecedor.getTelefone());
            stmt.setString(6, fornecedor.getCelular());
            stmt.setString(7, fornecedor.getEndereco());
            stmt.setString(8, fornecedor.getCidade());
            stmt.setString(9, fornecedor.getEstado());
            stmt.setString(10, fornecedor.getCep());
            stmt.setString(11, fornecedor.getContato());
            stmt.setString(12, fornecedor.getObservacoes());
            stmt.setBoolean(13, fornecedor.isAtivo());
            stmt.setLong(14, fornecedor.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Fornecedor atualizado com sucesso: " + fornecedor.getRazaoSocial());
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar fornecedor: " + e.getMessage());
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
                logger.info("Fornecedor excluído com sucesso. ID: " + id);
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao excluir fornecedor: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Fornecedor buscarPorId(Long id) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_ID)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSetParaFornecedor(rs);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar fornecedor por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Fornecedor buscarPorCPFCNPJ(String cpfCnpj) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_CNPJ)) {
            stmt.setString(1, cpfCnpj);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSetParaFornecedor(rs);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar fornecedor por CNPJ: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Fornecedor> listarTodos() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_LISTAR_TODOS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                fornecedores.add(mapearResultSetParaFornecedor(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao listar fornecedores: " + e.getMessage());
            e.printStackTrace();
        }
        return fornecedores;
    }
    @Override
    public List<Fornecedor> buscarPorNome(String nome) {
        List<Fornecedor> fornecedores = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_NOME)) {
            String nomePattern = "%" + nome + "%";
            stmt.setString(1, nomePattern);
            stmt.setString(2, nomePattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                fornecedores.add(mapearResultSetParaFornecedor(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar fornecedores por nome: " + e.getMessage());
            e.printStackTrace();
        }
        return fornecedores;
    }
    @Override
    public boolean cpfCnpjExiste(String cpfCnpj) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_CNPJ_EXISTE)) {
            stmt.setString(1, cpfCnpj);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao verificar CNPJ: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    private Fornecedor mapearResultSetParaFornecedor(ResultSet rs) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(rs.getLong("id"));
        fornecedor.setCnpj(rs.getString("cnpj"));
        fornecedor.setRazaoSocial(rs.getString("razao_social"));
        fornecedor.setNomeFantasia(rs.getString("nome_fantasia"));
        fornecedor.setEmail(rs.getString("email"));
        fornecedor.setTelefone(rs.getString("telefone"));
        fornecedor.setCelular(rs.getString("celular"));
        fornecedor.setEndereco(rs.getString("endereco"));
        fornecedor.setCidade(rs.getString("cidade"));
        fornecedor.setEstado(rs.getString("estado"));
        fornecedor.setCep(rs.getString("cep"));
        fornecedor.setContato(rs.getString("contato"));
        fornecedor.setObservacoes(rs.getString("observacoes"));
        fornecedor.setAtivo(rs.getBoolean("ativo"));
        fornecedor.setDataCadastro(rs.getTimestamp("data_cadastro"));
        fornecedor.setUltimaAtualizacao(rs.getTimestamp("ultima_atualizacao"));
        return fornecedor;
    }
}
