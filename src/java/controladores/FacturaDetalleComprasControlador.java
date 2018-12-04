/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;



import modelos.Articulos;
import modelos.FacturaDetalleCompras;
import modelos.FacturaCompras;
import utiles.Conexion;
import utiles.Utiles;
import java.math.BigDecimal;
//import modelos.Tipo_facturas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class FacturaDetalleComprasControlador {
    
    public static FacturaDetalleCompras buscarId(int id) {
        FacturaDetalleCompras facturadetallecompra = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_detalle_compras fdc "+
                             "left join factura_compras fc on fc.id_factura_compra=fdc.id_factura_compra "+
                             "left join articulos a on a.id_articulo=fdc.id_articulo "+
                             "where fdc.id_factura_detalle_compra=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturadetallecompra = new FacturaDetalleCompras();
                        facturadetallecompra.setId_factura_detalle_compra(rs.getInt("id_factura_detalle_compra"));
                        facturadetallecompra.setCantidad_compra(rs.getInt("cantidad_compra"));
                        facturadetallecompra.setSubtotal_compra(rs.getInt("subtotal_compra"));
                        
                        Articulos articulo = new Articulos();
                        articulo.setId_articulo(rs.getInt("id_articulo"));
                        articulo.setNombre_artiulo(rs.getString("nombre_articulo"));
                        articulo.setPrecio_compra(rs.getInt("precio_compra"));
                        facturadetallecompra.setArticulo(articulo);
                        
                        FacturaCompras facturacompra = new FacturaCompras();
                        facturacompra.setId_factura_compra(rs.getInt("id_factura_compra"));
                        facturadetallecompra.setFacturacompra(facturacompra);
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturadetallecompra;
    }
    
    public static String buscarIdFacturaCompra(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_detalle_compras fdc "+
                        "left join factura_compras fc on fc.id_factura_compra=fdc.id_factura_compra "+
                        "left join articulos a on a.id_articulo=fdc.id_articulo "+
                        "where fdc.id_factura_compra="+id+" "+
                        "order by id_factura_detalle_compra";
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    BigDecimal total1 = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_compra");
                        BigDecimal precio = rs.getBigDecimal("precio_compra");
                        BigDecimal total_compra = rs.getBigDecimal("subtotal_compra");
                        total = total.add(cantidad);
                        total1 = total1.add(total_compra);
                       // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_factura_detalle_compra") + "</td>"
                               + "<td>" + rs.getString("id_articulo") + "</td>"
                               + "<td>" + rs.getString("nombre_articulo") + "</td>" 
                               + "<td class='centrado'>" + df.format(precio) + "</td>"
                                //+ "<td class='centrado'>" + rs.getString("precio_compra") + "</td>"
                               + "<td class='centrado'>" + rs.getInt("cantidad_compra") + "</td>"
                               
                               + "<td class='centrado'>" + df.format(total_compra) + "</td>" 
                                
                               + "<td class='centrado'>"
                                + "<button onclick='editarLinea("+rs.getString("id_factura_detalle_compra")+")'"
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
                String sql = "select * from factura_detalle_compras fdc "+
                        "left join factura_compras fc on fv.id_factura_compra=fdc.id_factura_compra "+
                        "left join articulos a on a.id_articulo=fdc.id_articulo "+
                        "where upper(a.nombre_articulo) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_factura_detalle_compra "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_factura_detalle_compra") + "</td>"
                               + "<td>" + rs.getString("id_factura_compra") + "</td>"
                               + "<td>" + rs.getString("id_articulo") + "</td>"
                               + "<td>" + rs.getString("nombre_articulo") + "</td>"
                               + "<td>" + rs.getInt("cantidad_compra") + "</td>" 
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

    public static boolean agregar(FacturaDetalleCompras facturadetallecompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into factura_detalle_compras "
                    + "(id_factura_compra,id_articulo,cantidad_compra, subtotal_compra) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetallecompra.getFacturacompra().getId_factura_compra());
                ps.setInt(2, facturadetallecompra.getArticulo().getId_articulo());
                ps.setInt(3, facturadetallecompra.getCantidad_compra());
                ps.setInt(4, facturadetallecompra.getSubtotal_compra());
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

    public static boolean modificar(FacturaDetalleCompras facturadetallecompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_detalle_compras set "
                    + "id_factura_compra=?,"
                    + "id_articulo=?,"
                    + "cantidad_compra=?,"
                    + "subtotal_compra=? "
                    + "where id_factura_detalle_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetallecompra.getFacturacompra().getId_factura_compra());
                ps.setInt(2, facturadetallecompra.getArticulo().getId_articulo());
                ps.setInt(3, facturadetallecompra.getCantidad_compra());
                ps.setInt(4, facturadetallecompra.getSubtotal_compra());
                ps.setInt(5,facturadetallecompra.getId_factura_detalle_compra());
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

    public static boolean eliminar(FacturaDetalleCompras facturadetallecompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from factura_detalle_compras where id_factura_detalle_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetallecompra.getId_factura_detalle_compra());
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
    
    public static boolean eliminarArticuloPedido(FacturaCompras facturacompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from factura_detalle_compras where id_factura_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturacompra.getId_factura_compra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + facturacompra.getId_factura_compra());
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
