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

    public int insertarOferta(Oferta oferta, String correo) throws SQLException{
            String codigoEmpresa="";
            int filasAfectadas=0;
        try {
            String sql="SELECT DISTINCT e.CodigoEmpresa FROM empresas e inner join ofertas o on "
                    + "e.CodigoEmpresa=o.CodigoEmpresa inner join usuarios u on "
                    + "e.IdUsuario=u.IdUsuario where u.correo=?";
            
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setString(1, correo);
            rs=st.executeQuery();
            
            rs.next();
                codigoEmpresa=rs.getString("codigoEmpresa");
            
            
            //Diferente insert segun hay limite de disponibilidad o no
            if(oferta.getCantidadLimite()==0){
                sql="INSERT into ofertas VALUES(NULL,?,?,?,?,?,?,?,NULL,?,?,?,?,?)";
                st=conexion.prepareStatement(sql);
                st.setString(1,oferta.getTituloOferta());
                st.setString(2,oferta.getPrecioRegular());
                st.setString(3,oferta.getPrecioOferta());
                st.setString(4,oferta.getFechaInicio());               
                st.setString(5,oferta.getFechaFin());
                st.setString(6,oferta.getFechaLimite());
                st.setString(7,oferta.getDescripcionOferta());
                st.setString(8,oferta.getOtrosDetalles());
                st.setInt(9,1);
                st.setString(10," ");
                st.setString(11,codigoEmpresa);
                st.setString(12,oferta.getUrl_foto());
            }else{
                sql="INSERT into ofertas VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?)";               
                st=conexion.prepareStatement(sql);
                st.setString(1,oferta.getTituloOferta());
                st.setDouble(2,Double.parseDouble(oferta.getPrecioRegular()));
                st.setDouble(3,Double.parseDouble(oferta.getPrecioOferta()));
                st.setString(4,oferta.getFechaInicio());               
                st.setString(5,oferta.getFechaFin());
                st.setString(6,oferta.getFechaLimite());
                st.setInt(7,oferta.getCantidadLimite());
                st.setString(8,oferta.getDescripcionOferta());
                st.setString(9,oferta.getOtrosDetalles());
                st.setInt(10,1);
                st.setString(11," ");
                st.setString(12,codigoEmpresa);
                st.setString(13,oferta.getUrl_foto());                
            }
            
            filasAfectadas=st.executeUpdate();

            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 4;
        }
    }

}
