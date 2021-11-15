<%-- 
    Document   : Recuperar
    Created on : Sep 19, 2021, 10:57:20 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Recuperar password</title>
        <meta charset="UTF-8"/>
        <link href="styles/style_Recuperar.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com/%22%3E">
              <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
              <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style_Recuperar.css" />
    </head>
    <body>
        
        <%!
            String mensaje = "";

        %>

        <%
            //logH = (LogInServlet) request.getAttribute("logM");

            try {
                if (request.getAttribute("mensaje") != null) {
                    mensaje = String.valueOf(request.getAttribute("mensaje"));
                }
            } catch (Exception e) {

            }

        %>
        
        <h1>Recuperar contraseña</h1>
        <div>
            <form action="./RecuperarServlet" method="POST">
                <jsp:useBean id="servletRecuperar" scope="session" class="co.edu.unipiloto.servlet.RecuperarServlet" />
                Introduzca su correo: <input type="text" name="correo" placeholder="Ej: alguien@gmail.com"/><br>
                <input name="action" type="submit" value="Aceptar" class="login" style="margin-top: 10px"/>
                <p><%=mensaje%></p>
                <input name="action" type="submit" value="Volver..." class="login" style="margin-top: 40px"/>
            </form>
        </div>
        <footer>Copyright 2021 Cedd Oz</footer>
    </body>