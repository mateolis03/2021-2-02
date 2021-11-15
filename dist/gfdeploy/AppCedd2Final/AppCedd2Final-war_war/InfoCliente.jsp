<%-- 
    Document   : InfoCliente
    Created on : Sep 26, 2021, 5:37:11 PM
    Author     : edwin
--%>

<%@page import="co.edu.unipiloto.entities.Cliente"%>
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
    <title>AGDT: Mis datos</title>
</head>

<body>
    <%!
            String idS, name, user, mensaje = "", email, loc, dir;
            Cliente cliente;
            HttpSession misession;
        %>

        <%
            //logH = (LogInServlet) request.getAttribute("logM");
            misession = request.getSession(true);
            cliente = (Cliente) misession.getAttribute("clienteActual");
            name = cliente.getNombre();
            user = cliente.getUsuario();
            //idS = logH.getCliente().getClienteid() + "";
            idS = cliente.getClienteid() + "";
            email = cliente.getEmail();
            loc = cliente.getCiudad() + ", " + cliente.getDepartamento();
            dir = cliente.getDireccion();

            try {
                if (request.getAttribute("mensaje") != null) {
                    mensaje = String.valueOf(request.getAttribute("mensaje"));
                }
            } catch (Exception e) {

            }

            //String usuario = request.getParameter("usuario");       
            //pedidos = logH.getCliente().getPedidosCollection();
            //pedidos = user.getPedidosCollection();
            /*myObject = (Objetos )misession.getAttribute("myObject");
            pedidos = myObject.getPedidosAll();*/
        %>
        <jsp:useBean id="servUser" scope="application" class="co.edu.unipiloto.servlet.UsuarioServlet" />
    <a><h1>Mi información<p>&nbsp;</p></h1></a>
    <nav>
        <ul>
            <a href="ClienteMenu.jsp"><li>Atrás</li></a>
        </ul>
    </nav>
    <div id="container">
        <div id="contentManager">
            <div id="profilePic"></div><br>
            Nombre de usuario<br>
            <p><%=user%></p><br>
            
            Nombre completo<br>
            <p><%=name%></p><br>
            
            Número de identificación<br>
            <p><%=idS%></p><br>
            
            E-mail<br>
            <p><%=email%></p><br>
            
            Ciudad<br>
            <p><%=loc%></p><br>
            
            Dirección<br>
            <p><%=dir%></p><br>
        </div>
    </div>
</body>
</html>
