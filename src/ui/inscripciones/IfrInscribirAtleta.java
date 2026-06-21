package ui.inscripciones;

import excepciones.InscripcionDuplicadaException;
import modelo.*;
import modelo.deporte.Competencia;
import modelo.persona.Atleta;
import servicio.ServicioDeporte;
import servicio.ServicioDelegacion;
import servicio.ServicioInscripcion;

import javax.swing.*;
import java.awt.*;

public class IfrInscribirAtleta extends JInternalFrame {
    private ServicioInscripcion servicioInscripcion;
    private ServicioDeporte servicioDeporte;
    private ServicioDelegacion servicioDelegacion;

    private JTextField txtDeporte;
    private JTextField txtDisciplina;
    private JTextField txtCompetencia;
    private JTextField txtDni;

    private JButton btnInscribir;
    private JButton btnLimpiar;

    public IfrInscribirAtleta(SistemaJJOO sistema) {
        super("Inscribir Atleta",
                false,
                true,
                false,
                true);

        servicioInscripcion = new ServicioInscripcion(sistema);
        servicioDeporte = new ServicioDeporte(sistema);
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
        panelFormulario.add(new JLabel("Deporte:")); txtDeporte = new JTextField(15); panelFormulario.add(txtDeporte);
        panelFormulario.add(new JLabel("Disciplina:")); txtDisciplina = new JTextField(15); panelFormulario.add(txtDisciplina);
        panelFormulario.add(new JLabel("Competencia:")); txtCompetencia = new JTextField(15); panelFormulario.add(txtCompetencia);
        panelFormulario.add(new JLabel("DNI Atleta:")); txtDni = new JTextField(10); panelFormulario.add(txtDni);
        JPanel panelBotones = new JPanel();
        btnInscribir = new JButton("Inscribir"); btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnInscribir); panelBotones.add(btnLimpiar);
        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnInscribir.addActionListener(e -> inscribirAtleta());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void inscribirAtleta() {
        try {
            String deporte = txtDeporte.getText().trim();
            String disciplina = txtDisciplina.getText().trim();
            String competencia = txtCompetencia.getText().trim();
            Long dni = Long.parseLong(txtDni.getText().trim());

            Competencia comp = servicioDeporte.buscarCompetencia(deporte, disciplina, competencia);
            Atleta atleta = servicioDelegacion.buscarAtleta(dni);
            if (comp == null || atleta == null) {
                throw new IllegalArgumentException("Competencia o atleta no encontrado.");
            }

            servicioInscripcion.inscribirAtleta(comp, atleta);
            JOptionPane.showMessageDialog(this, "El atleta ha sido inscripto correctamente.");
            limpiarCampos();
        } catch (InscripcionDuplicadaException e) {
            JOptionPane.showMessageDialog(this, "El atleta ya está inscripto en esta competencia.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El DNI debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtDeporte.setText("");
        txtDisciplina.setText("");
        txtCompetencia.setText("");
        txtDni.setText("");
        txtDeporte.requestFocus();
    }
}
