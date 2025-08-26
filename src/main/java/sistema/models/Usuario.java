package sistema.models;
import java.util.Date;
public class Usuario {
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String perfil;
    private String telefone;
    private String celular;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    private String dataNascimento;
    private String genero;
    private boolean ativo;
    private Date dataCadastro;
    private Date ultimaAtualizacao;
    private Date ultimoLogin;
    public Usuario() {
        this.dataCadastro = new Date();
        this.ultimaAtualizacao = new Date();
        this.ativo = true;
        this.perfil = "VENDEDOR";
    }
    public Usuario(String cpf, String nome, String email, String senha, String perfil) {
        this();
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
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
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public Date getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }
    public Date getUltimaAtualizacao() { return ultimaAtualizacao; }
    public void setUltimaAtualizacao(Date ultimaAtualizacao) { this.ultimaAtualizacao = ultimaAtualizacao; }
    public Date getUltimoLogin() { return ultimoLogin; }
    public void setUltimoLogin(Date ultimoLogin) { this.ultimoLogin = ultimoLogin; }
    public boolean isAdmin() {
        return "ADMIN".equals(this.perfil);
    }
    public boolean isGerente() {
        return "GERENTE".equals(this.perfil) || isAdmin();
    }
    public boolean isVendedor() {
        return "VENDEDOR".equals(this.perfil) || isGerente();
    }
    public boolean isEstoquista() {
        return "ESTOQUISTA".equals(this.perfil) || isGerente();
    }
    public boolean podeGerenciarUsuarios() {
        return isAdmin() || isGerente();
    }
    public boolean podeGerenciarEstoque() {
        return isEstoquista() || isGerente();
    }
    public boolean podeRealizarVendas() {
        return isVendedor() || isGerente();
    }
    public void registrarLogin() {
        this.ultimoLogin = new Date();
        this.ultimaAtualizacao = new Date();
    }
    @Override
    public String toString() {
        return nome + " (" + perfil + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return id != null && id.equals(usuario.getId());
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
