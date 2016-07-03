<%-- 
    Document   : reservas
    Created on : Jan 17, 2016, 12:19:52 AM
    Author     : MarcoA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List,middleware.Reserva" %>
<%@page language="java" import="middleware.HTTPRequest" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>Reservas</title>
    </head>
    <body>
        <jsp:useBean id="getR" class="middleware.HTTPRequest" scope="page" />
        <jsp:setProperty name="getR" property="*"></jsp:setProperty>
        <% List<Reserva> listaReservas = getR.doGetReservas(); 
            request.setAttribute("listaReservas",listaReservas);
        %>
        <h1>Reservas generadas</h1>
        <table>
            <thead>
                <th>Id Vuelo</th>
                <th>Id Reserva</th>
                <th>Nombre</th>
            </thead>
            <tbody>
            <c:forEach items="${listaReservas}" var="reserva">
                    <tr>
                        <td><c:out value="${reserva.vuelo}" /></td>
                        <td><c:out value="${reserva.reserva}" /></td>
                        <td><c:out value="${reserva.nombre}" /></td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="administrador.jsp">Regresar</a>

    </body>
</html>
