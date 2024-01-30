package Clases;

import java.util.Date;

public class Comerciantes extends Persona {

    private String codigo_participante;//llave primaria
    private String Tipo_Comercio, Productos_c, Servicio_c;
    private String codigo_puesto;//llave secundaria
    private String evetoSolicita;

    public Comerciantes() {
        super();
    }

    public Comerciantes(String codigo_participante, String Tipo_Comercio, String Productos_c, String Servicio_c, String codigo_puesto, String cedula, String nombre, String apellido, String telefono, String correo, String direccion, String celular, Date fecchaNaci, String genero,String evetoSolicita) {
        super(cedula, nombre, apellido, telefono, correo, direccion, celular, fecchaNaci, genero);
        this.codigo_participante = codigo_participante;
        this.Tipo_Comercio = Tipo_Comercio;
        this.Productos_c = Productos_c;
        this.Servicio_c = Servicio_c;
        this.codigo_puesto = codigo_puesto;
        this.evetoSolicita=evetoSolicita;
    }

    public String getCodigo_participante() {
        return codigo_participante;
    }

    public void setCodigo_participante(String codigo_participante) {
        this.codigo_participante = codigo_participante;
    }

    public String getTipo_Comercio() {
        return Tipo_Comercio;
    }

    public void setTipo_Comercio(String Tipo_Comercio) {
        this.Tipo_Comercio = Tipo_Comercio;
    }

    public String getProductos_c() {
        return Productos_c;
    }

    public void setProductos_c(String Productos_c) {
        this.Productos_c = Productos_c;
    }

    public String getServicio_c() {
        return Servicio_c;
    }

    public void setServicio_c(String Servicio_c) {
        this.Servicio_c = Servicio_c;
    }

    public String getCodigo_puesto() {
        return codigo_puesto;
    }

    public void setCodigo_puesto(String codigo_puesto) {
        this.codigo_puesto = codigo_puesto;
    }

    public String getEvetoSolicita() {
        return evetoSolicita;
    }

    public void setEvetoSolicita(String evetoSolicita) {
        this.evetoSolicita = evetoSolicita;
    }
    
    

}
