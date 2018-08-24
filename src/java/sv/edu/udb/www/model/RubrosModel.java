
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Rubro;
import sv.edu.udb.www.model.Conexion;

/**
 *
 * @author Emerson Torres
 */
public class RubrosModel extends Conexion{
    public List<Rubro> obtenerRubro() throws SQLException{
        try {
            List<Rubro> lista = new ArrayList<>();
            String sql = "SELECT * FROM rubros";
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Rubro rubro = new Rubro();
                rubro.setIdRubro(rs.getInt("IdRubro"));
                rubro.setRubro(rs.getString("Rubro"));
                lista.add(rubro);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }finally{
        this.desconectar();
        }
    }
    public int insertarRubro(Rubro rubro) throws SQLException{
        try {
            int filasAfectadas;
            String sql = "INSERT INTO rubros(Rubro) VALUES(?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, rubro.getRubro());
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }finally{
          this.desconectar();
        }
    }
    public int modificarRubro(Rubro rubro) throws SQLException{
        try {
            int filasAfectadas;
            String sql = "UPDATE rubros SET Rubro = ? WHERE IdRubro = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, rubro.getRubro());
            st.setInt(2, rubro.getIdRubro());
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }finally{
          this.desconectar();
        }
    }
    public int validarRubro(Rubro rubro) throws SQLException{
        try {
            int repetido = 0;
            String sql = "SELECT COUNT(Rubro) numero FROM rubros WHERE Rubro = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, rubro.getRubro());
            rs = st.executeQuery();
            while(rs.next()){
                repetido = rs.getInt("numero");
            }
            this.desconectar();
            return repetido;
        } catch (SQLException ex) {
            Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }finally{
            this.desconectar();
        }
    }
    public int eliminarRubro(int idRubro) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql = "DELETE FROM rubros WHERE IdRubro = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idRubro);
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }finally{
         this.desconectar();
        }
    }
}
