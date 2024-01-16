/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Lenovo
 */
public class Tipo_Comercio {
    
    private String Cod_tipocomercion,nombre,descripcion;

    public Tipo_Comercio(String Cod_tipocomercion, String nombre, String descripcion) {
        this.Cod_tipocomercion = Cod_tipocomercion;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Tipo_Comercio() {
    }

    public String getCod_tipocomercion() {
        return Cod_tipocomercion;
    }

    public void setCod_tipocomercion(String Cod_tipocomercion) {
        this.Cod_tipocomercion = Cod_tipocomercion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Tipo_Comercio{" + "Cod_tipocomercion=" + Cod_tipocomercion + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
    
    
}
