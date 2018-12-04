/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;




public class Pedidos {
    private int id_pedido;
    private Date fecha_pedido;
  
    
    private Clientes clientes;

    public Pedidos() {
    }

    public Pedidos(int id_pedido, Date fecha_pedido, Clientes clientes) {
        this.id_pedido = id_pedido;
        this.fecha_pedido = fecha_pedido;
        this.clientes = clientes;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

   

   
    
}

