package pe.edu.utp.dao;

import pe.edu.utp.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    private List<Producto> productos = new ArrayList<>();

    @Override
    public void guardar(Producto producto) {
        productos.add(producto);
    }

    @Override
    public List<Producto> listar() {
        return productos;
    }
}