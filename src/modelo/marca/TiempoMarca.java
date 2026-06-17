package modelo.marca;

public class TiempoMarca extends Marca {
    public TiempoMarca(double valor) {
        super(valor);
    }

    @Override
    public int compareTo(Marca otraMarca) {
        // Menor tiempo = mejor marca
        return Double.compare(
                otraMarca.getValor(),
                this.valor
        );
    }

    @Override
    public String toString() {
        return valor + " segundos";
    }
}
