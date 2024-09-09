import java.util.Scanner;

public class Passaro {
    // Definir os atributos:
    private String especie;
    private String cor;
    private String genero;

    // Definir o Construtor:
    public Passaro(String especie, String cor, String genero) {
        this.especie = especie;
        this.cor = cor;
        this.genero = genero;
    }

    // Método cantar:
    public void cantar() {
        System.out.println("O pássaro está cantando.");
    }
    
    // Método voar:
    public void voar() {
        System.out.println("O pássaro está voando.");
    }
    // Método comer:
    public void comer() {
        System.out.println("O pássaro está comendo.");
    }

    // Usar o toString:
    @Override
    public String toString() {
        return "Passaro{" +
                "especie='" + especie + '\'' +
                ", cor='" + cor + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }

    // Main:
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leitura e impressão dos atributos:
        System.out.print("Digite a espécie do pássaro: ");
        String especie = scanner.nextLine();
        System.out.print("Digite a cor do pássaro: ");
        String cor = scanner.nextLine();
        System.out.print("Digite o gênero do pássaro: ");
        String genero = scanner.nextLine();

        // Criar um novo pássaro com os dados fornecidos:
        Passaro passaro = new Passaro(especie, cor, genero);

        // Exibir informações e chamar métodos:
        System.out.println(passaro);
        passaro.cantar();
        passaro.voar();
        passaro.comer();

        scanner.close();
    }
}
