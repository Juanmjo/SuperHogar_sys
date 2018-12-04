
<%@page import="controladores.TipoFacturasControlador"%>
<%@page import="modelos.TipoFacturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    TipoFacturas tipo_factura = new TipoFacturas();
    tipo_factura.setId_tipo_factura(id_tipo_factura);
    
   TipoFacturasControlador.buscarId(tipo_factura);
    if (tipo_factura.getId_tipo_factura()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_tipo_factura", tipo_factura.getId_tipo_factura());
    obj.put("nombre_tipo_factura", tipo_factura.getNombre_tipo_factura());
    out.print(obj);
    out.flush();
%>
