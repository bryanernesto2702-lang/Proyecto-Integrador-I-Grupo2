package pe.edu.utp.config;

import pe.edu.utp.model.Usuario;

public class Sesion {

    private static Usuario usuarioActual;

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

}