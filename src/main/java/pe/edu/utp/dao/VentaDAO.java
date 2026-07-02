package pe.edu.utp.dao;

import pe.edu.utp.model.Venta;

import java.util.List;

public interface VentaDAO {

    void guardar(Venta venta);

    List<Venta> listar();

}