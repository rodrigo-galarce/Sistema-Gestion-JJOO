package persistencia;

import excepciones.PersistenciaException;
import modelo.SistemaJJOO;
import modelo.ceremonia.Ceremonia;
import modelo.ceremonia.ParticipacionCeremonia;
import modelo.ceremonia.RolCeremonia;
import modelo.deporte.Competencia;
import modelo.deporte.Deporte;
import modelo.deporte.Disciplina;
import modelo.inscripcion.Inscripcion;
import modelo.pais.Pais;
import modelo.persona.Atleta;
import modelo.persona.Persona;

import java.util.ArrayList;

public class PersistenciaInscripciones extends Persistencia {
    public void guardar(SistemaJJOO sistema) throws PersistenciaException {
        guardarInscripciones(sistema);
        guardarParticipacionesCeremonia(sistema);
    }

    public void cargar(SistemaJJOO sistema) throws PersistenciaException {
        cargarInscripciones(sistema);
        cargarParticipacionesCeremonia(sistema);
    }

    private void guardarInscripciones(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Deporte deporte : sistema.getListaDeportes().values()) {
            for (Disciplina disciplina : deporte.getListaDisciplinas()) {
                for (Competencia competencia : disciplina.getListaCompetencias()) {
                    for (Inscripcion inscripcion : competencia.getListaInscripciones()) {
                        lineas.add(inscripcion.getAtleta().getDni() + ";" + deporte.getNombre()
                                        + ";" + disciplina.getNombre() + ";" + competencia.getNombre());
                    }
                }
            }
        }
        guardarArchivo("datos/inscripciones.txt", lineas);
    }

    private void cargarInscripciones(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = cargarArchivo("datos/inscripciones.txt");
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            long dni = Long.parseLong(datos[0]);
            String nombreDeporte = datos[1];
            String nombreDisciplina = datos[2];
            String nombreCompetencia = datos[3];
            Atleta atleta = null;
            for (Pais pais : sistema.getListaPaises().values()) {
                atleta = pais.getDelegacion().getListaAtletas().get(dni);
                if (atleta != null) {
                    break;
                }
            }
            Competencia competencia = null;
            Deporte deporte = sistema.getListaDeportes().get(nombreDeporte);
            if (deporte != null) {
                for (Disciplina disciplina : deporte.getListaDisciplinas()) {
                    if (disciplina.getNombre().equalsIgnoreCase(nombreDisciplina)) {
                        for (Competencia c : disciplina.getListaCompetencias()) {
                            if (c.getNombre().equalsIgnoreCase(nombreCompetencia)) {
                                competencia = c;
                                break;
                            }
                        }
                    }
                }
            }
            if (atleta != null && competencia != null) {
                competencia.inscribirAtleta(atleta);
            }
        }
    }

    private void guardarParticipacionesCeremonia(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = new ArrayList<>();
        for (ParticipacionCeremonia participacion : sistema.getListaParticipacionesCeremonia()) {
            lineas.add(participacion.getPersona().getDni() + ";" + participacion.getCeremonia().getNombre()
                            + ";" + participacion.getRol());
        }
        guardarArchivo("datos/participacionesCeremonia.txt", lineas);
    }

    private void cargarParticipacionesCeremonia(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = cargarArchivo("datos/participacionesCeremonia.txt");
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            long dni = Long.parseLong(datos[0]);
            String nombreCeremonia = datos[1];
            RolCeremonia rol = RolCeremonia.valueOf(datos[2]);
            Persona persona = null;
            for (Pais pais : sistema.getListaPaises().values()) {
                persona = pais.getDelegacion().getListaAtletas().get(dni);
                if (persona != null) {
                    break;
                }
                persona = pais.getDelegacion().getListaEntrenadores().get(dni);
                if (persona != null) {
                    break;
                }
            }
            Ceremonia ceremonia = null;
            for (Ceremonia c : sistema.getListaCeremonias()) {
                if (c.getNombre().equalsIgnoreCase(nombreCeremonia)) {
                    ceremonia = c;
                    break;
                }
            }
            if (persona != null && ceremonia != null) {
                ParticipacionCeremonia participacion = new ParticipacionCeremonia(persona, ceremonia, rol);
                sistema.agregarParticipacionCeremonia(participacion);
            }
        }
    }
}
