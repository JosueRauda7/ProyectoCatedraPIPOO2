package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Cliente;
import sv.edu.udb.www.beans.Cupon;
import sv.edu.udb.www.beans.Empleado;
import sv.edu.udb.www.beans.Oferta;
import static sv.edu.udb.www.model.Conexion.conexion;


public class EmpleadosModel extends Conexion{
    
     public Cupon obtenerCupon(String codigo) throws SQLException {
        try {
            
            Cliente cliente = new Cliente();
            Oferta oferta = new Oferta();
            
            String sql = "SELECT * FROM cupones AS cup INNER JOIN clientes AS cli ON cup.IdCliente=cli.IdCliente INNER JOIN ofertas AS ofer ON cup.IdOferta=ofer.IdOferta WHERE CodigoCupo = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();

            if (rs.next()) {
                Cupon cupon = new Cupon();
                cupon.setCodigoCupo(rs.getString("CodigoCupo"));
                cupon.setFechaCompra(rs.getDate("FechaCompra"));
                cupon.setFechaCanje(rs.getDate("FechaCanje"));
                cupon.setCliente(cliente);
                cupon.setOferta(oferta);
                
                this.desconectar();
                return cupon;
            }
            this.desconectar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }

    }
     
    
     }
}
