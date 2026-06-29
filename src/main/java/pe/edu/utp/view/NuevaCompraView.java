package pe.edu.utp.view;

import pe.edu.utp.dao.CompraDAOImpl;
import pe.edu.utp.dao.ProductoDAOImpl;
import pe.edu.utp.dao.MovimientoInventarioDAOImpl;
import pe.edu.utp.model.Compra;
import pe.edu.utp.model.MovimientoInventario;
import pe.edu.utp.model.Producto;

import javax.swing.*;
import java.awt.*;
import pe.edu.utp.config.Sesion;

public class NuevaCompraView extends JDialog {

    private JTextField txtProveedor;
    private JComboBox<Producto> cboProducto;
    private JTextField txtCantidad;
    private JTextField txtPrecio;

    private JButton btnGuardar;

    public NuevaCompraView(JFrame parent){

        super(parent,"Nueva Compra",true);

        setSize(450,350);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5,2,10,10));

        add(new JLabel("Proveedor"));
        txtProveedor = new JTextField();
        add(txtProveedor);

        add(new JLabel("Producto"));

        cboProducto = new JComboBox<>();

        ProductoDAOImpl productoDAO = new ProductoDAOImpl();

        for(Producto p : productoDAO.listar()){

            cboProducto.addItem(p);

        }

        add(cboProducto);

        add(new JLabel("Cantidad"));
        txtCantidad = new JTextField();
        add(txtCantidad);

        add(new JLabel("Precio"));
        txtPrecio = new JTextField();
        add(txtPrecio);

        btnGuardar = new JButton("Registrar Compra");

        add(new JLabel());
        add(btnGuardar);

        btnGuardar.addActionListener(e -> registrarCompra());

    }

    private void registrarCompra(){

        try{

            Producto producto =
                    (Producto) cboProducto.getSelectedItem();

            int cantidad =
                    Integer.parseInt(txtCantidad.getText());

            double precio =
                    Double.parseDouble(txtPrecio.getText());

            Compra compra = new Compra();

            compra.setProveedor(txtProveedor.getText());
            compra.setProductoId(producto.getId());
            compra.setCantidad(cantidad);
            compra.setPrecio(precio);
            compra.setUsuarioId(
                    Sesion.getUsuarioActual().getId()
            );

            CompraDAOImpl compraDAO =
                    new CompraDAOImpl();

            compraDAO.guardar(compra);

            producto.setStock(
                    producto.getStock()+cantidad
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
            movimiento.setTipo("ENTRADA");
            movimiento.setUsuarioId(
                    Sesion.getUsuarioActual().getId()
            );

            MovimientoInventarioDAOImpl movimientoDAO =
                    new MovimientoInventarioDAOImpl();

            movimientoDAO.registrar(movimiento);

            JOptionPane.showMessageDialog(
                    this,
                    "Compra registrada correctamente."
            );

            dispose();

        }catch(Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Error al registrar compra."
            );

            e.printStackTrace();

        }

    }

}
