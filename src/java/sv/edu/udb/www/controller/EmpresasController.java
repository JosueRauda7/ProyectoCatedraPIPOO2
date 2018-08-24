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
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.Usuario;
import sv.edu.udb.www.model.EmpresasModel;
import sv.edu.udb.www.model.RubrosModel;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author Emerson Torres
 */
@WebServlet(name = "EmpresasController", urlPatterns = {"/empresas.do"})
public class EmpresasController extends HttpServlet {

    RubrosModel rubro = new RubrosModel();
    EmpresasModel modelo = new EmpresasModel();
    UsuariosModel modelo2 = new UsuariosModel();
    ArrayList listaErrores = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String operacion = request.getParameter("operacion");

            switch (operacion) {
                case "listar":
                    listar(request, response);
                    break;
                case "nuevo":
                    nuevo(request, response);
                    break;
                case "agregar":
                    agregar(request, response);
                    break;
                case "verificar":
                    confirmar(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);
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

    private void nuevo(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaRubros", rubro.obtenerRubro());
            request.getRequestDispatcher("/Administrador/AgregarEmpresas.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregar(HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErrores.clear();

            String cadenaAleatoria = UUID.randomUUID().toString();
            //Creacion password
            char[] caracteres;
            caracteres = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            String pass = "";
            for (int i = 0; i < 8; i++) {
                pass += caracteres[new Random().nextInt(62)];
            }
            //fin contraseña

            Usuario usuario = new Usuario();
            usuario.setCorreo(request.getParameter("correo"));
            usuario.setContrasenia(pass);
            usuario.setIdTipoUsuario(2);
            usuario.setConfirmado(0);
            usuario.setId_confirmacion(cadenaAleatoria);

            Empresa empresa = new Empresa();
            empresa.setNombreEmpresa(request.getParameter("nombreEmpresa"));
            empresa.setNombreContacto(request.getParameter("nombreContacto"));
            empresa.setDireccion(request.getParameter("direccion"));
            empresa.setTelefono(request.getParameter("telefono"));
            empresa.setIdRubro(Integer.parseInt(request.getParameter("rubro")));
            empresa.setComision(request.getParameter("comision"));

            if (Validaciones.isEmpty(usuario.getCorreo())) {
                listaErrores.add("El correo de la empresa es obligatorio");
            } else if (!Validaciones.esCorreo(usuario.getCorreo())) {
                listaErrores.add("El correo no tiene el formato correcto");
            }

            if (Validaciones.isEmpty(empresa.getNombreEmpresa())) {
                listaErrores.add("El nombre de la empresa es obligatorio");
            }
            if (Validaciones.isEmpty(empresa.getNombreContacto())) {
                listaErrores.add("El nombre del contacto es obligatorio");
            }
            if (Validaciones.isEmpty(empresa.getDireccion())) {
                listaErrores.add("La direccion es obligatoria obligatorio");
            }
            if (Validaciones.isEmpty(empresa.getTelefono())) {
                listaErrores.add("El telefono es obligatorio");
            }
            if (Validaciones.isEmpty(String.valueOf(empresa.getComision()))) {
                listaErrores.add("El nombre del contacto es obligatorio");
            }

            if (listaErrores.size() > 0) {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("empresa", empresa);
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("empresas.do?operacion=nuevo").forward(request, response);
            } else {
                if (modelo2.isertarUsuarioEmpresa(usuario) > 0) {
                    request.setAttribute("exito", "La empresa se ha ingresado exitosamente");
                    String texto = "Te has registrado exitosamente.<br>";
                    texto += "Para confirmar tu cuenta debes dar click ";

                    String enlace = request.getRequestURL().toString()
                            + "?operacion=verificar&id=" + cadenaAleatoria;
                    texto += "<a style='color: #fff; background-color: #428bca;border-color: #357ebd;display: inline-block; padding: 6px 12px;\n"
                            + "    margin-bottom: 0;\n"
                            + "    font-size: 14px;\n"
                            + "    font-weight: normal;\n"
                            + "    line-height: 1.428571429;\n"
                            + "    text-align: center;\n"
                            + "    white-space: nowrap;\n"
                            + "    vertical-align: middle;\n"
                            + "    cursor: pointer;\n"
                            + "    background-image: none;\n"
                            + "    border: 1px solid transparent;\n"
                            + "    border-radius: 4px;\n"
                            + "    -webkit-user-select: none;\n"
                            + "    -moz-user-select: none;\n"
                            + "    -ms-user-select: none;\n"
                            + "    -o-user-select: none;\n"
                            + "    user-select: none;'  target='a_blank' "
                            + "href='" + enlace + "'>aqui</a>";

                    Correo correo = new Correo();
                    correo.setAsunto("Confirmacion de registro");
                    correo.setMensaje(texto);
                    correo.setDestinatario(usuario.getCorreo());
                    correo.enviarCorreo();

                    empresa.setIdUsuario(modelo2.ultimoUsuarioEmpresa());

                    modelo.agregarEmpresa(empresa);
                    request.getRequestDispatcher("empresas.do?operacion=listar").forward(request, response);
                } else {
                    request.setAttribute("fracaso", "La empresa no se ha ingresado");
                    request.getRequestDispatcher("empresas.do?operacion=listar").forward(request, response);
                }
            }
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void confirmar(HttpServletRequest request, HttpServletResponse response) {
        try {
            modelo2.confirmarCuenta(request.getParameter("id"));
            request.getSession().setAttribute("exito", "Cuenta verificada exitosamente, "
                    + "ya puedes iniciar sesion");
            response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=login");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("empresas", modelo.listarEmpresas());
            request.getRequestDispatcher("/Administrador/ListarEmpresas.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
       
    }

}
