<%-- 
    Document   : replica
    Created on : Jan 17, 2016, 1:40:42 AM
    Author     : MarcoA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="middleware.HTTPRequest" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <%! String optionSvrD, optionSvrR; %>
        <jsp:useBean id="postReplica" class="middleware.HTTPRequest" scope="page" />
        <jsp:setProperty name="postReplica" property="*"></jsp:setProperty>
        <%
            optionSvrD = request.getParameter("disponibilidad");
            optionSvrR = request.getParameter("reserva");
            
            if(optionSvrD != null && optionSvrR != null)
            {
             
                if((optionSvrD.equals("commit") && optionSvrR.equals("abort")) || (optionSvrR.equals("commit") && optionSvrD.equals("abort")))
                {%>
                <span>DUAL, debe haber dos Commits para realizar la replica</span>
                <jsp:include page="replicacion.jsp" />
                <%}
                else if(optionSvrD.equals("commit") && optionSvrR.equals("commit"))
                {
                    postReplica.doPostReplica();
                %>
                <span class="green">COMMIT, replica realizada satisfactoriamente</span>
                <jsp:include page="replicacion.jsp" />
                <%
                }
                else if(optionSvrD.equals("abort") && optionSvrR.equals("abort"))
                {%>
                <span>ABORT, replica cancelada</span>
                <jsp:include page="replicacion.jsp" />
                <%
                }
            }
            else
            {%>
                <span>Se debe seleccionar una opción para cada servidor</span>
                <jsp:include page="replicacion.jsp" />
            <%}

        %>
    </body>
</html>
