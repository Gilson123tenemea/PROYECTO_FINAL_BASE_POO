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
public class Tipo_evento {

    private String codigo_tipo, nombre, codigo_evento;
    private byte[] data;

    public Tipo_evento() {
    }

    public Tipo_evento(String codigo_tipo, String nombre, String codigo_evento, byte[] data) {
        this.codigo_tipo = codigo_tipo;
        this.nombre = nombre;
        this.codigo_evento = codigo_evento;
        this.data = data;
    }

    public String getCodigo_tipo() {
        return codigo_tipo;
    }

    public void setCodigo_tipo(String codigo_tipo) {
        this.codigo_tipo = codigo_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo_evento() {
        return codigo_evento;
    }

    public void setCodigo_evento(String codigo_evento) {
        this.codigo_evento = codigo_evento;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

  
}
