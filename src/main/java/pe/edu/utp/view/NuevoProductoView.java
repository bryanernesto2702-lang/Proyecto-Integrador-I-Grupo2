package pe.edu.utp.view;

import pe.edu.utp.dao.ProductoDAOImpl;
import pe.edu.utp.model.Producto;

import javax.swing.*;
import java.awt.*;

public class NuevoProductoView extends JDialog {

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtCategoria;
    private JTextField txtPrecio;
    private JTextField txtStock;

    private JButton btnGuardar;
    private JButton btnCancelar;

    // Si es null -> Nuevo Producto
    // Si tiene datos -> Editar Producto
    private Producto productoEditar = null;

    public NuevoProductoView(JFrame parent) {

        super(parent, "Nuevo Producto", true);

        setSize(400,350);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6,2,10,10));

        add(new JLabel("Código"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Categoría"));
        txtCategoria = new JTextField();
        add(txtCategoria);

        add(new JLabel("Precio"));
        txtPrecio = new JTextField();
        add(txtPrecio);

        add(new JLabel("Stock"));
        txtStock = new JTextField();
        add(txtStock);

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        add(btnGuardar);
        add(btnCancelar);

        btnCancelar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> guardarProducto());

    }

    // Constructor para editar
    public NuevoProductoView(JFrame parent, Producto producto) {

        this(parent);

        this.productoEditar = producto;

        setTitle("Editar Producto");

        txtCodigo.setText(producto.getCodigo());
        txtNombre.setText(producto.getNombre());
        txtCategoria.setText(producto.getCategoria());
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        txtStock.setText(String.valueOf(producto.getStock()));

        btnGuardar.setText("Actualizar");

    }

    private void guardarProducto() {

        try {

            ProductoDAOImpl dao = new ProductoDAOImpl();

            if (productoEditar == null) {

                // NUEVO

                Producto producto = new Producto();

                producto.setCodigo(txtCodigo.getText());
                producto.setNombre(txtNombre.getText());
                producto.setCategoria(txtCategoria.getText());
                producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
                producto.setStock(Integer.parseInt(txtStock.getText()));

                dao.guardar(producto);

                JOptionPane.showMessageDialog(
                        this,
                        "Producto registrado correctamente."
                );

            } else {

                // EDITAR

                productoEditar.setCodigo(txtCodigo.getText());
                productoEditar.setNombre(txtNombre.getText());
                productoEditar.setCategoria(txtCategoria.getText());
                productoEditar.setPrecio(Double.parseDouble(txtPrecio.getText()));
                productoEditar.setStock(Integer.parseInt(txtStock.getText()));

                dao.actualizar(productoEditar);

                JOptionPane.showMessageDialog(
                        this,
                        "Producto actualizado correctamente."
                );

            }

            dispose();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Precio y Stock deben ser numéricos."
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al guardar el producto."
            );

            ex.printStackTrace();

        }

    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtCategoria() {
        return txtCategoria;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JTextField getTxtStock() {
        return txtStock;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

}