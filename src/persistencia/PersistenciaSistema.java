package persistencia;

import excepciones.PersistenciaException;
import modelo.SistemaJJOO;

import java.io.*;

public class PersistenciaSistema {
    private static final String RUTA_ARCHIVO = "datos/sistema.dat";

    public void guardarSistema(SistemaJJOO sistema) throws PersistenciaException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO));
            oos.writeObject(sistema);
            oos.close();
            System.out.println("Sistema guardado correctamente.");
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar sistema.");
        }
    }

    public SistemaJJOO cargarSistema() throws PersistenciaException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO));
            SistemaJJOO sistema = (SistemaJJOO)ois.readObject();
            ois.close();
            System.out.println("Sistema cargado correctamente.");
            return sistema;
        } catch (FileNotFoundException e) {   // no se trata como error porque es normal que no exista el archivo
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al cargar sistema.");
        }
    }
}