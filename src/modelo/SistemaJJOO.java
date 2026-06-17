package modelo;

import modelo.ceremonia.Ceremonia;
import modelo.ceremonia.ParticipacionCeremonia;
import modelo.deporte.Deporte;
import modelo.pais.Pais;

import java.util.ArrayList;
import java.util.HashMap;

public class SistemaJJOO {
    private int anio;
    private String sedeCiudad;
    private HashMap<String, Pais> listaPaises;
    private HashMap<String, Deporte> listaDeportes;
    private ArrayList<Ceremonia> listaCeremonias;
    private ArrayList<ParticipacionCeremonia> listaParticipacionesCeremonia;

    public SistemaJJOO(int anio, String sedeCiudad) {
        this.anio = anio;
        this.sedeCiudad = sedeCiudad;
        listaPaises = new HashMap<>();
        listaDeportes = new HashMap<>();
        listaCeremonias = new ArrayList<>();
        listaParticipacionesCeremonia = new ArrayList<>();
    }

    public int getAnio() {
        return anio;
    }

    public String getSedeCiudad() {
        return sedeCiudad;
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

    public ArrayList<ParticipacionCeremonia> getListaParticipacionesCeremonia() {
        return listaParticipacionesCeremonia;
    }

    public void agregarPais(Pais pais) {
        listaPaises.put(pais.getCodigoISO(), pais);
    }

    public void agregarDeporte(Deporte deporte) {
        listaDeportes.put(deporte.getNombre(), deporte);
    }

    public void agregarCeremonia(Ceremonia ceremonia) {
        listaCeremonias.add(ceremonia);
    }

    public void agregarParticipacionCeremonia(ParticipacionCeremonia participacion) {
        listaParticipacionesCeremonia.add(participacion);
    }
}
