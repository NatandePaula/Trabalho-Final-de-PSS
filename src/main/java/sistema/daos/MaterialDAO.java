package sistema.daos;
import sistema.models.Material;
import java.util.List;
public interface MaterialDAO {
    boolean inserir(Material material);
    boolean atualizar(Material material);
    boolean remover(Integer id);
    Material buscarPorId(Integer id);
    Material buscarPorNome(String nome);
    List<Material> listarTodos();
    List<Material> listarTodosCompletos();
    boolean existePorNome(String nome);
}

