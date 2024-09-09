import java.util.Scanner;

public class Fruta {
    // Atributos
    private String tipo;   // Tipo da fruta (ex: "Maçã", "Banana")
    private String tamanho; // Tamanho da fruta (ex: "Pequena", "Média", "Grande")
    private String cor;    // Cor da fruta (ex: "Vermelha", "Amarela")

    // Construtor
    public Fruta(String tipo, String tamanho, String cor) {
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.cor = cor;
    }

    // Métodos
    public void descascar() {
        System.out.println("Descascar a " + tipo + ".");
    }

    public void comer() {
        System.out.println("Comer a " + tipo + ".");
    }

    public void jogarFora() {
        System.out.println("Jogar fora a " + tipo + ".");
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Fruta{" +
                "tipo='" + tipo + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", cor='" + cor + '\'' +
                '}';
    }

    // Método main para interagir com o usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Coletar dados do usuário
        System.out.print("Digite o tipo da fruta (ex: Maçã, Banana): ");
        String tipo = scanner.nextLine();

        System.out.print("Digite o tamanho da fruta (ex: Pequena, Média, Grande): ");
        String tamanho = scanner.nextLine();

        System.out.print("Digite a cor da fruta (ex: Vermelha, Amarela): ");
        String cor = scanner.nextLine();

        // Criar uma instância da fruta com os dados fornecidos
        Fruta fruta = new Fruta(tipo, tamanho, cor);

        // Mostrar os dados da fruta e chamar métodos
        System.out.println("\nDetalhes da Fruta:");
        System.out.println(fruta);

        fruta.descascar();
        fruta.comer();
        fruta.jogarFora();

        // Fechar o scanner
        scanner.close();
    }
}
