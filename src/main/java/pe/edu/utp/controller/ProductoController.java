package pe.edu.utp.controller;

import pe.edu.utp.dao.ProductoDAOImpl;
import pe.edu.utp.model.Producto;
import pe.edu.utp.service.ProductoService;
import pe.edu.utp.service.ProductoServiceImpl;

public class ProductoController {

    private ProductoService service =
            new ProductoServiceImpl(
                    new ProductoDAOImpl()
            );

}