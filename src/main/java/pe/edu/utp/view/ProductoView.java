package pe.edu.utp.view;

import pe.edu.utp.dao.ProductoDAOImpl;
import pe.edu.utp.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ProductoView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtBuscar;

    public ProductoView() {

        setTitle("Gestión de Productos");
        setSize(900,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelSuperior.add(new JLabel("🔍 Buscar:"));

        txtBuscar = new JTextField(30);

        panelSuperior.add(txtBuscar);

        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarProductos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarProductos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarProductos();
            }

        });

        JLabel titulo = new JLabel("📦 GESTIÓN DE PRODUCTOS");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel norte = new JPanel(new BorderLayout());

        norte.add(titulo, BorderLayout.NORTH);
        norte.add(panelSuperior, BorderLayout.SOUTH);

        panel.add(norte, BorderLayout.NORTH);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Categoría");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");

        tabla = new JTable(modelo);
        tabla.setRowHeight(28);

        tabla.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        tabla.getTableHeader().setBackground(
                new Color(33,150,243)
        );

        tabla.getTableHeader().setForeground(Color.BLACK);

        tabla.setSelectionBackground(
                new Color(187,222,251)
        );

        tabla.setGridColor(Color.LIGHT_GRAY);

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
        btnNuevo.setBackground(new Color(76,175,80));
        btnNuevo.setForeground(Color.WHITE);

        btnEditar.setBackground(new Color(255,193,7));
        btnEditar.setForeground(Color.BLACK);

        btnEliminar.setBackground(new Color(244,67,54));
        btnEliminar.setForeground(Color.WHITE);

        btnNuevo.setFocusPainted(false);
        btnEditar.setFocusPainted(false);
        btnEliminar.setFocusPainted(false);

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
    private void buscarProductos() {

        String texto = txtBuscar.getText().trim();

        if (texto.isEmpty()) {

            cargarProductos();
            return;

        }

        modelo.setRowCount(0);

        ProductoDAOImpl dao = new ProductoDAOImpl();

        for (Producto p : dao.buscar(texto)) {

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