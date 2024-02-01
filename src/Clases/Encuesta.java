/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Encuesta {

    private String Cod_encuesta;
    private String Nombre_encuesta;
    private String evento;
    private String Descrpcion_encuesta;
    private Date fecha_inicio;
    private Date Fecha_fin;
    private String Pregunta1;
    private String Pregunta2;
    private String Pregunta3;
    private String Pregunta4;
    private String Pregunta5;

    public Encuesta() {
    }

    public Encuesta(String Cod_encuesta, String Nombre_encuesta, String evento, String Descrpcion_encuesta, Date fecha_inicio, Date Fecha_fin, String Pregunta1, String Pregunta2, String Pregunta3, String Pregunta4, String Pregunta5) {
        this.Cod_encuesta = Cod_encuesta;
        this.Nombre_encuesta = Nombre_encuesta;
        this.evento = evento;
        this.Descrpcion_encuesta = Descrpcion_encuesta;
        this.fecha_inicio = fecha_inicio;
        this.Fecha_fin = Fecha_fin;
        this.Pregunta1 = Pregunta1;
        this.Pregunta2 = Pregunta2;
        this.Pregunta3 = Pregunta3;
        this.Pregunta4 = Pregunta4;
        this.Pregunta5 = Pregunta5;
    }

    public String getCod_encuesta() {
        return Cod_encuesta;
    }

    public void setCod_encuesta(String Cod_encuesta) {
        this.Cod_encuesta = Cod_encuesta;
    }

    public String getNombre_encuesta() {
        return Nombre_encuesta;
    }

    public void setNombre_encuesta(String Nombre_encuesta) {
        this.Nombre_encuesta = Nombre_encuesta;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getDescrpcion_encuesta() {
        return Descrpcion_encuesta;
    }

    public void setDescrpcion_encuesta(String Descrpcion_encuesta) {
        this.Descrpcion_encuesta = Descrpcion_encuesta;
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

    public String getPregunta1() {
        return Pregunta1;
    }

    public void setPregunta1(String Pregunta1) {
        this.Pregunta1 = Pregunta1;
    }

    public String getPregunta2() {
        return Pregunta2;
    }

    public void setPregunta2(String Pregunta2) {
        this.Pregunta2 = Pregunta2;
    }

    public String getPregunta3() {
        return Pregunta3;
    }

    public void setPregunta3(String Pregunta3) {
        this.Pregunta3 = Pregunta3;
    }

    public String getPregunta4() {
        return Pregunta4;
    }

    public void setPregunta4(String Pregunta4) {
        this.Pregunta4 = Pregunta4;
    }

    public String getPregunta5() {
        return Pregunta5;
    }

    public void setPregunta5(String Pregunta5) {
        this.Pregunta5 = Pregunta5;
    }

    @Override
    public String toString() {
        return "Encuesta{" + "Cod_encuesta=" + Cod_encuesta + ", Nombre_encuesta=" + Nombre_encuesta + ", evento=" + evento + ", Descrpcion_encuesta=" + Descrpcion_encuesta + ", fecha_inicio=" + fecha_inicio + ", Fecha_fin=" + Fecha_fin + ", Pregunta1=" + Pregunta1 + ", Pregunta2=" + Pregunta2 + ", Pregunta3=" + Pregunta3 + ", Pregunta4=" + Pregunta4 + ", Pregunta5=" + Pregunta5 + '}';
    }

}
