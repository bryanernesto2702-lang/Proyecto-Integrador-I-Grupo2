package pe.edu.utp;

import org.junit.jupiter.api.Test;
import pe.edu.utp.model.Producto;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductoTest {

    @Test
    void deberiaCrearProductoCorrectamente() {

        Producto producto =
                new Producto(
                        0,
                        "CAR-122",
                        "Tela Velvet Azul",
                        "Tapicería",
                        25.50,
                        100
                );

        assertEquals(
                "Tela Velvet Azul",
                producto.getNombre()
        );

        assertEquals(
                "CAR-122",
                producto.getCodigo()
        );

        assertEquals(
                "Tapicería",
                producto.getCategoria()
        );

        assertEquals(
                25.50,
                producto.getPrecio()
        );

        assertEquals(
                100,
                producto.getStock()
        );

    }

}