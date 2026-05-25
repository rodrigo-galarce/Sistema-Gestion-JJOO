package modelo.pais;

import java.io.Serializable;

public class Pais implements Serializable {
    private String nombre;
    private String codigoISO;
    private Delegacion delegacion;

    public Pais(String nombre, String codigoISO) {
        this.nombre = nombre;
        this.codigoISO = codigoISO;
        delegacion = new Delegacion();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigoISO() {
        return codigoISO;
    }

    public Delegacion getDelegacion() {
        return delegacion;
    }
}