package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Cliente;
import sv.edu.udb.www.beans.Cupon;
import sv.edu.udb.www.beans.Empleado;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.beans.Usuario;
import static sv.edu.udb.www.model.Conexion.conexion;

public class EmpleadosModel extends Conexion {

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

    }
    
    public List<Empleado> listarEmpleados(String correo) throws SQLException{
        try {
            List<Empleado> lista=new ArrayList<>();
            
            String sql="Select e.CodigoEmpresa from empresas e inner join usuarios u on e.IdUsuario=u.IdUsuario where u.correo=?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setString(1, correo);
            rs=st.executeQuery();
            rs.next();
            
            String CodigoEmpresa=rs.getString("CodigoEmpresa");
            
            sql="Select * from empleado e inner join empresas em on e.CodigoEmpresa=em.CodigoEmpresa"
                    + " inner join usuarios u on e.IdUsuario=u.IdUsuario where em.CodigoEmpresa=?";
            st=conexion.prepareStatement(sql);
            st.setString(1,CodigoEmpresa);
            
            rs=st.executeQuery();
            while(rs.next()){
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("IdEmpleado"));
                empleado.setNombreEmpleado(rs.getString("NombreEmpleado"));
                empleado.setApellidoEmpleado(rs.getString("ApellidoEmpleado"));
                empleado.setUsuario(new Usuario(rs.getString("correo")));
                lista.add(empleado);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    
    public int insertarEmpleado(Empleado empleado,String correo, int idUsuario) throws SQLException{        
        try {
            int filasAfectadas=0;
            String sql="select * from empresas e inner join usuarios u on e.IdUsuario=u.IdUsuario where u.correo=?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setString(1,correo);
            rs=st.executeQuery();
            rs.next();
            String codigoEmpresa= rs.getString("CodigoEmpresa");
            
            sql ="Insert into empleado values(NULL,?,?,?,?)";
            st=conexion.prepareStatement(sql);
            st.setString(1,empleado.getNombreEmpleado());
            st.setString(2, empleado.getApellidoEmpleado());
            st.setInt(3, idUsuario);
            st.setString(4,codigoEmpresa);
            filasAfectadas=st.executeUpdate();
            this.desconectar();            
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
    
     
}
