package pe.edu.utp.dao;

import pe.edu.utp.model.Compra;
import java.util.List;

public interface CompraDAO {

    void guardar(Compra compra);

    List<Compra> listar();

}