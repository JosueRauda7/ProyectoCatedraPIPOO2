
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.Rubro;
import sv.edu.udb.www.model.Conexion;

/**
 *
 * @author Emerson Torres
 */
public class EmpresasModel extends Conexion{
      
    public List<Empresa> listarEmpresas() throws SQLException{
        try {
            List<Empresa> lista = new ArrayList<>();
            String sql = "SELECT * FROM empresas e INNER JOIN rubros r on e.IdRubro = r.IdRubro";
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Empresa empresa = new Empresa();
                empresa.setCodigoEmpresa(rs.getString("CodigoEmpresa"));
                empresa.setNombreEmpresa(rs.getString("NombreEmpresa"));
                empresa.setNombreContacto(rs.getString("NombreContacto"));
                empresa.setDireccion(rs.getString("Direccion"));
                empresa.setTelefono(rs.getString("Telefono"));
                empresa.setRubro(new Rubro(rs.getString("Rubro")));
                empresa.setComision(rs.getString("Comision"));
                lista.add(empresa);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }finally{
         this.desconectar();
        }
    }
    public int agregarEmpresa(Empresa empresa) throws SQLException{
        try {
            int filasAfectadas=0;
            int numero=0;
            String cadena = "EMP";
            String resultado ="";
            this.conectar();
            st = conexion.prepareStatement("SELECT SUBSTRING(MAX(CodigoEmpresa) , 4,6) AS Numero FROM empresas WHERE CodigoEmpresa LIKE 'EMP%'");
            rs = st.executeQuery();
            while(rs.next()){
                numero = rs.getInt("Numero") + 1;
                
                if (numero <= 9) {
                    resultado = cadena + "00" + numero;
                }
                else if (numero >=10 && numero <=99 ) {
                    resultado = cadena + "0" + numero;
                }
                else if (numero>=100 && numero <=999) {
                    resultado = cadena + numero;
                }
            }
            
            String sql = "INSERT INTO empresas VALUES(?,?,?,?,?,?,?,?)";
            
            st = conexion.prepareStatement(sql);
            st.setString(1, resultado);
            st.setString(2, empresa.getNombreEmpresa());
            st.setString(3, empresa.getNombreContacto());
            st.setString(4, empresa.getDireccion());
            st.setString(5, empresa.getTelefono());
            st.setInt(6, empresa.getIdRubro());
            st.setString(7, String.valueOf(empresa.getComision()));
            st.setInt(8, empresa.getIdUsuario());
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }finally{
        this.desconectar();
        }
    }
    
}
