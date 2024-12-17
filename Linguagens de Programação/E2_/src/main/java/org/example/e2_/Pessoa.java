package org.example.e2_;

public class Pessoa {

    private String nome;
    private int idade;
    private String cidade;

    // Construtor
    public Pessoa(String nome, int idade, String cidade) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
    }

    // Métodos para exibir as informações
    public String mostrarInformacoes() {
        return "Nome: " + nome + ", Idade: " + idade + ", Cidade: " + cidade;
    }

    public String saudacao() {
        return "Olá, " + nome + "! Bem-vindo!";
    }
}
