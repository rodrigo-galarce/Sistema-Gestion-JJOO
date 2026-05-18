package modelo.ceremonia;

import java.time.LocalDate;

public class Ceremonia {
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
}
