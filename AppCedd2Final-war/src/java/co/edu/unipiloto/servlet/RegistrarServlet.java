/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.servlet;

import co.edu.unipiloto.entities.Cliente;
import co.edu.unipiloto.entities.session.ClienteFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edwin
 */
public class RegistrarServlet extends HttpServlet {

    @EJB
    private ClienteFacadeLocal clienteFacade;

    String respuesta = "";

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

        String nombre = request.getParameter("fname");

        String docS = request.getParameter("doc");
        long doc = 0;
        try {
            doc = Long.valueOf(docS);
        } catch (Exception e) {

        }

        String mail = request.getParameter("mail");
        String telefono = request.getParameter("telefono");
        String usuario = request.getParameter("usuario");
        String ciudad = request.getParameter("ciudad");
        String departamento = request.getParameter("departamento");
        String direccion = request.getParameter("direccion");
        String password = request.getParameter("password");
        String passwordC = request.getParameter("passwordC");

        Cliente cliente;
        if (!(nombre.equals("") || doc == 0 || mail.equals("") || telefono.equals("") || departamento.equals("")|| 
                usuario.equals("") || direccion.equals("") || ciudad.equals("") || password.equals("") || passwordC.equals(""))) {
            for (Cliente a : clienteFacade.findAll()) {
                if (a.getUsuario().equals(usuario)) {
                    respuesta = "El nombre de usuario ya se encuentra en uso, intente de nuevo";
                    request.setAttribute("servlet", this);
                    request.getRequestDispatcher("Registrar.jsp").forward(request, response);
                    
                }
            }
            if (password.equals(passwordC)) {
                //(Long clienteid, String nombre, BigInteger cedula, String email, String telefono, String ciudad, String departamento, String direccion, String usuario, String password)
                cliente = new Cliente(doc, nombre, BigInteger.valueOf(doc), mail, telefono, ciudad, departamento, direccion, usuario, password);
                clienteFacade.create(cliente);
                String[] temp = nombre.split(" ");
                respuesta = "Bienvenido a nuestra plataforma Sr(a) " + temp[0];
            } else {
                respuesta = "Las contraseñas no coinciden, intente de nuevo...";
            }
        } else {
            respuesta = "Los campos no están correctamente diligenciados, intente de nuevo";
        }

        request.setAttribute("mensaje", respuesta);
        request.getRequestDispatcher("Registrar.jsp").forward(request, response);
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

    public ClienteFacadeLocal getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClienteFacadeLocal clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
