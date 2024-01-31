/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Lenovo.User
 */
public class Asistencia {
    private String cod_aistencia,evento,cliente;

    public Asistencia() {
    }

    public Asistencia(String cod_aistencia, String evento, String cliente) {
        this.cod_aistencia = cod_aistencia;
        this.evento = evento;
        this.cliente = cliente;
    }

    public String getCod_aistencia() {
        return cod_aistencia;
    }

    public void setCod_aistencia(String cod_aistencia) {
        this.cod_aistencia = cod_aistencia;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    
    
}
