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
            String mensaje = "Ingrese sus datos para el registro";
            int sizeStr;
        %>

        <%
            //logH = (LogInServlet) request.getAttribute("logM");
            mensaje = "Ingrese sus datos para el registro";
            try {
                if (request.getAttribute("mensaje") != null) {
                    mensaje = String.valueOf(request.getAttribute("mensaje"));
                    sizeStr = mensaje.length();
                }else{
                    sizeStr = 0;
                }
            } catch (Exception e) {

            }
            
        %>
        
    <nav><a href="LogIn.jsp">&#60; Inicio</a></nav>
    <div id="formContainer" style="text-align: center">
        <h1>Registro</h1>
        <form action="./RegistrarServlet" method="POST">
            <%-- 
(Long clienteid, String nombre, BigInteger cedula, String email,
String telefono, String ciudad, String departamento, String direccion, String usuario, String password)
--%>
            <jsp:useBean id="servlet" scope="session" class="co.edu.unipiloto.servlet.RegistrarServlet" />
            
            <c:set var = "tamS" scope = "session" value = "<%=sizeStr%>"/>
                <c:if test="${tamS != 0}">
                    <p><b>Alerta:</b> <%=mensaje%></p><br>
                </c:if>
            
            <label for="fname">Nombre completo:</label><br>
            <input type="text" id="fname" name="fname"><br><br>
            <label for="lname">Número de documento:</label><br>
            <input type="text" id="lname" name="doc"><br><br>
            <label for="lname">Correo electrónico:</label><br>
            <input type="text" id="lname" name="mail" placeholder="Ej: alguien@gmail.com"><br><br>
            <label for="lname">Teléfono:</label><br>
            <input type="text" id="lname" name="telefono" placeholder="Ej: 3124567899"><br><br>
            <label for="lname">Ciudad:</label><br>
            <input type="text" id="lname" name="ciudad"><br><br>
            <label for="lname">Departamento:</label><br>
            <input type="text" id="lname" name="departamento"><br><br>
            <label for="lname">Dirección:</label><br>
            <input type="text" id="lname" name="direccion" placeholder="Ej: Calle 4 # 1 - 23"><br><br>
            <label for="lname">Usuario acceso:</label><br>
            <input type="text" id="lname" name="usuario" placeholder="Ej: taniafg"><br><br>
            <label for="lname">Password:</label><br>
            <input type="password" id="lname" name="password"><br><br>
            <label for="lname">Confirmar Password:</label><br>
            <input type="password" id="lname" name="passwordC"><br><br>
            <input type="submit" value="Registrarse" class="regButton">
        </form>
    </div>
    <footer>Copyright 2021 Cedd Oz</footer>
</body>

</html>
