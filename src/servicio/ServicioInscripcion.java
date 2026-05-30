package servicio;

import excepciones.InscripcionDuplicadaException;
import modelo.SistemaJJOO;
import modelo.ceremonia.Ceremonia;
import modelo.ceremonia.ParticipacionCeremonia;
import modelo.ceremonia.RolCeremonia;
import modelo.deporte.Competencia;
import modelo.inscripcion.Inscripcion;
import modelo.persona.Atleta;
import modelo.persona.Persona;

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

    public void inscribirPersonalACeremonia(Persona persona, Ceremonia ceremonia, RolCeremonia rol) {
        ParticipacionCeremonia participacion = new ParticipacionCeremonia(persona, ceremonia, rol);
        sistema.agregarParticipacionCeremonia(participacion);
        System.out.println("Participación registrada correctamente.");
    }

    public Ceremonia buscarCeremonia(String nombre) {
        for (Ceremonia ceremonia : sistema.getListaCeremonias()) {
            if (ceremonia.getNombre().equalsIgnoreCase(nombre)) {
                return ceremonia;
            }
        }
        return null;
    }
}