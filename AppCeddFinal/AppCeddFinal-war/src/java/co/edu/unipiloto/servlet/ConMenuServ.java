/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.servlet;

import co.edu.unipiloto.entities.Conductor;
import co.edu.unipiloto.entities.Pedidos;
import co.edu.unipiloto.entities.session.ConductorFacadeLocal;
import co.edu.unipiloto.entities.session.PedidosFacadeLocal;
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

/**
 *
 * @author edwin
 */
@WebServlet(name = "ConMenuServ", urlPatterns = {"/ConMenuServ"})
public class ConMenuServ extends HttpServlet {

    @EJB
    private PedidosFacadeLocal pedidosFacade;

    @EJB
    private ConductorFacadeLocal conductorFacade;

    String idS, name;

    public ConMenuServ() {
        idS = name = "";
    }

    public String getIdS() {
        return idS;
    }

    public void setIdS(String idS) {
        this.idS = idS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        idS = request.getParameter("identificador");
        String idPedS = request.getParameter("pedido");
        String action = request.getParameter("action");
        String eleccion = request.getParameter("eleccion");

        if (!(idS.equals("") || idPedS.equals("") || action.equals("") || eleccion.equals(""))) {
            
        } else if (action.equals("Actualizar")) {
            int id = 0, idP = 0;
            try {
                id = Integer.parseInt(idS);
                idP = Integer.parseInt(idPedS);
            } catch (Exception e) {

            }
            Conductor conductor = conductorFacade.find(id);
            Collection<Pedidos> pedidos = conductor.getPedidosCollection();
            Pedidos ped = new Pedidos();

            for (Pedidos p : pedidos) {
                if (p.getId() == idP) {
                    ped = p;
                    pedidos.remove(p);
                    break;
                }
            }

            ped.setEstadoEnvio(eleccion);

            Date date = new Date();

            switch (eleccion) {
                case "En camino":
                    ped.setFechaEnvio(date);
                    pedidos.add(ped);
                    pedidosFacade.edit(ped);
                    conductor.setPedidosCollection(pedidos);
                    conductorFacade.edit(conductor);
                    break;
                case "Demorado":
                    pedidos.add(ped);
                    pedidosFacade.edit(ped);
                    conductor.setPedidosCollection(pedidos);
                    conductorFacade.edit(conductor);
                    break;
                case "Entregado":
                    ped.setFechaRecibido(date);
                    pedidosFacade.edit(ped);
                    conductor.setPedidosCollection(pedidos);
                    conductorFacade.edit(conductor);
                    break;
                case "Cancelado":
                    ped.setFechaRecibido(date);
                    pedidosFacade.edit(ped);
                    conductor.setPedidosCollection(pedidos);
                    conductorFacade.edit(conductor);
                    break;
            }

        }
        
        request.getRequestDispatcher("LogIn.jsp").forward(request, response);

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
