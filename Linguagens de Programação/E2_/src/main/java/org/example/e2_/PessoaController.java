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

public class PessoaController {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoIdade;

    @FXML
    private TextField campoEndereco;

    @FXML
    private Button btnAdicionarTabela;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<String[]> tabelaPessoas;

    @FXML
    private TableColumn<String[], String> colunaNome;

    @FXML
    private TableColumn<String[], String> colunaIdade;

    @FXML
    private TableColumn<String[], String> colunaEndereco;

    @FXML
    private Label mensagemExecucao;  // Label para exibir a mensagem

    // Método para adicionar os dados à tabela e salvar no banco de dados
    @FXML
    private void handleAdicionarTabela() {
        String nome = campoNome.getText();
        String idade = campoIdade.getText();
        String endereco = campoEndereco.getText();

        String[] dadosPessoa = {nome, idade, endereco};
        tabelaPessoas.getItems().add(dadosPessoa);

        // Salva a pessoa no banco de dados
        salvarPessoaNoBanco(nome, idade, endereco);

        // Exibe mensagem de sucesso
        mensagemExecucao.setText("Pessoa adicionada à tabela!");

        // Limpa os campos de texto após adicionar
        campoNome.clear();
        campoIdade.clear();
        campoEndereco.clear();
    }

    // Método para salvar a pessoa no banco de dados (PostgreSQL)
    private void salvarPessoaNoBanco(String nome, String idade, String endereco) {
        String sql = "INSERT INTO pessoas (nome, idade, endereco) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            preparedStatement.setString(1, nome);
            preparedStatement.setInt(2, Integer.parseInt(idade));
            preparedStatement.setString(3, endereco);

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
        tabelaPessoas.getItems().clear();

        // Deleta todas as pessoas do banco de dados
        deletarTodasPessoasNoBanco();

        // Exibe uma mensagem de sucesso
        mensagemExecucao.setText("Tabela limpa!");
    }

    // Método para deletar todas as pessoas do banco de dados (PostgreSQL)
    private void deletarTodasPessoasNoBanco() {
        String sql = "DELETE FROM pessoas";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Executa a consulta de deleção
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao deletar pessoas do banco: " + e.getMessage());
        }
    }

    // Inicializa a tabela e carrega os dados do banco de dados
    @FXML
    public void initialize() {
        // Define como os dados serão exibidos nas colunas
        colunaNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
        colunaIdade.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
        colunaEndereco.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));

        // Carregar as pessoas do banco de dados
        carregarPessoasDoBanco();
    }

    // Método para carregar as pessoas do banco de dados e exibir na tabela
    private void carregarPessoasDoBanco() {
        String sql = "SELECT nome, idade, endereco FROM pessoas";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Limpa a tabela antes de carregar novos dados
            tabelaPessoas.getItems().clear();

            // Adiciona as pessoas à tabela
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String idade = resultSet.getString("idade");
                String endereco = resultSet.getString("endereco");
                tabelaPessoas.getItems().add(new String[]{nome, idade, endereco});
            }
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao carregar pessoas: " + e.getMessage());
        }
    }
}
