package sistema.observer;
public interface Sujeito {
    void adicionarObservador(Observador observador);
    void removerObservador(Observador observador);
    void notificarMudanca(String tipoMudanca, Object dados);
}
