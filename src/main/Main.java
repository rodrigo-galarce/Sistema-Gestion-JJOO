package main;

import modelo.SistemaJJOO;
import ui.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        SistemaJJOO sistema1 = new SistemaJJOO(2028, "Buenos Aires");
        MenuPrincipal menu = new MenuPrincipal(sistema1);
        menu.iniciarMenuPrincipal();

        }
}