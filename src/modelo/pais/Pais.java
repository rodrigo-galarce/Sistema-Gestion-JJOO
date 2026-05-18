package modelo.pais;

public class Pais {
    private String nombre;
    private String codigoISO;

    public Pais(String nombre, String codigoISO) {
        this.nombre = nombre;
        this.codigoISO = codigoISO;
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
}