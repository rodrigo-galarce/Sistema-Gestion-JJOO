package persistencia;

import excepciones.PersistenciaException;

import java.io.*;
import java.util.ArrayList;

public abstract class Persistencia {

    protected void guardarArchivo(String ruta, ArrayList<String> lineas) throws PersistenciaException {
        try { PrintWriter pw = new PrintWriter(new FileWriter(ruta));
            for (String linea : lineas) {
                pw.println(linea);
            }
            pw.close();
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar archivo: " + ruta);
        }
    }

    protected ArrayList<String> cargarArchivo(String ruta) throws PersistenciaException {
        ArrayList<String> lineas = new ArrayList<>();
        try { BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
            br.close();
        } catch (FileNotFoundException e) {
            return lineas;
        } catch (IOException e) {
            throw new PersistenciaException("Error al cargar archivo: " + ruta);
        }
        return lineas;
    }


}