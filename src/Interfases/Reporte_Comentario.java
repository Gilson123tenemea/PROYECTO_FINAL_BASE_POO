/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Comentario;
import base.ReporteCalificacion;
import base.ReporteComentario;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.awt.CardLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
public class Reporte_Comentario extends javax.swing.JPanel {

    private TableRowSorter trs;
    public Reporte_Comentario() {
        initComponents();
        cargarDatosEnTabla();
    }

    private void cargarDatosEnTabla() {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        ObjectSet<Comentario> result = base.queryByExample(new Comentario());

        while (result.hasNext()) {
            Comentario comentario = result.next();
            Object[] row = {
                comentario.getCodigo_publico(),
                comentario.getCliente(),
                comentario.getCodigo_evento(),
                comentario.getComentario()
            };
            model.addRow(row);
        }
        base.close();
    }
    
    public void Filtro() {

        if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Cliente")) {
            int Columnastabla = 1;
            trs.setRowFilter(RowFilter.regexFilter(txtconsulta.getText().trim(), Columnastabla));

        } else if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Comentario")) {
            int Columnastabla = 3;
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Cliente", "Evento", "Comentario"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 820, 230));

        cbxbusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Nombre y Apellido", "Comentario" }));
        jPanel1.add(cbxbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 100, -1));

        txtconsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtconsultaKeyTyped(evt);
            }
        });
        jPanel1.add(txtconsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 120, -1));

        btnGuardar.setBackground(new java.awt.Color(51, 204, 0));
        btnGuardar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/crear.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setIconTextGap(8);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, -1, -1));

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
          if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Cliente")) {

            txtconsulta.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (txtconsulta.getText());

                    txtconsulta.setText(cadena);
                    Filtro();

                }

            });

        } else if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Comentario")) {
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
        ReporteComentario reporte = new ReporteComentario();
        JPanel contenedorPrincipal = (JPanel) this.getParent();
        contenedorPrincipal.add(reporte, "reporte");

        CardLayout vista;
        vista = (CardLayout) contenedorPrincipal.getLayout();
        vista.show(contenedorPrincipal, "reporte");
        SwingUtilities.updateComponentTreeUI(contenedorPrincipal);
        contenedorPrincipal.repaint();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxbusqueda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtconsulta;
    // End of variables declaration//GEN-END:variables
}
