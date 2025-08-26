package sistema.models;
import java.util.Date;
import sistema.models.Material;
public class Produto {
    private Long id;
    private String codigo;
    private String nome;
    private String descricao;
    private String categoria;
    private String marca;
    private String cor;
    private String tamanho;
    private Material material;
    private Double precoCusto;
    private Double precoVenda;
    private Integer quantidadeEstoque;
    private Integer quantidadeMinima;
    private String localizacao;
    private Fornecedor fornecedor;
    private boolean ativo;
    private Date dataCadastro;
    private Date ultimaAtualizacao;
    private Double massa;
    private Double percentualReciclado;
    private Integer vidaUtil;
    private String nivelDefeito;
    private Double gwpEstimado;
    private Double mciEstimado;
    public Produto() {
        this.dataCadastro = new Date();
        this.ultimaAtualizacao = new Date();
        this.ativo = true;
        this.quantidadeEstoque = 0;
        this.quantidadeMinima = 5;
    }
    public Produto(String codigo, String nome, String categoria, String marca,
                   String cor, String tamanho, Double precoCusto, Double precoVenda) {
        this();
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.marca = marca;
        this.cor = cor;
        this.tamanho = tamanho;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }
    public Double getPrecoCusto() { return precoCusto; }
    public void setPrecoCusto(Double precoCusto) { this.precoCusto = precoCusto; }
    public Double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(Double precoVenda) { this.precoVenda = precoVenda; }
    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public Integer getQuantidadeMinima() { return quantidadeMinima; }
    public void setQuantidadeMinima(Integer quantidadeMinima) { this.quantidadeMinima = quantidadeMinima; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public Date getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }
    public Date getUltimaAtualizacao() { return ultimaAtualizacao; }
    public void setUltimaAtualizacao(Date ultimaAtualizacao) { this.ultimaAtualizacao = ultimaAtualizacao; }
    public Double getMassa() { return massa; }
    public void setMassa(Double massa) { this.massa = massa; }
    public Double getPercentualReciclado() { return percentualReciclado; }
    public void setPercentualReciclado(Double percentualReciclado) { this.percentualReciclado = percentualReciclado; }
    public Integer getVidaUtil() { return vidaUtil; }
    public void setVidaUtil(Integer vidaUtil) { this.vidaUtil = vidaUtil; }
    public String getNivelDefeito() { return nivelDefeito; }
    public void setNivelDefeito(String nivelDefeito) { this.nivelDefeito = nivelDefeito; }
    public Double getGwpEstimado() { return gwpEstimado; }
    public void setGwpEstimado(Double gwpEstimado) { this.gwpEstimado = gwpEstimado; }
    public Double getMciEstimado() { return mciEstimado; }
    public void setMciEstimado(Double mciEstimado) { this.mciEstimado = mciEstimado; }
    public Double getMargemLucro() {
        if (precoCusto != null && precoVenda != null && precoCusto > 0) {
            return ((precoVenda - precoCusto) / precoCusto) * 100;
        }
        return 0.0;
    }
    public boolean isEstoqueBaixo() {
        return quantidadeEstoque <= quantidadeMinima;
    }
    public boolean isSemEstoque() {
        return quantidadeEstoque <= 0;
    }
    public String getStatusEstoque() {
        if (isSemEstoque()) return "SEM ESTOQUE";
        if (isEstoqueBaixo()) return "ESTOQUE BAIXO";
        return "ESTOQUE OK";
    }
    @Override
    public String toString() {
        return codigo + " - " + nome + " (" + marca + ", " + cor + ", " + tamanho + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produto produto = (Produto) obj;
        return id != null && id.equals(produto.getId());
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
