package sistema.models;
import java.util.Date;
public class Fornecedor {
    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String celular;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    private String contato;
    private String observacoes;
    private boolean ativo;
    private Date dataCadastro;
    private Date ultimaAtualizacao;
    public Fornecedor() {
        this.dataCadastro = new Date();
        this.ultimaAtualizacao = new Date();
        this.ativo = true;
    }
    public Fornecedor(String cnpj, String razaoSocial, String nomeFantasia, String email, String telefone) {
        this();
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.telefone = telefone;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public Date getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }
    public Date getUltimaAtualizacao() { return ultimaAtualizacao; }
    public void setUltimaAtualizacao(Date ultimaAtualizacao) { this.ultimaAtualizacao = ultimaAtualizacao; }
    @Override
    public String toString() {
        return nomeFantasia != null ? nomeFantasia : razaoSocial;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fornecedor fornecedor = (Fornecedor) obj;
        return id != null && id.equals(fornecedor.getId());
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
