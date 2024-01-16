/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Comerciantes;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN_01
 */
public class Crud_Comerciante extends javax.swing.JPanel {

    /**
     * Creates new form Crud_Comerciante
     */
    public Crud_Comerciante() {
        initComponents();
    }

     private void cargarTabla(ObjectContainer base) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    ObjectSet<Comerciantes> result = base.queryByExample(new Comerciantes());

    while (result.hasNext()) {
        Comerciantes comerciante = result.next();

        Object[] row = {
            comerciante.getCedula(),
            comerciante.getApellido(),
            comerciante.getNombre(),
            comerciante.getCorreo(),
            comerciante.getTelefono(),
            comerciante.getDireccion(),
            comerciante.getTipo_Comercio(),
            comerciante.getGenero(),
            comerciante.getFecchaNaci(),
            comerciante.getCodigo_participante(),
            comerciante.getProductos_c(),
            comerciante.getServicio_c(),
            comerciante.getCodigo_puesto(),
            comerciante.getCelular()
        };
        model.addRow(row);
    }
}
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        REPORTE = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("REGUISTRO DEL COMERCIANTE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 24, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 220, 10));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cedula", "Nombre", "Apellido", "Email", "Telefono", "Direccion", "Tipo.Comercio", "Genero", "F.Nacimiento", "Codigo.Comerciente", "Productos", "Servicios", "Codigo.Puesto", "Celular"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 890, 210));

        REPORTE.setText("REPORTE");
        REPORTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REPORTEActionPerformed(evt);
            }
        });
        jPanel1.add(REPORTE, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, -1, -1));

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

    private void REPORTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REPORTEActionPerformed

        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        cargarTabla(base);
        base.close();

    }//GEN-LAST:event_REPORTEActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton REPORTE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
