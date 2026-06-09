package pe.edu.utp.model;

public class Inventario {

    private Producto producto;
    private Almacen almacen;
    private int stock;

    public Inventario(Producto producto, Almacen almacen, int stock) {
        this.producto = producto;
        this.almacen = almacen;
        this.stock = stock;
    }

    public Producto getProducto() {
        return producto;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public int getStock() {
        return stock;
    }

    public void actualizarStock(int cantidad) {
        this.stock += cantidad;
    }
}