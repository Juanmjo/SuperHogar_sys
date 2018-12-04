
<%@page import="controladores.MarcasControlador"%>
<%@page import="modelos.Marcas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);
    
    if (MarcasControlador.eliminar(marca)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>