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

public class RestauranteController {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoEndereco;

    @FXML
    private TextField campoTipo;

    @FXML
    private Button btnAdicionarTabela;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<String[]> tabelaRestaurantes;

    @FXML
    private TableColumn<String[], String> colunaNome;

    @FXML
    private TableColumn<String[], String> colunaEndereco;

    @FXML
    private TableColumn<String[], String> colunaTipo;

    @FXML
    private Label mensagemExecucao;  // Label para exibir a mensagem

    // Método para adicionar os dados à tabela e salvar no banco de dados
    @FXML
    private void handleAdicionarTabela() {
        String nome = campoNome.getText();
        String endereco = campoEndereco.getText();
        String tipo = campoTipo.getText();

        String[] dadosRestaurante = {nome, endereco, tipo};
        tabelaRestaurantes.getItems().add(dadosRestaurante);

        // Salva o restaurante no banco de dados
        salvarRestauranteNoBanco(nome, endereco, tipo);

        // Exibe mensagem de sucesso
        mensagemExecucao.setText("Restaurante adicionado à tabela!");

        // Limpa os campos de texto após adicionar
        campoNome.clear();
        campoEndereco.clear();
        campoTipo.clear();
    }

    // Método para salvar o restaurante no banco de dados (PostgreSQL)
    private void salvarRestauranteNoBanco(String nome, String endereco, String tipo) {
        String sql = "INSERT INTO restaurantes (nome, endereco, tipo) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, endereco);
            preparedStatement.setString(3, tipo);

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
        tabelaRestaurantes.getItems().clear();

        // Deleta todos os restaurantes do banco de dados
        deletarTodosRestaurantesNoBanco();

        // Exibe uma mensagem de sucesso
        mensagemExecucao.setText("Tabela limpa!");
    }

    // Método para deletar todos os restaurantes do banco de dados (PostgreSQL)
    private void deletarTodosRestaurantesNoBanco() {
        String sql = "DELETE FROM restaurantes";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Executa a consulta de deleção
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao deletar restaurantes do banco: " + e.getMessage());
        }
    }

    // Inicializa a tabela e carrega os dados do banco de dados
    @FXML
    public void initialize() {
        // Define como os dados serão exibidos nas colunas
        colunaNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
        colunaEndereco.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
        colunaTipo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));

        // Carregar os restaurantes do banco de dados
        carregarRestaurantesDoBanco();
    }

    // Método para carregar os restaurantes do banco de dados e exibir na tabela
    private void carregarRestaurantesDoBanco() {
        String sql = "SELECT nome, endereco, tipo FROM restaurantes";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Limpa a tabela antes de carregar novos dados
            tabelaRestaurantes.getItems().clear();

            // Adiciona os restaurantes à tabela
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");
                String tipo = resultSet.getString("tipo");
                tabelaRestaurantes.getItems().add(new String[]{nome, endereco, tipo});
            }
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao carregar restaurantes: " + e.getMessage());
        }
    }
}
