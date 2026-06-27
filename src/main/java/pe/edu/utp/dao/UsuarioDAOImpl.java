package pe.edu.utp.dao;

import pe.edu.utp.config.ConexionBD;
import pe.edu.utp.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    // ==========================
    // LOGIN
    // ==========================

    @Override
    public Usuario iniciarSesion(String usuario, String contrasena) {

        String sql =
                "SELECT * FROM usuarios WHERE usuario=? AND contrasena=?";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Usuario u = new Usuario();

                u.setId(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setContrasena(rs.getString("contrasena"));
                u.setNombre(rs.getString("nombre"));
                u.setRol(rs.getString("rol"));

                return u;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    // ==========================
    // GUARDAR
    // ==========================

    @Override
    public void guardar(Usuario usuario) {

        String sql =
                "INSERT INTO usuarios(usuario,contrasena,nombre,rol) VALUES(?,?,?,?)";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getRol());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ==========================
    // ACTUALIZAR
    // ==========================

    @Override
    public void actualizar(Usuario usuario) {

        String sql =
                "UPDATE usuarios SET usuario=?,contrasena=?,nombre=?,rol=? WHERE id=?";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getRol());
            ps.setInt(5, usuario.getId());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ==========================
    // ELIMINAR
    // ==========================

    @Override
    public void eliminar(int id) {

        String sql = "DELETE FROM usuarios WHERE id=?";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ==========================
    // LISTAR
    // ==========================

    @Override
    public List<Usuario> listar() {

        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setRol(rs.getString("rol"));

                lista.add(usuario);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;

    }

    public Usuario buscarPorId(int id){

        String sql = "SELECT * FROM usuarios WHERE id=?";

        try{

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                Usuario u = new Usuario();

                u.setId(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setContrasena(rs.getString("contrasena"));
                u.setNombre(rs.getString("nombre"));
                u.setRol(rs.getString("rol"));

                return u;

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return null;

    }

}