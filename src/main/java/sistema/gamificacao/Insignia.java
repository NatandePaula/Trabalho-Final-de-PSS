package sistema.gamificacao;
public class Insignia {
    private final String codigo;
    private final String nome;
    private final String descricao;
    private final boolean permanente;
    public Insignia(String codigo, String nome, String descricao, boolean permanente) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.permanente = permanente;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public boolean isPermanente() {
        return permanente;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Insignia insignia = (Insignia) obj;
        return codigo.equals(insignia.codigo);
    }
    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
    @Override
    public String toString() {
        return nome + " - " + descricao;
    }
}
