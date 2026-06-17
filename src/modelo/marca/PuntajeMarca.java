package modelo.marca;

public class PuntajeMarca extends Marca {
    public PuntajeMarca(double valor) {
        super(valor);
    }

    @Override
    public int compareTo(Marca otraMarca) {
        // Mayor puntaje = mejor marca
        return Double.compare(
                this.valor,
                otraMarca.getValor()
        );
    }

    @Override
    public String toString() {
        return valor + " puntos";
    }
}