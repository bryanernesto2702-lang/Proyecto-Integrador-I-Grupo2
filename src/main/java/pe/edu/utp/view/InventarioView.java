package pe.edu.utp.view;

import pe.edu.utp.model.Producto;

import javax.swing.*;
import java.awt.*;

public class InventarioView extends JFrame {

    private JTextField txtSku;
    private JTextField txtNombre;
    private JTextField txtCategoria;
    private JTextArea areaResultado;

    public InventarioView() {

        setTitle("Sistema de Inventarios Multitop");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("SKU:"));
        txtSku = new JTextField();
        panel.add(txtSku);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        panel.add(txtCategoria);

        JButton btnRegistrar = new JButton("Registrar Producto");
        panel.add(btnRegistrar);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrarProducto());
    }

    private void registrarProducto() {

        Producto producto = new Producto(
                txtSku.getText(),
                txtNombre.getText(),
                txtCategoria.getText()
        );

        areaResultado.setText(
                "Producto registrado correctamente\n\n" +
                        "SKU: " + producto.getSku() + "\n" +
                        "Nombre: " + producto.getNombre() + "\n" +
                        "Categoría: " + producto.getCategoria()
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new InventarioView().setVisible(true);
        });

    }
}