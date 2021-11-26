package pe.edu.pucp.pteleshock.Beans;

import java.io.InputStream;

public class BDetProdFarm {

    private String nombre;
    private String recetamedica;
    private Integer stock;
    private Double preciounitario;
    private String descripcion;
    private InputStream foto;


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


    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }
}
