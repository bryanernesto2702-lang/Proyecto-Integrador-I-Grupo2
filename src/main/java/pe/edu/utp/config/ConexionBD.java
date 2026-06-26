package pe.edu.utp.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/inventario_db";

    private static final String USER = "root";

    private static final String PASSWORD = "2727";

    public static Connection conectar() {

        try {

            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println("Conexión exitosa a MySQL.");

            return conexion;

        } catch (Exception e) {

            System.out.println("Error al conectar con MySQL.");
            e.printStackTrace();

            return null;
        }
    }
}