/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.servlet;

import co.edu.unipiloto.entities.Cliente;
import co.edu.unipiloto.entities.Conductor;
import co.edu.unipiloto.entities.Pedidos;
import co.edu.unipiloto.entities.Registro;
import co.edu.unipiloto.entities.session.ConductorFacadeLocal;
import co.edu.unipiloto.entities.session.PedidosFacadeLocal;
import co.edu.unipiloto.entities.session.RegistroFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edwin
 */
@WebServlet(name = "ConductorServlet", urlPatterns = {"/ConductorServlet"})
public class ConductorServlet extends HttpServlet {

    @EJB
    private RegistroFacadeLocal registroFacade;

    @EJB
    private PedidosFacadeLocal pedidosFacade;

    @EJB
    private ConductorFacadeLocal conductorFacade;

    private String mensaje;
    private Conductor conductor;

    public ConductorServlet() {
        this.mensaje = "";
        this.conductor = new Conductor();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        String pedidoId = request.getParameter("pedido");
        String estado = request.getParameter("eleccion");
        String ciudad = request.getParameter("ciudad");
        String departamento = request.getParameter("departamento");

        HttpSession misessionCon = request.getSession(true);
        conductor = (Conductor) misessionCon.getAttribute("clienteActual");
        Object myObject = misessionCon.getAttribute("myObject");

        if (action.equals("Mostrar historial")) {
            try (PrintWriter out = response.getWriter()) {
                //Long pedidoid, String tipo, Double alto, Double ancho, Double profundidad, Double peso, String nombreDestinatario, String telefonoDestinatario, 
                //String ciudadDestinatario, String departamentoDestinatario, String direccionDestinatario, String ultimoEstado, Date ultimaFecha, Double totalPagar
                
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet UsuarioServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>HISTORIAL DE SOLICITUDES " + "</h1><br>");
                out.println("<table border=\"1\">");
                
                out.println("<th>ID</th>");
                out.println("<th>Tipo Pedido</th>");
                out.println("<th>Dimensiones</th>");
                out.println("<th>Peso</th>");
                out.println("<th>Nombre Destinatario</th>");
                out.println("<th>Teléfono Destinatario</th>");
                out.println("<th>Destino</th>");
                out.println("<th>Dirección</th>");
                out.println("<th>Estado</th>");
                out.println("<th>Fecha de actualización</th>");

                for (Pedidos x : conductor.getPedidosCollection()) {
                    out.println("<tr>");
                    out.println("<td>" + x.getPedidoid() + "</td>");
                    out.println("<td>" + x.getTipo() + "</td>");
                    out.println("<td>" + x.getAlto() + " m * " + x.getAncho() + " m * " + x.getProfundidad() + " m" + "</td>");
                    out.println("<td>" + x.getPeso() + "</td>");
                    out.println("<td>" + x.getNombreDestinatario() + "</td>");
                    out.println("<td>" + x.getTelefonoDestinatario() + "</td>");
                    out.println("<td>" + x.getCiudadDestinatario() + ", " + x.getDepartamentoDestinatario() + "</td>");
                    out.println("<td>" + x.getDireccionDestinatario() + "</td>");
                    out.println("<td>" + x.getUltimoEstado() + "</td>");
                    out.println("<td>" + x.getUltimaFecha().toString() + "</td>");
                    out.println("</tr>");

                }
                
                out.println("</table>");

                out.print("<p><a href='ConductorMenu.jsp'>Volver...</a></p>");

                out.println("</body>");
                out.println("</html>");

                //misession.setAttribute("clienteActual", cliente);
            }
        } else if (pedidoId.equals("") || pedidoId.equals("ID")) {
            mensaje = "No ha ingresado un número de guía, intente de nuevo...";

            //misession.setAttribute("clienteActual", cliente);
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("ClienteMenu.jsp").forward(request, response);

        } else {
            long idP = 0;
            boolean ok = true;
            try {
                idP = Long.parseLong(pedidoId);
            } catch (Exception e) {
                ok = false;
            }

            if (!ok) { //No se pudo parsear
                mensaje = "Valor incorrecto, intente de nuevo...";

                //misession.setAttribute("clienteActual", cliente);
                request.setAttribute("mensaje", mensaje);
                request.getRequestDispatcher("ClienteMenu.jsp").forward(request, response);
            }

            ok = false;
            Pedidos pedido = new Pedidos();
            for (Pedidos a : conductor.getPedidosCollection()) {
                if (a.getPedidoid() == idP) {
                    pedido = a;
                    ok = true;
                    break;
                }
            }

            if (!ok) { //No está el ID
                mensaje = "No se encontró el número de guía(ID) en el registro, intente de nuevo...";

                //misession.setAttribute("clienteActual", cliente);
                request.setAttribute("mensaje", mensaje);
                request.getRequestDispatcher("ClienteMenu.jsp").forward(request, response);
            }

            if (action.equals("Actualizar")) {
                if (pedido.getUltimoEstado().equals("Entregado") || pedido.getUltimoEstado().equals("Cancelado")) { //Si el pedido ya se entregó o canceló
                    mensaje = "El pedido ya ha terminado por lo que su estado no puede editarse...";

                    //misession.setAttribute("clienteActual", cliente);
                    request.setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("ClienteMenu.jsp").forward(request, response);
                } else {//Actualizar estado
                    //Long registroid, String ciudad, String departamento, Date fecha, String descripcion, String estado, Pedidos pedidoId
                    if (ciudad.equals("") || departamento.equals("") || ciudad.equals("Ciudad") || departamento.equals("Departamento")) {
                        mensaje = "Edite los campos de Cuidad y Departamento antes de continuar...";

                        //misession.setAttribute("clienteActual", cliente);
                        request.setAttribute("mensaje", mensaje);
                        request.getRequestDispatcher("ClienteMenu.jsp").forward(request, response);
                    }
                    
                    long count = 0;
                    for (Registro a : registroFacade.findAll()) {
                        count++;
                    }

                    Date date = new Date();

                    Registro registro = new Registro(count + 1, ciudad, departamento, date, "Actualización del conductor", estado, pedido);

                    //Añadir registro a pedido
                    Collection<Registro> registrosP = pedido.getRegistroCollection();
                    registrosP.add(registro);
                    //pedido.setRegistroCollection(registrosP);
                    pedido.setUltimoEstado(estado);
                    pedido.setUltimaFecha(date);

                    //Añadir el pedido
                    pedidosFacade.edit(pedido);

                    //Añadir pedido a cliente
                    Collection<Pedidos> pedidosC = conductor.getPedidosCollection();
                    pedidosC.add(pedido);
                    conductor.setPedidosCollection(pedidosC);

                    conductorFacade.edit(conductor); //Agregamos el pedido

                    mensaje = "El pedido con ID " + idP + " se ha cancelado con éxito...";

                    misessionCon.setAttribute("conductorActual", conductor);
                    request.setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("ClienteMenu.jsp").forward(request, response);

                }

            } else if (action.equals("Ver Información Detallada")) { //Ver info
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet UsuarioServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>INFORMACIÓN DEL PEDIDO " + "</h1><br>");

                    out.println("<h3>Tipo de paquete: " + pedido.getTipo() + "</h3>");
                    out.println("<h3>Dimensiones: " + pedido.getAlto() + "m  *  " + pedido.getAncho() + "m  *  " + pedido.getProfundidad() + "m</h3>");
                    out.println("<h3>Peso: " + pedido.getPeso() + " g</h2>");
                    out.println("<h3>Nombre destinatario: " + pedido.getNombreDestinatario() + "</h3>");
                    out.println("<h3>Teléfono destinatario: +57 " + pedido.getTelefonoDestinatario() + "</h3>");
                    out.println("<h3>Dirección de entrega: " + pedido.getDireccionDestinatario() + ", " + pedido.getCiudadDestinatario() + ", " + pedido.getDepartamentoDestinatario() + "</h3>");
                    out.println("<h3>Costo transporte: $" + pedido.getTotalPagar() + "</h3>");
                    out.println("<h3>Estado: " + pedido.getUltimoEstado() + "</h3>");
                    out.println("<h3>Última fecha de actualización: " + pedido.getUltimaFecha().toString() + "</h3><br>");

                    out.println("<h2>Historial" + "</h2><br>");

                    int count = 1;
                    for (Registro temp : pedido.getRegistroCollection()) {
                        out.println("<h3> " + count + ") Estado: '" + temp.getEstado() + "' " + temp.getFecha().toString() + "</h3>");
                        out.println("<h3>Tramo: " + temp.getCiudad() + ", " + temp.getDepartamento() + "</h3>");
                        out.println("<h3>Descripción: " + temp.getDescripcion() + "</h3><br>");
                        count++;
                    }

                    out.print("<p><a href='ClienteMenu.jsp'>Volver...</a></p>");

                    out.println("</body>");
                    out.println("</html>");

                    //misession.setAttribute("clienteActual", cliente);
                }
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
