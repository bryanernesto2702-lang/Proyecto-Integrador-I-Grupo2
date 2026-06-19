package pe.edu.utp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pe.edu.utp.dao.ProductoDAO;
import pe.edu.utp.model.Producto;
import pe.edu.utp.service.ProductoServiceImpl;

import static org.mockito.Mockito.verify;

public class ProductoServiceTest {

    @Test
    void deberiaGuardarProducto() {

        ProductoDAO daoMock =
                Mockito.mock(ProductoDAO.class);

        ProductoServiceImpl service =
                new ProductoServiceImpl(daoMock);

        Producto producto =
                new Producto(
                        "CAR-122",
                        "Tela Velvet Azul",
                        "Tapicería"
                );

        service.registrarProducto(producto);

        verify(daoMock).guardar(producto);
    }
}