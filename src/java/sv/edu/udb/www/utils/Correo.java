/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.utils;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author ivanm
 */
public class Correo {
    private String usuario;
    private String clave;
    private String destinatario;
    private String asunto;
    private String mensaje;

    public Correo() {
        this.usuario = "becas.fantel.sa.sv@gmail.com";
        this.clave = "proyectolp";
        this.destinatario = "";
        this.asunto = "";
        this.mensaje = "";

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }



    
    public boolean enviarCorreo(){
        
        try {
            Properties p = new Properties();
            p.put("mail.smtp.host","smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable","true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", usuario);
            p.setProperty("mail.smtp.auth", "true");
            
            Session sesion=Session.getDefaultInstance(p,null);
            MimeMultipart cuerpo = new MimeMultipart();
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensaje,"text/html");
            cuerpo.addBodyPart(texto);
 
            MimeMessage correo = new MimeMessage(sesion);
            correo.setFrom(new InternetAddress(usuario));
            correo.addRecipient(Message.RecipientType.TO,new InternetAddress(destinatario));
            correo.setSubject(asunto);
            correo.setContent(cuerpo);
            
                Transport t = sesion.getTransport("smtp");
                t.connect(usuario,clave);
                t.sendMessage(correo,correo.getAllRecipients());
                t.close();
                
                return true;
                    
            } catch (MessagingException ex) {
                Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
                return false;
        }
        
    }
}
