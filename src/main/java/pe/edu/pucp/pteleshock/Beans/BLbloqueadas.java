package pe.edu.pucp.pteleshock.Beans;

public class BLbloqueadas {
    public BLbloqueadas(int id, String nombre, String ruc) {
        this.setId(id);
        this.setNombre(nombre);
        this.setRuc(ruc);
    }
    private int id;
    private String nombre;
    private String ruc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
}
