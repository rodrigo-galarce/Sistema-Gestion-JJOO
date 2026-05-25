package servicio;

import excepciones.InscripcionDuplicadaException;
import modelo.SistemaJJOO;
import modelo.deporte.Competencia;
import modelo.inscripcion.Inscripcion;
import modelo.persona.Atleta;

public class ServicioInscripcion {
    private SistemaJJOO sistema;

    public ServicioInscripcion(SistemaJJOO sistema) {
        this.sistema = sistema;
    }

    public void inscribirAtleta(Competencia competencia, Atleta atleta) throws InscripcionDuplicadaException {
        for (Inscripcion inscripcion : competencia.getListaInscripciones()) {
            if (inscripcion.getAtleta().getDni() == atleta.getDni()) {
                throw new InscripcionDuplicadaException("El atleta ya está inscripto.");
            }
        }
        competencia.inscribirAtleta(atleta);
        System.out.println("Atleta inscripto correctamente.");
    }
}