package modelo.deporte;

import modelo.resultado.Resultado;
import modelo.resultado.Record;

import java.io.Serializable;
import java.util.ArrayList;

public class Disciplina {
    private String nombre;
    private Record recordActual;
    private ArrayList<Competencia> listaCompetencias;

    public Disciplina(String nombre) {
        this.nombre = nombre;
        this.listaCompetencias = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Record getRecordActual() {
        return recordActual;
    }

    public void agregarCompetencia(Competencia competencia) {
        listaCompetencias.add(competencia);
    }

    public ArrayList<Competencia> getListaCompetencias() {
        return listaCompetencias;
    }

    public void actualizarRecord(Resultado resultadoGanador) {
        if (recordActual == null) {
            recordActual = new Record(resultadoGanador);
        } else if (resultadoGanador.getMarca().compareTo(recordActual.getMarca()) > 0) {
            recordActual = new Record(resultadoGanador);
        }
    }

}
