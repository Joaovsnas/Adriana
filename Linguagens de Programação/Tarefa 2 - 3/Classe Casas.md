import java.util.Scanner;

public class Casa {
    // Atributos
    private String cor;    // Cor da casa (ex: "Branca", "Azul")
    private String local;  // Localização da casa (ex: "Centro", "Subúrbio")
    private String tamanho; // Tamanho da casa (ex: "Pequena", "Média", "Grande")

    // Construtor
    public Casa(String cor, String local, String tamanho) {
        this.cor = cor;
        this.local = local;
        this.tamanho = tamanho;
    }

    // Métodos
    public void abrir() {
        System.out.println("A casa está sendo aberta.");
    }

    public void fechar() {
        System.out.println("A casa está sendo fechada.");
    }

    public void trancar() {
        System.out.println("A casa está sendo trancada.");
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Casa{" +
                "cor='" + cor + '\'' +
                ", local='" + local + '\'' +
                ", tamanho='" + tamanho + '\'' +
                '}';
    }

    // Método main para interagir com o usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Coletar dados do usuário
        System.out.print("Digite a cor da casa (ex: Branca, Azul): ");
        String cor = scanner.nextLine();

        System.out.print("Digite a localização da casa (ex: Centro, Subúrbio): ");
        String local = scanner.nextLine();

        System.out.print("Digite o tamanho da casa (ex: Pequena, Média, Grande): ");
        String tamanho = scanner.nextLine();

        // Criar uma instância da casa com os dados fornecidos
        Casa casa = new Casa(cor, local, tamanho);

        // Mostrar os dados da casa e chamar métodos
        System.out.println("\nDetalhes da Casa:");
        System.out.println(casa);

        casa.abrir();
        casa.fechar();
        casa.trancar();

        // Fechar o scanner
        scanner.close();
    }
}
