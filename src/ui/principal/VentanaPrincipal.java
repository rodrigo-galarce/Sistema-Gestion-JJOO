package ui.principal;

import excepciones.PersistenciaException;
import modelo.SistemaJJOO;
import persistencia.PersistenciaSistema;
import ui.gestion.IfrRegistrarAtleta;
import ui.gestion.IfrRegistrarDeporte;
import ui.gestion.IfrRegistrarEntrenador;
import ui.gestion.IfrRegistrarPais;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPrincipal extends JFrame {
    private SistemaJJOO sistema;
    private PersistenciaSistema persistencia;
    private JDesktopPane desktopPane;
    private JLabel lblEstado;

    public VentanaPrincipal(SistemaJJOO sistema, PersistenciaSistema persistencia) {
        this.sistema = sistema;
        this.persistencia = persistencia;
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
        JMenuItem itemRegistrarDeporte = new JMenuItem("Registrar Deporte");
        itemRegistrarDeporte.addActionListener(e -> {
            IfrRegistrarDeporte ventana5  = new IfrRegistrarDeporte(sistema);
            desktopPane.add(ventana5); ventana5.setVisible(true);});
        JMenuItem itemRegistrarDisciplina = new JMenuItem("Registrar Disciplina");
        JMenuItem itemCrearCompetencia = new JMenuItem("Crear Competencia");
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
        JMenuItem itemInscribirCeremonia = new JMenuItem("Inscribir Personal a Ceremonia");
        menuInscripciones.add(itemInscribirAtleta);
        menuInscripciones.add(itemInscribirCeremonia);

        // Resultados
        JMenu menuResultados = new JMenu("Resultados");
        JMenuItem itemRegistrarResultado = new JMenuItem("Registrar Resultado");
        menuResultados.add(itemRegistrarResultado);

        // Consultas
        JMenu menuConsultas = new JMenu("Consultas");
        JMenuItem itemMedallero = new JMenuItem("Medallero por País");
        JMenuItem itemAtletas = new JMenuItem("Atletas");
        JMenuItem itemEntrenadores = new JMenuItem("Entrenadores");
        JMenuItem itemDisciplinas = new JMenuItem("Disciplinas");
        JMenuItem itemCompetencias = new JMenuItem("Competencias");
        JMenuItem itemDelegaciones = new JMenuItem("Delegaciones");
        JMenuItem itemRecords = new JMenuItem("Récords");
        JMenuItem itemCeremonias = new JMenuItem("Ceremonias");
        menuConsultas.add(itemMedallero);
        menuConsultas.add(itemAtletas);
        menuConsultas.add(itemEntrenadores);
        menuConsultas.add(itemDisciplinas);
        menuConsultas.add(itemCompetencias);
        menuConsultas.add(itemDelegaciones);
        menuConsultas.add(itemRecords);
        menuConsultas.add(itemCeremonias);

        // Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcercaDe = new JMenuItem("Acerca de");
        menuAyuda.add(itemAcercaDe);
        menuBar.add(menuArchivo);
        menuBar.add(menuGestion);
        menuBar.add(menuInscripciones);
        menuBar.add(menuResultados);
        menuBar.add(menuConsultas);
        menuBar.add(menuAyuda);

        // AGREGAR MENÚS
        menuBar.add(menuArchivo);
        menuBar.add(menuGestion);
        menuBar.add(menuInscripciones);
        menuBar.add(menuResultados);
        menuBar.add(menuConsultas);
        menuBar.add(menuAyuda);

        // EVENTOS BÁSICOS
        itemGuardar.addActionListener(e -> guardarSistema());
        itemSalir.addActionListener(e -> cerrarAplicacion());
        itemAcercaDe.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Sistema de Gestión de Juegos Olímpicos", "Acerca de", JOptionPane.INFORMATION_MESSAGE));
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

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public SistemaJJOO getSistema() {
        return sistema;
    }

    public void actualizarEstado(String mensaje) {
        lblEstado.setText(mensaje);
    }
}