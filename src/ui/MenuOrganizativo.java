package ui;

import modelo.SistemaJJOO;
import modelo.ceremonia.TipoCeremonia;
import servicio.ServicioDelegacion;
import servicio.ServicioPais;
import servicio.ServicioCeremonia;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuOrganizativo {
    private ServicioPais servicioPais;
    private ServicioDelegacion servicioAtletaYEntrenador;
    private ServicioCeremonia servicioCeremonia;
    private Scanner scanner;

    public MenuOrganizativo(SistemaJJOO sistema) {
        servicioPais = new ServicioPais(sistema);
        servicioAtletaYEntrenador = new ServicioDelegacion(sistema);
        servicioCeremonia = new ServicioCeremonia(sistema);
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
                4. Registrar ceremonia
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
                    MenuPrincipal.volverMenu(scanner);
                } else if (opcionSeleccionada == 2) {
                    System.out.print("Ingrese el DNI del atleta: "); Long dni = scanner.nextLong(); scanner.nextLine();
                    System.out.print("Ingrese el nombre del atleta: "); String nombre = scanner.nextLine();
                    System.out.print("Ingrese el apellido del atleta: "); String apellido = scanner.nextLine();
                    System.out.print("Ingrese la edad del atleta: "); int edad = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Ingrese la especialidad del atleta: "); String especialidad = scanner.nextLine();
                    System.out.print("Ingrese el código del país: "); String codigoPais = scanner.nextLine();
                    try {
                        if (edad <= 0) {
                            throw new IllegalArgumentException("La edad debe ser mayor a cero.");
                        }
                        servicioAtletaYEntrenador.registrarAtleta(codigoPais, dni, nombre, apellido, edad, especialidad);
                        System.out.println("El atleta ha sido registrado correctamente.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    MenuPrincipal.volverMenu(scanner);
                } else if (opcionSeleccionada == 3) {
                    System.out.print("Ingrese el DNI del entrenador: "); Long dni = scanner.nextLong(); scanner.nextLine();
                    System.out.print("Ingrese el nombre del entrenador: "); String nombre = scanner.nextLine();
                    System.out.print("Ingrese el apellido del entrenador: "); String apellido = scanner.nextLine();
                    System.out.print("Ingrese la edad del entrenador: "); int edad = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Ingrese la especialidad del entrenador: "); String especialidad = scanner.nextLine();
                    System.out.print("Ingrese el código del país: "); String codigoPais = scanner.nextLine();
                    try {
                        if (edad <= 0) {
                            throw new IllegalArgumentException("La edad debe ser mayor a cero.");
                        }
                        servicioAtletaYEntrenador.registrarEntrenador(codigoPais, dni, nombre, apellido, edad, especialidad);
                        System.out.println("El entrenador ha sido registrado correctamente.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    MenuPrincipal.volverMenu(scanner);
                }
                else if (opcionSeleccionada == 4) {
                    System.out.print("Ingrese nombre de la ceremonia: "); String nombre = scanner.nextLine();
                    System.out.print("Ingrese fecha (AAAA-MM-DD): "); LocalDate fecha = LocalDate.parse(scanner.nextLine());
                    TipoCeremonia tipo = null;
                    while (tipo == null) {
                        System.out.println("""
                                Tipo de ceremonia:
                                1. APERTURA
                                2. CLAUSURA
                                3. PREMIACION
                                4. SORTEO
                                5. ENTRETIEMPO
                                """);
                        System.out.println("Ingrese el tipo de ceremonia: "); int opcionTipo =  scanner.nextInt(); scanner.nextLine();
                        if (opcionTipo == 1) {
                            tipo = TipoCeremonia.APERTURA;
                        } else if (opcionTipo == 2) {
                            tipo = TipoCeremonia.CLAUSURA;
                        } else if (opcionTipo == 3) {
                            tipo = TipoCeremonia.PREMIACION;
                        } else if (opcionTipo == 4) {
                            tipo = TipoCeremonia.SORTEO;
                        } else if (opcionTipo == 5) {
                            tipo = TipoCeremonia.ENTRETIEMPO;
                        } else {
                            System.out.println("Opción inválida.");
                        }
                    }
                    System.out.print("Ingrese ubicación: "); String ubicacion = scanner.nextLine();
                    servicioCeremonia.registrarCeremonia(nombre, fecha, tipo, ubicacion);
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