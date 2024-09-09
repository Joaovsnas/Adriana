import java.util.Scanner;

public class Pessoa {
    // Atributos
    private String cabelo;  // Tipo de cabelo (ex: "Louro", "Castanho")
    private String roupa;   // Tipo de roupa (ex: "Camisa", "Vestido")
    private String genero;  // Gênero da pessoa (ex: "Masculino", "Feminino")

    // Construtor
    public Pessoa(String cabelo, String roupa, String genero) {
        this.cabelo = cabelo;
        this.roupa = roupa;
        this.genero = genero;
    }

    // Métodos
    public void correr() {
        System.out.println("A pessoa está correndo.");
    }

    public void andar() {
        System.out.println("A pessoa está andando.");
    }

    public void pular() {
        System.out.println("A pessoa está pulando.");
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Pessoa{" +
                "cabelo='" + cabelo + '\'' +
                ", roupa='" + roupa + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }

    // Método main para interagir com o usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Coletar dados do usuário
        System.out.print("Digite o tipo de cabelo (ex: Louro, Castanho): ");
        String cabelo = scanner.nextLine();

        System.out.print("Digite o tipo de roupa (ex: Camisa, Vestido): ");
        String roupa = scanner.nextLine();

        System.out.print("Digite o gênero (ex: Masculino, Feminino): ");
        String genero = scanner.nextLine();

        // Criar uma instância da pessoa com os dados fornecidos
        Pessoa pessoa = new Pessoa(cabelo, roupa, genero);

        // Mostrar os dados da pessoa e chamar métodos
        System.out.println("\nDetalhes da Pessoa:");
        System.out.println(pessoa);

        pessoa.correr();
        pessoa.andar();
        pessoa.pular();

        // Fechar o scanner
        scanner.close();
    }
}
