package modelo;

import modelo.ceremonia.Ceremonia;
import modelo.deporte.Deporte;
import modelo.pais.Pais;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SistemaJJOO implements Serializable {
    private int anio;
    private String sedeCiudad;
    private HashMap<String, Pais> listaPaises;
    private HashMap<String, Deporte> listaDeportes;
    private ArrayList<Ceremonia> listaCeremonias;

    public SistemaJJOO(int anio, String sedeCiudad) {
        this.anio = anio;
        this.sedeCiudad = sedeCiudad;
        listaPaises = new HashMap<>();
        listaDeportes = new HashMap<>();
        listaCeremonias = new ArrayList<>();
    }

    public HashMap<String, Pais> getListaPaises() {
        return listaPaises;
    }

    public HashMap<String, Deporte> getListaDeportes() {
        return listaDeportes;
    }

    public ArrayList<Ceremonia> getListaCeremonias() {
        return listaCeremonias;
    }

    public void agregrarPais(Pais pais) {
        listaPaises.put(pais.getCodigoISO(), pais);
    }

    public void agregarDeporte(Deporte deporte) {
        listaDeportes.put(deporte.getNombre(), deporte);
    }

    public void agregarCeremonia(Ceremonia ceremonia) {
        listaCeremonias.add(ceremonia);
    }
}
