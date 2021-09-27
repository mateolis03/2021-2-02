<%-- 
    Document   : Registrar
    Created on : Sep 19, 2021, 10:38:25 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200&display=swap" rel="stylesheet">
    <link href="styles/registerStyle.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/registerStyle.css" />
    <title>Registrar</title>
</head>

<body>
    <nav><a href="LogIn.jsp">&#60; Inicio</a></nav>
    <div id="formContainer">
        <h1>Registro</h1>
        <form action="./RegistrarServlet" method="POST">
            <jsp:useBean id="servlet" scope="session" class="co.edu.unipiloto.servlet.RegistrarServlet" />
            <label for="fname">Nombre completo:</label><br>
            <input type="text" id="fname" name="fname"><br><br>
            <label for="lname">Numero de documento:</label><br>
            <input type="text" id="lname" name="doc"><br><br>
            <label for="lname">Correo electronico:</label><br>
            <input type="text" id="lname" name="mail"><br><br>
            <label for="lname">Telefono:</label><br>
            <input type="text" id="lname" name="telefono"><br><br>
            <label for="lname">Direccion:</label><br>
            <input type="text" id="lname" name="direccion"><br><br>
            <label for="lname">Usuario acceso:</label><br>
            <input type="text" id="lname" name="usuario"><br><br>
            <label for="lname">Password:</label><br>
            <input type="password" id="lname" name="password"><br><br>
            <label for="lname">Confirmacion Password:</label><br>
            <input type="password" id="lname" name="passwordC"><br><br>
            <input type="submit" value="Registrarse" class="regButton">
            <p><jsp:getProperty name="servlet" property="respuesta" /></p>
        </form>
    </div>
    <footer>Copyright 2021 Cedd Oz</footer>
</body>

</html>
