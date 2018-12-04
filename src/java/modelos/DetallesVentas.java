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
public class DetallesVentas {
    private int id_detalleventa;
    private int cantidad_articuloventa;
    
    private Articulos articulo;
    
    private Ventas ventas;

    public DetallesVentas() {
    }

    public DetallesVentas(int id_detalleventa, int cantidad_articuloventa, Articulos articulo, Ventas ventas) {
        this.id_detalleventa = id_detalleventa;
        this.cantidad_articuloventa = cantidad_articuloventa;
        this.articulo = articulo;
        this.ventas = ventas;
    }

    public int getId_detalleventa() {
        return id_detalleventa;
    }

    public void setId_detalleventa(int id_detalleventa) {
        this.id_detalleventa = id_detalleventa;
    }

    public int getCantidad_articuloventa() {
        return cantidad_articuloventa;
    }

    public void setCantidad_articuloventa(int cantidad_articuloventa) {
        this.cantidad_articuloventa = cantidad_articuloventa;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }
    
    
   
}

