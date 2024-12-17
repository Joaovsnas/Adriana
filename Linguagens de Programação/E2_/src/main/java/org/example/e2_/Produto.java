package org.example.e2_;

public class Produto {

    private String nome;
    private double preco;
    private int quantidadeEstoque;

    // Construtor
    public Produto(String nome, double preco, int quantidadeEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Métodos para exibir as informações
    public String mostrarDetalhes() {
        return "Produto: " + nome + ", Preço: R$ " + preco + ", Estoque: " + quantidadeEstoque;
    }

    public String saudacao() {
        return "Este produto está disponível para venda!";
    }
}
