package modelo.pais;

import modelo.persona.Atleta;
import modelo.persona.Entrenador;

import java.util.HashMap;
import java.util.Map;

public class Delegacion {
    private Map<Long, Atleta> listaAtletas;
    private Map<Long, Entrenador> listaEntrenadores;

    public Delegacion() {
        listaAtletas = new HashMap<>();
        listaEntrenadores = new HashMap<>();
    }

    public void agregrarEntrenador(Entrenador entrenador) {
        listaEntrenadores.put(entrenador.getDni(), entrenador);
    }

    public void agregrarAtleta(Atleta atleta) {
        listaAtletas.put(atleta.getDni(), atleta);
    }

    public Map<Long, Atleta> getListaAtletas() {
        return listaAtletas;
    }

    public Map<Long, Entrenador> getListaEntrenadores() {
        return listaEntrenadores;
    }
}
