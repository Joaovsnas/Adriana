package org.example.e2_;

public class Restaurante {

    private String nome;
    private String localizacao;
    private String tipoComida;

    // Construtor
    public Restaurante(String nome, String localizacao, String tipoComida) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.tipoComida = tipoComida;
    }

    // Métodos para exibir as informações
    public String mostrarInformacoes() {
        return "Restaurante: " + nome + ", Localização: " + localizacao + ", Tipo de Comida: " + tipoComida;
    }

    public String saudacao() {
        return "Venha conhecer nosso restaurante especializado em " + tipoComida + "!";
    }
}
