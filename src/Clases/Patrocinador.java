
package Clases;

import java.util.Date;

public class Patrocinador extends Persona{
    private String codigo_patri;//llave primaria
    private String Descripcion_p;
    private String redes_sociales;

    public Patrocinador() {
        super();
    }

    public Patrocinador(String codigo_patri, String Descripcion_p, String redes_sociales, String cedula, String nombre, String apellido, String telefono, String correo, String direccion, String celular, Date fecchaNaci, String genero) {
        super(cedula, nombre, apellido, telefono, correo, direccion, celular, fecchaNaci, genero);
        this.codigo_patri = codigo_patri;
        this.Descripcion_p = Descripcion_p;
        this.redes_sociales = redes_sociales;
    }

    public String getCodigo_patri() {
        return codigo_patri;
    }

    public void setCodigo_patri(String codigo_patri) {
        this.codigo_patri = codigo_patri;
    }

    public String getDescripcion_p() {
        return Descripcion_p;
    }

    public void setDescripcion_p(String Descripcion_p) {
        this.Descripcion_p = Descripcion_p;
    }

    public String getRedes_sociales() {
        return redes_sociales;
    }

    public void setRedes_sociales(String redes_sociales) {
        this.redes_sociales = redes_sociales;
    }
    
    
}
