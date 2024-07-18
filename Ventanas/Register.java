package Ventanas;

import Dominio.ControladorLogin;
import Dominio.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Register extends JFrame implements ActionListener, FocusListener {
    private JPanel jpRegister;
    private JPasswordField pfPassRegi;
    private JTextField txfEmailRegi;
    private JLabel lblRegi;
    private JButton btnVolverRegi;
    private JButton btnConfirmarRegi;
    private Usuario usuario;
    public Register(Usuario usuario) {

        this.usuario = usuario;
    }
    public void PantallaRegi(){
        setSize(500,500);
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(jpRegister);
        setVisible(true);
        jpRegister.setFocusable(true);
        jpRegister.requestFocusInWindow();
        btnVolverRegi.addActionListener(this);
        btnConfirmarRegi.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnVolverRegi){
            Login login = new Login();
            login.Pantalla();
            setVisible(false);
        }
        else if(e.getSource() == btnConfirmarRegi){
            String correo = txfEmailRegi.getText();
            String contraseña = String.valueOf(pfPassRegi.getPassword());
            ControladorLogin cl = new ControladorLogin();
            Boolean result = cl.registrarNuevoUsuario(correo,contraseña);
            if(result){
                JOptionPane.showMessageDialog(this,"Registro exitoso!");
                Login login = new Login();
                login.Pantalla();
                setVisible(false);
            }
            else{
                JOptionPane.showMessageDialog(this,"Error al registrar usuario!");
            }
        }

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
