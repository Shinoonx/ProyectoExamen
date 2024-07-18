package Dominio;

import java.util.HashMap;

public class Cliente extends Usuario {
    private HashMap<String, String> almuerzosComprados;

    public Cliente(String correoElectronico, String contraseña) {
        super(correoElectronico, contraseña, TipoUsuario.CLIENTE);
        this.almuerzosComprados = new HashMap<>();
    }

}