<%-- 
    Document   : InfoCliente
    Created on : Sep 26, 2021, 5:37:11 PM
    Author     : edwin
--%>


<%@page import="co.edu.unipiloto.utilities.Objetos"%>
<%@page import="java.util.Arrays"%>
<%@page import="co.edu.unipiloto.entities.session.PedidosFacade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.edu.unipiloto.entities.session.PedidosFacadeLocal"%>
<%@page import="java.util.Collection"%>
<%@page import="co.edu.unipiloto.servlet.LogInServlet"%>
<%@page import="co.edu.unipiloto.entities.Pedidos"%>
<%@page import="co.edu.unipiloto.entities.Conductor"%>
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
        <title>AGDT: Aplicar a Solicitudes</title>
    </head>

    <body>
        <%!
            String idS, name, mensaje = "";
            PedidosFacadeLocal pedidosFacade = new PedidosFacade();
            Conductor user;
            LogInServlet logH;
            HttpSession misessionCon;
            Objetos myObj;
            Collection<Pedidos> pedidosAll = new ArrayList();
        %>

        <%
            //logH = (LogInServlet) request.getAttribute("logM");
            misessionCon = request.getSession(true);
            user = (Conductor) misessionCon.getAttribute("conductorActual");
            name = user.getNombre();
            //idS = logH.getCliente().getClienteid() + "";
            idS = user.getConductorid() + "";
            misessionCon.setAttribute("conductorActual", user);

            try {
                if (request.getAttribute("mensaje") != null) {
                    mensaje = String.valueOf(request.getAttribute("mensaje"));
                }
            } catch (Exception e) {

            }

            //String usuario = request.getParameter("usuario");       
            //pedidos = logH.getCliente().getPedidosCollection();
            
            myObj = (Objetos) misessionCon.getAttribute("myObject");    
            pedidosAll = myObj.getPedidosAll();
        %>

        <form action="./ConductorServlet" method="POST">

            <a><h1>Aplicar a solicitudes de transporte de carga<p>&nbsp;</p></h1></a>
            <nav>
                <ul>
                    <li><a href="ConductorMenu.jsp">Inicio</a></li>
                    <li><a href="AplicarSolCon.jsp">Aplicar a solicitudes</a></li>
                    <li><a href="InfoCliente.jsp">Perfil de usuario</a></li> <%-- ojo No esta creado--%>
                    <li><a href="LogOut.jsp">Salir</a></li>
                </ul>
            </nav>
            <div id="container">
                <div id="contentManager">
                    <p><%=mensaje%></p><br>

                    Lista de pedidos<br>
                    <%-- 
                    <jsp:setProperty name="conMenServlet" property="name" value="<%=user%>" />
                    <jsp:setProperty name="conMenServlet" property="idS" value="<%=idS%>" />
                    
                    <input type="text" name="identificador" value="<%=idS%>" readonly="readonly"/>
                    <c:if test="${pedid.ultimoEstado eq "Sin Asignar"}">
                                                </c:if>

                    --%>



                    <input type="text" name="pedido" value="ID"/>
                    <input type="submit" value="Aplicar a Solicitud" name="action" /><br>

                    <table border="1">
                        <th>ID</th>
                        <th>Tipo Pedido</th>
                        <th>Nombre destinatario</th>
                        <th>Estado</th>
                        <th>Fecha de Actualización</th>

                        <c:forEach items="<%=pedidosAll%>" var="pedidis" >
                            
                                <tr>
                                    <td>${pedidis.pedidoid}</td>
                                    <td>${pedidis.tipo}</td>
                                    <td>${pedidis.nombreDestinatario}</td>
                                    <td>${pedidis.ultimoEstado}</td>
                                    <td>${pedidis.ultimaFecha}</td>
                                </tr>
                        </c:forEach> 
                    </table>
                </div>
            </div>
        </form>



    </body>
</html>
