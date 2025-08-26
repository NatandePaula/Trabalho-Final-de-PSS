package sistema.controllers;
import java.util.Map;
import sistema.ambiental.SistemaIndicadoresAmbientais;
import sistema.models.Produto;
import sistema.utils.LoggerSistema;
public class AmbientalController {
    private final SistemaIndicadoresAmbientais sistemaAmbiental;
    private final LoggerSistema logger;
    public AmbientalController() {
        this.sistemaAmbiental = SistemaIndicadoresAmbientais.getInstance();
        this.logger = LoggerSistema.getInstance();
    }
    public double calcularGWP(String material, double massa, String defeito) {
        if (material == null || material.trim().isEmpty()) {
            throw new IllegalArgumentException("Material não pode ser vazio");
        }
        if (massa <= 0) {
            throw new IllegalArgumentException("Massa deve ser maior que zero");
        }
        double gwp = sistemaAmbiental.calcularGWP(material, massa, defeito);
        logger.logar("CALCULAR_GWP", "GWP calculado para " + material + ": " + gwp + " kg CO2e");
        return gwp;
    }
    public double calcularMCI(double materialReciclado, double materialTotal, int vidaUtil) {
        if (materialTotal <= 0) {
            throw new IllegalArgumentException("Material total deve ser maior que zero");
        }
        if (vidaUtil <= 0) {
            throw new IllegalArgumentException("Vida útil deve ser maior que zero");
        }
        double mci = sistemaAmbiental.calcularMCI(materialReciclado, materialTotal, vidaUtil);
        logger.logar("CALCULAR_MCI", "MCI calculado: " + mci + " para produto com " +
                    (materialReciclado/materialTotal*100) + "% de material reciclado");
        return mci;
    }
    public double calcularImpactoCompra(java.util.List<Object> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Lista de itens não pode ser vazia");
        }
        double impactoTotal = sistemaAmbiental.calcularImpactoCompra(itens);
        logger.logar("CALCULAR_IMPACTO_COMPRA", "Impacto total da compra: " + impactoTotal + " kg CO2e");
        return impactoTotal;
    }
    public void adicionarFatorEmissao(String material, double fatorEmissao) {
        if (material == null || material.trim().isEmpty()) {
            throw new IllegalArgumentException("Material não pode ser vazio");
        }
        if (fatorEmissao < 0) {
            throw new IllegalArgumentException("Fator de emissão não pode ser negativo");
        }
        sistemaAmbiental.adicionarFatorEmissao(material, fatorEmissao);
        logger.logar("ADICIONAR_FATOR_EMISSAO", "Novo fator de emissão: " + material + " = " + fatorEmissao);
    }
    public void adicionarAbatimentoDefeito(String nivelDefeito, double abatimento) {
        if (nivelDefeito == null || nivelDefeito.trim().isEmpty()) {
            throw new IllegalArgumentException("Nível de defeito não pode ser vazio");
        }
        if (abatimento < 0 || abatimento > 1) {
            throw new IllegalArgumentException("Abatimento deve estar entre 0 e 1");
        }
        sistemaAmbiental.adicionarAbatimentoDefeito(nivelDefeito, abatimento);
        logger.logar("ADICIONAR_ABATIMENTO_DEFEITO", "Novo abatimento: " + nivelDefeito + " = " + abatimento);
    }
    public Map<String, Double> getFatoresEmissao() {
        return sistemaAmbiental.getFatoresEmissao();
    }
    public Map<String, Double> getAbatimentosDefeito() {
        return sistemaAmbiental.getAbatimentosDefeito();
    }
    public static class EstatisticasAmbientais {
        private final double totalGWP;
        private final double mediaMCI;
        private final int totalProdutos;
        public EstatisticasAmbientais(double totalGWP, double mediaMCI, int totalProdutos) {
            this.totalGWP = totalGWP;
            this.mediaMCI = mediaMCI;
            this.totalProdutos = totalProdutos;
        }
        public double getTotalGWP() {
            return totalGWP;
        }
        public double getMediaMCI() {
            return mediaMCI;
        }
        public int getTotalProdutos() {
            return totalProdutos;
        }
        @Override
        public String toString() {
            return String.format("Total GWP: %.2f kg CO2e, Média MCI: %.2f, Total Produtos: %d",
                               totalGWP, mediaMCI, totalProdutos);
        }
    }
}
