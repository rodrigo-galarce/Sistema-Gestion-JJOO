package ui.resultados;

import modelo.*;
import modelo.deporte.Competencia;
import modelo.marca.*;
import modelo.persona.Atleta;
import modelo.resultado.Medalla;
import modelo.resultado.Resultado;
import servicio.ServicioDeporte;
import servicio.ServicioDelegacion;
import servicio.ServicioResultado;

import javax.swing.*;
import java.awt.*;

public class IfrRegistrarResultado extends JInternalFrame {
    private ServicioResultado servicioResultado;
    private ServicioDeporte servicioDeporte;
    private ServicioDelegacion servicioDelegacion;

    private JTextField txtDeporte;
    private JTextField txtDisciplina;
    private JTextField txtCompetencia;
    private JTextField txtDni;
    private JTextField txtValor;

    private JComboBox<String> cmbTipoMarca;

    private JButton btnRegistrar;
    private JButton btnLimpiar;

    public IfrRegistrarResultado(SistemaJJOO sistema) {
        super("Registrar Resultado",
                false,
                true,
                false,
                true);

        servicioResultado = new ServicioResultado(sistema);
        servicioDeporte = new ServicioDeporte(sistema);
        servicioDelegacion = new ServicioDelegacion(sistema);
        configurarVentana();
        inicializarComponentes();
        configurarEventos();
    }

    private void configurarVentana() {
        setSize(450, 260);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(); panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelFormulario.add(new JLabel("Deporte:")); txtDeporte = new JTextField(12); panelFormulario.add(txtDeporte);
        panelFormulario.add(new JLabel("Disciplina:")); txtDisciplina = new JTextField(12); panelFormulario.add(txtDisciplina);
        panelFormulario.add(new JLabel("Competencia:")); txtCompetencia = new JTextField(12); panelFormulario.add(txtCompetencia);
        panelFormulario.add(new JLabel("DNI Atleta:")); txtDni = new JTextField(10); panelFormulario.add(txtDni);
        panelFormulario.add(new JLabel("Tipo:")); cmbTipoMarca = new JComboBox<>(new String[]{
                "Tiempo",
                "Distancia",
                "Puntaje"
        });
        panelFormulario.add(cmbTipoMarca); panelFormulario.add(new JLabel("Valor:")); txtValor = new JTextField(8);
        panelFormulario.add(txtValor); JPanel panelBotones = new JPanel();
        btnRegistrar = new JButton("Registrar"); btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnRegistrar); panelBotones.add(btnLimpiar);
        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrarResultado());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void registrarResultado() {
        try {
            String deporte = txtDeporte.getText().trim();
            String disciplina = txtDisciplina.getText().trim();
            String competencia = txtCompetencia.getText().trim();
            Long dni = Long.parseLong(txtDni.getText().trim());
            double valor = Double.parseDouble(txtValor.getText().trim());
            Competencia comp = servicioDeporte.buscarCompetencia(deporte, disciplina, competencia);
            Atleta atleta = servicioDelegacion.buscarAtleta(dni);
            if (comp == null || atleta == null) {
                throw new IllegalArgumentException("Competencia o atleta no encontrado.");
            }
            Marca marca;
            String tipo = (String) cmbTipoMarca.getSelectedItem();
            if ("Tiempo".equals(tipo)) {
                marca = new TiempoMarca(valor);
            } else if ("Distancia".equals(tipo)) {
                marca = new DistanciaMarca(valor);
            } else {
                marca = new PuntajeMarca(valor);
            }
            servicioResultado.registrarResultado(comp, atleta, marca);
            JOptionPane.showMessageDialog(this, "Resultado registrado correctamente.");
            limpiarCampos();
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "DNI y valor deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtDeporte.setText("");
        txtDisciplina.setText("");
        txtCompetencia.setText("");
        txtDni.setText("");
        txtValor.setText("");
        cmbTipoMarca.setSelectedIndex(0);
        txtDeporte.requestFocus();
    }
}