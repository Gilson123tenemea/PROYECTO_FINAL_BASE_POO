
package Clases;

import java.util.Date;

public class Reservar {
    private String codigo_rese ;
    private String coidigo_cli;
    private String codigo_casa;
    private Date fecha_ini;
    private Date fecha_fin;

    public Reservar(String codigo_rese, String coidigo_cli, String codigo_casa, Date fecha_ini, Date fecha_fin) {
        this.codigo_rese = codigo_rese;
        this.coidigo_cli = coidigo_cli;
        this.codigo_casa = codigo_casa;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
    }

    public String getCodigo_rese() {
        return codigo_rese;
    }

    public void setCodigo_rese(String codigo_rese) {
        this.codigo_rese = codigo_rese;
    }

    public String getCoidigo_cli() {
        return coidigo_cli;
    }

    public void setCoidigo_cli(String coidigo_cli) {
        this.coidigo_cli = coidigo_cli;
    }

    public String getCodigo_casa() {
        return codigo_casa;
    }

    public void setCodigo_casa(String codigo_casa) {
        this.codigo_casa = codigo_casa;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    @Override
    public String toString() {
        return "Reservar{" + "codigo_rese=" + codigo_rese + ", coidigo_cli=" + coidigo_cli + ", codigo_casa=" + codigo_casa + ", fecha_ini=" + fecha_ini + ", fecha_fin=" + fecha_fin + '}';
    }

}
