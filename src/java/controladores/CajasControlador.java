/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Cajas;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.sql.Date;
import java.sql.Time;

public class CajasControlador {
    public static boolean agregar(Cajas caja){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into cajas (nombre_caja, fecha, hora_apertura, hora_cierre)" 
                     + "values ('" + caja.getNombre_caja()+ "','"
                                  + caja.getFecha()+ "','"
                                  + caja.getHora_apertura()+ "','"
                                  + caja.getHora_cierre()+"')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(CajasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Cajas caja){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update cajas set nombre_caja='" + caja.getNombre_caja() + "',"
                     + "fecha='"+caja.getFecha() + "',"
                     + "hora_apertura='"+caja.getHora_apertura()+ "',"
                     + "hora_cierre='"+caja.getHora_cierre()+ "',"
                    + " where id_caja=" + caja.getId_caja();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(CajasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Cajas caja){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from cajas where id_caja=" + caja.getId_caja();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(CajasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Cajas buscarId(Cajas caja) {
        if (Conexion.conectar()){
            String sql = "select * from cajas where id_caja ='"+caja.getId_caja()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    caja.setId_caja(rs.getInt("id_caja"));
                    caja.setNombre_caja(rs.getString("nombre_caja"));
                } else {
                    caja.setId_caja(0);
                    caja.setNombre_caja("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return caja;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from cajas where upper(nombre_caja) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_caja offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_caja") + "</td>"
                                + "<td>" + rs.getString("nombre_caja") + "</td>"
                                + "<td>" + rs.getString("facha") + "</td>"
                                + "<td>" + rs.getString("hora_apertura") + "</td>"
                                + "<td>" + rs.getString("hora_cierre") + "</td>"
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
