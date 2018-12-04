<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.DetallesVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detalleventa = Integer.parseInt(request.getParameter("id_detalleventa"));
    int cantidad_articuloventa = Integer.parseInt(request.getParameter("cantidad_articuloventa"));
   int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo")); 

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    DetallesVentas detalleventa = new DetallesVentas();
    detalleventa.setId_detalleventa(id_detalleventa);
    detalleventa.setCantidad_articuloventa(cantidad_articuloventa);
    
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    
    detalleventa.setVentas(venta);
    detalleventa.setArticulo(articulo);
      
    if (DetallesVentasControlador.modificar(detalleventa)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>