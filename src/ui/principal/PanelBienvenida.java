package ui.principal;

import modelo.SistemaJJOO;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PanelBienvenida extends JPanel {

    public PanelBienvenida(SistemaJJOO sistema) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("JUEGOS OLÍMPICOS");
        lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 30));
        lblSubtitulo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblSede = new JLabel(sistema.getSedeCiudad() + " " + sistema.getAnio());
        lblSede.setFont(new Font("Arial", Font.PLAIN, 24));
        lblSede.setAlignmentX(CENTER_ALIGNMENT);

        // GIF
        ImageIcon gif = new ImageIcon(Objects.requireNonNull(getClass().getResource("/recursos/bandera_jjoo.gif")));
        JLabel lblGif = new JLabel(gif);
        lblGif.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblMensaje = new JLabel("Seleccione una opción del menú superior para comenzar.");
        lblMensaje.setFont(new Font("Arial", Font.ITALIC, 18));
        lblMensaje.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(lblTitulo);
        add(Box.createVerticalStrut(15));
        add(lblSubtitulo);
        add(Box.createVerticalStrut(10));
        add(lblSede);
        add(Box.createVerticalStrut(30));
        add(lblGif);
        add(Box.createVerticalStrut(20));
        add(lblMensaje);
        add(Box.createVerticalGlue());
    }
}