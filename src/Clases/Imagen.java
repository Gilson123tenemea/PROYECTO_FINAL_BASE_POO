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
public class Imagen {
    
    private String cod_evento,cod_publico,cod_imagen;
    private byte[] data;

    public Imagen() {
    }

    public Imagen(String cod_evento, String cod_publico, String cod_imagen, byte[] data) {
        this.cod_evento = cod_evento;
        this.cod_publico = cod_publico;
        this.cod_imagen = cod_imagen;
        this.data = data;
    }

    public String getCod_evento() {
        return cod_evento;
    }

    public void setCod_evento(String cod_evento) {
        this.cod_evento = cod_evento;
    }

    public String getCod_publico() {
        return cod_publico;
    }

    public void setCod_publico(String cod_publico) {
        this.cod_publico = cod_publico;
    }

    public String getCod_imagen() {
        return cod_imagen;
    }

    public void setCod_imagen(String cod_imagen) {
        this.cod_imagen = cod_imagen;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Imagen{" + "cod_evento=" + cod_evento + ", cod_publico=" + cod_publico + ", cod_imagen=" + cod_imagen + ", data=" + data + '}';
    }
    
    
    
}
