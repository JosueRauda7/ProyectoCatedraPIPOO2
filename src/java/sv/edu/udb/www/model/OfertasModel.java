/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.EstadoOferta;
import sv.edu.udb.www.beans.Oferta;
import static sv.edu.udb.www.model.Conexion.conexion;

/**
 *
 * @author ivanm
 */
public class OfertasModel extends Conexion {

    public List<Oferta> listarOferta(String correo, int tipo) throws SQLException {
        try {
            List<Oferta> lista = new ArrayList<>();

            String sql = "";

            if (tipo != 0) {
                sql = "Select * from ofertas o inner join estadooferta eo on o.idEstado=eo.idEstadoOferta "
                        + "inner join empresas e on o.CodigoEmpresa=e.CodigoEmpresa inner join usuarios u ON e.IdUsuario = u.IdUsuario "
                        + "WHERE u.correo = ? AND o.IdEstado=?";
                this.conectar();
                st = conexion.prepareStatement(sql);
                st.setString(1, correo);
                st.setInt(2, tipo);
            } else {
                sql = "Select * from ofertas o inner join estadooferta eo on o.idEstado=eo.idEstadoOferta "
                        + "inner join empresas e on o.CodigoEmpresa=e.CodigoEmpresa inner join usuarios u ON e.IdUsuario = u.IdUsuario "
                        + "WHERE u.correo = ?";
                this.conectar();
                st = conexion.prepareStatement(sql);
                st.setString(1, correo);
            }

            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(rs.getInt("IdOferta"));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getString("FechaInicio"));
                oferta.setFechaFin(rs.getString("FechaFin"));
                oferta.setFechaLimite(rs.getString("FechaLimite"));
                oferta.setCantidadLimite(rs.getInt("CantidadLimite"));
                oferta.setDescripcionOferta(rs.getString("DescripcionOferta"));
                oferta.setOtrosDetalles(rs.getString("OtrosDetalles"));
                oferta.setEstadoOferta(new EstadoOferta(rs.getString("Estado")));
                oferta.setJustificacion(rs.getString("Justificacion"));
                oferta.setEmpresa(new Empresa(rs.getString("NombreEmpresa")));
                oferta.setUrl_foto(rs.getString("Url_foto"));
                lista.add(oferta);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public Oferta detalleOferta(int codigo) throws SQLException {
        try {
            int cantidadCupones = 0;
            String sql = "Select Count(*) as cantidad FROM cupones where IdOferta=? ";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, codigo);
            rs = st.executeQuery();
            rs.next();
            cantidadCupones = rs.getInt("cantidad");

            sql = "Select * from ofertas where IdOferta=?";
            st = conexion.prepareStatement(sql);
            st.setInt(1, codigo);
            rs = st.executeQuery();
            if (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(rs.getInt("IdOferta"));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getString("FechaInicio"));
                oferta.setFechaFin(rs.getString("FechaFin"));
                oferta.setFechaLimite(rs.getString("FechaLimite"));
                oferta.setCantidadVendida(cantidadCupones);
                oferta.setCantidadLimite(Integer.parseInt(rs.getString("CantidadLimite")));
                oferta.setDescripcionOferta(rs.getString("DescripcionOferta"));
                oferta.setOtrosDetalles(rs.getString("OtrosDetalles"));
                oferta.setJustificacion(rs.getString("Justificacion"));
                oferta.setUrl_foto(rs.getString("Url_foto"));
                this.desconectar();
                return oferta;
            }
            this.desconectar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public void actualizarEstados() throws SQLException {
        Oferta oferta = new Oferta();
        int estado = 0;
        //Capturare todos los idofertas existentes aqui
        ArrayList<Integer> idoferta = new ArrayList();
        //Capturare las fechas de las ofertas y la fecha actual del sistema
        Date fechaInicio, fechaFin, fechaActual;

        try {

            //Busco todas las ofertas
            String sql = "Select * from ofertas";
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();

            //Guardando el idOferta de cada oferta
            while (rs.next()) {
                idoferta.add(rs.getInt("IdOferta"));
            }

            //Si el array tiene datos (tamaño distinto a 0), el proceso puede continuar
            if (idoferta.size()!= 0) {

                //CURDATE() me da la fechaactual en formato año-mes-dia
                sql = "Select CURDATE() as fechaActual";
                rs = st.executeQuery(sql);
                rs.next();
                fechaActual = rs.getDate("fechaActual");

                //Recorriendo todos los idOfertas disponibles
                for (int i = 0; i < idoferta.size(); i++) {

                    //Usando el idOferta actual en (i) capturo las fechas de esta oferta 
                    sql = "Select * from ofertas where IdOferta=?";
                    st = conexion.prepareStatement(sql);
                    st.setInt(1, idoferta.get(i));
                    rs = st.executeQuery();
                    rs.next();

                    estado = rs.getInt("IdEstado");
                    fechaInicio = rs.getDate("FechaInicio");
                    fechaFin = rs.getDate("FechaFin");

                    //fecha.before() y fecha.after() solo compara si la fecha se encuentra "antes" o "despues" respectivamente de 
                    //la fechaActual, NO calcula la diferencia entre fechas!
                    //Si la oferta tiene estado En espera y se cumple la condicion de fecha
                    if (estado == 1) {
                        if (fechaFin.before(fechaActual)) {
                            sql = "Update ofertas SET IdEstado=4 where IdOferta=?";
                            st = conexion.prepareStatement(sql);
                            st.setInt(1, idoferta.get(i));
                            st.executeUpdate();
                        }
                    }

                    //Si la oferta tiene estado Aprovado y la fechaActual se encuentra entre las fechaInicio y fechaFin 
                    if (estado == 2) {
                        if (fechaInicio.before(fechaActual) && fechaFin.after(fechaActual)) {
                            sql = "Update ofertas SET IdEstado=3 where IdOferta=?";
                            st = conexion.prepareStatement(sql);
                            st.setInt(1, idoferta.get(i));
                            st.executeUpdate();
                        }
                    }
                    //Si la oferta tiene estado Activo y la fechaFin es inferior a la fechaActual
                    if (estado == 3) {
                        if (fechaFin.before(fechaActual)) {
                            sql = "Update ofertas SET IdEstado=4 where IdOferta=?";
                            st = conexion.prepareStatement(sql);
                            st.setInt(1, idoferta.get(i));
                            st.executeUpdate();
                        }
                    }

                }
            }
            this.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
        }

    }

    public List<Oferta> ListarOfertasEspera(String codigo) throws SQLException {
        try {
            String sql = "SELECT o.*, (COUNT(c.CodigoCupo)* o.PrecioOferta) AS valor,ROUND((e.Comision*(COUNT(c.CodigoCupo)* o.PrecioOferta)),2) as valor2 FROM ofertas o LEFT JOIN cupones c on o.IdOferta = c.IdOferta INNER JOIN empresas e on o.CodigoEmpresa = e.CodigoEmpresa WHERE o.IdEstado=1 and o.CodigoEmpresa=? GROUP by o.IdOferta";
            List<Oferta> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(rs.getInt("o.IdOferta"));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getString("FechaInicio"));
                oferta.setFechaFin(rs.getString("FechaFin"));
                oferta.setFechaLimite(rs.getString("FechaLimite"));
                oferta.setCantidadLimite(rs.getInt("CantidadLimite"));
                oferta.setDescripcionOferta(rs.getString("DescripcionOferta"));
                oferta.setOtrosDetalles(rs.getString("OtrosDetalles"));
                oferta.setJustificacion(rs.getString("Justificacion"));
                oferta.setUrl_foto(rs.getString("Url_foto"));
                oferta.setIngresos(rs.getString("valor"));
                oferta.setComision(rs.getString("valor2"));
                oferta.setCodigoEmpresa(rs.getString("CodigoEmpresa"));
                lista.add(oferta);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        } finally {
            this.desconectar();
        }
    }

    public List<Oferta> ListarOfertasFutura(String codigo) throws SQLException {
        try {
            String sql = "SELECT o.*, (COUNT(c.CodigoCupo)* o.PrecioOferta) AS valor,ROUND((e.Comision*(COUNT(c.CodigoCupo)* o.PrecioOferta)),2) as valor2 FROM ofertas o LEFT JOIN cupones c on o.IdOferta = c.IdOferta INNER JOIN empresas e on o.CodigoEmpresa = e.CodigoEmpresa WHERE o.IdEstado=2 and o.CodigoEmpresa=? GROUP by o.IdOferta";
            List<Oferta> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(rs.getInt("o.IdOferta"));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getString("FechaInicio"));
                oferta.setFechaFin(rs.getString("FechaFin"));
                oferta.setFechaLimite(rs.getString("FechaLimite"));
                oferta.setCantidadLimite(rs.getInt("CantidadLimite"));
                oferta.setDescripcionOferta(rs.getString("DescripcionOferta"));
                oferta.setOtrosDetalles(rs.getString("OtrosDetalles"));
                oferta.setJustificacion(rs.getString("Justificacion"));
                oferta.setUrl_foto(rs.getString("Url_foto"));
                oferta.setIngresos(rs.getString("valor"));
                oferta.setComision(rs.getString("valor2"));
                lista.add(oferta);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        } finally {
            this.desconectar();
        }
    }

    public List<Oferta> ListarOfertasActiva(String codigo) throws SQLException {
        try {
            String sql = "SELECT o.*, (COUNT(c.CodigoCupo)* o.PrecioOferta) AS valor,ROUND((e.Comision*(COUNT(c.CodigoCupo)* o.PrecioOferta)),2) as valor2 FROM ofertas o LEFT JOIN cupones c on o.IdOferta = c.IdOferta INNER JOIN empresas e on o.CodigoEmpresa = e.CodigoEmpresa WHERE o.IdEstado=3 and o.CodigoEmpresa=? GROUP by o.IdOferta";
            List<Oferta> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(rs.getInt("o.IdOferta"));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getString("FechaInicio"));
                oferta.setFechaFin(rs.getString("FechaFin"));
                oferta.setFechaLimite(rs.getString("FechaLimite"));
                oferta.setCantidadLimite(rs.getInt("CantidadLimite"));
                oferta.setDescripcionOferta(rs.getString("DescripcionOferta"));
                oferta.setOtrosDetalles(rs.getString("OtrosDetalles"));
                oferta.setJustificacion(rs.getString("Justificacion"));
                oferta.setUrl_foto(rs.getString("Url_foto"));
                oferta.setIngresos(rs.getString("valor"));
                oferta.setComision(rs.getString("valor2"));
                lista.add(oferta);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        } finally {
            this.desconectar();
        }
    }
    public List<Oferta> ListarOfertasFinalizada(String codigo) throws SQLException {
        try {
            String sql = "SELECT o.*, (COUNT(c.CodigoCupo)* o.PrecioOferta) AS valor,ROUND((e.Comision*(COUNT(c.CodigoCupo)* o.PrecioOferta)),2) as valor2 FROM ofertas o LEFT JOIN cupones c on o.IdOferta = c.IdOferta INNER JOIN empresas e on o.CodigoEmpresa = e.CodigoEmpresa WHERE o.IdEstado=4 and o.CodigoEmpresa=? GROUP by o.IdOferta";
            List<Oferta> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(rs.getInt("o.IdOferta"));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getString("FechaInicio"));
                oferta.setFechaFin(rs.getString("FechaFin"));
                oferta.setFechaLimite(rs.getString("FechaLimite"));
                oferta.setCantidadLimite(rs.getInt("CantidadLimite"));
                oferta.setDescripcionOferta(rs.getString("DescripcionOferta"));
                oferta.setOtrosDetalles(rs.getString("OtrosDetalles"));
                oferta.setJustificacion(rs.getString("Justificacion"));
                oferta.setUrl_foto(rs.getString("Url_foto"));
                oferta.setIngresos(rs.getString("valor"));
                oferta.setComision(rs.getString("valor2"));
                lista.add(oferta);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        } finally {
            this.desconectar();
        }
    }
    public List<Oferta> ListarOfertasRechazada(String codigo) throws SQLException {
        try {
            String sql = "SELECT o.*, (COUNT(c.CodigoCupo)* o.PrecioOferta) AS valor,ROUND((e.Comision*(COUNT(c.CodigoCupo)* o.PrecioOferta)),2) as valor2 FROM ofertas o LEFT JOIN cupones c on o.IdOferta = c.IdOferta INNER JOIN empresas e on o.CodigoEmpresa = e.CodigoEmpresa WHERE o.IdEstado=5 and o.CodigoEmpresa=? GROUP by o.IdOferta";
            List<Oferta> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(rs.getInt("o.IdOferta"));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getString("FechaInicio"));
                oferta.setFechaFin(rs.getString("FechaFin"));
                oferta.setFechaLimite(rs.getString("FechaLimite"));
                oferta.setCantidadLimite(rs.getInt("CantidadLimite"));
                oferta.setDescripcionOferta(rs.getString("DescripcionOferta"));
                oferta.setOtrosDetalles(rs.getString("OtrosDetalles"));
                oferta.setJustificacion(rs.getString("Justificacion"));
                oferta.setUrl_foto(rs.getString("Url_foto"));
                oferta.setIngresos(rs.getString("valor"));
                oferta.setComision(rs.getString("valor2"));
                lista.add(oferta);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        } finally {
            this.desconectar();
        }
    }
    public List<Oferta> ListarOfertasDescartada(String codigo) throws SQLException {
        try {
            String sql = "SELECT o.*, (COUNT(c.CodigoCupo)* o.PrecioOferta) AS valor,ROUND((e.Comision*(COUNT(c.CodigoCupo)* o.PrecioOferta)),2) as valor2 FROM ofertas o LEFT JOIN cupones c on o.IdOferta = c.IdOferta INNER JOIN empresas e on o.CodigoEmpresa = e.CodigoEmpresa WHERE o.IdEstado=6 and o.CodigoEmpresa=? GROUP by o.IdOferta";
            List<Oferta> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(rs.getInt("o.IdOferta"));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getString("FechaInicio"));
                oferta.setFechaFin(rs.getString("FechaFin"));
                oferta.setFechaLimite(rs.getString("FechaLimite"));
                oferta.setCantidadLimite(rs.getInt("CantidadLimite"));
                oferta.setDescripcionOferta(rs.getString("DescripcionOferta"));
                oferta.setOtrosDetalles(rs.getString("OtrosDetalles"));
                oferta.setJustificacion(rs.getString("Justificacion"));
                oferta.setUrl_foto(rs.getString("Url_foto"));
                oferta.setIngresos(rs.getString("valor"));
                oferta.setComision(rs.getString("valor2"));
                lista.add(oferta);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        } finally {
            this.desconectar();
        }
    }
    public int insertarOferta(Oferta oferta, String correo) throws SQLException {
        String codigoEmpresa = "";
        int filasAfectadas = 0;
        try {
            String sql = "SELECT DISTINCT e.CodigoEmpresa FROM empresas e inner join ofertas o on "
                    + "e.CodigoEmpresa=o.CodigoEmpresa inner join usuarios u on "
                    + "e.IdUsuario=u.IdUsuario where u.correo=?";

            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, correo);
            rs = st.executeQuery();

            rs.next();
            codigoEmpresa = rs.getString("codigoEmpresa");

            //Diferente insert segun hay limite de disponibilidad o no
            if (oferta.getCantidadLimite() == 0) {
                sql = "INSERT into ofertas VALUES(NULL,?,?,?,?,?,?,?,NULL,?,?,?,?,?)";
                st = conexion.prepareStatement(sql);
                st.setString(1, oferta.getTituloOferta());
                st.setString(2, oferta.getPrecioRegular());
                st.setString(3, oferta.getPrecioOferta());
                st.setString(4, oferta.getFechaInicio());
                st.setString(5, oferta.getFechaFin());
                st.setString(6, oferta.getFechaLimite());
                st.setString(7, oferta.getDescripcionOferta());
                st.setString(8, oferta.getOtrosDetalles());
                st.setInt(9, 1);
                st.setString(10, " ");
                st.setString(11, codigoEmpresa);
                st.setString(12, oferta.getUrl_foto());
            } else {
                sql = "INSERT into ofertas VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                st = conexion.prepareStatement(sql);
                st.setString(1, oferta.getTituloOferta());
                st.setDouble(2, Double.parseDouble(oferta.getPrecioRegular()));
                st.setDouble(3, Double.parseDouble(oferta.getPrecioOferta()));
                st.setString(4, oferta.getFechaInicio());
                st.setString(5, oferta.getFechaFin());
                st.setString(6, oferta.getFechaLimite());
                st.setInt(7, oferta.getCantidadLimite());
                st.setString(8, oferta.getDescripcionOferta());
                st.setString(9, oferta.getOtrosDetalles());
                st.setInt(10, 1);
                st.setString(11, " ");
                st.setString(12, codigoEmpresa);
                st.setString(13, oferta.getUrl_foto());
            }

            filasAfectadas = st.executeUpdate();

            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 4;

        }
    }

    public int modificarOferta(Oferta oferta, String correo, int IdOferta) throws SQLException {
        int filasAfectadas = 0;
        String codigoEmpresa = "";
        try {
            String sql = "SELECT DISTINCT e.CodigoEmpresa FROM empresas e inner join ofertas o on "
                    + "e.CodigoEmpresa=o.CodigoEmpresa inner join usuarios u on "
                    + "e.IdUsuario=u.IdUsuario where u.correo=?";

            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, correo);
            rs = st.executeQuery();

            rs.next();
            codigoEmpresa = rs.getString("codigoEmpresa");

            //Diferente insert segun hay limite de disponibilidad o no
            if (oferta.getCantidadLimite() == 0) {

                sql = "UPDATE ofertas SET TituloOferta=?, PrecioRegular=?, PrecioOferta=?, FechaInicio=?, FechaFin=?,"
                        + " FechaLimite=?, CantidadLimite=NULL, DescripcionOferta=?, OtrosDetalles=?, IdEstado=?, Justificacion=?,"
                        + " CodigoEmpresa=?, Url_foto=? WHERE IdOferta=?";
                st = conexion.prepareStatement(sql);
                st.setString(1, oferta.getTituloOferta());
                st.setString(2, oferta.getPrecioRegular());
                st.setString(3, oferta.getPrecioOferta());
                st.setString(4, oferta.getFechaInicio());
                st.setString(5, oferta.getFechaFin());
                st.setString(6, oferta.getFechaLimite());
                st.setString(7, oferta.getDescripcionOferta());
                st.setString(8, oferta.getOtrosDetalles());
                st.setInt(9, 1);
                st.setString(10, oferta.getJustificacion());
                st.setString(11, codigoEmpresa);
                st.setString(12, oferta.getUrl_foto());
                st.setInt(13, IdOferta);

            } else {
                sql = "UPDATE ofertas SET TituloOferta=?, PrecioRegular=?, PrecioOferta=?, FechaInicio=?, FechaFin=?,"
                        + " FechaLimite=?, CantidadLimite=?, DescripcionOferta=?, OtrosDetalles=?, IdEstado=?, Justificacion=?,"
                        + " CodigoEmpresa=?, Url_foto=? WHERE IdOferta=?";
                st = conexion.prepareStatement(sql);
                st.setString(1, oferta.getTituloOferta());
                st.setDouble(2, Double.parseDouble(oferta.getPrecioRegular()));
                st.setDouble(3, Double.parseDouble(oferta.getPrecioOferta()));
                st.setString(4, oferta.getFechaInicio());
                st.setString(5, oferta.getFechaFin());
                st.setString(6, oferta.getFechaLimite());
                st.setInt(7, oferta.getCantidadLimite());
                st.setString(8, oferta.getDescripcionOferta());
                st.setString(9, oferta.getOtrosDetalles());
                st.setInt(10, 1);
                st.setString(11, oferta.getJustificacion());
                st.setString(12, codigoEmpresa);
                st.setString(13, oferta.getUrl_foto());
                st.setInt(14, IdOferta);
            }
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
    
    public int aprobarOferta(int idoferta) throws SQLException{
        try {
            int filasAfectadas = 0;
            String sql ="UPDATE ofertas SET IdEstado = 2 WHERE IdOferta = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idoferta);
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }finally{
        this.desconectar();
        }
    }

}
