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
import co.edu.unipiloto.utilities.Utilidades;
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
@WebServlet(name = "SolEnvServlet", urlPatterns = {"/SolEnvServlet"})
public class SolEnvServlet extends HttpServlet {

    @EJB
    private ConductorFacadeLocal conductorFacade;

    @EJB
    private PedidosFacadeLocal pedidosFacade;

    @EJB
    private ClienteFacadeLocal clienteFacade;

    private String salida;

    public SolEnvServlet() {
        salida = "";
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
        final String ESTADO = "Generado";
        String tipo = request.getParameter("tipo");
        String altoS = request.getParameter("alto");

        String anchoS = request.getParameter("ancho");

        String profundidadS = request.getParameter("profundidad");

        String pesoS = request.getParameter("peso");

        String doc = request.getParameter("doc");

        String nombre = request.getParameter("nombre");
        String telefonoS = request.getParameter("telefono");

        String direccion = request.getParameter("direccion");
        int count = 0;

        if (!(tipo.equals("") || altoS.equals("") || anchoS.equals("") || profundidadS.equals("") || pesoS.equals("") || doc.equals("") ||
                nombre.equals("") || telefonoS.equals("") || direccion.equals(""))) { //Si no esta vacio algun campo
            
            boolean todgud = true;
            double alto = 0, ancho = 0, profundidad = 0, peso = 0;
            int id = 0, telefono = 0;
            
            try{
                alto = Double.parseDouble(altoS);
            }catch(Exception e){
                todgud = false;
                salida += "Error: Alto. ";
            }
            
            try{
                ancho = Double.parseDouble(anchoS);
            }catch(Exception e){
                todgud = false;
                salida += "Error: Ancho. ";
            }
            
            try{
                profundidad = Double.parseDouble(profundidadS);
            }catch(Exception e){
                salida += "Error: Profundidad. ";
                todgud = false;
            }
            
            try{
                peso = Double.parseDouble(pesoS);
            }catch(Exception e){
                salida += "Error: Peso. ";
                todgud = false;
            }
            
            try{
                id = Integer.parseInt(doc);
            }catch(Exception e){
                salida += "Error: Documento. ";
                todgud = false;
            }
            
            try{
                telefono = Integer.parseInt(telefonoS);
            }catch(Exception e){
                salida += "Error: Telefono. ";
                todgud = false;
            }
            
            if(!todgud){ //Se pudo parsear todo?
                salida += "Intente de nuevo.";
                request.setAttribute("solServlet", this);
                request.getRequestDispatcher("SolicitudE.jsp").forward(request, response);
            }
            
            boolean esta = false;
            
            for(Cliente a: clienteFacade.findAll()){
                if(a.getId() == id){
                    esta = true;
                    break;
                }
            }
            
            if (!esta) { //El id está?
                salida = "El documento ingresado no hace parte de alguna cuenta en nuestro sistema.";
                request.setAttribute("solServlet", this);
                request.getRequestDispatcher("SolicitudE.jsp").forward(request, response);
            }

            for (Pedidos a : pedidosFacade.findAll()) {
                count++;
            }

            Pedidos pedido = new Pedidos(count + 1, tipo, alto, ancho, profundidad, peso, nombre, telefono, direccion, ESTADO);
            Cliente temp = clienteFacade.find(id);
            Collection<Pedidos> pedidosC = temp.getPedidosCollection();
            pedidosC.add(pedido);
            temp.setPedidosCollection(pedidosC);
            clienteFacade.edit(temp); //Agregamos el pedido

            Collection<Conductor> conductores = conductorFacade.findAll(); // Buscamos conductor

            Conductor elegido = Utilidades.verDisponibilidad(conductores);

            pedidosC = elegido.getPedidosCollection();
            pedidosC.add(pedido);
            elegido.setPedidosCollection(pedidosC);
            conductorFacade.edit(elegido); //Asignamos pedido a conductor

            salida = "Se agregó el pedido satisfactoriamente.";
            request.setAttribute("solServlet", this);
            request.getRequestDispatcher("SolicitudE.jsp").forward(request, response);

        } else {// Campos nulos
            salida = "Datos incorrectos. Intente de nuevo";
            request.setAttribute("solServlet", this);
            request.getRequestDispatcher("SolicitudE.jsp").forward(request, response);
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

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

}
