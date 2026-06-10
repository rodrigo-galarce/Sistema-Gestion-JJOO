package persistencia;

import excepciones.PersistenciaException;
import modelo.SistemaJJOO;

public class PersistenciaSistema {
    private PersistenciaOrganizativa persistenciaOrganizativa;
    private PersistenciaDeportiva persistenciaDeportiva;
    private PersistenciaInscripciones persistenciaInscripciones;
    private PersistenciaResultados persistenciaResultados;

    public PersistenciaSistema() {
        persistenciaOrganizativa = new PersistenciaOrganizativa();
        persistenciaDeportiva = new PersistenciaDeportiva();
        persistenciaInscripciones = new PersistenciaInscripciones();
        persistenciaResultados = new PersistenciaResultados();
    }

    public void guardarSistema(SistemaJJOO sistema) throws PersistenciaException {
        System.out.println("Guardando el sistema.");
        persistenciaOrganizativa.guardar(sistema);
        persistenciaDeportiva.guardar(sistema);
        persistenciaInscripciones.guardar(sistema);
        persistenciaResultados.guardar(sistema);
    }

    public SistemaJJOO cargarSistema(SistemaJJOO sistema) throws PersistenciaException {
        persistenciaOrganizativa.cargar(sistema);
        persistenciaDeportiva.cargar(sistema);
        persistenciaInscripciones.cargar(sistema);
        persistenciaResultados.cargar(sistema);
        return sistema;
    }
}