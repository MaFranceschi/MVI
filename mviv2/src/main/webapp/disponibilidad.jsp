<%-- 
    Document   : disponibilidad
    Created on : Jan 17, 2016, 12:18:52 AM
    Author     : MarcoA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List,middleware.Vuelo" %>
<%@page language="java" import="middleware.HTTPRequest" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>Disponibilidad</title>
    </head>
    <body>
        <jsp:useBean id="getD" class="middleware.HTTPRequest" scope="page" />
        <jsp:setProperty name="getD" property="*"></jsp:setProperty>
        <% List<Vuelo> listaVuelos = getD.doGetVuelos(); 
            request.setAttribute("listaVuelos",listaVuelos);
        %>
        <h1>Disponibilidad de Vuelos</h1>
        <table>
            <thead>
                <th>Id</th>
                <th>Línea</th>
                <th>Origen</th>
                <th>Destino</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Capacidad</th>
                <th>Precio</th>
            </thead>
            <tbody>
            <c:forEach items="${listaVuelos}" var="vuelo">
                    <tr>
                        <td><c:out value="${vuelo.id}" /></td>
                        <td><c:out value="${vuelo.linea}" /></td>
                        <td><c:out value="${vuelo.origen}" /></td>
                        <td><c:out value="${vuelo.destino}" /></td>
                        <td><c:out value="${vuelo.fecha}" /></td>
                        <td><c:out value="${vuelo.hora}" /></td>
                        <td><c:out value="${vuelo.capacidad}" /></td>
                        <td><c:out value="${vuelo.precio}" /></td>
                    </tr>
            </c:forEach>

            </tbody>
        </table>
        <a href="administrador.jsp">Regresar</a>

    </body>
</html>
