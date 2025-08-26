package sistema.models;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class Venda {
    private Long id;
    private String codigo;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private Double subtotal;
    private Double desconto;
    private Double total;
    private String formaPagamento;
    private String status;
    private String observacoes;
    private Date dataVenda;
    private Date dataPagamento;
    private boolean ativo;
    private Date dataCadastro;
    private Date ultimaAtualizacao;
    public Venda() {
        this.dataCadastro = new Date();
        this.ultimaAtualizacao = new Date();
        this.ativo = true;
        this.status = "PENDENTE";
        this.itens = new ArrayList<>();
        this.subtotal = 0.0;
        this.desconto = 0.0;
        this.total = 0.0;
    }
    public Venda(Cliente cliente, String formaPagamento) {
        this();
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.dataVenda = new Date();
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<ItemVenda> getItens() { return itens; }
    public void setItens(List<ItemVenda> itens) { this.itens = itens; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public Double getDesconto() { return desconto; }
    public void setDesconto(Double desconto) { this.desconto = desconto; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Date getDataVenda() { return dataVenda; }
    public void setDataVenda(Date dataVenda) { this.dataVenda = dataVenda; }
    public Date getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(Date dataPagamento) { this.dataPagamento = dataPagamento; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public Date getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }
    public Date getUltimaAtualizacao() { return ultimaAtualizacao; }
    public void setUltimaAtualizacao(Date ultimaAtualizacao) { this.ultimaAtualizacao = ultimaAtualizacao; }
    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
        calcularTotais();
    }
    public void removerItem(ItemVenda item) {
        this.itens.remove(item);
        calcularTotais();
    }
    public void calcularTotais() {
        this.subtotal = 0.0;
        for (ItemVenda item : this.itens) {
            this.subtotal += item.getSubtotal();
        }
        this.total = this.subtotal - this.desconto;
    }
    public void aplicarDesconto(Double desconto) {
        this.desconto = desconto;
        calcularTotais();
    }
    public void finalizarVenda() {
        this.status = "CONCLUIDA";
        this.dataPagamento = new Date();
        this.ultimaAtualizacao = new Date();
    }
    public void cancelarVenda() {
        this.status = "CANCELADA";
        this.ultimaAtualizacao = new Date();
    }
    public boolean isPendente() {
        return "PENDENTE".equals(this.status);
    }
    public boolean isConcluida() {
        return "CONCLUIDA".equals(this.status);
    }
    public boolean isCancelada() {
        return "CANCELADA".equals(this.status);
    }
    public Integer getQuantidadeTotalItens() {
        Integer total = 0;
        for (ItemVenda item : this.itens) {
            total += item.getQuantidade();
        }
        return total;
    }
    @Override
    public String toString() {
        return "Venda #" + codigo + " - " + cliente.getNome() + " (" + status + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Venda venda = (Venda) obj;
        return id != null && id.equals(venda.getId());
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
