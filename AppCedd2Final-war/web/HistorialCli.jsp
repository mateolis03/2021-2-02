<%-- 
    Document   : HistorialCli
    Created on : Nov 12, 2021, 12:05:49 AM
    Author     : edwin


    No imprime registros
--%>

<%@page import="java.util.Collection"%>
<%@page import="co.edu.unipiloto.entities.Pedidos"%>
<%@page import="co.edu.unipiloto.servlet.LogInServlet"%>
<%@page import="co.edu.unipiloto.entities.Cliente"%>
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
        <title>AGDT: Historial</title>
    </head>
    <body>
        <%!
            String idS, name, mensaje = "";
            Cliente userH;
            int size = 0;
            LogInServlet logH;
            HttpSession misession;
            Collection<Pedidos> pedidosA;
        %>

        <%
            //logH = (LogInServlet) request.getAttribute("logM");
            misession = request.getSession(true);
            userH = (Cliente) misession.getAttribute("clienteActual");
            name = userH.getNombre();
            //idS = logH.getCliente().getClienteid() + "";
            idS = userH.getClienteid() + "";
            misession.setAttribute("clienteActual", userH);

            try {
                if (request.getAttribute("mensaje") != null) {
                    mensaje = String.valueOf(request.getAttribute("mensaje"));
                }
            } catch (Exception e) {

            }

            //String usuario = request.getParameter("usuario");       
            //pedidos = logH.getCliente().getPedidosCollection();
            pedidosA = userH.getPedidosCollection();
            size = pedidosA.size();
        %>
        
        <form action="./UsuarioServlet" method="post"></form>

        <h1>Bienvenido Sr(a) <%=name%></h1>
        <h1><%=idS%></h1>

        <nav>
            <ul>
                <li><a href="ClienteMenu.jsp">Inicio</a></li>
                <li><a href="SolicitudE.jsp">Solicitar envío</a></li>
                <li><a href="HistorialCli.jsp">Ver Historial</a></li><%-- ojo --%>
                <li><a href="InfoCliente.jsp" id="<%=idS%>">Perfil de usuario</a></li> <%-- ojo --%>
                <li><a href="LogOut.jsp">Salir</a></li>
            </ul>
        </nav>
        <div id="container">
            <div id="contentManager">
                <p><%=mensaje%></p><br>

                Historial de pedidos<br><br>
                <%-- 
                <jsp:setProperty name="ctlMenServlet" property="username" value="<%=user%>" />
                <jsp:setProperty name="ctlMenServlet" property="id" value="<%=idS%>" />
                
                <input type="hidden" name="identificador" value="<%=idS%>" readonly="readonly"/>   
                <input type="text" name="pedido" value="ID"/> <input type="submit" value="Ver Info Detallada" name="action" />
                
                Long pedidoid, String tipo, Double alto, Double ancho, Double profundidad, Double peso, String nombreDestinatario, String telefonoDestinatario, 
                String ciudadDestinatario, String departamentoDestinatario, String direccionDestinatario, String ultimoEstado, Date ultimaFecha, Double totalPagar
                --%>

                    <c:set var = "size" scope = "session" value = "<%=size%>"/>
                    <c:choose>
                        <c:when test="${size > 0}">
                            <table border="1">
                                <th>ID</th>
                                <th>Tipo Pedido</th>
                                <th>Dimensiones</th>
                                <th>Ciudad del destinatario</th>
                                <th>Estado</th>
                                <th>Fecha de actualización</th>
                                <th>Costo</th>
                                
                                <c:forEach items="<%=pedidosA%>" var="pedi">
                                    <tr>
                                        <td>${pedi.pedidoid}</td>
                                        <td>${pedi.tipo}</td>
                                        <td>${pedi.alto}  ${pedi.alto}  ${pedi.alto}</td>
                                        <td>${pedi.ciudadDestinatario} ${pedi.departamentoDestinatario}</td>
                                        <td>${pedi.ultimoEstado}</td>
                                        <td>${pedi.ultimaFecha}</td>
                                        <td>${pedi.totalPagar}</td>
                                    </tr>

                                </c:forEach> 

                            </table>
                        </c:when>
                                    
                        <c:otherwise>
                            
                            <h3>Aún no ha realizado solicitudes...</h3>
                                
                        </c:otherwise>
                    </c:choose>
                    
                    
                </table>
            </div>
        </div>
    </body>
</html>
