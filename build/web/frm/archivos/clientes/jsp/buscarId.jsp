
<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    
   ClientesControlador.buscarId(cliente);
    if (cliente.getId_cliente()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        cliente = new Clientes();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_cliente", cliente.getId_cliente());
    obj.put("nombre_cliente", cliente.getNombre_cliente());
    obj.put("apellido_cliente", cliente.getApellido_cliente());
    obj.put("ruc_cliente", cliente.getRuc_cliente());
    obj.put("ci_cliente", cliente.getCi_cliente());
    obj.put("direccion_cliente", cliente.getDireccion_cliente());
    obj.put("telefono_cliente", cliente.getTelefono_cliente());
    obj.put("correo_cliente", cliente.getCorreo_cliente());
    
    obj.put("id_estadocivil", cliente.getEstadosciviles().getId_estadocivil());
    obj.put("nombre_estadocivil", cliente.getEstadosciviles().getNombre_estadocivil());
    
    out.print(obj);
    out.flush();
%>
