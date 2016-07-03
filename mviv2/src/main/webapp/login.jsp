<%-- 
    Document   : login
    Created on : Jan 16, 2016, 11:04:54 PM
    Author     : MarcoA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>Iniciar sesion</title>
    </head>
    <body>
        <form action="verificacion.jsp" method="POST">
            <label>Ingrese su clave de administrador</label>
            <input type="password" name="pass"/>
            <input type="submit" value="Ingresar">
        </form>
        <a href="index.jsp">Regresar</a>
    </body>
</html>
