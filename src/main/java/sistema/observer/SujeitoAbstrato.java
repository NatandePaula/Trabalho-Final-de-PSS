package sistema.observer;
import java.util.ArrayList;
import java.util.List;
public abstract class SujeitoAbstrato implements Sujeito {
    protected List<Observador> observadores;
    public SujeitoAbstrato() {
        this.observadores = new ArrayList<>();
    }
    @Override
    public void adicionarObservador(Observador observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }
    @Override
    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }
    @Override
    public void notificarMudanca(String tipoMudanca, Object dados) {
        for (Observador observador : observadores) {
            observador.atualizar(tipoMudanca, dados);
        }
    }
    protected List<Observador> getObservadores() {
        return new ArrayList<>(observadores);
    }
}
