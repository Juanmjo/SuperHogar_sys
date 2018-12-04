/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author jj
 */
import modelos.Clientes;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import modelos.EstadosCiviles;


public class ClientesControlador {
    
    
     public static boolean agregar(Clientes cliente){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into clientes (nombre_cliente, apellido_cliente, ruc_cliente, ci_cliente, direccion_cliente, "
                    + "telefono_cliente, correo_cliente, id_estadocivil)" 
                    + "values ('" + cliente.getNombre_cliente()+ "','"
                                  + cliente.getApellido_cliente()+ "','"
                                  + cliente.getRuc_cliente()+ "','"
                                  + cliente.getCi_cliente()+ "','"
                                  + cliente.getDireccion_cliente()+ "','"
                                  + cliente.getTelefono_cliente() + "','"
                                  + cliente.getCorreo_cliente()+ "','"
                                  + cliente.getEstadosciviles().getId_estadocivil()+"')";
            System.out.println("sql"+sql);         
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(Clientes cliente){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update clientes set nombre_cliente='" + cliente.getNombre_cliente() + "',"
                    + "apellido_cliente='"+cliente.getApellido_cliente() + "',"
                    + "ruc_cliente='"+cliente.getRuc_cliente() + "',"
                    + "ci_cliente='"+cliente.getCi_cliente() + "',"
                    + "direccion_cliente='"+cliente.getDireccion_cliente() + "',"
                    + "telefono_cliente='"+cliente.getTelefono_cliente() + "',"
                    + "correo_cliente='"+cliente.getCorreo_cliente() + "',"
                    + "id_estadocivil='"+cliente.getEstadosciviles().getId_estadocivil() + "'"
                    + " where id_cliente='" + cliente.getId_cliente()+ "'";
            System.out.println("sql"+sql);               
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Clientes cliente){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from clientes where id_cliente=" + cliente.getId_cliente();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static Clientes buscarId(Clientes cliente) {
        if (Conexion.conectar()){
            String sql = "select * from clientes c,estadosciviles e" 
                    + " where " 
                    + "c.id_estadocivil = e.id_estadocivil"
                    + " and "
                    + "id_cliente = '"+cliente.getId_cliente()+"'";
            System.out.println("SQL : "+sql);
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    
                    EstadosCiviles estadocivil = new EstadosCiviles();
                    estadocivil.setId_estadocivil(rs.getInt("id_estadocivil"));
                    estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                    
                    cliente.setId_cliente(rs.getInt("id_cliente"));
                    cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                    cliente.setApellido_cliente(rs.getString("apellido_cliente"));
                    cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                    cliente.setCi_cliente(rs.getString("ci_cliente"));
                    cliente.setDireccion_cliente(rs.getString("direccion_cliente"));
                    cliente.setTelefono_cliente(rs.getString("telefono_cliente"));
                    cliente.setCorreo_cliente(rs.getString("correo_cliente"));
                    
                    cliente.setEstadosciviles(estadocivil);
                } else {
                    
                    EstadosCiviles estadocivil = new EstadosCiviles();
                    
                    cliente.setId_cliente(0);
                    cliente.setNombre_cliente("");
                    cliente.setApellido_cliente("");
                    cliente.setRuc_cliente("");
                    cliente.setDireccion_cliente("");
                    cliente.setTelefono_cliente("");
                    cliente.setCorreo_cliente("");
                    
                    estadocivil.setId_estadocivil(0);
                    estadocivil.setNombre_estadocivil("");
                    
                    cliente.setEstadosciviles(estadocivil);    
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return cliente;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from clientes where upper(nombre_cliente) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_cliente offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                
                System.out.println("SQL : "+sql);
                
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td>" + rs.getString("apellido_cliente") + "</td>"
                                + "<td>" + rs.getString("ruc_cliente") + "</td>"
                                + "<td>" + rs.getString("ci_cliente") + "</td>"
                                + "<td>" + rs.getString("direccion_cliente") + "</td>"
                                + "<td>" + rs.getString("telefono_cliente") + "</td>"
                                + "<td>" + rs.getString("correo_cliente") + "</td>"
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
