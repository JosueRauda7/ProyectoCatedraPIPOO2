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
import sv.edu.udb.www.beans.Cupon;
import sv.edu.udb.www.model.EmpleadosModel;

@WebServlet(name = "EmpleadosController", urlPatterns = {"/empleados.do"})
public class EmpleadosController extends HttpServlet {

    EmpleadosModel modelo = new EmpleadosModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String operacion = request.getParameter("operacion");

            switch (operacion) {
                case "obtener":
                    obtenerCupon(request, response);
                    break;
                case "canjear":
                    canjearCupon(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex); //Si no funciona, eliminar esto
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

    private void obtenerCupon(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo = request.getParameter("codigo");
            request.setAttribute("listaCupones", modelo.obtenerCupon(codigo));

            request.getRequestDispatcher("/Empleado/canjearCupon.jsp").forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void canjearCupon(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        try {
            String codigoCupon = request.getParameter("codigoCupon");;
            String estadoCupon = request.getParameter("estadoCupon");
            String duiCanjeador = request.getParameter("duiCanjeador");
            String duiComprador = request.getParameter("duiComprador"); 

            if (!"Disponible".equals(estadoCupon)) {
                request.setAttribute("Fracaso", "El cupón no esta disponible");
                request.setAttribute("listaCupones", modelo.obtenerCupon(codigoCupon));
                request.getRequestDispatcher("/Empleado/canjearCupon.jsp").forward(request, response);
            } else if (!duiComprador.equals(duiCanjeador)) {
                request.setAttribute("Fracaso", "El DUI no coincide");
                request.setAttribute("listaCupones", modelo.obtenerCupon(codigoCupon));
                request.getRequestDispatcher("/Empleado/canjearCupon.jsp").forward(request, response);
            } else {
                if(modelo.canjearCupon(codigoCupon) > 0){
                    request.setAttribute("Exito", "El cupón ha sido canjeado, exitosamente");
                    response.sendRedirect("/Empleado/canjearCupon.jsp");
                }
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
