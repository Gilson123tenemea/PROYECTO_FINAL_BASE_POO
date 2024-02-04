package Clases;

import Clases.Persona;
import java.util.Date;

public class Personal extends Persona {

    private String codigo_perso;//llave primraria
    private String Tipo_personal, Departamento_p, contanto_p, Calificacion_p;
    private Date Fecha_contrato_inicio;
    private Date Fecha_contrato_fin;
    private String cedula_perso;//llave secundaria de persona
    private String cod_evento;//llave secundaria de persona
    private String cod_organizador;

    public Personal() {
        super();
    }

    public Personal(String codigo_perso, String Tipo_personal, String Departamento_p, String contanto_p, String Calificacion_p, Date Fecha_contrato_inicio, Date Fecha_contrato_fin, String cedula_perso, String cod_evento, String cod_organizador, String cedula, String nombre, String apellido, String telefono, String correo, String direccion, String celular, Date fecchaNaci, String genero) {
        super(cedula, nombre, apellido, telefono, correo, direccion, celular, fecchaNaci, genero);
        this.codigo_perso = codigo_perso;
        this.Tipo_personal = Tipo_personal;
        this.Departamento_p = Departamento_p;
        this.contanto_p = contanto_p;
        this.Calificacion_p = Calificacion_p;
        this.Fecha_contrato_inicio = Fecha_contrato_inicio;
        this.Fecha_contrato_fin = Fecha_contrato_fin;
        this.cedula_perso = cedula_perso;
        this.cod_evento = cod_evento;
        this.cod_organizador = cod_organizador;
    }

    public String getCodigo_perso() {
        return codigo_perso;
    }

    public void setCodigo_perso(String codigo_perso) {
        this.codigo_perso = codigo_perso;
    }

    public String getTipo_personal() {
        return Tipo_personal;
    }

    public void setTipo_personal(String Tipo_personal) {
        this.Tipo_personal = Tipo_personal;
    }

    public String getDepartamento_p() {
        return Departamento_p;
    }

    public void setDepartamento_p(String Departamento_p) {
        this.Departamento_p = Departamento_p;
    }

    public String getContanto_p() {
        return contanto_p;
    }

    public void setContanto_p(String contanto_p) {
        this.contanto_p = contanto_p;
    }

    public String getCalificacion_p() {
        return Calificacion_p;
    }

    public void setCalificacion_p(String Calificacion_p) {
        this.Calificacion_p = Calificacion_p;
    }

    public Date getFecha_contrato_inicio() {
        return Fecha_contrato_inicio;
    }

    public void setFecha_contrato_inicio(Date Fecha_contrato_inicio) {
        this.Fecha_contrato_inicio = Fecha_contrato_inicio;
    }

    public Date getFecha_contrato_fin() {
        return Fecha_contrato_fin;
    }

    public void setFecha_contrato_fin(Date Fecha_contrato_fin) {
        this.Fecha_contrato_fin = Fecha_contrato_fin;
    }

    public String getCedula_perso() {
        return cedula_perso;
    }

    public void setCedula_perso(String cedula_perso) {
        this.cedula_perso = cedula_perso;
    }

    public String getCod_evento() {
        return cod_evento;
    }

    public void setCod_evento(String cod_evento) {
        this.cod_evento = cod_evento;
    }

    public String getCod_organizador() {
        return cod_organizador;
    }

    public void setCod_organizador(String cod_organizador) {
        this.cod_organizador = cod_organizador;
    }

    @Override
    public String toString() {
        return "Personal{" + "codigo_perso=" + codigo_perso + ", Tipo_personal=" + Tipo_personal + ", Departamento_p=" + Departamento_p + ", contanto_p=" + contanto_p + ", Calificacion_p=" + Calificacion_p + ", Fecha_contrato_inicio=" + Fecha_contrato_inicio + ", Fecha_contrato_fin=" + Fecha_contrato_fin + ", cedula_perso=" + cedula_perso + ", cod_evento=" + cod_evento + ", cod_organizador=" + cod_organizador + '}';
    }

}
