package org.example.e2_;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CursoController {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoDuracao;

    @FXML
    private TextField campoProfessor;

    @FXML
    private Button btnAdicionarTabela;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<String[]> tabelaCursos;

    @FXML
    private TableColumn<String[], String> colunaNome;

    @FXML
    private TableColumn<String[], String> colunaDuracao;

    @FXML
    private TableColumn<String[], String> colunaProfessor;

    @FXML
    private Label mensagemExecucao;  // Label para exibir a mensagem

    // Método para adicionar os dados à tabela e ao banco de dados
    @FXML
    private void handleAdicionarTabela() {
        // Recupera os dados dos campos de texto
        String nome = campoNome.getText();
        String duracao = campoDuracao.getText();
        String professor = campoProfessor.getText();

        // Cria um array com os dados do curso
        String[] dadosCurso = {nome, duracao, professor};

        // Adiciona os dados à tabela
        tabelaCursos.getItems().add(dadosCurso);

        // Salva o curso no banco de dados
        salvarCursoNoBanco(nome, duracao, professor);

        // Exibe uma mensagem de sucesso
        mensagemExecucao.setText("Curso adicionado à tabela!");

        // Limpa os campos de texto após adicionar
        campoNome.clear();
        campoDuracao.clear();
        campoProfessor.clear();
    }

    // Método para salvar o curso no banco de dados
    private void salvarCursoNoBanco(String nome, String duracao, String professor) {
        String sql = "INSERT INTO cursos (nome, duracao, professor) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, duracao);
            preparedStatement.setString(3, professor);

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
        tabelaCursos.getItems().clear();

        // Deleta todos os cursos da tabela no banco de dados
        deletarTodosCursosNoBanco();

        // Exibe uma mensagem de sucesso
        mensagemExecucao.setText("Tabela limpa e cursos deletados do banco!");
    }

    // Método para deletar todos os cursos do banco de dados
    private void deletarTodosCursosNoBanco() {
        String sql = "DELETE FROM cursos";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Executa a consulta de deleção
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao deletar cursos do banco: " + e.getMessage());
        }
    }

    // Inicializa a tabela
    @FXML
    public void initialize() {
        // Define como os dados serão exibidos nas colunas
        colunaNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
        colunaDuracao.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
        colunaProfessor.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));
    }
}
