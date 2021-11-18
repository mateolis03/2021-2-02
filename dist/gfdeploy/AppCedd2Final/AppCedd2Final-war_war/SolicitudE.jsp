<%-- 
    Document   : SolicitudE
    Created on : Sep 19, 2021, 11:02:08 PM
    Author     : david
--%>

<%@page import="co.edu.unipiloto.entities.Cliente"%>
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
        <title>Solicitar envío</title>
    </head>
    <BODY>

        <%!
            String idS, name, mensaje = "";
            Cliente user;
            HttpSession misession;
            int sizeStr;
        %>

        <%
            misession = request.getSession(true);
            mensaje = "Digite la información para la solicitud";
            user = (Cliente) misession.getAttribute("clienteActual");
            name = user.getNombre();
            //idS = logH.getCliente().getClienteid() + "";
            idS = user.getClienteid() + "";
            misession.setAttribute("clienteActual", user);

            try {
                if (request.getAttribute("mensaje") != null) {
                    mensaje = String.valueOf(request.getAttribute("mensaje"));
                }
            } catch (Exception e) {

            }

            sizeStr = mensaje.length();

        %>

        <nav><a href="ClienteMenu.jsp">&#60; Volver</a></nav>
        <div>
            <h1>Solicitud de envio, <%=name%></h1>
            <form action="./SolEnvServlet" method="get">
                <%-- 
String tipo, Double alto, Double ancho, Double profundidad, Double peso, 
String nombreDestinatario, String telefonoDestinatario, String ciudadDestinatario, String departamentoDestinatario, 
String direccionDestinatario,

                --%>
                <jsp:useBean id="solServlet" scope="application" class="co.edu.unipiloto.servlet.SolEnvServlet" />

                <c:set var = "tamS" scope = "session" value = "<%=sizeStr%>"/>
                <c:if test="${tamS != 0}">
                    <p><b>Alerta:</b> <%=mensaje%></p><br>
                </c:if>

                <label for="fname">Tipo paquete:</label><br>
                <input type="text" id="fname" name="tipo" placeholder="Ej: Electrodoméstico"><br>
                <label for="lname">Alto(m):</label><br>
                <input type="text" id="lname" name="alto" placeholder="Ej: 0.5"><br>
                <label for="lname">Ancho(m):</label><br>
                <input type="text" id="lname" name="ancho" placeholder="Ej: 1.2"><br>
                <label for="lname">Profundidad(m):</label><br>
                <input type="text" id="lname" name="profundidad" placeholder="Ej: 1"><br>	
                <label for="lname">Peso(g):</label><br>
                <input type="text" id="lname" name="peso" placeholder="Ej: 100.2"><br>

                <label for="lname">Nombre destinatario</label><br>
                <input type="text" id="lname" name="nombre" ><br>	
                <label for="lname">Telefono destinatario</label><br>
                <input type="text" id="lname" name="telefono" placeholder="Ej: 3124152314"><br>
                <label for="lname">Ciudad destinatario</label><br>
                <input type="text" id="lname" name="ciudad" ><br>
                <label for="lname">Departamento destinatario</label><br>
                <input type="text" id="lname" name="departamento" ><br>
                <label for="lname">Direccion destinatario</label><br>
                <input type="text" id="lname" name="direccion" placeholder="Ej: Calle 5 # 3 - 02"><br>	



                <input type="submit" value="Solicitar envio">
            </form>    		
        </div>





    </BODY>
</HTML>