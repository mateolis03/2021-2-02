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
            int size = 0;
            Collection<Pedidos> arrayP = new ArrayList();
        %>

        <%
            //logH = (LogInServlet) request.getAttribute("logM");
            misessionCon = request.getSession(true);
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
            
            size = 0;
            for(Pedidos a: arrayP){
                if(a.getUltimoEstado().equals("Sin Asignar")){
                    size++;
                }
            }
        %>

        <form action="./AplicarSolServlet" method="POST">

            <h1>Bienvenido Sr(a). <p><%= name%></p></h1>
            <nav>
                <ul>
                    <li><a href="ConductorMenu.jsp" style="text-decoration:none">Inicio</a></li>
                    <li><a href="AplicarSolCon.jsp" style="text-decoration:none">Aplicar a Solicitudes</a></li>
                    <li><a href="InfoCliente.jsp" style="text-decoration:none">Perfil de usuario</a></li> <%-- ojo No esta creado--%>
                    <li><a href="./LogOutServlet" style="text-decoration:none">Cerrar Sesión</a></li>
                </ul>
            </nav>
            <div id="container">
                <div id="contentManager">
                    <p><%=mensaje%></p><br>

                    Lista de solicitudes<br>
                    <%-- 
                    <jsp:setProperty name="conMenServlet" property="name" value="<%=user%>" />
                    <jsp:setProperty name="conMenServlet" property="idS" value="<%=idS%>" />
                    
                    <input type="text" name="identificador" value="<%=idS%>" readonly="readonly"/>
                    <c:if test="${pedid.ultimoEstado eq "Sin Asignar"}">
                                                </c:if>

                    --%>

                    <p>Usted puede aplicar a <%=size%> pedido(s)</p><br>

                    <input type="text" name="pedido" value="" placeholder="ID"/>
                    <input type="submit" value="Ver Información Detallada" name="action" />
                    <input type="submit" value="Aplicar a Solicitud" name="action" /><br><br>

                    <table border="1">
                        <th>ID</th>
                        <th>Tipo Pedido</th>
                        <th>Nombre destinatario</th>
                        <th>Estado</th>
                        <th>Fecha de Actualización</th>

                        <c:set var = "pedidosA" scope = "application" value = "<%=arrayP%>"/>
                        <c:forEach items="${pedidosA}" var="pedid" >
                            <c:if test="${pedid.ultimoEstado eq "Sin Asignar"}">
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
