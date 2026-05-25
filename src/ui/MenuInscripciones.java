package ui;

import excepciones.InscripcionDuplicadaException;
import modelo.SistemaJJOO;
import modelo.deporte.Competencia;
import modelo.persona.Atleta;
import servicio.ServicioDelegacion;
import servicio.ServicioDeporte;
import servicio.ServicioInscripcion;
import java.util.Scanner;

public class MenuInscripciones {
    private ServicioInscripcion servicioInscripcion;
    private ServicioDeporte servicioDeporte;
    private ServicioDelegacion servicioDelegacion;
    private Scanner scanner;

    public MenuInscripciones(SistemaJJOO sistema) {
        servicioInscripcion = new ServicioInscripcion(sistema);
        servicioDeporte = new ServicioDeporte(sistema);
        servicioDelegacion = new ServicioDelegacion(sistema);
        scanner = new Scanner(System.in);
    }

    private void mostrarMenuInscripciones() {
        System.out.println("""         
                ========================================
                        Gestion de Inscripciones
                ========================================
                1. Inscribir atleta
                2. Inscribir personal para ceremonia
                ----------------------------------------
                0. Volver
                ----------------------------------------
                """);
    }

    public void iniciarMenuInscripciones() {
        int opcionSeleccionada = -1;
        while (opcionSeleccionada != 0) {
            mostrarMenuInscripciones();
            try {
                System.out.print("Ingrese una opción: "); opcionSeleccionada = scanner.nextInt(); scanner.nextLine();
                if (opcionSeleccionada == 1) {
                    System.out.print("Ingrese el nombre del deporte: "); String nombreDeporte = scanner.nextLine();
                    System.out.print("Ingrese el nombre de la disciplina: "); String nombreDisciplina = scanner.nextLine();
                    System.out.print("Ingrese el nombre de la competencia: "); String nombreCompetencia = scanner.nextLine();
                    System.out.print("Ingrese el DNI del atleta: "); Long dni = scanner.nextLong(); scanner.nextLine();
                    Competencia competencia = servicioDeporte.buscarCompetencia(nombreDeporte, nombreDisciplina, nombreCompetencia);
                    Atleta atleta = servicioDelegacion.buscarAtleta(dni);
                    if (competencia != null && atleta != null) {
                        servicioInscripcion.inscribirAtleta(competencia, atleta);
                        }
                    else {
                        System.out.println("Competencia o atleta no encontrado.");
                        }
                    MenuPrincipal.volverMenu(scanner);
                }
                else if (opcionSeleccionada != 0) {
                    System.out.println("Opción inválida.");
                }

            } catch (InscripcionDuplicadaException e) {
                System.out.println(e.getMessage());
                }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


}
