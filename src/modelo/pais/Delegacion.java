package modelo.pais;

import modelo.persona.Atleta;
import modelo.persona.Entrenador;

import java.util.HashMap;
import java.util.Map;

public class Delegacion {
    private Map<Long, Atleta> listaAtletas;
    private Map<Long, Entrenador> listaEntrenadores;

    public Delegacion() {
        listaAtletas = new HashMap<Long, Atleta>();
        listaEntrenadores = new HashMap<Long, Entrenador>();
    }

    public void incorporarAtleta(Atleta atleta) {
        listaAtletas.put(atleta.getDni(), atleta);
    }

    public void incorporarEntrenador(Entrenador entrenador) {
        listaEntrenadores.put(entrenador.getDni(), entrenador);
    }
}
