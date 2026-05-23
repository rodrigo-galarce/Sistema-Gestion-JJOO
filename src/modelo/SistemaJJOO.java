package modelo;

import modelo.deporte.Deporte;
import modelo.pais.Pais;

import java.util.HashMap;

public class SistemaJJOO {
    private int anio;
    private String sedeCiudad;
    private HashMap<String, Pais> listaPaises;
    private HashMap<String, Deporte> listaDeportes;

    public SistemaJJOO(int anio, String sedeCiudad) {
        this.anio = anio;
        this.sedeCiudad = sedeCiudad;
        this.listaPaises = new HashMap<>();
        this.listaDeportes = new HashMap<>();
    }

    public HashMap<String, Pais> getListaPaises() {
        return listaPaises;
    }

    public HashMap<String, Deporte> getListaDeportes() {
        return listaDeportes;
    }

    public void agregrarPais(Pais pais) {
        listaPaises.put(pais.getCodigoISO(), pais);
    }

    public void agregarDeporte(Deporte deporte) {
        listaDeportes.put(deporte.getNombre(), deporte);
    }

}
