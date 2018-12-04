<%@page import="modelos.Articulos"%>
<%@page import="controladores.StockControlador"%>
<%@page import="modelos.Stock"%>
<%@page import="modelos.FacturaDetalleCompras"%>
<%@page import="controladores.FacturaDetalleComprasControlador"%>
<%@page import="modelos.DetallesPedidos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_detalle_compra = Integer.parseInt(request.getParameter("id_factura_detalle_compra"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    int cantidad_compra = Integer.parseInt(request.getParameter("cantidad_compra"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    FacturaDetalleCompras facturadetallecompra = new FacturaDetalleCompras();
    facturadetallecompra.setId_factura_detalle_compra(id_factura_detalle_compra);

    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    Stock stock = new Stock();
    stock.setCantidad_stock(cantidad_compra);
    stock.setArticulo(articulo);
    StockControlador.descontar(stock);
    if (FacturaDetalleComprasControlador.eliminar(facturadetallecompra)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>