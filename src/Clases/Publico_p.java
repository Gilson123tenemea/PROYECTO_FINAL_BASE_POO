package Clases;

import Clases.Persona;
import java.util.Date;

public class Publico_p extends Persona {

    private String codigo_publico;
    private String estado_registro;
    private String preferencias_p;
    private String contraseña;
    private String codigo_encuesta;
    private String codigo_cali_even;
    private String cedula_persona;

    public Publico_p() {
        super();
    }

    public Publico_p(String codigo_publico, String estado_registro, String preferencias_p, String contraseña, String codigo_encuesta, String codigo_cali_even, String cedula_persona, String cedula, String nombre, String apellido, String telefono, String correo, String direccion, String celular, Date fecchaNaci, String genero) {
        super(cedula, nombre, apellido, telefono, correo, direccion, celular, fecchaNaci, genero);
        this.codigo_publico = codigo_publico;
        this.estado_registro = estado_registro;
        this.preferencias_p = preferencias_p;
        this.contraseña = contraseña;
        this.codigo_encuesta = codigo_encuesta;
        this.codigo_cali_even = codigo_cali_even;
        this.cedula_persona = cedula_persona;
    }

    public String getCodigo_publico() {
        return codigo_publico;
    }

    public void setCodigo_publico(String codigo_publico) {
        this.codigo_publico = codigo_publico;
    }

    public String getEstado_registro() {
        return estado_registro;
    }

    public void setEstado_registro(String estado_registro) {
        this.estado_registro = estado_registro;
    }

    public String getPreferencias_p() {
        return preferencias_p;
    }

    public void setPreferencias_p(String preferencias_p) {
        this.preferencias_p = preferencias_p;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCodigo_encuesta() {
        return codigo_encuesta;
    }

    public void setCodigo_encuesta(String codigo_encuesta) {
        this.codigo_encuesta = codigo_encuesta;
    }

    public String getCodigo_cali_even() {
        return codigo_cali_even;
    }

    public void setCodigo_cali_even(String codigo_cali_even) {
        this.codigo_cali_even = codigo_cali_even;
    }

    public String getCedula_persona() {
        return cedula_persona;
    }

    public void setCedula_persona(String cedula_persona) {
        this.cedula_persona = cedula_persona;
    }

    @Override
    public String toString() {
        return "Publico{" + "codigo_publico=" + codigo_publico + ", estado_registro=" + estado_registro + ", preferencias_p=" + preferencias_p + ", contrase\u00f1a=" + contraseña + ", codigo_encuesta=" + codigo_encuesta + ", codigo_cali_even=" + codigo_cali_even + ", cedula_persona=" + cedula_persona + '}';
    }

}
