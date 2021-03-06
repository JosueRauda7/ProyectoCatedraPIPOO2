package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Cliente;
import sv.edu.udb.www.beans.Cupon;
import sv.edu.udb.www.beans.EstadoCupon;
import sv.edu.udb.www.beans.Empleado;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.beans.Usuario;
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
                cupon.setFechaCompra(rs.getString("FechaCompra"));
                cupon.setFechaCanje(rs.getString("FechaCanje"));
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
    
    public int modificarEmpelado(Empleado empleado,String correo, int idEmpleado) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql="select * from empresas e inner join usuarios u on e.IdUsuario=u.IdUsuario where u.correo=?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setString(1,correo);
            rs=st.executeQuery();
            rs.next();
            String codigoEmpresa= rs.getString("CodigoEmpresa");
            
            sql ="Update empleado SET NombreEmpleado=?, ApellidoEmpleado=? where IdEmpleado=?";
            st=conexion.prepareStatement(sql);
            st.setString(1,empleado.getNombreEmpleado());
            st.setString(2, empleado.getApellidoEmpleado());
            st.setInt(3, idEmpleado);            
            filasAfectadas=st.executeUpdate();
            this.desconectar();            
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public int eliminarEmpleado(int idEmpleado) throws SQLException{
            int IdUsuario=0;
            int filasAfectadas=0;
        try {
            String sql = "SELECT * from empleado where IdEmpleado=?";
            
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setInt(1,idEmpleado);
            rs=st.executeQuery();
            
            if(rs.next()){
                IdUsuario=rs.getInt("IdUsuario");
            }else{
                this.desconectar();
                return 0;
            }
            
            sql="Delete FROM empleado WHERE IdEmpleado=?";
            st=conexion.prepareStatement(sql);
            st.setInt(1, idEmpleado);
            filasAfectadas=st.executeUpdate();
            if(filasAfectadas==0){
                this.desconectar();
                return 0;
            }
            
            sql="Delete From usuarios where IdUsuario=?";
            st=conexion.prepareStatement(sql);
            st.setInt(1, IdUsuario);
            filasAfectadas=st.executeUpdate();
            
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
        
    }
    
    public Empleado obtenerEmpleado(String correo, int idEmpleado) throws SQLException{
        try {
            
            Empleado empleado = new Empleado();
            String sql="Select e.CodigoEmpresa from empresas e inner join usuarios u on e.IdUsuario=u.IdUsuario where u.correo=?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setString(1, correo);
            rs=st.executeQuery();
            rs.next();
            
            String CodigoEmpresa=rs.getString("CodigoEmpresa");
            
            sql="Select * from empleado e inner join empresas em on e.CodigoEmpresa=em.CodigoEmpresa"
                    + " inner join usuarios u on e.IdUsuario=u.IdUsuario where em.CodigoEmpresa=? and e.IdEmpleado=?";
            st=conexion.prepareStatement(sql);
            st.setString(1,CodigoEmpresa);
            st.setInt(2,idEmpleado);
            
            rs=st.executeQuery();
            while(rs.next()){
                
                empleado.setIdEmpleado(rs.getInt("IdEmpleado"));
                empleado.setNombreEmpleado(rs.getString("NombreEmpleado"));
                empleado.setApellidoEmpleado(rs.getString("ApellidoEmpleado"));
                empleado.setUsuario(new Usuario(rs.getString("correo")));
                
            }
            this.desconectar();
            return empleado;
        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
}
