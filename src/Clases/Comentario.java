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
public class Comentario {
    
    private String comentario,codigo_evento,codigo_publico,cliente;
    private Date fecha_comentario;

    public Comentario() {
    }

    public Comentario(String comentario, String codigo_evento, String codigo_publico, Date fecha_comentario,String cliente) {
        this.comentario = comentario;
        this.codigo_evento = codigo_evento;
        this.codigo_publico = codigo_publico;
        this.fecha_comentario = fecha_comentario;
        this.cliente=cliente;
    }
    
    

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCodigo_evento() {
        return codigo_evento;
    }

    public void setCodigo_evento(String codigo_evento) {
        this.codigo_evento = codigo_evento;
    }

    public String getCodigo_publico() {
        return codigo_publico;
    }

    public void setCodigo_publico(String codigo_publico) {
        this.codigo_publico = codigo_publico;
    }

    public Date getFecha_comentario() {
        return fecha_comentario;
    }

    public void setFecha_comentario(Date fecha_comentario) {
        this.fecha_comentario = fecha_comentario;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    

    @Override
    public String toString() {
        return "Comentario{" + "comentario=" + comentario + ", codigo_evento=" + codigo_evento + ", codigo_publico=" + codigo_publico + ", fecha_comentario=" + fecha_comentario + '}';
    }
    
    
    
    
    
    
    
    
}
