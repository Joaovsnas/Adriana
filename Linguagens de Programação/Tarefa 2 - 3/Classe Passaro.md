public class Passaro {
    // Definição dos atributos
    private int penas;
    private int asas;
    private String bico;

    // Definição do Construtor
    public Passaro(int penas, int asas, String bico) {
        this.penas = penas;
        this.asas = asas;
        this.bico = bico;
    }

    // Métodos
    public void cantar() {
        System.out.println("O pássaro está cantando.");
    }

    public void voar() {
        System.out.println("O pássaro está voando.");
    }

    public void comer() {
        System.out.println("O pássaro está comendo.");
    }

    // Usar toString 
    @Override
    public String toString() {
        return "Passaro{" +
                "penas=" + penas +
                ", asas=" + asas +
                ", bico='" + bico + '\'' +
                '}';
    }

    // Método main
    public static void main(String[] args) {
        Passaro passaro = new Passaro(100, 2, "comprido");
        System.out.println(passaro);
        passaro.cantar();
        passaro.voar();
        passaro.comer();
    }
}
