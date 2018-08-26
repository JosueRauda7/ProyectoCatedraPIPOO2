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
import sv.edu.udb.www.model.EstadoOfertaModel;
import sv.edu.udb.www.model.OfertasModel;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author ivanm
 */
@WebServlet(name = "OfertasController", urlPatterns = {"/ofertas.do"})
public class OfertasController extends HttpServlet {

    OfertasModel modeloOfertas = new OfertasModel();
    EstadoOfertaModel modeloEstado = new EstadoOfertaModel();
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
        response.setContentType("text/html;charset=UTF-8");
        String operacion = request.getParameter("operacion");

        switch (operacion) {

            case "home":
                request.getRequestDispatcher("/Empresa/Home.jsp").forward(request, response);
                break;
            case "listar":
                listar(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
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
        listaErrores.clear();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String directorio = getServletContext().getRealPath("/images/ofertas");
        MultipartRequest multi = new MultipartRequest(request, directorio, 1 * 1024 * 1024, new DefaultFileRenamePolicy());
        String operacion = multi.getParameter("operacion");
        if (operacion.equals("insertar")) {
            try {
                insertar(multi, request, response);
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

    private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {
            ;
            request.setAttribute("listaOfertas", modeloOfertas.listarOferta((String) request.getSession().getAttribute("correo")));
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
            request.setAttribute("listaEstados", modeloEstado.listarEstadoOferta());
            request.getRequestDispatcher("/Empresa/NuevaOferta.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertar(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) throws ParseException {
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
            
            if(ilimitado==1){
                if (Validaciones.isEmpty(multi.getParameter("cantidad"))) {
                    listaErrores.add("La cantidad de cupones es obligatorio");
                    oferta.setCantidadLimite(1);
                } else if (Integer.parseInt(multi.getParameter("cantidad")) <= 0) {
                    listaErrores.add("La cantidad de cupones debe ser mayor a 0");
                    oferta.setCantidadLimite(1);
                }else{
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

}
