package sistema.state.estados;
import sistema.state.Estado;
import sistema.state.ContextoEstado;
public class EstadoCompraRecebida implements Estado {
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
        return "RECEBIDA";
    }
    @Override
    public String getDescricao() {
        return "Compra recebida e finalizada";
    }
}
