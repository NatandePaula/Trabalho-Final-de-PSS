package sistema.state;
public interface ContextoEstado {
    void alterarEstado(Estado novoEstado);
    Estado getEstadoAtual();
    boolean executarAcao();
    Long getId();
    String getNomeParaLog();
}
