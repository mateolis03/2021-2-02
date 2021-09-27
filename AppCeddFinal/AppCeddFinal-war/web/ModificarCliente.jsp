<%-- 
    Document   : modificarCliente
    Created on : Sep 26, 2021, 5:36:52 PM
    Author     : edwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8"/>
        <link href="styles/profileStyle.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com/%22%3E">
              <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
              <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/profileStyle.css" />
        <title>AGDT: Mis pedidos</title>
    </head>

    <body>
        <a><h1>Mis pedidos<p>&nbsp;</p></h1></a>
        <nav>
            <ul>
                <a href="InfoCliente.jsp"><li>Atras</li></a>
            </ul>
        </nav>
        <div id="container" style="height: fit-content !important;">
            <div id="contentManager">
                <form action="">
                    <label for="opcion"><p>Seleccione el dato que desea cambiar:</p></label>
                    <select id="opcion">
                        <option value="nombre">Nombre</option>
                        <option value="username">Nombre de usuario</option>
                        <option value="contraseña">Contraseña</option>
                        <option value="telefono">Telefono</option>
                        <option value="direccion">Dirección</option>
                        <option value="e-mail">Correo electronico</option>
                    </select>
                    <input type="submit" value="Aceptar" class="submit"/>
                </form>
            </div>
        </div>
    </body>
</html>
