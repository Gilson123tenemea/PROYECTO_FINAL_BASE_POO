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
public class Ciudad {
    
    private String Codigo_ciudad;
    private String Pais_C;
    private String nombre;
    private String region;

    public Ciudad(String Codigo_ciudad, String Pais_C, String nombre, String region) {
        this.Codigo_ciudad = Codigo_ciudad;
        this.Pais_C = Pais_C;
        this.nombre = nombre;
        this.region = region;
    }

    public Ciudad() {
    }

    public String getCodigo_ciudad() {
        return Codigo_ciudad;
    }

    public void setCodigo_ciudad(String Codigo_ciudad) {
        this.Codigo_ciudad = Codigo_ciudad;
    }

    public String getPais_C() {
        return Pais_C;
    }

    public void setPais_C(String Pais_C) {
        this.Pais_C = Pais_C;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    
    
    
}
