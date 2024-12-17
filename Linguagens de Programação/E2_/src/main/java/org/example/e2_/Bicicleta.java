package org.example.e2_;

public class Bicicleta {

    private String modelo;
    private String tipo;
    private String cor;

    // Construtor
    public Bicicleta(String modelo, String tipo, String cor) {
        this.modelo = modelo;
        this.tipo = tipo;
        this.cor = cor;
    }

    // Métodos para exibir as informações
    public String mostrarDetalhes() {
        return "Modelo: " + modelo + ", Tipo: " + tipo + ", Cor: " + cor;
    }

    public String saudacao() {
        return "Esta bicicleta é perfeita para passeios!";
    }
}
