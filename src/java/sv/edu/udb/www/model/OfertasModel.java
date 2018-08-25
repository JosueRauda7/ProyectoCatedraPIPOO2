/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
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
public class OfertasModel extends Conexion{
    public List<Oferta> listarOferta(String correo) throws SQLException{
        try {
            List<Oferta> lista=new ArrayList<>();
            String sql="Select * from ofertas o inner join estadooferta eo on o.idEstado=eo.idEstadoOferta "
                    + "inner join empresas e on o.CodigoEmpresa=e.CodigoEmpresa inner join usuarios u ON e.IdUsuario = u.IdUsuario "
                    + "WHERE u.correo = ?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setString(1, correo);
            rs=st.executeQuery();
            while(rs.next()){
                Oferta oferta=new Oferta();
                oferta.setIdOferta(rs.getInt("IdOferta"));
                oferta.setTituloOferta(rs.getString("TituloOferta"));
                oferta.setPrecioRegular(rs.getString("PrecioRegular"));
                oferta.setPrecioOferta(rs.getString("PrecioOferta"));
                oferta.setFechaInicio(rs.getDate("FechaInicio"));
                oferta.setFechaFin(rs.getDate("FechaFin"));
                oferta.setFechaLimite(rs.getDate("FechaLimite"));
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
  
}
