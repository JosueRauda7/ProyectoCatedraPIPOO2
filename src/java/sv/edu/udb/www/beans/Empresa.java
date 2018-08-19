
package sv.edu.udb.www.beans;

/**
 *
 * @author admi
 */
public class Empresa {
    private String codigoEmpresa;
    private String nombreEmpresa;
    private String nombreContacto;
    private String direccion;
    private String telefono;
    private Rubro rubro;
    private double comision;
    private Usuario usuario;
    private int idRubro;
    private int idUsuario;
    
    public Empresa(){
        this.codigoEmpresa="";
        this.nombreEmpresa="";
        this.nombreContacto="";
        this.direccion="";
        this.telefono="";
        this.rubro=null;
        this.comision=0;
        this.usuario=null;
        this.idRubro=0;
        this.idUsuario=0;
    }
public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
