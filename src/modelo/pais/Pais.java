package modelo.pais;

import modelo.persona.Atleta;

import java.util.Map;

public class Pais {
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

    public void obtenerCantMedallas(){
        return;
    }

    public Delegacion getDelegacion() {
        return delegacion;
    }
}