package sistema.gamificacao;
import java.util.HashMap;
import java.util.Map;
public class DesafioSemanal {
    private final String codigo;
    private final String nome;
    private final String tipoAcao;
    private final int meta;
    private final Map<Long, Integer> progressoUsuarios;
    public DesafioSemanal(String codigo, String nome, String tipoAcao, int meta) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipoAcao = tipoAcao;
        this.meta = meta;
        this.progressoUsuarios = new HashMap<>();
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getTipoAcao() {
        return tipoAcao;
    }
    public int getMeta() {
        return meta;
    }
    public int getProgresso(Long usuarioId) {
        return progressoUsuarios.getOrDefault(usuarioId, 0);
    }
    public void incrementarProgresso(Long usuarioId, int quantidade) {
        int progressoAtual = getProgresso(usuarioId);
        progressoUsuarios.put(usuarioId, progressoAtual + quantidade);
    }
    public boolean isCompletado(Long usuarioId) {
        return getProgresso(usuarioId) >= meta;
    }
    public void resetarProgresso() {
        progressoUsuarios.clear();
    }
    public Map<Long, Integer> getProgressoUsuarios() {
        return new HashMap<>(progressoUsuarios);
    }
    @Override
    public String toString() {
        return nome + " - Meta: " + meta + " " + tipoAcao;
    }
}
