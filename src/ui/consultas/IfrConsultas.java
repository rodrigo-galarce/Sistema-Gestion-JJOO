package ui.consultas;

import modelo.SistemaJJOO;
import modelo.ceremonia.Ceremonia;
import modelo.deporte.Competencia;
import modelo.deporte.Deporte;
import modelo.deporte.Disciplina;
import modelo.pais.Pais;
import modelo.persona.Atleta;
import modelo.persona.Entrenador;
import modelo.resultado.Record;
import servicio.ServicioConsulta;
import ui.principal.VentanaPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class IfrConsultas extends JInternalFrame {
    private SistemaJJOO sistema;
    private VentanaPrincipal ventanaPrincipal;

    private JComboBox<String> cmbConsulta;
    private JTable tabla;
    private DefaultTableModel model;

    private JButton btnActualizar;
    private JButton btnVolver;

    public IfrConsultas(SistemaJJOO sistema, VentanaPrincipal ventanaPrincipal) {
        super("Consultas",
                true,
                true,
                true,
                true);

        this.sistema = sistema;
        this.ventanaPrincipal = ventanaPrincipal;
        configurarVentana();
        inicializarComponentes();
        configurarEventos();
    }

    private void configurarVentana() {
        setSize(700, 450);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        cmbConsulta = new JComboBox<>(new String[]{
                "Medallero",
                "Atletas",
                "Entrenadores",
                "Disciplinas",
                "Competencias",
                "Delegaciones",
                "Records",
                "Ceremonias"
        });
        model = new DefaultTableModel();
        tabla = new JTable(model);

        JScrollPane scroll = new JScrollPane(tabla);

        btnActualizar = new JButton("Actualizar");
        btnVolver = new JButton("Volver");
        JPanel top = new JPanel();
        top.add(new JLabel("Consulta:"));
        top.add(cmbConsulta);
        top.add(btnActualizar);

        JPanel bottom = new JPanel();
        bottom.add(btnVolver);

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnActualizar.addActionListener(e -> cargarTabla());
        btnVolver.addActionListener(e -> dispose());
    }

    private void cargarTabla() {
        String seleccion = (String) cmbConsulta.getSelectedItem();
        model.setRowCount(0);
        model.setColumnCount(0);
        switch (seleccion) {
            case "Medallero" -> cargarMedallero();
            case "Atletas" -> cargarAtletas();
            case "Entrenadores" -> cargarEntrenadores();
            case "Disciplinas" -> cargarDisciplinas();
            case "Competencias" -> cargarCompetencias();
            case "Delegaciones" -> cargarDelegaciones();
            case "Records" -> cargarRecords();
            case "Ceremonias" -> cargarCeremonias();
        }
    }

    private void cargarMedallero() {
        model.setColumnIdentifiers(new String[]{"País", "Medallas"});
        for (Pais pais : sistema.getListaPaises().values()) {
            int cantidad = 0;
            for (Atleta a : pais.getDelegacion().getListaAtletas().values()) {
                cantidad += a.getListaMedallas().size();
            }
            model.addRow(new Object[]{pais.getNombre(), cantidad});
        }
    }

    private void cargarAtletas() {
        model.setColumnIdentifiers(new String[]{"País", "Nombre", "Apellido", "DNI", "Especialidad"});
        for (Pais pais : sistema.getListaPaises().values()) {
            for (Atleta a : pais.getDelegacion().getListaAtletas().values()) {
                model.addRow(new Object[]{pais.getNombre(), a.getNombre(), a.getApellido(), a.getDni(), a.getEspecialidad()});
            }
        }
    }

    private void cargarEntrenadores() {
        model.setColumnIdentifiers(new String[]{"País", "Nombre", "Apellido", "DNI", "Especialidad"});
        for (Pais pais : sistema.getListaPaises().values()) {
            for (Entrenador e : pais.getDelegacion().getListaEntrenadores().values()) {
                model.addRow(new Object[]{pais.getNombre(), e.getNombre(), e.getApellido(), e.getDni(), e.getEspecialidad()});
            }
        }
    }

    private void cargarDisciplinas() {
        model.setColumnIdentifiers(new String[]{"Deporte", "Disciplina"});
        for (Deporte d : sistema.getListaDeportes().values()) {
            for (Disciplina dis : d.getListaDisciplinas()) {
                model.addRow(new Object[]{d.getNombre(), dis.getNombre()});
            }
        }
    }

    private void cargarCompetencias() {
        model.setColumnIdentifiers(new String[]{"Disciplina", "Competencia", "Fecha"});
        for (Deporte d : sistema.getListaDeportes().values()) {
            for (Disciplina dis : d.getListaDisciplinas()) {
                for (Competencia c : dis.getListaCompetencias()) {
                    model.addRow(new Object[]{dis.getNombre(), c.getNombre(), c.getFecha()});
                }
            }
        }
    }

    private void cargarDelegaciones() {
        model.setColumnIdentifiers(new String[]{"País"});
        for (Pais pais : sistema.getListaPaises().values()) {
            model.addRow(new Object[]{pais.getNombre()});
        }
    }

    private void cargarRecords() {
        model.setColumnIdentifiers(new String[]{"Disciplina", "Record"});
        for (Deporte d : sistema.getListaDeportes().values()) {
            for (Disciplina dis : d.getListaDisciplinas()) {
                Record r = dis.getRecordActual();
                if (r != null) {
                    model.addRow(new Object[]{dis.getNombre(), r.getMarca()});
                }
            }
        }
    }

    private void cargarCeremonias() {
        model.setColumnIdentifiers(new String[]{"Nombre", "Fecha", "Tipo", "Ubicación"});
        for (Ceremonia c : sistema.getListaCeremonias()) {
            model.addRow(new Object[]{c.getNombre(), c.getFecha(), c.getTipo(), c.getUbicacion()});
        }
    }
}
