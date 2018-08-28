package sv.edu.udb.www.controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.beans.Usuario;
import sv.edu.udb.www.model.EmpleadosModel;
import sv.edu.udb.www.model.EmpresasModel;
import sv.edu.udb.www.model.EstadoOfertaModel;
import sv.edu.udb.www.model.OfertasModel;
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

    UsuariosModel UM = new UsuariosModel();
    OfertasModel modeloOfertas = new OfertasModel();
    EstadoOfertaModel modeloEstado = new EstadoOfertaModel();
    EmpleadosModel modeloEmpleado = new EmpleadosModel();
    EmpresasModel modeloEmpresa = new EmpresasModel();
    ArrayList listaErrores = new ArrayList();

    /*RubrosModel rubro = new RubrosModel();
    UsuariosModel modelo2 = new UsuariosModel();
    OfertasModel modelo3 = new OfertasModel();*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getSession().getAttribute("correo") == null || !request.getSession().getAttribute("estadoUsuario").toString().equals("2")) {
                response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=login");
                return;
            }
            String operacion = request.getParameter("operacion");

            switch (operacion) {

                case "home":
                    request.getRequestDispatcher("/Empresa/Home.jsp").forward(request, response);
                    break;
                case "listarOferta":
                    listarOferta(request, response);
                    break;
                case "nuevaOferta":
                    nuevaOferta(request, response);
                    break;
                case "detalleOferta":
                    detalleOferta(request, response);
                    break;
                case "listarEmpleado":
                    listarEmpleado(request, response);
                    break;
                case "nuevoEmpleado":
                    request.getRequestDispatcher("/Empresa/NuevoEmpleado.jsp").forward(request, response);
                    break;
                case "eliminarEmpleado":
                    eliminarEmpleado(request, response);
                    break;
                case "updateC":
                    request.getRequestDispatcher("/Empresa/cambiarContrasena.jsp").forward(request, response);
                    break;
                case "cambiarC":
                    cambiarContrasena(request, response);
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
        listaErrores.clear();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String directorio = getServletContext().getRealPath("/images/ofertas");
        MultipartRequest multi = new MultipartRequest(request, directorio, 1 * 1024 * 1024, new DefaultFileRenamePolicy());
        String operacion = multi.getParameter("operacion");
        if (operacion.equals("insertarOferta")) {
            try {
                insertarOferta(multi, request, response);
            } catch (ParseException ex) {
                Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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

    private void listarOferta(HttpServletRequest request, HttpServletResponse response) {
        try {
            int tipoOferta = 0;
            if (request.getParameter("tipoOferta") != null) {
                tipoOferta = Integer.parseInt(request.getParameter("tipoOferta"));
            }

            request.setAttribute("listaOfertas", modeloOfertas.listarOferta((String) request.getSession().getAttribute("correo"), tipoOferta));
            try {
                request.getRequestDispatcher("/Empresa/ListaOfertas.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void nuevaOferta(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaEstados", modeloEstado.listarEstadoOferta());
            request.getRequestDispatcher("/Empresa/NuevaOferta.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertarOferta(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        listaErrores.clear();
        //Si la cantidad es ilimitada o no 
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int ilimitado = 0;
            Oferta oferta = new Oferta();
            Date hoy = new Date();
            String actual = formato.format(hoy);

            ilimitado = Integer.parseInt(multi.getParameter("limitar"));
            oferta.setTituloOferta(multi.getParameter("titulo"));
            oferta.setPrecioRegular(multi.getParameter("precior"));
            oferta.setPrecioOferta(multi.getParameter("precioo"));
            oferta.setFechaInicio(actual);
            oferta.setFechaFin(multi.getParameter("fechaf"));
            oferta.setFechaLimite(multi.getParameter("fechal"));
            oferta.setDescripcionOferta(multi.getParameter("descripcion"));
            oferta.setOtrosDetalles(multi.getParameter("detalles"));

            oferta.setDescripcionOferta(multi.getParameter("descripcion"));
            oferta.setOtrosDetalles(multi.getParameter("detalles"));

            if (Validaciones.isEmpty(oferta.getTituloOferta())) {
                listaErrores.add("Titulo es obligatorio");
            }
            if (Validaciones.isEmpty(oferta.getPrecioRegular())) {
                listaErrores.add("Precio regular es obligatorio");
            } else if (!Validaciones.esDecimal(oferta.getPrecioRegular())) {
                listaErrores.add("Precio regular debe ser un decimal");
            } else if (!Validaciones.esDecimalPositivo(oferta.getPrecioRegular())) {
                listaErrores.add("Precio regular debe ser un decimal positivo");
            }

            if (Validaciones.isEmpty(oferta.getPrecioOferta())) {
                listaErrores.add("Precio oferta es obligatorio");
            } else if (!Validaciones.esDecimal(oferta.getPrecioOferta())) {
                listaErrores.add("Precio oferta debe ser un decimal");
            } else if (!Validaciones.esDecimalPositivo(oferta.getPrecioOferta())) {
                listaErrores.add("Precio oferta debe ser un decimal positivo");
            }

            if (ilimitado == 0) {
                oferta.setCantidadLimite(0);
            }

            if (ilimitado == 1) {
                if (Validaciones.isEmpty(multi.getParameter("cantidad"))) {
                    listaErrores.add("La cantidad de cupones es obligatorio");
                    oferta.setCantidadLimite(1);
                } else if (Integer.parseInt(multi.getParameter("cantidad")) <= 0) {
                    listaErrores.add("La cantidad de cupones debe ser mayor a 0");
                    oferta.setCantidadLimite(1);
                } else {
                    oferta.setCantidadLimite(Integer.parseInt(multi.getParameter("cantidad")));
                }

            }

            if (Validaciones.isEmpty(oferta.getFechaFin())) {
                listaErrores.add("La fecha final de la oferta es obligatoria");
            }

            if (Validaciones.isEmpty(oferta.getFechaLimite())) {
                listaErrores.add("La fecha limite de la oferta es obligatoria");
            }

            if (Validaciones.isEmpty(oferta.getDescripcionOferta())) {
                listaErrores.add("Agrega una descripción a la oferta");
            }
            if (Validaciones.isEmpty(oferta.getOtrosDetalles())) {
                listaErrores.add("Agrege otros detalles a la oferta");
            }
            if (multi.getFile("archivo") == null) {
                listaErrores.add("La imagen es obligatoria");
            } else {
                File ficheroTemp = multi.getFile("archivo");
                oferta.setUrl_foto(ficheroTemp.getName());
            }

            if (listaErrores.isEmpty()) {

                if (Double.parseDouble(oferta.getPrecioOferta()) > Double.parseDouble(oferta.getPrecioRegular())) {
                    listaErrores.add("El precio de la oferta no debe superar el precio regular");
                }

                //VALIDACION DE COMPARACION DE FECHAS AQUI
                //***************************************
                if (listaErrores.isEmpty()) {
                    if (modeloOfertas.insertarOferta(oferta, (String) request.getSession().getAttribute("correo")) == 1) {
                        request.getSession().setAttribute("exito", "Oferta registrada existosamente.");
                    } else {
                        request.getSession().setAttribute("fracaso", "Ocurrio un error, no se pudo registrar la oferta...");
                    }

                    response.sendRedirect(request.getContextPath() + "/ofertas.do?operacion=listar");
                } else {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("oferta", oferta);
                    request.getRequestDispatcher("/Empresa/NuevaOferta.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("oferta", oferta);
                request.getRequestDispatcher("/Empresa/NuevaOferta.jsp").forward(request, response);
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        try {

            String correo = (String) request.getSession().getAttribute("correo");
            request.setAttribute("listaEmpleados", modeloEmpleado.listarEmpleados(correo));
            try {
                request.getRequestDispatcher("/Empresa/ListaEmpleados.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void detalleOferta(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            int codigo = Integer.parseInt(request.getParameter("id"));
            Oferta oferta = modeloOfertas.detalleOferta(codigo);
            Empresa empresa = modeloEmpresa.detalleEmpresa((String) request.getSession().getAttribute("correo"));
            JSONObject json = new JSONObject();
            json.put("CuponesVendidos", oferta.getCantidadVendida());
            json.put("CuponesDisponibles", oferta.getCantidadLimite());
            json.put("IngresosTotales", (oferta.getCantidadVendida() * Double.parseDouble(oferta.getPrecioOferta())));
            json.put("Cargoporservicios", (Double.parseDouble(empresa.getComision())) * (oferta.getCantidadVendida() * Double.parseDouble(oferta.getPrecioOferta())));
            json.put("Descripcion", oferta.getDescripcionOferta());
            json.put("Detalles", oferta.getOtrosDetalles());
            json.put("Titulo", oferta.getTituloOferta());
            out.print(json);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        try {
            int codigo = Integer.parseInt(request.getParameter("id"));
            if (modeloEmpleado.eliminarEmpleado(codigo) > 0) {
                request.setAttribute("exito", "Empleado eliminado exitosamente");

            } else {
                request.setAttribute("fracaso", "No se puede eliminar este empleado");
            }
            request.getRequestDispatcher("/empresas.do?operacion=listarEmpleado").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
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
                request.getRequestDispatcher("/empresas.do?operacion=updateC").forward(request, response);
            }

            if (confirmContra.equals("")) {
                request.setAttribute("Fracaso", "Ingrese su contraseña actual");
                request.getRequestDispatcher("/empresas.do?operacion=updateC").forward(request, response);
            }

            if (nuevaContrasena.equals("")) {
                request.setAttribute("Fracaso", "Ingrese su nueva contraseña");
                request.getRequestDispatcher("/empresas.do?operacion=updateC").forward(request, response);
            }

            if (!contrasenabdd.getContrasenia().equals(contrasenaActual)) {
                request.setAttribute("Fracaso", "Contraseña incorrecta");
                request.getRequestDispatcher("/empresas.do?operacion=updateC").forward(request, response);
            }

            if (!contrasenaActual.equals(confirmContra)) {
                request.setAttribute("Fracaso", "Las contraseñas no coinciden");
                request.getRequestDispatcher("/empresas.do?operacion=updateC").forward(request, response);
            } else {
                if (UM.cambiarContrasena(idUsuario, nuevaContrasena) > 0) {
                    request.getRequestDispatcher("/Empresa/Home.jsp").forward(request, response);
                }
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
