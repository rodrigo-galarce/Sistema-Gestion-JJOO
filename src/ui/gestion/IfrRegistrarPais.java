package ui.gestion;

import modelo.SistemaJJOO;
import servicio.ServicioPais;

import javax.swing.*;
import java.awt.*;

public class IfrRegistrarPais extends JInternalFrame {
    private ServicioPais servicioPais;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    public IfrRegistrarPais(SistemaJJOO sistema) {
        super("Registrar País",
                false,
                true,
                false,
                true);

        servicioPais = new ServicioPais(sistema);
        configurarVentana();
        inicializarComponentes();
        configurarEventos();
    }

    private void configurarVentana() {
        setSize(280, 180);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panelFormulario.add(new JLabel("Nombre:")); txtNombre = new JTextField(15); panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Código:")); txtCodigo = new JTextField(6); panelFormulario.add(txtCodigo);

        JPanel panelBotones = new JPanel(); btnRegistrar = new JButton("Registrar"); btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnRegistrar); panelBotones.add(btnLimpiar);

        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrarPais());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void registrarPais() {
        try {
            String nombre = txtNombre.getText().trim();
            String codigo = txtCodigo.getText().trim();
            servicioPais.registrarPais(nombre, codigo);

            JOptionPane.showMessageDialog(this, "El país ha sido registrado correctamente.");
            limpiarCampos();
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCodigo.setText("");
        txtNombre.requestFocus();
    }
}