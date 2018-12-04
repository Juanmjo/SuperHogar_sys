<%@page import="controladores.FacturaDetalleComprasControlador"%>
<%@page import="modelos.FacturaDetalleCompras"%>
<%@page import="modelos.Articulos"%>
<%@page import="controladores.FacturaComprasControlador"%>
<%@page import="modelos.FacturaCompras"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_detalle_compra = Integer.parseInt(request.getParameter("id_factura_detalle_compra"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    FacturaDetalleCompras facturadetallecompra = FacturaDetalleComprasControlador.buscarId(id_factura_detalle_compra);
    if (facturadetallecompra != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        facturadetallecompra = new FacturaDetalleCompras();
        facturadetallecompra.setId_factura_detalle_compra(0);
        facturadetallecompra.setSubtotal_compra(0);
        
        FacturaCompras facturacompra = new FacturaCompras();
        facturacompra.setId_factura_compra(0);
        facturadetallecompra.setFacturacompra(facturacompra);
        
        Articulos articulo = new Articulos();
        articulo.setId_articulo(0);
        articulo.setNombre_artiulo("");
        facturadetallecompra.setArticulo(articulo);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_factura_detalle_compra", String.valueOf(facturadetallecompra.getId_factura_detalle_compra()));
    obj.put("id_factura_compra", String.valueOf(facturadetallecompra.getFacturacompra().getId_factura_compra()));
    obj.put("id_articulo", String.valueOf(facturadetallecompra.getArticulo().getId_articulo()));
    obj.put("nombre_articulo", facturadetallecompra.getArticulo().getNombre_artiulo());
    obj.put("precio_compra", facturadetallecompra.getArticulo().getPrecio_compra());
    obj.put("cantidad_compra", String.valueOf(facturadetallecompra.getCantidad_compra()));
    obj.put("subtotal_compra", String.valueOf(facturadetallecompra.getSubtotal_compra()));
    
    out.print(obj);
    out.flush();
%>