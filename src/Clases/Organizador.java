/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Organizador extends Persona {

    private String cod_organizador;
    private String usuario, contraseña;
    private double Presupuesto;
   //llave secundaria de persona

    public Organizador() {
        super();
    }

    public Organizador(String cod_organizador, String usuario, String contraseña, double Presupuesto, String cedula, String nombre, String apellido, String telefono, String correo, String direccion, String celular, Date fecchaNaci, String genero) {
        super(cedula, nombre, apellido, telefono, correo, direccion, celular, fecchaNaci, genero);
        this.cod_organizador = cod_organizador;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.Presupuesto = Presupuesto;
    }


    

    

    public String getCod_organizador() {
        return cod_organizador;
    }

    public void setCod_organizador(String cod_organizador) {
        this.cod_organizador = cod_organizador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public double getPresupuesto() {
        return Presupuesto;
    }

    public void setPresupuesto(double Presupuesto) {
        this.Presupuesto = Presupuesto;
    }

    @Override
    public String toString() {
        return "Organizador{" + "cod_organizador=" + cod_organizador + ", usuario=" + usuario + ", contrase\u00f1a=" + contraseña + ", Presupuesto=" + Presupuesto + '}';
    }

  
  
}
