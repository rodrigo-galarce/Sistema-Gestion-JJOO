package modelo.ceremonia;

import java.io.Serializable;
import java.time.LocalDate;

public class Ceremonia implements Serializable {
    private String nombre;
    private LocalDate fecha;
    private TipoCeremonia tipo;
    private String ubicacion;

    public Ceremonia(String nombre, LocalDate fecha, TipoCeremonia tipo, String ubicacion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoCeremonia getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }
}
