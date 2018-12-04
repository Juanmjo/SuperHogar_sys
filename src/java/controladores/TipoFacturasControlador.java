/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.TipoFacturas;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TipoFacturasControlador {
    public static boolean agregar(TipoFacturas tipo_factura){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into tipo_facturas (nombre_tipo_factura)" 
                    + "values ('" + tipo_factura.getNombre_tipo_factura() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TipoFacturasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(TipoFacturas tipo_factura){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update tipo_facturas set nombre_tipo_factura='" + tipo_factura.getNombre_tipo_factura() + "'"
                    + " where id_tipo_factura=" + tipo_factura.getId_tipo_factura();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TipoFacturasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(TipoFacturas tipo_factura){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from tipo_facturas where id_tipo_factura=" + tipo_factura.getId_tipo_factura();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TipoFacturasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static TipoFacturas buscarId(TipoFacturas tipo_factura) {
        if (Conexion.conectar()){
            String sql = "select * from tipo_facturas where id_tipo_factura ='"+tipo_factura.getId_tipo_factura()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    tipo_factura.setId_tipo_factura(rs.getInt("id_tipo_factura"));
                    tipo_factura.setNombre_tipo_factura(rs.getString("nombre_tipo_factura"));
                } else {
                    tipo_factura.setId_tipo_factura(0);
                    tipo_factura.setNombre_tipo_factura("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return tipo_factura;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from tipo_facturas where upper(nombre_tipo_factura) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_tipo_factura offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_tipo_factura") + "</td>"
                                + "<td>" + rs.getString("nombre_tipo_factura") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
