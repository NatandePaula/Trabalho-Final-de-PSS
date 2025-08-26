package sistema.controllers;
import sistema.daos.VendaDAO;
import sistema.daos.CompraDAO;
import sistema.daos.ProdutoDAO;
import sistema.daos.ClienteDAO;
import sistema.utils.LoggerSistema;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
public class DashboardController {
    private VendaDAO vendaDAO;
    private CompraDAO compraDAO;
    private ProdutoDAO produtoDAO;
    private ClienteDAO clienteDAO;
    private ReputacaoController reputacaoController;
    private AmbientalController ambientalController;
    private static final LoggerSistema logger = LoggerSistema.getInstance();
    public DashboardController() {
        this.vendaDAO = new sistema.daos.VendaDAOImpl();
        this.compraDAO = new sistema.daos.CompraDAOImpl();
        this.produtoDAO = new sistema.daos.ProdutoDAOImpl();
        this.clienteDAO = new sistema.daos.ClienteDAOImpl();
        this.reputacaoController = new ReputacaoController();
        this.ambientalController = new AmbientalController();
    }
    public double getTotalVendas() {
        try {
            List<sistema.models.Venda> vendas = vendaDAO.listarTodas();
            double total = vendas.stream()
                .mapToDouble(venda -> venda.getTotal())
                .sum();
            logger.logar("INFO", "Dashboard: Total de vendas obtido: R$ " + total);
            return total;
        } catch (Exception e) {
            logger.logar("ERRO", "Dashboard: Erro ao buscar total de vendas: " + e.getMessage());
            return 0.0;
        }
    }
    public double getTotalCompras() {
        try {
            List<sistema.models.Compra> compras = compraDAO.listarTodas();
            if (compras.isEmpty()) {
                return 0.0;
            }
            double total = 0.0;
            for (sistema.models.Compra compra : compras) {
                if (compra.getTotal() != null && compra.getTotal() > 0.0) {
                    total += compra.getTotal();
                }
            }
            return total;
        } catch (Exception e) {
            logger.logar("ERRO", "Erro ao buscar total de compras: " + e.getMessage());
            e.printStackTrace();
            return 0.0;
        }
    }
    public int getTotalProdutosEstoque() {
        try {
            List<sistema.models.Produto> produtos = produtoDAO.listarTodos();
            int total = produtos.size();
            logger.logar("INFO", "Dashboard: Total de produtos em estoque: " + total);
            return total;
        } catch (Exception e) {
            logger.logar("ERRO", "Dashboard: Erro ao contar produtos: " + e.getMessage());
            return 0;
        }
    }
    public int getTotalClientesAtivos() {
        try {
            List<sistema.models.Cliente> clientes = clienteDAO.listarTodos();
            int total = clientes.size();
            logger.logar("INFO", "Dashboard: Total de clientes ativos: " + total);
            return total;
        } catch (Exception e) {
            logger.logar("ERRO", "Dashboard: Erro ao contar clientes: " + e.getMessage());
            return 0;
        }
    }
    public Map<String, Double> getVendasPorMes() {
        try {
            Map<String, Double> dadosExemplo = new HashMap<>();
            dadosExemplo.put("Jan", 12000.0);
            dadosExemplo.put("Fev", 15000.0);
            dadosExemplo.put("Mar", 18000.0);
            dadosExemplo.put("Abr", 22000.0);
            dadosExemplo.put("Mai", 19000.0);
            dadosExemplo.put("Jun", 25000.0);
            logger.logar("INFO", "Dashboard: Dados de vendas por mês obtidos");
            return dadosExemplo;
        } catch (Exception e) {
            logger.logar("ERRO", "Dashboard: Erro ao buscar vendas por mês: " + e.getMessage());
            return new HashMap<>();
        }
    }
    public double getGWPTotal() {
        try {
            List<sistema.models.Produto> produtos = produtoDAO.listarTodos();
            logger.logar("INFO", "Dashboard: === DEBUG GWP ===");
            logger.logar("INFO", "Dashboard: Total de produtos encontrados: " + produtos.size());
            if (produtos.isEmpty()) {
                logger.logar("INFO", "Dashboard: Nenhum produto encontrado para calcular GWP");
                return 0.0;
            }
            double gwpTotal = 0.0;
            int produtosComGWP = 0;
            for (sistema.models.Produto produto : produtos) {
                logger.logar("INFO", "Dashboard: Produto: " + produto.getNome() +
                            ", GWP: " + produto.getGwpEstimado() +
                            ", Tipo: " + (produto.getGwpEstimado() != null ? produto.getGwpEstimado().getClass().getSimpleName() : "NULL"));
                if (produto.getGwpEstimado() != null) {
                    gwpTotal += produto.getGwpEstimado();
                    produtosComGWP++;
                    logger.logar("INFO", "Dashboard: GWP adicionado: " + produto.getGwpEstimado() + ", Total acumulado: " + gwpTotal);
                }
            }
            logger.logar("INFO", "Dashboard: Produtos com GWP: " + produtosComGWP + " de " + produtos.size());
            if (produtosComGWP == 0) {
                logger.logar("INFO", "Dashboard: Nenhum produto com GWP calculado encontrado");
                return 0.0;
            }
            logger.logar("INFO", "Dashboard: GWP total real: " + gwpTotal + " kg CO2e para " + produtosComGWP + " produtos");
            logger.logar("INFO", "Dashboard: === FIM DEBUG GWP ===");
            return gwpTotal;
        } catch (Exception e) {
            logger.logar("ERRO", "Dashboard: Erro ao calcular GWP: " + e.getMessage());
            e.printStackTrace();
            return 0.0;
        }
    }
    public double getMCIMedio() {
        try {
            List<sistema.models.Produto> produtos = produtoDAO.listarTodos();
            logger.logar("INFO", "Dashboard: === DEBUG MCI ===");
            logger.logar("INFO", "Dashboard: Total de produtos encontrados: " + produtos.size());
            if (produtos.isEmpty()) {
                logger.logar("INFO", "Dashboard: Nenhum produto encontrado para calcular MCI");
                return 0.0;
            }
            double mciTotal = 0.0;
            int produtosComMCI = 0;
            for (sistema.models.Produto produto : produtos) {
                logger.logar("INFO", "Dashboard: Produto: " + produto.getNome() +
                            ", MCI: " + produto.getMciEstimado() +
                            ", Tipo: " + (produto.getMciEstimado() != null ? produto.getMciEstimado().getClass().getSimpleName() : "NULL"));
                if (produto.getMciEstimado() != null) {
                    mciTotal += produto.getMciEstimado();
                    produtosComMCI++;
                    logger.logar("INFO", "Dashboard: MCI adicionado: " + produto.getMciEstimado() + ", Total acumulado: " + mciTotal);
                }
            }
            logger.logar("INFO", "Dashboard: Produtos com MCI: " + produtosComMCI + " de " + produtos.size());
            if (produtosComMCI == 0) {
                logger.logar("INFO", "Dashboard: Nenhum produto com MCI calculado encontrado");
                return 0.0;
            }
            double mciMedio = mciTotal / produtosComMCI;
            logger.logar("INFO", "Dashboard: MCI médio real: " + mciMedio + " para " + produtosComMCI + " produtos");
            logger.logar("INFO", "Dashboard: === FIM DEBUG MCI ===");
            return mciMedio;
        } catch (Exception e) {
            logger.logar("ERRO", "Dashboard: Erro ao calcular MCI: " + e.getMessage());
            e.printStackTrace();
            return 0.0;
        }
    }
    public Map<String, Object> getDadosReputacao() {
        return getDadosReputacao("Administrador");
    }
    public Map<String, Object> getEstatisticasAmbientais() {
        try {
            Map<String, Object> stats = new HashMap<>();
            List<sistema.models.Produto> produtos = produtoDAO.listarTodos();
            if (produtos.isEmpty()) {
                stats.put("total_produtos", 0);
                stats.put("produtos_com_gwp", 0);
                stats.put("produtos_com_mci", 0);
                stats.put("gwp_total", 0.0);
                stats.put("mci_medio", 0.0);
                stats.put("mci_min", 0.0);
                stats.put("mci_max", 0.0);
                return stats;
            }
            int totalProdutos = produtos.size();
            int produtosComGWP = 0;
            int produtosComMCI = 0;
            double gwpTotal = 0.0;
            double mciTotal = 0.0;
            double mciMin = Double.MAX_VALUE;
            double mciMax = Double.MIN_VALUE;
            for (sistema.models.Produto produto : produtos) {
                if (produto.getGwpEstimado() != null) {
                    gwpTotal += produto.getGwpEstimado();
                    produtosComGWP++;
                }
                if (produto.getMciEstimado() != null) {
                    mciTotal += produto.getMciEstimado();
                    produtosComMCI++;
                    mciMin = Math.min(mciMin, produto.getMciEstimado());
                    mciMax = Math.max(mciMax, produto.getMciEstimado());
                }
            }
            double mciMedio = produtosComMCI > 0 ? mciTotal / produtosComMCI : 0.0;
            stats.put("total_produtos", totalProdutos);
            stats.put("produtos_com_gwp", produtosComGWP);
            stats.put("produtos_com_mci", produtosComMCI);
            stats.put("gwp_total", gwpTotal);
            stats.put("mci_medio", mciMedio);
            stats.put("mci_min", produtosComMCI > 0 ? mciMin : 0.0);
            stats.put("mci_max", produtosComMCI > 0 ? mciMax : 0.0);
            logger.logar("INFO", "Dashboard: Estatísticas ambientais calculadas - GWP: " + gwpTotal + " kg CO2e, MCI: " + mciMedio);
            return stats;
        } catch (Exception e) {
            logger.logar("ERRO", "Dashboard: Erro ao calcular estatísticas ambientais: " + e.getMessage());
            return new HashMap<>();
        }
    }
    public Map<String, Object> getDadosReputacao(String nomeUsuario) {
        try {
            Map<String, Object> reputacao = new HashMap<>();
            reputacao.put("usuario", nomeUsuario);
            if ("Administrador".equals(nomeUsuario)) {
                reputacao.put("nivel", "Expert");
                reputacao.put("estrelas", 85);
                reputacao.put("ranking", "1º Lugar");
                reputacao.put("desafiosCompletos", 3);
                reputacao.put("totalDesafios", 5);
                reputacao.put("proximoNivel", "15 estrelas para Mestre");
            } else if ("João Silva".equals(nomeUsuario)) {
                reputacao.put("nivel", "Intermediário");
                reputacao.put("estrelas", 67);
                reputacao.put("ranking", "3º Lugar");
                reputacao.put("desafiosCompletos", 2);
                reputacao.put("totalDesafios", 5);
                reputacao.put("proximoNivel", "33 estrelas para Expert");
            } else if ("Maria Santos".equals(nomeUsuario)) {
                reputacao.put("nivel", "Iniciante");
                reputacao.put("estrelas", 23);
                reputacao.put("ranking", "7º Lugar");
                reputacao.put("desafiosCompletos", 1);
                reputacao.put("totalDesafios", 5);
                reputacao.put("proximoNivel", "27 estrelas para Intermediário");
            } else {
                reputacao.put("nivel", "Novato");
                reputacao.put("estrelas", 0);
                reputacao.put("ranking", "N/A");
                reputacao.put("desafiosCompletos", 0);
                reputacao.put("totalDesafios", 5);
                reputacao.put("proximoNivel", "50 estrelas para Iniciante");
            }
            reputacao.put("explicacao", "Sistema de gamificação que recompensa ações no sistema");
            logger.logar("INFO", "Dashboard: Dados de reputação obtidos - Usuário: " + reputacao.get("usuario") + ", Nível: " + reputacao.get("nivel"));
            return reputacao;
        } catch (Exception e) {
            logger.logar("ERRO", "Dashboard: Erro ao buscar reputação: " + e.getMessage());
            return new HashMap<>();
        }
    }
    public List<String> getUsuariosDisponiveis() {
        List<String> usuarios = new ArrayList<>();
        usuarios.add("Administrador");
        usuarios.add("João Silva");
        usuarios.add("Maria Santos");
        usuarios.add("Pedro Costa");
        usuarios.add("Ana Oliveira");
        return usuarios;
    }
    public void atualizarDashboard() {
        try {
            logger.logar("INFO", "Dashboard: Iniciando atualização de dados");
            getTotalVendas();
            getTotalCompras();
            getTotalProdutosEstoque();
            getTotalClientesAtivos();
            getGWPTotal();
            getMCIMedio();
            logger.logar("INFO", "Dashboard: Atualização concluída com sucesso");
        } catch (Exception e) {
            logger.logar("ERRO", "Dashboard: Erro na atualização: " + e.getMessage());
        }
    }
    public List<Map<String, Object>> getAtividadesRecentes() {
        try {
            List<Map<String, Object>> atividades = new ArrayList<>();
            List<sistema.models.Venda> vendas = vendaDAO.listarTodas();
            if (!vendas.isEmpty()) {
                for (int i = 0; i < Math.min(3, vendas.size()); i++) {
                    sistema.models.Venda venda = vendas.get(i);
                    Map<String, Object> atividade = new HashMap<>();
                    atividade.put("tipo", "Venda");
                    atividade.put("codigo", venda.getCodigo());
                    atividade.put("valor", String.format("R$ %.2f", venda.getTotal()));
                    atividade.put("data", venda.getDataVenda());
                    atividade.put("status", venda.getStatus() != null ? venda.getStatus() : "N/A");
                    atividades.add(atividade);
                }
            }
            List<sistema.models.Compra> compras = compraDAO.listarTodas();
            if (!compras.isEmpty()) {
                for (int i = 0; i < Math.min(3, compras.size()); i++) {
                    sistema.models.Compra compra = compras.get(i);
                    Map<String, Object> atividade = new HashMap<>();
                    atividade.put("tipo", "Compra");
                    atividade.put("codigo", compra.getCodigo());
                    atividade.put("valor", String.format("R$ %.2f", compra.getTotal()));
                    atividade.put("data", compra.getDataCompra());
                    atividade.put("status", compra.getEstadoAtual() != null ? compra.getEstadoAtual().getNome() : "N/A");
                    atividades.add(atividade);
                }
            }
            atividades.sort((a, b) -> {
                Date dataA = (Date) a.get("data");
                Date dataB = (Date) b.get("data");
                if (dataA == null || dataB == null) return 0;
                return dataB.compareTo(dataA);
            });
            if (atividades.size() > 5) {
                atividades = atividades.subList(0, 5);
            }
            logger.logar("INFO", "Dashboard: " + atividades.size() + " atividades recentes obtidas");
            return atividades;
        } catch (Exception e) {
            logger.logar("ERRO", "Dashboard: Erro ao buscar atividades recentes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public void criarDadosTeste() {
        try {
            logger.logar("INFO", "Dashboard: Verificando se há dados no banco...");
            List<sistema.models.Compra> compras = compraDAO.listarTodas();
            if (compras.isEmpty()) {
                logger.logar("INFO", "Dashboard: Banco vazio - criando dados de teste...");
                logger.logar("INFO", "Dashboard: Banco vazio - adicione algumas compras para ver dados reais");
            } else {
                logger.logar("INFO", "Dashboard: Banco possui " + compras.size() + " compras");
                logger.logar("INFO", "Dashboard: === DEBUG DETALHADO DAS COMPRAS ===");
                for (sistema.models.Compra compra : compras) {
                    logger.logar("INFO", "Dashboard: Compra ID: " + compra.getCodigo() +
                                ", Total: " + compra.getTotal() +
                                ", Status: " + (compra.getEstadoAtual() != null ? compra.getEstadoAtual().getNome() : "NULL") +
                                ", Data: " + compra.getDataCompra());
                }
                logger.logar("INFO", "Dashboard: === FIM DO DEBUG ===");
            }
            logger.logar("INFO", "Dashboard: === TESTE DE LÓGICA ===");
            double totalTeste = getTotalCompras();
            logger.logar("INFO", "Dashboard: Total calculado pelo método: R$ " + totalTeste);
            logger.logar("INFO", "Dashboard: === FIM DO TESTE ===");
        } catch (Exception e) {
            logger.logar("ERRO", "Dashboard: Erro ao verificar dados de teste: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
