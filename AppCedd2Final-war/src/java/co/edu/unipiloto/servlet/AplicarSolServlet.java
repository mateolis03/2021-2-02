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
import co.edu.unipiloto.utilities.Objetos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "AplicarSolServlet", urlPatterns = {"/AplicarSolServlet"})
public class AplicarSolServlet extends HttpServlet {

    @EJB
    private RegistroFacadeLocal registroFacade;

    @EJB
    private ClienteFacadeLocal clienteFacade;

    @EJB
    private ConductorFacadeLocal conductorFacade;

    @EJB
    private PedidosFacadeLocal pedidosFacade;

    private String mensaje;
    private Conductor conductor;

    public AplicarSolServlet() {
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

    //FALTAAAAAAAA
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

        long id;
        boolean ok = true;

        HttpSession misessionCon = request.getSession(true);
        conductor = (Conductor) misessionCon.getAttribute("conductorActual");
        Pedidos pedido = new Pedidos();
        Cliente cliente = new Cliente();

        try {
            id = Long.parseLong(pedidoId);
            pedido = pedidosFacade.find(id);
        } catch (Exception e) {
            ok = false;
            id = 0;
        }

        if (!ok) { //Se pudo parsear?
            mensaje = "Dato incorrecto, intente de nuevo...";

            //misession.setAttribute("clienteActual", cliente);
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("PruAplicarSolCon.jsp").forward(request, response);
        } else {
            if (pedido != null && pedido.getUltimoEstado().equals("Sin Asignar")) { //Se encontró el id y está sin asignar

                if (action.equals("Aplicar a Solicitud")) { //Aplicar a solicitud

                    Date fecha = new Date();

                    long count = 0;
                    for (Registro r : registroFacade.findAll()) {
                        count++;
                    }

                    Registro registro = new Registro(count + 1, "Central Bogotá", "Bogotá", fecha, "Pedido tomado por un conductor", "Asignado", pedido); //creamos el registro

                    //Añadir registro a pedido
                    Collection<Registro> registrosP = pedido.getRegistroCollection();
                    registrosP.add(registro);
                    pedido.setRegistroCollection(registrosP);

                    pedido.setUltimaFecha(fecha);
                    pedido.setUltimoEstado("Asignado");

                    pedidosFacade.edit(pedido); //editamos el pedido

                    Collection<Pedidos> pedidosC = conductor.getPedidosCollection();
                    pedidosC.add(pedido);
                    conductor.setPedidosCollection(pedidosC);//Agregamos el pedido al conductor

                    conductorFacade.edit(conductor);//Editamos el conductor
                    
                    conductor = conductorFacade.find(conductor.getConductorid());

                    mensaje = "Se aplicó con éxito a la solicitud con el ID " + id;

                    Collection<Pedidos> allPedidos = new ArrayList();
                    allPedidos = pedidosFacade.findAll();

                    Objetos myObject;
                    myObject = new Objetos(allPedidos, allPedidos.size());

                    //misession.setAttribute("clienteActual", cliente);
                    misessionCon.setAttribute("conductorActual", conductor);
                    misessionCon.setAttribute("myObject", myObject);
                    request.setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("PruAplicarSolCon.jsp").forward(request, response);

                } else { //Ver información del pedido
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        mensaje = "";
                        request.setAttribute("mensaje", mensaje);
                        
                        for (Cliente a : clienteFacade.findAll()) { //Buscamos al cliente que tiene ese pedido
                            for (Pedidos x : a.getPedidosCollection()) {
                                if (x.getPedidoid() == id) {
                                    cliente = a;
                                    break;
                                }
                            }
                        }

                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet UsuarioServlet</title>");
                        out.println("</head>");
                        out.println("<body>");

                        out.println("<h1>DETALLE DE LA SOLICITUD " + "</h1><br>");

                        out.println("<h2>Información del cliente" + "</h2><br>");
                        out.println("<h3>Nombre: " + cliente.getNombre() + "</h3>");
                        out.println("<h3>Teléfono: +57 " + cliente.getTelefono() + "</h3>");
                        out.println("<h3>Email: " + cliente.getEmail() + "</h3>");
                        out.println("<h3>Dirección: " + cliente.getCiudad() + ", " + cliente.getDepartamento() + ", " + cliente.getDireccion() + "</h3><br>");

                        out.println("<h2>Información del pedido" + "</h2><br>");
                        out.println("<h3>Tipo de paquete: " + pedido.getTipo() + "</h3>");
                        out.println("<h3>Dimensiones: " + pedido.getAlto() + "m  *  " + pedido.getAncho() + "m  *  " + pedido.getProfundidad() + "m</h3>");
                        out.println("<h3>Peso: " + pedido.getPeso() + " g</h2>");
                        out.println("<h3>Nombre destinatario: " + pedido.getNombreDestinatario() + "</h3>");
                        out.println("<h3>Teléfono destinatario: +57 " + pedido.getTelefonoDestinatario() + "</h3>");
                        out.println("<h3>Dirección de entrega: " + pedido.getDireccionDestinatario() + ", " + pedido.getCiudadDestinatario() + ", " + pedido.getDepartamentoDestinatario() + "</h3>");
                        out.println("<h3>Costo transporte: $" + pedido.getTotalPagar() + "</h3>");
                        out.println("<h3>Fecha de solicitud: " + pedido.getUltimaFecha().toString() + "</h3><br>");

                        out.print("<p><a href='PruAplicarSolCon.jsp'>Volver...</a></p>");

                        out.println("</body>");
                        out.println("</html>");

                        //misession.setAttribute("clienteActual", cliente);
                    }
                }

            } else {

                mensaje = "El ID del pedido no se encuentra disponible...";

                //misession.setAttribute("clienteActual", cliente);
                request.setAttribute("mensaje", mensaje);
                request.getRequestDispatcher("PruAplicarSolCon.jsp").forward(request, response);
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
