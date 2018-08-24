package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Cliente;
import sv.edu.udb.www.beans.Cupon;
import sv.edu.udb.www.beans.EstadoCupon;
import sv.edu.udb.www.beans.Oferta;
import static sv.edu.udb.www.model.Conexion.conexion;

public class EmpleadosModel extends Conexion {

    /*public Cupon obtenerCupon(String codigo) throws SQLException {
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
                //cupon.setCliente((Cliente) rs.getObject("IdCliente"));
                //cupon.setOferta((Oferta) rs.getObject("IdOferta"));

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
    }*/
    
    public List<Cupon> obtenerCupon(String codigo) throws SQLException{
        
        try {
            List<Cupon> lista = new ArrayList<>();
            String sql = "SELECT * FROM cupones AS cup INNER JOIN clientes AS cli ON cup.IdCliente=cli.IdCliente INNER JOIN ofertas AS ofer ON cup.IdOferta=ofer.IdOferta INNER JOIN estadocupon AS estaCu ON cup.IdEstadoCupon=estaCu.IdEstadoCupon INNER JOIN empresas AS emp ON ofer.CodigoEmpresa=emp.CodigoEmpresa WHERE CodigoCupo = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();
            
            while(rs.next()){
                Cupon cupon = new Cupon();
                cupon.setCodigoCupo(rs.getString("CodigoCupo"));
                cupon.setFechaCompra(rs.getDate("FechaCompra"));
                cupon.setFechaCanje(rs.getDate("FechaCanje"));
                cupon.setCliente(new Cliente (rs.getString("NombreClientes"),rs.getString("ApellidosClientes"), rs.getString("DUI")));
                cupon.setOferta(new Oferta (rs.getString("TituloOferta")));   
                cupon.setEstadoCupon(new EstadoCupon (rs.getString("Estado")));
                lista.add(cupon);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    
    public int canjearCupon(String codigo) throws SQLException{        
        try {
            int filasAfectadas = 0;
            String sql = "UPDATE cupones SET IdEstadoCupon=2 WHERE CodigoCupo = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
}
