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
        
    <nav><a href="LogIn.jsp">&#60; Inicio</a></nav>
    <div id="formContainer">
        <h1>Registro</h1>
        <form action="./RegistrarServlet" method="POST">
            <%-- 
(Long clienteid, String nombre, BigInteger cedula, String email,
String telefono, String ciudad, String departamento, String direccion, String usuario, String password)
--%>
            <jsp:useBean id="servlet" scope="session" class="co.edu.unipiloto.servlet.RegistrarServlet" />
            <label for="fname">Nombre completo:</label><br>
            <input type="text" id="fname" name="fname"><br><br>
            <label for="lname">Número de documento:</label><br>
            <input type="text" id="lname" name="doc"><br><br>
            <label for="lname">Correo electrónico:</label><br>
            <input type="text" id="lname" name="mail"><br><br>
            <label for="lname">Teléfono:</label><br>
            <input type="text" id="lname" name="telefono"><br><br>
            <label for="lname">Ciudad:</label><br>
            <input type="text" id="lname" name="ciudad"><br><br>
            <label for="lname">Departamento:</label><br>
            <input type="text" id="lname" name="departamento"><br><br>
            <label for="lname">Dirección:</label><br>
            <input type="text" id="lname" name="direccion"><br><br>
            <label for="lname">Usuario acceso:</label><br>
            <input type="text" id="lname" name="usuario"><br><br>
            <label for="lname">Password:</label><br>
            <input type="password" id="lname" name="password"><br><br>
            <label for="lname">Confirmar Password:</label><br>
            <input type="password" id="lname" name="passwordC"><br><br>
            <input type="submit" value="Registrarse" class="regButton">
            <p><%=mensaje%></p>
        </form>
    </div>
    <footer>Copyright 2021 Cedd Oz</footer>
</body>

</html>
