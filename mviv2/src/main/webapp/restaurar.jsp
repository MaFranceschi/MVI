<%-- 
    Document   : restaurar
    Created on : Jan 17, 2016, 12:20:21 AM
    Author     : MarcoA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>Restauracion</title>
    </head>
    <body>
        <h1>Restaurar información</h1>
        <form action="restaura.jsp" method="POST">
            <div>
                <label><strong>Servidor Disponibilidad</strong></label>
                <input type="radio" name="disponibilidad" value="commit" /> Commit
                <input type="radio" name="disponibilidad" value="abort" /> Abort                
            </div>
            <div>
                <label><strong>Servidor Reservas</strong></label>
                <input type="radio" name="reserva" value="commit" /> Commit
                <input type="radio" name="reserva" value="abort" /> Abort                
            </div>
            <div>
                <input type="submit" value="Restaurar" />                
            </div>
        </form>
        <a href="administrador.jsp">Regresar</a>       
    </body>
</html>
