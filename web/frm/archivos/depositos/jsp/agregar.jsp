
<%@page import="controladores.DepositosControlador"%>
<%@page import="modelos.Depositos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_deposito = Integer.parseInt(request.getParameter("id_deposito"));
    String nombre_deposito = request.getParameter("nombre_deposito");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Depositos deposito = new Depositos();
    deposito.setId_deposito(id_deposito);
    deposito.setNombre_deposito(nombre_deposito);
    
    if (DepositosControlador.agregar(deposito)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

