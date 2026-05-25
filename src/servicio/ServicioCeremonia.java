package servicio;

import modelo.SistemaJJOO;
import modelo.ceremonia.Ceremonia;
import modelo.ceremonia.TipoCeremonia;

import java.time.LocalDate;

public class ServicioCeremonia {
    private SistemaJJOO sistema;

    public ServicioCeremonia(SistemaJJOO sistema) {
        this.sistema = sistema;
    }

    public void registrarCeremonia(String nombre, LocalDate fecha, TipoCeremonia tipo, String ubicacion) {
        Ceremonia ceremonia = new Ceremonia(nombre, fecha, tipo, ubicacion);
        sistema.agregarCeremonia(ceremonia);
        System.out.println("Ceremonia registrada correctamente.");
    }
}
