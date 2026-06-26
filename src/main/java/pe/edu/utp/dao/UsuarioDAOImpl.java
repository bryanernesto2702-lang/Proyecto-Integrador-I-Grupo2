package pe.edu.utp.dao;

import pe.edu.utp.config.ConexionBD;
import pe.edu.utp.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAOImpl implements UsuarioDAO {

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

                return u;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

}