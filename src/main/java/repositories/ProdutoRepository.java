/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistema.models.Produto;
import sistema.utils.DatabaseConfig;
/**
 *
 * @author USER
 */
public class ProdutoRepository {
    public void salvarProduto(Produto produto) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "INSERT INTO produtos (codigo, nome, descricao) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, produto.getCodigo());
                stmt.setString(2, produto.getNome());
                stmt.setString(3, produto.getDescricao());
                stmt.executeUpdate();
                System.out.println("Produto salvo com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar produto: " + e.getMessage());
        }
    }

    public Produto getProdutoById(int id) {
        Produto produto = null;
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT * FROM produtos WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    produto = new Produto();
                    produto.setId(rs.getLong("id"));
                    produto.setCodigo(rs.getString("codigo"));
                    produto.setNome(rs.getString("nome"));
                    produto.setDescricao(rs.getString("descricao"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar produto: " + e.getMessage());
        }
        return produto;
    }
}
