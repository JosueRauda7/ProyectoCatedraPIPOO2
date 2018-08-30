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
import sv.edu.udb.www.beans.Cliente;
import sv.edu.udb.www.beans.Empleado;
import sv.edu.udb.www.beans.Usuario;
import sv.edu.udb.www.model.OfertasModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author ivanm
 */
@WebServlet(name = "UsuariosController", urlPatterns = {"/usuarios.do"})
public class UsuariosController extends HttpServlet {

    UsuariosModel UM = new UsuariosModel();
    OfertasModel modeloOfertas = new OfertasModel();
    ArrayList listaErrores = new ArrayList();

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
        String operacion = request.getParameter("operacion");
        
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameter("operacion").equals("cerrar")){
            cerrarSesion(request,response);
            return;
        }
        if (request.getSession().getAttribute("correo") != null || request.getSession().getAttribute("estadoUsuario") != null) {
            switch (request.getSession().getAttribute("estadoUsuario").toString()) {
                case "1":
                    //Administrador
                    return;
                case "2":
                    //Empresa
                    return;
                case "3":
                    //Empleado
                    return;
                case "4":
                    //Cliente
                    response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=inicio");
                    return;
                default:
                    response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=login");
                    return;
            }
        }
        
        switch (operacion) {
            case "registro":
                request.getRequestDispatcher("/Registro.jsp").forward(request, response);
                break;
            case "insertar":
                insertar(request, response);
                break;
            case "insertarE":
                insertarUsuarioEmpleado(request, response);
                break;
            case "login":
                request.getRequestDispatcher("/Login.jsp").forward(request, response);
                break;
            case "verificar":
                confirmar(request, response);
                break;
            case "ingresar":
                ingresar(request, response);
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

    private void insertar(HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErrores.clear();
            PrintWriter out = response.getWriter();

            Usuario newUsuario = new Usuario();
            Cliente newCliente = new Cliente();

            newUsuario.setCorreo(request.getParameter("correo"));
            newUsuario.setContrasenia(request.getParameter("contra"));
            newUsuario.setConfirmado(0);

            newCliente.setNombreClientes(request.getParameter("nombre"));
            newCliente.setApellidosClientes(request.getParameter("apellido"));
            newCliente.setDireccion(request.getParameter("direccion"));
            newCliente.setDui(request.getParameter("dui"));

            if (newCliente.getNombreClientes().isEmpty()) {
                listaErrores.add("Ingrese sus nombres.");
            } else if (Validaciones.esEntero(newCliente.getNombreClientes())) {
                listaErrores.add("Ingrese sus nombres correctamente.");
            }
            if (newCliente.getApellidosClientes().isEmpty()) {
                listaErrores.add("Ingrese sus apellidos.");
            } else if (Validaciones.esEntero(newCliente.getApellidosClientes())) {
                listaErrores.add("Ingrese sus apellidos correctamente.");
            }
            if (newCliente.getDireccion().isEmpty()) {
                listaErrores.add("Ingrese su dirección.");
            }
            if (!Validaciones.esDui(newCliente.getDui())) {
                listaErrores.add("Ingrese un DUI valido.");
            }
            if (!Validaciones.esCorreo(newUsuario.getCorreo())) {
                listaErrores.add("Ingrese un correo valido.");
            }
            if (Validaciones.esContraseña(newUsuario.getContrasenia())) {
                listaErrores.add("La contraseña debe tener una longitud mínima de 8 caracteres"
                        + " y debe contener al menos una mayuscula, "
                        + "una minuscula y un numero o caracter especial");
            }

            if (listaErrores.isEmpty()) {
                String cadenaAleatoria = UUID.randomUUID().toString();

                if (UM.insertarUsuario(newUsuario, newCliente, cadenaAleatoria) > 0) {
                    request.getSession().setAttribute("exito", "Usuario registrado "
                            + "existosamente. Se te ha enviado un correo para que "
                            + "confirmes tu cuenta");

                    String texto = "Te has registrado exitosamente.<br>";
                    texto += "Para confirmar tu cuenta debes dar click ";

                    String enlace = request.getRequestURL().toString()
                            + "?operacion=verificar&id=" + cadenaAleatoria;
                    texto += "<a target='a_blank' "
                            + "href='" + enlace + "'>aqui</a>";

                    Correo correo = new Correo();
                    correo.setAsunto("Confirmacion de registro");
                    correo.setMensaje(texto);
                    correo.setDestinatario(newUsuario.getCorreo());
                    correo.enviarCorreo();
                    response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=login");
                } else {
                    listaErrores.add("Este correo ya esta registrado");
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("usuario", newUsuario);
                    request.setAttribute("cliente", newCliente);
                    request.getRequestDispatcher("/usuarios.do?operacion=registro").forward(request, response);
                }
            } else {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("usuario", newUsuario);
                request.setAttribute("cliente", newCliente);
                request.getRequestDispatcher("/usuarios.do?operacion=registro").forward(request, response);
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertarUsuarioEmpleado(HttpServletRequest request, HttpServletResponse response) {
        listaErrores.clear();
        try {
            Usuario usuario = new Usuario();
            Empleado empleado = new Empleado();

            //Creacion password
            char[] caracteres;
            caracteres = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            String pass = "";
            for (int i = 0; i < 8; i++) {
                pass += caracteres[new Random().nextInt(62)];
            }
            //fin contraseña

            usuario.setCorreo(request.getParameter("correo"));
            usuario.setContrasenia(pass);
            empleado.setNombreEmpleado(request.getParameter("nombre"));
            empleado.setApellidoEmpleado(request.getParameter("apellido"));

            if (Validaciones.isEmpty(empleado.getNombreEmpleado())) {
                listaErrores.add("El nombre es obligatorio");
            }
            if (Validaciones.isEmpty(empleado.getApellidoEmpleado())) {
                listaErrores.add("El apellido es obligatorio");
            }
            if (Validaciones.isEmpty(usuario.getCorreo())) {
                listaErrores.add("El correo es obligatorio");
            } else if (!Validaciones.esCorreo(usuario.getCorreo())) {
                listaErrores.add("El correo electronico no tiene el formato correcto");
            }
            if (listaErrores.size() > 0) {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("usuario", usuario);
                request.setAttribute("empleado", empleado);
                request.getRequestDispatcher("/empresas.do?operacion=nuevoEmpleado").forward(request, response);
            } else {
                String cadenaAleatoria = UUID.randomUUID().toString();
                if (UM.insertarUsuarioEmpleado(usuario, empleado, (String) request.getSession().getAttribute("correo"), cadenaAleatoria) > 0) {
                    request.getSession().setAttribute("exito", "Empleado registrado "
                            + "existosamente.");

                    String texto = "Has sido registrado exitosamente a la cuponera.<br>";
                    texto += "Anota tu contraseña: " + pass + " (puedes cambiarla cuando quieras)<br> ";
                    texto += "Para confirmar tu cuenta debes dar click ";

                    String enlace = request.getRequestURL().toString()
                            + "?operacion=verificar&id=" + cadenaAleatoria;
                    texto += "<a target='a_blank' "
                            + "href='" + enlace + "'>aqui</a>";

                    Correo correo = new Correo();
                    correo.setAsunto("Confirmacion de registro");
                    correo.setMensaje(texto);
                    correo.setDestinatario(usuario.getCorreo());
                    correo.enviarCorreo();
                    response.sendRedirect(request.getContextPath() + "/empresas.do?operacion=listarEmpleado");
                } else {
                    listaErrores.add("Este correo ya esta registrado");
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("usuario", usuario);
                    request.setAttribute("empleado", empleado);
                    request.getRequestDispatcher("/empresas.do?operacion=listarEmpleado").forward(request, response);
                }
            }
        } catch (ServletException | IOException ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void confirmar(HttpServletRequest request, HttpServletResponse response) {
        try {
            UM.confirmarCuenta(request.getParameter("id"));
            request.getSession().setAttribute("exito", "Cuenta verificada exitosamente, "
                    + "ya puedes iniciar sesion");
            response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=login");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ingresar(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            int estado=0;
            int idUsuario=0;
            Usuario usuario = new Usuario();
            usuario.setCorreo(request.getParameter("correo"));
            usuario.setContrasenia(request.getParameter("password"));
            request.getSession().setAttribute("correo", request.getParameter("correo"));
            
            //int estado = UM.verificar(usuario);
            Usuario usu = new Usuario();
            
            if(UM.verificar(usuario)==null){
                estado=0;
            }else{
                 usu= UM.verificar(usuario);
                estado = usu.getIdTipoUsuario();
                idUsuario = usu.getIdUsuario();
            }           
            
            
            request.getSession().setAttribute("idUsuario", idUsuario);
            request.getSession().setAttribute("estadoUsuario", estado);
            

            System.out.println("Variable sesión: " + idUsuario);
            
            modeloOfertas.actualizarEstados();
            

            switch (estado) {

                case -1:
                    request.getSession().setAttribute("fracaso", "Usuario y/o contraseña incorrecta");
                    cerrarSesion(request,response);
                    break;
                case 0:
                    request.getSession().setAttribute("fracaso", "Usuario y/o contraseña incorrecta");
                    cerrarSesion(request,response);
                    break;

                case 1:
                    //Administrador
                    request.getRequestDispatcher("/Administrador/Home.jsp").forward(request, response);
                    break;
                case 2:
                    //Empresa                                     
                    response.sendRedirect(request.getContextPath() + "/empresas.do?operacion=home");
                    //request.getRequestDispatcher("/empresas/Home.jsp").forward(request, response);
                    break;
                case 3:
                    //Empleado
                    //response.sendRedirect(request.getContextPath() + "/empresas.do?operacion=home");
                    request.getRequestDispatcher("/Empleado/canjearCupon.jsp").forward(request, response);
                    break;
                case 4:
                    //Cliente
                    request.getRequestDispatcher("/clientes.do?operacion=inicio").forward(request, response);
                    break;

                default:
                    request.getSession().setAttribute("fracaso", "Usuario y/o contraseña incorrecta");
                    break;

                    
            }

        } catch (SQLException | IOException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("correo", null);
            request.getSession().setAttribute("estadoUsuario", null);
            response.sendRedirect(request.getContextPath()+"/usuarios.do?operacion=login");
        } catch (IOException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
