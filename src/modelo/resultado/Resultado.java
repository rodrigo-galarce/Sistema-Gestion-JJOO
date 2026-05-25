package modelo.resultado;

import modelo.marca.Marca;
import modelo.persona.Atleta;

import java.io.Serializable;

public class Resultado implements Comparable<Resultado>, Serializable {
    private int posicion;
    private Atleta atleta;
    private Marca marca;

    public Resultado(int posicion, Atleta atleta, Marca marca) {
        this.posicion = posicion;
        this.atleta = atleta;
        this.marca = marca;
    }
    public Atleta getAtleta() {
        return atleta;
    }

    public Marca getMarca() {
        return marca;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public int compareTo(Resultado otro) {
        return this.marca.compareTo(otro.getMarca());
    }

}
