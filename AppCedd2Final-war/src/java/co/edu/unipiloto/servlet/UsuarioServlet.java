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
import co.edu.unipiloto.entities.session.ClienteFacadeLocal;
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
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    @EJB
    private ConductorFacadeLocal conductorFacade;

    @EJB
    private ClienteFacadeLocal clienteFacade;

    @EJB
    private PedidosFacadeLocal pedidosFacade;

    @EJB
    private RegistroFacadeLocal registroFacade;

    private String mensaje;
    private Cliente cliente;

    public UsuarioServlet() {
        this.mensaje = "";
        this.cliente = new Cliente();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        HttpSession misession = request.getSession(true);
        cliente = (Cliente) misession.getAttribute("clienteActual");

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
                out.println("<th>Peso (g)</th>");
                out.println("<th>Nombre Destinatario</th>");
                out.println("<th>Teléfono Destinatario</th>");
                out.println("<th>Destino</th>");
                out.println("<th>Dirección</th>");
                out.println("<th>Estado</th>");
                out.println("<th>Fecha de actualización</th>");
                out.println("<th>Valor a pagar</th>");

                for (Pedidos x : cliente.getPedidosCollection()) {
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
                    out.println("<td>$ " + x.getTotalPagar() + "</td>");
                    out.println("</tr>");

                }
                out.println("</table>");

                out.print("<p><a href='ClienteMenu.jsp'>Volver...</a></p>");

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
            for (Pedidos a : cliente.getPedidosCollection()) {
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

            if (action.equals("Cancelar Solicitud")) {
                if (!pedido.getUltimoEstado().equals("Sin Asignar")) { //Si el pedido ya fue tomado o cancelado no se puede cancelar
                    mensaje = "El pedido ya no se encuentra en estado de solicitud por lo que no se puede cancelar...";

                    //misession.setAttribute("clienteActual", cliente);
                    request.setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("ClienteMenu.jsp").forward(request, response);
                } else {//Cancelar pedido
                    //Long registroid, String ciudad, String departamento, Date fecha, String descripcion, String estado, Pedidos pedidoId
                    long count = 0;
                    for (Registro a : registroFacade.findAll()) {
                        count++;
                    }

                    Date date = new Date();

                    Registro registro = new Registro(count + 1, cliente.getCiudad(), cliente.getDepartamento(), date, "Pedido cancelado por el cliente", "Cancelado", pedido);

                    //Añadir registro a pedido
                    Collection<Registro> registrosP = pedido.getRegistroCollection();
                    registrosP.add(registro);
                    pedido.setRegistroCollection(registrosP);
                    pedido.setUltimoEstado("Cancelado");
                    pedido.setUltimaFecha(date);
                    
                    

                    //Añadir el pedido
                    pedidosFacade.edit(pedido);

                    //Añadir pedido a cliente
                    Collection<Pedidos> pedidosC = cliente.getPedidosCollection();
                    //pedidosC.add(pedido);
                    cliente.setPedidosCollection(pedidosC);

                    clienteFacade.edit(cliente); //Agregamos el pedido

                    mensaje = "El pedido con ID " + idP + " se ha cancelado con éxito...";

                    misession.setAttribute("clienteActual", cliente);
                    request.setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("ClienteMenu.jsp").forward(request, response);

                }

            } else if (action.equals("Ver Info Detallada")) { //Ver info
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
                    
                    if(!(pedido.getUltimoEstado().equals("Cancelado") || pedido.getUltimoEstado().equals("Sin Asignar") )){ //Imprimir el conductor si aplica
                        Conductor conductor = new Conductor();
                        for(Conductor c: conductorFacade.findAll()){
                            for(Pedidos p: c.getPedidosCollection()){
                                if(p.getPedidoid() == idP){
                                    conductor = c;
                                    break;
                                }
                            }
                        }
                        
                        out.println("<h2>Datos del conductor" + "</h2><br>");
                        
                        out.println("<h3>Nombre: " + conductor.getNombre() + "</h3>");
                        out.println("<h3>Teléfono: +57 " + conductor.getTelefono() + "</h3>");
                        out.println("<h3>Modelo del vehículo: " + conductor.getModeloVehiculo() + "</h3>");
                        out.println("<h3>Placa del vehículo: " + conductor.getPlacaVehiculo() + "</h3><br>");
                    }

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
