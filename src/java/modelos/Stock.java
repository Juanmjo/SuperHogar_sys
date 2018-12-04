/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author alumno
 */
public class Stock {
    private int id_stock;
    private Articulos articulo;
    private int cantidad_stock;

    public Stock() {
    }

    public Stock(int id_stock, Articulos articulo, int cantidad_stock) {
        this.id_stock = id_stock;
        this.articulo = articulo;
        this.cantidad_stock = cantidad_stock;
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public int getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(int cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }
    
    
}
