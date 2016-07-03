<%-- 
    Document   : verificacion
    Created on : Jan 16, 2016, 11:22:28 PM
    Author     : MarcoA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>Verificacion</title>
    </head>
    <body>
        <%! String password; %>
        <% 
            password = request.getParameter("pass");
            if(password.equals("1234"))
            {%>
            <jsp:forward page="administrador.jsp" />                
            <%}
            else
            {%>
            Contraseña o usuario incorrecto
            <jsp:include page="login.jsp" />
            <%}    
        %>
     </body>
</html>
