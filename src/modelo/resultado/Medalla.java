package modelo.resultado;

import modelo.deporte.Competencia;
import modelo.persona.Atleta;

import java.io.Serializable;

public class Medalla implements Serializable {
    private TipoMedalla tipo;
    private Atleta atleta;
    private Competencia competencia;

    public Medalla(TipoMedalla tipo, Atleta atleta, Competencia competencia) {
        this.tipo = tipo;
        this.atleta = atleta;
        this.competencia = competencia;
    }
}
