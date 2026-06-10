package persistencia;

import excepciones.PersistenciaException;
import modelo.SistemaJJOO;
import modelo.deporte.*;
import modelo.marca.*;
import modelo.pais.Pais;
import modelo.persona.Atleta;
import modelo.resultado.Resultado;
import servicio.ServicioResultado;

import java.util.ArrayList;

public class PersistenciaResultados extends Persistencia {
    public void guardar(SistemaJJOO sistema) throws PersistenciaException {
        guardarResultados(sistema);
    }

    public void cargar(SistemaJJOO sistema) throws PersistenciaException {
        cargarResultados(sistema);
    }

    private void guardarResultados(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Deporte deporte : sistema.getListaDeportes().values()) {
            for (Disciplina disciplina : deporte.getListaDisciplinas()) {
                for (Competencia competencia : disciplina.getListaCompetencias()) {
                    for (Resultado resultado : competencia.getListaResultados()) {
                        String tipoMarca;
                        if (resultado.getMarca() instanceof TiempoMarca) {
                            tipoMarca = "TIEMPO";
                        }
                        else if (resultado.getMarca() instanceof DistanciaMarca) {
                            tipoMarca = "DISTANCIA";
                        }
                        else {
                            tipoMarca = "PUNTAJE";
                        }
                        lineas.add(deporte.getNombre()+ ";" + disciplina.getNombre() + ";"
                                        + competencia.getNombre() + ";" + resultado.getAtleta().getDni()
                                        + ";" + tipoMarca + ";" + resultado.getMarca().getValor());
                    }
                }
            }
        }
        guardarArchivo("datos/resultados.txt", lineas);
    }

    private void cargarResultados(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = cargarArchivo("datos/resultados.txt");
        ServicioResultado servicioResultado = new ServicioResultado(sistema);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            String nombreDeporte = datos[0];
            String nombreDisciplina = datos[1];
            String nombreCompetencia = datos[2];
            long dni = Long.parseLong(datos[3]);
            String tipoMarca = datos[4];
            double valor = Double.parseDouble(datos[5]);
            Atleta atleta = null;
            for (Pais pais : sistema.getListaPaises().values()) {
                atleta = pais.getDelegacion().getListaAtletas().get(dni);
                if (atleta != null) {
                    break;
                }
            }
            Competencia competencia = null;
            Deporte deporte = sistema.getListaDeportes().get(nombreDeporte);
            if (deporte != null) {
                for (Disciplina disciplina : deporte.getListaDisciplinas()) {
                    if (disciplina.getNombre().equalsIgnoreCase(nombreDisciplina)) {
                        for (Competencia c : disciplina.getListaCompetencias()) {
                            if (c.getNombre().equalsIgnoreCase(nombreCompetencia)) {
                                competencia = c;
                                break;
                            }
                        }
                    }
                }
            }
            Marca marca = null;
            if (tipoMarca.equals("TIEMPO")) {
                marca = new TiempoMarca(valor);
            }
            else if (tipoMarca.equals("DISTANCIA")) {
                marca = new DistanciaMarca(valor);
            }
            else if (tipoMarca.equals("PUNTAJE")) {
                marca = new PuntajeMarca(valor);
            }
            if (competencia != null && atleta != null && marca != null) {
                servicioResultado.registrarResultado(competencia, atleta, marca);
            }
        }
    }
}
