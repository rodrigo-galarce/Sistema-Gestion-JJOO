package ui;

import modelo.SistemaJJOO;
import servicio.ServicioDelegacion;
import servicio.ServicioPais;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuOrganizativo {
    private ServicioPais servicioPais;
    private ServicioDelegacion servicioAtletaYEntrenador;
    private Scanner scanner;

    public MenuOrganizativo(SistemaJJOO sistema) {
        servicioPais = new ServicioPais(sistema);
        servicioAtletaYEntrenador = new ServicioDelegacion(sistema);
        scanner = new Scanner(System.in);
    }

    private void mostrarMenuOrganizativo() {
        System.out.println("""         
                ========================================
                          Gestion Organizativa
                ========================================
                1. Registrar país
                2. Registrar atleta
                3. Registrar entrenador
                ----------------------------------------
                0. Volver
                ----------------------------------------
                """);
    }

    public void iniciarMenuOrganizativo() {
        int opcionSeleccionada = -1;
        while (opcionSeleccionada != 0) {
            mostrarMenuOrganizativo();
            try {
                System.out.print("Ingrese una opción: "); opcionSeleccionada = scanner.nextInt(); scanner.nextLine();
                if (opcionSeleccionada == 1) {
                    System.out.print("Ingrese el nombre del país: "); String nombre = scanner.nextLine();
                    System.out.print("Ingrese el código del país: "); String codigo = scanner.nextLine();
                    try {
                        servicioPais.registrarPais(nombre, codigo);
                        System.out.println("El país ha sido registrado correctamente.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (opcionSeleccionada == 2) {
                    System.out.print("Ingrese el DNI del atleta: "); Long dni = scanner.nextLong(); scanner.nextLine();
                    System.out.print("Ingrese el nombre del atleta: "); String nombre = scanner.nextLine();
                    System.out.print("Ingrese el apellido del atleta: "); String apellido = scanner.nextLine();
                    System.out.print("Ingrese la edad del atleta: "); int edad = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Ingrese la nacionalidad del atleta: "); String nacionalidad = scanner.nextLine();
                    System.out.print("Ingrese la especialidad del atleta: "); String especialidad = scanner.nextLine();
                    System.out.print("Ingrese el código del país: "); String codigoPais = scanner.nextLine();
                    try {
                        servicioAtletaYEntrenador.registrarAtleta(codigoPais, dni, nombre, apellido, edad, nacionalidad, especialidad);
                        System.out.println("El atleta ha sido registrado correctamente.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (opcionSeleccionada == 3) {
                    System.out.print("Ingrese el DNI del entrenador: "); Long dni = scanner.nextLong(); scanner.nextLine();
                    System.out.print("Ingrese el nombre del entrenador: "); String nombre = scanner.nextLine();
                    System.out.print("Ingrese el apellido del entrenador: "); String apellido = scanner.nextLine();
                    System.out.print("Ingrese la edad del entrenador: "); int edad = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Ingrese la nacionalidad del entrenador: "); String nacionalidad = scanner.nextLine();
                    System.out.print("Ingrese la especialidad del entrenador: "); String especialidad = scanner.nextLine();
                    System.out.print("Ingrese el código del país: "); String codigoPais = scanner.nextLine();
                    try {
                        servicioAtletaYEntrenador.registrarEntrenador(codigoPais, dni, nombre, apellido, edad, nacionalidad, especialidad);
                        System.out.println("El entrenador ha sido registrado correctamente.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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