package modelo.resultado;

import modelo.marca.Marca;
import modelo.persona.Atleta;

import java.time.LocalDate;

public class Record {
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
