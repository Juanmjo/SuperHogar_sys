<%@page import="utiles.Utiles"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente")); 
    
    

    
    String sfecha_venta = request.getParameter("fecha_venta");
    java.sql.Date fecha_venta = Utiles.stringToSqlDate(sfecha_venta);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    
    
    
    
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    venta.setClientes(cliente);
    venta.setFecha_venta(fecha_venta);
      
    
    if (VentasControlador.agregar(venta)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_venta", String.valueOf(venta.getId_venta()));
    out.print(obj);
    out.flush();
    
%>