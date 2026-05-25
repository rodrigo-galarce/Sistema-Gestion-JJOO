package servicio;

import modelo.SistemaJJOO;
import modelo.pais.Pais;
import modelo.persona.Atleta;
import modelo.resultado.Record;

public class ServicioConsulta {
    private SistemaJJOO sistema;

    public ServicioConsulta(SistemaJJOO sistema) {
        this.sistema = sistema;
    }

    public void consultarMedalleroPorPais() {
        for (Pais pais : sistema.getListaPaises().values()) {
            int cantidad = 0;
            for (Atleta atleta : pais.getDelegacion().getListaAtletas().values()) {
                cantidad += atleta.getListaMedallas().size();
            }
            System.out.println(pais.getNombre() + " -> " + cantidad + " medallas");
        }
    }

    public void consultarRecords() {
        sistema.getListaDeportes().values().forEach(deporte -> {
            deporte.getListaDisciplinas().forEach(disciplina -> {
                Record record = disciplina.getRecordActual();
                if (record != null) {
                    System.out.println(disciplina.getNombre() + " -> " + record.getMarca());
                    }
                });
            });
        }
}