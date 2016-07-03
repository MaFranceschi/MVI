<%-- 
    Document   : cargar
    Created on : Jan 17, 2016, 12:45:15 AM
    Author     : MarcoA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="middleware.HTTPRequest" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>Cargar vuelo</title>
    </head>
    <body>
        <%! String linea,fecha,hora,capacidad,origen,destino,precio,requestPost; %>
        <jsp:useBean id="post" class="middleware.HTTPRequest" scope="page" />
        <jsp:setProperty name="post" property="*"></jsp:setProperty>
        <%
            linea = request.getParameter("linea");
            fecha = request.getParameter("fecha");
            hora = request.getParameter("hora");
            capacidad = request.getParameter("capacidad");
            origen = request.getParameter("origen");
            destino = request.getParameter("destino");
            precio = request.getParameter("precio");
            
            if(!(linea.isEmpty() || origen.isEmpty() || destino.isEmpty() || fecha.isEmpty() || hora.isEmpty() || capacidad.isEmpty() || precio.isEmpty()))
            {
                if(linea.matches("^[a-zA-Z ]*$") && origen.matches("^[a-zA-Z ]*$") && destino.matches("^[a-zA-Z ]*$"))
                {
                    if(fecha.matches("^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$"))
                    {
                        if(hora.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"))
                        {
                            if(capacidad.matches("^[0-9]+$"))
                            {
                                if(precio.matches("^-?[0-9]+([,\\.][0-9]*)?$"))
                                {
                                    requestPost = post.createJsonRequestVuelo(linea, origen, destino, fecha, hora, capacidad, precio);
                                    post.doPostVuelo(requestPost);

                                    %>
                                    <span class="green">Vuelo registrado satisfactoriamente.</span>
                                    <jsp:include page="cargarvuelo.jsp" />
                                    <%
                                }
                                else
                                {
                                    %>
                                    <span>¡Formato incorrecto! El precio debe ser numérico</span>
                                    <jsp:include page="cargarvuelo.jsp" />
                                    <%
                                }

                            }
                            else
                            {
                                %>
                                <span>¡Formato incorrecto! La capacidad debe ser un número entero</span>
                                <jsp:include page="cargarvuelo.jsp" />
                                <%
                            }
                        }
                        else
                        {
                            %>
                            <span>¡Formato incorrecto! Hora debe ser expresada en hh:mm</span>
                            <jsp:include page="cargarvuelo.jsp" />
                            <%
                        }
                    }
                    else
                    {
                        %>
                        <span>¡Formato incorrecto! La fecha debe estar en formato mm/dd/yyyy</span>
                        <jsp:include page="cargarvuelo.jsp" />
                        <%
                    }
                    
                }
                else
                {
                        %>
                        <span>¡Formato incorrecto! Origen, Destino y Línea Aérea sólo admiten texto</span>
                        <jsp:include page="cargarvuelo.jsp" />
                        <%
                }
            }
            else
            {
                %>
                <span>No pueden existir campos vacíos</span>
                <jsp:include page="cargarvuelo.jsp" />
                <%
            }
    
        %>
    </body>
</html>
