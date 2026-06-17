package main;

import excepciones.PersistenciaException;
import modelo.SistemaJJOO;
import persistencia.PersistenciaSistema;
import ui.MenuPrincipal;
import ui.principal.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SistemaJJOO sistema1 = new SistemaJJOO(2028,"Buenos Aires");
        PersistenciaSistema persistencia = new PersistenciaSistema();
        try {persistencia.cargarSistema(sistema1);
            } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
                }
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal(sistema1, persistencia);
        });
    }
}