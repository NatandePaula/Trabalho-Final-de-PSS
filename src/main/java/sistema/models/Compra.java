package sistema.models;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import sistema.state.ContextoEstado;
import sistema.state.Estado;
import sistema.state.estados.EstadoCompraPendente;
import sistema.state.estados.EstadoCompraAprovada;
import sistema.state.estados.EstadoCompraRecebida;
import sistema.state.estados.EstadoCompraCancelada;
import sistema.observer.SujeitoAbstrato;
public class Compra extends SujeitoAbstrato implements ContextoEstado {
    private Long id;
    private String codigo;
    private Fornecedor fornecedor;
    private Usuario responsavel;
    private List<ItemCompra> itens;
    private Double subtotal;
    private Double frete;
    private Double impostos;
    private Double total;
    private String formaPagamento;
    private Estado estadoAtual;
    private String observacoes;
    private Date dataCompra;
    private Date dataRecebimento;
    private Date dataPagamento;
    private boolean ativo;
    private Date dataCadastro;
    private Date ultimaAtualizacao;
    public Compra() {
        this.dataCadastro = new Date();
        this.ultimaAtualizacao = new Date();
        this.ativo = true;
        this.estadoAtual = new EstadoCompraPendente();
        this.itens = new ArrayList<>();
        this.subtotal = 0.0;
        this.frete = 0.0;
        this.impostos = 0.0;
        this.total = 0.0;
    }
    public Compra(Fornecedor fornecedor, Usuario responsavel) {
        this();
        this.fornecedor = fornecedor;
        this.responsavel = responsavel;
        this.dataCompra = new Date();
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
    public Usuario getResponsavel() { return responsavel; }
    public void setResponsavel(Usuario responsavel) { this.responsavel = responsavel; }
    public List<ItemCompra> getItens() { return itens; }
    public void setItens(List<ItemCompra> itens) { this.itens = itens; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public Double getFrete() { return frete; }
    public void setFrete(Double frete) { this.frete = frete; }
    public Double getImpostos() { return impostos; }
    public void setImpostos(Double impostos) { this.impostos = impostos; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Date getDataCompra() { return dataCompra; }
    public void setDataCompra(Date dataCompra) { this.dataCompra = dataCompra; }
    public Date getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(Date dataRecebimento) { this.dataRecebimento = dataRecebimento; }
    public Date getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(Date dataPagamento) { this.dataPagamento = dataPagamento; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public Date getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }
    public Date getUltimaAtualizacao() { return ultimaAtualizacao; }
    public void setUltimaAtualizacao(Date ultimaAtualizacao) { this.ultimaAtualizacao = ultimaAtualizacao; }
    @Override
    public void alterarEstado(Estado novoEstado) {
        if (estadoAtual.podeTransicionarPara(novoEstado)) {
            Estado estadoAnterior = this.estadoAtual;
            this.estadoAtual = novoEstado;
            this.ultimaAtualizacao = new Date();
            notificarMudanca("ALTERACAO_ESTADO", this);
            System.out.println("Compra " + codigo + " alterou estado de " +
                             estadoAnterior.getNome() + " para " + novoEstado.getNome());
        } else {
            throw new IllegalStateException("Não é possível transicionar de " +
                                          estadoAtual.getNome() + " para " + novoEstado.getNome());
        }
    }
    @Override
    public Estado getEstadoAtual() {
        return estadoAtual;
    }
    @Override
    public boolean executarAcao() {
        return estadoAtual.executarAcao(this);
    }
    @Override
    public String getNomeParaLog() {
        return "Compra " + (codigo != null ? codigo : "N/A");
    }
    public void aprovarCompra() {
        alterarEstado(new EstadoCompraAprovada());
    }
    public void receberCompra() {
        alterarEstado(new EstadoCompraRecebida());
        this.dataRecebimento = new Date();
    }
    public void cancelarCompra() {
        alterarEstado(new EstadoCompraCancelada());
    }
    public void adicionarItem(ItemCompra item) {
        this.itens.add(item);
        calcularTotais();
        notificarMudanca("ADICAO_ITEM", this);
    }
    public void removerItem(ItemCompra item) {
        this.itens.remove(item);
        calcularTotais();
        notificarMudanca("REMOCAO_ITEM", this);
    }
    public void calcularTotais() {
        this.subtotal = 0.0;
        for (ItemCompra item : this.itens) {
            this.subtotal += item.getSubtotal();
        }
        this.total = this.subtotal + this.frete + this.impostos;
    }
    public boolean isPendente() {
        return estadoAtual instanceof EstadoCompraPendente;
    }
    public boolean isAprovada() {
        return estadoAtual instanceof EstadoCompraAprovada;
    }
    public boolean isRecebida() {
        return estadoAtual instanceof EstadoCompraRecebida;
    }
    public boolean isCancelada() {
        return estadoAtual instanceof EstadoCompraCancelada;
    }
    public Integer getQuantidadeTotalItens() {
        Integer total = 0;
        for (ItemCompra item : this.itens) {
            total += item.getQuantidade();
        }
        return total;
    }
    @Override
    public String toString() {
        return "Compra #" + codigo + " - " + fornecedor.getNomeFantasia() + " (" + estadoAtual.getNome() + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Compra compra = (Compra) obj;
        return id != null && id.equals(compra.getId());
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
