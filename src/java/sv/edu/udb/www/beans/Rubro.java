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
public class Rubro {
    private int idRubro;
    private String rubro;
    public Rubro(){
        this.idRubro=0;
        this.rubro="";
       
    }

    public Rubro(String rubro) {
        this.rubro = rubro;
    }
    
    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }
    
}
