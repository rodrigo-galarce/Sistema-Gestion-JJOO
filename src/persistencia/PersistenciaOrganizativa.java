package persistencia;

import excepciones.PersistenciaException;
import modelo.SistemaJJOO;
import modelo.ceremonia.Ceremonia;
import modelo.ceremonia.TipoCeremonia;
import modelo.pais.Pais;
import modelo.persona.Atleta;
import modelo.persona.Entrenador;

import java.time.LocalDate;
import java.util.ArrayList;

public class PersistenciaOrganizativa extends Persistencia {
    public void guardar(SistemaJJOO sistema) throws PersistenciaException {
        guardarPaises(sistema);
        guardarCeremonias(sistema);
        guardarAtletas(sistema);
        guardarEntrenadores(sistema);
    }

    public void cargar(SistemaJJOO sistema) throws PersistenciaException {
        cargarPaises(sistema);
        cargarCeremonias(sistema);
        cargarAtletas(sistema);
        cargarEntrenadores(sistema);
    }

    private void guardarPaises(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Pais pais : sistema.getListaPaises().values()) {
            lineas.add(pais.getNombre() + ";" + pais.getCodigoISO());
        }
        guardarArchivo("datos/paises.txt",lineas);
    }

    private void cargarPaises(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = cargarArchivo("datos/paises.txt");
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            Pais pais = new Pais(datos[0].trim(), datos[1].trim());
            sistema.agregarPais(pais);
        }
    }

    private void guardarAtletas(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Pais pais : sistema.getListaPaises().values()) {
            for (Atleta atleta : pais.getDelegacion().getListaAtletas().values()) {
                lineas.add(atleta.getDni() + ";" + atleta.getNombre() + ";" + atleta.getApellido()
                                + ";" + atleta.getEdad() + ";" + atleta.getNacionalidad()
                                + ";" + atleta.getEspecialidad() + ";" + pais.getCodigoISO());
            }
        }
        guardarArchivo("datos/atletas.txt", lineas);
    }

    private void cargarAtletas(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = cargarArchivo("datos/atletas.txt");
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            long dni = Long.parseLong(datos[0]);
            String nombre = datos[1];
            String apellido = datos[2];
            int edad = Integer.parseInt(datos[3]);
            String nacionalidad = datos[4];
            String especialidad = datos[5];
            String codigoPais = datos[6];
            Atleta atleta = new Atleta(dni, nombre, apellido, edad, nacionalidad, especialidad);
            Pais pais = sistema.getListaPaises().get(codigoPais);
            if (pais != null) {
                pais.getDelegacion().agregrarAtleta(atleta);
            }
        }
    }

    private void guardarEntrenadores(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Pais pais : sistema.getListaPaises().values()) {
            for (Entrenador entrenador : pais.getDelegacion().getListaEntrenadores().values()) {
                lineas.add(entrenador.getDni() + ";" + entrenador.getNombre() + ";" + entrenador.getApellido()
                                + ";" + entrenador.getEdad() + ";" + entrenador.getNacionalidad()
                                + ";" + entrenador.getEspecialidad() + ";" + pais.getCodigoISO());
            }
        }
        guardarArchivo("datos/entrenadores.txt", lineas);
    }

    private void cargarEntrenadores(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = cargarArchivo("datos/entrenadores.txt");
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            long dni = Long.parseLong(datos[0]);
            String nombre = datos[1];
            String apellido = datos[2];
            int edad = Integer.parseInt(datos[3]);
            String nacionalidad = datos[4];
            String especialidad = datos[5];
            String codigoPais = datos[6];
            Entrenador entrenador = new Entrenador(dni, nombre, apellido, edad, nacionalidad, especialidad);
            Pais pais = sistema.getListaPaises().get(codigoPais);
            if (pais != null) {
                pais.getDelegacion().agregrarEntrenador(entrenador);
            }
        }
    }

    private void guardarCeremonias(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Ceremonia ceremonia : sistema.getListaCeremonias()) {
            lineas.add(ceremonia.getNombre() + ";" + ceremonia.getFecha() + ";" + ceremonia.getTipo() + ";"
                            + ceremonia.getUbicacion());
        }
        guardarArchivo("datos/ceremonias.txt", lineas);
    }

    private void cargarCeremonias(SistemaJJOO sistema) throws PersistenciaException {
        ArrayList<String> lineas = cargarArchivo("datos/ceremonias.txt");
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            Ceremonia ceremonia = new Ceremonia(datos[0], LocalDate.parse(datos[1]), TipoCeremonia.valueOf(datos[2]), datos[3]);
            sistema.agregarCeremonia(ceremonia);
        }
    }
}