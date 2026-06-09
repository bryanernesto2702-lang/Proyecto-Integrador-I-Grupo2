package pe.edu.utp.model;

public class Almacen {

    private int id;
    private String nombreSede;
    private String ubicacion;

    public Almacen(int id, String nombreSede, String ubicacion) {
        this.id = id;
        this.nombreSede = nombreSede;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public String getUbicacion() {
        return ubicacion;
    }
}
