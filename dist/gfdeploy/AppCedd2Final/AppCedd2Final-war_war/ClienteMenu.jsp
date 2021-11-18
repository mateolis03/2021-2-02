<%-- 
    Document   : ClienteMenu
    Created on : Sep 25, 2021, 9:23:20 PM
    Author     : edwin
--%>

<%@page import="java.util.ArrayList"%>
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
            int size, sizeStr;
        %>

        <%
            //logH = (LogInServlet) request.getAttribute("logM");
            misession = request.getSession(true);
            user = (Cliente) misession.getAttribute("clienteActual");
            name = user.getNombre();
            mensaje = "";
            pedidos = new ArrayList();
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
            for (Pedidos x : user.getPedidosCollection()) {
                if (!x.getUltimoEstado().equals("Cancelado") && !x.getUltimoEstado().equals("Entregado")) {
                    pedidos.add(x);
                }
            }
            size = pedidos.size();
            sizeStr = mensaje.length();
            /*myObject = (Objetos )misession.getAttribute("myObject");
            pedidos = myObject.getPedidosAll();*/
        %>
        <%-- 
    <jsp:useBean id="ctlMenServlet" scope="application" class="co.edu.unipiloto.servlet.CltMenuServlet" />
        --%>


        <form action="./UsuarioServlet" method="post">


            <jsp:useBean id="servUser" scope="session" class="co.edu.unipiloto.servlet.UsuarioServlet" />


            <h1>Bienvenid@ Sr(a) <p><%=name%></p></h1>
            <h1>C.C. <%=idS%></h1>



            <nav>
                <ul>
                    <li><a href="ClienteMenu.jsp" style="text-decoration:none">Inicio</a></li>
                    <li><a href="SolicitudE.jsp" style="text-decoration:none">Solicitar envío</a></li>
                    <li><a href="InfoCliente.jsp" id="<%=idS%>" style="text-decoration:none">Perfil de usuario</a></li> <%-- ojo --%>
                    <li><a href="./LogOutServlet" style="text-decoration:none">Cerrar Sesión</a></li>
                </ul>
            </nav>
            <div id="container">
                <div id="contentManager">
                    <c:set var = "tamS" scope = "session" value = "<%=sizeStr%>"/>
                    <c:if test="${tamS != 0}">
                        <p><b>Alerta:</b> <%=mensaje%></p><br>
                    </c:if>

                    <c:set var = "tam" scope = "session" value = "<%=size%>"/>

                    Lista de pedidos <input type="submit" value="Mostrar historial" name="action" /><br><br>
                    <%-- 
                    <jsp:setProperty name="ctlMenServlet" property="username" value="<%=user%>" />
                    <jsp:setProperty name="ctlMenServlet" property="id" value="<%=idS%>" />
                    --%>



                    <input type="hidden" name="identificador" value="<%=idS%>" readonly="readonly"/>   
                    <input type="text" name="pedido" value="" placeholder="ID"/> <input type="submit" value="Ver Info Detallada" name="action" /><input type="submit" value="Cancelar Solicitud" name="action" /><br>
                    <p></p>

                    <c:choose>
                        <c:when test="${tam != 0}">
                            <table border="1">
                                <th WIDTH="50">ID</th>
                                <th WIDTH="170">Tipo Pedido</th>
                                <th WIDTH="120">Estado</th>
                                <th WIDTH="400">Fecha de actualización</th>

                                <c:forEach items="<%=pedidos%>" var="pedido">

                                    <tr>
                                        <td style="text-align: center"><p>${pedido.pedidoid}</p></td>
                                        <td style="text-align: center"><p>${pedido.tipo}</p></td>
                                        <td style="text-align: center"><p>${pedido.ultimoEstado}</p></td>
                                        <td style="text-align: center"><p>${pedido.ultimaFecha}</p></td>
                                    </tr>

                                </c:forEach> 
                            </table>
                        </c:when>    
                        <c:otherwise>
                            <br><p>Usted no tiene pedidos en curso</p><br>

                            <p>Puede consultar la información detallada de un pedido pasado con el ID</p>
                        </c:otherwise>
                    </c:choose>



                </div>
            </div>

        </form>

    </body>
</html>
