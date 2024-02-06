
package Clases;

import java.util.Date;

public class Reporte_Boletos {
    private String compueva,cliente,evento;
    private Date fecha;
    private double precio,cantidad,total;

    public Reporte_Boletos(String compueva, String cliente, String evento, Date fecha, double precio, double cantidad, double total) {
        this.compueva = compueva;
        this.cliente = cliente;
        this.evento = evento;
        this.fecha = fecha;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public String getCompueva() {
        return compueva;
    }

    public void setCompueva(String compueva) {
        this.compueva = compueva;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    
}
