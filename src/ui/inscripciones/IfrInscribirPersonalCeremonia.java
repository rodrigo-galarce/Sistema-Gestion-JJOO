package ui.inscripciones;

import excepciones.InscripcionDuplicadaException;
import modelo.*;
import modelo.ceremonia.Ceremonia;
import modelo.ceremonia.RolCeremonia;
import modelo.persona.Persona;
import servicio.ServicioCeremonia;
import servicio.ServicioDelegacion;
import servicio.ServicioInscripcion;

import javax.swing.*;
import java.awt.*;

public class IfrInscribirPersonalCeremonia extends JInternalFrame {

    private ServicioInscripcion servicioInscripcion;
    private ServicioDelegacion servicioDelegacion;
    private ServicioCeremonia servicioCeremonia;

    private JTextField txtDni;
    private JTextField txtCeremonia;

    private JComboBox<RolCeremonia> cmbRol;

    private JButton btnInscribir;
    private JButton btnLimpiar;

    public IfrInscribirPersonalCeremonia(SistemaJJOO sistema) {
        super("Inscribir Personal a Ceremonia",
                false,
                true,
                false,
                true);

        servicioInscripcion = new ServicioInscripcion(sistema);
        servicioDelegacion = new ServicioDelegacion(sistema);
        servicioCeremonia = new ServicioCeremonia(sistema);
        configurarVentana();
        inicializarComponentes();
        configurarEventos();
    }

    private void configurarVentana() {
        setSize(420, 200);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(); panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelFormulario.add(new JLabel("DNI Persona:")); txtDni = new JTextField(10); panelFormulario.add(txtDni);
        panelFormulario.add(new JLabel("Ceremonia:")); txtCeremonia = new JTextField(15); panelFormulario.add(txtCeremonia);
        panelFormulario.add(new JLabel("Rol:")); cmbRol = new JComboBox<>(RolCeremonia.values()); panelFormulario.add(cmbRol);
        JPanel panelBotones = new JPanel();
        btnInscribir = new JButton("Inscribir"); btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnInscribir); panelBotones.add(btnLimpiar);
        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnInscribir.addActionListener(e -> inscribirPersonal());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void inscribirPersonal() {
        try {
            Long dni = Long.parseLong(txtDni.getText().trim());
            String nombreCeremonia = txtCeremonia.getText().trim();
            RolCeremonia rol = (RolCeremonia) cmbRol.getSelectedItem();

            Persona persona = servicioDelegacion.buscarPersona(dni);
            Ceremonia ceremonia = servicioInscripcion.buscarCeremonia(nombreCeremonia);
            if (persona == null || ceremonia == null) {
                throw new IllegalArgumentException("Persona o ceremonia no encontrada.");
            }
            servicioInscripcion.inscribirPersonalACeremonia(persona, ceremonia, rol);
            JOptionPane.showMessageDialog(this, "El personal fue inscripto correctamente a la ceremonia.");
            limpiarCampos();
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El DNI debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);}
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtDni.setText("");
        txtCeremonia.setText("");
        cmbRol.setSelectedIndex(0);
        txtDni.requestFocus();
    }
}