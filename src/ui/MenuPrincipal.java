package ui;

import modelo.SistemaJJOO;
import servicio.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {
    private SistemaJJOO sistema;
    private MenuOrganizativo menuOrganizativo;
    private MenuDeportivo menuDeportivo;
    private MenuInscripciones menuInscripciones;
    private MenuResultados menuResultados;
    private MenuConsulta menuConsulta;
    private Scanner scanner;

    public MenuPrincipal(SistemaJJOO sistema) {
        this.sistema = sistema;
        scanner = new Scanner(System.in);
        menuOrganizativo = new MenuOrganizativo(sistema);
        menuDeportivo = new MenuDeportivo(sistema);
        menuInscripciones = new MenuInscripciones(sistema);
        menuResultados = new MenuResultados(sistema);
        menuConsulta = new MenuConsulta(sistema);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("""         
                ========================================
                            Sistema de Gestión
                             Juegos Olímpicos
                ========================================
                1. Gestión organizativa
                2. Gestión deportiva
                3. Gestión de inscripciones
                4. Gestión de resultados
                5. Consultas
                ----------------------------------------
                0. Salir
                ----------------------------------------
                
                """);
    }

    public void iniciarMenuPrincipal() {
        int opcionSeleccionada = -1;
        while (opcionSeleccionada != 0) {
            mostrarMenuPrincipal();
            try {
                System.out.print("Ingrese una opción: "); opcionSeleccionada = scanner.nextInt(); scanner.nextLine();
                if (opcionSeleccionada == 1) {
                    menuOrganizativo.iniciarMenuOrganizativo();
                } else if (opcionSeleccionada == 2) {
                    menuDeportivo.iniciarMenuDeportivo();
                } else if (opcionSeleccionada == 3) {
                    menuInscripciones.iniciarMenuInscripciones();
                } else if (opcionSeleccionada == 4) {
                    menuResultados.iniciarMenuResultados();
                } else if (opcionSeleccionada == 5) {
                    menuConsulta.iniciarMenuConsulta();
                } else if (opcionSeleccionada == 0) {
                    System.out.println("Saliendo del sistema.");
                } else {
                    System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Solo se permiten números enteros."); scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getClass().getSimpleName()); scanner.nextLine();
            }
        }
    }

    public static void volverMenu(Scanner scanner) {
        System.out.println("Presione ENTER para continuar.");
        scanner.nextLine();
    }
}