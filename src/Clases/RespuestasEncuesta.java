
package Clases;
public class RespuestasEncuesta {
    private String cod_Respu;
    private String cliente;
    private String evento;
    private String Res1;
    private String Res2;
    private String Res3;
    private String Res4;
    private String Res5;

    public RespuestasEncuesta(String cod_Respu, String cliente, String evento, String Res1, String Res2, String Res3, String Res4, String Res5) {
        this.cod_Respu = cod_Respu;
        this.cliente = cliente;
        this.evento = evento;
        this.Res1 = Res1;
        this.Res2 = Res2;
        this.Res3 = Res3;
        this.Res4 = Res4;
        this.Res5 = Res5;
    }

    public String getCod_Respu() {
        return cod_Respu;
    }

    public void setCod_Respu(String cod_Respu) {
        this.cod_Respu = cod_Respu;
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

    public String getRes1() {
        return Res1;
    }

    public void setRes1(String Res1) {
        this.Res1 = Res1;
    }

    public String getRes2() {
        return Res2;
    }

    public void setRes2(String Res2) {
        this.Res2 = Res2;
    }

    public String getRes3() {
        return Res3;
    }

    public void setRes3(String Res3) {
        this.Res3 = Res3;
    }

    public String getRes4() {
        return Res4;
    }

    public void setRes4(String Res4) {
        this.Res4 = Res4;
    }

    public String getRes5() {
        return Res5;
    }

    public void setRes5(String Res5) {
        this.Res5 = Res5;
    }

    @Override
    public String toString() {
        return "RespuestasEncuesta{" + "cod_Respu=" + cod_Respu + ", cliente=" + cliente + ", evento=" + evento + ", Res1=" + Res1 + ", Res2=" + Res2 + ", Res3=" + Res3 + ", Res4=" + Res4 + ", Res5=" + Res5 + '}';
    }

}
