package pe.edu.utp.dao;

import pe.edu.utp.config.ConexionBD;
import pe.edu.utp.model.Compra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompraDAOImpl implements CompraDAO {

    @Override
    public void guardar(Compra compra) {

        String sql = """
                INSERT INTO compras
                (proveedor,producto_id,cantidad,precio,usuario_id)
                VALUES(?,?,?,?,?)
                """;

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setString(1, compra.getProveedor());
            ps.setInt(2, compra.getProductoId());
            ps.setInt(3, compra.getCantidad());
            ps.setDouble(4, compra.getPrecio());
            ps.setInt(5, compra.getUsuarioId());

            ps.executeUpdate();

            ps.close();
            conexion.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public List<Compra> listar() {

        List<Compra> lista = new ArrayList<>();

        String sql = """
        SELECT
            c.id,
            c.proveedor,
            p.nombre AS producto,
            c.producto_id,
            c.cantidad,
            c.precio,
            c.fecha,
            c.usuario_id
        FROM compras c
        INNER JOIN productos p
            ON c.producto_id = p.id
        """;

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Compra compra = new Compra();

                compra.setId(rs.getInt("id"));
                compra.setProveedor(rs.getString("proveedor"));
                compra.setProductoId(rs.getInt("producto_id"));
                compra.setNombreProducto(rs.getString("producto"));
                compra.setCantidad(rs.getInt("cantidad"));
                compra.setPrecio(rs.getDouble("precio"));
                compra.setFecha(rs.getString("fecha"));
                compra.setUsuarioId(rs.getInt("usuario_id"));

                lista.add(compra);

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