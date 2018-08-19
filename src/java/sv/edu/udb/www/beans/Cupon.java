/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.beans;

import java.util.Date;

/**
 *
 * @author admi
 */
public class Cupon {
    private String codigoCupo;
    private Date fechaCompra;
    private Date fechaCanje;
    private Cliente cliente;
    private Oferta oferta;
    private EstadoCupon estadoCupon;
    
    public Cupon(){
        this.codigoCupo="";
        this.fechaCompra=new Date();
        this.fechaCanje=new Date();
        this.cliente=null;
        this.oferta=null;
        this.estadoCupon=null;
    }

    public String getCodigoCupo() {
        return codigoCupo;
    }

    public void setCodigoCupo(String codigoCupo) {
        this.codigoCupo = codigoCupo;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(Date fechaCanje) {
        this.fechaCanje = fechaCanje;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public EstadoCupon getEstadoCupon() {
        return estadoCupon;
    }

    public void setEstadoCupon(EstadoCupon estadoCupon) {
        this.estadoCupon = estadoCupon;
    }
    
}
