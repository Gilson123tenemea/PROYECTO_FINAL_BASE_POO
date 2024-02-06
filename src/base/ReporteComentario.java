package base;

import Clases.Asistencia;
import Clases.Comentario;
import Clases.Encuesta;
import Clases.Evento;
import Clases.Patrocinador;
import Clases.Personal;
import Clases.Validaciones;
import Interfases.Inicio;
import Interfases.Login_Organizador;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ReporteComentario extends javax.swing.JPanel {
    
    ArrayList<Comentario> listaComentarios = new ArrayList<>();

    public ReporteComentario() {
        initComponents();
        init();
    }
    public void init() {
        cargarData();
        estadisticasFechaComentarios();
        estadisticasEventosMasComentados();
    }

    public void cargarData() {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        ObjectSet<Comentario> result = base.queryByExample(Comentario.class);
        
        if (!result.isEmpty()) {
            while (result.hasNext()) {
                Comentario comentario = result.next();
                listaComentarios.add(comentario);
            }
        }
        base.close();
    }
    
    
    public void estadisticasEventosMasComentados() {

        Map<String, Integer> asistencias = new TreeMap<>();
        
        for (Comentario comentario: listaComentarios) {
            if (comentario.getCodigo_evento()!= null) {
                asistencias.put(comentario.getCodigo_evento(), asistencias.getOrDefault(comentario.getCodigo_evento(), 0) + 1);
            }

        }
        

        DefaultPieDataset data = new DefaultPieDataset();
        List<Map.Entry<String, Integer>> listaEntradas = new ArrayList<>(asistencias.entrySet());

        listaEntradas.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Map.Entry<String, Integer>> topClientes = listaEntradas.subList(0, Math.min(3, listaEntradas.size()));

        System.out.println("Array size --> " + topClientes.size());
        // Mostrar los resultados de los top 5 clientes
        System.out.println("Top 5 Clientes con Mayor Demanda:");
        for (Map.Entry<String, Integer> entry : asistencias.entrySet()) {
            data.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart graficos_circular = ChartFactory.createPieChart(
                "", // title
                data, // fuente de datos 
                false, // nombre de las categorias 
                true, // herramientas  
                false // generacion de URL
        );

        PiePlot plot = (PiePlot) graficos_circular.getPlot();

        plot.setLabelBackgroundPaint(new Color(255, 255, 255)); // Color de fondo
        plot.setLabelOutlinePaint(Color.WHITE); // Color del borde
        plot.setLabelFont(new Font("Arial", Font.BOLD, 12)); // Fuente y tamaño
        plot.setLabelShadowPaint(null);

        graficos_circular.getPlot().setBackgroundPaint(new Color(255, 255, 255));
        graficos_circular.getPlot().setOutlinePaint(null);

        ChartPanel panel = new ChartPanel(graficos_circular);
        panel.setMouseWheelEnabled(false);
        panel.setPreferredSize(new Dimension(535, 371));
        panel.setBackground(new Color(250, 250, 250));

        pnGrAsistentes.setLayout(new BorderLayout());
        pnGrAsistentes.add(panel, BorderLayout.NORTH);
        
        repaint();
    }
    
    public void estadisticasFechaComentarios() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> descuentosPorMes = new TreeMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        Date fechaActual = new Date();

        for (Comentario comentario : listaComentarios) {
            Date fechaInicio = comentario.getFecha_comentario();
            String mesAnio = dateFormat.format(fechaInicio);

            descuentosPorMes.put(mesAnio, descuentosPorMes.getOrDefault(mesAnio, 0) + 1);

        }

        // Agregar los valores al dataset en el orden inverso
        for (Map.Entry<String, Integer> entry : descuentosPorMes.entrySet()) {
            dataset.addValue(entry.getValue(), "Encuestas", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Comentarios por Mes",
                "Mes",
                "Cantidad de Comentarios",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(853, 476));

        pnGrFecha.setLayout(new BorderLayout());
        pnGrFecha.add(chartPanel, BorderLayout.NORTH);

        repaint();
    }

    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnGrFecha = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnGrAsistentes = new javax.swing.JPanel();

        jTextField3.setText("jTextField3");

        setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(920, 895));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(920, 1308));

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 102, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Estadisticas de las Comentarios");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lOGO1.png"))); // NOI18N

        javax.swing.GroupLayout pnGrFechaLayout = new javax.swing.GroupLayout(pnGrFecha);
        pnGrFecha.setLayout(pnGrFechaLayout);
        pnGrFechaLayout.setHorizontalGroup(
            pnGrFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 853, Short.MAX_VALUE)
        );
        pnGrFechaLayout.setVerticalGroup(
            pnGrFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fechas donde los clientes mas han comentado");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Eventos mas comentados");

        javax.swing.GroupLayout pnGrAsistentesLayout = new javax.swing.GroupLayout(pnGrAsistentes);
        pnGrAsistentes.setLayout(pnGrAsistentesLayout);
        pnGrAsistentesLayout.setHorizontalGroup(
            pnGrAsistentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );
        pnGrAsistentesLayout.setVerticalGroup(
            pnGrAsistentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 371, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(pnGrAsistentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(169, 169, 169))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(pnGrFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnGrFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnGrAsistentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel pnGrAsistentes;
    private javax.swing.JPanel pnGrFecha;
    // End of variables declaration//GEN-END:variables
}
