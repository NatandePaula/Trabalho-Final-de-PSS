package sistema.daos;
import java.util.List;
import sistema.models.Fornecedor;
public interface FornecedorDAO {
    boolean inserir(Fornecedor fornecedor);
    boolean atualizar(Fornecedor fornecedor);
    boolean excluir(Long id);
    Fornecedor buscarPorId(Long id);
    Fornecedor buscarPorCPFCNPJ(String cpfCnpj);
    List<Fornecedor> listarTodos();
    List<Fornecedor> buscarPorNome(String nome);
    boolean cpfCnpjExiste(String cpfCnpj);
}
