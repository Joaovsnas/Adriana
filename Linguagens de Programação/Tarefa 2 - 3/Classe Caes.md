import java.util.Scanner;

public class Cao {
    // Atributos
    private String raca;   // Raça do cachorro (ex: "Labrador", "Beagle")
    private String tamanho; // Tamanho do cachorro (ex: "Pequeno", "Médio", "Grande")
    private String cor;    // Cor do pelo do cachorro (ex: "Marrom", "Preto")

    // Construtor
    public Cao(String raca, String tamanho, String cor) {
        this.raca = raca;
        this.tamanho = tamanho;
        this.cor = cor;
    }

    // Métodos
    public void latir() {
        System.out.println("O cachorro está latindo: Au au!");
    }

    public void uivar() {
        System.out.println("O cachorro está uivando: Auuuu!");
    }

    public void deitar() {
        System.out.println("O cachorro está deitando.");
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Cao{" +
                "raca='" + raca + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", cor='" + cor + '\'' +
                '}';
    }

    // Método main para interagir com o usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Coletar dados do usuário
        System.out.print("Digite a raça do cachorro (ex: Labrador, Beagle): ");
        String raca = scanner.nextLine();

        System.out.print("Digite o tamanho do cachorro (ex: Pequeno, Médio, Grande): ");
        String tamanho = scanner.nextLine();

        System.out.print("Digite a cor do pelo do cachorro (ex: Marrom, Preto): ");
        String cor = scanner.nextLine();

        // Criar uma instância do cachorro com os dados fornecidos
        Cao cao = new Cao(raca, tamanho, cor);

        // Mostrar os dados do cachorro e chamar métodos
        System.out.println("\nDetalhes do Cachorro:");
        System.out.println(cao);

        cao.latir();
        cao.uivar();
        cao.deitar();

        // Fechar o scanner
        scanner.close();
    }
}
