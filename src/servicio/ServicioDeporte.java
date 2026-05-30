package servicio;

import excepciones.CompetenciaFueraDeFechaException;
import modelo.SistemaJJOO;
import modelo.ceremonia.Ceremonia;
import modelo.ceremonia.TipoCeremonia;
import modelo.deporte.Competencia;
import modelo.deporte.Deporte;
import modelo.deporte.Disciplina;
import modelo.deporte.Instalacion;

import java.time.LocalDate;

public class ServicioDeporte {

    private SistemaJJOO sistema;

    public ServicioDeporte(SistemaJJOO sistema) {
        this.sistema = sistema;
    }

    public void registrarDeporte(String nombreDeporte) {
        if (sistema.getListaDeportes().containsKey(nombreDeporte)) {
            System.out.println("El deporte ya existe.");
            return;
        }
        Deporte deporte = new Deporte(nombreDeporte);
        sistema.agregarDeporte(deporte);
    }

    public void registrarDisciplina(String nombreDeporte, String nombreDisciplina) {
        Deporte deporte = sistema.getListaDeportes().get(nombreDeporte);
        if (deporte != null) {
            Disciplina disciplina = new Disciplina(nombreDisciplina);
            deporte.agregarDisciplina(disciplina);
        }
    }

    public void crearCompetencia(String nombreDeporte, String nombreDisciplina, String nombreCompetencia, LocalDate fecha, String nombreInstalacion, String ubicacion, int capacidad) throws CompetenciaFueraDeFechaException {
        // Validación contra ceremonia de clausura
        for (Ceremonia ceremonia : sistema.getListaCeremonias()) {
            if (ceremonia.getTipo() == TipoCeremonia.CLAUSURA
                    && fecha.isAfter(ceremonia.getFecha())) {
                throw new CompetenciaFueraDeFechaException("No se puede registrar una competencia posterior a la ceremonia de clausura.");
            }
        }
        Deporte deporte = sistema.getListaDeportes().get(nombreDeporte);
        if (deporte != null) {
            Disciplina disciplina = null;
            for (Disciplina d : deporte.getListaDisciplinas()) {
                if (d.getNombre().equalsIgnoreCase(nombreDisciplina)) {
                    disciplina = d;
                    break;
                }
            }
            if (disciplina != null) {
                Instalacion instalacion = new Instalacion(nombreInstalacion, ubicacion, capacidad);
                Competencia competencia = new Competencia(nombreCompetencia, fecha, instalacion, disciplina);
                disciplina.agregarCompetencia(competencia);
            }
        }
    }

    public Competencia buscarCompetencia(String nombreDeporte, String nombreDisciplina, String nombreCompetencia) {
        Deporte deporte = sistema.getListaDeportes().get(nombreDeporte);
        if (deporte != null) {
            for (Disciplina disciplina : deporte.getListaDisciplinas()) {
                if (disciplina.getNombre().equalsIgnoreCase(nombreDisciplina)) {
                    for (Competencia competencia : disciplina.getListaCompetencias()) {
                        if (competencia.getNombre().equalsIgnoreCase(nombreCompetencia)) {
                            return competencia;
                        }
                    }
                }
            }
        }
        return null;
    }
}