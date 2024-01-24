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
public class Calificar_evento {
    
    private String cod_evento,cod_cliente,cod_calificacion;
    private int valoracion;
    
    private Date fecha_calificacion;

    public Calificar_evento() {
    }

    public Calificar_evento(String cod_evento, String cod_cliente, String cod_calificacion, int valoracion, Date fecha_calificacion) {
        this.cod_evento = cod_evento;
        this.cod_cliente = cod_cliente;
        this.cod_calificacion = cod_calificacion;
        this.valoracion = valoracion;
        this.fecha_calificacion = fecha_calificacion;
    }
    
    

    public String getCod_evento() {
        return cod_evento;
    }

    public void setCod_evento(String cod_evento) {
        this.cod_evento = cod_evento;
    }

    public String getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getCod_calificacion() {
        return cod_calificacion;
    }

    public void setCod_calificacion(String cod_calificacion) {
        this.cod_calificacion = cod_calificacion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Date getFecha_calificacion() {
        return fecha_calificacion;
    }

    public void setFecha_calificacion(Date fecha_calificacion) {
        this.fecha_calificacion = fecha_calificacion;
    }

    @Override
    public String toString() {
        return "Calificar_evento{" + "cod_evento=" + cod_evento + ", cod_cliente=" + cod_cliente + ", cod_calificacion=" + cod_calificacion + ", valoracion=" + valoracion + ", fecha_calificacion=" + fecha_calificacion + '}';
    }
    
    
    
    
}
