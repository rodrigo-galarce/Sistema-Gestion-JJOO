package servicio;

import modelo.SistemaJJOO;
import modelo.pais.Delegacion;
import modelo.pais.Pais;
import modelo.persona.*;

public class ServicioDelegacion {
    private SistemaJJOO sistema;

    public ServicioDelegacion(SistemaJJOO sistema) {
        this.sistema = sistema;
    }

    public void registrarAtleta(String codigoPais,long dni,String nombre, String apellido, int edad, String especialidad) {
        Pais pais = sistema.getListaPaises().get(codigoPais);
        Delegacion delegacion = pais.getDelegacion();
        String nacionalidad = pais.getNombre();

        Atleta atleta = new Atleta(dni, nombre, apellido, edad, nacionalidad, especialidad);
        delegacion.agregrarAtleta(atleta);
    }

    public void registrarEntrenador(String codigoPais,long dni,String nombre, String apellido, int edad, String especialidad) {
        Pais pais = sistema.getListaPaises().get(codigoPais);
        Delegacion delegacion = pais.getDelegacion();
        String nacionalidad = pais.getNombre();

        Entrenador entrenador = new Entrenador(dni, nombre, apellido, edad, nacionalidad, especialidad);
        delegacion.agregrarEntrenador(entrenador);
    }

    public Atleta buscarAtleta(Long dni) {
        for (Pais pais : sistema.getListaPaises().values()) {
            Atleta atleta = pais.getDelegacion().getListaAtletas().get(dni);
            if (atleta != null) {
                return atleta;
            }
        }
        return null;
    }

    public Persona buscarPersona(Long dni) {
        for (Pais pais : sistema.getListaPaises().values()) {
            Atleta atleta = pais.getDelegacion().getListaAtletas().get(dni);
            if (atleta != null) {
                return atleta;
            }
            Entrenador entrenador = pais.getDelegacion().getListaEntrenadores().get(dni);
            if (entrenador != null) {
                return entrenador;
            }
        }
        return null;
    }

}
