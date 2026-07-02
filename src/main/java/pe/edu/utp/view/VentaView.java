package pe.edu.utp.view;

import pe.edu.utp.dao.VentaDAOImpl;
import pe.edu.utp.model.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentaView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    public VentaView() {

        setTitle("Gestión de Ventas");
        setSize(900,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("GESTIÓN DE VENTAS");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        panel.add(titulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Fecha");

        tabla = new JTable(modelo);
        tabla.setRowHeight(28);

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel botones = new JPanel();

        JButton btnNueva = new JButton("Nueva Venta");

        btnNueva.setBackground(new Color(76,175,80));
        btnNueva.setForeground(Color.WHITE);
        btnNueva.setFocusPainted(false);

        btnNueva.addActionListener(e -> {

            NuevaVentaView ventana =
                    new NuevaVentaView(this);

            ventana.setVisible(true);

            cargarVentas();

        });

        botones.add(btnNueva);

        panel.add(botones, BorderLayout.SOUTH);

        add(panel);

        cargarVentas();

    }

    private void cargarVentas() {

        modelo.setRowCount(0);

        VentaDAOImpl dao = new VentaDAOImpl();

        for (Venta v : dao.listar()) {

            modelo.addRow(new Object[]{

                    v.getId(),
                    v.getCliente(),
                    v.getNombreProducto(),
                    v.getCantidad(),
                    String.format("S/. %.2f", v.getPrecio()),
                    v.getFecha()

            });

        }

    }

}
