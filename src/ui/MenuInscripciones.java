package ui;

import excepciones.InscripcionDuplicadaException;
import modelo.SistemaJJOO;
import modelo.ceremonia.Ceremonia;
import modelo.ceremonia.RolCeremonia;
import modelo.deporte.Competencia;
import modelo.persona.Atleta;
import modelo.persona.Persona;
import servicio.ServicioDelegacion;
import servicio.ServicioDeporte;
import servicio.ServicioInscripcion;

import java.util.InputMismatchException;
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
                System.out.print("Ingrese una opción: ");
                opcionSeleccionada = scanner.nextInt();
                scanner.nextLine();
                if (opcionSeleccionada == 1) {
                    System.out.print("Ingrese el nombre del deporte: ");
                    String nombreDeporte = scanner.nextLine();
                    System.out.print("Ingrese el nombre de la disciplina: ");
                    String nombreDisciplina = scanner.nextLine();
                    System.out.print("Ingrese el nombre de la competencia: ");
                    String nombreCompetencia = scanner.nextLine();
                    System.out.print("Ingrese el DNI del atleta: ");
                    Long dni = scanner.nextLong();
                    scanner.nextLine();
                    Competencia competencia = servicioDeporte.buscarCompetencia(nombreDeporte, nombreDisciplina, nombreCompetencia);
                    Atleta atleta = servicioDelegacion.buscarAtleta(dni);
                    if (competencia != null && atleta != null) {
                        servicioInscripcion.inscribirAtleta(competencia, atleta);
                    } else {
                        System.out.println("Competencia o atleta no encontrado.");
                    }
                    MenuPrincipal.volverMenu(scanner);
                } else if (opcionSeleccionada == 2) {
                    System.out.print("Ingrese DNI de la persona: "); Long dni = scanner.nextLong(); scanner.nextLine();
                    System.out.print("Ingrese nombre de la ceremonia: "); String nombreCeremonia = scanner.nextLine();
                    Persona persona = servicioDelegacion.buscarPersona(dni);
                    Ceremonia ceremonia = servicioInscripcion.buscarCeremonia(nombreCeremonia);
                    if (persona == null || ceremonia == null) {
                        System.out.println("Persona o ceremonia inexistente.");
                    } else {
                        RolCeremonia rol = null;
                        while (rol == null) {
                            try {
                                System.out.println("""
                                    1. ABANDERADO
                                    2. PREMIADO
                                    3. PRESENTADOR
                                    4. CANTANTE
                                    5. PERFORMER
                                    """);
                                System.out.print("Elija una opción: "); int opcionRol = scanner.nextInt(); scanner.nextLine();
                                if (opcionRol == 1) {
                                    rol = RolCeremonia.ABANDERADO;
                                } else if (opcionRol == 2) {
                                    rol = RolCeremonia.PREMIADO;
                                } else if (opcionRol == 3) {
                                    rol = RolCeremonia.PRESENTADOR;
                                } else if (opcionRol == 4) {
                                    rol = RolCeremonia.CANTANTE;
                                } else if (opcionRol == 5) {
                                    rol = RolCeremonia.PERFORMER;
                                } else {
                                    System.out.println("Opción inválida.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debe ingresar un número."); scanner.nextLine();
                            }
                        }
                        servicioInscripcion.inscribirPersonalACeremonia(persona, ceremonia, rol);
                    }
                    MenuPrincipal.volverMenu(scanner);
            } else if (opcionSeleccionada != 0) {
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
