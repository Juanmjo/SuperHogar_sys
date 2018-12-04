<%@page import="controladores.FacturaDetalleComprasControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="modelos.FacturaCompras"%>
<%@page import="modelos.FacturaDetalleCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_factura_detalle_compra = Integer.parseInt(request.getParameter("id_factura_detalle_compra"));
    int cantidad_compra = Integer.parseInt(request.getParameter("cantidad_compra"));
    int subtotal_compra = Integer.parseInt(request.getParameter("subtotal_compra"));
   int id_factura_compra = Integer.parseInt(request.getParameter("id_factura_compra"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo")); 

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    FacturaDetalleCompras facturadetallecompra = new FacturaDetalleCompras();
    facturadetallecompra.setId_factura_detalle_compra(id_factura_detalle_compra);
    facturadetallecompra.setCantidad_compra(cantidad_compra);
    facturadetallecompra.setSubtotal_compra(subtotal_compra);
    
    FacturaCompras facturacompra = new FacturaCompras();
    facturacompra.setId_factura_compra(id_factura_compra);
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    
    facturadetallecompra.setFacturacompra(facturacompra);
    facturadetallecompra.setArticulo(articulo);
      
    if (FacturaDetalleComprasControlador.modificar(facturadetallecompra)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>