package Ventanas;

import Dominio.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Comprar extends JFrame implements ActionListener, FocusListener {
    private JPanel panel1;
    private JPanel jpComprar;
    private JButton btnComprar;
    private JButton btnHistorialC;
    private JButton btnSalirCompra;
    private JLabel lblComprar;
    private Usuario usuario;

    public Comprar(Usuario usuario) {
        this.usuario = usuario;
    }

    public void PantallaCompra() {
        setSize(500, 500);
        setTitle("Compra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(jpComprar);
        setVisible(true);
        jpComprar.setFocusable(true);
        jpComprar.requestFocusInWindow();
        btnComprar.addActionListener(this);
        btnHistorialC.addActionListener(this);
        btnSalirCompra.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalirCompra) {
            Login log = new Login();
            log.Pantalla();
            setVisible(false);
        }
        if (e.getSource() == btnHistorialC) {
            Historial historial = new Historial(usuario);
            historial.PantallaHistorial();
            setVisible(false);
        }
        if (e.getSource() == btnComprar) {
            MenuCompra diaSemana = new MenuCompra(usuario);
            diaSemana.PantallaMenuCompra();
            setVisible(false);
        }
    }



    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
}