<%@page import="modelos.Articulos"%>
<%@page import="modelos.Pedidos"%>
<%@page import="controladores.DetallesPedidosControlador"%>
<%@page import="modelos.DetallesPedidos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesPedidos detallepedido = DetallesPedidosControlador.buscarId(id_detallepedido);
    if (detallepedido != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallepedido = new DetallesPedidos();
        detallepedido.setId_detallepedido(0);
        
        Pedidos pedido = new Pedidos();
        pedido.setId_pedido(0);
        detallepedido.setPedidos(pedido);
        
        Articulos articulo = new Articulos();
        articulo.setId_articulo(0);
        articulo.setNombre_artiulo("");
        detallepedido.setArticulo(articulo);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detallepedido", String.valueOf(detallepedido.getId_detallepedido()));
    obj.put("id_pedido", String.valueOf(detallepedido.getPedidos().getId_pedido()));
    obj.put("id_articulo", String.valueOf(detallepedido.getArticulo().getId_articulo()));
    obj.put("nombre_articulo", detallepedido.getArticulo().getNombre_artiulo());
    obj.put("cantidad_articulopedido", String.valueOf(detallepedido.getCantidad_articulopedido()));
    
    out.print(obj);
    out.flush();
%>