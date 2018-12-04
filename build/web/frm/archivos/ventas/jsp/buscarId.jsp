<%@page import="utiles.Utiles"%>
<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
     String sfecha_venta = request.getParameter("fecha_venta");
    java.sql.Date fecha_venta = Utiles.stringToSqlDate(sfecha_venta);
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Ventas ventas = VentasControlador.buscarId(id_venta);
    if (ventas != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        ventas = new Ventas();
        ventas.setId_venta(0);
        ventas.setFecha_venta(fecha_venta);
  
        Clientes cliente = new Clientes();
        ventas.setClientes(cliente);
        }
    
    String contenido_detalle = DetallesVentasControlador.buscarIdVenta(id_venta);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_venta", String.valueOf(ventas.getId_venta()));
    obj.put("fecha_venta", String.valueOf(ventas.getFecha_venta()));
    obj.put("id_cliente", String.valueOf(ventas.getClientes().getId_cliente()));
    obj.put("nombre_cliente", ventas.getClientes().getNombre_cliente());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>