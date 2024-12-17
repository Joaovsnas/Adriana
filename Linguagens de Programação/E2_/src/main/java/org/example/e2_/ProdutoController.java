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

public class ProdutoController {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoPreco;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private Button btnAdicionarTabela;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<String[]> tabelaProdutos;

    @FXML
    private TableColumn<String[], String> colunaNome;

    @FXML
    private TableColumn<String[], String> colunaPreco;

    @FXML
    private TableColumn<String[], String> colunaQuantidade;

    @FXML
    private Label mensagemExecucao;  // Label para exibir a mensagem

    // Método para adicionar os dados à tabela e salvar no banco de dados
    @FXML
    private void handleAdicionarTabela() {
        String nome = campoNome.getText();
        String preco = campoPreco.getText();
        String quantidade = campoQuantidade.getText();

        String[] dadosProduto = {nome, preco, quantidade};
        tabelaProdutos.getItems().add(dadosProduto);

        // Salva o produto no banco de dados
        salvarProdutoNoBanco(nome, preco, quantidade);

        // Exibe mensagem de sucesso
        mensagemExecucao.setText("Produto adicionado à tabela!");

        // Limpa os campos de texto após adicionar
        campoNome.clear();
        campoPreco.clear();
        campoQuantidade.clear();
    }

    // Método para salvar o produto no banco de dados (PostgreSQL)
    private void salvarProdutoNoBanco(String nome, String preco, String quantidade) {
        String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, preco);
            preparedStatement.setString(3, quantidade);

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
        tabelaProdutos.getItems().clear();

        // Deleta todos os produtos do banco de dados
        deletarTodosProdutosNoBanco();

        // Exibe uma mensagem de sucesso
        mensagemExecucao.setText("Tabela limpa!");
    }

    // Método para deletar todos os produtos do banco de dados (PostgreSQL)
    private void deletarTodosProdutosNoBanco() {
        String sql = "DELETE FROM produtos";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            // Executa a consulta de deleção
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao deletar produtos do banco: " + e.getMessage());
        }
    }

    // Inicializa a tabela e carrega os dados do banco de dados
    @FXML
    public void initialize() {
        // Define como os dados serão exibidos nas colunas
        colunaNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
        colunaPreco.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
        colunaQuantidade.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));

        // Carregar os produtos do banco de dados
        carregarProdutosDoBanco();
    }

    // Método para carregar os produtos do banco de dados e exibir na tabela
    private void carregarProdutosDoBanco() {
        String sql = "SELECT nome, preco, quantidade FROM produtos";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Limpa a tabela antes de carregar novos dados
            tabelaProdutos.getItems().clear();

            // Adiciona os produtos à tabela
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String preco = resultSet.getString("preco");
                String quantidade = resultSet.getString("quantidade");
                tabelaProdutos.getItems().add(new String[]{nome, preco, quantidade});
            }
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao carregar produtos: " + e.getMessage());
        }
    }
}
