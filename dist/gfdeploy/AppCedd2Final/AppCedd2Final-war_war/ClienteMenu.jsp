<%-- 
    Document   : ClienteMenu
    Created on : Sep 25, 2021, 9:23:20 PM
    Author     : edwin
--%>

<%@page import="co.edu.unipiloto.utilities.Objetos"%>
<%@page import="co.edu.unipiloto.entities.Cliente"%>
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
            String idS, name, mensaje = "";
            Cliente user;
            LogInServlet logH;
            HttpSession misession;
            Collection<Pedidos> pedidos;
            Objetos myObject;
        %>

        <%
            //logH = (LogInServlet) request.getAttribute("logM");
            misession = request.getSession(true);
            user = (Cliente) misession.getAttribute("clienteActual");
            name = user.getNombre();
            //idS = logH.getCliente().getClienteid() + "";
            idS = user.getClienteid() + "";

            try {
                if (request.getAttribute("mensaje") != null) {
                    mensaje = String.valueOf(request.getAttribute("mensaje"));
                }
            } catch (Exception e) {

            }

            //String usuario = request.getParameter("usuario");       
            //pedidos = logH.getCliente().getPedidosCollection();
            pedidos = user.getPedidosCollection();
            /*myObject = (Objetos )misession.getAttribute("myObject");
            pedidos = myObject.getPedidosAll();*/
        %>
        <%-- 
    <jsp:useBean id="ctlMenServlet" scope="application" class="co.edu.unipiloto.servlet.CltMenuServlet" />
        --%>


        <form action="./UsuarioServlet" method="post">


            <jsp:useBean id="servUser" scope="application" class="co.edu.unipiloto.servlet.UsuarioServlet" />


            <h1>Bienvenido Sr(a) <%=name%></h1>
            <h1><%=idS%></h1>

            <nav>
                <ul>
                    <li><a href="ClienteMenu.jsp">Inicio</a></li>
                    <li><a href="SolicitudE.jsp">Solicitar envío</a></li>
                    <li><a href="InfoCliente.jsp" id="<%=idS%>">Perfil de usuario</a></li> <%-- ojo --%>
                    <li><a href="LogOut.jsp">Salir</a></li>
                </ul>
            </nav>
            <div id="container">
                <div id="contentManager">
                    <p><%=mensaje%></p><br>

                    Lista de pedidos <input type="submit" value="Mostrar historial" name="action" /><br><br>
                    <%-- 
                    <jsp:setProperty name="ctlMenServlet" property="username" value="<%=user%>" />
                    <jsp:setProperty name="ctlMenServlet" property="id" value="<%=idS%>" />
                    --%>



                    <input type="hidden" name="identificador" value="<%=idS%>" readonly="readonly"/>   
                    <input type="text" name="pedido" value="ID"/> <input type="submit" value="Ver Info Detallada" name="action" /><input type="submit" value="Cancelar Solicitud" name="action" /><br>
                    <p></p>

                    <table border="1">
                        <th>ID</th>
                        <th>Tipo Pedido</th>
                        <th>Estado</th>
                        <th>Fecha de actualización</th>

                        <c:forEach items="<%=pedidos%>" var="pedido">
                            <c:if test="${pedido.ultimoEstado eq 'Sin Asignar'}">
                                <tr>
                                    <td>${pedido.pedidoid}</td>
                                    <td>${pedido.tipo}</td>
                                    <td>${pedido.ultimoEstado}</td>
                                    <td>${pedido.ultimaFecha}</td>
                                </tr>
                            </c:if>
                        </c:forEach> 
                    </table>
                </div>
            </div>

        </form>

    </body>
</html>
