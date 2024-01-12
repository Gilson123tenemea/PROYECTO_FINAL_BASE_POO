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
public class Direccion {
    
     private String Codigo_direccion;
        private String calle_principal;
        private String calle_secundaria;

    public Direccion(String Codigo_direccion, String calle_principal, String calle_secundaria) {
        this.Codigo_direccion = Codigo_direccion;
        this.calle_principal = calle_principal;
        this.calle_secundaria = calle_secundaria;
    }

    public Direccion() {
    }

    public String getCodigo_direccion() {
        return Codigo_direccion;
    }

    public void setCodigo_direccion(String Codigo_direccion) {
        this.Codigo_direccion = Codigo_direccion;
    }

    public String getCalle_principal() {
        return calle_principal;
    }

    public void setCalle_principal(String calle_principal) {
        this.calle_principal = calle_principal;
    }

    public String getCalle_secundaria() {
        return calle_secundaria;
    }

    public void setCalle_secundaria(String calle_secundaria) {
        this.calle_secundaria = calle_secundaria;
    }

    @Override
    public String toString() {
        return "Direccion{" + "Codigo_direccion=" + Codigo_direccion + ", calle_principal=" + calle_principal + ", calle_secundaria=" + calle_secundaria + '}';
    }
        
        
        
    
}
