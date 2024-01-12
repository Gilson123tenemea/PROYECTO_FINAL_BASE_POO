/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;

/**
 *
 * @author Lenovo.User
 */
public class Imagen implements Serializable {

    
    private byte[] data;
    private String nombreArchivo;
    private String id_imagen;
    private String ruta;
  

    public Imagen() {
    }

    public Imagen(byte[] data, String nombreArchivo, String id_imagen, String ruta) {
        this.data = data;
        this.nombreArchivo = nombreArchivo;
        this.id_imagen = id_imagen;
        this.ruta = ruta;
    }

  

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(String id_imagen) {
        this.id_imagen = id_imagen;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }


   

}
