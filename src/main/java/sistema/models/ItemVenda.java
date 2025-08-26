package sistema.models;
public class ItemVenda {
    private Long id;
    private Venda venda;
    private Produto produto;
    private Integer quantidade;
    private Double precoUnitario;
    private Double desconto;
    private Double subtotal;
    public ItemVenda() {
        this.quantidade = 1;
        this.desconto = 0.0;
        this.subtotal = 0.0;
    }
    public ItemVenda(Produto produto, Integer quantidade, Double precoUnitario) {
        this();
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        calcularSubtotal();
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Venda getVenda() { return venda; }
    public void setVenda(Venda venda) { this.venda = venda; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        calcularSubtotal();
    }
    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
        calcularSubtotal();
    }
    public Double getDesconto() { return desconto; }
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
        calcularSubtotal();
    }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public void calcularSubtotal() {
        if (quantidade != null && precoUnitario != null) {
            this.subtotal = quantidade * precoUnitario;
            if (desconto != null) {
                this.subtotal -= desconto;
            }
        }
    }
    public void aplicarDesconto(Double desconto) {
        this.desconto = desconto;
        calcularSubtotal();
    }
    public Double getPrecoTotal() {
        return subtotal;
    }
    public String getDescricaoCompleta() {
        if (produto != null) {
            return produto.getNome() + " - " + produto.getCor() + ", " + produto.getTamanho();
        }
        return "Produto não especificado";
    }
    @Override
    public String toString() {
        return produto != null ? produto.getNome() : "Item sem produto" + " x" + quantidade;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemVenda itemVenda = (ItemVenda) obj;
        return id != null && id.equals(itemVenda.getId());
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
