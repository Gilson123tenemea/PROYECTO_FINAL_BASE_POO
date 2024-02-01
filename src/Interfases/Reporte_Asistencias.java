/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Asisitencia;
import Clases.Asistencia;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

public class Reporte_Asistencias extends javax.swing.JPanel {

     private DefaultTableModel modeloTabla;
    public Reporte_Asistencias() {
        initComponents();
        
    }

     private void cargarDatosEnTabla() {
       ObjectContainer base = Db4o.openFile(Inicio.direccion);
       DefaultTableModel model = (DefaultTableModel) Tablarepor.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        ObjectSet<Asistencia> result = base.queryByExample(new Asistencia());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        while (result.hasNext()) {
            Asistencia acti = result.next();
            Object[] row = {
                acti.getCod_aistencia(),
                acti.getCliente(),
                acti.getEvento()
                
            };
            model.addRow(row);
        }
        base.close();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tablarepor = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tablarepor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigon Asistencia", "Cliente", "Evento"
            }
        ));
        jScrollPane1.setViewportView(Tablarepor);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 930, 390));

        jButton1.setText("ACTUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      cargarDatosEnTabla(); 
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tablarepor;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
