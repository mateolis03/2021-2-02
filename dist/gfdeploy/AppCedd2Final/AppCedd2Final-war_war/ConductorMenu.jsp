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
            String idS, name, mensaje = "";
            Conductor user;
            LogInServlet logH;
            HttpSession misessionCon;
            Collection<Pedidos> pedidos;
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
            pedidos = user.getPedidosCollection();
        %>


        <jsp:useBean id="conMenServlet" scope="application" class="co.edu.unipiloto.servlet.ConductorServlet" />
        <form action="./ConductorServlet" method="POST">

            <h1>Bienvenido Sr(a). <p><%= name%></p></h1>
            <nav>
                <ul>
                    <li><a href="ConductorMenu.jsp">Inicio</a></li>
                    <li><a href="AplicarSolCon.jsp">Aplicar a Solicitudes</a></li>
                    <li><a href="InfoCliente.jsp">Perfil de usuario</a></li> <%-- ojo No esta creado--%>
                    <li><a href="LogOut.jsp">Salir</a></li>
                </ul>
            </nav>
            <div id="container">
                <div id="contentManager">
                    <p><%=mensaje%></p><br>
                    
                    Lista de pedidos <input type="submit" value="Mostrar historial" name="action" /><br><br>
                    <%-- 
                    <jsp:setProperty name="conMenServlet" property="name" value="<%=user%>" />
                    <jsp:setProperty name="conMenServlet" property="idS" value="<%=idS%>" />
                    
                    <input type="text" name="identificador" value="<%=idS%>" readonly="readonly"/>
                    --%>



                    <input type="text" name="pedido" value="ID"/><input type="submit" value="Ver Información Detallada" name="action" /><br>
                    <select name="eleccion" size="1">
                        <option>En proceso</option>
                        <option>Retrasado</option>
                        <option>Entregado</option>
                    </select>
                    <input type="text" name="ciudad" value="Ciudad"/>
                    <input type="text" name="departamento" value="Departamento"/>
                    <input type="submit" value="Actualizar" name="action" /><br><p></p>
                    
                    
                    <table border="1">
                        <th>ID</th>
                        <th>Tipo Pedido</th>
                        <th>Nombre destinatario</th>
                        <th>Estado</th>
                        <th>Fecha de Actualización</th>

                        <c:forEach items="<%=pedidos%>" var="pedid" >
                            <c:if test="${pedid.ultimoEstado ne "Sin Asignar"}">
                                <tr>
                                    <td>${pedid.pedidoid}</td>
                                    <td>${pedid.tipo}</td>
                                    <td>${pedid.nombreDestinatario}</td>
                                    <td>${pedid.ultimoEstado}</td>
                                    <td>${pedid.ultimaFecha}</td>
                                </tr>
                            </c:if>
                        </c:forEach> 
                    </table>
                </div>
            </div>
        </form>
    </body>
</html>
