package pe.edu.utp.service;

import pe.edu.utp.dao.ProductoDAO;
import pe.edu.utp.model.Producto;

public class ProductoServiceImpl implements ProductoService {

    private ProductoDAO productoDAO;

    public ProductoServiceImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public void registrarProducto(Producto producto) {

        productoDAO.guardar(producto);

        System.out.println(
                "Producto registrado: "
                        + producto.getNombre()
        );
    }
}