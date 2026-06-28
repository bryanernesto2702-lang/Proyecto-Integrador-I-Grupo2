package pe.edu.utp.dao;

import pe.edu.utp.model.MovimientoInventario;
import java.util.List;

public interface MovimientoInventarioDAO {

    void registrar(MovimientoInventario movimiento);

    List<MovimientoInventario> listar();

}