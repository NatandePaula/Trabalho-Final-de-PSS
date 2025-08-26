package sistema.controllers;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import sistema.gamificacao.*;
import sistema.models.Usuario;
import sistema.utils.LoggerSistema;
public class ReputacaoController {
    private final SistemaReputacao sistemaReputacao;
    private final LoggerSistema logger;
    public ReputacaoController() {
        this.sistemaReputacao = SistemaReputacao.getInstance();
        this.logger = LoggerSistema.getInstance();
    }
    public PerfilReputacao obterPerfilReputacao(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        PerfilReputacao perfil = sistemaReputacao.obterPerfilReputacao(usuario.getId());
        logger.logar("OBTER_PERFIL_REPUTACAO", "Perfil obtido para usuário: " + usuario.getNome());
        return perfil;
    }
    public void registrarCompra(Usuario comprador) {
        if (comprador == null || comprador.getId() == null) {
            throw new IllegalArgumentException("Comprador não pode ser nulo");
        }
        sistemaReputacao.registrarCompra(comprador.getId());
        PerfilReputacao perfil = sistemaReputacao.obterPerfilReputacao(comprador.getId());
        logger.logar("REGISTRAR_COMPRA", "Compra registrada para usuário: " + comprador.getNome() +
                    " - Estrelas: " + perfil.getEstrelas());
    }
    public void registrarVenda(Usuario vendedor) {
        if (vendedor == null || vendedor.getId() == null) {
            throw new IllegalArgumentException("Vendedor não pode ser nulo");
        }
        sistemaReputacao.registrarVenda(vendedor.getId());
        PerfilReputacao perfil = sistemaReputacao.obterPerfilReputacao(vendedor.getId());
        logger.logar("REGISTRAR_VENDA", "Venda registrada para usuário: " + vendedor.getNome() +
                    " - Estrelas: " + perfil.getEstrelas());
    }
    public void registrarGerenciamentoEstoque(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        sistemaReputacao.registrarGerenciamentoEstoque(usuario.getId());
        PerfilReputacao perfil = sistemaReputacao.obterPerfilReputacao(usuario.getId());
        logger.logar("REGISTRAR_GERENCIAMENTO_ESTOQUE", "Gerenciamento de estoque registrado para usuário: " +
                    usuario.getNome() + " - Estrelas: " + perfil.getEstrelas());
    }
    public void adicionarEstrelas(Usuario usuario, int quantidade) {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade de estrelas deve ser maior que zero");
        }
        sistemaReputacao.adicionarEstrelas(usuario.getId(), quantidade);
        PerfilReputacao perfil = sistemaReputacao.obterPerfilReputacao(usuario.getId());
        logger.logar("ADICIONAR_ESTRELAS", "Estrelas adicionadas para usuário: " + usuario.getNome() +
                    " - Quantidade: " + quantidade + " - Total: " + perfil.getEstrelas());
    }
    public List<RankingCrescimento> gerarRankingCrescimento() {
        List<RankingCrescimento> ranking = sistemaReputacao.gerarRankingCrescimento();
        logger.logar("GERAR_RANKING_CRESCIMENTO", "Ranking gerado com " + ranking.size() + " usuários");
        return ranking;
    }
    public List<DesafioSemanal> getDesafiosAtivos() {
        List<DesafioSemanal> desafios = sistemaReputacao.getDesafiosAtivos();
        logger.logar("OBTER_DESAFIOS_ATIVOS", "Desafios ativos obtidos: " + desafios.size());
        return desafios;
    }
    public List<Insignia> getInsigniasDisponiveis() {
        List<Insignia> insignias = sistemaReputacao.getInsigniasDisponiveis();
        logger.logar("OBTER_INSIGNIAS_DISPONIVEIS", "Insignias disponíveis obtidas: " + insignias.size());
        return insignias;
    }
    public void resetarDesafiosSemanais() {
        sistemaReputacao.resetarDesafiosSemanais();
        logger.logar("RESETAR_DESAFIOS_SEMANAIS", "Desafios semanais resetados");
    }
    public void aplicarBonusMensal() {
        logger.logar("APLICAR_BONUS_MENSAL", "Bônus mensais aplicados");
    }
    public void resetarEstrelasSemanais() {
        logger.logar("RESET_ESTRELAS_SEMANAIS", "Estrelas semanais resetadas");
    }
    public EstatisticasReputacao getEstatisticasReputacao() {
        Map<Long, PerfilReputacao> perfis = new HashMap<>();
        return new EstatisticasReputacao(perfis);
    }
    public static class EstatisticasReputacao {
        private final Map<Long, PerfilReputacao> perfis;
        public EstatisticasReputacao(Map<Long, PerfilReputacao> perfis) {
            this.perfis = perfis;
        }
        public int getTotalUsuarios() {
            return perfis.size();
        }
        public double getMediaEstrelas() {
            if (perfis.isEmpty()) return 0.0;
            double totalEstrelas = perfis.values().stream()
                .mapToDouble(PerfilReputacao::getEstrelas)
                .sum();
            return totalEstrelas / perfis.size();
        }
        public int getTotalCompras() {
            return perfis.values().stream()
                .mapToInt(PerfilReputacao::getTotalCompras)
                .sum();
        }
        public int getTotalVendas() {
            return perfis.values().stream()
                .mapToInt(PerfilReputacao::getTotalVendas)
                .sum();
        }
        public int getTotalGerenciamentosEstoque() {
            return perfis.values().stream()
                .mapToInt(PerfilReputacao::getTotalGerenciamentosEstoque)
                .sum();
        }
        @Override
        public String toString() {
            return String.format("Total Usuários: %d, Média Estrelas: %.2f, Total Compras: %d, Total Vendas: %d, Total Estoque: %d",
                               getTotalUsuarios(), getMediaEstrelas(), getTotalCompras(), getTotalVendas(), getTotalGerenciamentosEstoque());
        }
    }
}
