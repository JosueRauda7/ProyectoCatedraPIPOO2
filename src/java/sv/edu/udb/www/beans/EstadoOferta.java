/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.beans;

/**
 *
 * @author admi
 */
public class EstadoOferta {
    private int idEstadoOferta;
    private String estado;
    
    public EstadoOferta(){
        this.idEstadoOferta=0;
        this.estado="";
    }

    public int getIdEstadoOferta() {
        return idEstadoOferta;
    }

    public void setIdEstadoOferta(int idEstadoOferta) {
        this.idEstadoOferta = idEstadoOferta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
