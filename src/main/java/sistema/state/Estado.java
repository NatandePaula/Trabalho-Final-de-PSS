package sistema.state;
public interface Estado {
    boolean executarAcao(ContextoEstado contexto);
    boolean podeTransicionarPara(Estado novoEstado);
    String getNome();
    String getDescricao();
}
