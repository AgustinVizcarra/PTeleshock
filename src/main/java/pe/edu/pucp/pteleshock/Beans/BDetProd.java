package pe.edu.pucp.pteleshock.Beans;

public class BDetProd {

    private String nombre;
    private String recetamedica;
    private Integer stock;
    private Double preciounitario;
    private String descripcion;
    private String foto;
    private int idProducto;
    private boolean recetaMedicaB;



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRecetamedica() {
        return recetamedica;
    }

    public void setRecetamedica(String recetamedica) {
        this.recetamedica = recetamedica;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(Double preciounitario) {
        this.preciounitario = preciounitario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public boolean isRecetaMedicaB() {
        return recetaMedicaB;
    }

    public void setRecetaMedicaB(boolean recetaMedicaB) {
        this.recetaMedicaB = recetaMedicaB;
    }
}
