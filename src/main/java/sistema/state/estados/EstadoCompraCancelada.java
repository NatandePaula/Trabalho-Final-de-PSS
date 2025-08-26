package sistema.state.estados;
import sistema.state.Estado;
import sistema.state.ContextoEstado;
public class EstadoCompraCancelada implements Estado {
    @Override
    public boolean executarAcao(ContextoEstado contexto) {
        return true;
    }
    @Override
    public boolean podeTransicionarPara(Estado novoEstado) {
        return false;
    }
    @Override
    public String getNome() {
        return "CANCELADA";
    }
    @Override
    public String getDescricao() {
        return "Compra cancelada";
    }
}
