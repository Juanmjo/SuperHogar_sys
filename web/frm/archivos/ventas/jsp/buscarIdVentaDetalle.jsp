<%@page import="modelos.Articulos"%>
<%@page import="modelos.Ventas"%>
<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.DetallesVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleventa = Integer.parseInt(request.getParameter("id_detalleventa"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesVentas detalleventa = DetallesVentasControlador.buscarId(id_detalleventa);
    if (detalleventa != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleventa = new DetallesVentas();
        detalleventa.setId_detalleventa(0);
        
        Ventas venta = new Ventas();
        venta.setId_venta(0);
        detalleventa.setVentas(venta);
        
        Articulos articulo = new Articulos();
        articulo.setId_articulo(0);
        articulo.setNombre_artiulo("");
        detalleventa.setArticulo(articulo);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detalleventa", String.valueOf(detalleventa.getId_detalleventa()));
    obj.put("id_venta", String.valueOf(detalleventa.getVentas().getId_venta()));
    obj.put("id_articulo", String.valueOf(detalleventa.getArticulo().getId_articulo()));
    obj.put("nombre_articulo", detalleventa.getArticulo().getNombre_artiulo());
    obj.put("cantidad_articuloventa", String.valueOf(detalleventa.getCantidad_articuloventa()));
    
    out.print(obj);
    out.flush();
%>