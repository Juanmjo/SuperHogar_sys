
<%@page import="controladores.IvasControlador"%>
<%@page import="modelos.Ivas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int porcentaje_iva = Integer.parseInt(request.getParameter("porcentaje_iva"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Ivas iva = new Ivas();
    iva.setPorcentaje_iva(porcentaje_iva);
    
   IvasControlador.buscarIva(iva);
    if (iva.getId_iva()==0){
        tipo = "success";
        mensaje = "Iva repetida.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    out.print(obj);
    out.flush();
%>
