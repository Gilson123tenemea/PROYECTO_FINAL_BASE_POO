
package Clases;

public class Tipos_Personales {
    private String id_tip_peronal;
    private String nombre;
    private String Descripcion;

    public Tipos_Personales() {
    }

    public Tipos_Personales(String id_tip_peronal, String nombre, String Descripcion) {
        this.id_tip_peronal = id_tip_peronal;
        this.nombre = nombre;
        this.Descripcion = Descripcion;
    }

    public String getId_tip_peronal() {
        return id_tip_peronal;
    }

    public void setId_tip_peronal(String id_tip_peronal) {
        this.id_tip_peronal = id_tip_peronal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

}
