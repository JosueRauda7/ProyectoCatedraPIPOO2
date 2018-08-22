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
import sv.edu.udb.www.beans.EstadoOferta;

/**
 *
 * @author ivanm
 */
public class EstadoOfertaModel extends Conexion{
    
    public List<EstadoOferta> listarEstadoOferta() throws SQLException{
        try {
            List<EstadoOferta> lista=new ArrayList<>();
            String sql="Select * from estadooferta";
                    
            this.conectar();
            st=conexion.prepareCall(sql);
            rs=st.executeQuery();
            while(rs.next()){
                EstadoOferta estado=new EstadoOferta();
                estado.setIdEstadoOferta(rs.getInt("IdEstadoOferta"));
                estado.setEstado(rs.getString("Estado"));
                lista.add(estado);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EstadoOfertaModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
}
