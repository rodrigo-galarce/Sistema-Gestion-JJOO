package ui;

import modelo.SistemaJJOO;
import servicio.ServicioConsulta;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuConsulta {
    private ServicioConsulta servicioConsulta;
    private Scanner scanner;

    public MenuConsulta(SistemaJJOO sistema) {
        servicioConsulta = new ServicioConsulta(sistema);
        scanner = new Scanner(System.in);
    }

    private void mostrarMenuConsulta() {
        System.out.println("""         
                ========================================
                                Consultas
                ========================================
                1. Consultar medallero por país
                2. Consultar atletas
                3. Consultar entrenadores
                4. Consultar disciplinas
                5. Consultar competencias
                6. Consultar delegaciones
                7. Consultar récords
                ----------------------------------------
                0. Volver
                ----------------------------------------
                """);
    }

    public void iniciarMenuConsulta() {
        int opcionSeleccionada = -1;
        while (opcionSeleccionada != 0) {
            mostrarMenuConsulta();
            try {
                System.out.print("Ingrese una opción: "); opcionSeleccionada = scanner.nextInt(); scanner.nextLine();
                if (opcionSeleccionada == 1) {
                    servicioConsulta.consultarMedalleroPorPais();
                /*} else if (opcionSeleccionada == 2) {
                    servicioConsulta.consultarAtletas();
                } else if (opcionSeleccionada == 3) {
                    servicioConsulta.consultarEntrenadores();
                } else if (opcionSeleccionada == 4) {
                    servicioConsulta.consultarDisciplinas();
                } else if (opcionSeleccionada == 5) {
                    servicioConsulta.consultarCompetencias();
                } else if (opcionSeleccionada == 6) {
                    servicioConsulta.consultarDelegaciones();
                } else if (opcionSeleccionada == 7) {
                    servicioConsulta.consultarRecords();*/
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
}