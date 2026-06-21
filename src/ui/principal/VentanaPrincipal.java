package ui.principal;

import excepciones.PersistenciaException;
import modelo.SistemaJJOO;
import persistencia.PersistenciaSistema;
import ui.consultas.IfrConsultas;
import ui.gestion.*;
import ui.inscripciones.IfrInscribirAtleta;
import ui.inscripciones.IfrInscribirPersonalCeremonia;
import ui.resultados.IfrRegistrarResultado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPrincipal extends JFrame {
    private SistemaJJOO sistema;
    private PersistenciaSistema persistencia;
    private JDesktopPane desktopPane;
    private JLabel lblEstado;
    private IfrConsultas ifrConsultas;

    public VentanaPrincipal(SistemaJJOO sistema, PersistenciaSistema persistencia) {
        this.sistema = sistema;
        this.persistencia = persistencia;
        ifrConsultas = new IfrConsultas(sistema);
        configurarVentana();
        inicializarComponentes();
        configurarEventos();
        setVisible(true);
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Juegos Olímpicos");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        // Área principal
        desktopPane = new JDesktopPane();
        desktopPane.setLayout(new BorderLayout());
        add(desktopPane, BorderLayout.CENTER);
        PanelBienvenida panelBienvenida = new PanelBienvenida(sistema);
        desktopPane.add(panelBienvenida, BorderLayout.CENTER);
        // Barra de estado
        lblEstado = new JLabel("Sistema iniciado.");
        add(lblEstado, BorderLayout.SOUTH);
        // Menú superior
        setJMenuBar(crearMenuBar());
    }

    private JMenuBar crearMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Archivo
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemGuardar = new JMenuItem("Guardar");
        JMenuItem itemSalir = new JMenuItem("Salir");
        menuArchivo.add(itemGuardar);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);

        // Gestión
        JMenu menuGestion = new JMenu("Gestión");
        JMenuItem itemRegistrarPais = new JMenuItem("Registrar País");
        itemRegistrarPais.addActionListener(e -> {
            IfrRegistrarPais ventana1 = new IfrRegistrarPais(sistema);
            desktopPane.add(ventana1); ventana1.setVisible(true);});
        JMenuItem itemRegistrarAtleta = new JMenuItem("Registrar Atleta");
        itemRegistrarAtleta.addActionListener(e -> {
            IfrRegistrarAtleta ventana2 = new IfrRegistrarAtleta(sistema);
            desktopPane.add(ventana2); ventana2.setVisible(true);});
        JMenuItem itemRegistrarEntrenador = new JMenuItem("Registrar Entrenador");
        itemRegistrarEntrenador.addActionListener(e -> {
            IfrRegistrarEntrenador ventana3 = new IfrRegistrarEntrenador(sistema);
            desktopPane.add(ventana3); ventana3.setVisible(true);});
        JMenuItem itemRegistrarCeremonia = new JMenuItem("Registrar Ceremonia");
        itemRegistrarCeremonia.addActionListener(e -> {
            IfrRegistrarCeremonia ventana4 = new IfrRegistrarCeremonia(sistema);
            desktopPane.add(ventana4); ventana4.setVisible(true);});
        JMenuItem itemRegistrarDeporte = new JMenuItem("Registrar Deporte");
        itemRegistrarDeporte.addActionListener(e -> {
            IfrRegistrarDeporte ventana5  = new IfrRegistrarDeporte(sistema);
            desktopPane.add(ventana5); ventana5.setVisible(true);});
        JMenuItem itemRegistrarDisciplina = new JMenuItem("Registrar Disciplina");
        itemRegistrarDisciplina.addActionListener(e -> {
            IfrRegistrarDisciplina ventana6 = new IfrRegistrarDisciplina(sistema);
            desktopPane.add(ventana6); ventana6.setVisible(true);});
        JMenuItem itemCrearCompetencia = new JMenuItem("Crear Competencia");
        itemCrearCompetencia.addActionListener(e -> {
            IfrCrearCompetencia ventana7 = new IfrCrearCompetencia(sistema);
            desktopPane.add(ventana7); ventana7.setVisible(true);});
        menuGestion.add(itemRegistrarPais);
        menuGestion.add(itemRegistrarAtleta);
        menuGestion.add(itemRegistrarEntrenador);
        menuGestion.addSeparator();
        menuGestion.add(itemRegistrarCeremonia);
        menuGestion.addSeparator();
        menuGestion.add(itemRegistrarDeporte);
        menuGestion.add(itemRegistrarDisciplina);
        menuGestion.add(itemCrearCompetencia);

        // Inscripciones
        JMenu menuInscripciones = new JMenu("Inscripciones");
        JMenuItem itemInscribirAtleta = new JMenuItem("Inscribir Atleta");
        itemInscribirAtleta.addActionListener(e -> {
            IfrInscribirAtleta ventana8 = new IfrInscribirAtleta(sistema);
            desktopPane.add(ventana8); ventana8.setVisible(true);});
        JMenuItem itemInscribirPersonalCeremonia = new JMenuItem("Inscribir Personal a Ceremonia");
        itemInscribirPersonalCeremonia.addActionListener(e -> {
            IfrInscribirPersonalCeremonia ventana9 = new IfrInscribirPersonalCeremonia(sistema);
            desktopPane.add(ventana9); ventana9.setVisible(true);});
        menuInscripciones.add(itemInscribirAtleta);
        menuInscripciones.add(itemInscribirPersonalCeremonia);

        // Resultados
        JMenu menuResultados = new JMenu("Resultados");
        JMenuItem itemRegistrarResultado = new JMenuItem("Registrar Resultado");
        itemRegistrarResultado.addActionListener(e -> {
            IfrRegistrarResultado ventana10 = new IfrRegistrarResultado(sistema);
            desktopPane.add(ventana10); ventana10.setVisible(true);});
        menuResultados.add(itemRegistrarResultado);

        // Consultas
        JMenu menuConsultas = new JMenu("Consultas");
        JMenuItem itemConsultas = new JMenuItem("Abrir Consultas");
        itemConsultas.addActionListener(e -> abrirConsultas());
        menuConsultas.add(itemConsultas);

        // Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcercaDe = new JMenuItem("Acerca de");
        menuAyuda.add(itemAcercaDe);

        // Agregar menus
        menuBar.add(menuArchivo);
        menuBar.add(menuGestion);
        menuBar.add(menuInscripciones);
        menuBar.add(menuResultados);
        menuBar.add(menuConsultas);
        menuBar.add(menuAyuda);

        // Eventos básicos
        itemGuardar.addActionListener(e -> guardarSistema());
        itemSalir.addActionListener(e -> cerrarAplicacion());
        itemAcercaDe.addActionListener(e -> {
                    String mensaje =
                            "Sistema de Gestión de Juegos Olímpicos\n" +
                                    "Versión 1.0\n\n" +
                                    "Trabajo Final para la materia \"Programación Orientada a Objetos\"\n\n" +
                                    "Desarrollado en Java con Swing\n\n" +
                                    "Autor: Rodrigo Agustin Galarce\n" +
                                    "Año: 2026";
            JOptionPane.showMessageDialog(this, mensaje, "Acerca de", JOptionPane.INFORMATION_MESSAGE);});
        return menuBar;
    }

    private void configurarEventos() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarAplicacion();
            }
        });
    }

    private void guardarSistema() {
        try {
            persistencia.guardarSistema(sistema);
            lblEstado.setText("Sistema guardado correctamente.");
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarAplicacion() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Desea guardar los cambios antes de salir?", "Salir", JOptionPane.YES_NO_CANCEL_OPTION);
        if (opcion == JOptionPane.CANCEL_OPTION) {
            return;
        }
        if (opcion == JOptionPane.YES_OPTION) {
            guardarSistema();
        }
        dispose();
        System.exit(0);
    }

    private void abrirConsultas() {
        if (ifrConsultas.getParent() == null) {
            desktopPane.add(ifrConsultas);
        }
        ifrConsultas.setVisible(true);
        try {
            ifrConsultas.setSelected(true);
        } catch (Exception ignored) {}
        actualizarEstado("Consultando datos...");
    }

    public SistemaJJOO getSistema() {
        return sistema;
    }

    public void actualizarEstado(String mensaje) {
        lblEstado.setText(mensaje);
    }
}