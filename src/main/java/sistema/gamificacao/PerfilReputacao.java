package sistema.gamificacao;
import java.util.ArrayList;
import java.util.List;
public class PerfilReputacao {
    private final Long usuarioId;
    private int estrelas;
    private String nivel;
    private final List<Insignia> insignias;
    private int totalCompras;
    private int totalVendas;
    private int totalGerenciamentosEstoque;
    public PerfilReputacao(Long usuarioId) {
        this.usuarioId = usuarioId;
        this.estrelas = 0;
        this.nivel = "Bronze";
        this.insignias = new ArrayList<>();
        this.totalCompras = 0;
        this.totalVendas = 0;
        this.totalGerenciamentosEstoque = 0;
    }
    public Long getUsuarioId() {
        return usuarioId;
    }
    public int getEstrelas() {
        return estrelas;
    }
    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }
    public void adicionarEstrelas(int quantidade) {
        this.estrelas += quantidade;
    }
    public String getNivel() {
        return nivel;
    }
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    public List<Insignia> getInsignias() {
        return new ArrayList<>(insignias);
    }
    public void adicionarInsignia(Insignia insignia) {
        if (!insignias.contains(insignia)) {
            insignias.add(insignia);
        }
    }
    public boolean possuiInsignia(String codigoInsignia) {
        return insignias.stream().anyMatch(i -> i.getCodigo().equals(codigoInsignia));
    }
    public int getTotalCompras() {
        return totalCompras;
    }
    public void incrementarCompras() {
        this.totalCompras++;
    }
    public int getTotalVendas() {
        return totalVendas;
    }
    public void incrementarVendas() {
        this.totalVendas++;
    }
    public int getTotalGerenciamentosEstoque() {
        return totalGerenciamentosEstoque;
    }
    public void incrementarGerenciamentosEstoque() {
        this.totalGerenciamentosEstoque++;
    }
}
