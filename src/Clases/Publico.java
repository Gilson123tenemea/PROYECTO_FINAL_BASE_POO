
package Clases;

import Clases.Persona;
import java.util.Date;

public class Publico extends Persona{
    private String codigo_publico;//llave primaria
    private String Tipo_Entrada, Estado_reguistro, Preferencias_p;
    private double Pago_p;
    private String codigo_sube_ima;//llave secundaria de sube imagen
    private String codigo_encuesta;// llave secundaria de Encuesta
    private String califica_eve;//llave secundaria de califica evento
    private String codigo_comen;// llave secunadaria de comentario
    private String cedula_persona;//llave secuandaria de persona

    public Publico() {
        super ();
    }

    public Publico(String codigo_publico, String Tipo_Entrada, String Estado_reguistro, String Preferencias_p, double Pago_p, String codigo_sube_ima, String codigo_encuesta, String califica_eve, String codigo_comen, String cedula_persona, String cedula, String nombre, String apellido, String telefono, String correo, String direccion, String celular, Date fecchaNaci, String genero) {
        super(cedula, nombre, apellido, telefono, correo, direccion, celular, fecchaNaci, genero);
        this.codigo_publico = codigo_publico;
        this.Tipo_Entrada = Tipo_Entrada;
        this.Estado_reguistro = Estado_reguistro;
        this.Preferencias_p = Preferencias_p;
        this.Pago_p = Pago_p;
        this.codigo_sube_ima = codigo_sube_ima;
        this.codigo_encuesta = codigo_encuesta;
        this.califica_eve = califica_eve;
        this.codigo_comen = codigo_comen;
        this.cedula_persona = cedula_persona;
    }

    


    public String getCodigo_publico() {
        return codigo_publico;
    }

    public void setCodigo_publico(String codigo_publico) {
        this.codigo_publico = codigo_publico;
    }

    public String getTipo_Entrada() {
        return Tipo_Entrada;
    }

    public void setTipo_Entrada(String Tipo_Entrada) {
        this.Tipo_Entrada = Tipo_Entrada;
    }

    public String getEstado_reguistro() {
        return Estado_reguistro;
    }

    public void setEstado_reguistro(String Estado_reguistro) {
        this.Estado_reguistro = Estado_reguistro;
    }

    public String getPreferencias_p() {
        return Preferencias_p;
    }

    public void setPreferencias_p(String Preferencias_p) {
        this.Preferencias_p = Preferencias_p;
    }

    public double getPago_p() {
        return Pago_p;
    }

    public void setPago_p(double Pago_p) {
        this.Pago_p = Pago_p;
    }

    public String getCodigo_sube_ima() {
        return codigo_sube_ima;
    }

    public void setCodigo_sube_ima(String codigo_sube_ima) {
        this.codigo_sube_ima = codigo_sube_ima;
    }

    public String getCodigo_encuesta() {
        return codigo_encuesta;
    }

    public void setCodigo_encuesta(String codigo_encuesta) {
        this.codigo_encuesta = codigo_encuesta;
    }

    public String getCalifica_eve() {
        return califica_eve;
    }

    public void setCalifica_eve(String califica_eve) {
        this.califica_eve = califica_eve;
    }

    public String getCodigo_comen() {
        return codigo_comen;
    }

    public void setCodigo_comen(String codigo_comen) {
        this.codigo_comen = codigo_comen;
    }

    public String getCedula_persona() {
        return cedula_persona;
    }

    public void setCedula_persona(String cedula_persona) {
        this.cedula_persona = cedula_persona;
    }
    
    
}
