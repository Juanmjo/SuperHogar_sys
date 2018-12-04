/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Clientes;
import modelos.Ventas;
import utiles.Conexion;
import utiles.Utiles;
import java.sql.Date;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class VentasControlador {

    public static Ventas buscarId(int id) {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas c "
                        + "left join clientes a on c.id_cliente=a.id_cliente "
                        + "where id_venta=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    
                   
                            
                            
                    
                    
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        ventas = new Ventas();
                        ventas.setId_venta(rs.getInt("id_venta"));
                        ventas.setFecha_venta(rs.getDate("fecha_venta"));
                        Clientes cliente = new Clientes();
                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        ventas.setClientes(cliente);
                        
                        
                        
                        
                         
                    java.sql.Date fecha_venta = new java.sql.Date(new java.util.Date().getTime());
                    ventas.setFecha_venta(fecha_venta);
                    
                    
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas p "
                        + "left join clientes c on c.id_cliente=p.id_cliente "
                        + "where upper(nombre_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
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

    public static boolean agregar(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = venta.getClientes().getId_cliente();
            Date v2 = venta.getFecha_venta();

            String sql = "insert into ventas(id_cliente, fecha_venta) "
                    + "values('" + v1 + "','" + v2 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_venta = keyset.getInt(1);
                    venta.setId_venta(id_venta);
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

    public static boolean modificar(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set id_cliente=? "
                    + "where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, venta.getClientes().getId_cliente());
                ps.setInt(2, venta.getId_venta());
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

    public static boolean eliminar(Ventas venta  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ventas where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, venta.getId_venta());
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