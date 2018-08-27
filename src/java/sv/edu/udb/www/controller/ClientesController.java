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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import sv.edu.udb.www.beans.EstadoCupon;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.beans.Rubro;
import sv.edu.udb.www.model.ClientesModel;

/**
 *
 * @author admi
 */
@WebServlet(name = "ClientesController", urlPatterns = {"/clientes.do"})
public class ClientesController extends HttpServlet {

    ClientesModel model = new ClientesModel();
    ArrayList<String> listaErrores = new ArrayList<String>();
    List<Oferta> ofertas = new ArrayList();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getSession().getAttribute("correo")==null||!request.getSession().getAttribute("estadoUsuario").toString().equals("4")){
                response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=login");
                return;
            }
            if (request.getParameter("operacion") == null) {
                listar(request, response);
                return;
            }
            String operacion = request.getParameter("operacion");
            switch (operacion) {
                case "inicio":
                    inicio(request, response);
                    break;
                case "listar":
                    listar(request, response);
                    break;
                case "filtrar":
                    filtrar(request, response);
                    break;
                case "misCupones":
                    misCupones(request, response);
                    break;
                case "categorizar":
                    categorizar(request, response);
                    break;
                case "agregar":
                    agregar(request, response);
                    break;
                case "ver":
                    ver(request,response);
                    break;
                case "cerrar":
                    cerrarSesion(request,response);
                    break;
                case "detalles":
                    detalles(request,response);
                    break;
                case "cancelar":
                    cancelar(request,response);
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
        List<Rubro> rubro = new ArrayList();
        listaErrores.clear();
        try {
            if (model.obtenerRubro() == null) {
                listaErrores.add("Error al cargar los rubros");
            } else if (model.listarCupones() == null) {
                listaErrores.add("Error al cargar los cupones");
            } else {
                rubro = model.obtenerRubro();
                request.setAttribute("ofertita", model.listarCupones());
                request.setAttribute("rubrito", rubro);
                request.getRequestDispatcher("/Cliente/ComprarCupones.jsp").forward(request, response);
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void filtrar(HttpServletRequest request, HttpServletResponse response) {
        List<Rubro> rubro = new ArrayList();
        listaErrores.clear();
        try {
            if (model.obtenerRubro() == null) {
                listaErrores.add("Error al cargar los rubros");
            } else {
                if (request.getParameter("rubroFiltro") == null) {
                    response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=listar");
                } else {
                    if (request.getParameter("rubroFiltro").equals("0")) {
                        response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=listar");
                    } else {
                        request.setAttribute("rubro", request.getParameter("rubroFiltro"));
                        rubro = model.obtenerRubro();
                        request.setAttribute("ofertita", model.listarCupones(request.getParameter("rubroFiltro")));
                        request.setAttribute("rubrito", rubro);
                        request.getRequestDispatcher("/Cliente/ComprarCupones.jsp").forward(request, response);
                    }
                }
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void misCupones(HttpServletRequest request, HttpServletResponse response) {
        List<EstadoCupon> estado = new ArrayList();
        listaErrores.clear();
        try {
            if (model.listarEstadosCupon() == null) {
                listaErrores.add("Error al cargar los rubros");
            } else if (model.listarMisCupones("1") == null) {
                listaErrores.add("Error al cargar los cupones");
            } else {
                estado = model.listarEstadosCupon();
                request.setAttribute("ofertita", model.listarMisCupones("1"));
                request.setAttribute("estado", estado);
                request.getRequestDispatcher("/Cliente/MisCupones.jsp").forward(request, response);
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void categorizar(HttpServletRequest request, HttpServletResponse response) {
        List<EstadoCupon> estado = new ArrayList();
        listaErrores.clear();
        try {
            if (model.listarEstadosCupon() == null) {
                listaErrores.add("Error al cargar los rubros");
            } else if (model.listarMisCupones(request.getParameter("categoriaFiltro")) == null) {
                listaErrores.add("Error al cargar los cupones");
            } else {
                request.setAttribute("categoria", request.getParameter("categoriaFiltro"));
                estado = model.listarEstadosCupon();
                request.setAttribute("ofertita", model.listarMisCupones(request.getParameter("categoriaFiltro")));
                request.setAttribute("estado", estado);
                request.getRequestDispatcher("/Cliente/MisCupones.jsp").forward(request, response);
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inicio(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/Cliente/InicioCliente.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregar(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idOferta = Integer.parseInt(request.getParameter("id"));
            ofertas.add(model.obtenerOferta(idOferta));
            request.getSession().setAttribute("ofertas", ofertas);
            response.sendRedirect(request.getContextPath()+"/clientes.do?operacion=ver");
        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("correo",null);
            request.getSession().setAttribute("estadoUsuario",null);
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ver(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("ofertas", ofertas);
            request.getRequestDispatcher("/Cliente/VerCarrito.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) {
        try {
            ofertas.remove(Integer.parseInt(request.getParameter("id")));
            request.getSession().setAttribute("ofertas", ofertas);
            request.getRequestDispatcher("/Cliente/VerCarrito.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void detalles(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = null;
            out = response.getWriter();
            String codigo=request.getParameter("id");
            Oferta oferta = model.obtenerCupones(codigo);
            JSONObject json = new JSONObject();
            json.put("codigo",oferta.getIdOferta());
            json.put("titulo",oferta.getTituloOferta());
            json.put("precioR",oferta.getPrecioRegular());
            json.put("precioO",oferta.getPrecioOferta());
            json.put("fechaI",oferta.getFechaInicio());
            json.put("fechaF",oferta.getFechaFin());
            json.put("fechaL",oferta.getFechaLimite());
            json.put("cantidad",oferta.getCantidadLimite());
            json.put("descripcion", oferta.getDescripcionOferta());
            json.put("otros",oferta.getOtrosDetalles());
            json.put("empresa", oferta.getNombreEmpresa());
            out.print(json);
        } catch (SQLException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
