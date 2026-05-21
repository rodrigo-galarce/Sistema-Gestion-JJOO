package modelo.marca;

public class DistanciaMarca extends Marca {
    public DistanciaMarca(double valor) {
        super(valor);
    }

    @Override
    public int compareTo(Marca otraMarca) {
        // Mayor distancia = mejor marca
        return Double.compare(
                this.valor,
                otraMarca.getValor()
        );
    }
}
