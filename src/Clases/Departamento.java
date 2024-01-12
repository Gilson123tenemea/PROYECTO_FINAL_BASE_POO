/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author eliza
 */
public class Departamento {

    private String id_departamento;
    private String nombre;
    private String descripcion;

    public Departamento() {
    }

    public Departamento(String id_departamento, String nombre, String descripcion) {
        this.id_departamento = id_departamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(String id_departamento) {
        this.id_departamento = id_departamento;
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

    @Override
    public String toString() {
        return "Departamento{" + "id_departamento=" + id_departamento + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

}
