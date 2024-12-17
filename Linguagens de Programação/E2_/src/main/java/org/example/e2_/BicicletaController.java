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

public class BicicletaController {

    @FXML
    private TextField campoModelo;

    @FXML
    private TextField campoCor;

    @FXML
    private TextField campoAno;

    @FXML
    private Button btnAdicionarTabela;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<String[]> tabelaBicicletas;

    @FXML
    private TableColumn<String[], String> colunaModelo;

    @FXML
    private TableColumn<String[], String> colunaCor;

    @FXML
    private TableColumn<String[], String> colunaAno;

    @FXML
    private Label mensagemExecucao;  // Label para exibir a mensagem

    // Método para adicionar os dados à tabela e salvar no banco de dados
    @FXML
    private void handleAdicionarTabela() {
        String modelo = campoModelo.getText();
        String cor = campoCor.getText();
        String ano = campoAno.getText();

        String[] dadosBicicleta = {modelo, cor, ano};
        tabelaBicicletas.getItems().add(dadosBicicleta);

        // Salva a bicicleta no banco de dados
        salvarBicicletaNoBanco(modelo, cor, ano);

        // Exibe mensagem de sucesso
        mensagemExecucao.setText("Bicicleta adicionada à tabela!");

        // Limpa os campos de texto após adicionar
        campoModelo.clear();
        campoCor.clear();
        campoAno.clear();
    }

    // Método para salvar a bicicleta no banco de dados (PostgreSQL)
    private void salvarBicicletaNoBanco(String modelo, String cor, String ano) {
        String sql = "INSERT INTO bicicletas (modelo, cor, ano) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            preparedStatement.setString(1, modelo);
            preparedStatement.setString(2, cor);
            preparedStatement.setInt(3, Integer.parseInt(ano));

            // Executa a consulta
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao salvar no banco: " + e.getMessage());
        }
    }

    // Método para eliminar os dados da tabela
    @FXML
    private void handleEliminarTabela() {
        // Limpa todos os itens da tabela
        tabelaBicicletas.getItems().clear();

        // Deleta todas as bicicletas do banco de dados
        deletarTodasBicicletasNoBanco();

        // Exibe uma mensagem de sucesso
        mensagemExecucao.setText("Tabela limpa!");
    }

    // Método para deletar todas as bicicletas do banco de dados (PostgreSQL)
    private void deletarTodasBicicletasNoBanco() {
        String sql = "DELETE FROM bicicletas";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Executa a consulta de deleção
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao deletar bicicletas do banco: " + e.getMessage());
        }
    }

    // Inicializa a tabela e carrega os dados do banco de dados
    @FXML
    public void initialize() {
        // Define como os dados serão exibidos nas colunas
        colunaModelo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
        colunaCor.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
        colunaAno.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));

        // Carregar as bicicletas do banco de dados
        carregarBicicletasDoBanco();
    }

    // Método para carregar as bicicletas do banco de dados e exibir na tabela
    private void carregarBicicletasDoBanco() {
        String sql = "SELECT modelo, cor, ano FROM bicicletas";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Limpa a tabela antes de carregar novos dados
            tabelaBicicletas.getItems().clear();

            // Adiciona as bicicletas à tabela
            while (resultSet.next()) {
                String modelo = resultSet.getString("modelo");
                String cor = resultSet.getString("cor");
                String ano = resultSet.getString("ano");
                tabelaBicicletas.getItems().add(new String[]{modelo, cor, ano});
            }
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao carregar bicicletas: " + e.getMessage());
        }
    }
}
