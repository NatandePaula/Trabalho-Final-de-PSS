package sistema.daos;
import sistema.models.Material;
import sistema.utils.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
public class MaterialDAOImpl implements MaterialDAO {
    private static final Logger logger = Logger.getLogger(MaterialDAOImpl.class.getName());
    @Override
    public boolean inserir(Material material) {
        String sql = "INSERT INTO materiais (nome, fator_emissao, densidade, reciclabilidade, vida_util_padrao, ativo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, material.getNome());
            stmt.setDouble(2, material.getFatorEmissao());
            stmt.setDouble(3, material.getDensidade());
            stmt.setDouble(4, material.getReciclabilidade());
            stmt.setInt(5, material.getVidaUtilPadrao());
            stmt.setBoolean(6, material.getAtivo());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    material.setId(rs.getInt(1));
                }
                logger.info("Material inserido com sucesso: " + material.getNome());
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao inserir material: " + e.getMessage());
        }
        return false;
    }
    @Override
    public boolean atualizar(Material material) {
        String sql = "UPDATE materiais SET nome = ?, fator_emissao = ?, densidade = ?, reciclabilidade = ?, vida_util_padrao = ?, ativo = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getNome());
            stmt.setDouble(2, material.getFatorEmissao());
            stmt.setDouble(3, material.getDensidade());
            stmt.setDouble(4, material.getReciclabilidade());
            stmt.setInt(5, material.getVidaUtilPadrao());
            stmt.setBoolean(6, material.getAtivo());
            stmt.setInt(7, material.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Material atualizado com sucesso: " + material.getNome());
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar material: " + e.getMessage());
        }
        return false;
    }
    @Override
    public boolean remover(Integer id) {
        String sql = "UPDATE materiais SET ativo = false WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Material removido com sucesso. ID: " + id);
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao remover material: " + e.getMessage());
        }
        return false;
    }
    @Override
    public Material buscarPorId(Integer id) {
        String sql = "SELECT * FROM materiais WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSetParaMaterial(rs);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar material por ID: " + e.getMessage());
        }
        return null;
    }
    @Override
    public Material buscarPorNome(String nome) {
        String sql = "SELECT * FROM materiais WHERE nome = ? AND ativo = true";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSetParaMaterial(rs);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar material por nome: " + e.getMessage());
        }
        return null;
    }
    @Override
    public List<Material> listarTodos() {
        String sql = "SELECT * FROM materiais WHERE ativo = true ORDER BY nome";
        List<Material> materiais = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Material material = mapearResultSetParaMaterial(rs);
                materiais.add(material);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao listar materiais: " + e.getMessage());
        }
        return materiais;
    }
    @Override
    public List<Material> listarTodosCompletos() {
        String sql = "SELECT * FROM materiais ORDER BY nome";
        List<Material> materiais = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Material material = mapearResultSetParaMaterial(rs);
                materiais.add(material);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao listar todos os materiais: " + e.getMessage());
        }
        return materiais;
    }
    @Override
    public boolean existePorNome(String nome) {
        String sql = "SELECT COUNT(*) FROM materiais WHERE nome = ? AND ativo = true";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao verificar existência do material: " + e.getMessage());
        }
        return false;
    }
    private Material mapearResultSetParaMaterial(ResultSet rs) throws SQLException {
        Material material = new Material();
        material.setId(rs.getInt("id"));
        material.setNome(rs.getString("nome"));
        material.setFatorEmissao(rs.getDouble("fator_emissao"));
        material.setDensidade(rs.getDouble("densidade"));
        material.setReciclabilidade(rs.getDouble("reciclabilidade"));
        material.setVidaUtilPadrao(rs.getInt("vida_util_padrao"));
        material.setAtivo(rs.getBoolean("ativo"));
        return material;
    }
    public boolean popularMateriaisPadrao() {
        List<Material> materiaisPadrao = new ArrayList<>();
        materiaisPadrao.add(new Material("Algodão", 2.5, 1540.0, 0.8, 2));
        materiaisPadrao.add(new Material("Poliester", 5.2, 1380.0, 0.6, 3));
        materiaisPadrao.add(new Material("Lã", 3.8, 1310.0, 0.9, 4));
        materiaisPadrao.add(new Material("Seda", 4.1, 1300.0, 0.7, 3));
        materiaisPadrao.add(new Material("Linho", 2.8, 1500.0, 0.85, 2));
        materiaisPadrao.add(new Material("Bambu", 1.9, 1200.0, 0.95, 3));
        materiaisPadrao.add(new Material("Cânhamo", 2.1, 1480.0, 0.9, 4));
        materiaisPadrao.add(new Material("Modal", 3.2, 1500.0, 0.75, 2));
        materiaisPadrao.add(new Material("Viscose", 3.9, 1500.0, 0.7, 2));
        materiaisPadrao.add(new Material("Elastano", 6.8, 1200.0, 0.4, 2));
        boolean sucesso = true;
        for (Material material : materiaisPadrao) {
            if (!inserir(material)) {
                sucesso = false;
                logger.warning("Falha ao inserir material padrão: " + material.getNome());
            }
        }
        if (sucesso) {
            logger.info("Materiais padrão populados com sucesso!");
        }
        return sucesso;
    }
}
