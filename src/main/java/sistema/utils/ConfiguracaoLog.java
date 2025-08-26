package sistema.utils;
public class ConfiguracaoLog {
    private static ConfiguracaoLog instance;
    private String tipoLog = "CSV";
    private String caminhoArquivo = "logs/sistema.log";
    private long tamanhoMaximoArquivo = 10 * 1024 * 1024;
    private boolean logArquivoHabilitado = true;
    private ConfiguracaoLog() {
    }
    public static ConfiguracaoLog getInstance() {
        if (instance == null) {
            instance = new ConfiguracaoLog();
        }
        return instance;
    }
    public String getTipoLog() {
        return tipoLog;
    }
    public void setTipoLog(String tipoLog) {
        this.tipoLog = tipoLog;
    }
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }
    public long getTamanhoMaximoArquivo() {
        return tamanhoMaximoArquivo;
    }
    public void setTamanhoMaximoArquivo(long tamanhoMaximoArquivo) {
        this.tamanhoMaximoArquivo = tamanhoMaximoArquivo;
    }
    public boolean isLogArquivoHabilitado() {
        return logArquivoHabilitado;
    }
    public void setLogArquivoHabilitado(boolean logArquivoHabilitado) {
        this.logArquivoHabilitado = logArquivoHabilitado;
    }
}
