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

public class SmartphoneController {

    @FXML private TextField campoMarca;
    @FXML private TextField campoModelo;
    @FXML private TextField campoAno;

    @FXML private Button btnAdicionarTabela;
    @FXML private Button btnEliminar;

    @FXML private TableView<String[]> tabelaSmartphones;
    @FXML private TableColumn<String[], String> colunaMarca;
    @FXML private TableColumn<String[], String> colunaModelo;
    @FXML private TableColumn<String[], String> colunaAno;

    @FXML private Label mensagemExecucao;

    // Método para adicionar os dados à tabela e salvar no banco de dados
    @FXML
    private void handleAdicionarTabela() {
        String marca = campoMarca.getText();
        String modelo = campoModelo.getText();
        String ano = campoAno.getText();

        String[] dadosSmartphone = {marca, modelo, ano};
        tabelaSmartphones.getItems().add(dadosSmartphone);

        // Salva o smartphone no banco de dados
        salvarSmartphoneNoBanco(marca, modelo, ano);

        // Exibe mensagem de sucesso
        mensagemExecucao.setText("Smartphone adicionado à tabela!");

        // Limpa os campos de texto após adicionar
        campoMarca.clear();
        campoModelo.clear();
        campoAno.clear();
    }

    // Método para salvar o smartphone no banco de dados (PostgreSQL)
    private void salvarSmartphoneNoBanco(String marca, String modelo, String ano) {
        String sql = "INSERT INTO smartphones (marca, modelo, ano) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            preparedStatement.setString(1, marca);
            preparedStatement.setString(2, modelo);
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
        tabelaSmartphones.getItems().clear();

        // Deleta todos os smartphones do banco de dados
        deletarTodosSmartphonesNoBanco();

        // Exibe uma mensagem de sucesso
        mensagemExecucao.setText("Tabela limpa!");
    }

    // Método para deletar todos os smartphones do banco de dados (PostgreSQL)
    private void deletarTodosSmartphonesNoBanco() {
        String sql = "DELETE FROM smartphones";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Executa a consulta de deleção
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao deletar smartphones do banco: " + e.getMessage());
        }
    }

    // Inicializa a tabela e carrega os dados do banco de dados
    @FXML
    public void initialize() {
        // Define como os dados serão exibidos nas colunas
        colunaMarca.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
        colunaModelo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
        colunaAno.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));

        // Carregar os smartphones do banco de dados
        carregarSmartphonesDoBanco();
    }

    // Método para carregar os smartphones do banco de dados e exibir na tabela
    private void carregarSmartphonesDoBanco() {
        String sql = "SELECT marca, modelo, ano FROM smartphones";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Limpa a tabela antes de carregar novos dados
            tabelaSmartphones.getItems().clear();

            // Adiciona os smartphones à tabela
            while (resultSet.next()) {
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                String ano = resultSet.getString("ano");
                tabelaSmartphones.getItems().add(new String[]{marca, modelo, ano});
            }
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao carregar smartphones: " + e.getMessage());
        }
    }
}
