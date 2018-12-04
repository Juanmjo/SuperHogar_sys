/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author nicof
 */
public class Articulos {
    private int id_articulo;
    private String nombre_artiulo;
    private String descripcion_articulo;
    private int precio_unitario;
    private int precio_venta;
    private int precio_compra;
    
    private Categorias categoria;
    
    private Marcas marcas;

    public Articulos() {
    }

    public Articulos(int id_articulo, String nombre_artiulo, String descripcion_articulo, int precio_unitario, int precio_venta, int precio_compra, Categorias categoria) {
        this.id_articulo = id_articulo;
        this.nombre_artiulo = nombre_artiulo;
        this.descripcion_articulo = descripcion_articulo;
        this.precio_unitario = precio_unitario;
        this.precio_venta = precio_venta;
        this.precio_compra = precio_compra;
        this.categoria = categoria;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getNombre_artiulo() {
        return nombre_artiulo;
    }

    public void setNombre_artiulo(String nombre_artiulo) {
        this.nombre_artiulo = nombre_artiulo;
    }

    public String getDescripcion_articulo() {
        return descripcion_articulo;
    }

    public void setDescripcion_articulo(String descripcion_articulo) {
        this.descripcion_articulo = descripcion_articulo;
    }

    public int getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(int precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(int precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(int precio_compra) {
        this.precio_compra = precio_compra;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Marcas getMarcas() {
        return marcas;
    }

    public void setMarcas(Marcas marcas) {
        this.marcas = marcas;
    }
    
    
    
}

