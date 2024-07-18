package Dominio;

public class Usuario {
    private String correoElectronico;
    private String contraseña;
    private TipoUsuario tipo;

    public Usuario(String correoElectronico, String contraseña, TipoUsuario tipo) {
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}