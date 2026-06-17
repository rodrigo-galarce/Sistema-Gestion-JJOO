package modelo.inscripcion;

import modelo.deporte.Competencia;
import modelo.persona.Atleta;

public class Inscripcion {
    private Atleta atleta;
    private Competencia competencia;

    public Inscripcion(Atleta atleta, Competencia competencia) {
        this.atleta = atleta;
        this.competencia = competencia;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public Competencia getCompetencia() {
        return competencia;
    }
}