import java.util.Scanner;

public class Flor {
    // Atributos
    private String cor;    // Cor da flor (ex: "Vermelha", "Amarela")
    private int tamanho;   // Tamanho da flor (em centímetros)
    private String tipo;   // Tipo da flor (ex: "Rosa", "Tulipa")

    // Construtor
    public Flor(String cor, int tamanho, String tipo) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.tipo = tipo;
    }

    // Métodos
    public void florir() {
        System.out.println("A " + tipo + " está florescendo.");
    }

    public void podar() {
        System.out.println("A " + tipo + " está sendo podada.");
    }

    public void regar() {
        System.out.println("A " + tipo + " está sendo regada.");
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Flor{" +
                "cor='" + cor + '\'' +
                ", tamanho=" + tamanho +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    // Método main para interagir com o usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Coletar dados do usuário
        System.out.print("Digite a cor da flor (ex: Vermelha, Amarela): ");
        String cor = scanner.nextLine();

        System.out.print("Digite o tamanho da flor (em cm): ");
        int tamanho = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("Digite o tipo da flor (ex: Rosa, Tulipa): ");
        String tipo = scanner.nextLine();

        // Criar uma instância da flor com os dados fornecidos
        Flor flor = new Flor(cor, tamanho, tipo);

        // Mostrar os dados da flor e chamar métodos
        System.out.println("\nDetalhes da Flor:");
        System.out.println(flor);

        flor.florir();
        flor.podar();
        flor.regar();

        // Fechar o scanner
        scanner.close();
    }
}
