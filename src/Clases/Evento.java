/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author Mauricio
 */
public class Evento {

    private String cod_evento, nombre, descripcion, codigo_patrocinador, codigo_agenda, tipo;
    private Date fecha_inicio, Fecha_fin;
    private String hora_inicio, hora_fin;
    private byte[] data;

    public Evento() {
    }

    public Evento(String cod_evento, String nombre, String descripcion, String codigo_patrocinador, String codigo_agenda, Date fecha_inicio, Date Fecha_fin, String hora_inicio, String hora_fin, String tipo,byte[] data) {
        this.cod_evento = cod_evento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo_patrocinador = codigo_patrocinador;
        this.codigo_agenda = codigo_agenda;
        this.fecha_inicio = fecha_inicio;
        this.Fecha_fin = Fecha_fin;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.tipo = tipo;
        this.data=data;

    }

    public String getCod_evento() {
        return cod_evento;
    }

    public void setCod_evento(String cod_evento) {
        this.cod_evento = cod_evento;
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

    public String getCodigo_patrocinador() {
        return codigo_patrocinador;
    }

    public void setCodigo_patrocinador(String codigo_patrocinador) {
        this.codigo_patrocinador = codigo_patrocinador;
    }

    public String getCodigo_agenda() {
        return codigo_agenda;
    }

    public void setCodigo_agenda(String codigo_agenda) {
        this.codigo_agenda = codigo_agenda;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return Fecha_fin;
    }

    public void setFecha_fin(Date Fecha_fin) {
        this.Fecha_fin = Fecha_fin;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

   

}
