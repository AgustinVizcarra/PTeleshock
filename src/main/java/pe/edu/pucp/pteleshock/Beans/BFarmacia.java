package pe.edu.pucp.pteleshock.Beans;

public class BFarmacia {
    private int idusuario;
    private int idfarmacia;
    private String nombre;
    private String ruc;
    private String direccion;
    private String correo;
    private String estado;
    private String nombreDistrito;

    public BFarmacia(int idusuario, int idfarmacia, String nombre, String ruc, String direccion, String correo, String estado, String nombreFarmacia, int pedEntregados, int pedPendientes, int pedCancelados, int pedDeseados) {
        this.idusuario = idusuario;
        this.idfarmacia = idfarmacia;
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.correo = correo;
        this.estado = estado;
        this.nombreDistrito = nombreFarmacia;
        this.pedEntregados = pedEntregados;
        this.pedPendientes = pedPendientes;
        this.pedCancelados = pedCancelados;
        this.pedDeseados = pedDeseados;
    }

    private int pedEntregados;
    private int pedPendientes;
    private int pedCancelados;
    private int pedDeseados;

    public BFarmacia() {
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public BFarmacia(int idusuario, int idfarmacia, String nombre, String ruc, String direccion, String correo, String estado, int pedEntregados, int pedPendientes, int pedCancelados, int pedDeseados) {
        this.idusuario = idusuario;
        this.idfarmacia = idfarmacia;
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.correo = correo;
        this.estado = estado;
        this.pedEntregados = pedEntregados;
        this.pedPendientes = pedPendientes;
        this.pedCancelados = pedCancelados;
        this.pedDeseados = pedDeseados;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdfarmacia() {
        return idfarmacia;
    }

    public void setIdfarmacia(int idfarmacia) {
        this.idfarmacia = idfarmacia;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPedEntregados() {
        return pedEntregados;
    }

    public void setPedEntregados(int pedEntregados) {
        this.pedEntregados = pedEntregados;
    }

    public int getPedPendientes() {
        return pedPendientes;
    }

    public void setPedPendientes(int pedPendientes) {
        this.pedPendientes = pedPendientes;
    }

    public int getPedCancelados() {
        return pedCancelados;
    }

    public void setPedCancelados(int pedCancelados) {
        this.pedCancelados = pedCancelados;
    }

    public int getPedDeseados() {
        return pedDeseados;
    }

    public void setPedDeseados(int pedDeseados) {
        this.pedDeseados = pedDeseados;
    }
}

