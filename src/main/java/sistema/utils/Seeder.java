/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema.utils;

/**
 *
 * @author Natan
 */

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.sql.ResultSet;

public class Seeder {
    private static final String SQL_FILE_PATH = "src/main/resources/bancoloja.sql";

    // Verificar se o banco de dados existe e está vazio
    
    public static void seedData() {
        Connection connection = null;
        BufferedReader reader = null;

        try {
            connection = DatabaseConfig.getConnection();
            reader = new BufferedReader(new FileReader(SQL_FILE_PATH));
            StringBuilder sql = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }

            // Executando o script SQL
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql.toString());
                System.out.println("Banco de dados populado com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao executar o script SQL: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de script SQL: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou executar o script no banco de dados: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
                if (connection != null) connection.close();
            } catch (IOException | SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    // Método para verificar se o banco de dados está vazio
    private static boolean isDatabaseEmpty() {
        try (Connection connection = DatabaseConfig.getConnection()) {
            // Verificar se a tabela "clientes" está vazia
            String checkQuery = "SELECT COUNT(*) FROM clientes";
            try (Statement stmt = connection.createStatement()) {
                var rs = stmt.executeQuery(checkQuery);
                if (rs.next()) {
                    return rs.getInt(1) == 0;  // Se o número de registros for 0, o banco está vazio
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar se o banco está vazio: " + e.getMessage());
        }
        return true;  // Retorna true se não conseguir verificar
    }

    // Método para carregar o arquivo SQL e popular o banco
    private static void loadData() {
        try (Connection connection = DatabaseConfig.getConnection()) {
            // Lê o arquivo SQL
            String sqlFile = "src/main/resources/bancoloja.sql";  // Caminho correto para o arquivo .sql
            BufferedReader reader = new BufferedReader(new FileReader(sqlFile));
            String line;
            StringBuilder sql = new StringBuilder();

            // Lê o arquivo linha por linha e constrói o comando SQL
            while ((line = reader.readLine()) != null) {
                sql.append(line);
                sql.append("\n");
            }

            // Executa o script SQL
            Statement statement = connection.createStatement();
            statement.execute(sql.toString());
            System.out.println("Banco de dados criado e dados carregados com sucesso!");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}