package sistema.daos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import sistema.models.Venda;
import sistema.models.ItemVenda;
import sistema.models.Cliente;
import sistema.models.Usuario;
import sistema.utils.DatabaseConfig;
public class VendaDAOImpl implements VendaDAO {
    private static final Logger logger = Logger.getLogger(VendaDAOImpl.class.getName());
    private static final String SQL_INSERIR_VENDA =
        "INSERT INTO vendas (codigo, cliente_id, subtotal, desconto, total, forma_pagamento, status, observacoes, data_venda, ativo) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), 1)";
    private static final String SQL_INSERIR_ITEM_VENDA =
        "INSERT INTO itens_venda (venda_id, produto_id, quantidade, preco_unitario, desconto, subtotal) " +
        "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_ATUALIZAR_ESTOQUE =
        "UPDATE produtos SET quantidade_estoque = quantidade_estoque - ? WHERE id = ?";
    private static final String SQL_BUSCAR_POR_ID = "SELECT * FROM vendas WHERE id=?";
    private static final String SQL_LISTAR_TODAS = "SELECT * FROM vendas ORDER BY data_venda DESC";
    private static final String SQL_BUSCAR_POR_CLIENTE = "SELECT * FROM vendas WHERE cliente_id=? ORDER BY data_venda DESC";
    private static final String SQL_BUSCAR_POR_DATA = "SELECT * FROM vendas WHERE DATE(data_venda)=? ORDER BY data_venda DESC";
    private static final String SQL_BUSCAR_POR_PERIODO = "SELECT * FROM vendas WHERE DATE(data_venda) BETWEEN ? AND ? ORDER BY data_venda DESC";
    private static final String SQL_PROXIMO_NUMERO = "SELECT MAX(CAST(SUBSTRING(codigo, 4) AS UNSIGNED)) FROM vendas WHERE codigo LIKE 'VEN%'";
    @Override
    public boolean inserir(Venda venda) {
        Connection conn = null;
        try {
            conn = DatabaseConfig.getConnection();
            conn.setAutoCommit(false);
            Long vendaId = inserirVenda(conn, venda);
            if (vendaId == null) {
                conn.rollback();
                return false;
            }
            if (!inserirItensVenda(conn, vendaId, venda.getItens())) {
                conn.rollback();
                return false;
            }
            if (!atualizarEstoque(conn, venda.getItens())) {
                conn.rollback();
                return false;
            }
            conn.commit();
            venda.setId(vendaId);
            logger.info("Venda inserida com sucesso: " + venda.getCodigo());
            return true;
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                logger.severe("Erro ao fazer rollback: " + ex.getMessage());
            }
            logger.severe("Erro ao inserir venda: " + e.getMessage());
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
    private Long inserirVenda(Connection conn, Venda venda) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERIR_VENDA, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, venda.getCodigo());
            stmt.setLong(2, venda.getCliente().getId());
            stmt.setDouble(3, venda.getSubtotal());
            stmt.setDouble(4, venda.getDesconto());
            stmt.setDouble(5, venda.getTotal());
            stmt.setString(6, venda.getFormaPagamento());
            stmt.setString(7, venda.getStatus());
            stmt.setString(8, venda.getObservacoes());
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
    private boolean inserirItensVenda(Connection conn, Long vendaId, List<ItemVenda> itens) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERIR_ITEM_VENDA)) {
            for (ItemVenda item : itens) {
                stmt.setLong(1, vendaId);
                stmt.setLong(2, item.getProduto().getId());
                stmt.setInt(3, item.getQuantidade());
                stmt.setDouble(4, item.getPrecoUnitario());
                stmt.setDouble(5, 0.0);
                stmt.setDouble(6, item.getSubtotal());
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean atualizarEstoque(Connection conn, List<ItemVenda> itens) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(SQL_ATUALIZAR_ESTOQUE)) {
            for (ItemVenda item : itens) {
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
    public boolean atualizar(Venda venda) {
        return false;
    }
    @Override
    public boolean excluir(Long id) {
        return false;
    }
    @Override
    public Venda buscarPorId(Long id) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_ID)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSetParaVenda(rs);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar venda por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Venda> listarTodas() {
        List<Venda> vendas = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_LISTAR_TODAS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                vendas.add(mapearResultSetParaVenda(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao listar vendas: " + e.getMessage());
            e.printStackTrace();
        }
        return vendas;
    }
    @Override
    public List<Venda> buscarPorCliente(Long clienteId) {
        List<Venda> vendas = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_CLIENTE)) {
            stmt.setLong(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vendas.add(mapearResultSetParaVenda(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar vendas por cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return vendas;
    }
    @Override
    public List<Venda> buscarPorData(java.sql.Date data) {
        List<Venda> vendas = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_DATA)) {
            stmt.setDate(1, data);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vendas.add(mapearResultSetParaVenda(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar vendas por data: " + e.getMessage());
            e.printStackTrace();
        }
        return vendas;
    }
    @Override
    public List<Venda> buscarPorPeriodo(java.sql.Date dataInicio, java.sql.Date dataFim) {
        List<Venda> vendas = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_BUSCAR_POR_PERIODO)) {
            stmt.setDate(1, dataInicio);
            stmt.setDate(2, dataFim);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vendas.add(mapearResultSetParaVenda(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar vendas por período: " + e.getMessage());
            e.printStackTrace();
        }
        return vendas;
    }
    @Override
    public String obterProximoNumeroVenda() {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_PROXIMO_NUMERO)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int ultimoNumero = rs.getInt(1);
                return String.format("VEN%06d", ultimoNumero + 1);
            } else {
                return "VEN000001";
            }
        } catch (SQLException e) {
            logger.severe("Erro ao obter próximo número de venda: " + e.getMessage());
            e.printStackTrace();
            return "VEN000001";
        }
    }
    private Venda mapearResultSetParaVenda(ResultSet rs) throws SQLException {
        Venda venda = new Venda();
        venda.setId(rs.getLong("id"));
        venda.setCodigo(rs.getString("codigo"));
        venda.setDataVenda(rs.getTimestamp("data_venda"));
        venda.setSubtotal(rs.getDouble("subtotal"));
        venda.setDesconto(rs.getDouble("desconto"));
        venda.setTotal(rs.getDouble("total"));
        venda.setFormaPagamento(rs.getString("forma_pagamento"));
        venda.setStatus(rs.getString("status"));
        venda.setObservacoes(rs.getString("observacoes"));
        venda.setAtivo(rs.getBoolean("ativo"));
        return venda;
    }
}
