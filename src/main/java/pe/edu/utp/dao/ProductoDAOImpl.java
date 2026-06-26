package pe.edu.utp.dao;

import pe.edu.utp.config.ConexionBD;
import pe.edu.utp.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public void guardar(Producto producto) {

        String sql = "INSERT INTO productos(codigo,nombre,categoria,precio,stock) VALUES(?,?,?,?,?)";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getCategoria());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getStock());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public void actualizar(Producto producto) {

        String sql = "UPDATE productos SET codigo=?,nombre=?,categoria=?,precio=?,stock=? WHERE id=?";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getCategoria());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getStock());
            ps.setInt(6, producto.getId());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public void eliminar(int id) {

        String sql = "DELETE FROM productos WHERE id=?";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public List<Producto> listar() {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM productos";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps = conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Producto producto = new Producto();

                producto.setId(rs.getInt("id"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));

                lista.add(producto);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;

    }
    public Producto buscarPorId(int id) {

        String sql = "SELECT * FROM productos WHERE id=?";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Producto p = new Producto();

                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));

                return p;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

}