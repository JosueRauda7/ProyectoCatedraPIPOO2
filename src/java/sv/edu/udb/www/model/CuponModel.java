package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Cupon;

public class CuponModel extends Conexion {

    public void analizarCupones() throws SQLException {
        try {
            Cupon cupon = new Cupon();
            ArrayList<Cupon> listaCupon = new ArrayList();
            //Capturare las fechas de los cupones y la fecha actual del sistema
            Date fechaLimite, fechaActual = null;
            this.conectar();
            String sql = "SELECT cupones.CodigoCupo,cupones.FechaCompra,cupones.FechaCanje,cupones.IdCliente,\n"
                    + "       cupones.IdOferta,cupones.IdEstadoCupon,ofertas.FechaLimite,ofertas.IdOferta FROM (bddpoo.cupones cupones\n"
                    + "      INNER JOIN bddpoo.ofertas ofertas\n"
                    + "         ON (cupones.IdOferta = ofertas.IdOferta))";
            String sqlV = "UPDATE cupones SET IdEstadoCupon=3 WHERE CodigoCupo=?";
            //CURDATE() me da la fechaactual en formato año-mes-dia
            String sqlF = "SELECT CURRENT_DATE AS fechaActual";
            st = conexion.prepareStatement(sqlF);
            rs = st.executeQuery();
            while (rs.next()) {
                fechaActual = rs.getDate("fechaActual");
            }
            //Obtencion de consulta sql    
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                cupon.setCodigoCupo(rs.getString("CodigoCupo"));
                cupon.setIdEstadoCupon(rs.getInt("IdEstadoCupon"));
                cupon.setFechaL(rs.getDate("FechaLimite"));
                if (cupon.getIdEstadoCupon() == 1 && cupon.getFechaL().before(fechaActual)) {
                    st2 = conexion.prepareStatement(sqlV);
                    st2.setString(1, cupon.getCodigoCupo());
                    st2.executeUpdate();
                }
                listaCupon.add(cupon);
            }
            this.desconectar();

        } catch (SQLException ex) {
            this.desconectar();
        }

    }

    public List<Cupon> obtenerCuponesDisponibles(int idusuario) throws SQLException {
        try {
            String sql = "";
            List<Cupon> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Cupon cupon = new Cupon();
                lista.add(cupon);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex){
            this.desconectar();
            return null;
        } finally {
            this.desconectar();
        }
    }
}
