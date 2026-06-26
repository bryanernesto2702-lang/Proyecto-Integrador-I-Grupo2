package pe.edu.utp.dao;

import pe.edu.utp.model.Producto;

import java.util.List;

public interface ProductoDAO {

    void guardar(Producto producto);

    void actualizar(Producto producto);

    void eliminar(int id);

    List<Producto> listar();

}