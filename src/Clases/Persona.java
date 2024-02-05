
package Clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Persona {
    private String cedula, nombre, apellido, telefono, correo, direccion, celular;
    private Date fecchaNaci;
    private String genero;
    
    private Integer edad; // Esta variable se definira solo en los casos que se necesiten

    public Persona() {
    }

    public Persona(String cedula, String nombre, String apellido, String telefono, String correo, String direccion, String celular, Date fecchaNaci, String genero) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.fecchaNaci = fecchaNaci;
        this.genero = genero;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFecchaNaci() {
        return fecchaNaci;
    }

    public void setFecchaNaci(Date fecchaNaci) {
        this.fecchaNaci = fecchaNaci;
    }

 
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public Integer getEdad() {
        return edad;
    }
    
    public void setEdad() {
        Date fechaActual = new Date();
        LocalDateTime fechaNacimiento = LocalDateTime.ofInstant(getFecchaNaci().toInstant(), ZoneId.systemDefault());
        this.edad = Period.between(fechaNacimiento.toLocalDate(), LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Persona{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", correo=" + correo + ", direccion=" + direccion + ", celular=" + celular + ", fecchaNaci=" + fecchaNaci + ", genero=" + genero + '}';
    }
 
}
