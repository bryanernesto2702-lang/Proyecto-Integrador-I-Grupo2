package pe.edu.utp;

import pe.edu.utp.model.Almacen;
import pe.edu.utp.model.Inventario;
import pe.edu.utp.model.Producto;

public class MainMultitop {

    public static void main(String[] args) {

        Producto producto =
                new Producto(
                        "CAR-122",
                        "Tela Velvet Azul",
                        "Tapicería"
                );

        Almacen almacen =
                new Almacen(
                        1,
                        "Lima Centro",
                        "Av. Principal 123"
                );

        Inventario inventario =
                new Inventario(
                        producto,
                        almacen,
                        50
                );

        System.out.println("Producto: "
                + inventario.getProducto().getNombre());

        System.out.println("Almacén: "
                + inventario.getAlmacen().getNombreSede());

        System.out.println("Stock: "
                + inventario.getStock());
    }
}