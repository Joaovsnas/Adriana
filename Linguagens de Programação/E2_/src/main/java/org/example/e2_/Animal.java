package org.example.e2_;

public class Animal {

    private String nome;
    private String tipo;
    private int idade;

    // Construtor
    public Animal(String nome, String tipo, int idade) {
        this.nome = nome;
        this.tipo = tipo;
        this.idade = idade;
    }

    // Métodos para exibir as informações
    public String mostrarDetalhes() {
        return "Nome: " + nome + ", Tipo: " + tipo + ", Idade: " + idade + " anos.";
    }

    public String saudacaoAnimal() {
        return "Olá, " + nome + "! Como você está?";
    }
}
