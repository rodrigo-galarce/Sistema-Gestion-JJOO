package modelo.marca;

import java.io.Serializable;

public class TiempoMarca extends Marca implements Serializable {
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
}
