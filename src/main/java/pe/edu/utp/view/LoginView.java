package pe.edu.utp.view;

import pe.edu.utp.dao.UsuarioDAOImpl;
import pe.edu.utp.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pe.edu.utp.config.Sesion;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;

    public LoginView() {

        setTitle("Sistema de Inventario");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));

        JLabel lblTitulo = new JLabel("SISTEMA DE INVENTARIO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(70, 25, 320, 30);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(60, 90, 100, 25);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(60, 115, 320, 35);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setBounds(60, 165, 100, 25);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(60, 190, 320, 35);

        btnIngresar = new JButton("Iniciar Sesión");
        btnIngresar.setBounds(120, 255, 200, 40);
        btnIngresar.setBackground(new Color(33, 150, 243));
        btnIngresar.setForeground(Color.WHITE);

        panel.add(lblTitulo);
        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(btnIngresar);

        add(panel);

        btnIngresar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String usuario = txtUsuario.getText();
                String contrasena = String.valueOf(txtContrasena.getPassword());

                UsuarioDAOImpl dao = new UsuarioDAOImpl();

                Usuario u = dao.iniciarSesion(usuario, contrasena);

                if (u != null) {

                    Sesion.setUsuarioActual(u);
                    JOptionPane.showMessageDialog(

                            LoginView.this,
                            "Bienvenido " + u.getNombre()
                    );

                    dispose();

                    // Se envía el usuario al Dashboard
                    DashboardView dashboard = new DashboardView(u);
                    dashboard.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(
                            LoginView.this,
                            "Usuario o contraseña incorrectos"
                    );

                }

            }

        });

    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }

    public JButton getBtnIngresar() {
        return btnIngresar;
    }

}