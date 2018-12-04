
<%@page import="modelos.Marcas"%>
<%@page import="controladores.ArticulosControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    String nombre_articulo = request.getParameter("nombre_articulo");
    String descripcion_articulo = request.getParameter("descripcion_articulo");
    int precio_unitario = Integer.parseInt(request.getParameter("precio_unitario"));
    int precio_venta = Integer.parseInt(request.getParameter("precio_venta"));
    int precio_compra = Integer.parseInt(request.getParameter("precio_compra"));
    
    
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));
    String nombre_marca = request.getParameter("nombre_marca");
    
    Marcas marca = new Marcas();
    
    marca.setId_marca(id_marca);
    marca.setNombre_marca(nombre_marca);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    articulo.setNombre_artiulo(nombre_articulo);
    articulo.setDescripcion_articulo(descripcion_articulo);
    articulo.setPrecio_unitario(precio_unitario);
    articulo.setPrecio_venta(precio_venta);
    articulo.setPrecio_compra(precio_compra);
   
    
    articulo.setMarcas(marca);

    if (ArticulosControlador.agregar(articulo)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

