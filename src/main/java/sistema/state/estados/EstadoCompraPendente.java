package sistema.state.estados;
import sistema.state.Estado;
import sistema.state.ContextoEstado;
public class EstadoCompraPendente implements Estado {
    @Override
    public boolean executarAcao(ContextoEstado contexto) {
        return true;
    }
    @Override
    public boolean podeTransicionarPara(Estado novoEstado) {
        String nomeNovoEstado = novoEstado.getNome();
        return "APROVADA".equals(nomeNovoEstado) || "CANCELADA".equals(nomeNovoEstado);
    }
    @Override
    public String getNome() {
        return "PENDENTE";
    }
    @Override
    public String getDescricao() {
        return "Compra aguardando aprovação";
    }
}
