package modelo.persona;

public class Persona {
    protected Long dni;
    protected String nombre;
    protected String apellido;
    protected int edad;
    protected String nacionalidad;

    public Persona(Long dni, String nombre, String apellido, int edad, String nacionalidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }
    public Long getDni() {
        return dni;
    }
}
