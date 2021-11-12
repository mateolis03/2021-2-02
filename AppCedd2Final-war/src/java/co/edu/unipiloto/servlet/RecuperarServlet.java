/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.servlet;

import co.edu.unipiloto.entities.Cliente;
import co.edu.unipiloto.entities.Conductor;
import co.edu.unipiloto.entities.session.ClienteFacadeLocal;
import co.edu.unipiloto.entities.session.ConductorFacadeLocal;
import co.edu.unipiloto.utilities.Utilidades;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RecuperarServlet", urlPatterns = {"/RecuperarServlet"})
public class RecuperarServlet extends HttpServlet {

    @EJB
    private ConductorFacadeLocal conductorFacade;

    @EJB
    private ClienteFacadeLocal clienteFacade;

    private String mensaje;


    public RecuperarServlet() {
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

        String correo = request.getParameter("correo");
        String action = request.getParameter("action");
        String claveTemp;
        boolean estaC = false;
        boolean estaCo = false;

        if (action.equals("Aceptar")) {
            for (Cliente a : clienteFacade.findAll()) {
                if (a.getEmail().equals(correo)) {
                    claveTemp = Utilidades.passGenerator(10);
                    Cliente cTemp = a;
                    cTemp.setPassword(claveTemp);
                    clienteFacade.edit(cTemp);
                    estaC = true;
                    break;
                }
            }

            if (!estaC) {

                for (Conductor a : conductorFacade.findAll()) {
                    if (a.getEmail().equals(correo)) {
                        claveTemp = Utilidades.passGenerator(10);
                        Conductor coTemp = a;
                        coTemp.setPassword(claveTemp);
                        conductorFacade.edit(coTemp);
                        estaCo = true;
                        break;
                    }

                    if (!estaCo) {
                        mensaje = "Datos incorrectos. El correo no está registrado en nuestro sistema, intente de nuevo...";
                    }
                }
            }

            if (estaCo || estaC) {
                mensaje = "Se ha enviado una clave de acceso al correo " + correo + "\nSe recomienda cambiar la contraseña una vez ingrese en el sistema.";
            }

            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("Recuperar.jsp").forward(request, response);
        } else if (action.equals("Volver...")) {
            request.getRequestDispatcher("LogIn.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("Recuperar.jsp").forward(request, response);
        }
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
