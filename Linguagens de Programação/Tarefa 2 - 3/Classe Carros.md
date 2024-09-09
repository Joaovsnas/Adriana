import java.util.Scanner;

public class Carro {
    // Atributos
    private String cor;    // Cor do carro (ex: "Vermelho", "Azul")
    private String marca;  // Marca do carro (ex: "Toyota", "Ford")
    private int portas;    // Número de portas do carro

    // Construtor
    public Carro(String cor, String marca, int portas) {
        this.cor = cor;
        this.marca = marca;
        this.portas = portas;
    }

    // Métodos
    public void acelerar() {
        System.out.println("O carro está acelerando.");
    }

    public void frear() {
        System.out.println("O carro está freando.");
    }

    public void buzinar() {
        System.out.println("O carro está buzinando: Beep beep!");
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Carro{" +
                "cor='" + cor + '\'' +
                ", marca='" + marca + '\'' +
                ", portas=" + portas +
                '}';
    }

    // Método main para interagir com o usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Coletar dados do usuário
        System.out.print("Digite a cor do carro (ex: Vermelho, Azul): ");
        String cor = scanner.nextLine();

        System.out.print("Digite a marca do carro (ex: Toyota, Ford): ");
        String marca = scanner.nextLine();

        System.out.print("Digite o número de portas do carro: ");
        int portas = scanner.nextInt();

        // Criar uma instância do carro com os dados fornecidos
        Carro carro = new Carro(cor, marca, portas);

        // Mostrar os dados do carro e chamar métodos
        System.out.println("\nDetalhes do Carro:");
        System.out.println(carro);

        carro.acelerar();
        carro.frear();
        carro.buzinar();

        // Fechar o scanner
        scanner.close();
    }
}
