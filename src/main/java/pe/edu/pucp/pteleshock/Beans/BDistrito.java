package pe.edu.pucp.pteleshock.Beans;

public class BDistrito {

    public BDistrito(String nombre, int id_distrito) {
        this.nombre = nombre;
        this.id_distrito = id_distrito;
    }

    private String nombre;
    private int id_distrito;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_distrito() {
        return id_distrito;
    }

    public void setId_distrito(int id_distrito) {
        this.id_distrito = id_distrito;
    }
}
