package main;

import excepciones.PersistenciaException;
import modelo.SistemaJJOO;
import persistencia.PersistenciaSistema;
import ui.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        SistemaJJOO sistema1 = new SistemaJJOO(2028,"Buenos Aires");
        PersistenciaSistema persistencia = new PersistenciaSistema();
        try {persistencia.cargarSistema(sistema1);
            } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
                }
        MenuPrincipal menu = new MenuPrincipal(sistema1);
        menu.iniciarMenuPrincipal();
        try {persistencia.guardarSistema(sistema1);
            } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            }
    }
}