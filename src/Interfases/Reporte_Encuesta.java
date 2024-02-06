/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Encuesta;
import Clases.RespuestasEncuesta;
import base.ImpresionDeReportes;
import base.ReporteEncuesta;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
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
public class Reporte_Encuesta extends javax.swing.JPanel {
 private TableRowSorter trs;
    String encuesta = "", pregunta1 = "", pregunta2 = "", pregunta3 = "", pregunta4 = "", pregunta5 = "";
    private final int indiceColumnaCodigoEvento = 2;

    public Reporte_Encuesta() {
        initComponents();
        cargarDatosEnTabla();
    }

    private void cargarDatosEnTabla() {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        DefaultTableModel model = (DefaultTableModel) tablares.getModel();
        model.setRowCount(0);

        ObjectSet<RespuestasEncuesta> result = base.queryByExample(new RespuestasEncuesta(null, null, null, null, null, null, null, null));

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

    public void mostrarPreguntas(String codigo) {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        Query encuestaQuery = base.query();
        encuestaQuery.constrain(Encuesta.class);
        encuestaQuery.descend("evento").constrain(codigo);
        ObjectSet<Encuesta> encuestaResult = encuestaQuery.execute();

        if (!encuestaResult.isEmpty()) {
            Encuesta encuesta = encuestaResult.next();

            pregunta1 = encuesta.getPregunta1();
            pregunta2 = encuesta.getPregunta2();
            pregunta3 = encuesta.getPregunta3();
            pregunta4 = encuesta.getPregunta4();
            pregunta5 = encuesta.getPregunta5();

            String mensaje = "Preguntas de la encuesta:\n\n";
            mensaje += "1. " + pregunta1 + "\n";
            mensaje += "2. " + pregunta2 + "\n";
            mensaje += "3. " + pregunta3 + "\n";
            mensaje += "4. " + pregunta4 + "\n";
            mensaje += "5. " + pregunta5;

            JOptionPane.showMessageDialog(null, mensaje, "Preguntas de la Encuesta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay encuesta asociada al evento.", "Sin Encuesta", JOptionPane.WARNING_MESSAGE);
        }

        base.close();
    }

    public void mostrarPreguntasSeleccionada() {
        int filaSeleccionada = tablares.getSelectedRow();
        if (filaSeleccionada != -1) {
            String codigoEvento = (String) tablares.getValueAt(filaSeleccionada, indiceColumnaCodigoEvento);
            mostrarPreguntas(codigoEvento);
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una fila para ver las preguntas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void Filtro() {

        if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Cedula")) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablares = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtconsulta = new javax.swing.JTextField();
        cbxbusqueda = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 12, true));
        jPanel1.setMinimumSize(new java.awt.Dimension(923, 638));
        jPanel1.setPreferredSize(new java.awt.Dimension(923, 638));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("REPORTE DE ENCUESTA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 860, 320));

        jButton1.setText("Ver eventos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

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
        jPanel1.add(txtconsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 140, -1));

        cbxbusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Cedula", "Evento" }));
        jPanel1.add(cbxbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 130, -1));

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
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 560, -1, -1));

        jLabel2.setText("Busca por:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo Oficila de Eventos.PNG"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mostrarPreguntasSeleccionada();


    }//GEN-LAST:event_jButton1ActionPerformed

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

        trs = new TableRowSorter(tablares.getModel());
        tablares.setRowSorter(trs);
    }//GEN-LAST:event_txtconsultaKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        ReporteEncuesta reporte = new ReporteEncuesta();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxbusqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablares;
    private javax.swing.JTextField txtconsulta;
    // End of variables declaration//GEN-END:variables
}
