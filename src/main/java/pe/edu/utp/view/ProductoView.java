package pe.edu.utp.view;

import pe.edu.utp.dao.ProductoDAOImpl;
import pe.edu.utp.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductoView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    public ProductoView() {

        setTitle("Gestión de Productos");
        setSize(900,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("GESTIÓN DE PRODUCTOS");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        panel.add(titulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Categoría");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");

        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);

        panel.add(scroll, BorderLayout.CENTER);

        JPanel botones = new JPanel();

        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.addActionListener(e -> {

            NuevoProductoView ventana = new NuevoProductoView(this);

            ventana.setVisible(true);

            cargarProductos();

        });
        JButton btnEditar = new JButton("Editar");

        btnEditar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un producto."
                );

                return;
            }

            int id = Integer.parseInt(
                    modelo.getValueAt(fila, 0).toString()
            );

            ProductoDAOImpl dao = new ProductoDAOImpl();

            Producto producto = dao.buscarPorId(id);

            NuevoProductoView ventana =
                    new NuevoProductoView(this, producto);

            ventana.setVisible(true);

            cargarProductos();

        });
        JButton btnEliminar = new JButton("Eliminar");

        btnEliminar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un producto."
                );

                return;
            }

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea eliminar este producto?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                int id = Integer.parseInt(
                        modelo.getValueAt(fila, 0).toString()
                );

                ProductoDAOImpl dao = new ProductoDAOImpl();

                dao.eliminar(id);

                cargarProductos();

                JOptionPane.showMessageDialog(
                        this,
                        "Producto eliminado correctamente."
                );

            }

        });

        botones.add(btnNuevo);
        botones.add(btnEditar);
        botones.add(btnEliminar);


        panel.add(botones, BorderLayout.SOUTH);

        add(panel);

        cargarProductos();
    }

    private void cargarProductos() {

        modelo.setRowCount(0);

        ProductoDAOImpl dao = new ProductoDAOImpl();

        for (Producto p : dao.listar()) {

            modelo.addRow(new Object[]{

                    p.getId(),
                    p.getCodigo(),
                    p.getNombre(),
                    p.getCategoria(),
                    p.getPrecio(),
                    p.getStock()

            });

        }

    }

}