package modelo.persona;

public class Atleta extends Persona {
    private String especialidad;

    public Atleta(long dni, String nombre, String apellido, int edad, String nacionalidad, String especialidad){
        super(dni, nombre, apellido, edad, nacionalidad);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

}
