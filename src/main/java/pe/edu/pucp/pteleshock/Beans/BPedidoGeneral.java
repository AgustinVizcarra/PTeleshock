package pe.edu.pucp.pteleshock.Beans;

public class BPedidoGeneral {
    private int idCliente;
    private String codigoVenta;
    private String fechaPedido;
    private double montoTotal;
    private double total;
    private int canceladosT;
    private int pendientesT;
    private int entregadosT;
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }



    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(String codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getCanceladosT() {
        return canceladosT;
    }

    public void setCanceladosT(int canceladosT) {
        this.canceladosT = canceladosT;
    }

    public int getPendientesT() {
        return pendientesT;
    }

    public void setPendientesT(int pendientesT) {
        this.pendientesT = pendientesT;
    }

    public int getEntregadosT() {
        return entregadosT;
    }

    public void setEntregadosT(int entregadosT) {
        this.entregadosT = entregadosT;
    }
}
