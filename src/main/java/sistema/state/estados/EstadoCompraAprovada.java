package sistema.state.estados;
import sistema.state.Estado;
import sistema.state.ContextoEstado;
public class EstadoCompraAprovada implements Estado {
    @Override
    public boolean executarAcao(ContextoEstado contexto) {
        return true;
    }
    @Override
    public boolean podeTransicionarPara(Estado novoEstado) {
        String nomeNovoEstado = novoEstado.getNome();
        return "RECEBIDA".equals(nomeNovoEstado) || "CANCELADA".equals(nomeNovoEstado);
    }
    @Override
    public String getNome() {
        return "APROVADA";
    }
    @Override
    public String getDescricao() {
        return "Compra aprovada e aguardando recebimento";
    }
}
