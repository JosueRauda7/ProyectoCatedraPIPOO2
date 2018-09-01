/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Cliente;
import sv.edu.udb.www.beans.Cupon;
import sv.edu.udb.www.beans.EstadoCupon;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.beans.Rubro;
import sv.edu.udb.www.beans.Usuario;

/**
 *
 * @author ivanm
 */
public class ClientesModel extends Conexion {

    public List<Cliente> listarClientes() throws SQLException {
        try {
            String sql = "SELECT c.*, u.Correo FROM clientes c INNER JOIN usuarios u ON c.IdUsuario = u.IdUsuario";
            List<Cliente> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setNombreClientes(rs.getString("NombreClientes"));
                cliente.setApellidosClientes(rs.getString("ApellidosClientes"));
                cliente.setDireccion(rs.getString("Direccion"));
                cliente.setDui(rs.getString("Dui"));
                cliente.setUsuario(new Usuario(rs.getString("Correo")));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }finally{
        this.desconectar();
        }
    }

    public int insertarCliente(int idUsuario, Cliente cliente) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "INSERT INTO clientes VALUES(NULL,?,?,?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, cliente.getNombreClientes());
            st.setString(2, cliente.getApellidosClientes());
            st.setString(3, cliente.getDireccion());
            st.setString(4, cliente.getDui());
            st.setInt(5, idUsuario);
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public List<Rubro> obtenerRubro() throws SQLException {
        try {
            List<Rubro> rubro = new ArrayList();
            this.conectar();
            String query = "SELECT * FROM rubros";
            st = conexion.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Rubro miRubro = new Rubro();
                miRubro.setIdRubro(Integer.parseInt(rs.getString("IdRubro")));
                miRubro.setRubro(rs.getString("Rubro"));
                rubro.add(miRubro);
            }
            this.desconectar();
            return rubro;
        } catch (Exception e) {
            this.desconectar();
            return null;
        }
    }

    public List<Oferta> listarCupones() throws SQLException {
        try {
            List<Oferta> ofertas = new ArrayList();
            this.conectar();
            String sql = "SELECT * FROM ofertas WHERE IdEstado=3";
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(Integer.parseInt(rs.getString("IdOferta")));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getString("FechaInicio"));
                oferta.setFechaFin(rs.getString("FechaFin"));
                oferta.setFechaLimite(rs.getString("FechaLimite"));
                oferta.setCantidadLimite(Integer.parseInt(rs.getString("Cantidadlimite")));
                oferta.setDescripcionOferta(rs.getString("DescripcionOferta"));
                oferta.setOtrosDetalles(rs.getString("OtrosDetalles"));
                oferta.setUrl_foto(rs.getString("Url_foto"));
                ofertas.add(oferta);
            }
            this.desconectar();
            return ofertas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<Oferta> listarCupones(String idRubro) throws SQLException {
        try {
            List<Oferta> ofertas = new ArrayList();
            String sql = "SELECT ofertas.IdOferta,ofertas.TituloOferta,ofertas.PrecioRegular,\n"
                    + "       ofertas.PrecioOferta,ofertas.FechaInicio,ofertas.FechaFin,ofertas.FechaLimite,\n"
                    + "       ofertas.CantidadLimite,ofertas.DescripcionOferta,ofertas.OtrosDetalles,ofertas.IdEstado,\n"
                    + "       ofertas.Justificacion,ofertas.CodigoEmpresa,ofertas.Url_foto,empresas.IdRubro\n"
                    + "FROM ofertas inner join empresas\n"
                    + "on (empresas.CodigoEmpresa=ofertas.CodigoEmpresa)  WHERE empresas.IdRubro=? AND ofertas.IdEstado=3";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(idRubro));
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(Integer.parseInt(rs.getString("IdOferta")));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getString("FechaInicio"));
                oferta.setFechaFin(rs.getString("FechaFin"));
                oferta.setFechaLimite(rs.getString("FechaLimite"));
                oferta.setCantidadLimite(Integer.parseInt(rs.getString("Cantidadlimite")));
                oferta.setDescripcionOferta(rs.getString("DescripcionOferta"));
                oferta.setOtrosDetalles(rs.getString("OtrosDetalles"));
                oferta.setUrl_foto(rs.getString("Url_foto"));
                ofertas.add(oferta);
            }
            this.desconectar();
            return ofertas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public Oferta obtenerCupones(String idOferta) throws SQLException {
        try {
            Oferta oferta = new Oferta();
            String sql = "SELECT ofertas.IdOferta,ofertas.TituloOferta,ofertas.PrecioRegular,\n"
                    + "       ofertas.PrecioOferta,ofertas.FechaInicio,ofertas.FechaFin,ofertas.FechaLimite,\n"
                    + "       ofertas.CantidadLimite,ofertas.DescripcionOferta,ofertas.OtrosDetalles,ofertas.IdEstado,\n"
                    + "       ofertas.Justificacion,ofertas.CodigoEmpresa,ofertas.Url_foto,empresas.IdRubro,empresas.NombreEmpresa\n"
                    + "FROM ofertas inner join empresas\n"
                    + "on (empresas.CodigoEmpresa=ofertas.CodigoEmpresa)  WHERE ofertas.IdOferta=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(idOferta));
            rs = st.executeQuery();
            while (rs.next()) {
                oferta.setIdOferta(Integer.parseInt(rs.getString("IdOferta")));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getString("FechaInicio"));
                oferta.setFechaFin(rs.getString("FechaFin"));
                oferta.setFechaLimite(rs.getString("FechaLimite"));
                oferta.setCantidadLimite(Integer.parseInt(rs.getString("Cantidadlimite")));
                oferta.setDescripcionOferta(rs.getString("DescripcionOferta"));
                oferta.setOtrosDetalles(rs.getString("OtrosDetalles"));
                oferta.setUrl_foto(rs.getString("Url_foto"));
                oferta.setNombreEmpresa(rs.getString("NombreEmpresa"));
                oferta.setCodigoEmpresa(rs.getString("CodigoEmpresa"));
            }
            this.desconectar();
            return oferta;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public ArrayList listarMisCupones(String id,String cliente) throws SQLException {
        try {
            ArrayList cupones = new ArrayList();
            String sql = "SELECT ofertas.TituloOferta,ofertas.PrecioRegular,"
                    + "ofertas.PrecioOferta,ofertas.DescripcionOferta,ofertas.FechaLimite,\n"
                    + "           ofertas.Url_foto,cupones.FechaCompra,cupones.CodigoCupo,cupones.IdEstadoCupon\n"
                    + "           FROM bddpoo.cupones cupones INNER JOIN bddpoo.ofertas ofertas "
                    + "ON (cupones.IdOferta = ofertas.IdOferta)"
                    + "WHERE cupones.IdEstadoCupon=? AND cupones.IdCliente=?";
            int idCliente = this.obtenerIdCliente(cliente);
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));
            st.setInt(2,idCliente);
            rs = st.executeQuery();
            while (rs.next()) {
                Cupon cupon = new Cupon();
                cupon.setTituloOferta(rs.getString("TituloOferta"));
                cupon.setDescripcionOferta(rs.getString("DescripcionOferta"));
                cupon.setFechaLimite(rs.getString("FechaLimite"));
                cupon.setUrl_foto(rs.getString("Url_foto"));
                cupon.setPrecioRegular(Double.parseDouble(rs.getString("PrecioRegular")));
                cupon.setPrecioOferta(Double.parseDouble(rs.getString("PrecioOferta")));
                cupon.setFechaCompra(rs.getString("FechaCompra"));
                cupon.setCodigoCupo(rs.getString("CodigoCupo"));
                cupones.add(cupon);
            }
            this.desconectar();
            return cupones;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<EstadoCupon> listarEstadosCupon() throws SQLException {
        try {
            List<EstadoCupon> estado = new ArrayList();
            this.conectar();
            String sql = "SELECT * FROM estadocupon";
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                EstadoCupon estadito = new EstadoCupon();
                estadito.setIdEstadoCupon(Integer.parseInt(rs.getString("IdEstadoCupon")));
                estadito.setEstado(rs.getString("Estado"));
                estado.add(estadito);
            }
            this.desconectar();
            return estado;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    
    public int obtenerIdCliente(String idUsuario) throws SQLException{
        try {
            String sql="SELECT IdCliente FROM clientes WHERE IdUsuario=?";
            int idCliente=0;
            this.conectar();
            st2=conexion.prepareStatement(sql);
            st2.setInt(1, Integer.parseInt(idUsuario));
            rs2=st2.executeQuery();
            rs2.next();
            idCliente=rs2.getInt("IdCliente");
            this.desconectar();
            return idCliente;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
    
    public int insertarCupon(String idOferta, String idCliente) throws SQLException {
        try {
            int filasAfectadas = 0;
            String sql = "INSERT INTO cupones VALUES (?,?,0000-00-00,?,?,1)";
            Oferta oferta = new Oferta();
            Date fechaActual = null;
            int cliente = this.obtenerIdCliente(idCliente);
            oferta = this.obtenerCupones(idOferta);
            this.conectar();
            //CURDATE() me da la fechaactual en formato año-mes-dia
            String sqlF = "SELECT CURRENT_DATE AS fechaActual";
            st = conexion.prepareStatement(sqlF);
            rs = st.executeQuery();
            while (rs.next()) {
                fechaActual = rs.getDate("fechaActual");
            }
            if (oferta.getCantidadLimite() > 0) {
                //Restar cantidad limite
                String sqlC = "UPDATE ofertas SET CantidadLimite=(SELECT CantidadLimite)-1 WHERE IdOferta=?";
                st = conexion.prepareStatement(sqlC);
                st.setInt(1, Integer.parseInt(idOferta));
                filasAfectadas = st.executeUpdate();
                //insercion de cupon
                st = conexion.prepareStatement(sql);
                st.setString(1, oferta.getCodigoEmpresa()+(int)(Math.random() * 8999999 + 1000000));
                st.setString(2, fechaActual.toString());
                st.setInt(3, cliente);
                st.setInt(4, Integer.parseInt(idOferta));
                filasAfectadas = st.executeUpdate();
                this.desconectar();
                return filasAfectadas;
            }
            this.desconectar();
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public Oferta obtenerOferta(int idOferta) {

        try {
            Oferta oferta = new Oferta();
            String sql = "SELECT * FROM ofertas WHERE idOferta=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idOferta);
            rs = st.executeQuery();
            while (rs.next()) {
                oferta.setIdOferta(Integer.parseInt(rs.getString("idOferta")));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
            }
            this.desconectar();
            return oferta;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
