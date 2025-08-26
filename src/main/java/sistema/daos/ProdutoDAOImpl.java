package sistema.daos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import sistema.models.Produto;
import sistema.models.Material;
import sistema.daos.MaterialDAO;
import sistema.utils.DatabaseConfig;
public class ProdutoDAOImpl implements ProdutoDAO {
    private static final Logger logger = Logger.getLogger(ProdutoDAOImpl.class.getName());
    private static final String SQL_INSERIR =
        "INSERT INTO produtos (codigo, nome, categoria, marca, cor, tamanho, material_id, preco_custo, preco_venda, quantidade_estoque, quantidade_minima, fornecedor_id, descricao, ativo, data_cadastro, ultima_atualizacao, massa, percentual_reciclado, vida_util, nivel_defeito, gwp_estimado, mci_estimado) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW(), ?, ?, ?, ?, ?, ?)";
    private static final String SQL_ATUALIZAR =
        "UPDATE produtos SET codigo=?, nome=?, categoria=?, marca=?, cor=?, tamanho=?, material_id=?, preco_custo=?, preco_venda=?, quantidade_estoque=?, quantidade_minima=?, fornecedor_id=?, descricao=?, ativo=?, ultima_atualizacao=NOW(), massa=?, percentual_reciclado=?, vida_util=?, nivel_defeito=?, gwp_estimado=?, mci_estimado=? " +
        "WHERE id=?";
    private static final String SQL_EXCLUIR = "DELETE FROM produtos WHERE id=?";
    private static final String SQL_BUSCAR_POR_ID = "SELECT * FROM produtos WHERE id=?";
    private static final String SQL_BUSCAR_POR_CODIGO = "SELECT * FROM produtos WHERE codigo=?";
    private static final String SQL_LISTAR_TODOS = "SELECT * FROM produtos ORDER BY nome";
    private static final String SQL_BUSCAR_POR_NOME = "SELECT * FROM produtos WHERE nome LIKE ? AND ativo=1 ORDER BY nome";
    private static final String SQL_BUSCAR_POR_CATEGORIA = "SELECT * FROM produtos WHERE categoria=? AND ativo=1 ORDER BY nome";
    private static final String SQL_BUSCAR_POR_MARCA = "SELECT * FROM produtos WHERE marca=? AND ativo=1 ORDER BY nome";
    private static final String SQL_BUSCAR_POR_TAMANHO = "SELECT * FROM produtos WHERE tamanho=? AND ativo=1 ORDER BY nome";
    private static final String SQL_ESTOQUE_BAIXO = "SELECT * FROM produtos WHERE quantidade_estoque <= quantidade_minima AND ativo=1 ORDER BY quantidade_estoque";
    private static final String SQL_ATUALIZAR_ESTOQUE = "UPDATE produtos SET quantidade_estoque=?, ultima_atualizacao=NOW() WHERE id=?";
    @Override
    public boolean inserir(Produto produto) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERIR, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, produto.getCodigo());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getCategoria());
            stmt.setString(4, produto.getMarca());
            stmt.setString(5, produto.getCor());
            stmt.setString(6, produto.getTamanho());
            if (produto.getMaterial() != null) {
                stmt.setInt(7, produto.getMaterial().getId());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            stmt.setDouble(8, produto.getPrecoCusto());
            stmt.setDouble(9, produto.getPrecoVenda());
            stmt.setInt(10, produto.getQuantidadeEstoque());
            stmt.setInt(11, produto.getQuantidadeMinima());
            if (produto.getFornecedor() != null && produto.getFornecedor().getId() != null) {
                stmt.setLong(12, produto.getFornecedor().getId());
            } else {
                stmt.setNull(12, java.sql.Types.BIGINT);
            }
            stmt.setString(13, produto.getDescricao());
            stmt.setBoolean(14, produto.isAtivo());
            if (produto.getMassa() != null) {
                stmt.setDouble(15, produto.getMassa());
            } else {
                stmt.setNull(15, java.sql.Types.DECIMAL);
            }
            if (produto.getPercentualReciclado() != null) {
                stmt.setDouble(16, produto.getPercentualReciclado());
            } else {
                stmt.setNull(16, java.sql.Types.DECIMAL);
            }
            if (produto.getVidaUtil() != null) {
                stmt.setInt(17, produto.getVidaUtil());
            } else {
                stmt.setNull(17, java.sql.Types.INTEGER);
            }
            if (produto.getNivelDefeito() != null) {
                stmt.setString(18, produto.getNivelDefeito());
            } else {
                stmt.setNull(18, java.sql.Types.VARCHAR);
            }
            if (produto.getGwpEstimado() != null) {
                stmt.setDouble(19, produto.getGwpEstimado());
            } else {
                stmt.setNull(19, java.sql.Types.DECIMAL);
            }
            if (produto.getMciEstimado() != null) {
                stmt.setDouble(20, produto.getMciEstimado());
            } else {
                stmt.setNull(20, java.sql.Types.DECIMAL);
            }
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    produto.setId(rs.getLong(1));
                }
                logger.info("Produto inserido com sucesso: " + produto.getNome());
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao inserir produto: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean atualizar(Produto produto) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_ATUALIZAR)) {
            stmt.setString(1, produto.getCodigo());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getCategoria());
            stmt.setString(4, produto.getMarca());
            stmt.setString(5, produto.getCor());
            stmt.setString(6, produto.getTamanho());
            if (produto.getMaterial() != null) {
                stmt.setInt(7, produto.getMaterial().getId());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            stmt.setDouble(8, produto.getPrecoCusto());
            stmt.setDouble(9, produto.getPrecoVenda());
            stmt.setInt(10, produto.getQuantidadeEstoque());
            stmt.setInt(11, produto.getQuantidadeMinima());
            if (produto.getFornecedor() != null && produto.getFornecedor().getId() != null) {
                stmt.setLong(12, produto.getFornecedor().getId());
            } else {
                stmt.setNull(12, java.sql.Types.BIGINT);
            }
            stmt.setString(13, produto.getDescricao());
            stmt.setBoolean(14, produto.isAtivo());
            if (produto.getMassa() != null) {
                stmt.setDouble(15, produto.getMassa());
            } else {
                stmt.setNull(15, java.sql.Types.DECIMAL);
            }
            if (produto.getPercentualReciclado() != null) {
                stmt.setDouble(16, produto.getPercentualReciclado());
            } else {
                stmt.setNull(16, java.sql.Types.DECIMAL);
            }
            if (produto.getVidaUtil() != null) {
                stmt.setInt(17, produto.getVidaUtil());
            } else {
                stmt.setNull(17, java.sql.Types.INTEGER);
            }
            if (produto.getNivelDefeito() != null) {
                stmt.setString(18, produto.getNivelDefeito());
            } else {
                stmt.setNull(18, java.sql.Types.VARCHAR);
            }
            if (produto.getGwpEstimado() != null) {
                stmt.setDouble(19, produto.getGwpEstimado());
            } else {
                stmt.setNull(19, java.sql.Types.DECIMAL);
            }
            if (produto.getMciEstimado() != null) {
                stmt.setDouble(20, produto.getMciEstimado());
            } else {
                stmt.setNull(20, java.sql.Types.DECIMAL);
            }
            stmt.setLong(21, produto.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Produto atualizado com sucesso: " + produto.getNome());
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar produto: " + e.getMessage());
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
                logger.info("Produto excluído com sucesso. ID: " + id);
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao excluir produto: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Produto buscarPorId(Long id) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_ID)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSetParaProduto(rs);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar produto por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Produto buscarPorCodigo(String codigo) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_CODIGO)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSetParaProduto(rs);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar produto por código: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConfig.getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR_TODOS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = mapearResultSetParaProduto(rs);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao listar produtos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                logger.severe("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return produtos;
    }
    @Override
    public List<Produto> buscarPorNome(String nome) {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_NOME)) {
            String nomePattern = "%" + nome + "%";
            stmt.setString(1, nomePattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produtos.add(mapearResultSetParaProduto(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar produtos por nome: " + e.getMessage());
            e.printStackTrace();
        }
        return produtos;
    }
    @Override
    public List<Produto> buscarPorCategoria(String categoria) {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_CATEGORIA)) {
            stmt.setString(1, categoria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produtos.add(mapearResultSetParaProduto(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar produtos por categoria: " + e.getMessage());
            e.printStackTrace();
        }
        return produtos;
    }
    @Override
    public List<Produto> buscarPorMarca(String marca) {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_MARCA)) {
            stmt.setString(1, marca);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produtos.add(mapearResultSetParaProduto(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar produtos por marca: " + e.getMessage());
            e.printStackTrace();
        }
        return produtos;
    }
    @Override
    public List<Produto> buscarPorTamanho(String tamanho) {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_TAMANHO)) {
            stmt.setString(1, tamanho);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produtos.add(mapearResultSetParaProduto(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar produtos por tamanho: " + e.getMessage());
            e.printStackTrace();
        }
        return produtos;
    }
    @Override
    public List<Produto> buscarEstoqueBaixo() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_ESTOQUE_BAIXO);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                produtos.add(mapearResultSetParaProduto(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar produtos com estoque baixo: " + e.getMessage());
            e.printStackTrace();
        }
        return produtos;
    }
    @Override
    public boolean atualizarEstoque(Long id, int novaQuantidade) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_ATUALIZAR_ESTOQUE)) {
            stmt.setInt(1, novaQuantidade);
            stmt.setLong(2, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Estoque atualizado com sucesso para produto ID: " + id);
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar estoque: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Produto> buscarComFiltros(String nome, String categoria, String marca, String tamanho) {
        List<Produto> produtos = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM produtos WHERE ativo=1");
        List<Object> params = new ArrayList<>();
        if (nome != null && !nome.trim().isEmpty()) {
            sql.append(" AND nome LIKE ?");
            params.add("%" + nome + "%");
        }
        if (categoria != null && !categoria.trim().isEmpty()) {
            sql.append(" AND categoria = ?");
            params.add(categoria);
        }
        if (marca != null && !marca.trim().isEmpty()) {
            sql.append(" AND marca = ?");
            params.add(marca);
        }
        if (tamanho != null && !tamanho.trim().isEmpty()) {
            sql.append(" AND tamanho = ?");
            params.add(tamanho);
        }
        sql.append(" ORDER BY nome");
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produtos.add(mapearResultSetParaProduto(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar produtos com filtros: " + e.getMessage());
            e.printStackTrace();
        }
        return produtos;
    }
    private Produto mapearResultSetParaProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getLong("id"));
        produto.setCodigo(rs.getString("codigo"));
        produto.setNome(rs.getString("nome"));
        produto.setCategoria(rs.getString("categoria"));
        produto.setMarca(rs.getString("marca"));
        produto.setCor(rs.getString("cor"));
        produto.setTamanho(rs.getString("tamanho"));
        Integer materialId = rs.getObject("material_id", Integer.class);
        if (materialId != null) {
            Material material = new Material();
            material.setId(materialId);
            produto.setMaterial(material);
        }
        produto.setPrecoCusto(rs.getDouble("preco_custo"));
        produto.setPrecoVenda(rs.getDouble("preco_venda"));
        produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
        produto.setQuantidadeMinima(rs.getInt("quantidade_minima"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setAtivo(rs.getBoolean("ativo"));
        produto.setDataCadastro(rs.getTimestamp("data_cadastro"));
        produto.setUltimaAtualizacao(rs.getTimestamp("ultima_atualizacao"));
        produto.setMassa(rs.getObject("massa", Double.class));
        produto.setPercentualReciclado(rs.getObject("percentual_reciclado", Double.class));
        produto.setVidaUtil(rs.getObject("vida_util", Integer.class));
        produto.setNivelDefeito(rs.getString("nivel_defeito"));
        produto.setGwpEstimado(rs.getObject("gwp_estimado", Double.class));
        produto.setMciEstimado(rs.getObject("mci_estimado", Double.class));
        return produto;
    }
}
