package pe.edu.pucp.pteleshock.Beans;

public class BUsuario {
    private int idUsuario;
    private BRol rol;
    private BDistristos distritos;
    private String nombre;
    private String apellido;
    private String correo;
    private String dni;
    private String ruc;
    private String contrasenia;

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BRol getRol() {
        return rol;
    }

    public void setRol(BRol rol) {
        this.rol = rol;
    }

    public BDistristos getDistritos() {
        return distritos;
    }

    public void setDistritos(BDistristos distritos) {
        this.distritos = distritos;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
