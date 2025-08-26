package sistema.models;
import java.util.Date;
public class MovimentacaoEstoque {
    private Long id;
    private Produto produto;
    private String tipo;
    private Integer quantidade;
    private String motivo;
    private String observacoes;
    private Usuario responsavel;
    private Date dataMovimentacao;
    private Integer estoqueAnterior;
    private Integer estoqueAtual;
    private boolean ativo;
    private Date dataCadastro;
    public MovimentacaoEstoque() {
        this.dataCadastro = new Date();
        this.ativo = true;
    }
    public MovimentacaoEstoque(Produto produto, String tipo, Integer quantidade, String motivo, Usuario responsavel) {
        this();
        this.produto = produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.motivo = motivo;
        this.responsavel = responsavel;
        this.dataMovimentacao = new Date();
        this.estoqueAnterior = produto.getQuantidadeEstoque();
        if ("ENTRADA".equals(tipo)) {
            this.estoqueAtual = estoqueAnterior + quantidade;
        } else if ("SAIDA".equals(tipo)) {
            this.estoqueAtual = estoqueAnterior - quantidade;
        } else if ("AJUSTE".equals(tipo)) {
            this.estoqueAtual = quantidade;
        }
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Usuario getResponsavel() { return responsavel; }
    public void setResponsavel(Usuario responsavel) { this.responsavel = responsavel; }
    public Date getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(Date dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }
    public Integer getEstoqueAnterior() { return estoqueAnterior; }
    public void setEstoqueAnterior(Integer estoqueAnterior) { this.estoqueAnterior = estoqueAnterior; }
    public Integer getEstoqueAtual() { return estoqueAtual; }
    public void setEstoqueAtual(Integer estoqueAtual) { this.estoqueAtual = estoqueAtual; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public Date getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }
    public boolean isEntrada() {
        return "ENTRADA".equals(this.tipo);
    }
    public boolean isSaida() {
        return "SAIDA".equals(this.tipo);
    }
    public boolean isAjuste() {
        return "AJUSTE".equals(this.tipo);
    }
    public String getDescricaoMovimentacao() {
        StringBuilder descricao = new StringBuilder();
        descricao.append(tipo).append(": ").append(quantidade).append(" unidade(s)");
        if (produto != null) {
            descricao.append(" de ").append(produto.getNome());
        }
        if (motivo != null) {
            descricao.append(" - Motivo: ").append(motivo);
        }
        return descricao.toString();
    }
    public void executarMovimentacao() {
        if (produto != null) {
            produto.setQuantidadeEstoque(estoqueAtual);
            produto.setUltimaAtualizacao(new Date());
        }
    }
    @Override
    public String toString() {
        return tipo + " - " + produto.getNome() + " x" + quantidade + " (" + motivo + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MovimentacaoEstoque movimentacao = (MovimentacaoEstoque) obj;
        return id != null && id.equals(movimentacao.getId());
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
