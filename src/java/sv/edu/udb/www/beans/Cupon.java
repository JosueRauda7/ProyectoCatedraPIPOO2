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
    private String fechaCompra;
    private String fechaCanje;
    private Cliente cliente;
    private Oferta oferta;
    private EstadoCupon estadoCupon;
    private int idEstadoCupon;

    //datos de ofertas que se utilizaran en una vista
    private String tituloOferta;
    private String fechaLimite;
    private String url_foto;
    private String descripcionOferta;
    private double precioRegular;
    private double precioOferta;
    
    private Date fechaL;
    
    public Cupon(){
        this.codigoCupo="";
        this.fechaCompra="";
        this.fechaCanje="";
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

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(String fechaCanje) {
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
    //datos que se utilizan para vista
    public String getTituloOferta() {
        return tituloOferta;
    }

    public void setTituloOferta(String tituloOferta) {
        this.tituloOferta = tituloOferta;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public String getDescripcionOferta() {
        return descripcionOferta;
    }

    public void setDescripcionOferta(String descripcionOferta) {
        this.descripcionOferta = descripcionOferta;
    }

    public double getPrecioRegular() {
        return precioRegular;
    }

    public void setPrecioRegular(double precioRegular) {
        this.precioRegular = precioRegular;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
    }

    public int getIdEstadoCupon() {
        return idEstadoCupon;
    }

    public void setIdEstadoCupon(int idEstadoCupon) {
        this.idEstadoCupon = idEstadoCupon;
    }

    public Date getFechaL() {
        return fechaL;
    }

    public void setFechaL(Date fechaL) {
        this.fechaL = fechaL;
    }
    
}
