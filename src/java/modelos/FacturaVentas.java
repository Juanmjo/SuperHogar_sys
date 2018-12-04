/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

public class FacturaVentas {

    private int id_factura_venta;
    
    private Clientes cliente;
    private Date fecha_factura_venta;
    private TipoFacturas tipofactura;
    private int subtotal_5;
    private int subtotal_10;
    private int subtotal_exenta;
    private int cantidad_cuotas;
    


    public FacturaVentas() {
    }

    public FacturaVentas(int id_factura_venta, Clientes cliente, Date fecha_factura_venta, TipoFacturas tipofactura, int subtotal_5, int subtotal_10, int subtotal_exenta, int cantidad_cuotas) {
        this.id_factura_venta = id_factura_venta;
        this.cliente = cliente;
        this.fecha_factura_venta = fecha_factura_venta;
        this.tipofactura = tipofactura;
        this.subtotal_5 = subtotal_5;
        this.subtotal_10 = subtotal_10;
        this.subtotal_exenta = subtotal_exenta;
        this.cantidad_cuotas = cantidad_cuotas;
    }

    public int getId_factura_venta() {
        return id_factura_venta;
    }

    public void setId_factura_venta(int id_factura_venta) {
        this.id_factura_venta = id_factura_venta;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Date getFecha_factura_venta() {
        return fecha_factura_venta;
    }

    public void setFecha_factura_venta(Date fecha_factura_venta) {
        this.fecha_factura_venta = fecha_factura_venta;
    }

    public TipoFacturas getTipofactura() {
        return tipofactura;
    }

    public void setTipofactura(TipoFacturas tipofactura) {
        this.tipofactura = tipofactura;
    }

    public int getSubtotal_5() {
        return subtotal_5;
    }

    public void setSubtotal_5(int subtotal_5) {
        this.subtotal_5 = subtotal_5;
    }

    public int getSubtotal_10() {
        return subtotal_10;
    }

    public void setSubtotal_10(int subtotal_10) {
        this.subtotal_10 = subtotal_10;
    }

    public int getSubtotal_exenta() {
        return subtotal_exenta;
    }

    public void setSubtotal_exenta(int subtotal_exenta) {
        this.subtotal_exenta = subtotal_exenta;
    }

    public int getCantidad_cuotas() {
        return cantidad_cuotas;
    }

    public void setCantidad_cuotas(int cantidad_cuotas) {
        this.cantidad_cuotas = cantidad_cuotas;
    }

    

    

    
}
