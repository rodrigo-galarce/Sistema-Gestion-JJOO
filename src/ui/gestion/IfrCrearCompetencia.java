package ui.gestion;

import excepciones.CompetenciaFueraDeFechaException;
import modelo.SistemaJJOO;
import servicio.ServicioDeporte;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class IfrCrearCompetencia extends JInternalFrame {
    private ServicioDeporte servicioDeporte;

    private JTextField txtNombreDeporte;
    private JTextField txtNombreDisciplina;
    private JTextField txtNombreCompetencia;
    private JTextField txtFecha;
    private JTextField txtNombreInstalacion;
    private JTextField txtUbicacion;
    private JTextField txtCapacidad;

    private JButton btnCrear;
    private JButton btnLimpiar;

    public IfrCrearCompetencia(SistemaJJOO sistema) {
        super("Crear Competencia",
                false,
                true,
                false,
                true);

        servicioDeporte = new ServicioDeporte(sistema);
        configurarVentana();
        inicializarComponentes();
        configurarEventos();
    }

    private void configurarVentana() {
        setSize(500, 260);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelFormulario.add(new JLabel("Deporte:")); txtNombreDeporte = new JTextField(15); panelFormulario.add(txtNombreDeporte);
        panelFormulario.add(new JLabel("Disciplina:")); txtNombreDisciplina = new JTextField(15); panelFormulario.add(txtNombreDisciplina);
        panelFormulario.add(new JLabel("Competencia:")); txtNombreCompetencia = new JTextField(15); panelFormulario.add(txtNombreCompetencia);
        panelFormulario.add(new JLabel("Fecha (AAAA-MM-DD):")); txtFecha = new JTextField(10); panelFormulario.add(txtFecha);
        panelFormulario.add(new JLabel("Instalación:")); txtNombreInstalacion = new JTextField(15); panelFormulario.add(txtNombreInstalacion);
        panelFormulario.add(new JLabel("Ubicación:")); txtUbicacion = new JTextField(15); panelFormulario.add(txtUbicacion);
        panelFormulario.add(new JLabel("Capacidad:")); txtCapacidad = new JTextField(6); panelFormulario.add(txtCapacidad);
        JPanel panelBotones = new JPanel();
        btnCrear = new JButton("Crear"); btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnCrear); panelBotones.add(btnLimpiar);
        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnCrear.addActionListener(e -> crearCompetencia());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void crearCompetencia() {
        try {
            String nombreDeporte = txtNombreDeporte.getText().trim();
            String nombreDisciplina = txtNombreDisciplina.getText().trim();
            String nombreCompetencia = txtNombreCompetencia.getText().trim();
            LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
            String nombreInstalacion = txtNombreInstalacion.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();
            int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
            if (capacidad <= 0) {
                throw new IllegalArgumentException("La capacidad debe ser mayor a cero.");
            }
            servicioDeporte.crearCompetencia(nombreDeporte, nombreDisciplina, nombreCompetencia, fecha, nombreInstalacion, ubicacion, capacidad);
            JOptionPane.showMessageDialog(this, "La competencia ha sido creada correctamente.");
            limpiarCampos();
        }
        catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido.\nIngrese la fecha como AAAA-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (CompetenciaFueraDeFechaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La capacidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombreDeporte.setText("");
        txtNombreDisciplina.setText("");
        txtNombreCompetencia.setText("");
        txtFecha.setText("");
        txtNombreInstalacion.setText("");
        txtUbicacion.setText("");
        txtCapacidad.setText("");
        txtNombreDeporte.requestFocus();
    }
}