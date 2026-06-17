package ui.gestion;

import modelo.SistemaJJOO;
import servicio.ServicioDelegacion;

import javax.swing.*;
        import java.awt.*;

public class IfrRegistrarEntrenador extends JInternalFrame {
    private ServicioDelegacion servicioDelegacion;
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEdad;
    private JTextField txtEspecialidad;
    private JTextField txtCodigoPais;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    public IfrRegistrarEntrenador(SistemaJJOO sistema) {
        super("Registrar Entrenador",
                false,
                true,
                false,
                true);

        servicioDelegacion = new ServicioDelegacion(sistema);
        configurarVentana();
        inicializarComponentes();
        configurarEventos();
    }

    private void configurarVentana() {
        setSize(420, 220);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelFormulario.add(new JLabel("DNI:")); txtDni = new JTextField(10); panelFormulario.add(txtDni);
        panelFormulario.add(new JLabel("Nombre:")); txtNombre = new JTextField(15); panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellido:")); txtApellido = new JTextField(15); panelFormulario.add(txtApellido);
        panelFormulario.add(new JLabel("Edad:")); txtEdad = new JTextField(5); panelFormulario.add(txtEdad);
        panelFormulario.add(new JLabel("Especialidad:")); txtEspecialidad = new JTextField(15); panelFormulario.add(txtEspecialidad);
        panelFormulario.add(new JLabel("Código País:")); txtCodigoPais = new JTextField(6); panelFormulario.add(txtCodigoPais);

        JPanel panelBotones = new JPanel();
        btnRegistrar = new JButton("Registrar"); btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnRegistrar); panelBotones.add(btnLimpiar);

        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrarEntrenador());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void registrarEntrenador() {
        try {
            Long dni = Long.parseLong(txtDni.getText());
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText());
            String especialidad = txtEspecialidad.getText().trim();
            String codigoPais = txtCodigoPais.getText().trim();
            if (edad <= 0) {
                throw new IllegalArgumentException("La edad debe ser mayor a cero.");
            }
            servicioDelegacion.registrarEntrenador(codigoPais, dni, nombre, apellido, edad, especialidad);
            JOptionPane.showMessageDialog(this, "El entrenador ha sido registrado correctamente.");
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtDni.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtEspecialidad.setText("");
        txtCodigoPais.setText("");

        txtDni.requestFocus();
    }
}