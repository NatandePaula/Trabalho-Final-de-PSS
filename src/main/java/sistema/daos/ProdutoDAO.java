package sistema.daos;
import java.util.List;
import sistema.models.Produto;
public interface ProdutoDAO {
    boolean inserir(Produto produto);
    boolean atualizar(Produto produto);
    boolean excluir(Long id);
    Produto buscarPorId(Long id);
    Produto buscarPorCodigo(String codigo);
    List<Produto> listarTodos();
    List<Produto> buscarPorNome(String nome);
    List<Produto> buscarPorCategoria(String categoria);
    List<Produto> buscarPorMarca(String marca);
    List<Produto> buscarPorTamanho(String tamanho);
    List<Produto> buscarEstoqueBaixo();
    boolean atualizarEstoque(Long id, int novaQuantidade);
    List<Produto> buscarComFiltros(String nome, String categoria, String marca, String tamanho);
}
