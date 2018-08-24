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
public class EstadoCupon {
    private int idEstadoCupon;
    private String estado;
    
    public EstadoCupon(){
        this.idEstadoCupon=0;
        this.estado="";
    }
    
    public EstadoCupon(String estado){
        this.estado = estado;
    }

    public int getIdEstadoCupon() {
        return idEstadoCupon;
    }

    public void setIdEstadoCupon(int idEstadoCupon) {
        this.idEstadoCupon = idEstadoCupon;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
