package pe.edu.utp.view;

import pe.edu.utp.dao.ProductoDAOImpl;
import pe.edu.utp.model.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import pe.edu.utp.dao.VentaDAOImpl;

public class DashboardView extends JFrame {

    public DashboardView(Usuario usuario) {

        setTitle("Sistema de Inventario");
        setSize(1100,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel principal = new JPanel(new BorderLayout());

        //==========================
        // MENÚ IZQUIERDO
        //==========================

        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(220,650));
        menu.setBackground(new Color(40,62,80));
        menu.setLayout(new GridLayout(10,1,5,5));

        JLabel titulo = new JLabel(" INVENTARIO");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial",Font.BOLD,20));

        JLabel lblUsuario = new JLabel(usuario.getNombre(), SwingConstants.CENTER);
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Arial",Font.BOLD,15));

        JLabel lblRol = new JLabel(usuario.getRol(), SwingConstants.CENTER);
        lblRol.setForeground(Color.LIGHT_GRAY);
        lblRol.setFont(new Font("Arial",Font.PLAIN,13));

        JButton btnInicio = new JButton("Inicio");
        btnInicio.addActionListener(e -> {

            dispose();

            new DashboardView(usuario).setVisible(true);

        });

        JButton btnProductos = new JButton("Productos");
        btnProductos.addActionListener(e -> {
            new ProductoView().setVisible(true);
        });

        JButton btnInventario = new JButton("Inventario");

        btnInventario.addActionListener(e -> {

            InventarioView ventana = new InventarioView();

            ventana.setVisible(true);

        });

        JButton btnCompras = new JButton("Compras");
        btnCompras.addActionListener(e -> {

            CompraView ventana = new CompraView();

            ventana.setVisible(true);

        });

        JButton btnVentas = new JButton("Ventas");
        btnVentas.addActionListener(e -> {

            VentaView ventana = new VentaView();

            ventana.setVisible(true);

        });

        JButton btnUsuarios = new JButton("Usuarios");
        btnUsuarios.addActionListener(e -> {
            new UsuarioView().setVisible(true);
        });

        JButton btnSalir = new JButton("Cerrar sesión");
        btnSalir.addActionListener(e -> {

            dispose();

            new LoginView().setVisible(true);

        });

        //==========================
        // PERMISOS
        //==========================

        if(usuario.getRol().equalsIgnoreCase("Empleado")){

            btnUsuarios.setVisible(false);
            btnCompras.setVisible(false);

        }

        JButton[] botones = {
                btnInicio,
                btnProductos,
                btnInventario,
                btnCompras,
                btnVentas,
                btnUsuarios,
                btnSalir
        };

        menu.add(titulo);
        menu.add(lblUsuario);
        menu.add(lblRol);

        for(JButton boton : botones){

            boton.setFocusPainted(false);
            boton.setBackground(new Color(52,73,94));
            boton.setForeground(Color.WHITE);

            menu.add(boton);

        }

        //==========================
        // DASHBOARD
        //==========================

        ProductoDAOImpl dao = new ProductoDAOImpl();

        int totalProductos = dao.contarProductos();
        int stockBajo = dao.contarStockBajo();

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(2,2,20,20));
        centro.setBorder(new EmptyBorder(30,30,30,30));

        centro.add(crearTarjeta("📦 Productos", String.valueOf(totalProductos)));
        centro.add(crearTarjeta("⚠ Stock Bajo", String.valueOf(stockBajo)));
        VentaDAOImpl ventaDAO = new VentaDAOImpl();

        double ventasHoy = ventaDAO.totalVentasHoy();

        centro.add(
                crearTarjeta(
                        "💰 Ventas Hoy",
                        String.format("S/. %.2f", ventasHoy)
                )
        );
        centro.add(crearTarjeta(
                "👤 Usuario",
                usuario.getNombre()
        ));

        principal.add(menu,BorderLayout.WEST);
        principal.add(centro,BorderLayout.CENTER);

        add(principal);

    }

    private JPanel crearTarjeta(String titulo,String valor){

        JPanel tarjeta = new JPanel();

        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        tarjeta.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel(titulo,SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial",Font.BOLD,18));

        JLabel lblValor = new JLabel(valor,SwingConstants.CENTER);
        lblValor.setFont(new Font("Arial",Font.BOLD,28));

        tarjeta.add(lblTitulo,BorderLayout.NORTH);
        tarjeta.add(lblValor,BorderLayout.CENTER);

        return tarjeta;

    }

}