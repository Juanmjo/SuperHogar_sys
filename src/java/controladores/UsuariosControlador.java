/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Roles;
import modelos.Usuarios;
import utiles.Conexion;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.HashSet;
import utiles.Utiles;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jj
 */
public class UsuariosControlador {

    public static boolean agregar(Usuarios usuario) {

        boolean valor = false;
        if (Conexion.conectar()) {

           // String sql = "insert into usuarios (nombre_usuario,login_usuario,password_usuario,id_rol)"
             //       + " values ('" + usuario.getNombre_usuario()
             //       + "','" + usuario.getLogin_usuario()
              //      + "','" + Utiles.md5(Utiles.quitarGuiones(usuario.getPassword_usuario()))
              //      + "','" + usuario.getRol().getId_rol()+"')";
              
              String sql = "insert into usuarios(nombre_usuario, login_usuario, password_usuario,id_rol) "
                    + "values ('" + usuario.getNombre_usuario()
                    + "','" + usuario.getLogin_usuario()
                    + "','" + Utiles.md5(Utiles.quitarGuiones(usuario.getPassword_usuario()))
                    + "','" + usuario.getRol().getId_rol()+ "')"; 
           
                        try {
                            System.out.println("INSERTAR AAAA: "   + sql);
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error Agregar:" + ex);
            }
        }
        return valor;
    }

    public static Usuarios buscarId(Usuarios usuario) {
        if (Conexion.conectar()) {
            String sql ="select * from usuarios u,roles r" 
                    + " where " 
                    + "u.id_rol = r.id_rol"
                    + " and "
                    + "id_usuario = '"+usuario.getId_usuario()+"'";
            System.out.println("SQL : "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    
                    Roles rol = new Roles();
                    rol.setId_rol(rs.getInt("id_rol"));
                    rol.setNombre_rol(rs.getString("nombre_rol"));

                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                    usuario.setLogin_usuario(rs.getString("login_usuario"));
                    usuario.setPassword_usuario(rs.getString("password_usuario"));
                    
                    usuario.setRol(rol);
                } else {
                    
                    Roles rol = new Roles();
                    
                    usuario.setId_usuario(0);
                    usuario.setNombre_usuario("");
                    usuario.setLogin_usuario("");
                    usuario.setPassword_usuario("");
                    
                    rol.setId_rol(0);
                    rol.setNombre_rol("");
                    
                    usuario.setRol(rol);
                    
//return null;
//return usuario;
                }
            } catch (SQLException ex) {
                System.out.println("Error BuscarID: " + ex);
            }
        }
        return usuario;

    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "Select * from usuarios where upper(nombre_usuario) like '%"
                        + nombre.toUpperCase() + "%'" + " order by id_usuario offset " + offset + " limit "
                        + Utiles.REGISTROS_PAGINA;
                //   System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_usuario") + "</td>"
                                + "<td>" + rs.getString("nombre_usuario") + "</td>"
                                + "<td>" + rs.getString("login_usuario") + "</td>"
                                + "<td>" + rs.getString("password_usuario") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error :" + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error BuscarNombre:" + ex);
            }

        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(Usuarios usuario) {
        boolean valor = false;
        if (Conexion.conectar()) {

            String sql = "update usuarios set nombre_usuario= '" + usuario.getNombre_usuario()
                    + "', login_usuario='" + usuario.getLogin_usuario()
                    + "', password_usuario='" + Utiles.md5(Utiles.quitarGuiones(usuario.getPassword_usuario())) 
                    + "', id_rol='" + usuario.getRol().getId_rol() + "'"
                    + " where id_usuario=" + usuario.getId_usuario();
            // System.out.println("Mensj" + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                // System.out.println("Mensaje de tryRubroCOntrolador:" + valor);

            } catch (SQLException ex) {
                System.out.println("Error Modif:" + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Usuarios usuario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from usuarios where id_usuario=" + usuario.getId_usuario();

            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error BORRAR:" + ex);
            }
        }
        return valor;
    }

//    public static boolean validarAcceso(Usuarios usuario) {
//        boolean valor = false;
//        if (Conexion.conectar()) {
//
//            String sql = "Select * from usuarios where login_usuario='"
//                    + usuario.getLogin_usuario() + "'"
//                    + "and password_usuario='"
//                    + Utiles.md5(Utiles.quitarGuiones(usuario.getPassword_usuario())) + "'";
//
//            
//            try {
//                ResultSet rs = Conexion.getSt().executeQuery(sql);
//
//                if (rs.next()) {
//                    valor = true;
//                    
//                    
//                }
//            } catch (SQLException e) {
//
//                System.err.println("Error login:" + e);
//            }
//        }
//       
//        return valor;
//    }
    /*public static Usuarios validarAcceso(Usuarios usuario, HttpServletRequest request) {
        if (Conexion.conectar()) {
            try {
                String sql = "select * from usuarios "
                        + "where login_usuario=? and password_usuario=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setString(1, Utiles.quitarGuiones(usuario.getLogin_usuario()));
                    ps.setString(2,
                            Utiles.md5(Utiles.quitarGuiones(usuario.getPassword_usuario())));
                    ResultSet rs = ps.executeQuery();
                    //System.out.println("---> " + sql);
                    if (rs.next()) {
                        HttpSession sesion = request.getSession(true);
                        usuario = new Usuarios();
                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuario.setLogin_usuario(rs.getString("login_usuario"));
                        usuario.setPassword_usuario(rs.getString("password_usuario"));
                        sesion.setAttribute("usuarioLogueado", usuario);
                    } else {
                        usuario = null;
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return usuario;
    }
*/
    
    
   public static Usuarios validarAcceso(Usuarios usuario, HttpServletRequest request) {
        if (Conexion.conectar()) {
            try {
                String sql = "select * from usuarios u "
                        + "left join roles r on u.id_rol=r.id_rol "
                        + "where u.login_usuario=? and u.password_usuario=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setString(1, Utiles.quitarGuiones(usuario.getLogin_usuario()));
                    ps.setString(2, Utiles.md5(Utiles.quitarGuiones(usuario.getPassword_usuario())));
                    ResultSet rs = ps.executeQuery();
                    System.out.println("---> " + sql);
                    if (rs.next()) {
                        HttpSession sesion = request.getSession(true);
                        Roles rol = new Roles();
                        rol.setId_rol(rs.getInt("id_rol"));
                        rol.setNombre_rol(rs.getString("nombre_rol"));
                        usuario = new Usuarios();
                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuario.setLogin_usuario(rs.getString("login_usuario"));
                        usuario.setPassword_usuario(rs.getString("password_usuario"));
                        usuario.setRol(rol);

                        
                        sesion.setAttribute("usuarioLogueado", usuario);
                    } else {
                        usuario = null;
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return usuario;
    }
}
