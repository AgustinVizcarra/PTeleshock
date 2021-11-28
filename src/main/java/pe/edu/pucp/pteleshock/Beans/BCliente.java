package pe.edu.pucp.pteleshock.Beans;

public class BCliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private String dni;
    private BDistristos distrito;


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    public BDistristos getDistrito() {
        return distrito;
    }

    public void setDistrito(BDistristos distrito) {
        this.distrito = distrito;
    }
}
