package pe.edu.utp.view;

import pe.edu.utp.dao.CompraDAOImpl;
import pe.edu.utp.model.Compra;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CompraView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    public CompraView() {

        setTitle("Gestión de Compras");
        setSize(900,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("GESTIÓN DE COMPRAS");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        panel.add(titulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Proveedor");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Fecha");

        tabla = new JTable(modelo);
        tabla.setRowHeight(28);

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel botones = new JPanel();

        JButton btnNueva = new JButton("Nueva Compra");

        btnNueva.setBackground(new Color(76,175,80));
        btnNueva.setForeground(Color.WHITE);
        btnNueva.setFocusPainted(false);

        btnNueva.addActionListener(e -> {

            NuevaCompraView ventana =
                    new NuevaCompraView(this);

            ventana.setVisible(true);

            cargarCompras();

        });

        botones.add(btnNueva);

        panel.add(botones, BorderLayout.SOUTH);

        add(panel);

        cargarCompras();

    }

    private void cargarCompras(){

        modelo.setRowCount(0);

        CompraDAOImpl dao = new CompraDAOImpl();

        for(Compra c : dao.listar()){

            modelo.addRow(new Object[]{

                    c.getId(),
                    c.getProveedor(),
                    c.getNombreProducto(),
                    c.getCantidad(),
                    String.format("S/. %.2f", c.getPrecio()),
                    c.getFecha()

            });

        }

    }

}