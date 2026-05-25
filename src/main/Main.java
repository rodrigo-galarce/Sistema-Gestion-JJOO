package main;

import excepciones.PersistenciaException;
import modelo.SistemaJJOO;
import persistencia.PersistenciaSistema;
import ui.MenuPrincipal;

public class Main {

    public static void main(String[] args) throws PersistenciaException {
        PersistenciaSistema persistencia = new PersistenciaSistema();
        SistemaJJOO sistema1 = persistencia.cargarSistema();
        if (sistema1 == null) {
            sistema1 = new SistemaJJOO(2028, "Buenos Aires");
        }
        MenuPrincipal menu = new MenuPrincipal(sistema1);
        menu.iniciarMenuPrincipal();
        persistencia.guardarSistema(sistema1);
    }
}