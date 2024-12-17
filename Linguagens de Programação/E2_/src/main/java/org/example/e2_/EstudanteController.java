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

public class EstudanteController {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCurso;

    @FXML
    private TextField campoIdade;

    @FXML
    private Button btnAdicionarTabela;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<String[]> tabelaEstudantes;

    @FXML
    private TableColumn<String[], String> colunaNome;

    @FXML
    private TableColumn<String[], String> colunaCurso;

    @FXML
    private TableColumn<String[], String> colunaIdade;

    @FXML
    private Label mensagemExecucao;  // Label para exibir a mensagem

    // Método para adicionar os dados à tabela e salvar no banco de dados
    @FXML
    private void handleAdicionarTabela() {
        String nome = campoNome.getText();
        String curso = campoCurso.getText();
        String idade = campoIdade.getText();

        String[] dadosEstudante = {nome, curso, idade};
        tabelaEstudantes.getItems().add(dadosEstudante);

        // Salva o estudante no banco de dados
        salvarEstudanteNoBanco(nome, curso, idade);

        // Exibe mensagem de sucesso
        mensagemExecucao.setText("Estudante adicionado à tabela!");

        // Limpa os campos de texto após adicionar
        campoNome.clear();
        campoCurso.clear();
        campoIdade.clear();
    }

    // Método para salvar o estudante no banco de dados (PostgreSQL)
    private void salvarEstudanteNoBanco(String nome, String curso, String idade) {
        String sql = "INSERT INTO estudantes (nome, curso, idade) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, curso);
            preparedStatement.setString(3, idade);

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
        tabelaEstudantes.getItems().clear();

        // Deleta todos os estudantes do banco de dados
        deletarTodosEstudantesNoBanco();

        // Exibe uma mensagem de sucesso
        mensagemExecucao.setText("Tabela limpa!");
    }

    // Método para deletar todos os estudantes do banco de dados (PostgreSQL)
    private void deletarTodosEstudantesNoBanco() {
        String sql = "DELETE FROM estudantes";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Executa a consulta de deleção
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao deletar estudantes do banco: " + e.getMessage());
        }
    }

    // Inicializa a tabela e carrega os dados do banco de dados
    @FXML
    public void initialize() {
        // Define como os dados serão exibidos nas colunas
        colunaNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
        colunaCurso.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
        colunaIdade.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));

        // Carregar os estudantes do banco de dados
        carregarEstudantesDoBanco();
    }

    // Método para carregar os estudantes do banco de dados e exibir na tabela
    private void carregarEstudantesDoBanco() {
        String sql = "SELECT nome, curso, idade FROM estudantes";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Limpa a tabela antes de carregar novos dados
            tabelaEstudantes.getItems().clear();

            // Adiciona os estudantes à tabela
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String curso = resultSet.getString("curso");
                String idade = resultSet.getString("idade");
                tabelaEstudantes.getItems().add(new String[]{nome, curso, idade});
            }
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao carregar estudantes: " + e.getMessage());
        }
    }
}
