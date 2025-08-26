package sistema.daos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import sistema.models.Compra;
import sistema.models.Fornecedor;
import sistema.models.Produto;
import sistema.models.ItemCompra;
import sistema.utils.DatabaseConfig;
public class CompraDAOImpl implements CompraDAO {
    private static final Logger logger = Logger.getLogger(CompraDAOImpl.class.getName());
    private static final String SQL_INSERIR_COMPRA =
        "INSERT INTO compras (codigo, fornecedor_id, responsavel_id, subtotal, impostos, total, status, observacoes) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERIR_ITEM_COMPRA =
        "INSERT INTO itens_compra (compra_id, produto_id, quantidade, preco_unitario, subtotal) " +
        "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_ATUALIZAR_ESTOQUE =
        "UPDATE produtos SET quantidade_estoque = quantidade_estoque + ? WHERE id = ?";
    private static final String SQL_BUSCAR_POR_ID = "SELECT * FROM compras WHERE id=?";
    private static final String SQL_LISTAR_TODAS = "SELECT * FROM compras ORDER BY data_compra DESC";
    private static final String SQL_BUSCAR_POR_FORNECEDOR = "SELECT * FROM compras WHERE fornecedor_id=? ORDER BY data_compra DESC";
    private static final String SQL_BUSCAR_POR_DATA = "SELECT * FROM compras WHERE DATE(data_compra)=? ORDER BY data_compra DESC";
    private static final String SQL_PROXIMO_NUMERO = "SELECT MAX(CAST(SUBSTRING(codigo, 4) AS UNSIGNED)) FROM compras WHERE codigo LIKE 'COM%'";
    @Override
    public boolean inserir(Compra compra) {
        Connection conn = null;
        try {
            logger.info("Iniciando inserção de compra: " + compra.getCodigo());
            conn = DatabaseConfig.getConnection();
            conn.setAutoCommit(false);
            logger.info("Inserindo compra principal...");
            Long compraId = inserirCompra(conn, compra);
            if (compraId == null) {
                logger.severe("Falha ao inserir compra principal");
                conn.rollback();
                return false;
            }
            logger.info("Compra principal inserida com ID: " + compraId);
            logger.info("Inserindo itens da compra...");
            if (!inserirItensCompra(conn, compraId, compra.getItens())) {
                logger.severe("Falha ao inserir itens da compra");
                conn.rollback();
                return false;
            }
            logger.info("Itens da compra inseridos com sucesso");
            logger.info("Atualizando estoque dos produtos...");
            if (!atualizarEstoque(conn, compra.getItens())) {
                logger.severe("Falha ao atualizar estoque");
                conn.rollback();
                return false;
            }
            logger.info("Estoque atualizado com sucesso");
            conn.commit();
            compra.setId(compraId);
            logger.info("Compra inserida com sucesso: " + compra.getCodigo());
            return true;
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                logger.severe("Erro ao fazer rollback: " + ex.getMessage());
            }
            logger.severe("Erro ao inserir compra: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                logger.severe("Erro ao fazer rollback: " + ex.getMessage());
            }
            logger.severe("Erro inesperado ao inserir compra: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                logger.severe("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
    private Long inserirCompra(Connection conn, Compra compra) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERIR_COMPRA, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, compra.getCodigo());
            stmt.setLong(2, compra.getFornecedor().getId());
            stmt.setLong(3, 3L);
            stmt.setDouble(4, compra.getSubtotal());
            stmt.setDouble(5, compra.getImpostos() != null ? compra.getImpostos() : 0.0);
            stmt.setDouble(6, compra.getTotal());
            stmt.setString(7, compra.getEstadoAtual().getNome());
            stmt.setString(8, compra.getObservacoes());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return null;
    }
    private boolean inserirItensCompra(Connection conn, Long compraId, List<ItemCompra> itens) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERIR_ITEM_COMPRA)) {
            for (ItemCompra item : itens) {
                stmt.setLong(1, compraId);
                stmt.setLong(2, item.getProduto().getId());
                stmt.setInt(3, item.getQuantidade());
                stmt.setDouble(4, item.getPrecoUnitario());
                stmt.setDouble(5, item.getSubtotal());
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean atualizarEstoque(Connection conn, List<ItemCompra> itens) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(SQL_ATUALIZAR_ESTOQUE)) {
            for (ItemCompra item : itens) {
                stmt.setInt(1, item.getQuantidade());
                stmt.setLong(2, item.getProduto().getId());
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public boolean atualizar(Compra compra) {
        return false;
    }
    @Override
    public boolean excluir(Long id) {
        return false;
    }
    @Override
    public Compra buscarPorId(Long id) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_ID)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSetParaCompra(rs);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar compra por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Compra> listarTodas() {
        List<Compra> compras = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_LISTAR_TODAS);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("CompraDAO: Executando query: " + SQL_LISTAR_TODAS);
            int contador = 0;
            while (rs.next()) {
                contador++;
                logger.info("CompraDAO: Processando registro " + contador);
                Compra compra = mapearResultSetParaCompra(rs);
                compras.add(compra);
                logger.info("CompraDAO: Compra mapeada: " + compra.getCodigo() + " - Total: " + compra.getTotal());
            }
            logger.info("CompraDAO: Total de compras mapeadas: " + compras.size());
        } catch (SQLException e) {
            logger.severe("Erro ao listar compras: " + e.getMessage());
            e.printStackTrace();
        }
        return compras;
    }
    @Override
    public List<Compra> buscarPorFornecedor(Long fornecedorId) {
        List<Compra> compras = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_FORNECEDOR)) {
            stmt.setLong(1, fornecedorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                compras.add(mapearResultSetParaCompra(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar compras por fornecedor: " + e.getMessage());
            e.printStackTrace();
        }
        return compras;
    }
    @Override
    public List<Compra> buscarPorData(java.sql.Date data) {
        List<Compra> compras = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_DATA)) {
            stmt.setDate(1, data);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                compras.add(mapearResultSetParaCompra(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar compras por data: " + e.getMessage());
            e.printStackTrace();
        }
        return compras;
    }
    @Override
    public String obterProximoNumeroCompra() {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_PROXIMO_NUMERO)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int ultimoNumero = rs.getInt(1);
                return String.format("COM%06d", ultimoNumero + 1);
            } else {
                return "COM000001";
            }
        } catch (SQLException e) {
            logger.severe("Erro ao obter próximo número de compra: " + e.getMessage());
            e.printStackTrace();
            return "COM000001";
        }
    }
    private Compra mapearResultSetParaCompra(ResultSet rs) throws SQLException {
        Compra compra = new Compra();
        compra.setId(rs.getLong("id"));
        compra.setCodigo(rs.getString("codigo"));
        compra.setDataCompra(rs.getTimestamp("data_compra"));
        compra.setSubtotal(rs.getDouble("subtotal"));
        compra.setImpostos(rs.getDouble("impostos"));
        double totalFromDB = rs.getDouble("total");
        logger.info("CompraDAO: Mapeando compra " + rs.getString("codigo") + " - Total do banco: " + totalFromDB);
        logger.info("CompraDAO: Verificando se total é NaN: " + Double.isNaN(totalFromDB));
        logger.info("CompraDAO: Verificando se total é infinito: " + Double.isInfinite(totalFromDB));
        compra.setTotal(totalFromDB);
        logger.info("CompraDAO: Total definido na compra: " + compra.getTotal());
        String statusBanco = rs.getString("status");
        if (statusBanco != null) {
            switch (statusBanco.toUpperCase()) {
                case "PENDENTE":
                    compra.alterarEstado(new sistema.state.estados.EstadoCompraPendente());
                    break;
                case "APROVADA":
                    compra.alterarEstado(new sistema.state.estados.EstadoCompraAprovada());
                    break;
                case "RECEBIDA":
                    compra.alterarEstado(new sistema.state.estados.EstadoCompraRecebida());
                    break;
                case "CANCELADA":
                    compra.alterarEstado(new sistema.state.estados.EstadoCompraCancelada());
                    break;
                default:
                    compra.alterarEstado(new sistema.state.estados.EstadoCompraPendente());
                    break;
            }
        }
        compra.setObservacoes(rs.getString("observacoes"));
        compra.setAtivo(rs.getBoolean("ativo"));
        return compra;
    }
}
