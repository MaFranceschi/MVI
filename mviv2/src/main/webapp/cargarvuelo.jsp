<%-- 
    Document   : cargarvuelo
    Created on : Jan 17, 2016, 12:19:37 AM
    Author     : MarcoA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>Cargar vuelo</title>
    </head>
    <body>
        <h1>Ingresar un nuevo Vuelo</h1>
        <form action="cargar.jsp" method="POST">
            <div>
                <label>Línea aérea</label>
                <input type="text" name="linea" />
            </div>
            <div>
                <label>Fecha</label>
                <input type="date" name="fecha" placeholder="MM/DD/YYYY"/>
            </div>
            <div>
                <label>Hora</label>
                <input type="time" name="hora" placeholder="HH:MM"/>
            </div>
            <div>
                <label>Capacidad</label>
                <input type="number" name="capacidad"/>
            </div>
            <div>
                <label>Origen</label>
                <input type="text" name="origen"/>
            </div>
            <div>
                <label>Destino</label>
                <input type="text" name="destino"/>
            </div>
            <div>
                <label>Precio</label>
                <input type="number" name="precio"/>
            </div>
            <div>
                <input type="submit" value="Cargar vuelo"/>
            </div>
        </form>
        <a href="administrador.jsp">Regresar</a>
    </body>
</html>
