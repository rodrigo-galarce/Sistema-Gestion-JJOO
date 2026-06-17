package ui.gestion;

import modelo.SistemaJJOO;
import servicio.ServicioDeporte;

import javax.swing.*;
import java.awt.*;

public class IfrRegistrarDeporte extends JInternalFrame {
    private ServicioDeporte servicioDeporte;
    private JTextField txtNombre;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    public IfrRegistrarDeporte(SistemaJJOO sistema) {
        super("Registrar Deporte",
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
        setSize(280, 150);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelFormulario.add(new JLabel("Nombre:")); txtNombre = new JTextField(15); panelFormulario.add(txtNombre);
        JPanel panelBotones = new JPanel();
        btnRegistrar = new JButton("Registrar"); btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnRegistrar); panelBotones.add(btnLimpiar);
        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrarDeporte());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void registrarDeporte() {
        try {
            String nombre = txtNombre.getText().trim();
            servicioDeporte.registrarDeporte(nombre);
            JOptionPane.showMessageDialog(this, "El deporte ha sido registrado correctamente.");
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtNombre.requestFocus();
    }
}