
<%@page import="controladores.DepositosControlador"%>
<%@page import="modelos.Depositos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_deposito = Integer.parseInt(request.getParameter("id_deposito"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Depositos deposito = new Depositos();
    deposito.setId_deposito(id_deposito);
    
   DepositosControlador.buscarId(deposito);
    if (deposito.getId_deposito()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        deposito = new Depositos();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_deposito", deposito.getId_deposito());
    obj.put("nombre_deposito", deposito.getNombre_deposito());
    out.print(obj);
    out.flush();
%>
