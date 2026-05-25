package modelo.resultado;

import modelo.marca.Marca;
import modelo.persona.Atleta;

import java.io.Serializable;
import java.time.LocalDate;

public class Record implements Serializable {
    private Marca mejorMarca;
    private LocalDate fecha;
    private Atleta atleta;

    public Record(Resultado resultado) {
        this.atleta = resultado.getAtleta();
        this.mejorMarca = resultado.getMarca();
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public Marca getMarca() {
        return mejorMarca;
    }
}
