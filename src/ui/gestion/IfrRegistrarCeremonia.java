package ui.gestion;

import modelo.SistemaJJOO;
import modelo.ceremonia.TipoCeremonia;
import servicio.ServicioCeremonia;

import java.time.format.DateTimeParseException;
import javax.swing.*;
        import java.awt.*;
        import java.time.LocalDate;

public class IfrRegistrarCeremonia extends JInternalFrame {
    private ServicioCeremonia servicioCeremonia;
    private JTextField txtNombre;
    private JTextField txtFecha;
    private JTextField txtUbicacion;
    private JComboBox<TipoCeremonia> cmbTipo;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    public IfrRegistrarCeremonia(SistemaJJOO sistema) {
        super("Registrar Ceremonia",
                false,
                true,
                false,
                true);

        servicioCeremonia = new ServicioCeremonia(sistema);
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
        panelFormulario.add(new JLabel("Nombre:")); txtNombre = new JTextField(15); panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Fecha (AAAA-MM-DD):")); txtFecha = new JTextField(10); panelFormulario.add(txtFecha);
        panelFormulario.add(new JLabel("Tipo:")); cmbTipo = new JComboBox<>(TipoCeremonia.values()); panelFormulario.add(cmbTipo);
        panelFormulario.add(new JLabel("Ubicación:")); txtUbicacion = new JTextField(15); panelFormulario.add(txtUbicacion);
        JPanel panelBotones = new JPanel();
        btnRegistrar = new JButton("Registrar"); btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnRegistrar); panelBotones.add(btnLimpiar);
        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrarCeremonia());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void registrarCeremonia() {
        try {
            String nombre = txtNombre.getText().trim();
            LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
            TipoCeremonia tipo = (TipoCeremonia) cmbTipo.getSelectedItem();
            String ubicacion = txtUbicacion.getText().trim();
            servicioCeremonia.registrarCeremonia(nombre, fecha, tipo, ubicacion);
            JOptionPane.showMessageDialog(this, "La ceremonia ha sido registrada correctamente.");
            limpiarCampos();
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido.\nIngrese la fecha como AAAA-MM-DD.", "Error",JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtFecha.setText("");
        txtUbicacion.setText("");
        cmbTipo.setSelectedIndex(0);
        txtNombre.requestFocus();
    }
}