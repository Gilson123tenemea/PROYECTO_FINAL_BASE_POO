package Interfases;

import Clases.Calificar_evento;
import Clases.Encuesta;
import Clases.Evento;
import Clases.RespuestasEncuesta;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;


/**
 *
 * @author HP
 */
public class Encuesta_Pregunt extends javax.swing.JFrame {

    String encuesta = "", pregunta1 = "", pregunta2 = "", pregunta3 = "", pregunta4 = "", pregunta5 = "";

    String cod = " ";

    public Encuesta_Pregunt(String codigo) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.cod = codigo;
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
            

            if (!validarRadioButton(SI1, NO1)) {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una opción en la Pregunta 1", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!validarRadioButton(SI2, NO2)) {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una opción en la Pregunta 2", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!validarRadioButton(SI3, NO3)) {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una opción en la Pregunta 3", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!validarRadioButton(SI4, NO4)) {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una opción en la Pregunta 4", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!validarRadioButton(SI5, NO5)) {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una opción en la Pregunta 5", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


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
            
             ObjectSet<RespuestasEncuesta> resul = base.queryByExample(new RespuestasEncuesta(null,txtCedula.getText().trim(), null,null,null,null,null, null));

            if (!resul.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ya ha respondido esta encuesta.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            RespuestasEncuesta respuestas = new RespuestasEncuesta(nuevoCodigo, txtCedula.getText().trim(), cod, res1, res2, res3, res4, res5);
            base.store(respuestas);

            JOptionPane.showMessageDialog(this, "Encuesta creada exitosamente");
            jButton1.setEnabled(false);
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

    private boolean validarRadioButton(JRadioButton... buttons) {
        for (JRadioButton button : buttons) {
            if (button.isSelected()) {
                return true;
            }
        }
        return false;
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 12, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setText("Pregunta 1:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        txt1.setEditable(false);
        txt1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jPanel1.add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 498, -1));

        txt2.setEditable(false);
        txt2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jPanel1.add(txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 498, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setText("Pregunta 2:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, -1));

        txt3.setEditable(false);
        txt3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt3ActionPerformed(evt);
            }
        });
        jPanel1.add(txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 498, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setText("Pregunta 3:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setText("Pregunta 4:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, -1, -1));

        txt4.setEditable(false);
        txt4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jPanel1.add(txt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, 500, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setText("Pregunta 5:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, -1, 20));

        txt5.setEditable(false);
        txt5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt5ActionPerformed(evt);
            }
        });
        jPanel1.add(txt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, 500, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/inmigracion.png"))); // NOI18N
        jLabel1.setText("ENCUESTA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        SI1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        SI1.setText("SI");
        jPanel1.add(SI1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, -1, -1));

        NO1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        NO1.setText("NO");
        jPanel1.add(NO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));

        SI2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        SI2.setText("SI");
        jPanel1.add(SI2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, -1, -1));

        NO2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        NO2.setText("NO");
        jPanel1.add(NO2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, -1, -1));

        SI3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        SI3.setText("SI");
        jPanel1.add(SI3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, -1, -1));

        NO3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        NO3.setText("NO");
        jPanel1.add(NO3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, -1, -1));

        SI4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        SI4.setText("SI");
        jPanel1.add(SI4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, -1));

        NO4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        NO4.setText("NO");
        jPanel1.add(NO4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, -1, -1));

        SI5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        SI5.setText("SI");
        jPanel1.add(SI5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, -1, -1));

        NO5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        NO5.setText("NO");
        jPanel1.add(NO5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 500, -1, -1));
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, 120, 20));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disco-flexible.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 560, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salida (2).png"))); // NOI18N
        jButton2.setToolTipText("DA CLICK PARA SALIR AL MENÚ CLIENTE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo Oficila de Eventos.PNG"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pregunta.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/charlar.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 310, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pregunta (1).png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 510, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/charlar (1).png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 410, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/hablando.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 220, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
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
        Menu_Cliente men = new Menu_Cliente();
        men.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt3ActionPerformed

    private void txt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt5ActionPerformed


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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JLabel txtCedula;
    // End of variables declaration//GEN-END:variables
}
