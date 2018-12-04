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
public class DetallesPedidos {
    private int id_detallepedido;
    private int cantidad_articulopedido;
    
    private Articulos articulo;
    
    private Pedidos pedidos;

    public DetallesPedidos() {
    }

    public DetallesPedidos(int id_detallepedido, int cantidad_articulopedido, Articulos articulo, Pedidos pedidos) {
        this.id_detallepedido = id_detallepedido;
        this.cantidad_articulopedido = cantidad_articulopedido;
        this.articulo = articulo;
        this.pedidos = pedidos;
    }

    public int getId_detallepedido() {
        return id_detallepedido;
    }

    public void setId_detallepedido(int id_detallepedido) {
        this.id_detallepedido = id_detallepedido;
    }

    public int getCantidad_articulopedido() {
        return cantidad_articulopedido;
    }

    public void setCantidad_articulopedido(int cantidad_articulopedido) {
        this.cantidad_articulopedido = cantidad_articulopedido;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }
    
    
   
}

