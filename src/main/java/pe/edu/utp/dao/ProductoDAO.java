package pe.edu.utp.dao;

import pe.edu.utp.model.Producto;
import java.util.List;

public interface ProductoDAO {

    void guardar(Producto producto);

    List<Producto> listar();
}