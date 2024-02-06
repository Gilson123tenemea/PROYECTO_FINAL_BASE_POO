package base;

import Clases.Departamento;
import Clases.Encuesta;
import Clases.Organizador;
import Clases.Patrocinador;
import Clases.Personal;
import Clases.Puesto;
import Clases.Tipo_Comercio;
import Clases.Tipos_Personales;
import Interfases.Inicio;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ImpresionDeReportes {

    ArrayList<Departamento> listaDepartamentos = new ArrayList<>();
    ArrayList<Tipos_Personales> listaTipoPersonal = new ArrayList<>();
    ArrayList<Personal> listaPersonal = new ArrayList<>();
    ArrayList<Puesto> listaPuesto = new ArrayList<>();
    ArrayList<Tipo_Comercio> listaTipoComercial = new ArrayList<>();
    ArrayList<Organizador> listaOrganizadores = new ArrayList<>();
    

    public Organizador organizador;

    public ImpresionDeReportes(Organizador organizador) {
        this.organizador = organizador;

    }

    public void impresionPatrocinador(ArrayList<Patrocinador> listaPatrocinadores) {

        if (!listaPatrocinadores.isEmpty()) {

            JRBeanArrayDataSource emptyDataSource = new JRBeanArrayDataSource(listaPatrocinadores.toArray());
            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(listaPatrocinadores.toArray());

            cargarParametros("ReportePatrocinador.jasper", ds, emptyDataSource);
        } else {
            JOptionPane.showMessageDialog(null, "NO podemos mostrar datos estadisticas y reportes debido a que no existen registros");

        }

    }

    public void impresionDepartamento() {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        ObjectSet<Departamento> result = base.queryByExample(Departamento.class);

        if (!result.isEmpty()) {

            while (result.hasNext()) {
                Departamento departamento = result.next();
                listaDepartamentos.add(departamento);
            }
            base.close();

            JRBeanArrayDataSource emptyDataSource = new JRBeanArrayDataSource(listaDepartamentos.toArray());
            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(listaDepartamentos.toArray());

            cargarParametros("ReporteDepartamento.jasper", ds, emptyDataSource);
        } else {

            JOptionPane.showMessageDialog(null, "NO podemos mostrar datos estadisticas y reportes debido a que no existen registros");

        }

    }

    public void impresionTipoPersonal() {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        ObjectSet<Tipos_Personales> result = base.queryByExample(Tipos_Personales.class);

        if (!result.isEmpty()) {

            while (result.hasNext()) {
                Tipos_Personales tipo = result.next();
                listaTipoPersonal.add(tipo);
            }
            System.out.println("Size Tipo ---> " + listaTipoPersonal.size());
            base.close();

            JRBeanArrayDataSource emptyDataSource = new JRBeanArrayDataSource(listaTipoPersonal.toArray());
            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(listaTipoPersonal.toArray());

            cargarParametros("ReporteTipoPersona.jasper", ds, emptyDataSource);
        } else {

            JOptionPane.showMessageDialog(null, "NO podemos mostrar datos estadisticas y reportes debido a que no existen registros");

        }

    }

    public void impresionPersonal(ArrayList<Personal> listaPersonal) {

        if (!listaPersonal.isEmpty()) {

            JRBeanArrayDataSource emptyDataSource = new JRBeanArrayDataSource(listaPersonal.toArray());
            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(listaPersonal.toArray());

            cargarParametros("ReportePersonales.jasper", ds, emptyDataSource);
        } else {

            JOptionPane.showMessageDialog(null, "NO podemos mostrar datos estadisticas y reportes debido a que no existen registros");

        }

    }

    public void impresionPersonal(String departamento, String tipo_personal, String evento) {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        ObjectSet<Personal> result = null;
        Query query = base.query();
        query.constrain(Personal.class);

        if (departamento.equalsIgnoreCase("Seleccione")
                && tipo_personal.equalsIgnoreCase("Seleccione")
                && evento.equalsIgnoreCase("Seleccione")) {

            result = base.queryByExample(Personal.class);
            if (!result.isEmpty()) {

                result.forEachRemaining(listaPersonal::add);
            }

        } else {

            if (!departamento.equalsIgnoreCase("Seleccione")) {
                query.descend("departamento_p").constrain(departamento);
                result = query.execute();
                if (!result.isEmpty()) {
                    result.forEachRemaining(listaPersonal::add);
                }
            }

            if (!tipo_personal.equalsIgnoreCase("Seleccione")) {
                query.descend("tipo_personal").constrain(tipo_personal);
                result = query.execute();
                if (!result.isEmpty()) {
                    result.forEachRemaining(listaPersonal::add);
                }
            }

            if (!evento.equalsIgnoreCase("Seleccione")) {
                query.descend("cod_evento").constrain(evento);
                result = query.execute();
                if (!result.isEmpty()) {
                    result.forEachRemaining(listaPersonal::add);
                }
            }
        }

        base.close();
        System.out.println("Muestra --->" + listaPersonal.get(1));

        JRBeanArrayDataSource emptyDataSource = new JRBeanArrayDataSource(listaPersonal.toArray());
        JRBeanArrayDataSource ds = new JRBeanArrayDataSource(listaPersonal.toArray());


    }

    public void impresionPuesto() {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        ObjectSet<Puesto> result = base.queryByExample(Puesto.class);

        if (!result.isEmpty()) {

            while (result.hasNext()) {
                Puesto puesto = result.next();
                listaPuesto.add(puesto);
            }
            base.close();

            JRBeanArrayDataSource emptyDataSource = new JRBeanArrayDataSource(listaPuesto.toArray());
            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(listaPuesto.toArray());

            cargarParametros("ReportePuesto.jasper", ds, emptyDataSource);
        } else {

            JOptionPane.showMessageDialog(null, "NO podemos mostrar datos estadisticas y reportes debido a que no existen registros");

        }

    }
    
    public void impresionTipoComercial() {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        ObjectSet<Tipo_Comercio> result = base.queryByExample(Tipo_Comercio.class);

        if (!result.isEmpty()) {

            while (result.hasNext()) {
                Tipo_Comercio puesto = result.next();
                listaTipoComercial.add(puesto);
            }
            base.close();

            JRBeanArrayDataSource emptyDataSource = new JRBeanArrayDataSource(listaTipoComercial.toArray());
            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(listaTipoComercial.toArray());

            cargarParametros("ReporteTipoComercial.jasper", ds, emptyDataSource);
        } else {

            JOptionPane.showMessageDialog(null, "NO podemos mostrar datos estadisticas y reportes debido a que no existen registros");

        }

    }
    
    public void impresionOrganizadores() {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        ObjectSet<Organizador> result = base.queryByExample(Organizador.class);

        if (!result.isEmpty()) {

            while (result.hasNext()) {
                Organizador organizador = result.next();
                organizador.setEdad();
                listaOrganizadores.add(organizador);
            }
            base.close();

            JRBeanArrayDataSource emptyDataSource = new JRBeanArrayDataSource(listaOrganizadores.toArray());
            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(listaOrganizadores.toArray());

            cargarParametrosOrganizador("ReporteOrganizador.jasper", ds, emptyDataSource);
        } else {
            JOptionPane.showMessageDialog(null, "NO podemos mostrar datos estadisticas y reportes debido a que no existen registros");

        }

    }

    public void impresionEncuesta(String evento) {
        ArrayList<Encuesta> listaEncuesta = new ArrayList<>();

        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        ObjectSet<Encuesta> result = null;
        Query query = base.query();
        query.constrain(Encuesta.class);

        if (evento.equalsIgnoreCase("Seleccione")) {

            result = base.queryByExample(Encuesta.class);
            if (!result.isEmpty()) {
                result.forEachRemaining(listaEncuesta::add);
            }

        } else {
            query.descend("evento").constrain(evento);
            result = query.execute();
            if (!result.isEmpty()) {
                result.forEachRemaining(listaEncuesta::add);
            }

        }

        base.close();
        System.out.println("Size ---> " + listaEncuesta.size());

        JRBeanArrayDataSource emptyDataSource = new JRBeanArrayDataSource(listaEncuesta.toArray());
        JRBeanArrayDataSource ds = new JRBeanArrayDataSource(listaEncuesta.toArray());

        cargarParametros("ReporteConsulta.jasper", ds, emptyDataSource);

    }

    public void cargarParametros(String rutaJasper, JRBeanArrayDataSource... ds) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource(rutaJasper));
            InputStream inputStreamImagen = new FileInputStream(new File("src/imagenes/lOGO1.png"));

            Map<String, Object> params = new HashMap<String, Object>();

            params.put("ds", ds[0]);
            params.put("image", inputStreamImagen);
            params.put("codigoOrganizador", organizador.getCod_organizador());
            params.put("cedulaOrganizador", organizador.getCedula());
            params.put("nombreOrganizador", organizador.getNombre() + " " + organizador.getApellido());
            params.put("emailOrganizador", organizador.getCorreo());

            JasperPrint jp = JasperFillManager.fillReport(jr, params, ds[1]);
            JasperViewer pv = new JasperViewer(jp, false);
            pv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            pv.setVisible(true);

        } catch (JRException ex) {
            System.out.println("NO SE ENCONTRO LA PLANTILLA RUTA NO ENCONTRADA ----> " + rutaJasper);
        } catch (FileNotFoundException ex) {
            System.out.println("NO SE ENCONTRO LA PLANTILLA RUTA NO ENCONTRADA DE LA IMAGEN");
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
    
    public void cargarParametrosOrganizador(String rutaJasper, JRBeanArrayDataSource... ds) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource(rutaJasper));
            InputStream inputStreamImagen = new FileInputStream(new File("src/imagenes/lOGO1.png"));

            Map<String, Object> params = new HashMap<String, Object>();

            params.put("ds", ds[0]);
            params.put("image", inputStreamImagen);

            JasperPrint jp = JasperFillManager.fillReport(jr, params, ds[1]);
            JasperViewer pv = new JasperViewer(jp, false);
            pv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            pv.setVisible(true);

        } catch (JRException ex) {
            System.out.println("NO SE ENCONTRO LA PLANTILLA RUTA NO ENCONTRADA");
        } catch (FileNotFoundException ex) {
            System.out.println("NO SE ENCONTRO LA PLANTILLA RUTA NO ENCONTRADA DE LA IMAGEN");
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}
