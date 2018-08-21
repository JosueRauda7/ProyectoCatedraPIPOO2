/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.beans.Rubro;
import sv.edu.udb.www.model.RubrosModel;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author Emerson Torres
 */
@WebServlet(name = "RubrosController", urlPatterns = {"/rubros.do"})
public class RubrosController extends HttpServlet {

    RubrosModel modelo = new RubrosModel();
    ArrayList listaErrores = new ArrayList<>();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("operacion");
            switch(operacion){
                case "listar":
                    listar(request, response);
                    break;
                case "agregar":
                    agregar(request, response);
                    break;
            }
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
            request.setAttribute("listaRubros", modelo.obtenerRubro());
            request.getRequestDispatcher("/Administrador/ListaRubros.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregar(HttpServletRequest request, HttpServletResponse response) {
      try{
       listaErrores.clear();
       Rubro rubro = new Rubro();
       rubro.setRubro(request.getParameter("rubro"));
       if(Validaciones.isEmpty(rubro.getRubro())){
          listaErrores.add("El campo nombre del rubro es obligatorio");
       }
       if(modelo.validarRubro(rubro)>0){
           listaErrores.add("Este rubro ya existe");
       }
       if(listaErrores.size() >0){
          request.setAttribute("listaErrores", listaErrores);
          request.setAttribute("rubro", rubro);
          request.getRequestDispatcher("rubros.do?operacion=listar").forward(request, response);
       }else{
           if(modelo.insertarRubro(rubro)>0){
               request.setAttribute("exito", "El rubro ha sido insertado correctamente");
               request.getRequestDispatcher("rubros.do?operacion=listar").forward(request, response);
           }else{
               request.setAttribute("fracaso", "El rubro no se ha podido insertar");
               request.getRequestDispatcher("rubros.do?operacion=listar").forward(request, response);           
           }           
       }
       
    }   catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    


