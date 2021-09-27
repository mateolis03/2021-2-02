<%-- 
    Document   : ClienteMenu
    Created on : Sep 25, 2021, 9:23:20 PM
    Author     : edwin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="co.edu.unipiloto.entities.Pedidos"%>
<%@page import="java.util.Collection"%>
<%@page import="co.edu.unipiloto.servlet.LogInServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200&display=swap" rel="stylesheet">
        <link href="styles/cltMenu.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/cltMenu.css" />
        <title>AGDT: Inicio</title>
    </head>

    <body>
        <%!
        String idS, user;
        LogInServlet logH;
        Collection<Pedidos> pedidos;
        %>
        
        <%
            logH = (LogInServlet) request.getAttribute("logM");
            user = logH.getCliente().getNombre();
            idS = logH.getCliente().getId() + "";

            //String usuario = request.getParameter("usuario");
            pedidos= logH.getCliente().getPedidosCollection();
        %>
        <jsp:useBean id="ctlMenServlet" scope="application" class="co.edu.unipiloto.servlet.CltMenuServlet" />
        
        <form action="./CltMenuServlet" method="post">
            
                
            <h1>Bienvenido Sr(a) <%=user%> <p></p></h1>
            <h1><%=idS%></h1>
                
            <nav>
                <ul>
                    <li><a href="InfoCliente.jsp" id="<%=idS%>">Perfil de usuario</a></li> <%-- ojo --%>
                    <li><a href="SolicitudE.jsp">Solicitar envío</a></li>
                    <li><a href="index.html">Salir</a></li>
                </ul>
            </nav>
            <div id="container">
                <div id="contentManager">
                    Lista de pedidos<br>
                    <jsp:setProperty name="ctlMenServlet" property="username" value="<%=user%>" />
                    <jsp:setProperty name="ctlMenServlet" property="id" value="<%=idS%>" />
                        
                    
                    <input type="hidden" name="identificador" value="<%=idS%>" readonly="readonly"/>   
                    <input type="text" name="pedido" value="ID"/> <input type="submit" value="Ver Info" name="action" /><input type="submit" value="Eliminar" name="action" />
                    
                    <table border="1">
                        <th>ID</th>
                        <th>Tipo Pedido</th>
                        <th>Nombre destinatario</th>
                        <th>Estado</th>
                        
                        <c:forEach items="<%=pedidos%>" var="pedido">
                            <tr>
                                <td>${pedido.id}</td>
                                <td>${pedido.tipo}</td>
                                <td>${pedido.nombreRemitente}</td>
                                <td>${pedido.estadoEnvio}</td>
                            </tr>
                        </c:forEach> 
                    </table>
                </div>
            </div>

        </form>

    </body>
</html>
