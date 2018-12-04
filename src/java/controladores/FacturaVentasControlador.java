/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Clientes;
//import modelos.TipoFacturas;
import utiles.Conexion;
import utiles.Utiles;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.FacturaVentas;
import modelos.TipoFacturas;



public class FacturaVentasControlador {

    public static FacturaVentas buscarId(int id) {
        FacturaVentas facturaventa = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_ventas fc "
                        + "left join clientes p on fc.id_cliente=p.id_cliente "
                        + "left join tipo_facturas tf on fc.id_tipo_factura=tf.id_tipo_factura "
                        + "where id_factura_venta=?";
                System.out.println("sql ::"+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturaventa = new FacturaVentas();
                        facturaventa.setId_factura_venta(rs.getInt("id_factura_venta"));
                        Clientes cliente = new Clientes();
                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                        facturaventa.setCliente(cliente);
                        facturaventa.setFecha_factura_venta(rs.getDate("fecha_factura_venta"));
                        TipoFacturas tipofactura = new TipoFacturas();
                        tipofactura.setId_tipo_factura(rs.getInt("id_tipo_factura"));
                        tipofactura.setNombre_tipo_factura(rs.getString("nombre_tipo_factura"));
                        facturaventa.setTipofactura(tipofactura);
                        
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturaventa;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_ventas fc "
                        + "left join clientes p on p.id_cliente=fc.id_cliente "
                        + "where upper(nombre_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_factura_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_factura_venta") + "</td>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
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

    public static boolean agregar(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = facturaventa.getCliente().getId_cliente();
            Date v2 = facturaventa.getFecha_factura_venta();
            int v3 = facturaventa.getTipofactura().getId_tipo_factura();
            String sql = "insert into factura_ventas(id_cliente, fecha_factura_venta, id_tipo_factura) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_factura_venta = keyset.getInt(1);
                    facturaventa.setId_factura_venta(id_factura_venta);
                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(FacturaVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_ventas set id_cliente=?,"
                    + "id_tipo_factura=? "
                    + "where id_factura_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, facturaventa.getCliente().getId_cliente());
                ps.setInt(2, facturaventa.getTipofactura().getId_tipo_factura());
                ps.setInt(3, facturaventa.getId_factura_venta());
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

    public static boolean eliminar(FacturaVentas facturaventa  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from factura_ventas where id_factura_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturaventa.getId_factura_venta());
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
}