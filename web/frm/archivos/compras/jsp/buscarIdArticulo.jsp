
<%@page import="controladores.ArticulosControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    
   ArticulosControlador.buscarId(articulo);
    if (articulo.getId_articulo()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_articulo", articulo.getId_articulo());
    obj.put("nombre_articulo", articulo.getNombre_artiulo());
    obj.put("descripcion_articulo", articulo.getDescripcion_articulo());
    obj.put("precio_unitario", articulo.getPrecio_unitario());
    obj.put("precio_venta", articulo.getPrecio_venta());
    obj.put("precio_compra", articulo.getPrecio_compra());
    //obj.put("id_iva", articulo.getIva().getId_iva());
    //obj.put("porcentaje_iva", articulo.getIva().getPorcentaje_iva());
    
    //obj.put("codigo_articulo", articulo.getCodigo_articulo());
    obj.put("id_marca", articulo.getMarcas().getId_marca());
    obj.put("nombre_marca", articulo.getMarcas().getNombre_marca());
    //obj.put("id_categoria", articulo.getCategoria().getId_categoria());
    //obj.put("nombre_categoria", articulo.getCategoria().getNombre_categoria());
    out.print(obj);
    out.flush();
%>
