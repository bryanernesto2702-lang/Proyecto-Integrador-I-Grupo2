package pe.edu.utp.view;

import pe.edu.utp.dao.UsuarioDAOImpl;
import pe.edu.utp.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class NuevoUsuarioView extends JDialog {

    private JTextField txtUsuario;
    private JTextField txtNombre;
    private JPasswordField txtContrasena;
    private JComboBox<String> cboRol;

    private JButton btnGuardar;
    private JButton btnCancelar;

    private Usuario usuarioEditar = null;

    public NuevoUsuarioView(JFrame parent) {

        super(parent, "Nuevo Usuario", true);

        setSize(400,350);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5,2,10,10));

        add(new JLabel("Usuario"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Contraseña"));
        txtContrasena = new JPasswordField();
        add(txtContrasena);

        add(new JLabel("Rol"));
        cboRol = new JComboBox<>();

        cboRol.addItem("Administrador");
        cboRol.addItem("Empleado");

        add(cboRol);

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        add(btnGuardar);
        add(btnCancelar);

        btnCancelar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> guardarUsuario());

    }
    public NuevoUsuarioView(JFrame parent, Usuario usuario) {

        this(parent);

        this.usuarioEditar = usuario;

        txtUsuario.setText(usuario.getUsuario());
        txtNombre.setText(usuario.getNombre());
        txtContrasena.setText(usuario.getContrasena());

        cboRol.setSelectedItem(usuario.getRol());

        btnGuardar.setText("Actualizar");

    }

    private void guardarUsuario() {

        try {

            UsuarioDAOImpl dao = new UsuarioDAOImpl();

            if (usuarioEditar == null) {

                Usuario usuario = new Usuario();

                usuario.setUsuario(txtUsuario.getText());
                usuario.setNombre(txtNombre.getText());
                usuario.setContrasena(
                        String.valueOf(txtContrasena.getPassword())
                );
                usuario.setRol(
                        cboRol.getSelectedItem().toString()
                );

                dao.guardar(usuario);

                JOptionPane.showMessageDialog(
                        this,
                        "Usuario registrado correctamente."
                );

            } else {

                usuarioEditar.setUsuario(txtUsuario.getText());
                usuarioEditar.setNombre(txtNombre.getText());
                usuarioEditar.setContrasena(
                        String.valueOf(txtContrasena.getPassword())
                );
                usuarioEditar.setRol(
                        cboRol.getSelectedItem().toString()
                );

                dao.actualizar(usuarioEditar);

                JOptionPane.showMessageDialog(
                        this,
                        "Usuario actualizado correctamente."
                );

            }

            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error."
            );

            e.printStackTrace();

        }

    }

}
