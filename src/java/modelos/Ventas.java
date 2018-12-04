/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;




public class Ventas {
    private int id_venta;
    private Date fecha_venta;
  
    
    private Clientes clientes;

    public Ventas() {
    }

    public Ventas(int id_venta, Date fecha_venta, Clientes clientes) {
        this.id_venta = id_venta;
        this.fecha_venta = fecha_venta;
        this.clientes = clientes;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

   

   
    
}

