package sistema.daos;
import java.util.List;
import sistema.models.Cliente;
public interface ClienteDAO {
    boolean inserir(Cliente cliente);
    boolean atualizar(Cliente cliente);
    boolean excluir(Long id);
    Cliente buscarPorId(Long id);
    Cliente buscarPorCPF(String cpf);
    List<Cliente> listarTodos();
    List<Cliente> buscarPorNome(String nome);
    List<Cliente> buscarPorCidade(String cidade);
    boolean cpfExiste(String cpf);
}
