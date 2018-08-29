package sv.edu.udb.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.beans.Cupon;
import sv.edu.udb.www.beans.Empleado;
import sv.edu.udb.www.beans.Usuario;
import sv.edu.udb.www.model.EmpleadosModel;
import sv.edu.udb.www.model.OfertasModel;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.Validaciones;

@WebServlet(name = "EmpleadosController", urlPatterns = {"/empleados.do"})
public class EmpleadosController extends HttpServlet {

    UsuariosModel UM = new UsuariosModel();
    ArrayList listaErrores = new ArrayList();
    EmpleadosModel modelo = new EmpleadosModel();
    UsuariosModel modeloUsuario = new UsuariosModel();
    OfertasModel modeloOfertas = new OfertasModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            modeloOfertas.actualizarEstados();
            if (request.getSession().getAttribute("correo") == null || !request.getSession().getAttribute("estadoUsuario").toString().equals("3")) {
                response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=login");
                return;
            }

            if (request.getParameter("operacion") == null) {
                //Redireccionar a la página principal del empleado
                return;
            }
            String operacion = request.getParameter("operacion");

            switch (operacion) {
                case "obtener":
                    obtenerCupon(request, response);
                    break;
                case "canjear":
                    canjearCupon(request, response);
                    break;
                case "listar":
                    listar(request, response);
                    break;
                case "nuevo":
                    request.getRequestDispatcher("/Empresa/NuevoEmpleado.jsp").forward(request, response);
                    break;
                case "updateC":
                    request.getRequestDispatcher("/Empleado/cambiarContrasena.jsp").forward(request, response);
                    break;
                case "cambiarC":
                    cambiarContrasena(request, response);
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

    private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {

            String correo = (String) request.getSession().getAttribute("correo");
            request.setAttribute("listaEmpleados", modelo.listarEmpleados(correo));
            try {
                request.getRequestDispatcher("/Empresa/ListaEmpleados.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
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
                if (modelo.canjearCupon(codigoCupon) > 0) {
                    request.setAttribute("Exito", "El cupón ha sido canjeado, exitosamente");
                    response.sendRedirect("/Empleado/canjearCupon.jsp");
                }
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cambiarContrasena(HttpServletRequest request, HttpServletResponse response) {
        try {
            String contrasenaActual = request.getParameter("contrasenaActual");
            String confirmContra = request.getParameter("confirmarContrasena");
            String nuevaContrasena = request.getParameter("nuevaContrasena");

            //Se obtiene la variable de sesión, idUsuario
            int idUsuario = (Integer) request.getSession().getAttribute("idUsuario");
            System.out.println("Variable sesión: " + idUsuario);

            //Se manda a llamar el método para obtener la contraseña
            Usuario contrasenabdd = UM.obtenerContrasena(idUsuario);

            //Tipo de usuario que se recoge del formulario
            String tipoUsuario = request.getParameter("tipo");

            System.out.println(tipoUsuario);
            System.out.println(contrasenaActual);
            System.out.println(confirmContra);
            System.out.println(nuevaContrasena);
            System.out.println("BDD" + contrasenabdd.getContrasenia());

            if (contrasenaActual.equals("")) {
                request.setAttribute("Fracaso", "Ingrese su contraseña actual");
                request.getRequestDispatcher("/empleados.do?operacion=updateC").forward(request, response);
            }

            if (confirmContra.equals("")) {
                request.setAttribute("Fracaso", "Ingrese su contraseña actual");
                request.getRequestDispatcher("/empleados.do?operacion=updateC").forward(request, response);
            }

            if (nuevaContrasena.equals("")) {
                request.setAttribute("Fracaso", "Ingrese su nueva contraseña");
                request.getRequestDispatcher("/empleados.do?operacion=updateC").forward(request, response);
            }

            if (!contrasenabdd.getContrasenia().equals(contrasenaActual)) {
                request.setAttribute("Fracaso", "Contraseña incorrecta");
                request.getRequestDispatcher("/empleados.do?operacion=updateC").forward(request, response);
            }

            if (!contrasenaActual.equals(confirmContra)) {
                request.setAttribute("Fracaso", "Las contraseñas no coinciden");
                request.getRequestDispatcher("/empleados.do?operacion=updateC").forward(request, response);
            } else {
                if (UM.cambiarContrasena(idUsuario, nuevaContrasena) > 0) {
                    request.getRequestDispatcher("/Empleado/Home.jsp").forward(request, response); //Se deberá de llamar al método cerrar sesión
                }
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

