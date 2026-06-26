package pe.edu.utp.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView() {

        setTitle("Sistema de Inventario");
        setSize(1100,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //--------------------------
        // Panel Principal
        //--------------------------

        JPanel principal = new JPanel(new BorderLayout());

        //--------------------------
        // Menú izquierdo
        //--------------------------

        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(220,650));
        menu.setBackground(new Color(40,62,80));
        menu.setLayout(new GridLayout(8,1,5,5));

        JLabel titulo = new JLabel(" INVENTARIO");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial",Font.BOLD,20));

        JButton btnInicio = new JButton("Inicio");
        JButton btnProductos = new JButton("Productos");
        btnProductos.addActionListener(e -> {

            ProductoView ventana = new ProductoView();

            ventana.setVisible(true);

        });
        JButton btnInventario = new JButton("Inventario");
        JButton btnCompras = new JButton("Compras");
        JButton btnVentas = new JButton("Ventas");
        JButton btnUsuarios = new JButton("Usuarios");
        JButton btnSalir = new JButton("Cerrar sesión");

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

        for(JButton boton : botones){

            boton.setFocusPainted(false);
            boton.setBackground(new Color(52,73,94));
            boton.setForeground(Color.WHITE);

            menu.add(boton);
        }

        //--------------------------
        // Panel Central
        //--------------------------

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(2,2,20,20));
        centro.setBorder(new EmptyBorder(30,30,30,30));

        centro.add(crearTarjeta("Productos", "150"));
        centro.add(crearTarjeta("Stock Bajo", "12"));
        centro.add(crearTarjeta("Ventas Hoy", "S/.850"));
        centro.add(crearTarjeta("Usuarios", "5"));

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
        lblValor.setFont(new Font("Arial",Font.BOLD,30));

        tarjeta.add(lblTitulo,BorderLayout.NORTH);
        tarjeta.add(lblValor,BorderLayout.CENTER);

        return tarjeta;
    }

}