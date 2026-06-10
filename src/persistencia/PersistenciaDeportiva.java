package persistencia;

import excepciones.PersistenciaException;
import modelo.SistemaJJOO;
import modelo.deporte.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class PersistenciaDeportiva extends  Persistencia {
    public void guardar(SistemaJJOO sistema) throws PersistenciaException {
        guardarDeportes(sistema);
        guardarDisciplinas(sistema);
        guardarCompetencias(sistema);
    }

    public void cargar(SistemaJJOO sistema) throws PersistenciaException {
        cargarDeportes(sistema);
        cargarDisciplinas(sistema);
        cargarCompetencias(sistema);
    }

    private void guardarDeportes(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Deporte deporte : sistema.getListaDeportes().values()) {
            lineas.add(deporte.getNombre());
        }
        guardarArchivo("datos/deportes.txt", lineas);
    }

    private void cargarDeportes(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = cargarArchivo("datos/deportes.txt");
        for (String linea : lineas) {
            Deporte deporte = new Deporte(linea);
            sistema.agregarDeporte(deporte);
        }
    }

    private void guardarDisciplinas(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Deporte deporte : sistema.getListaDeportes().values()) {
            for (Disciplina disciplina : deporte.getListaDisciplinas()) {
                lineas.add(deporte.getNombre() + ";" + disciplina.getNombre());
            }
        }
        guardarArchivo("datos/disciplinas.txt", lineas);
    }

    private void cargarDisciplinas(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = cargarArchivo("datos/disciplinas.txt");
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            String nombreDeporte = datos[0];
            String nombreDisciplina = datos[1];
            Deporte deporte = sistema.getListaDeportes().get(nombreDeporte);
            if (deporte != null) {
                Disciplina disciplina = new Disciplina(nombreDisciplina);
                deporte.agregarDisciplina(disciplina);
            }
        }
    }

    private void guardarCompetencias(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Deporte deporte : sistema.getListaDeportes().values()) {
            for (Disciplina disciplina : deporte.getListaDisciplinas()) {
                for (Competencia competencia : disciplina.getListaCompetencias()) {
                    Instalacion instalacion = competencia.getInstalacionCompleta();
                    lineas.add(deporte.getNombre() + ";" + disciplina.getNombre() + ";"
                            + competencia.getNombre() + ";" + competencia.getFecha()
                            + ";" + instalacion.getNombre() + ";" + instalacion.getUbicacion()
                            + ";" + instalacion.getCapacidad());
                }
            }
        }
        guardarArchivo("datos/competencias.txt", lineas);
    }

    private void cargarCompetencias(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = cargarArchivo("datos/competencias.txt");
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            String nombreDeporte = datos[0];
            String nombreDisciplina = datos[1];
            String nombreCompetencia = datos[2];
            LocalDate fecha = LocalDate.parse(datos[3]);
            String nombreInstalacion = datos[4];
            String ubicacion = datos[5];
            int capacidad = Integer.parseInt(datos[6]);
            Deporte deporte = sistema.getListaDeportes().get(nombreDeporte);
            if (deporte != null) {
                for (Disciplina disciplina : deporte.getListaDisciplinas()) {
                    if (disciplina.getNombre().equalsIgnoreCase(nombreDisciplina)) {
                        Instalacion instalacion = new Instalacion(nombreInstalacion, ubicacion, capacidad);
                        Competencia competencia = new Competencia(nombreCompetencia, fecha, instalacion, disciplina);
                        disciplina.agregarCompetencia(competencia);
                        break;
                    }
                }
            }
        }
    }
}
