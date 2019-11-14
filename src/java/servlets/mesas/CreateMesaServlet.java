/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.mesas;

import controller.mesa.MesasJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import model.Mesas;

/**
 *
 * @author will
 */
@WebServlet(name = "CreateMesaServlet", urlPatterns = {"/CreateMesaServlet"})
public class CreateMesaServlet extends HttpServlet {
    
    
    @Resource
    private UserTransaction utx;
    @PersistenceUnit(unitName = "SmartFood4PU")
    EntityManagerFactory emf;
    

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateMesaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateMesaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
    
        Mesas mesas = new Mesas();
        int qtd = Integer.parseInt(request.getParameter("lugares"));
        
        int dispo = Integer.parseInt(request.getParameter("disponibilidade"));
        boolean x = dispo == 1 ? true : false;
        
        
        mesas.setDisponibilidade(x);
        mesas.setLugares(qtd);
        
        MesasJpaController mesasJpaController = new MesasJpaController(utx, emf);
        
        try {
            mesasJpaController.create(mesas);
            response.sendRedirect("mesas/List.jsp");
        } catch (Exception ex) {
            Logger.getLogger(CreateMesaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
