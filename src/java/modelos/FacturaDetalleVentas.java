/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;


public class FacturaDetalleVentas {

    private int id_factura_detalle_venta;
    private FacturaVentas facturaventa;
    private Articulos articulo;
    private int cantidad_venta;
    private int subtotal_venta;
    
    
    public FacturaDetalleVentas() {
    }


    public FacturaDetalleVentas(int id_factura_detalle_venta, FacturaVentas facturaventa, Articulos articulo, int cantidad_venta, int subtotal_venta) {
        this.id_factura_detalle_venta = id_factura_detalle_venta;
        this.facturaventa = facturaventa;
        this.articulo = articulo;
        this.cantidad_venta = cantidad_venta;
        this.subtotal_venta = subtotal_venta;
    }

    public int getId_factura_detalle_venta() {
        return id_factura_detalle_venta;
    }

    public void setId_factura_detalle_venta(int id_factura_detalle_venta) {
        this.id_factura_detalle_venta = id_factura_detalle_venta;
    }

    public FacturaVentas getFacturaventa() {
        return facturaventa;
    }

    public void setFacturaventa(FacturaVentas facturaventa) {
        this.facturaventa = facturaventa;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public int getCantidad_venta() {
        return cantidad_venta;
    }

    public void setCantidad_venta(int cantidad_venta) {
        this.cantidad_venta = cantidad_venta;
    }

    public int getSubtotal_venta() {
        return subtotal_venta;
    }

    public void setSubtotal_venta(int subtotal_venta) {
        this.subtotal_venta = subtotal_venta;
    }

    

  
    
}