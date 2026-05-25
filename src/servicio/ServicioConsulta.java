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

    public void consultarAtletas() {
        for (Pais pais : sistema.getListaPaises().values()) {
            System.out.println("\nPaís: " + pais.getNombre());
            for (Atleta atleta : pais.getDelegacion().getListaAtletas().values()) {
                System.out.println(atleta.getNombre() + " - DNI: " + atleta.getDni());
                }
            }
    }

    public void consultarEntrenadores() {
        for (Pais pais : sistema.getListaPaises().values()) {
            System.out.println("\nPaís: " + pais.getNombre());
            pais.getDelegacion().getListaEntrenadores().values().forEach(entrenador -> {
                System.out.println(entrenador.getNombre() + " - DNI: " + entrenador.getDni());
                });
        }
    }

    public void consultarDisciplinas() {
        sistema.getListaDeportes().values().forEach(deporte -> {
            System.out.println("\nDeporte: " + deporte.getNombre());
            deporte.getListaDisciplinas().forEach(disciplina -> {
                System.out.println(disciplina.getNombre());
                });
        });
    }

    public void consultarCompetencias() {
        sistema.getListaDeportes().values().forEach(deporte -> {
            deporte.getListaDisciplinas().forEach(disciplina -> {
                System.out.println("\nDisciplina: " + disciplina.getNombre());
                disciplina.getListaCompetencias().forEach(competencia -> {
                    System.out.println(competencia.getNombre());
                    });
                });
            });
    }

    public void consultarDelegaciones() {
        sistema.getListaPaises().values().forEach(pais -> {
            System.out.println(pais.getNombre());
            });
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