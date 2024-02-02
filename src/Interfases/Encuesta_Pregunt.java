package Interfases;

import Clases.Encuesta;
import Clases.Evento;
import Clases.RespuestasEncuesta;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Encuesta_Pregunt extends javax.swing.JFrame {

    String encuesta = "", pregunta1 = "", pregunta2 = "", pregunta3 = "", pregunta4 = "", pregunta5 = "";

    String cod=" ";
    public Encuesta_Pregunt(String codigo) {
        initComponents();
        this.cod=codigo;
        txtCedula.setText(Inicio.cedula);
        obtenerPreguntas(codigo);
        Agrupar1();
        Agrupar2();
        Agrupar3();
        Agrupar4();
        Agrupar5();

    }

    public void obtenerPreguntas(String codigo) {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        // Obtener la encuesta asociada al evento
        Query encuestaQuery = base.query();
        encuestaQuery.constrain(Encuesta.class);
        encuestaQuery.descend("evento").constrain(codigo);
        ObjectSet<Encuesta> encuestaResult = encuestaQuery.execute();

        if (!encuestaResult.isEmpty()) {
            Encuesta encuesta = encuestaResult.next();

            // Obtener las preguntas y hacer algo con ellas
            pregunta1 = encuesta.getPregunta1();
            pregunta2 = encuesta.getPregunta2();
            pregunta3 = encuesta.getPregunta3();
            pregunta4 = encuesta.getPregunta4();
            pregunta5 = encuesta.getPregunta5();

            // Haz algo con las preguntas (asignarlas a los TextView, por ejemplo)
            txt1.setText(pregunta1);
            txt2.setText(pregunta2);
            txt3.setText(pregunta3);
            txt4.setText(pregunta4);
            txt5.setText(pregunta5);
        } else {
            System.out.println("No hay encuesta asociada al evento.");
        }

        base.close();
    }

    public void Agrupar1() {
        ButtonGroup mibuton = new ButtonGroup();
        mibuton.add(SI1);
        mibuton.add(NO1);
    }

    public void Agrupar2() {
        ButtonGroup mibuton = new ButtonGroup();
        mibuton.add(SI2);
        mibuton.add(NO2);
    }

    public void Agrupar3() {
        ButtonGroup mibuton = new ButtonGroup();
        mibuton.add(SI3);
        mibuton.add(NO3);
    }

    public void Agrupar4() {
        ButtonGroup mibuton = new ButtonGroup();
        mibuton.add(SI4);
        mibuton.add(NO4);
    }

    public void Agrupar5() {
        ButtonGroup mibuton = new ButtonGroup();
        mibuton.add(SI5);
        mibuton.add(NO5);
    }

    public void crearRespuestas(ObjectContainer base) {
        try {
            
            System.out.println(cod);
            ObjectSet<RespuestasEncuesta> result = base.queryByExample(new RespuestasEncuesta(null, null, null, null, null, null, null, null));
            Query query = base.query();
            query.constrain(RespuestasEncuesta.class);
            query.descend("Codigo_puesto").orderDescending();

            int ultimoCodigo = 1;
            if (!result.isEmpty()) {
                RespuestasEncuesta ultimoPuesto = result.next();
                ultimoCodigo = Integer.parseInt(ultimoPuesto.getCod_Respu().substring(4)) + 1;
            }

            String nuevoCodigo = String.format("ENC-%03d", ultimoCodigo);

            result = base.queryByExample(new RespuestasEncuesta(nuevoCodigo, null, null, null, null, null, null, null));

            if (!result.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ya existe las respuestas de la encuesta con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String res1 = " ";
            if (SI1.isSelected()) {
                res1 = "SI";
            } else if (NO1.isSelected()) {
                res1 = "NO";
            }

            String res2 = " ";
            if (SI2.isSelected()) {
                res2 = "SI";
            } else if (NO2.isSelected()) {
                res2 = "NO";
            }

            String res3 = " ";
            if (SI3.isSelected()) {
                res3 = "SI";
            } else if (NO3.isSelected()) {
                res3 = "NO";
            }

            String res4 = " ";
            if (SI4.isSelected()) {
                res4 = "SI";
            } else if (NO4.isSelected()) {
                res4 = "NO";
            }

            String res5 = " ";
            if (SI5.isSelected()) {
                res5 = "SI";
            } else if (NO5.isSelected()) {
                res5 = "NO";
            }

            RespuestasEncuesta respuestas = new RespuestasEncuesta(nuevoCodigo, txtCedula.getText().trim(), cod, res1, res2, res3, res4, res5);
            base.store(respuestas);

            JOptionPane.showMessageDialog(this, "Puesto creado exitosamente");
            limpiar();

        } finally {
            base.close();
        }

    }

    public void limpiar() {
        SI1.setSelected(false);
        NO1.setSelected(false);
        SI2.setSelected(false);
        NO2.setSelected(false);
        SI3.setSelected(false);
        NO3.setSelected(false);
        SI4.setSelected(false);
        NO4.setSelected(false);
        SI5.setSelected(false);
        NO5.setSelected(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        SI1 = new javax.swing.JRadioButton();
        NO1 = new javax.swing.JRadioButton();
        SI2 = new javax.swing.JRadioButton();
        NO2 = new javax.swing.JRadioButton();
        SI3 = new javax.swing.JRadioButton();
        NO3 = new javax.swing.JRadioButton();
        SI4 = new javax.swing.JRadioButton();
        NO4 = new javax.swing.JRadioButton();
        SI5 = new javax.swing.JRadioButton();
        NO5 = new javax.swing.JRadioButton();
        txtCedula = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("PREGUNTA 1:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        txt1.setText("jTextField1");
        jPanel1.add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 498, -1));

        txt2.setText("jTextField2");
        jPanel1.add(txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 498, -1));

        jLabel3.setText("PREGUNTA 2:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        txt3.setText("jTextField3");
        jPanel1.add(txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 498, -1));

        jLabel4.setText("PREGUNTA 3:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));

        jLabel6.setText("PREGUNTA 4:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, -1, -1));

        txt4.setText("jTextField3");
        jPanel1.add(txt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 500, -1));

        jLabel5.setText("PREGUNTA 5:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        txt5.setText("jTextField3");
        jPanel1.add(txt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 500, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ENCUESTA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        SI1.setText("SI");
        jPanel1.add(SI1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        NO1.setText("NO");
        jPanel1.add(NO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        SI2.setText("SI");
        jPanel1.add(SI2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));

        NO2.setText("NO");
        jPanel1.add(NO2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, -1));

        SI3.setText("SI");
        jPanel1.add(SI3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, -1, -1));

        NO3.setText("NO");
        jPanel1.add(NO3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, -1, -1));

        SI4.setText("SI");
        jPanel1.add(SI4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, -1, -1));

        NO4.setText("NO");
        jPanel1.add(NO4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, -1, -1));

        SI5.setText("SI");
        jPanel1.add(SI5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, -1, -1));

        NO5.setText("NO");
        jPanel1.add(NO5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 480, -1, -1));
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 120, 20));

        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 550, -1, -1));

        jButton2.setText("REGRESAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        crearRespuestas(base);
        base.close();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        Menu_Cliente men =new Menu_Cliente();
        men.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton NO1;
    private javax.swing.JRadioButton NO2;
    private javax.swing.JRadioButton NO3;
    private javax.swing.JRadioButton NO4;
    private javax.swing.JRadioButton NO5;
    private javax.swing.JRadioButton SI1;
    private javax.swing.JRadioButton SI2;
    private javax.swing.JRadioButton SI3;
    private javax.swing.JRadioButton SI4;
    private javax.swing.JRadioButton SI5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JLabel txtCedula;
    // End of variables declaration//GEN-END:variables
}
