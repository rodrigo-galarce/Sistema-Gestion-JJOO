package modelo.deporte;

import java.util.ArrayList;

public class Deporte {
    private String nombre;
    private ArrayList<Disciplina> listaDisciplinas;

    public Deporte(String nombre) {
        this.nombre = nombre;
        listaDisciplinas = new ArrayList<>();
    }

    public void agregarDisciplina(Disciplina disciplina) {
        listaDisciplinas.add(disciplina);
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
