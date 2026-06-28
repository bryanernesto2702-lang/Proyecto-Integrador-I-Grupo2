package pe.edu.utp.view;

import pe.edu.utp.dao.ProductoDAOImpl;
import pe.edu.utp.model.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import pe.edu.utp.dao.MovimientoInventarioDAOImpl;
import pe.edu.utp.model.MovimientoInventario;

public class InventarioView extends JFrame {

    private JComboBox<Producto> cboProducto;
    private JRadioButton rbEntrada;
    private JRadioButton rbSalida;
    private JTextField txtCantidad;
    private JButton btnRegistrar;

    public InventarioView() {

        setTitle("Movimiento de Inventario");
        setSize(450,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(5,2,10,10));

        add(new JLabel("Producto"));

        cboProducto = new JComboBox<>();

        ProductoDAOImpl dao = new ProductoDAOImpl();

        List<Producto> productos = dao.listar();

        for(Producto p : productos){

            cboProducto.addItem(p);

        }

        add(cboProducto);

        add(new JLabel("Tipo"));

        JPanel panelTipo = new JPanel();

        rbEntrada = new JRadioButton("Entrada",true);
        rbSalida = new JRadioButton("Salida");

        ButtonGroup grupo = new ButtonGroup();

        grupo.add(rbEntrada);
        grupo.add(rbSalida);

        panelTipo.add(rbEntrada);
        panelTipo.add(rbSalida);

        add(panelTipo);

        add(new JLabel("Cantidad"));

        txtCantidad = new JTextField();

        add(txtCantidad);

        add(new JLabel());

        btnRegistrar = new JButton("Registrar Movimiento");

        btnRegistrar.addActionListener(e -> registrarMovimiento());
        add(btnRegistrar);


    }
    private void registrarMovimiento(){

        try{

            Producto producto =
                    (Producto) cboProducto.getSelectedItem();

            int cantidad =
                    Integer.parseInt(txtCantidad.getText());

            ProductoDAOImpl productoDAO =
                    new ProductoDAOImpl();

            MovimientoInventarioDAOImpl movimientoDAO =
                    new MovimientoInventarioDAOImpl();

            if(rbEntrada.isSelected()){

                producto.setStock(
                        producto.getStock()+cantidad
                );

            }else{

                if(cantidad>producto.getStock()){

                    JOptionPane.showMessageDialog(
                            this,
                            "No hay suficiente stock."
                    );

                    return;

                }

                producto.setStock(
                        producto.getStock()-cantidad
                );

            }

            productoDAO.actualizarStock(
                    producto.getId(),
                    producto.getStock()
            );

            MovimientoInventario movimiento =
                    new MovimientoInventario();

            movimiento.setProductoId(producto.getId());

            movimiento.setCantidad(cantidad);

            movimiento.setTipo(
                    rbEntrada.isSelected()
                            ? "ENTRADA"
                            : "SALIDA"
            );

            movimiento.setUsuarioId(1);

            movimientoDAO.registrar(movimiento);

            JOptionPane.showMessageDialog(
                    this,
                    "Movimiento registrado."
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
