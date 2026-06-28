package pe.edu.utp.model;

public class MovimientoInventario {

    private int id;
    private int productoId;
    private String tipo;
    private int cantidad;
    private String fecha;
    private int usuarioId;

    public MovimientoInventario() {
    }

    public MovimientoInventario(int id,
                                int productoId,
                                String tipo,
                                int cantidad,
                                String fecha,
                                int usuarioId) {

        this.id = id;
        this.productoId = productoId;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.usuarioId = usuarioId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

}