package modelo.marca;

import java.io.Serializable;

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

    @Override
    public String toString() {
        return valor + " metros";
    }
}
