<%@page import="modelos.EstadosCiviles"%>
<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    String nombre_cliente = request.getParameter("nombre_cliente");
    String apellido_cliente = request.getParameter("apellido_cliente");
    String ruc_cliente = request.getParameter("ruc_cliente");
    String ci_cliente = request.getParameter("ci_cliente");
    String direccion_cliente = request.getParameter("direccion_cliente");
    String telefono_cliente = request.getParameter("telefono_cliente");
    String correo_cliente = request.getParameter("correo_cliente");
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
   
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    
    EstadosCiviles estadocivil = new EstadosCiviles();
    
    estadocivil.setId_estadocivil(id_estadocivil);
    
   
    
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    cliente.setNombre_cliente(nombre_cliente);
    cliente.setApellido_cliente(apellido_cliente);
    cliente.setRuc_cliente(ruc_cliente);
    cliente.setCi_cliente(ci_cliente);
    cliente.setDireccion_cliente(direccion_cliente);
    cliente.setTelefono_cliente(telefono_cliente);
    cliente.setCorreo_cliente(correo_cliente);
    
    cliente.setEstadosciviles(estadocivil);
   
    if (ClientesControlador.modificar(cliente)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
