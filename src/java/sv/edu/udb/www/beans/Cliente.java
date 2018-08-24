package sv.edu.udb.www.beans;


public class Cliente {
    private int idCliente;
    private String nombreClientes;
    private String apellidosClientes;
    private String direccion;
    private String dui;
    private Usuario usuario;
    
    public Cliente(){
        this.idCliente=0;
        this.nombreClientes="";
        this.apellidosClientes="";
        this.direccion="";
        this.dui="";
        this.usuario=null;
    }

    public Cliente(String nombreClientes, String apellidosClientes, String dui){
        this.nombreClientes = nombreClientes;
        this.apellidosClientes = apellidosClientes;
        this.dui = dui;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreClientes() {
        return nombreClientes;
    }

    public void setNombreClientes(String nombreClientes) {
        this.nombreClientes = nombreClientes;
    }

    public String getApellidosClientes() {
        return apellidosClientes;
    }

    public void setApellidosClientes(String apellidosClientes) {
        this.apellidosClientes = apellidosClientes;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
