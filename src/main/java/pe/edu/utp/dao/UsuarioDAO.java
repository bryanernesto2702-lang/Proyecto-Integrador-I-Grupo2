package pe.edu.utp.dao;

import pe.edu.utp.model.Usuario;

public interface UsuarioDAO {

    Usuario iniciarSesion(String usuario, String contrasena);

}