package ui;

import modelo.SistemaJJOO;
import servicio.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {
    private SistemaJJOO sistema;
    private ServicioPais servicioPais;
    private ServicioAtleta servicioAtleta;
    private ServicioEntrenador servicioEntrenador;
    private ServicioDisciplina servicioDisciplina;
    private ServicioCompetencia servicioCompetencia;
    private ServicioResultado servicioResultado;
    private ServicioInscripcion servicioInscripcion;
    private Scanner scanner;

    public MenuPrincipal(SistemaJJOO sistema) {
        this.sistema = sistema;
        scanner = new Scanner(System.in);
        servicioPais = new ServicioPais(sistema);
        servicioAtleta = new ServicioAtleta(sistema);
        servicioEntrenador = new ServicioEntrenador(sistema);
        servicioDisciplina = new ServicioDisciplina(sistema);
        servicioCompetencia = new ServicioCompetencia(sistema);
        servicioResultado = new ServicioResultado(sistema);
        servicioInscripcion = new ServicioInscripcion(sistema);
    }

    private void mostrarMenu() {
        System.out.println("""         
                ========================================
                            Sistema de Gestión
                             Juegos Olímpicos
                ========================================
                1. Registrar país
                2. Registrar atleta
                3. Registrar entrenador
                4. Registrar disciplina
                5. Crear competencia
                6. Inscribir atleta
                7. Registrar resultado
                8. Consultar medallero
                9. Consultar récords
                ----------------------------------------
                0. Salir
                ----------------------------------------
                
                """);
    }

    public void iniciarMenu() {
        int opcionSeleccionada = -1;
        while (opcionSeleccionada != 0) {
            mostrarMenu();
            try {
                opcionSeleccionada = scanner.nextInt();
                scanner.nextLine();
                if (opcionSeleccionada == 1) {
                    System.out.print("Ingrese nombre del país: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese código del país: ");
                    String codigo = scanner.nextLine();
                    servicioPais.registrarPais(nombre, codigo);
                } else if (opcionSeleccionada == 2) {
                    registrarAtleta();
                } else if (opcionSeleccionada == 3) {
                    registrarEntrenador();
                } else if (opcionSeleccionada == 4) {
                    registrarDisciplina();
                } else if (opcionSeleccionada == 5) {
                    crearCompetencia();
                } else if (opcionSeleccionada == 6) {
                    inscribirAtleta();
                } else if (opcionSeleccionada == 7) {
                    registrarResultado();
                } else if (opcionSeleccionada == 8) {
                    servicioConsulta.consultarMedallero();
                } else if (opcionSeleccionada == 9) {
                    servicioConsulta.consultarRecords();
                } else if (opcionSeleccionada == 0) {
                    System.out.println("Saliendo del sistema...");
                } else {
                    System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        "Error: Solo se permiten números enteros"
                );
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(
                        "Error no contemplado: " + e.getClass().getSimpleName());
                scanner.nextLine();
            }
        }
    }
}