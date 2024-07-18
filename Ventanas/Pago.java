package Ventanas;

import Dominio.Cliente;
import Dominio.Pagos;
import Dominio.ServicioPedidos;
import Dominio.Usuario;
import Utilidadess.Validador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Pago extends JFrame implements ActionListener, FocusListener {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton btnPagar;
    private JLabel lbRut;
    private JLabel lbCodigoBaes;
    private JButton btnVolver;
    private JPanel jpPagos;

    private String pedido;
    private ServicioPedidos servicioPedidos;
    private Cliente cliente;
    private Pagos pagos;
    private Usuario usuario;
    private String dia;

    private ManejoMetodos manejoMetodos;

    public Pago(Usuario usu, Pagos pag, String pedido, ServicioPedidos servicioPedido, String dia, Cliente cli) {
        this.pagos = pag;
        this.pedido = pedido;
        this.servicioPedidos = servicioPedido;
        this.dia = dia;
        this.cliente = cli;
        this.usuario = usu;
        this.manejoMetodos = new ManejoMetodos(servicioPedido, pag);
    }

    public void Pantalla() {
        setSize(500, 500);
        setTitle("Pago");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(jpPagos);
        setVisible(true);
        jpPagos.setFocusable(true);
        jpPagos.requestFocusInWindow();
        btnPagar.addActionListener(this);
        btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPagar) {
            String rut = textField1.getText();
            String codigoBaes = new String(passwordField1.getPassword());
            String pagosRealizados = manejoMetodos.procesarPagoDos(rut, codigoBaes);
            Validador validadores = new Validador();
            if (!validadores.esRutValido(rut)) {
                JOptionPane.showMessageDialog(this, "El RUT ingresado no es válido. Formato esperado: 11111111-1");
            }

           else  if (!validadores.esCodigoBaesValido(codigoBaes)) {
                JOptionPane.showMessageDialog(this, "El código BAES ingresado no es válido. Debe contener exactamente 4 dígitos.");
            }

          else if (pagosRealizados != null && !pagosRealizados.isEmpty()) {
                pagosRealizados = manejoMetodos.actualizarPedido(dia, cliente, pedido);
                Ticket ticket = new Ticket(usuario, "Tickets", pagosRealizados, pedido);
                ticket.Pantalla();
                setVisible(false);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Pago no verificado o no disponible, no se puede agregar el pedido. Inténtelo nuevamente.");
          }

        } else if (e.getSource() == btnVolver) {
            MenuCompra menuCompra = new MenuCompra(usuario);
            menuCompra.PantallaMenuCompra();
            this.dispose();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
}
