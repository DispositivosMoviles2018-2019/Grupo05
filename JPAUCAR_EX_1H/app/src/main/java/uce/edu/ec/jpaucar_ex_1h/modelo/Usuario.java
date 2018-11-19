package uce.edu.ec.jpaucar_ex_1h.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
 private String nombreUsuario;
 private String contrasena;

    public Usuario() {
    }

    public Usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return nombreUsuario + ";" +contrasena;

    }
}
