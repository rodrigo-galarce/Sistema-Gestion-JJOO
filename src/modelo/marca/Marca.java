package modelo.marca;

public abstract class Marca implements Comparable<Marca> {
    protected double valor;

    public Marca(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }

    @Override
    public abstract int compareTo(Marca otraMarca);
}
