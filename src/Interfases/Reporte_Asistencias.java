/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Asistencia;
import base.ReporteAsistencia;
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

public class Reporte_Asistencias extends javax.swing.JPanel {

    private TableRowSorter trs;
    private DefaultTableModel modeloTabla;

    public Reporte_Asistencias() {
        initComponents();
        cargarDatosEnTabla();

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

    public void Filtro() {

        if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Cliente")) {
            int Columnastabla = 1;
            trs.setRowFilter(RowFilter.regexFilter(txtconsulta.getText().trim(), Columnastabla));

        } else if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Evento")) {
            int Columnastabla = 2;
            trs.setRowFilter(RowFilter.regexFilter(txtconsulta.getText().trim(), Columnastabla));

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tablarepor = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbxbusqueda = new javax.swing.JComboBox<>();
        txtconsulta = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

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

        jLabel1.setText("REPORTE DE LAS ASISTENCIAS");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        cbxbusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Cliente", "Evento" }));
        add(cbxbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, -1, -1));

        txtconsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtconsultaKeyTyped(evt);
            }
        });
        add(txtconsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 120, -1));

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
        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 560, -1, -1));
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

        } else if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Evento")) {
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

        trs = new TableRowSorter(Tablarepor.getModel());
        Tablarepor.setRowSorter(trs);
    }//GEN-LAST:event_txtconsultaKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        ReporteAsistencia reporte = new ReporteAsistencia();
        JPanel contenedorPrincipal = (JPanel) this.getParent();
        contenedorPrincipal.add(reporte, "reporte");
        
        CardLayout vista;
        vista = (CardLayout) contenedorPrincipal.getLayout();
        vista.show(contenedorPrincipal, "reporte");
        SwingUtilities.updateComponentTreeUI(contenedorPrincipal);
        contenedorPrincipal.repaint();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tablarepor;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxbusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtconsulta;
    // End of variables declaration//GEN-END:variables
}
