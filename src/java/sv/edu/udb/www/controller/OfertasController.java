/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.model.EstadoOfertaModel;
import sv.edu.udb.www.model.OfertasModel;

/**
 *
 * @author ivanm
 */
@WebServlet(name = "OfertasController", urlPatterns = {"/ofertas.do"})
public class OfertasController extends HttpServlet {
    OfertasModel modeloOfertas = new OfertasModel();
    EstadoOfertaModel modeloEstado = new EstadoOfertaModel();
    
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
        String operacion = request.getParameter("operacion");
        
        switch(operacion){

            case "home":
                request.getRequestDispatcher("/Empresa/Home.jsp").forward(request, response);
                break;
            case "listar":                
                listar(request,response);
                break;            
            case "nuevo":
                nuevo(request,response);
                break;
            case "ingresar":
                ingresar(request,response);
                break;
            
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
    
    private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String correo = (String) request.getSession().getAttribute("correo");
            request.setAttribute("listaOfertas",modeloOfertas.listarOferta(correo));
            try {
                request.getRequestDispatcher("/Empresa/ListaOfertas.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
        private void nuevo(HttpServletRequest request, HttpServletResponse response) {
         try {
            request.setAttribute("listaEstados",modeloEstado.listarEstadoOferta());
            request.getRequestDispatcher("/Empresa/NuevaOferta.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void ingresar(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
