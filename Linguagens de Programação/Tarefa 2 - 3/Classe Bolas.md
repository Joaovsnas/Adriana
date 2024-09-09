import java.util.Scanner;

public class Bola {
    // Atributos
    private String tamanho; // Tamanho da bola (ex: "Pequena", "Média", "Grande")
    private String cor;    // Cor da bola (ex: "Vermelha", "Azul")
    private String marca;  // Marca da bola (ex: "Nike", "Adidas")

    // Construtor
    public Bola(String tamanho, String cor, String marca) {
        this.tamanho = tamanho;
        this.cor = cor;
        this.marca = marca;
    }

    // Métodos
    public void quicar() {
        System.out.println("A bola está quicando.");
    }

    public void chutar() {
        System.out.println("A bola foi chutada.");
    }

    public void jogar() {
        System.out.println("A bola está sendo jogada.");
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Bola{" +
                "tamanho='" + tamanho + '\'' +
                ", cor='" + cor + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }

    // Método main para interagir com o usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Coletar dados do usuário
        System.out.print("Digite o tamanho da bola (ex: Pequena, Média, Grande): ");
        String tamanho = scanner.nextLine();

        System.out.print("Digite a cor da bola (ex: Vermelha, Azul): ");
        String cor = scanner.nextLine();

        System.out.print("Digite a marca da bola (ex: Nike, Adidas): ");
        String marca = scanner.nextLine();

        // Criar uma instância da bola com os dados fornecidos
        Bola bola = new Bola(tamanho, cor, marca);

        // Mostrar os dados da bola e chamar métodos
        System.out.println("\nDetalhes da Bola:");
        System.out.println(bola);

        bola.quicar();
        bola.chutar();
        bola.jogar();

        // Fechar o scanner
        scanner.close();
    }
}
