/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;



import modelos.Articulos;
import modelos.DetallesVentas;
import modelos.Ventas;
import utiles.Conexion;
import utiles.Utiles;
import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class DetallesVentasControlador {
    
    public static DetallesVentas buscarId(int id) {
        DetallesVentas detalleventa = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dp "+
                             "left join ventas p on p.id_venta=dp.id_venta "+
                             "left join articulos a on a.id_articulo=dp.id_articulo "+
                             "where dp.id_detalleventa=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleventa = new DetallesVentas();
                        detalleventa.setId_detalleventa(rs.getInt("id_detalleventa"));
                        detalleventa.setCantidad_articuloventa(rs.getInt("cantidad_articuloventa"));
                        
                        Articulos articulo = new Articulos();
                        articulo.setId_articulo(rs.getInt("id_articulo"));
                        articulo.setNombre_artiulo(rs.getString("nombre_articulo"));
                        detalleventa.setArticulo(articulo);
                        
                        Ventas venta = new Ventas();
                        venta.setId_venta(rs.getInt("id_venta"));
                        detalleventa.setVentas(venta);
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleventa;
    }
    
    public static String buscarIdVenta(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dp "+
                        "left join ventas p on p.id_venta=dp.id_venta "+
                        "left join articulos a on a.id_articulo=dp.id_articulo "+
                        "where dp.id_venta="+id+" "+
                        "order by id_detalleventa";
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###.00" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_articuloventa");
                        total = total.add(cantidad);
                       // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_detalleventa") + "</td>"
                               + "<td>" + rs.getString("id_articulo") + "</td>"
                               + "<td>" + rs.getString("nombre_articulo") + "</td>" 
                               + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                               + "<td class='centrado'>"
                                + "<button onclick='editarLinea("+rs.getString("id_detalleventa")+")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }else{
                        tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"+df.format(total)+"</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static String buscarNombre(String nombre, int pagina ) {
        int offset=(pagina-1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dp "+
                        "left join ventas p on p.id_venta=dp.id_venta "+
                        "left join articulos a on a.id_articulo=dp.id_articulo "+
                        "where upper(a.nombre_articulo) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_detalleventa "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_detalleventa") + "</td>"
                               + "<td>" + rs.getString("id_venta") + "</td>"
                               + "<td>" + rs.getString("id_articulo") + "</td>"
                               + "<td>" + rs.getString("nombre_articulo") + "</td>"
                               + "<td>" + rs.getInt("cantidad_articuloventa") + "</td>" 
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(DetallesVentas detalleventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallesventas "
                    + "(id_venta,id_articulo,cantidad_articuloventa) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleventa.getVentas().getId_venta());
                ps.setInt(2, detalleventa.getArticulo().getId_articulo());
                ps.setInt(3, detalleventa.getCantidad_articuloventa());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(DetallesVentas detalleventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallesventas set "
                    + "id_venta=?,"
                    + "id_articulo=?,"
                    + "cantidad_articuloventa=? "
                    + "where id_detalleventa=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleventa.getVentas().getId_venta());
                ps.setInt(2, detalleventa.getArticulo().getId_articulo());
                ps.setInt(3, detalleventa.getCantidad_articuloventa());
                ps.setInt(4,detalleventa.getId_detalleventa());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(DetallesVentas detalleventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesventas where id_detalleventa=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleventa.getId_detalleventa());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static boolean eliminarArticuloVenta(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesventas where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, venta.getId_venta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + venta.getId_venta());
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    
}
