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
import co.edu.unipiloto.utilities.Objetos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edwin
 */
public class LogInServlet extends HttpServlet {

    @EJB
    private PedidosFacadeLocal pedidosFacade;

    @EJB
    private ConductorFacadeLocal conductorFacade;

    @EJB
    private ClienteFacadeLocal clienteFacade;

    
    private String mensaje;

    private Conductor conductor;
    private Cliente cliente;

    public LogInServlet() {
        mensaje = "";

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

        String user = request.getParameter("usuario");
        String pass = request.getParameter("password");

        String action = request.getParameter("action");
        
        Collection<Pedidos> allPedidos = new ArrayList();
        allPedidos = pedidosFacade.findAll();
        
        Objetos myObject = new Objetos();
        HttpSession misession = request.getSession(true);
        myObject = new Objetos(allPedidos, allPedidos.size());

        boolean estaC = false;
        boolean estaCo = false;
        if (action.equals("INGRESAR")) {
            for (Cliente a : clienteFacade.findAll()) {
                if (a.getUsuario().equals(user) && a.getPassword().equals(pass)) {
                    cliente = a;
                    estaC = true;
                    break;
                }
            }

            if (!estaC) { //No está el cliente
                for (Conductor a : conductorFacade.findAll()) {
                    if (a.getUsuario().equals(user) && a.getPassword().equals(pass)) {
                        estaCo = true;
                        conductor = a;
                        request.setAttribute("logM", this);
                        misession.setAttribute("conductorActual", conductor);
                        misession.setAttribute("myObject", myObject);
                        request.getRequestDispatcher("PruConductor.jsp").forward(request, response);
                        break;
                    }

                    if (!estaCo) { //Si no está el conductor
                        mensaje = "Datos incorrectos, intente de nuevo...";
                    }
                }
                
                
                
            } else { //Cliente
                request.setAttribute("logM", this);             
                misession.setAttribute("clienteActual", cliente);
                misession.setAttribute("myObject", myObject);
                request.getRequestDispatcher("ClienteMenu.jsp").forward(request, response);
            }
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("LogIn.jsp").forward(request, response);
            
        } else if (action.equals("REGISTRESE")) {
            request.getRequestDispatcher("Registrar.jsp").forward(request, response);
        } else if (action.equals("RECUPERAR PASSWORD")) {
            request.getRequestDispatcher("Recuperar.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("LogIn.jsp").forward(request, response);
        }

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PedidosFacadeLocal getPedidosFacade() {
        return pedidosFacade;
    }

    public void setPedidosFacade(PedidosFacadeLocal pedidosFacade) {
        this.pedidosFacade = pedidosFacade;
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
