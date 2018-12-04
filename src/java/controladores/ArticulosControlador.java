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
import modelos.Articulos;
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
import modelos.Marcas;


public class ArticulosControlador {
    
    
     public static boolean agregar(Articulos articulo){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into articulos (nombre_articulo, descripcion_articulo, precio_unitario, precio_venta, precio_compra, "
                    + "id_marca)" 
                    + "values ('" + articulo.getNombre_artiulo()+ "','"
                                  + articulo.getDescripcion_articulo()+ "','"
                                  + articulo.getPrecio_unitario()+ "','"
                                  + articulo.getPrecio_venta() + "','"
                                  + articulo.getPrecio_compra()+ "','"
                                  + articulo.getMarcas().getId_marca()+"')";
            System.out.println("sql"+sql);         
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(Articulos articulo){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update articulos set nombre_articulo='" + articulo.getNombre_artiulo() + "',"
                    + "descripcion_articulo='"+articulo.getDescripcion_articulo() + "',"
                    + "precio_unitario='"+articulo.getPrecio_unitario() + "',"
                    + "precio_venta='"+articulo.getPrecio_venta() + "',"
                    + "precio_compra='"+articulo.getPrecio_compra() + "',"
                    + "id_marca='"+articulo.getMarcas().getId_marca() + "'"
                    + " where id_articulo='" + articulo.getId_articulo()+ "'";
            System.out.println("sql"+sql);               
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Articulos articulo){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from articulos where id_articulo=" + articulo.getId_articulo();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static Articulos buscarId(Articulos articulo) {
        if (Conexion.conectar()){
            String sql = "select * from articulos a,marcas m" 
                    + " where " 
                    + "a.id_marca = m.id_marca"
                    + " and "
                    + "id_articulo = '"+articulo.getId_articulo()+"'";
            System.out.println("SQL : "+sql);
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    
                    Marcas marca = new Marcas();
                    marca.setId_marca(rs.getInt("id_marca"));
                    marca.setNombre_marca(rs.getString("nombre_marca"));
                    
                    articulo.setId_articulo(rs.getInt("id_articulo"));
                    articulo.setNombre_artiulo(rs.getString("nombre_articulo"));
                    articulo.setDescripcion_articulo(rs.getString("descripcion_articulo"));
                    articulo.setPrecio_unitario(rs.getInt("precio_unitario"));
                    articulo.setPrecio_venta(rs.getInt("precio_venta"));
                    articulo.setPrecio_compra(rs.getInt("precio_compra"));
                                      
                    articulo.setMarcas(marca);
                } else {
                    
                    Marcas marca = new Marcas();
                    
                    articulo.setId_articulo(0);
                    articulo.setNombre_artiulo("");
                    articulo.setDescripcion_articulo("");
                    articulo.setPrecio_unitario(0);
                    articulo.setPrecio_venta(0);
                    articulo.setPrecio_compra(0);
                    
                    
                    marca.setId_marca(0);
                    marca.setNombre_marca("");
                    
                    articulo.setMarcas(marca);    
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return articulo;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from articulos where upper(nombre_articulo) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_articulo offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                
                System.out.println("SQL : "+sql);
                
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_articulo") + "</td>"
                                + "<td>" + rs.getString("nombre_articulo") + "</td>"
                                + "<td>" + rs.getString("descripcion_articulo") + "</td>"
                                + "<td>" + rs.getString("precio_unitario") + "</td>"
                                + "<td>" + rs.getString("precio_venta") + "</td>"
                                + "<td>" + rs.getString("precio_compra") + "</td>"
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
