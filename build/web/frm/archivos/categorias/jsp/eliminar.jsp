
<%@page import="controladores.CategoriasControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Categorias categoria = new Categorias();
    categoria.setId_categoria(id_categoria);
    
    if (CategoriasControlador.eliminar(categoria)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>