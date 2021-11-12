<%-- 
    Document   : InfoCliente
    Created on : Sep 26, 2021, 5:37:11 PM
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
            <a href="ModificarCliente.jsp" ><li>Modificar mis datos</li></a>
            <a href="LogIn.jsp"><li>Salir</li></a>
        </ul>
    </nav>
    <div id="container">
        <div id="contentManager">
            <div id="profilePic"></div><br>
            Nombre de Usuario<br>
            
            <p>Nombre</p><br>
            Nombre completo<br>
            <p>Nombre</p><br>
        </div>
    </div>
</body>
</html>
