<%-- 
    Document   : LogIn
    Created on : Sep 19, 2021, 10:39:21 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8"/>
        <link href="styles/style.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com/%22%3E
              <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
              <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css" />
        <title>Inicio de sesión</title>
    </head>
    <body>
        <h1>Bienvenido al aplicativo de gestión de carga</h1>
        <div>
            <form>
                Nombre de usuario: <input name="usuario" type="text" class="loginText"/><br>
                <span  style="margin-left: 58px;">Contraseña:</span> <input name="password"type="text" class="loginText"/><br>
                <input name="action" type="submit" value="Ingresar" class="login"/><br>
                <span class="submitSpan">No tiene cuenta?</span>
                <input name="action" type="submit" value="Registrese" class="login" style="margin-top: -15px"/>
                <input name="action" type="submit" value="Olvide mi contraseña" class="login" style="margin-top: 30px;"/>
                
            </form>
        </div>
        <footer>Copyrigth 2021 Cedd Oz</footer>
    </body>
</html>
