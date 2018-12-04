
<%@page import="controladores.RubrosControlador"%>
<%@page import="modelos.Rubros"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_rubro = Integer.parseInt(request.getParameter("id_rubro"));
    String nombre_rubro = request.getParameter("nombre_rubro");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Rubros rubro = new Rubros();    
    rubro.setId_rubro(id_rubro);
    rubro.setNombre_rubro(nombre_rubro);
    
    if (RubrosControlador.agregar(rubro)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

