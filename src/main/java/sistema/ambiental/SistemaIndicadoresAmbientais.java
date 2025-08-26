package sistema.ambiental;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
public class SistemaIndicadoresAmbientais {
    private static SistemaIndicadoresAmbientais instance;
    private final Logger logger;
    private final Map<String, Double> fatoresEmissao;
    private final Map<String, Double> abatimentosDefeito;
    private SistemaIndicadoresAmbientais() {
        this.logger = Logger.getLogger(SistemaIndicadoresAmbientais.class.getName());
        this.fatoresEmissao = new HashMap<>();
        this.abatimentosDefeito = new HashMap<>();
        inicializarFatores();
    }
    public static SistemaIndicadoresAmbientais getInstance() {
        if (instance == null) {
            instance = new SistemaIndicadoresAmbientais();
        }
        return instance;
    }
    private void inicializarFatores() {
        fatoresEmissao.put("ALGODAO", 2.5);
        fatoresEmissao.put("POLIESTER", 3.2);
        fatoresEmissao.put("JEANS", 4.1);
        fatoresEmissao.put("COURO", 8.5);
        fatoresEmissao.put("BORRACHA", 2.8);
        fatoresEmissao.put("METAL", 6.2);
        fatoresEmissao.put("PLASTICO", 3.8);
        abatimentosDefeito.put("PEQUENO", 0.05);
        abatimentosDefeito.put("MEDIO", 0.15);
        abatimentosDefeito.put("GRANDE", 0.30);
    }
    public double calcularGWP(String material, double massa, String defeito) {
        try {
            Double fatorEmissao = fatoresEmissao.get(material.toUpperCase());
            if (fatorEmissao == null) {
                logger.warning("Fator de emissão não encontrado para material: " + material);
                fatorEmissao = 3.0;
            }
            double gwpBase = fatorEmissao * massa;
            Double abatimento = abatimentosDefeito.get(defeito.toUpperCase());
            if (abatimento != null) {
                gwpBase = gwpBase * (1 - abatimento);
            }
            logger.info("GWP calculado: " + gwpBase + " kg CO2e para " + massa + "kg de " + material);
            return gwpBase;
        } catch (Exception e) {
            logger.severe("Erro ao calcular GWP: " + e.getMessage());
            return 0.0;
        }
    }
    public double calcularMCI(double materialReciclado, double materialTotal, int vidaUtil) {
        try {
            if (materialTotal <= 0) {
                return 0.0;
            }
            double fatorReciclagem = materialReciclado / materialTotal;
            double fatorDurabilidade = Math.min(1.0, vidaUtil / 5.0);
            double mci = (fatorReciclagem * 0.7) + (fatorDurabilidade * 0.3);
            mci = Math.max(0.0, Math.min(1.0, mci));
            logger.info("MCI calculado: " + mci + " para produto com " +
                       (fatorReciclagem * 100) + "% de material reciclado e vida útil de " + vidaUtil + " anos");
            return mci;
        } catch (Exception e) {
            logger.severe("Erro ao calcular MCI: " + e.getMessage());
            return 0.0;
        }
    }
    public double calcularImpactoCompra(java.util.List<Object> itens) {
        double impactoTotal = 0.0;
        try {
            for (Object item : itens) {
                double massa = 0.5;
                String material = "ALGODAO";
                String defeito = "PEQUENO";
                impactoTotal += calcularGWP(material, massa, defeito);
            }
            logger.info("Impacto total da compra: " + impactoTotal + " kg CO2e");
            return impactoTotal;
        } catch (Exception e) {
            logger.severe("Erro ao calcular impacto da compra: " + e.getMessage());
            return 0.0;
        }
    }
    public void adicionarFatorEmissao(String material, double fatorEmissao) {
        fatoresEmissao.put(material.toUpperCase(), fatorEmissao);
        logger.info("Novo fator de emissão adicionado: " + material + " = " + fatorEmissao);
    }
    public void adicionarAbatimentoDefeito(String nivelDefeito, double abatimento) {
        abatimentosDefeito.put(nivelDefeito.toUpperCase(), abatimento);
        logger.info("Novo abatimento por defeito adicionado: " + nivelDefeito + " = " + abatimento);
    }
    public Map<String, Double> getFatoresEmissao() {
        return new HashMap<>(fatoresEmissao);
    }
    public Map<String, Double> getAbatimentosDefeito() {
        return new HashMap<>(abatimentosDefeito);
    }
}
