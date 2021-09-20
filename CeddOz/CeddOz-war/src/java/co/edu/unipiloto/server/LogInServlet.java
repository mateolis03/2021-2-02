/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.server;



import co.edu.unipiloto.entities.Cliente;
import co.edu.unipiloto.entities.session.ClienteFacadeLocal;
import co.edu.unipiloto.entities.session.ConductorFacadeLocal;
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
 * @author david
 */
@WebServlet(name = "LogInServlet", urlPatterns = {"/LogInServlet"})
public class LogInServlet extends HttpServlet {

    @EJB
    private ConductorFacadeLocal conductorFacade;

    @EJB
    private ClienteFacadeLocal clienteFacade;
    




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
        try (PrintWriter out = response.getWriter()) {
             
            String action = request.getParameter("action");
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("password");
            Cliente client = new Cliente();
            if (action.equals("Ingresar")) {
                client = clienteFacade.find(usuario);

                if (client.getClienteid() == null || client.getClienteid().equals("")) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Usuario Incorrecto</title>");
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1>Usuario no encontrado, intente de nuevo" + "</h1>");
                    out.println("<a href='LogIn.jsp'>Volver atras</a>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    if (client.getPassword().equals(pass)) {
                        request.getRequestDispatcher("SolicitudE.jsp").forward(request, response);
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Contraseña Incorrecta</title>");
                        out.println("</head>");
                        out.println("<body>");

                        out.println("<h1>Contraseña incorrecta, intente de nuevo" + "</h1>");
                        out.println("<a href='LogIn.jsp'>Volver atras</a>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
            }

        }
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LogInServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Servlet LogInServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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
