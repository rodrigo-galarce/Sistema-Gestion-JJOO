package modelo.deporte;

import modelo.inscripcion.Inscripcion;
import modelo.persona.Atleta;
import modelo.resultado.Resultado;

import java.time.LocalDate;
import java.util.ArrayList;

public class Competencia {
    private String nombre;
    private LocalDate fecha;
    private EstadoCompetencia estado;
    private Instalacion instalacion;
    private Disciplina disciplina;

    private ArrayList<Inscripcion> listaInscripciones;
    private ArrayList<Resultado> listaResultados;

    public Competencia(String nombre,
                       LocalDate fecha,
                       Instalacion instalacion,
                       Disciplina disciplina) {

        this.nombre = nombre;
        this.fecha = fecha;
        this.instalacion = instalacion;
        this.disciplina = disciplina;

        this.estado = EstadoCompetencia.PENDIENTE;

        this.listaInscripciones = new ArrayList<>();
        this.listaResultados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Instalacion getInstalacionCompleta() {
        return instalacion;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void inscribirAtleta(Atleta atleta) {
        Inscripcion inscripcion = new Inscripcion(atleta, this);
        listaInscripciones.add(inscripcion);
    }

    public void agregarResultado(Resultado resultado) {
        listaResultados.add(resultado);
    }

    public ArrayList<Resultado> getListaResultados() {
        return listaResultados;
    }

    public ArrayList<Inscripcion> getListaInscripciones() {
        return listaInscripciones;
    }

}
