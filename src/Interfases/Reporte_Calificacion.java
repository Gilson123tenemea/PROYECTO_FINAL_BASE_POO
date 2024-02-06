/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Calificar_evento;
import base.ReporteCalificacion;
import base.ReportePatrocinador;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.awt.CardLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ADMIN_01
 */
public class Reporte_Calificacion extends javax.swing.JPanel {

    private TableRowSorter trs;

    public Reporte_Calificacion() {
        initComponents();

        cargarCalificacionesEnTabla();
    }

    private void cargarCalificacionesEnTabla() {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        Query query = base.query();
        query.constrain(Calificar_evento.class);
        ObjectSet<Calificar_evento> result = query.execute();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        while (result.hasNext()) {
            Calificar_evento calificacion = result.next();
            Object[] row = {
                calificacion.getCod_calificacion(),
                calificacion.getCod_evento(), // Código del evento
                calificacion.getValoracion(),
                calificacion.getCod_cliente(),
                calificacion.getFecha_calificacion() != null ? sdf.format(calificacion.getFecha_calificacion()) : null// Fecha de calificación
            };
            model.addRow(row);
        }
        base.close();
    }

    public void Filtro() {

        if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Cedula")) {
            int Columnastabla = 3;
            trs.setRowFilter(RowFilter.regexFilter(txtconsulta.getText().trim(), Columnastabla));

        } else if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Valoracion")) {
            int Columnastabla = 2;
            trs.setRowFilter(RowFilter.regexFilter(txtconsulta.getText().trim(), Columnastabla));

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cbxbusqueda = new javax.swing.JComboBox<>();
        txtconsulta = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 12, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Calificacion", "Evento", "Valoracion", "Cedula", "Fecha"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 880, 320));

        cbxbusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Cedula", "Valoracion" }));
        cbxbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxbusquedaActionPerformed(evt);
            }
        });
        jPanel1.add(cbxbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 120, -1));

        txtconsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtconsultaActionPerformed(evt);
            }
        });
        txtconsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtconsultaKeyTyped(evt);
            }
        });
        jPanel1.add(txtconsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 100, -1));

        btnGuardar.setBackground(new java.awt.Color(51, 204, 0));
        btnGuardar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disco-flexible.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setIconTextGap(8);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 570, -1, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("REPORTE DE CALIFICACIONES ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        jLabel2.setText("Buscar por:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo Oficila de Eventos.PNG"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, -1, -1));

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

    private void txtconsultaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtconsultaKeyTyped
        if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Cedula")) {

            txtconsulta.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (txtconsulta.getText());

                    txtconsulta.setText(cadena);
                    Filtro();

                }

            });

        } else if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Valoracion")) {
            txtconsulta.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (txtconsulta.getText());

                    txtconsulta.setText(cadena);
                    Filtro();

                }

            });

        } else if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Seleccione")) {
            JOptionPane.showMessageDialog(null, "Debe escoger mediante que campo desea ver los registros");
        }

        trs = new TableRowSorter(jTable1.getModel());
        jTable1.setRowSorter(trs);
    }//GEN-LAST:event_txtconsultaKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        ReporteCalificacion reporte = new ReporteCalificacion();
        JPanel contenedorPrincipal = (JPanel) this.getParent();
        contenedorPrincipal.add(reporte, "reporte");
        
        CardLayout vista;
        vista = (CardLayout) contenedorPrincipal.getLayout();
        vista.show(contenedorPrincipal, "reporte");
        SwingUtilities.updateComponentTreeUI(contenedorPrincipal);
        contenedorPrincipal.repaint();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtconsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtconsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtconsultaActionPerformed

    private void cbxbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxbusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxbusquedaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxbusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtconsulta;
    // End of variables declaration//GEN-END:variables
}
