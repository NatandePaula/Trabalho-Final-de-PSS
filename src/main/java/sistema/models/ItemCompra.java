package sistema.models;
public class ItemCompra {
    private Long id;
    private Compra compra;
    private Produto produto;
    private Integer quantidade;
    private Double precoUnitario;
    private Double subtotal;
    public ItemCompra() {
        this.quantidade = 1;
        this.subtotal = 0.0;
    }
    public ItemCompra(Produto produto, Integer quantidade, Double precoUnitario) {
        this();
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        calcularSubtotal();
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Compra getCompra() { return compra; }
    public void setCompra(Compra compra) { this.compra = compra; }
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
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public void calcularSubtotal() {
        if (quantidade != null && precoUnitario != null) {
            this.subtotal = quantidade * precoUnitario;
        }
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
        ItemCompra itemCompra = (ItemCompra) obj;
        return id != null && id.equals(itemCompra.getId());
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
