/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.RespuestasEncuesta;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN_01
 */
public class Reporte_Encuesta extends javax.swing.JPanel {

    /**
     * Creates new form Reporte_Encuesta
     */
    public Reporte_Encuesta() {
        initComponents();
        cargarDatosEnTabla();
    }

    private void cargarDatosEnTabla() {
       ObjectContainer base = Db4o.openFile(Inicio.direccion);
       DefaultTableModel model = (DefaultTableModel) tablares.getModel();
        model.setRowCount(0); 

        ObjectSet<RespuestasEncuesta> result = base.queryByExample(new RespuestasEncuesta(null,null,null,null,null,null,null,null));

        while (result.hasNext()) {
            RespuestasEncuesta acti = result.next();
            Object[] row = {
                acti.getCod_Respu(),
                acti.getCliente(),
                acti.getEvento(),
                acti.getRes1(),
                acti.getRes2(),
                acti.getRes3(),
                acti.getRes4(),
                 acti.getRes5()
                
            };
            model.addRow(row);
        }
        base.close();

    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablares = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("REPORTE DE ENCUESTA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 44, -1, -1));

        tablares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cod_Encuesta", "Cedula", "Evento", "¿Pregunta 1?", "¿Pregunta 2?", "¿Pregunta 3?", "¿Pregunta 4?", "¿Pregunta 5?"
            }
        ));
        jScrollPane1.setViewportView(tablares);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 830, 240));

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablares;
    // End of variables declaration//GEN-END:variables
}
