
package Interfases;

import Clases.Encuesta;
import Clases.Evento;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Encuesta_Pregunt extends javax.swing.JFrame {

    String encuesta = "", pregunta1 = "", pregunta2 = "", pregunta3 = "", pregunta4 = "", pregunta5 = "";
    private Evento eventoSeleccionado;

    public Encuesta_Pregunt(String codigo) {
        initComponents();
        obtenerPreguntas(codigo);
        

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

//    public void EventoSeleccionado(Evento evento) {
//        this.eventoSeleccionado = evento;
//        obtenerPreguntas(codigo);
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ENCUESTA");

        jLabel2.setText("PREGUNTA 1:");

        jLabel3.setText("PREGUNTA 2:");

        jLabel4.setText("PREGUNTA 3:");

        txt1.setText("jTextField1");

        txt2.setText("jTextField2");

        txt3.setText("jTextField3");

        jLabel5.setText("PREGUNTA 5:");

        txt5.setText("jTextField3");

        jLabel6.setText("PREGUNTA 4:");

        txt4.setText("jTextField3");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(717, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(78, 78, 78))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(260, 260, 260)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(84, 84, 84)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(84, 84, 84)
                            .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(84, 84, 84)
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(84, 84, 84)
                            .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(84, 84, 84)
                            .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jButton1)
                .addContainerGap(481, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(68, 68, 68)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(40, 40, 40)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel3))
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(46, 46, 46)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel4))
                        .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(36, 36, 36)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel6))
                        .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(46, 46, 46)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
    }//GEN-LAST:event_jButton1ActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    // End of variables declaration//GEN-END:variables
}
