package ui;

import modelo.SistemaJJOO;
import servicio.ServicioDeporte;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuDeportivo {
    private ServicioDeporte servicioDeporte;
    private Scanner scanner;

    public MenuDeportivo(SistemaJJOO sistema) {
        servicioDeporte = new ServicioDeporte(sistema);
        scanner = new Scanner(System.in);
    }

    private void mostrarMenuDeportivo() {
        System.out.println("""         
                ========================================
                           Gestion Deportiva
                ========================================
                1. Registrar deporte
                2. Registrar disciplina
                3. Crear competencia
                ----------------------------------------
                0. Volver
                ----------------------------------------
                """);
    }

    public void iniciarMenuDeportivo() {
        int opcionSeleccionada = -1;
        while (opcionSeleccionada != 0) {
            mostrarMenuDeportivo();
            try {
                System.out.print("Ingrese una opción: "); opcionSeleccionada = scanner.nextInt(); scanner.nextLine();
                if (opcionSeleccionada == 1) {
                    System.out.print("Ingrese nombre del deporte: "); String nombreDeporte = scanner.nextLine();
                    servicioDeporte.registrarDeporte(nombreDeporte);
                    System.out.println("El deporte ha sido registrado correctamente.");
                } else if (opcionSeleccionada == 2) {
                    System.out.print("Ingrese nombre del deporte: "); String nombreDeporte = scanner.nextLine();
                    System.out.print("Ingrese nombre de la disciplina: "); String nombreDisciplina = scanner.nextLine();
                    servicioDeporte.registrarDisciplina(nombreDeporte, nombreDisciplina);
                    System.out.println("La disciplina ha sido registrada correctamente.");
                } else if (opcionSeleccionada == 3) {
                    System.out.print("Ingrese nombre del deporte: "); String nombreDeporte = scanner.nextLine();
                    System.out.print("Ingrese nombre de la disciplina: "); String nombreDisciplina = scanner.nextLine();
                    System.out.print("Ingrese nombre de la competencia: "); String nombreCompetencia = scanner.nextLine();
                    System.out.print("Ingrese año: "); int anio = scanner.nextInt();
                    System.out.print("Ingrese mes: "); int mes = scanner.nextInt();
                    System.out.print("Ingrese día: "); int dia = scanner.nextInt();
                    scanner.nextLine(); LocalDate fecha = LocalDate.of(anio, mes, dia);
                    System.out.print("Ingrese nombre de la instalación: "); String nombreInstalacion = scanner.nextLine();
                    System.out.print("Ingrese ubicación: "); String ubicacion = scanner.nextLine();
                    System.out.print("Ingrese capacidad: "); int capacidad = scanner.nextInt();
                    scanner.nextLine();
                    servicioDeporte.crearCompetencia(nombreDeporte, nombreDisciplina, nombreCompetencia, fecha, nombreInstalacion, ubicacion, capacidad);
                    System.out.println("La competencia ha sido creada correctamente.");
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
