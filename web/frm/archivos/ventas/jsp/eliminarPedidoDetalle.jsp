<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.DetallesVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleventa = Integer.parseInt(request.getParameter("id_detalleventa"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesVentas detalleventa = new DetallesVentas();
    detalleventa.setId_detalleventa(id_detalleventa);

    if (DetallesVentasControlador.eliminar(detalleventa)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>