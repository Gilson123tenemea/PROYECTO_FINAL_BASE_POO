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
public class enca_boleto {

    private String cod_publico, nombre_publico, nobre_evento, codigo_encabezado;
    private Date fecha;

    public enca_boleto() {
    }

    public enca_boleto(String cod_publico, String nombre_publico, String nobre_evento, String codigo_encabezado, Date fecha) {
        this.cod_publico = cod_publico;
        this.nombre_publico = nombre_publico;
        this.nobre_evento = nobre_evento;
        this.codigo_encabezado = codigo_encabezado;
        this.fecha = fecha;
    }

    public String getCod_publico() {
        return cod_publico;
    }

    public void setCod_publico(String cod_publico) {
        this.cod_publico = cod_publico;
    }

    public String getNombre_publico() {
        return nombre_publico;
    }

    public void setNombre_publico(String nombre_publico) {
        this.nombre_publico = nombre_publico;
    }

    public String getNobre_evento() {
        return nobre_evento;
    }

    public void setNobre_evento(String nobre_evento) {
        this.nobre_evento = nobre_evento;
    }

    public String getCodigo_encabezado() {
        return codigo_encabezado;
    }

    public void setCodigo_encabezado(String codigo_encabezado) {
        this.codigo_encabezado = codigo_encabezado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "enca_boleto{" + "cod_publico=" + cod_publico + ", nombre_publico=" + nombre_publico + ", nobre_evento=" + nobre_evento + ", codigo_encabezado=" + codigo_encabezado + ", fecha=" + fecha + '}';
    }

}
