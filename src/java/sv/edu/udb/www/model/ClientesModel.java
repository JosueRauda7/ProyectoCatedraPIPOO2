/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Cliente;

/**
 *
 * @author ivanm
 */
public class ClientesModel extends Conexion{
    
    public int insertarCliente(int idUsuario, Cliente cliente) throws SQLException{       
        try {
            int filasAfectadas=0;
            sql = "INSERT INTO clientes VALUES(NULL,?,?,?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, cliente.getNombreClientes());
            st.setString(2, cliente.getApellidosClientes());
            st.setString(3, cliente.getDireccion());
            st.setString(4, cliente.getDui());
            st.setInt(5, idUsuario);            
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
    
}
