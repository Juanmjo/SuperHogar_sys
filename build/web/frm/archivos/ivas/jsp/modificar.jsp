<%@page import="controladores.IvasControlador"%>
<%@page import="modelos.Ivas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_iva = Integer.parseInt(request.getParameter("id_iva"));
    int porcentaje_iva = Integer.parseInt(request.getParameter("porcentaje_iva"));
    String nombre_iva = request.getParameter("nombre_iva");
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Ivas iva = new Ivas();
    iva.setId_iva(id_iva);
    iva.setNombre_iva(nombre_iva);
    iva.setPorcentaje_iva(porcentaje_iva);
    
    if (IvasControlador.modificar(iva)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
