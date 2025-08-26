package sistema.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseConfig {
    private static final Logger logger = Logger.getLogger(DatabaseConfig.class.getName());
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/loja_vestuario";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "";
    private static final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = criarConexao();
        }
        return connection;
    }
    
    private static Connection criarConexao() throws SQLException {
        try {
            Class.forName(DEFAULT_DRIVER);
            Properties props = carregarConfiguracoes();
            String url = props.getProperty("db.url", DEFAULT_URL);
            String user = props.getProperty("db.username", DEFAULT_USER);
            String password = props.getProperty("db.password", DEFAULT_PASSWORD);
            logger.info("Conectando ao banco: " + url);
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(true);
            logger.info("Conexão estabelecida com sucesso!");
            return conn;
        } catch (ClassNotFoundException e) {
            logger.severe("Driver MySQL não encontrado: " + e.getMessage());
            throw new SQLException("Driver MySQL não encontrado", e);
        } catch (SQLException e) {
            logger.severe("Erro ao conectar ao banco: " + e.getMessage());
            throw e;
        }
    }
    
    private static Properties carregarConfiguracoes() {
        Properties props = new Properties();
        try {
            props.load(DatabaseConfig.class.getClassLoader().getResourceAsStream("config.properties"));
            logger.info("Configurações carregadas do arquivo config.properties");
        } catch (Exception e) {
            logger.warning("Arquivo config.properties não encontrado, usando configurações padrão");
            props.setProperty("db.url", DEFAULT_URL);
            props.setProperty("db.username", DEFAULT_USER);
            props.setProperty("db.password", DEFAULT_PASSWORD);
        }
        return props;
    }
    
    public static void fecharConexao() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Conexão fechada com sucesso!");
            } catch (SQLException e) {
                logger.severe("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
    
    public static boolean testarConexao() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            logger.severe("Erro ao testar conexão: " + e.getMessage());
            return false;
        }
    }
}

