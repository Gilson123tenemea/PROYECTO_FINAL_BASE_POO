/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Evento;
import static Interfases.Reporte_evento.Listaeventos;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo.User
 */
public class Reporte_eventos extends javax.swing.JPanel {

    /**
     * Creates new form Reporte_eventos
     */
    public Reporte_eventos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtconsulta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CODIGO", "NOMBRE", "DESCRIPCION", "TIPO DE EVENTO", "PATROCINADOR", "FECHA DE INICIO", "HORA DE INICIO", "FECHA FINAL", "HORA FINAL"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Ingrese el codigo del evento a buscar");

        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(570, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 227, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 1090, 400));

        jButton3.setText("VOLVER");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 137, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1206, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        Query query = base.query();
        query.constrain(Evento.class);
        query.descend("cod_evento").constrain(txtconsulta.getText().trim());

        ObjectSet<Evento> result = query.execute();

        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(null, "");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String[] columnNames = {"CODIGO", "NOMBRE", "DESCRIPCION", "TIPO DE EVENTO", "PATROCINADOR", "FECHA DE INICIO", "HORA DE INICIO", "FECHA FINAL", "HORA FINAL"};

        Object[][] data = new Object[result.size()][9];

        int i = 0;
        for (Evento even : result) {
            data[i][0] = even.getCod_evento();
            data[i][1] = even.getNombre();
            data[i][2] = even.getDescripcion();
            data[i][3] = even.getTipo();

            data[i][4] = even.getCodigo_patrocinador();
            data[i][5] = even.getFecha_inicio() != null ? sdf.format(even.getFecha_inicio()) : null;
            data[i][6] = even.getHora_inicio();
            data[i][7] = even.getFecha_fin() != null ? sdf.format(even.getFecha_fin()) : null;
            data[i][8] = even.getHora_fin();

            i++;
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTable1.setModel(model);

        base.close();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        Query query = base.query();
        query.constrain(Evento.class);
        query.descend("cod_evento").constrain(txtconsulta.getText().trim());

        ObjectSet<Evento> result = query.execute();

        String[] columnNames = {"CODIGO", "NOMBRE", "DESCRIPCION", "TIPO DE EVENTO", "PATROCINADOR", "FECHA DE INICIO", "HORA DE INICIO", "FECHA DE CLAUSURA", "HORA FINAL DEL EVENTO"};

        Object[][] data = new Object[result.size()][9];

        int i = 0;
        for (Evento even : result) {
            data[i][0] = even.getCod_evento();
            data[i][1] = even.getNombre();
            data[i][2] = even.getDescripcion();
            data[i][3] = even.getTipo();

            data[i][4] = even.getCodigo_patrocinador();
            data[i][5] = even.getFecha_inicio();
            data[i][6] = even.getHora_inicio();
            data[i][7] = even.getFecha_fin();
            data[i][8] = even.getHora_fin();

            i++;
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTable1.setModel(model);

        int resul = JOptionPane.showConfirmDialog(null, "Deseas eliminar algun evento", "Confirmacio", JOptionPane.YES_NO_OPTION);

        if (resul == JOptionPane.YES_OPTION) {
            for (Evento evento1 : result) {

                // Eliminar el agente de la base de datos db4o
                base.delete(evento1);
                JOptionPane.showMessageDialog(null, "Se estan borrando los datos del evento");

            }
        } else if (resul == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Nose ha eliminado el veneto");
        }

        txtconsulta.setText(" ");
        vaciarTabla();

        base.close();


    }//GEN-LAST:event_jButton1ActionPerformed

    public void vaciarTabla() {
        // Obtenemos el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

        // Borramos las filas antiguas del modelo de tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtconsulta;
    // End of variables declaration//GEN-END:variables
}
