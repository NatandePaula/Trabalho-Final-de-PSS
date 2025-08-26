package sistema.daos;
import java.util.List;
import sistema.models.Venda;
public interface VendaDAO {
    boolean inserir(Venda venda);
    boolean atualizar(Venda venda);
    boolean excluir(Long id);
    Venda buscarPorId(Long id);
    List<Venda> listarTodas();
    List<Venda> buscarPorCliente(Long clienteId);
    List<Venda> buscarPorData(java.sql.Date data);
    List<Venda> buscarPorPeriodo(java.sql.Date dataInicio, java.sql.Date dataFim);
    String obterProximoNumeroVenda();
}
