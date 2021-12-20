package pe.edu.pucp.pteleshock.Beans;

public class BPedidoG {
    private int idPedido;
    private String fecha;
    private String fechaEntrega;
    private String nombre;
    private String apellido;
    private String dni;
    private String codigoV;
    private double precioTotal;
    String estado;
    String correo;
    String distrito;
    String producto;
    String descripcion;
    int unidades;
    double precioUnitario;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodigoV() {
        return codigoV;
    }

    public void setCodigoV(String codigoV) {
        this.codigoV = codigoV;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}