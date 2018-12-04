
<%@page import="controladores.TipoFacturasControlador"%>
<%@page import="modelos.TipoFacturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    TipoFacturas tipo_factura = new TipoFacturas();
    tipo_factura.setId_tipo_factura(id_tipo_factura);
    
    if (TipoFacturasControlador.eliminar(tipo_factura)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>