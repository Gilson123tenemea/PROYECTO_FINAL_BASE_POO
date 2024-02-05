/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Encuesta;
import Clases.Evento;
import Clases.Organizador;
import Clases.Personal;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eliza
 */
public class EliminarOrganizador extends javax.swing.JPanel {

    /**
     * Creates new form EliminarOrganizador
     */
    public EliminarOrganizador() {
        initComponents();
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        cargarTabla(base);

        base.close();
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
        jTableDatos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnEliminar = new javax.swing.JButton();
        btnCargarTabla = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 12, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTableDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Cédula", "Nombre", "Apellido", "Celular", "Teléfono", "Dirección", "Género", "Correo", "Presupuesto", "Fecha de nacimiento"
            }
        ));
        jScrollPane1.setViewportView(jTableDatos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 114, 910, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/despedido.png"))); // NOI18N
        jLabel1.setText("Eliminar Organizador");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(0, 153, 153));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 910, -1));

        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/expediente.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 78, -1, -1));

        btnCargarTabla.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnCargarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dispositivo.png"))); // NOI18N
        btnCargarTabla.setText("Cargar Tabla");
        btnCargarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTablaActionPerformed(evt);
            }
        });
        jPanel1.add(btnCargarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 78, -1, -1));

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

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        String codEliminar = JOptionPane.showInputDialog("Ingrese el código del Organizador");
        boolean encontrado = false;

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        try {

            Evento eventoAsociado = new Evento(null, null, null, null, null, null, null, null, null, null, null, 0.0, 0, codEliminar,null,null,null);
            ObjectSet resultadoEvento = base.get(eventoAsociado);

            if (resultadoEvento.size() > 0) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar este Organizador porque está asociado a un Evento", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Encuesta encuestaAsociada = new Encuesta(null, null, null, null, null, null, null, null, null, null, null, codEliminar);
            ObjectSet resultadoEncuesta = base.get(encuestaAsociada);

            if (resultadoEncuesta.size() > 0) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar a este Organizador porque está asociado a una Encuesta", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Personal personalAsociado = new Personal(null, null, null, null, null, null, null, null, null, codEliminar, null, null, null, null, null, null, null, null, null);
            ObjectSet resultadoPersonal = base.get(personalAsociado);

            if (resultadoPersonal.size() > 0) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar a este Organizador porque está asociado a un Personal", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Query query = base.query();
            query.constrain(Organizador.class);
            query.descend("cod_organizador").constrain(codEliminar);
            ObjectSet<Organizador> resultadoOrganizador = query.execute();

            cargarTabla(base);

            if (resultadoOrganizador.size() > 0) {
                encontrado = true;
                int resul = JOptionPane.showConfirmDialog(null, "Deseas eliminar los datos del Organizador", "Confirmacion", JOptionPane.YES_NO_OPTION);

                if (resul == JOptionPane.YES_OPTION) {
                    for (Organizador org : resultadoOrganizador) {
                        base.delete(org);
                        JOptionPane.showMessageDialog(null, "Se están borrando los datos del Organizador");
                        cargarTabla(base);
                    }
                } else if (resul == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Datos del Organizador no eliminados");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el código");
                cargarTabla(base);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Manejar la excepción de manera adecuada
        } finally {
            base.close();
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarTablaActionPerformed
        // TODO add your handling code here:
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        cargarTabla(base);

        base.close();
    }//GEN-LAST:event_btnCargarTablaActionPerformed

    public void vaciarTabla() {
        // Obtenemos el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) jTableDatos.getModel();

        // Borramos las filas antiguas del modelo de tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarTabla;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableDatos;
    // End of variables declaration//GEN-END:variables
private void cargarTabla(ObjectContainer base) {
        DefaultTableModel model = (DefaultTableModel) jTableDatos.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ObjectSet<Organizador> result = base.queryByExample(Organizador.class);

        while (result.hasNext()) {
            Organizador organizador = result.next();

            Object[] row = {
                organizador.getCod_organizador(),
                organizador.getCedula(),
                organizador.getNombre(),
                organizador.getApellido(),
                organizador.getCelular(),
                organizador.getTelefono(),
                organizador.getDireccion(),
                organizador.getGenero(),
                organizador.getCorreo(),
                organizador.getPresupuesto(),
                organizador.getFecchaNaci() != null ? sdf.format(organizador.getFecchaNaci()) : null
            };
            model.addRow(row);
        }
    }

}
