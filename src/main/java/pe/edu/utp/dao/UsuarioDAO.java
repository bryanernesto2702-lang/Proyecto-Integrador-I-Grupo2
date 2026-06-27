package pe.edu.utp.dao;

import pe.edu.utp.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    // Login
    Usuario iniciarSesion(String usuario, String contrasena);

    // CRUD
    void guardar(Usuario usuario);

    void actualizar(Usuario usuario);

    void eliminar(int id);

    List<Usuario> listar();

}