package modelo.persona;

public class Entrenador extends Persona {
    private String especialidad;

    public Entrenador(double dni, String nombre, String apellido, int edad, String nacionalidad) {
        super(dni, nombre, apellido, edad, nacionalidad);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

}
