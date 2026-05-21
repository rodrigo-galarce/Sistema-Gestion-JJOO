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

    public void registrarPais(Pais pais) {
        listaPaises.put(pais.getCodigoISO(), pais);
    }

    public void registrarDeporte(Deporte deporte) {
        listaDeportes.put(deporte.getNombre(), deporte);
    }

}
