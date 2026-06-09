package pe.edu.utp;

import pe.edu.utp.controller.ProductoController;

public class MainMultitop {

    public static void main(String[] args) {

        ProductoController controller =
                new ProductoController();

        controller.crearProducto();
    }
}