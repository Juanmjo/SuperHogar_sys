<%@page import="utiles.Utiles"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    String nombre_caja = request.getParameter("nombre_caja");
    Time fecha = 
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    caja.setNombre_caja(nombre_caja);
    
    if (CajasControlador.agregar(caja)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

