/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Cliente;
import sv.edu.udb.www.beans.Usuario;
import sv.edu.udb.www.model.ClientesModel;
import static sv.edu.udb.www.model.Conexion.conexion;

/**
 *
 * @author ivanm
 */
public class UsuariosModel extends Conexion{
    public int insertarUsuario(Usuario usuario,Cliente cliente, String idconfirmacion) throws SQLException{
        int idUsuario=0;
        ClientesModel c = new ClientesModel();
        try {
            int filasAfectadas=0;
            sql = "INSERT INTO usuarios VALUES(NULL,?,SHA2(?,256),?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.setString(1, usuario.getCorreo());
            st.setString(2, usuario.getContrasenia());
            st.setBoolean(3, false);            
            st.setString(4, idconfirmacion);
            //4 = tipo de usuario Cliente
            st.setInt(5,4);
            filasAfectadas= st.executeUpdate();
           
            
            //Para obtener el ID del autonumerico ingresado...
            rs=st.getGeneratedKeys();
            rs.next();
            idUsuario=rs.getInt(1);
             this.desconectar();
            //...
            
            //Insertando fila en tabla Clientes teniendo el id de la tabla usuarios
            c.insertarCliente(idUsuario, cliente);
          
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }       
    }
    
    public void confirmarCuenta(String id) throws SQLException {
    try {
        sql = "UPDATE usuarios SET confirmado=true WHERE id_confirmacion=?";
        this.conectar();
        st = conexion.prepareStatement(sql);
        st.setString(1, id);
        st.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE,null, ex);
    }
        this.desconectar();
    }
    
    public int verificarSesion(Usuario usuario) throws SQLException {
        try {
            int tipousuario=0;
            sql = "SELECT Confirmado, IdTipoUsuario FROM usuarios WHERE Correo=? AND Contrasena=SHA2(?,256)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, usuario.getCorreo());
            st.setString(2, usuario.getContrasenia());
            rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean("Confirmado")) {

                    tipousuario = rs.getInt("IdTipoUsuario");
                    this.desconectar();
                    
                    //Retorna el IdTipoUsuario por motivos de logeo
                    return tipousuario;
                } else {
                    this.desconectar();
                    return 0;
                }
            } else {
                this.desconectar();
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return -1;
        }
    }
}
