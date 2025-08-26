package sistema.gamificacao;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
public class SistemaReputacao {
    private static SistemaReputacao instance;
    private final Map<Long, PerfilReputacao> perfis;
    private final List<Insignia> insigniasDisponiveis;
    private final List<DesafioSemanal> desafiosAtivos;
    private SistemaReputacao() {
        this.perfis = new ConcurrentHashMap<>();
        this.insigniasDisponiveis = new ArrayList<>();
        this.desafiosAtivos = new ArrayList<>();
        inicializarSistema();
    }
    public static SistemaReputacao getInstance() {
        if (instance == null) {
            instance = new SistemaReputacao();
        }
        return instance;
    }
    private void inicializarSistema() {
        insigniasDisponiveis.add(new Insignia("PRIMEIRA_COMPRA", "Primeira Compra", "Realizou a primeira compra", true));
        insigniasDisponiveis.add(new Insignia("COMPRADOR_FREQUENTE", "Comprador Frequente", "Realizou 10 compras", false));
        insigniasDisponiveis.add(new Insignia("VENDEDOR_ESTRELA", "Vendedor Estrela", "Vendeu mais de 50 produtos", false));
        insigniasDisponiveis.add(new Insignia("GERENTE_ESTOQUE", "Gerente de Estoque", "Gerenciou estoque por 30 dias", false));
        desafiosAtivos.add(new DesafioSemanal("COMPRAR_MAIS", "Comprar mais produtos", "COMPRA", 100));
        desafiosAtivos.add(new DesafioSemanal("VENDER_MAIS", "Vender mais produtos", "VENDA", 50));
        desafiosAtivos.add(new DesafioSemanal("GERENCIAR_ESTOQUE", "Gerenciar estoque diariamente", "ESTOQUE", 7));
    }
    public PerfilReputacao obterPerfilReputacao(Long usuarioId) {
        return perfis.computeIfAbsent(usuarioId, id -> new PerfilReputacao(id));
    }
    public void adicionarEstrelas(Long usuarioId, int quantidade) {
        PerfilReputacao perfil = obterPerfilReputacao(usuarioId);
        perfil.adicionarEstrelas(quantidade);
        verificarEvolucaoNivel(perfil);
        verificarInsignias(perfil);
    }
    public void registrarCompra(Long usuarioId) {
        PerfilReputacao perfil = obterPerfilReputacao(usuarioId);
        perfil.incrementarCompras();
        adicionarEstrelas(usuarioId, 5);
        for (DesafioSemanal desafio : desafiosAtivos) {
            if ("COMPRA".equals(desafio.getTipoAcao())) {
                desafio.incrementarProgresso(usuarioId, 1);
            }
        }
    }
    public void registrarVenda(Long usuarioId) {
        PerfilReputacao perfil = obterPerfilReputacao(usuarioId);
        perfil.incrementarVendas();
        adicionarEstrelas(usuarioId, 3);
        for (DesafioSemanal desafio : desafiosAtivos) {
            if ("VENDA".equals(desafio.getTipoAcao())) {
                desafio.incrementarProgresso(usuarioId, 1);
            }
        }
    }
    public void registrarGerenciamentoEstoque(Long usuarioId) {
        PerfilReputacao perfil = obterPerfilReputacao(usuarioId);
        perfil.incrementarGerenciamentosEstoque();
        adicionarEstrelas(usuarioId, 2);
        for (DesafioSemanal desafio : desafiosAtivos) {
            if ("ESTOQUE".equals(desafio.getTipoAcao())) {
                desafio.incrementarProgresso(usuarioId, 1);
            }
        }
    }
    private void verificarEvolucaoNivel(PerfilReputacao perfil) {
        int estrelas = perfil.getEstrelas();
        String nivelAtual = perfil.getNivel();
        String novoNivel = calcularNivel(estrelas);
        if (!nivelAtual.equals(novoNivel)) {
            perfil.setNivel(novoNivel);
            perfil.adicionarEstrelas(10);
        }
    }
    private String calcularNivel(int estrelas) {
        if (estrelas < 100) return "Bronze";
        if (estrelas < 500) return "Prata";
        return "Ouro";
    }
    private void verificarInsignias(PerfilReputacao perfil) {
        if (perfil.getTotalCompras() == 1) {
            perfil.adicionarInsignia(insigniasDisponiveis.get(0));
        }
        if (perfil.getTotalCompras() >= 10) {
            perfil.adicionarInsignia(insigniasDisponiveis.get(1));
        }
        if (perfil.getTotalVendas() >= 50) {
            perfil.adicionarInsignia(insigniasDisponiveis.get(2));
        }
        if (perfil.getTotalGerenciamentosEstoque() >= 30) {
            perfil.adicionarInsignia(insigniasDisponiveis.get(3));
        }
    }
    public List<RankingCrescimento> gerarRankingCrescimento() {
        List<RankingCrescimento> ranking = new ArrayList<>();
        for (PerfilReputacao perfil : perfis.values()) {
            double crescimento = calcularCrescimento(perfil);
            ranking.add(new RankingCrescimento(perfil.getUsuarioId(), crescimento, 0));
        }
        ranking.sort((r1, r2) -> Double.compare(r2.getCrescimento(), r1.getCrescimento()));
        for (int i = 0; i < ranking.size(); i++) {
            ranking.get(i).setPosicao(i + 1);
        }
        return ranking;
    }
    private double calcularCrescimento(PerfilReputacao perfil) {
        return perfil.getEstrelas() + (perfil.getTotalCompras() * 2) +
               (perfil.getTotalVendas() * 1.5) + perfil.getTotalGerenciamentosEstoque();
    }
    public List<DesafioSemanal> getDesafiosAtivos() {
        return new ArrayList<>(desafiosAtivos);
    }
    public List<Insignia> getInsigniasDisponiveis() {
        return new ArrayList<>(insigniasDisponiveis);
    }
    public void resetarDesafiosSemanais() {
        for (DesafioSemanal desafio : desafiosAtivos) {
            desafio.resetarProgresso();
        }
    }
}
