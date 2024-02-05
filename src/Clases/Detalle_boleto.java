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
public class Detalle_boleto {
    
    private double precio,total;
    private int cantidad;
    private String cod_evento,cod_detalle;

    public Detalle_boleto() {
    }

    public Detalle_boleto(double precio, double total, int cantidad, String cod_evento, String cod_detalle) {
        this.precio = precio;
        this.total = total;
        this.cantidad = cantidad;
        this.cod_evento = cod_evento;
        this.cod_detalle = cod_detalle;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCod_evento() {
        return cod_evento;
    }

    public void setCod_evento(String cod_evento) {
        this.cod_evento = cod_evento;
    }

    public String getCod_detalle() {
        return cod_detalle;
    }

    public void setCod_detalle(String cod_detalle) {
        this.cod_detalle = cod_detalle;
    }

    @Override
    public String toString() {
        return "Detalle_boleto{" + "precio=" + precio + ", total=" + total + ", cantidad=" + cantidad + ", cod_evento=" + cod_evento + ", cod_detalle=" + cod_detalle + '}';
    }
    
    
    
    
    
}
