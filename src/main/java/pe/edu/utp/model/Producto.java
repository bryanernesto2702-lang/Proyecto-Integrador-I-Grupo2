package pe.edu.utp.model;

public class Producto {

    private String sku;
    private String nombre;
    private String categoria;

    public Producto(String sku, String nombre, String categoria) {
        this.sku = sku;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }
}