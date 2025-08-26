package sistema.utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LoggerSistema {
    private static LoggerSistema instance;
    private final Logger logger;
    private final ConfiguracaoLog config;
    private final SimpleDateFormat dateFormat;
    private LoggerSistema() {
        this.logger = Logger.getLogger(LoggerSistema.class.getName());
        this.config = ConfiguracaoLog.getInstance();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    public static LoggerSistema getInstance() {
        if (instance == null) {
            instance = new LoggerSistema();
        }
        return instance;
    }
    public void logar(String tipo, String mensagem) {
        String logEntry = formatarLog(tipo, mensagem);
        logger.info(logEntry);
        if (config.isLogArquivoHabilitado()) {
            escreverLogArquivo(logEntry);
        }
    }
    private String formatarLog(String tipo, String mensagem) {
        Date agora = new Date();
        return String.format("[%s] %s: %s",
            dateFormat.format(agora), tipo, mensagem);
    }
    private void escreverLogArquivo(String logEntry) {
        try {
            File arquivoLog = new File(config.getCaminhoArquivo());
            File diretorio = arquivoLog.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }
            if (arquivoLog.exists() && arquivoLog.length() > config.getTamanhoMaximoArquivo()) {
                rotacionarArquivoLog(arquivoLog);
            }
            try (FileWriter writer = new FileWriter(arquivoLog, true)) {
                writer.write(logEntry + System.lineSeparator());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao escrever log no arquivo: " + e.getMessage(), e);
        }
    }
    private void rotacionarArquivoLog(File arquivoAtual) {
        try {
            String nomeArquivo = arquivoAtual.getName();
            String extensao = "";
            String nomeBase = nomeArquivo;
            int pontoIndex = nomeArquivo.lastIndexOf('.');
            if (pontoIndex > 0) {
                extensao = nomeArquivo.substring(pontoIndex);
                nomeBase = nomeArquivo.substring(0, pontoIndex);
            }
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String novoNome = nomeBase + "_" + timestamp + extensao;
            File arquivoRotacionado = new File(arquivoAtual.getParent(), novoNome);
            arquivoAtual.renameTo(arquivoRotacionado);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao rotacionar arquivo de log: " + e.getMessage(), e);
        }
    }
    public void logarErro(String tipo, String mensagem, Throwable erro) {
        String logEntry = formatarLog(tipo, mensagem + " - Erro: " + erro.getMessage());
        logger.log(Level.SEVERE, logEntry, erro);
        if (config.isLogArquivoHabilitado()) {
            escreverLogArquivo(logEntry);
        }
    }
}
