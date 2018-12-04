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
import modelos.Proveedores;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class ProveedoresControlador {
    
    
     public static boolean agregar(Proveedores proveedor){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into proveedores (nombre_proveedor, ruc_proveedor, direccion_proveedor, telefono_proveedor)" 
                    + "values ('" + proveedor.getNombre_proveedor()+ "','"
                                  + proveedor.getRuc_proveedor()+ "','"
                                  + proveedor.getDireccion_proveedor()+ "','"   
                                  + proveedor.getTelefono_proveedor()+"')";
            System.out.println("sql"+sql);         
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ProveedoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(Proveedores proveedor){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update proveedores set nombre_proveedor='" + proveedor.getNombre_proveedor() + "',"
                    + "ruc_proveedor='"+proveedor.getRuc_proveedor() + "',"
                    + "direccion_proveedor='"+proveedor.getDireccion_proveedor() + "',"
                    + "telefono_proveedor='"+proveedor.getTelefono_proveedor() + "',"
                    + " where id_proveedor='" + proveedor.getId_proveedor()+ "'";
            System.out.println("sql"+sql);               
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ProveedoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Proveedores proveedor){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from proveedores where id_proveedor=" + proveedor.getId_proveedor();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ProveedoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static Proveedores buscarId(Proveedores proveedor) {
        if (Conexion.conectar()){
            String sql = "select * from proveedores where id_proveedor ='"+proveedor.getId_proveedor()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                    proveedor.setRuc_proveedor(rs.getString("ruc_proveedor"));
                    proveedor.setDireccion_proveedor(rs.getString("direccion_proveedor"));
                    proveedor.setTelefono_proveedor(rs.getString("telefono_proveedor"));
                } else {
                    proveedor.setId_proveedor(0);
                    proveedor.setNombre_proveedor("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return proveedor;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from proveedores where upper(nombre_proveedor) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_proveedor offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_proveedor") + "</td>"
                                + "<td>" + rs.getString("nombre_proveedor") + "</td>"
                                + "<td>" + rs.getString("ruc_proveedor") + "</td>"
                                + "<td>" + rs.getString("direccion_proveedor") + "</td>"
                                + "<td>" + rs.getString("telefono_proveedor") + "</td>"
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
