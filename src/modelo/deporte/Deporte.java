package modelo.deporte;

import java.io.Serializable;
import java.util.ArrayList;

public class Deporte implements Serializable {
    private String nombre;
    private ArrayList<Disciplina> listaDisciplinas;

    public Deporte(String nombre) {
        this.nombre = nombre;
        listaDisciplinas = new ArrayList<>();
    }

    public void agregarDisciplina(Disciplina disciplina) {
        listaDisciplinas.add(disciplina);
    }

    public void eliminarDisciplina(Disciplina disciplina) {
        listaDisciplinas.remove(disciplina);
    }

    public ArrayList<Disciplina> getListaDisciplinas() {
        return listaDisciplinas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
