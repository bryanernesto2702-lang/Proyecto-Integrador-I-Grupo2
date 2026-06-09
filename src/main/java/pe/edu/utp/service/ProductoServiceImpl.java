package pe.edu.utp.service;

import pe.edu.utp.model.Producto;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public void registrarProducto(Producto producto) {

        System.out.println(
                "Producto registrado: "
                        + producto.getNombre()
        );

    }
}