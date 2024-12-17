package org.example.e2_;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroController {

    @FXML
    private TextField campoTitulo;

    @FXML
    private TextField campoAutor;

    @FXML
    private TextField campoAno;

    @FXML
    private Button btnAdicionarTabela;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<String[]> tabelaLivros;

    @FXML
    private TableColumn<String[], String> colunaTitulo;

    @FXML
    private TableColumn<String[], String> colunaAutor;

    @FXML
    private TableColumn<String[], String> colunaAno;

    @FXML
    private Label mensagemExecucao;

    // Método para adicionar os dados à tabela e salvar no banco de dados
    @FXML
    private void handleAdicionarTabela() {
        String titulo = campoTitulo.getText();
        String autor = campoAutor.getText();
        String ano = campoAno.getText();

        String[] dadosLivro = {titulo, autor, ano};
        tabelaLivros.getItems().add(dadosLivro);

        // Salva o livro no banco de dados
        salvarLivroNoBanco(titulo, autor, ano);

        // Exibe mensagem de sucesso
        mensagemExecucao.setText("Livro adicionado à tabela!");

        // Limpa os campos de texto após adicionar
        campoTitulo.clear();
        campoAutor.clear();
        campoAno.clear();
    }

    // Método para salvar o livro no banco de dados (PostgreSQL)
    private void salvarLivroNoBanco(String titulo, String autor, String ano) {
        String sql = "INSERT INTO livros (titulo, autor, ano) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            preparedStatement.setString(1, titulo);
            preparedStatement.setString(2, autor);
            preparedStatement.setString(3, ano);

            // Executa a consulta
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao salvar no banco: " + e.getMessage());
        }
    }

    // Método para eliminar os dados da tabela e do banco de dados
    @FXML
    private void handleEliminarTabela() {
        // Limpa todos os itens da tabela
        tabelaLivros.getItems().clear();

        // Deleta todos os livros do banco de dados
        deletarTodosLivrosNoBanco();

        // Exibe uma mensagem de sucesso
        mensagemExecucao.setText("Tabela limpa!");
    }

    // Método para deletar todos os livros do banco de dados (PostgreSQL)
    private void deletarTodosLivrosNoBanco() {
        String sql = "DELETE FROM livros";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Executa a consulta de deleção
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao deletar livros do banco: " + e.getMessage());
        }
    }

    // Inicializa a tabela e carrega os dados do banco de dados
    @FXML
    public void initialize() {
        // Define como os dados serão exibidos nas colunas
        colunaTitulo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
        colunaAutor.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
        colunaAno.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));

        // Carregar os livros do banco de dados
        carregarLivrosDoBanco();
    }

    // Método para carregar os livros do banco de dados e exibir na tabela
    private void carregarLivrosDoBanco() {
        String sql = "SELECT titulo, autor, ano FROM livros";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Limpa a tabela antes de carregar novos dados
            tabelaLivros.getItems().clear();

            // Adiciona os livros à tabela
            while (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String ano = resultSet.getString("ano");
                tabelaLivros.getItems().add(new String[]{titulo, autor, ano});
            }
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao carregar livros: " + e.getMessage());
        }
    }
}
