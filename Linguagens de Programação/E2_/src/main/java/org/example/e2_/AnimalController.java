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

public class AnimalController {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoEspecie;

    @FXML
    private TextField campoIdade;

    @FXML
    private Button btnAdicionarTabela;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<String[]> tabelaAnimais;

    @FXML
    private TableColumn<String[], String> colunaNome;

    @FXML
    private TableColumn<String[], String> colunaEspecie;

    @FXML
    private TableColumn<String[], String> colunaIdade;

    @FXML
    private Label mensagemExecucao;  // Label para exibir a mensagem

    // Método para adicionar os dados à tabela e salvar no banco de dados
    @FXML
    private void handleAdicionarTabela() {
        String nome = campoNome.getText();
        String especie = campoEspecie.getText();
        String idade = campoIdade.getText();

        String[] dadosAnimal = {nome, especie, idade};
        tabelaAnimais.getItems().add(dadosAnimal);

        // Salva o animal no banco de dados
        salvarAnimalNoBanco(nome, especie, idade);

        // Exibe mensagem de sucesso
        mensagemExecucao.setText("Animal adicionado à tabela!");

        // Limpa os campos de texto após adicionar
        campoNome.clear();
        campoEspecie.clear();
        campoIdade.clear();
    }

    // Método para salvar o animal no banco de dados
    private void salvarAnimalNoBanco(String nome, String especie, String idade) {
        String sql = "INSERT INTO animais (nome, especie, idade) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, especie);
            preparedStatement.setInt(3, Integer.parseInt(idade));

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
        tabelaAnimais.getItems().clear();

        // Deleta todos os animais do banco de dados
        deletarTodosAnimaisNoBanco();

        // Exibe uma mensagem de sucesso
        mensagemExecucao.setText("Tabela limpa!");
    }

    // Método para deletar todos os animais do banco de dados
    private void deletarTodosAnimaisNoBanco() {
        String sql = "DELETE FROM animais";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Executa a consulta de deleção
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao deletar animais do banco: " + e.getMessage());
        }
    }

    // Inicializa a tabela e carrega os dados do banco de dados
    @FXML
    public void initialize() {
        // Define como os dados serão exibidos nas colunas
        colunaNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
        colunaEspecie.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
        colunaIdade.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));

        // Carregar os animais do banco de dados
        carregarAnimaisDoBanco();
    }

    // Método para carregar os animais do banco de dados e exibir na tabela
    private void carregarAnimaisDoBanco() {
        String sql = "SELECT nome, especie, idade FROM animais";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Limpa a tabela antes de carregar novos dados
            tabelaAnimais.getItems().clear();

            // Adiciona os animais à tabela
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String especie = resultSet.getString("especie");
                String idade = resultSet.getString("idade");
                tabelaAnimais.getItems().add(new String[]{nome, especie, idade});
            }
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao carregar animais: " + e.getMessage());
        }
    }
}
