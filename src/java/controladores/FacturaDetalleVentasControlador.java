/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;



import modelos.Articulos;
import modelos.FacturaDetalleVentas;
import modelos.FacturaVentas;
import utiles.Conexion;
import utiles.Utiles;
import java.math.BigDecimal;
import modelos.TipoFacturas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Ivas;

public class FacturaDetalleVentasControlador {
    
    public static FacturaDetalleVentas buscarId(int id) {
        FacturaDetalleVentas facturadetalle = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_detalle_ventas fdv "+
                             "left join factura_ventas fv on fv.id_factura_venta=fdv.id_factura_venta "+
                             "left join articulos a on a.id_articulo=fdv.id_articulo "+
                             "left join ivas i on i.id_iva=a.id_iva "+
                             "where fdv.id_factura_detalle_venta=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturadetalle = new FacturaDetalleVentas();
                        facturadetalle.setId_factura_detalle_venta(rs.getInt("id_factura_detalle_venta"));
                        facturadetalle.setCantidad_venta(rs.getInt("cantidad_venta"));
                        facturadetalle.setSubtotal_venta(rs.getInt("subtotal_venta"));
                        
                        Articulos articulo = new Articulos();
                        articulo.setId_articulo(rs.getInt("id_articulo"));
                        articulo.setNombre_artiulo(rs.getString("nombre_articulo"));
                        articulo.setPrecio_venta(rs.getInt("precio_venta"));
                        articulo.setPrecio_compra(rs.getInt("precio_compra"));
                        Ivas iva = new Ivas();
                    iva.setId_iva(rs.getInt("id_iva"));
                    iva.setPorcentaje_iva(rs.getInt("porcentaje_iva"));
                   // articulo.setIva(iva);
                        facturadetalle.setArticulo(articulo);
                        
                        FacturaVentas facturaventa = new FacturaVentas();
                        facturaventa.setId_factura_venta(rs.getInt("id_factura_venta"));
                        facturadetalle.setFacturaventa(facturaventa);
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                System.out.println("aqui--> ");
                Logger.getLogger(FacturaDetalleVentasControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturadetalle;
    }
    
    public static String buscarIdFacturaVenta(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_detalle_ventas fdv "+
                        "left join factura_ventas fv on fv.id_factura_venta=fdv.id_factura_venta "+
                        "left join articulos a on a.id_articulo=fdv.id_articulo "+
                        //"left join ivas i on i.id_iva=a.id_iva "+
                        "where fdv.id_factura_venta="+id+" "+
                        "order by id_factura_detalle_venta";
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    BigDecimal total1 = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_venta");
                        BigDecimal precio = rs.getBigDecimal("precio_venta");
                        BigDecimal total_venta = rs.getBigDecimal("subtotal_venta");
                        total = total.add(cantidad);
                        total1 = total1.add(total_venta);
                       // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_factura_detalle_venta") + "</td>"
                               + "<td>" + rs.getString("id_articulo") + "</td>"
                               + "<td>" + rs.getString("nombre_articulo") + "</td>" 
                               + "<td class='centrado'>" + df.format(precio) + "</td>"
                                //+ "<td class='centrado'>" + rs.getString("precio_venta") + "</td>"
                               + "<td class='centrado'>" + rs.getInt("cantidad_venta") + "</td>"
                               
                               + "<td class='centrado'>" + df.format(total_venta) + "</td>" 
                                
                               + "<td class='centrado'>"
                                + "<button onclick='editarLinea("+rs.getString("id_factura_detalle_venta")+")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }else{
                        tabla += "<tr><td colspan=4>TOTAL</td><td class='centrado'>"+total+
                                "<td class='centrado'>"+df.format(total1)+"</td>";
                        
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                System.out.println("aqui--> ");
                Logger.getLogger(FacturaDetalleVentasControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
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
                String sql = "select * from factura_detalle_ventas fdv "+
                        "left join factura_ventas fv on fv.id_factura_venta=fdv.id_factura_venta "+
                        "left join articulos a on a.id_articulo=fdv.id_articulo "+
                        "where upper(a.nombre_articulo) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_factura_detalle_venta "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_factura_detalle_venta") + "</td>"
                               + "<td>" + rs.getString("id_factura_venta") + "</td>"
                               + "<td>" + rs.getString("id_articulo") + "</td>"
                               + "<td>" + rs.getString("nombre_articulo") + "</td>"
                               + "<td>" + rs.getInt("cantidad_venta") + "</td>" 
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

    public static boolean agregar(FacturaDetalleVentas facturadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into factura_detalle_ventas "
                    + "(id_factura_venta,id_articulo,cantidad_venta, subtotal_venta) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetalle.getFacturaventa().getId_factura_venta());
                ps.setInt(2, facturadetalle.getArticulo().getId_articulo());
                ps.setInt(3, facturadetalle.getCantidad_venta());
                ps.setInt(4, facturadetalle.getSubtotal_venta());
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

    public static boolean modificar(FacturaDetalleVentas facturadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_detalle_ventas set "
                    + "id_factura_venta=?,"
                    + "id_articulo=?,"
                    + "cantidad_venta=?,"
                    + "subtotal_venta=? "
                    + "where id_factura_detalle_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetalle.getFacturaventa().getId_factura_venta());
                ps.setInt(2, facturadetalle.getArticulo().getId_articulo());
                ps.setInt(3, facturadetalle.getCantidad_venta());
                ps.setInt(4, facturadetalle.getSubtotal_venta());
                ps.setInt(5,facturadetalle.getId_factura_detalle_venta());
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

    public static boolean eliminar(FacturaDetalleVentas facturadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from factura_detalle_ventas where id_factura_detalle_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetalle.getId_factura_detalle_venta());
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
    
    public static boolean eliminarArticuloVentas(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from factura_detalle_ventas where id_factura_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturaventa.getId_factura_venta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + facturaventa.getId_factura_venta());
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
