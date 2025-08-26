package sistema.command;
public interface Comando {
    boolean executar();
    void desfazer();
}
