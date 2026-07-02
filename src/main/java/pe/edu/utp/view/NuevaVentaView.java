package pe.edu.utp.view;

import pe.edu.utp.config.Sesion;
import pe.edu.utp.dao.MovimientoInventarioDAOImpl;
import pe.edu.utp.dao.ProductoDAOImpl;
import pe.edu.utp.dao.VentaDAOImpl;
import pe.edu.utp.model.MovimientoInventario;
import pe.edu.utp.model.Producto;
import pe.edu.utp.model.Venta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NuevaVentaView extends JDialog {

    private JTextField txtCliente;
    private JComboBox<Producto> cboProducto;
    private JTextField txtCantidad;
    private JTextField txtPrecio;

    private JButton btnGuardar;

    public NuevaVentaView(JFrame parent) {

        super(parent, "Nueva Venta", true);

        setSize(450,350);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5,2,10,10));

        add(new JLabel("Cliente"));

        txtCliente = new JTextField();
        add(txtCliente);

        add(new JLabel("Producto"));

        cboProducto = new JComboBox<>();

        ProductoDAOImpl productoDAO = new ProductoDAOImpl();

        List<Producto> productos = productoDAO.listar();

        for(Producto p : productos){

            cboProducto.addItem(p);

        }

        add(cboProducto);

        add(new JLabel("Cantidad"));

        txtCantidad = new JTextField();
        add(txtCantidad);

        add(new JLabel("Precio"));

        txtPrecio = new JTextField();
        add(txtPrecio);

        add(new JLabel());

        btnGuardar = new JButton("Registrar Venta");
        add(btnGuardar);

        btnGuardar.addActionListener(e -> registrarVenta());

    }

    private void registrarVenta(){

        try{

            Producto producto =
                    (Producto)cboProducto.getSelectedItem();

            int cantidad =
                    Integer.parseInt(txtCantidad.getText());

            if(cantidad > producto.getStock()){

                JOptionPane.showMessageDialog(
                        this,
                        "No hay suficiente stock."
                );

                return;

            }

            double precio =
                    Double.parseDouble(txtPrecio.getText());

            Venta venta = new Venta();

            venta.setCliente(txtCliente.getText());
            venta.setProductoId(producto.getId());
            venta.setCantidad(cantidad);
            venta.setPrecio(precio);
            venta.setUsuarioId(
                    Sesion.getUsuarioActual().getId()
            );

            VentaDAOImpl ventaDAO =
                    new VentaDAOImpl();

            ventaDAO.guardar(venta);

            producto.setStock(
                    producto.getStock()-cantidad
            );

            ProductoDAOImpl productoDAO =
                    new ProductoDAOImpl();

            productoDAO.actualizarStock(
                    producto.getId(),
                    producto.getStock()
            );

            MovimientoInventario movimiento =
                    new MovimientoInventario();

            movimiento.setProductoId(producto.getId());
            movimiento.setCantidad(cantidad);
            movimiento.setTipo("SALIDA");
            movimiento.setUsuarioId(
                    Sesion.getUsuarioActual().getId()
            );

            MovimientoInventarioDAOImpl movimientoDAO =
                    new MovimientoInventarioDAOImpl();

            movimientoDAO.registrar(movimiento);

            JOptionPane.showMessageDialog(
                    this,
                    "Venta registrada correctamente."
            );

            dispose();

        }catch(Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Datos incorrectos."
            );

            e.printStackTrace();

        }

    }

}