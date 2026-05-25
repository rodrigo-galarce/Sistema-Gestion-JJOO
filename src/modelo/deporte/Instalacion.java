package modelo.deporte;

import java.io.Serializable;

public class Instalacion implements Serializable {
    private String nombre;
    private String ubicacion;
    private int capacidad;

    public Instalacion(String nombre, String ubicacion, int capacidad) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }
}
