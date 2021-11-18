<%-- 
    Document   : ClienteMenu
    Created on : Sep 25, 2021, 9:23:20 PM
    Author     : edwin
--%>

<%@page import="co.edu.unipiloto.entities.Conductor"%>
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/drvMenu.css" />
        <title>AGDT: Inicio</title>
    </head>

    <body>
        <%!
            String idS, name, mensaje = "";
            Conductor user;
            LogInServlet logH;
            HttpSession misessionCon;
            Objetos myObj;
            int size, sizeStr;
            Collection<Pedidos> arrayP = new ArrayList();
            ArrayList<Pedidos> arrayF;
        %>

        <%
            //logH = (LogInServlet) request.getAttribute("logM");
            misessionCon = request.getSession(true);
            mensaje = "";
            user = (Conductor) misessionCon.getAttribute("conductorActual");
            name = user.getNombre();
            //idS = logH.getCliente().getClienteid() + "";
            idS = user.getConductorid() + "";
            //misessionCon.setAttribute("conductorActual", user);

            try {
                if (request.getAttribute("mensaje") != null) {
                    mensaje = String.valueOf(request.getAttribute("mensaje"));
                }
            } catch (Exception e) {

            }

            //String usuario = request.getParameter("usuario");       
            //pedidosAll = logH.getPedidosFacade().findAll();
            myObj = (Objetos) misessionCon.getAttribute("myObject");
            arrayP = myObj.getPedidosAll();
            arrayF = new ArrayList();
             
            for (Pedidos a : arrayP) {
                if (a.getUltimoEstado().equals("Sin Asignar")) {
                    arrayF.add(a);
                }
            }
            size = arrayF.size();
            sizeStr = mensaje.length();
        %>
        <%-- 
    <jsp:useBean id="ctlMenServlet" scope="application" class="co.edu.unipiloto.servlet.CltMenuServlet" />
        --%>


        <form action="./AplicarSolServlet" method="post">


            <jsp:useBean id="servUser" scope="session" class="co.edu.unipiloto.servlet.UsuarioServlet" />


            <h1>Bienvenid@ Sr(a) <p><%=name%></p></h1>
            <h1>C.C. <%=idS%></h1>



            <nav>
                <ul>
                    <li><a href="PruConductor.jsp" style="text-decoration:none">Inicio</a></li>
                    <li><a href="PruAplicarSolCon.jsp" style="text-decoration:none">Aplicar a Solicitudes</a></li>
                    <li><a href="InfoConductor.jsp" style="text-decoration:none">Perfil de usuario</a></li> <%-- ojo No esta creado--%>
                    <li><a href="./LogOutServlet" style="text-decoration:none">Cerrar Sesión</a></li>
                </ul>
            </nav>
            <div id="container">
                <div id="contentManager">
                    <c:set var = "tamS" scope = "session" value = "<%=sizeStr%>"/>
                    <c:if test="${tamS != 0}">
                        <p><b>Alerta:</b> <%=mensaje%></p><br>
                    </c:if>
                    Lista de solicitudes<br>
                    <%-- 
                    <jsp:setProperty name="conMenServlet" property="name" value="<%=user%>" />
                    <jsp:setProperty name="conMenServlet" property="idS" value="<%=idS%>" />
                    
                    <input type="text" name="identificador" value="<%=idS%>" readonly="readonly"/>
                    <c:if test="${pedid.ultimoEstado eq "Sin Asignar"}">
                                                </c:if>

                    --%>

                    <c:set var = "pedidosA" scope = "session" value = "<%=arrayF%>"/>
                    <c:set var = "tam" scope = "session" value = "<%=size%>"/>

                    <c:choose>
                        <c:when test="${tam != 0}">
                            <p>Usted puede aplicar a ${tam} pedido(s)</p><br>

                            <input type="text" name="pedido" value="" placeholder="ID"/>
                            <input type="submit" value="Ver Información Detallada" name="action" />
                            <input type="submit" value="Aplicar a Solicitud" name="action" /><br><br>

                            <table border="1">
                                <th WIDTH="50">ID</th>
                                <th WIDTH="170">Tipo Pedido</th>
                                <th WIDTH="400">Nombre destinatario</th>
                                <th WIDTH="120">Estado</th>
                                <th WIDTH="400">Fecha de solicitud</th>

                                <c:forEach items="<%=arrayF%>" var="pedid" >
                                    <tr>
                                        <td style="text-align: center"><p>${pedid.pedidoid}</p></td>
                                        <td style="text-align: center"><p>${pedid.tipo}</p></td>
                                        <td style="text-align: center"><p>${pedid.nombreDestinatario}</p></td>
                                        <td style="text-align: center"><p>${pedid.ultimoEstado}</p></td>
                                        <td style="text-align: center"><p>${pedid.ultimaFecha}</p></td>
                                    </tr>
                                </c:forEach> 
                            </table>
                        </c:when>    
                        <c:otherwise>
                            <p>No hay solicitudes disponibles...</p><br>

                        </c:otherwise>
                    </c:choose>




                </div>
            </div>

        </form>

    </body>
</html>
