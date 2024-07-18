package Ventanas;

import Dominio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MenuCompra extends JFrame implements ActionListener, FocusListener {
    private JPanel panel1;
    private JComboBox cbxEnsalada;
    private JComboBox cbxBebestible;
    private JComboBox cbxPostre;
    private JComboBox cbxPfondo;
    private JButton btnPagarEco;
    private JButton btnVolverEco;
    private JPanel jpEconomico;
    private JComboBox cbxDia;
    private JComboBox cbxTipoMenu;
    private JButton btnCambiar;
    private JComboBox cbxPan;
    private JComboBox cbxSopa;
    private JLabel lblSopa;
    private JLabel lblAcompañamiento;
    private ServicioPedidos servicioPedidos;
    private Usuario usuario;
    private Cliente cliente;
    private Pagos pago;

    private ManejoMetodos manejoMetodos;

    public MenuCompra(Usuario usuario) throws HeadlessException {
        this.usuario = usuario;
        this.servicioPedidos = new ServicioPedidos();
        this.pago = new Pagos();
        this.cliente = new Cliente(usuario.getCorreoElectronico(), usuario.getContraseña());
        this.manejoMetodos = new ManejoMetodos(servicioPedidos, pago);
    }

    public void PantallaMenuCompra() {
        setSize(700, 700);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(jpEconomico);
        setVisible(true);
        jpEconomico.setFocusable(true);
        jpEconomico.requestFocusInWindow();
        btnVolverEco.addActionListener(this);
        btnPagarEco.addActionListener(this);
        btnCambiar.addActionListener(this);
        llamarSupremo();
    }

    public void llamarSupremo() {
        manejoMetodos.llamarSupremo(cbxDia, cbxTipoMenu, cbxBebestible, cbxPan, cbxSopa, cbxPostre, cbxPfondo, cbxEnsalada);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolverEco) {
            Comprar elegirDiaSemana = new Comprar(usuario);
            elegirDiaSemana.PantallaCompra();
            setVisible(false);
        } else if (e.getSource() == btnCambiar) {
            llamarSupremo();
            JOptionPane.showMessageDialog(null, "Cambios realizados");
        } else if (e.getSource() == btnPagarEco) {
            String seleccionPedido = manejoMetodos.seleccionarMenuDetalles(cbxBebestible, cbxPfondo, cbxEnsalada, cbxPostre, cbxSopa, cbxPan);

            // Llamar pago
            Pago pagar = new Pago(usuario, pago, seleccionPedido, servicioPedidos, (String) cbxDia.getSelectedItem(), cliente);
            pagar.Pantalla();
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
