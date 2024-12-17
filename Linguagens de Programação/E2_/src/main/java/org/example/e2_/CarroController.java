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

public class CarroController {

    private Carro carro = new Carro();

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
    private Button btnAcelerar;

    @FXML
    private Button btnDiminuir;

    @FXML
    private TableView<String[]> tabelaCarros;

    @FXML
    private TableColumn<String[], String> colunaModelo;

    @FXML
    private TableColumn<String[], String> colunaCor;

    @FXML
    private TableColumn<String[], String> colunaAno;

    @FXML
    private Label mensagemExecucao;

    @FXML
    private Label indicadorVelocidade;


    @FXML
    private void handleAdicionarTabela() {

        String modelo = campoModelo.getText();
        String cor = campoCor.getText();
        String ano = campoAno.getText();


        String[] dadosCarro = {modelo, cor, ano};


        tabelaCarros.getItems().add(dadosCarro);


        salvarCarroNoBanco(modelo, cor, ano);


        mensagemExecucao.setText("Carro adicionado à tabela!");


        campoModelo.clear();
        campoCor.clear();
        campoAno.clear();
    }


    private void salvarCarroNoBanco(String modelo, String cor, String ano) {
        String sql = "INSERT INTO carros (modelo, cor, ano) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {


            preparedStatement.setString(1, modelo);
            preparedStatement.setString(2, cor);
            preparedStatement.setInt(3, Integer.parseInt(ano));


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao salvar no banco: " + e.getMessage());
        }
    }


    @FXML
    private void handleAcelerar() {
        carro.acelerar();
        mensagemExecucao.setText("Acelerando...");
        atualizarIndicadorVelocidade();
    }


    @FXML
    private void handleDiminuir() {
        carro.diminuir();
        mensagemExecucao.setText("Diminuindo...");
        atualizarIndicadorVelocidade();
    }


    private void atualizarIndicadorVelocidade() {
        indicadorVelocidade.setText("Velocidade atual: " + carro.getVelocidade() + " km/h");
    }


    @FXML
    public void initialize() {

        colunaModelo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
        colunaCor.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
        colunaAno.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));


        atualizarIndicadorVelocidade();


        carregarCarrosDoBanco();
    }


    private void carregarCarrosDoBanco() {
        String sql = "SELECT modelo, cor, ano FROM carros";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {


            tabelaCarros.getItems().clear();


            while (resultSet.next()) {
                String modelo = resultSet.getString("modelo");
                String cor = resultSet.getString("cor");
                String ano = resultSet.getString("ano");
                tabelaCarros.getItems().add(new String[]{modelo, cor, ano});
            }
        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao carregar carros: " + e.getMessage());
        }
    }


    @FXML
    private void handleEliminarTabela() {

        tabelaCarros.getItems().clear();


        deletarTodosCarrosNoBanco();


        mensagemExecucao.setText("Todos os carros foram deletados da tabela e do banco!");
    }


    private void deletarTodosCarrosNoBanco() {
        String sql = "DELETE FROM carros";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao deletar carros do banco: " + e.getMessage());
        }
    }


    @FXML
    private void handleEliminarCarro() {

        String modelo = campoModelo.getText();

        if (modelo.isEmpty()) {
            mensagemExecucao.setText("Por favor, insira o modelo do carro para excluir.");
            return;
        }


        deletarCarroNoBanco(modelo);


        tabelaCarros.getItems().removeIf(carro -> carro[0].equals(modelo));


        mensagemExecucao.setText("Carro " + modelo + " deletado da tabela e do banco!");
    }


    private void deletarCarroNoBanco(String modelo) {
        String sql = "DELETE FROM carros WHERE modelo = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {


            preparedStatement.setString(1, modelo);

            // Executa a consulta de deleção
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            mensagemExecucao.setText("Erro ao deletar carro do banco: " + e.getMessage());
        }
    }
}
