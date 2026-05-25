package modelo.persona;

import modelo.resultado.Medalla;
import java.util.ArrayList;

public class Atleta extends Persona {
    private String especialidad;
    private ArrayList<Medalla> listaMedallas;

    public Atleta(long dni, String nombre, String apellido, int edad, String nacionalidad, String especialidad){
        super(dni, nombre, apellido, edad, nacionalidad);
        this.especialidad = especialidad;
        listaMedallas = new ArrayList<>();
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void agregarMedalla(Medalla medalla) {
        listaMedallas.add(medalla);
    }

    public ArrayList<Medalla> getListaMedallas() {
        return listaMedallas;
    }

}
