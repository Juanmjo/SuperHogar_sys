
<%@page import="controladores.IvasControlador"%>
<%@page import="modelos.Ivas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_iva = Integer.parseInt(request.getParameter("id_iva"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Ivas iva = new Ivas();
    iva.setId_iva(id_iva);
    
    if (IvasControlador.eliminar(iva)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>