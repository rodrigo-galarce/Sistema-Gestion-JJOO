package ui.gestion;

import modelo.SistemaJJOO;
import servicio.ServicioDeporte;

import javax.swing.*;
import java.awt.*;

public class IfrRegistrarDisciplina extends JInternalFrame {

    private ServicioDeporte servicioDeporte;

    private JTextField txtNombreDeporte;
    private JTextField txtNombreDisciplina;

    private JButton btnRegistrar;
    private JButton btnLimpiar;

    public IfrRegistrarDisciplina(SistemaJJOO sistema) {
        super("Registrar Disciplina",
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
        setSize(350, 180);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelFormulario.add(new JLabel("Deporte:")); txtNombreDeporte = new JTextField(15); panelFormulario.add(txtNombreDeporte);
        panelFormulario.add(new JLabel("Disciplina:")); txtNombreDisciplina = new JTextField(15); panelFormulario.add(txtNombreDisciplina);
        JPanel panelBotones = new JPanel();
        btnRegistrar = new JButton("Registrar"); btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnRegistrar); panelBotones.add(btnLimpiar);
        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrarDisciplina());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void registrarDisciplina() {
        try {
            String nombreDeporte = txtNombreDeporte.getText().trim();
            String nombreDisciplina = txtNombreDisciplina.getText().trim();
            servicioDeporte.registrarDisciplina(nombreDeporte, nombreDisciplina);
            JOptionPane.showMessageDialog(this, "La disciplina ha sido registrada correctamente.");
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombreDeporte.setText("");
        txtNombreDisciplina.setText("");
        txtNombreDeporte.requestFocus();
    }
}