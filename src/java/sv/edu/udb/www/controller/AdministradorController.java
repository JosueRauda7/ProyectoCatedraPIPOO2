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
import sv.edu.udb.www.beans.Rubro;
import sv.edu.udb.www.beans.Usuario;
import sv.edu.udb.www.model.ClientesModel;
import sv.edu.udb.www.model.EmpresasModel;
import sv.edu.udb.www.model.OfertasModel;
import sv.edu.udb.www.model.RubrosModel;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author Emerson Torres
 */
@WebServlet(name = "AdministradorController", urlPatterns = {"/administrador.do"})
public class AdministradorController extends HttpServlet {

    UsuariosModel UM = new UsuariosModel();
    RubrosModel rubro = new RubrosModel();
    EmpresasModel modelo = new EmpresasModel();
    UsuariosModel modelo2 = new UsuariosModel();
    OfertasModel modelo3 = new OfertasModel();
    ClientesModel modelo4 = new ClientesModel();
    ArrayList listaErrores = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            modelo3.actualizarEstados();
            String operacion = request.getParameter("operacion");
            switch (operacion) {
                case "listarEmpresa":
                    listarEmpresas(request, response);
                    break;
                case "nuevaEmpresa":
                    nuevaEmpresa(request, response);
                    break;
                case "agregarEmpresa":
                    agregarEmpresa(request, response);
                    break;
                case "verificarCorreo":
                    confirmarCorreo(request, response);
                case "eliminarEmpresa":
                    eliminarEmpresa(request, response);
                case "modificarEmpresa":
                    modificarEmpresa(request, response);
                case "editarEmpresa":
                    editarEmpresa(request, response);
                case "ofertasEmpresa":
                    ofertasEmpresa(request, response);
                case "listarRubro":
                    listarRubro(request, response);
                    break;
                case "agregarRubro":
                    agregarRubro(request, response);
                    break;
                case "eliminarRubro":
                    eliminarRubro(request, response);
                    break;
                case "modificarRubro":
                    modificarRubro(request, response);
                    break;
                case "updateC":
                    request.getRequestDispatcher("/Administrador/cambiarContrasena.jsp").forward(request, response);
                    break;
                case "cambiarC":
                    cambiarContrasena(request, response);
                    break;
                case "verClientes":
                    verClientes(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void listarEmpresas(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("empresas", modelo.listarEmpresas());
            request.getRequestDispatcher("/Administrador/ListarEmpresas.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void nuevaEmpresa(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaRubros", rubro.obtenerRubro());
            request.getRequestDispatcher("/Administrador/AgregarEmpresas.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarEmpresa(HttpServletRequest request, HttpServletResponse response) {
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
                //fin contraseÃ±a
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
                    request.getRequestDispatcher("administrador.do?operacion=listarEmpresa").forward(request, response);
                } else {
                    request.setAttribute("fracaso", "La empresa no se ha ingresado");
                    request.getRequestDispatcher("administrador.do?operacion=listarEmpresa").forward(request, response);
                }
            }
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void confirmarCorreo(HttpServletRequest request, HttpServletResponse response) {
        try {
            modelo2.confirmarCuenta(request.getParameter("id"));
            request.getSession().setAttribute("exito", "Cuenta verificada exitosamente, "
                    + "ya puedes iniciar sesion");
            response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=login");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarEmpresa(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo = request.getParameter("idemp");
            int iduser = Integer.parseInt(request.getParameter("iduser"));

            if (modelo.eliminarEmpresas(codigo) > 0 && modelo2.eliminarUsuarioEmpresa(iduser) > 0) {
                request.setAttribute("exito", "La empresa ha sido eliminada exitosamente");
                request.getRequestDispatcher("administrador.do?operacion=listarEmpresa").forward(request, response);
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarEmpresa(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo = request.getParameter("codigo");
            request.setAttribute("empresa", modelo.obtenerEmpresa(codigo));
            request.setAttribute("listaRubros", rubro.obtenerRubro());
            request.getRequestDispatcher("/Administrador/ModificarEmpresas.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editarEmpresa(HttpServletRequest request, HttpServletResponse response) {
        try {
            Empresa empresa = new Empresa();
            empresa.setNombreEmpresa(request.getParameter("nombreEmpresa"));
            empresa.setNombreContacto(request.getParameter("nombreContacto"));
            empresa.setDireccion(request.getParameter("direccion"));
            empresa.setTelefono(request.getParameter("telefono"));
            empresa.setIdRubro(Integer.parseInt(request.getParameter("rubro")));
            empresa.setComision(request.getParameter("comision"));
            empresa.setCodigoEmpresa(request.getParameter("codigoEmpresa"));

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
                request.getRequestDispatcher("empresas.do?operacion=modificar").forward(request, response);
            } else {
                if (modelo.modificarEmpresa(empresa) > 0) {
                    request.setAttribute("exito", "La empresa se ha modificado exitosamente");
                    request.getRequestDispatcher("administrador.do?operacion=listarEmpresa").forward(request, response);
                } else {
                    request.setAttribute("fracaso", "error al modificar");
                    request.getRequestDispatcher("administrador.do?operacion=listarEmpresa").forward(request, response);
                }
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ofertasEmpresa(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo = request.getParameter("codigo");
            request.setAttribute("ofertasEspera", modelo3.ListarOfertasEspera(codigo));
            request.setAttribute("ofertasFuturas", modelo3.ListarOfertasFutura(codigo));
            request.setAttribute("ofertasActivas", modelo3.ListarOfertasActiva(codigo));
            request.setAttribute("ofertasFinalizadas", modelo3.ListarOfertasFinalizada(codigo));
            request.setAttribute("ofertasRechazadas", modelo3.ListarOfertasRechazada(codigo));
            request.setAttribute("ofertasDesartadas", modelo3.ListarOfertasDescartada(codigo));
            request.getRequestDispatcher("/Administrador/VerOfertas.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarRubro(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaRubros", rubro.obtenerRubro());
            request.getRequestDispatcher("/Administrador/ListaRubros.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarRubro(HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErrores.clear();
            Rubro rub = new Rubro();
            rub.setRubro(request.getParameter("rubro"));
            if (Validaciones.isEmpty(rub.getRubro())) {
                listaErrores.add("El campo nombre del rubro es obligatorio");
            }
            if (rubro.validarRubro(rub) > 0) {
                listaErrores.add("Este rubro ya existe");
            }
            if (listaErrores.size() > 0) {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("rubro", rubro);
                request.getRequestDispatcher("administrador.do?operacion=listarRubro").forward(request, response);
            } else {
                if (rubro.insertarRubro(rub) > 0) {
                    request.setAttribute("exito", "El rubro ha sido insertado correctamente");
                    request.getRequestDispatcher("administrador.do?operacion=listarRubro").forward(request, response);
                } else {
                    request.setAttribute("fracaso", "El rubro no se ha podido insertar");
                    request.getRequestDispatcher("administrador.do?operacion=listarRubro").forward(request, response);
                }
            }

        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarRubro(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (rubro.eliminarRubro(id) > 0) {
                request.setAttribute("exito", "El rubro se ha eliminado correctamente");
            } else {
                request.setAttribute("exito", "Este rubro no puede eliminarse");
            }
            request.getRequestDispatcher("rubros.do?operacion=listar").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarRubro(HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErrores.clear();
            Rubro rub = new Rubro();
            rub.setRubro(request.getParameter("rubro"));
            rub.setIdRubro(Integer.parseInt(request.getParameter("id")));
            if (Validaciones.isEmpty(rub.getRubro())) {
                listaErrores.add("El campo nombre del rubro es obligatorio");
            }
            if (rubro.validarRubro(rub) > 0) {
                listaErrores.add("Este rubro ya existe");
            }
            if (listaErrores.size() > 0) {

                request.setAttribute("fracaso", "Este rubro ya existe o esta vacio");
                request.getRequestDispatcher("rubros.do?operacion=listar").forward(request, response);
            } else {
                if (rubro.modificarRubro(rub) > 0) {
                    request.setAttribute("exito", "El rubro ha sido modificado correctamente");
                    request.getRequestDispatcher("rubros.do?operacion=listar").forward(request, response);
                } else {
                    request.setAttribute("fracaso", "El rubro no se ha podido modificar");
                    request.getRequestDispatcher("rubros.do?operacion=listar").forward(request, response);
                }
            }

        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
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
                request.getRequestDispatcher("/administrador.do?operacion=updateC").forward(request, response);
            }

            if (confirmContra.equals("")) {
                request.setAttribute("Fracaso", "Ingrese su contraseña actual");
                request.getRequestDispatcher("/administrador.do?operacion=updateC").forward(request, response);
            }

            if (nuevaContrasena.equals("")) {
                request.setAttribute("Fracaso", "Ingrese su nueva contraseña");
                request.getRequestDispatcher("/administrador.do?operacion=updateC").forward(request, response);
            }

            if (!contrasenabdd.getContrasenia().equals(contrasenaActual)) {
                request.setAttribute("Fracaso", "Contraseña incorrecta");
                request.getRequestDispatcher("/administrador.do?operacion=updateC").forward(request, response);
            }

            if (!contrasenaActual.equals(confirmContra)) {
                request.setAttribute("Fracaso", "Las contraseñas no coinciden");
                request.getRequestDispatcher("/administrador.do?operacion=updateC").forward(request, response);
            } else {
                if (UM.cambiarContrasena(idUsuario, nuevaContrasena) > 0) {
                    request.getRequestDispatcher("/Administrador/Home.jsp").forward(request, response);
                }
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verClientes(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaClientes",modelo4.listarClientes() );
            request.getRequestDispatcher("/Administrador/VerClientes.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
