package modelo.resultado;

import modelo.marca.Marca;
import modelo.persona.Atleta;

public class Resultado {
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

}
