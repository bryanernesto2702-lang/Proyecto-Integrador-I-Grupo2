package pe.edu.utp.dao;

import pe.edu.utp.config.ConexionBD;
import pe.edu.utp.model.MovimientoInventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovimientoInventarioDAOImpl implements MovimientoInventarioDAO {

    @Override
    public void registrar(MovimientoInventario movimiento) {

        String sql = """
                INSERT INTO movimientos_inventario
                (producto_id,tipo,cantidad,usuario_id)
                VALUES(?,?,?,?)
                """;

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setInt(1, movimiento.getProductoId());
            ps.setString(2, movimiento.getTipo());
            ps.setInt(3, movimiento.getCantidad());
            ps.setInt(4, movimiento.getUsuarioId());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public List<MovimientoInventario> listar() {

        List<MovimientoInventario> lista =
                new ArrayList<>();

        String sql = "SELECT * FROM movimientos_inventario";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                MovimientoInventario m =
                        new MovimientoInventario();

                m.setId(rs.getInt("id"));
                m.setProductoId(rs.getInt("producto_id"));
                m.setTipo(rs.getString("tipo"));
                m.setCantidad(rs.getInt("cantidad"));
                m.setFecha(rs.getString("fecha"));
                m.setUsuarioId(rs.getInt("usuario_id"));

                lista.add(m);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;

    }

}