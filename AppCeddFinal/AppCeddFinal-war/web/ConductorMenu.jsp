<%-- 
    Document   : ConductorMenu
    Created on : Sep 25, 2021, 9:25:37 PM
    Author     : edwin
--%>

<%@page import="co.edu.unipiloto.entities.Conductor"%>
<%@page import="java.util.Collection"%>
<%@page import="co.edu.unipiloto.entities.Pedidos"%>
<%@page import="co.edu.unipiloto.servlet.LogInServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200&display=swap" rel="stylesheet">
        <link href="styles/drvMenu.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/drvMenu.css" />
        <title>AGDT: Inicio</title>
    </head>

    <body>

        <%!
            String idS, user;
            LogInServlet logC;
            Collection<Pedidos> pedidos;
            Conductor c;
        %>

        <%
            logC = (LogInServlet) request.getAttribute("logM");
            c = logC.getConductor();
            user = c.getNombre();
            idS = c.getId()+ "";

            //String usuario = request.getParameter("usuario");
            pedidos = c.getPedidosCollection();
        %>
        
        
        <jsp:useBean id="conMenServlet" scope="application" class="co.edu.unipiloto.servlet.ConMenuServ" />
        <form action="./ConMenuServ" method="POST">

            <h1>Bienvenido <p><%= user%></p></h1>
            <nav>
                <ul>
                    <li><a href="InfoCliente.jsp">Perfil de usuario</a></li> <%-- ojo No esta creado--%>
                    <li><a href="index.html">Salir</a></li>
                </ul>
            </nav>
            <div id="container">
                <div id="contentManager">
                    Lista de pedidos<br>
                    <jsp:setProperty name="conMenServlet" property="name" value="<%=user%>" />
                    <jsp:setProperty name="conMenServlet" property="idS" value="<%=idS%>" />
                    
                    <input type="text" name="identificador" value="<%=idS%>" readonly="readonly"/>
                    <input type="text" name="pedido" value="ID"/> <select name="eleccion" size="1">
                        <option>En camino</option>
                        <option>Demorado</option>
                        <option>Entregado</option>
                        <option>Cancelado</option>
                    </select><input type="submit" value="Actualizar" name="action" />

                    <table border="1">
                        <th>ID</th>
                        <th>Tipo Pedido</th>
                        <th>Nombre destinatario</th>
                        <th>Dirección entrega</th>
                        <th>Estado</th>

                        <c:forEach items="<%=pedidos%>" var="pedid">
                            <tr>
                                <td>${pedid.id}</td>
                                <td>${pedid.tipo}</td>
                                <td>${pedid.nombreRemitente}</td>
                                <td>${pedid.direccionRemitente}</td>
                                <td>${pedid.estadoEnvio}</td>
                            </tr>
                        </c:forEach> 
                    </table>
                </div>
            </div>
        </form>
    </body>
</html>
