package Ventanas;

import Dominio.Cliente;
import Dominio.ServicioPedidos;
import Dominio.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class Historial extends JFrame implements ActionListener, FocusListener {
    private JTextArea textArea2;
    private JTextArea txaMHistorial;
    private JButton btnVolverH;
    private JPanel jpHistorial;
    private JButton btnMHistorial;
    private Usuario usuario;
    private ServicioPedidos servicioPedidos;

    public Historial(Usuario usuario) {
        this.usuario = usuario;
        this.servicioPedidos = new ServicioPedidos();
    }

    public void PantallaHistorial() {
        setSize(500, 500);
        setTitle("Historial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(jpHistorial);
        setVisible(true);
        jpHistorial.setFocusable(true);
        jpHistorial.requestFocusInWindow();
        btnVolverH.addActionListener(this);
        btnMHistorial.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolverH) {
            Comprar comprar = new Comprar(usuario);
            comprar.PantallaCompra();
            setVisible(false);
        }
        if (e.getSource() == btnMHistorial) {
            String historial = servicioPedidos.verAlmuerzosComprados(new Cliente(usuario.getCorreoElectronico(), usuario.getContraseña()));
            txaMHistorial.setText(historial);
        }
    }


    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
}
