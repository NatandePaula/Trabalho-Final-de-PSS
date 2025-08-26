package sistema.models;
public class Material {
    private Integer id;
    private String nome;
    private Double fatorEmissao;
    private Double densidade;
    private Double reciclabilidade;
    private Integer vidaUtilPadrao;
    private Boolean ativo;
    public Material() {
        this.ativo = true;
    }
    public Material(String nome, Double fatorEmissao, Double densidade, Double reciclabilidade, Integer vidaUtilPadrao) {
        this();
        this.nome = nome;
        this.fatorEmissao = fatorEmissao;
        this.densidade = densidade;
        this.reciclabilidade = reciclabilidade;
        this.vidaUtilPadrao = vidaUtilPadrao;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getFatorEmissao() { return fatorEmissao; }
    public void setFatorEmissao(Double fatorEmissao) { this.fatorEmissao = fatorEmissao; }
    public Double getDensidade() { return densidade; }
    public void setDensidade(Double densidade) { this.densidade = densidade; }
    public Double getReciclabilidade() { return reciclabilidade; }
    public void setReciclabilidade(Double reciclabilidade) { this.reciclabilidade = reciclabilidade; }
    public Integer getVidaUtilPadrao() { return vidaUtilPadrao; }
    public void setVidaUtilPadrao(Integer vidaUtilPadrao) { this.vidaUtilPadrao = vidaUtilPadrao; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    @Override
    public String toString() {
        return nome;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Material material = (Material) obj;
        return id != null && id.equals(material.getId());
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

