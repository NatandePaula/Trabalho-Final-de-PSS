package sistema.gamificacao;
public class RankingCrescimento {
    private final Long usuarioId;
    private final double crescimento;
    private int posicao;
    public RankingCrescimento(Long usuarioId, double crescimento, int posicao) {
        this.usuarioId = usuarioId;
        this.crescimento = crescimento;
        this.posicao = posicao;
    }
    public Long getUsuarioId() {
        return usuarioId;
    }
    public double getCrescimento() {
        return crescimento;
    }
    public int getPosicao() {
        return posicao;
    }
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    @Override
    public String toString() {
        return "Posição " + posicao + ": Usuário " + usuarioId + " - Crescimento: " + crescimento;
    }
}
