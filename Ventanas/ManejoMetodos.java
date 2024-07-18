package Ventanas;

import Dominio.*;

import javax.swing.*;

public class ManejoMetodos {
    private ServicioPedidos servicioPedidos;
    private Pagos pagos;


    public ManejoMetodos(ServicioPedidos servicioPedidos, Pagos pagos) {
        this.servicioPedidos = servicioPedidos;
        this.pagos = pagos;
    }

    public String procesarPagoDos(String rut, String codigoBaes) {
        if (pagos.verificarPago(rut, codigoBaes)) {
            JOptionPane.showMessageDialog(null, "Pago verificado.");
            return Integer.toString(servicioPedidos.getNumeroRetiro());
        } else {
            return null;
        }
    }

    public String actualizarPedido(String dia, Cliente cliente, String pedido) {
        try {
            return "" + servicioPedidos.actualizarJsonDia(dia, cliente, pedido);
        } catch (Exception e) {
            System.out.println("Error al leer los almuerzos comprados: " + e.getMessage());
            return null;
        }
    }

    public void llamarSupremo(JComboBox cbxDia, JComboBox cbxTipoMenu, JComboBox cbxBebestible, JComboBox cbxPan, JComboBox cbxSopa, JComboBox cbxPostre, JComboBox cbxPfondo, JComboBox cbxEnsalada) {
        Menu menu = servicioPedidos.obtenerMenu((String) cbxDia.getSelectedItem(), (String) cbxTipoMenu.getSelectedItem());
        llamarbebestible(menu, cbxBebestible);
        llamarpan(menu, cbxPan);
        llamarSopa(menu, cbxSopa);
        llamarPostre(menu, cbxPostre);
        llamarPlatoFondo(menu, cbxPfondo);
        llamarEnsalada(menu, cbxEnsalada);
    }

    public void llamarbebestible(Menu menu, JComboBox cbxBebestible) {
        cbxBebestible.removeAllItems();
        String[] bebidasMenu = menu.getBebestibles();
        if (bebidasMenu != null) {
            for (String bebida : bebidasMenu) {
                cbxBebestible.addItem(bebida);
            }
        }
    }

    public void llamarpan(Menu menu, JComboBox cbxPan) {
        cbxPan.removeAllItems();
        String[] panMenu = menu.getAcompañamiento();
        if (panMenu != null) {
            for (String pan : panMenu) {
                cbxPan.addItem(pan);
            }
        }
    }
    public void llamarSopa(Menu menu, JComboBox cbxSopa) {
        cbxSopa.removeAllItems();
        String[] sopaMenu = menu.getSopa();
        if (sopaMenu != null) {
            for (String sopa : sopaMenu) {
                cbxSopa.addItem(sopa);
            }
        }
    }

    public void llamarPostre(Menu menu, JComboBox cbxPostre) {
        cbxPostre.removeAllItems();
        String[] postreMenu = menu.getPostre();
        if (postreMenu != null) {
            for (String postre : postreMenu) {
                cbxPostre.addItem(postre);
            }
        }
    }

    public void llamarPlatoFondo(Menu menu, JComboBox cbxPfondo) {
        cbxPfondo.removeAllItems();
        String[] platoFondoMenu = menu.getPlatoDeFondo();
        if (platoFondoMenu != null) {
            for (String platoFondo : platoFondoMenu) {
                cbxPfondo.addItem(platoFondo);
            }
        }
    }

    public void llamarEnsalada(Menu menu, JComboBox cbxEnsalada) {
        cbxEnsalada.removeAllItems();
        String[] ensaladaMenu = menu.getEnsalada();
        if (ensaladaMenu != null) {
            for (String ensalada : ensaladaMenu) {
                cbxEnsalada.addItem(ensalada);
            }
        }
    }

    public String seleccionarMenuDetalles(JComboBox cbxBebestible, JComboBox cbxPfondo, JComboBox cbxEnsalada, JComboBox cbxPostre, JComboBox cbxSopa, JComboBox cbxPan) {
        return servicioPedidos.seleccionarMenuDetalles(
                (String) cbxBebestible.getSelectedItem(),
                (String) cbxPfondo.getSelectedItem(),
                (String) cbxEnsalada.getSelectedItem(),
                (String) cbxPostre.getSelectedItem(),
                (String) cbxSopa.getSelectedItem(),
                (String) cbxPan.getSelectedItem()
        );
    }
}

