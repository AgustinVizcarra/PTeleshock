package pe.edu.pucp.pteleshock.Beans;

public class BPedidoEstado {

    private BPedido pedido;
    private BDistristos distristos;
    private String fotoProducto;
    private String fechaStatus;
    private String direccionFarm;
    private Double precioProducto;
    private int idCliente;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private String tiempoRestante;
    private int idProducto;
    private boolean recetaMedica;



    public BPedido getPedido() {
        return pedido;
    }

    public void setPedido(BPedido pedido) {
        this.pedido = pedido;
    }

    public BDistristos getDistristos() {
        return distristos;
    }

    public void setDistristos(BDistristos distristos) {
        this.distristos = distristos;
    }


    public String getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(String fotoProducto) {
        this.fotoProducto = fotoProducto;
    }

    public String getFechaStatus() {
        return fechaStatus;
    }

    public void setFechaStatus(String fechaStatus) {
        this.fechaStatus = fechaStatus;
    }

    public String getDireccionFarm() {
        return direccionFarm;
    }

    public void setDireccionFarm(String direccionFarm) {
        this.direccionFarm = direccionFarm;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public boolean isRecetaMedica() {
        return recetaMedica;
    }

    public void setRecetaMedica(boolean recetaMedica) {
        this.recetaMedica = recetaMedica;
    }
}
