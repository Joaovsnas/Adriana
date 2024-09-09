import java.util.Scanner;

public class Tinta {
    // Atributos
    private String cor;        // Cor da tinta (ex: "Vermelha", "Azul")
    private double quantidade; // Quantidade de tinta disponível (em litros)
    private String tipo;       // Tipo de tinta (ex: "Acrílica", "Óleo")

    // Construtor
    public Tinta(String cor, double quantidade, String tipo) {
        this.cor = cor;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    // Métodos
    public void pintar() {
        if (quantidade > 0) {
            System.out.println("A tinta está sendo usada para pintar.");
        } else {
            System.out.println("Não há tinta suficiente para pintar.");
        }
    }

    public void removerPintura() {
        System.out.println("A pintura está sendo removida.");
    }

    public void verificarQuantidade() {
        System.out.println("Quantidade de tinta disponível: " + quantidade + " litros.");
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Tinta{" +
                "cor='" + cor + '\'' +
                ", quantidade=" + quantidade +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    // Método main para interagir com o usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Coletar dados do usuário
        System.out.print("Digite a cor da tinta (ex: Vermelha, Azul): ");
        String cor = scanner.nextLine();

        System.out.print("Digite a quantidade de tinta disponível (em litros): ");
        double quantidade = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha após o número

        System.out.print("Digite o tipo de tinta (ex: Acrílica, Óleo): ");
        String tipo = scanner.nextLine();

        // Criar uma instância da tinta com os dados fornecidos
        Tinta tinta = new Tinta(cor, quantidade, tipo);

        // Mostrar os dados da tinta e chamar métodos
        System.out.println("\nDetalhes da Tinta:");
        System.out.println(tinta);

        tinta.pintar();
        tinta.removerPintura();
        tinta.verificarQuantidade();

        // Fechar o scanner
        scanner.close();
    }
}
