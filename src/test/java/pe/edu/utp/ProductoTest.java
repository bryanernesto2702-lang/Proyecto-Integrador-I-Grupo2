package pe.edu.utp;

import org.junit.jupiter.api.Test;
import pe.edu.utp.model.Producto;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductoTest {

    @Test
    void deberiaCrearProductoCorrectamente() {

        Producto producto =
                new Producto(
                        "CAR-122",
                        "Tela Velvet Azul",
                        "Tapicería"
                );

        assertEquals(
                "Tela Velvet Azul",
                producto.getNombre()
        );
    }
}