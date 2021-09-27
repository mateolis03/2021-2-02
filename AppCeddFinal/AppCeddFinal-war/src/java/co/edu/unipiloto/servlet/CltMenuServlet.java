/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.servlet;

import co.edu.unipiloto.entities.Cliente;
import co.edu.unipiloto.entities.Conductor;
import co.edu.unipiloto.entities.Pedidos;
import co.edu.unipiloto.entities.session.ClienteFacadeLocal;
import co.edu.unipiloto.entities.session.ConductorFacadeLocal;
import co.edu.unipiloto.entities.session.PedidosFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edwin
 */
@WebServlet(name = "CltMenuServlet", urlPatterns = {"/CltMenuServlet"})
public class CltMenuServlet extends HttpServlet {

    @EJB
    private PedidosFacadeLocal pedidosFacade;

    @EJB
    private ConductorFacadeLocal conductorFacade;

    @EJB
    private ClienteFacadeLocal clienteFacade;

    private String username;
    private String id;

    public CltMenuServlet() {
        id = "";
        username = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

        String idS = request.getParameter("identificador");
        int id = 0, idPed = 0;
        try {
            id = Integer.parseInt(idS);
        } catch (Exception e) {

        }
        String idPedS = request.getParameter("pedido");
        try {
            idPed = Integer.parseInt(idPedS);
        } catch (Exception e) {

        }
        String action = request.getParameter("action");

        if (action.equals("Ver Info")) {
            String salida;
            for (Pedidos a : clienteFacade.find(id).getPedidosCollection()) {
                if (a.getId() == idPed) {
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Factura</title>");
                        out.println("</head>");
                        
                        out.println("<body>");
                        
                        out.println("<h1>FACTURA</h1>");
                        out.println("<h3>Tipo de paquete: " + a.getTipo() + "</h3>");
                        out.println("<h3>Dimensiones: " + a.getAlto() + "m  *  " + a.getAncho() + "m  *  " + a.getProfundidad() + "m</h3>");
                        out.println("<h3>Peso: " + a.getPeso() + " g</h2>");
                        out.println("<h3>Nombre destinatario: " + a.getNombreRemitente() + "</h3>");
                        out.println("<h3>Teléfono: +57 " + a.getTelefonoRemitente() + "</h3>");
                        out.println("<h3>Dirección de entrega: " + a.getDireccionRemitente() + "</h3>");
                        out.println("<h3>Estado del envío: " + a.getEstadoEnvio() + "</h3>");
                        out.println("<h3>Fecha de envío: " + a.getFechaEnvio().toString() + "</h3>");
                        out.println("<h3>Fecha de entrega: " + (a.getFechaRecibido().toString().equals("") ? "No aplica" : a.getFechaRecibido().toString()) + "</h3>");
                        out.print("<p><a href='index.html'>Volver...</a></p>");

                        out.println("</body>");
                        out.println("</html>");
                    }
                } else {
                    request.setAttribute("mensaje", "El ID de pedido no existe");
                    request.getRequestDispatcher("LogIn.jsp").forward(request, response);
                }
            }

        } else if (action.equals("Eliminar")) {
            for (Pedidos a : clienteFacade.find(id).getPedidosCollection()) {
                if (a.getId() == idPed) {
                    Cliente temp = clienteFacade.find(id);
                    Collection<Pedidos> pedidos = temp.getPedidosCollection();
                    pedidos.remove(a);
                    temp.setPedidosCollection(pedidos);
                    clienteFacade.edit(temp);

                    for (Conductor c : conductorFacade.findAll()) {
                        for (Pedidos p : c.getPedidosCollection()) {
                            if (p.getId() == idPed) {
                                Collection<Pedidos> ped = c.getPedidosCollection();
                                p.setEstadoEnvio("Cancelado");
                                pedidosFacade.edit(p);
                                ped.remove(p);
                                c.setPedidosCollection(ped);
                                conductorFacade.edit(c);
                                break;
                            }
                        }
                    }

                    break;
                }
            }

            request.getRequestDispatcher("LogIn.jsp").forward(request, response);

        }

        /*String idS = request.getParameter("identificador");
        int id = Integer.parseInt(idS);
        String action = request.getParameter("action");

        //request.setAttribute("allPedidos", clienteFacade.find(id).getPedidosCollection());
        //request.setAttribute("identificador", idS);
        request.getRequestDispatcher("ClienteMenu.jsp").forward(request, response);*/
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
