package pe.edu.pucp.pteleshock.Beans;

public class BListaPFarmacia {
    private Integer idProducto;
    private String nombre;
    private String foto;
    private int stock;
    private String estadoproducto;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEstadoproducto() {
        return estadoproducto;
    }

    public void setEstadoproducto(String estadoproducto) {
        this.estadoproducto = estadoproducto;
    }
}
