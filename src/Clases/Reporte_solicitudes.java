/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author Lenovo.User
 */
public class Reporte_solicitudes {
    
    private String organizador,comerciante,puesto,codigo_soli;
    private boolean aceptacion;
    private Date fecha_aceptacion;

    public Reporte_solicitudes() {
    }

    public Reporte_solicitudes(String organizador, String comerciante, String puesto, boolean aceptacion, Date fecha_aceptacion,String codigo_soli) {
        this.organizador = organizador;
        this.comerciante = comerciante;
        this.puesto = puesto;
        this.aceptacion = aceptacion;
        this.fecha_aceptacion = fecha_aceptacion;
        this.codigo_soli=codigo_soli;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public String getComerciante() {
        return comerciante;
    }

    public void setComerciante(String comerciante) {
        this.comerciante = comerciante;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public boolean isAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public Date getFecha_aceptacion() {
        return fecha_aceptacion;
    }

    public void setFecha_aceptacion(Date fecha_aceptacion) {
        this.fecha_aceptacion = fecha_aceptacion;
    }

    public String getCodigo_soli() {
        return codigo_soli;
    }

    public void setCodigo_soli(String codigo_soli) {
        this.codigo_soli = codigo_soli;
    }

    @Override
    public String toString() {
        return "Reporte_solicitudes{" + "organizador=" + organizador + ", comerciante=" + comerciante + ", puesto=" + puesto + ", codigo_soli=" + codigo_soli + ", aceptacion=" + aceptacion + ", fecha_aceptacion=" + fecha_aceptacion + '}';
    }

    
    
   
    
    
}
