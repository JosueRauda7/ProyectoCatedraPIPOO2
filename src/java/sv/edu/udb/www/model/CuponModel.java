package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Cupon;

public class CuponModel extends Conexion {
    public void analizarCupones() throws SQLException{
        try {
            Cupon cupon = new Cupon();
            List<Cupon> listaCupon = new ArrayList();
            //Capturare las fechas de los cupones y la fecha actual del sistema
            Date fechaLimite, fechaActual;
            this.conectar();
            String sql="SELECT cupones.CodigoCupo,cupones.FechaCompra,,cupones.FechaCanje,cupones.IdCliente,\n" +
                    "       cupones.IdOferta,cupones.IdEstadoCupon,ofertas.FechaLimite,ofertas.IdOferta,estadocupon.IdEstadoCupon,\n" +
                    "       estadocupon.Estado FROM (bddpoo.cupones cupones\n" +
                    "      INNER JOIN bddpoo.ofertas ofertas\n" +
                    "         ON (cupones.IdOferta = ofertas.IdOferta))\n" +
                    "     INNER JOIN bddpoo.estadocupon estadocupon\n" +
                    "        ON (cupones.IdEstadoCupon = estadocupon.IdEstadoCupon)";
            String sqlV = "UPDATE ofertas SET IdEstadoCupon=3 WHERE CodigoCupo=?";
            //CURDATE() me da la fechaactual en formato año-mes-dia
                String sqlF = "Select CURDATE() as fechaActual";
                rs = st.executeQuery(sqlF);
                rs.next();
                fechaActual = rs.getDate("fechaActual");
                
            //Obtencion de consulta sql    
            st=conexion.prepareStatement(sql);
            rs=st.executeQuery();
            while(rs.next()){
                cupon.setCodigoCupo(rs.getString("CodigoCupo"));
                cupon.setIdEstadoCupon(rs.getInt("IdEstadoCupon"));
                cupon.setFechaL(rs.getDate("FechaLimite"));
                listaCupon.add(cupon);
            }
            for(Cupon cuponsito:listaCupon){
                if(cuponsito.getIdEstadoCupon()==1&&cuponsito.getFechaL().before(fechaActual)){
                    st=conexion.prepareStatement(sqlV);
                    st.setString(1, cuponsito.getCodigoCupo());
                    int estado = st.executeUpdate();
                }
            }
            this.desconectar();
            
        } catch (SQLException ex) {
            this.desconectar();
        }
        
    }
}
