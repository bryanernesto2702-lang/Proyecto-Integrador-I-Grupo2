package pe.edu.utp.dao;

import pe.edu.utp.config.ConexionBD;
import pe.edu.utp.model.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImpl implements VentaDAO {

    @Override
    public void guardar(Venta venta) {

        String sql = """
                INSERT INTO ventas
                (cliente,producto_id,cantidad,precio,usuario_id)
                VALUES(?,?,?,?,?)
                """;

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setString(1, venta.getCliente());
            ps.setInt(2, venta.getProductoId());
            ps.setInt(3, venta.getCantidad());
            ps.setDouble(4, venta.getPrecio());
            ps.setInt(5, venta.getUsuarioId());

            ps.executeUpdate();

            ps.close();
            conexion.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public List<Venta> listar() {

        List<Venta> lista = new ArrayList<>();

        String sql = """
                SELECT
                    v.id,
                    v.cliente,
                    p.nombre AS producto,
                    v.producto_id,
                    v.cantidad,
                    v.precio,
                    v.fecha,
                    v.usuario_id
                FROM ventas v
                INNER JOIN productos p
                    ON v.producto_id = p.id
                ORDER BY v.fecha DESC
                """;

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Venta venta = new Venta();

                venta.setId(rs.getInt("id"));
                venta.setCliente(rs.getString("cliente"));
                venta.setProductoId(rs.getInt("producto_id"));
                venta.setNombreProducto(rs.getString("producto"));
                venta.setCantidad(rs.getInt("cantidad"));
                venta.setPrecio(rs.getDouble("precio"));
                venta.setFecha(rs.getString("fecha"));
                venta.setUsuarioId(rs.getInt("usuario_id"));

                lista.add(venta);

            }

            rs.close();
            ps.close();
            conexion.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;

    }

}