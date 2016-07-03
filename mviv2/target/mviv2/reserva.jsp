<%-- 
    Document   : reserva.jsp
    Created on : Jan 22, 2016, 12:46:58 AM
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
        <style>
        span {
            color: red;
            font-weight: bold;
        }
        span.green {
            color:green;
            font-weight: bold;
        }
        </style>
    </head>
    <body>
        <%! String nombre,vuelo,requestPost; %>
        <jsp:useBean id="postR" class="middleware.HTTPRequest" scope="page" />
        <jsp:setProperty name="postR" property="*"></jsp:setProperty>
        <%
            vuelo = request.getParameter("vueloId");
            nombre = request.getParameter("nombre");

            if(!(vuelo.isEmpty() || nombre.isEmpty()))
            {
                if(nombre.matches("^[a-zA-Z ]*$"))
                {
                                    requestPost = postR.createJsonRequestReserva(vuelo, nombre);
                                    postR.doPostReserva(requestPost);

                                    %>
                                    <span class="green">Reserva registrada satisfactoriamente.</span>
                                    <jsp:include page="disponibilidadCliente.jsp" />
                                    <%
                }
                else
                {
                        %>
                        <span>¡Formato incorrecto! Nombre sólo admite texto</span>
                        <jsp:include page="disponibilidadCliente.jsp" />
                        <%
                }
            }
            else
            {
                %>
                <span>No pueden existir campos vacíos</span>
                <jsp:include page="disponibilidadCliente.jsp" />
                <%
            }
    
        %>

    </body>
</html>
