package Ventanas;

import Dominio.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ticket extends JFrame implements ActionListener {
    private JPanel panel1;
    private JButton btnvolverTicket;
    private JPanel jpTicket;
    private JTextArea txaRetiro;
    private JTextArea txaDetalles;
    private Usuario usuario;

    public void Pantalla() {
        setSize(500, 500);
        setTitle("Ticket");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(jpTicket);
        setVisible(true);
        jpTicket.setFocusable(true);
        jpTicket.requestFocusInWindow();
        btnvolverTicket.addActionListener(this);
    }

    public Ticket(Usuario usu, String title, String numeroPedido, String pedido) throws HeadlessException {
        super(title);
        this.usuario = usu;
        txaRetiro.setText(numeroPedido);
        txaDetalles.setText(pedido);
    }

    public Ticket(ArrayList pagosRealizados) {
        txaRetiro.setText((String) pagosRealizados.get(0));
        txaDetalles.setText((String) pagosRealizados.get(1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnvolverTicket) {
            Comprar comprar = new Comprar(usuario);
            comprar.PantallaCompra();
            this.dispose();
        }
    }

}