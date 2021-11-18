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
import co.edu.unipiloto.utilities.Utilidades;
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
@WebServlet(name = "SolEnvServlet", urlPatterns = {"/SolEnvServlet"})
public class SolEnvServlet extends HttpServlet {
    //Revisar cancelar solicitudes, bug
    @EJB
    private RegistroFacadeLocal registroFacade;

    @EJB
    private PedidosFacadeLocal pedidosFacade;

    @EJB
    private ClienteFacadeLocal clienteFacade;

    private String salida;
    private Cliente cliente;

    public SolEnvServlet() {
        salida = "";
        cliente = new Cliente();
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
        final String ESTADO = "Sin Asignar";
        final int NUMMAX = 90000, NUMMIN = 10000;
        String tipo = request.getParameter("tipo");

        String altoS = request.getParameter("alto");

        String anchoS = request.getParameter("ancho");

        String profundidadS = request.getParameter("profundidad");

        String pesoS = request.getParameter("peso");

        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String ciudad = request.getParameter("ciudad");
        String departamento = request.getParameter("departamento");
        String direccion = request.getParameter("direccion");
        long count = 0;

        HttpSession misession = request.getSession(true);
        cliente = (Cliente) misession.getAttribute("clienteActual");

        if (!(tipo.equals("") || altoS.equals("") || anchoS.equals("") || profundidadS.equals("") || pesoS.equals("")
                || nombre.equals("") || telefono.equals("") || direccion.equals("") || ciudad.equals("") || departamento.equals(""))) { //Si no esta vacio algun campo
            boolean todgud = true;
            double alto = 0, ancho = 0, profundidad = 0, peso = 0;
            long id = cliente.getClienteid();
            salida = "";

            try {
                alto = Double.parseDouble(altoS);
            } catch (Exception e) {
                todgud = false;
                salida += "Error: Alto. ";
            }

            try {
                ancho = Double.parseDouble(anchoS);
            } catch (Exception e) {
                todgud = false;
                salida += "Error: Ancho. ";
            }

            try {
                profundidad = Double.parseDouble(profundidadS);
            } catch (Exception e) {
                salida += "Error: Profundidad. ";
                todgud = false;
            }

            try {
                peso = Double.parseDouble(pesoS);
            } catch (Exception e) {
                salida += "Error: Peso. ";
                todgud = false;
            }

            if (!todgud) { //Se pudo parsear todo?
                salida += "Intente de nuevo.";
                request.setAttribute("mensaje", salida);
                misession.setAttribute("clienteActual", cliente);
                request.getRequestDispatcher("SolicitudE.jsp").forward(request, response);
            } else {
                //Acá
                for (Pedidos a : pedidosFacade.findAll()) {
                    count++;
                }

                int totalPagar = (int) (Math.random() * NUMMAX + NUMMIN);
                Date fecha = new Date();
                //Long pedidoid, String tipo, Double alto, Double ancho, Double profundidad, Double peso, String nombreDestinatario, 
                //String telefonoDestinatario, String ciudadDestinatario, String departamentoDestinatario, String direccionDestinatario, String ultimoEstado, Date ultimaFecha, Double totalPagar

                Pedidos pedido = new Pedidos(count + 1, tipo, alto, ancho, profundidad, peso, nombre, telefono, ciudad, departamento, direccion, ESTADO, fecha, totalPagar * 1.0);

                count = 0;
                
                Cliente temp = clienteFacade.find(id);

                for (Registro a : registroFacade.findAll()) {
                    count++;
                }

                Registro registro = new Registro(count + 1, temp.getCiudad(), temp.getDepartamento(), fecha, "Creación de solicitud", ESTADO, pedido);

                //Añadir registro a pedido
                Collection<Registro> registrosP = pedido.getRegistroCollection();
                registrosP.add(registro);
                pedido.setRegistroCollection(registrosP);

                //Añadir el pedido
                //pedidosFacade.create(pedido);

                //Cambiar registrofacade
                //registroFacade.create(registro);
                
                //Añadir pedido a cliente
                

                Collection<Pedidos> pedidosC = temp.getPedidosCollection();
                pedidosC.add(pedido);
                temp.setPedidosCollection(pedidosC);

                clienteFacade.edit(temp); //Agregamos el pedido

                temp = clienteFacade.find(id);

                cliente = temp;//Asignamos

                /*Collection<Conductor> conductores = conductorFacade.findAll(); // Buscamos conductor

            Conductor elegido = Utilidades.verDisponibilidad(conductores);

            pedidosC = elegido.getPedidosCollection();
            pedidosC.add(pedido);
            elegido.setPedidosCollection(pedidosC);
            conductorFacade.edit(elegido); //Asignamos pedido a conductor
            
            System.out.println(elegido.getPedidosCollection().size());
            System.out.println(temp.getPedidosCollection().size());*/
                salida = "Solicitud creada con éxito..." + salida;
                request.setAttribute("mensaje", salida);
                misession.setAttribute("clienteActual", cliente);
                request.getRequestDispatcher("SolicitudE.jsp").forward(request, response);
            }
        } else {// Campos nulos
            salida = "Datos incorrectos. Intente de nuevo...";
            request.setAttribute("mensaje", salida);
            misession.setAttribute("clienteActual", cliente);
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
