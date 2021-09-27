<%-- 
    Document   : SolicitudE
    Created on : Sep 19, 2021, 11:02:08 PM
    Author     : david
--%>

<%@page import="co.edu.unipiloto.servlet.LogInServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="styles/style_solicitud.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<HTML>
    <head>
        <meta charset="UTF-8"/>
        <link href="styles/style_solicitud.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com/%22%3E">
              <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
              <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style_solicitud.css" />
    </head>
    <BODY>
        
        <nav><a href="LogIn.jsp">&#60; Volver</a></nav>
        <div>
            <h1>Solicitud de envio</h1>
            <form action="./SolEnvServlet" method="get">
                <jsp:useBean id="solServlet" scope="application" class="co.edu.unipiloto.servlet.SolEnvServlet" />
                <label for="fname">Tipo paquete:</label><br>
                <input type="text" id="fname" name="tipo"><br>
                <label for="lname">Alto(m):</label><br>
                <input type="text" id="lname" name="alto" ><br>
                <label for="lname">Ancho(m):</label><br>
                <input type="text" id="lname" name="ancho" ><br>
                <label for="lname">Profundidad(m):</label><br>
                <input type="text" id="lname" name="profundidad" ><br>	
                <label for="lname">Peso(g):</label><br>
                <input type="text" id="lname" name="peso" ><br>
                <label for="lname">Ingrese su número de documento:</label><br>
                <input type="text" id="lname" name="doc" ><br>
                <label for="lname">Nombre destinatario</label><br>
                <input type="text" id="lname" name="nombre" ><br>	
                <label for="lname">Telefono destinatario</label><br>
                <input type="text" id="lname" name="telefono" ><br>	
                <label for="lname">Direccion destinatario</label><br>
                <input type="text" id="lname" name="direccion" ><br>	
                <p><jsp:getProperty name="solServlet" property="salida" /></p>
                <input type="submit" value="Solicitar envio">
            </form>    		
        </div>





    </BODY>
</HTML>