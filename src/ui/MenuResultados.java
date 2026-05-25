package ui;

import modelo.SistemaJJOO;
import modelo.deporte.Competencia;
import modelo.marca.*;
import modelo.persona.Atleta;
import servicio.ServicioDelegacion;
import servicio.ServicioDeporte;
import servicio.ServicioResultado;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuResultados {
    private ServicioResultado servicioResultado;
    private ServicioDeporte servicioDeporte;
    private ServicioDelegacion servicioDelegacion;
    private Scanner scanner;

    public MenuResultados(SistemaJJOO sistema) {
        servicioResultado = new ServicioResultado(sistema);
        servicioDeporte = new ServicioDeporte(sistema);
        servicioDelegacion = new ServicioDelegacion(sistema);
        scanner = new Scanner(System.in);
    }

    private void mostrarMenuResultados() {
        System.out.println("""         
                ========================================
                          Gestion de Resultados
                ========================================
                1. Registrar resultado
                ----------------------------------------
                0. Volver
                ----------------------------------------
                """);
    }

    public void iniciarMenuResultados() {
        int opcionSeleccionada = -1;
        while (opcionSeleccionada != 0) {
            mostrarMenuResultados();
            try {
                System.out.print("Ingrese una opción: "); opcionSeleccionada = scanner.nextInt(); scanner.nextLine();
                if (opcionSeleccionada == 1) {
                    System.out.print("Ingrese el nombre del deporte: "); String nombreDeporte = scanner.nextLine();
                    System.out.print("Ingrese el nombre de la disciplina: "); String nombreDisciplina = scanner.nextLine();
                    System.out.print("Ingrese nombre de la competencia: "); String nombreCompetencia = scanner.nextLine();
                    System.out.print("Ingrese el DNI del atleta: "); Long dni = scanner.nextLong(); scanner.nextLine();
                    System.out.println("""
                    Tipo de marca:
                    1. Tiempo
                    2. Distancia
                    3. Puntaje
                    """);
                    int opcionMarca = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Elija una opción: ");
                    double valor = scanner.nextDouble(); scanner.nextLine();
                    Competencia competencia = servicioDeporte.buscarCompetencia(nombreDeporte, nombreDisciplina, nombreCompetencia);
                    Atleta atleta = servicioDelegacion.buscarAtleta(dni);
                    if (competencia != null && atleta != null) {
                        Marca marca = null;
                        if (opcionMarca == 1) {
                            marca = new TiempoMarca(valor);
                            }
                        else if (opcionMarca == 2) {
                            marca = new DistanciaMarca(valor);
                            }
                        else if (opcionMarca == 3) {
                            marca = new PuntajeMarca(valor);
                            }
                        servicioResultado.registrarResultado(competencia, atleta, marca);
                    }
                    else {
                        System.out.println("Competencia o atleta no encontrado.");
                    }
                    MenuPrincipal.volverMenu(scanner);
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
