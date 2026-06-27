package pe.edu.utp.view;

import pe.edu.utp.dao.UsuarioDAOImpl;
import pe.edu.utp.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UsuarioView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    public UsuarioView() {

        setTitle("Gestión de Usuarios");
        setSize(850,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("👤 GESTIÓN DE USUARIOS");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        panel.add(titulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Rol");

        tabla = new JTable(modelo);

        tabla.setRowHeight(28);

        JScrollPane scroll = new JScrollPane(tabla);

        panel.add(scroll, BorderLayout.CENTER);

        JPanel botones = new JPanel();

        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.addActionListener(e -> {

            NuevoUsuarioView ventana =
                    new NuevoUsuarioView(this);

            ventana.setVisible(true);

            cargarUsuarios();

        });
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un usuario."
                );

                return;

            }

            int id = Integer.parseInt(
                    modelo.getValueAt(fila, 0).toString()
            );

            UsuarioDAOImpl dao = new UsuarioDAOImpl();

            Usuario usuario = dao.buscarPorId(id);

            NuevoUsuarioView ventana =
                    new NuevoUsuarioView(this, usuario);

            ventana.setVisible(true);

            cargarUsuarios();

        });

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un usuario."
                );

                return;

            }

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Eliminar usuario?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {

                int id = Integer.parseInt(
                        modelo.getValueAt(fila, 0).toString()
                );

                UsuarioDAOImpl dao = new UsuarioDAOImpl();

                dao.eliminar(id);

                cargarUsuarios();

                JOptionPane.showMessageDialog(
                        this,
                        "Usuario eliminado correctamente."
                );

            }

        });
        // Colores de los botones
        btnNuevo.setBackground(new Color(76, 175, 80));
        btnNuevo.setForeground(Color.WHITE);

        btnEditar.setBackground(new Color(255, 193, 7));
        btnEditar.setForeground(Color.BLACK);

        btnEliminar.setBackground(new Color(244, 67, 54));
        btnEliminar.setForeground(Color.WHITE);

        // Quitar borde de enfoque
        btnNuevo.setFocusPainted(false);
        btnEditar.setFocusPainted(false);
        btnEliminar.setFocusPainted(false);

        botones.add(btnNuevo);
        botones.add(btnEditar);
        botones.add(btnEliminar);

        panel.add(botones, BorderLayout.SOUTH);

        add(panel);

        cargarUsuarios();

    }

    private void cargarUsuarios() {

        modelo.setRowCount(0);

        UsuarioDAOImpl dao = new UsuarioDAOImpl();

        for (Usuario u : dao.listar()) {

            modelo.addRow(new Object[]{

                    u.getId(),
                    u.getUsuario(),
                    u.getNombre(),
                    u.getRol()

            });


        }



    }


}