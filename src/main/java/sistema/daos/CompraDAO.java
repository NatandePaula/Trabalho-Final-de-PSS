package sistema.daos;
import java.util.List;
import sistema.models.Compra;
public interface CompraDAO {
    boolean inserir(Compra compra);
    boolean atualizar(Compra compra);
    boolean excluir(Long id);
    Compra buscarPorId(Long id);
    List<Compra> listarTodas();
    List<Compra> buscarPorFornecedor(Long fornecedorId);
    List<Compra> buscarPorData(java.sql.Date data);
    String obterProximoNumeroCompra();
}
