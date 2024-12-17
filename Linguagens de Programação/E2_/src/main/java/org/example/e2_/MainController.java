package org.example.e2_;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class MainController {

    // Método para abrir a tela do Carro
    @FXML
    private void handleCarroButton() {
        carregarTela("carro.fxml", "Carro");
    }

    // Método para abrir a tela do Smartphone
    @FXML
    private void handleSmartphoneButton() {
        carregarTela("smartphone.fxml", "Smartphone");
    }

    // Método para abrir a tela do Estudante
    @FXML
    private void handleEstudanteButton() {
        carregarTela("estudante.fxml", "Estudante");
    }

    // Método para abrir a tela do Curso
    @FXML
    private void handleCursoButton() {
        carregarTela("curso.fxml", "Curso");
    }

    // Método para abrir a tela do Produto
    @FXML
    private void handleProdutoButton() {
        carregarTela("produto.fxml", "Produto");
    }

    // Método para abrir a tela da Bicicleta
    @FXML
    private void handleBicicletaButton() {
        carregarTela("bicicleta.fxml", "Bicicleta");
    }

    // Método para abrir a tela do Restaurante
    @FXML
    private void handleRestauranteButton() {
        carregarTela("restaurante.fxml", "Restaurante");
    }

    // Método para abrir a tela do Livro
    @FXML
    private void handleLivroButton() {
        carregarTela("livro.fxml", "Livro");
    }

    // Método para abrir a tela da Pessoa
    @FXML
    private void handlePessoaButton() {
        carregarTela("pessoa.fxml", "Pessoa");
    }

    // Método para abrir a tela do Animal
    @FXML
    private void handleAnimalButton() {
        carregarTela("animal.fxml", "Animal");
    }

    // Método genérico para carregar as telas
    private void carregarTela(String fxml, String titulo) {
        try {
            // Carrega o arquivo FXML da tela desejada
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            AnchorPane root = loader.load();

            // Cria uma nova cena com o conteúdo carregado
            Scene scene = new Scene(root);

            // Cria e configura uma nova janela
            Stage newStage = new Stage();
            newStage.setTitle(titulo);
            newStage.setScene(scene);
            newStage.show();
        } catch (Exception e) {
            // Caso ocorra algum erro ao carregar a tela
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao abrir a tela");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
