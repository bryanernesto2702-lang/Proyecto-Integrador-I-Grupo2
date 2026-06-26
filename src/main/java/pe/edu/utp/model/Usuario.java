package pe.edu.utp.model;

public class Usuario {

    private int id;
    private String usuario;
    private String contrasena;
    private String nombre;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String contrasena, String nombre) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}