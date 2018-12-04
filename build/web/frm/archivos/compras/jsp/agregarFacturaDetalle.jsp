
<%@page import="controladores.FacturaDetalleComprasControlador"%>
<%@page import="modelos.FacturaCompras"%>
<%@page import="modelos.FacturaDetalleCompras"%>

<%@page import="modelos.Articulos"%>
<%--<%@page import="modelos.Pedidos"%>--%>
<%--<%@page import="modelos.DetallesPedidos"%>--%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int id_factura_compra = Integer.parseInt(request.getParameter("id_factura_compra"));

    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    int cantidad_compra = Integer.parseInt(request.getParameter("cantidad_compra"));
    int subtotal_compra = Integer.parseInt(request.getParameter("subtotal_compra"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    FacturaDetalleCompras facturadetallecompra = new FacturaDetalleCompras();
    //detallepedido.setId_detallepedido(id_detallepedido);
    facturadetallecompra.setCantidad_compra(cantidad_compra);
    facturadetallecompra.setSubtotal_compra(subtotal_compra);

    FacturaCompras facturacompra = new FacturaCompras();
    facturacompra.setId_factura_compra(id_factura_compra);

    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);

    facturadetallecompra.setFacturacompra(facturacompra);
    facturadetallecompra.setArticulo(articulo);
   
    if (FacturaDetalleComprasControlador.agregar(facturadetallecompra)) {
        tipo = "success";
        mensaje = "Datos agregados.";

    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>