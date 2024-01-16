/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Puesto;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eliza
 */
public class Crud_Puestos extends javax.swing.JPanel {

    public static ArrayList<Puesto> listapuesto = new ArrayList<>();

    public static ArrayList<Puesto> codigoseliminados = new ArrayList<>();

    public Crud_Puestos() {
        initComponents();
    }

    public void crearPuestos(ObjectContainer Base) {
        // Verificar si todos los campos están llenos
        if (txtnombrepuesto.getText().trim().isEmpty() || txttipopuesto.getText().trim().isEmpty() || txtdescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos antes de ingresar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Obtener el último código de puesto en la base de datos
            ObjectSet<Puesto> resul = Base.queryByExample(new Puesto(null, null, null, null));
            int ultimoCodigo = resul.size() + 1;

            // Formatear el código con ceros a la izquierda
           String cod = "PUE-" + String.format("%03d", ultimoCodigo);
            lblcod.setText(cod);

            // Verificar si ya existe un Puesto con el mismo código
            resul = Base.queryByExample(new Puesto(cod, null, null, null));

            if (!resul.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ya existe un Puesto con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

//                public Puesto(String Codigo_puesto, String NombrePuesto, String DescripcionPuesto, String tipo_puesto) {
            // Crear objeto Puesto y almacenar en la base de datos
            Puesto puesto1 = new Puesto(cod, txtnombrepuesto.getText().trim(), txtdescripcion.getText().trim(), txttipopuesto.getText().trim());

            Base.store(puesto1);

            cargarTabla(Base);

            JOptionPane.showMessageDialog(this, "Puesto Creado exitosamente");
        } finally {

            Base.close();

        }

        // Limpiar los campos después de almacenar
        txtnombrepuesto.setText("");
        txttipopuesto.setText("");
        txtdescripcion.setText("");
    }

    public void modificarPuesto(ObjectContainer base) {
        int filaSeleccionada = tablapuesto.getSelectedRow();

        if (filaSeleccionada != -1) {

            String codigoModificar = tablapuesto.getValueAt(filaSeleccionada, 0).toString();

            try {

                Query query = base.query();
                query.constrain(Puesto.class);
                query.descend("Codigo_puesto").constrain(codigoModificar);
                ObjectSet<Puesto> result = query.execute();

                if (!result.isEmpty()) {

                    Puesto puesto = result.get(0);

                    lblcod.setEnabled(false);
                    lblcod.setText(puesto.getCodigo_puesto());
                    txtnombrepuesto.setText(puesto.getNombrePuesto());
                    txttipopuesto.setText(puesto.getTipo_puesto());
                    txtdescripcion.setText(puesto.getDescripcionPuesto());
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró del Puesto en la base de datos.");
                }
            } finally {
                // No cierres la base de datos aquí; déjalo abierto para que puedas usarlo en el método que llama a modificarDepartamento
            }
        } else {
            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila.");
        }
    }

    public void confirmarModificacion(ObjectContainer base) {
        int filaSeleccionada = tablapuesto.getSelectedRow();

        if (filaSeleccionada != -1) {
            String codigoModificar = tablapuesto.getValueAt(filaSeleccionada, 0).toString();

            try {
                Query query = base.query();
                query.constrain(Puesto.class);
                query.descend("Codigo_puesto").constrain(codigoModificar);
                ObjectSet<Puesto> result = query.execute();

                if (!result.isEmpty()) {
                    Puesto puesto = result.get(0);

                    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de modificar el Puesto?", "Confirmación", JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        // Modificar los datos del departamento en la base de datos
                        lblcod.setEnabled(false);
                        puesto.setNombrePuesto(txtnombrepuesto.getText());
                        puesto.setTipo_puesto(txttipopuesto.getText());
                        puesto.setDescripcionPuesto(txtdescripcion.getText());

                        base.store(puesto);

                        JOptionPane.showMessageDialog(null, "Puesto modificado correctamente");
                        RefrescarTabla(base);
                        limpiar();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el Puesto en la base de datos.");
                }
            } finally {
                // No cierres la base de datos aquí; déjalo abierto para que puedas usarlo en otras partes del código
            }
        } else {
            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila.");
        }
    }

    public void RefrescarTabla(ObjectContainer base) {
        DefaultTableModel model = (DefaultTableModel) tablapuesto.getModel();
        model.setRowCount(0);

        Query query = base.query();
        query.constrain(Puesto.class);
        ObjectSet<Puesto> result = query.execute();

        for (Puesto puesto : result) {

            Object[] a = new Object[4];

            a[0] = puesto.getCodigo_puesto();
            a[1] = puesto.getNombrePuesto();
            a[2] = puesto.getTipo_puesto();
            a[3] = puesto.getDescripcionPuesto();

            model.addRow(a);
        }

    }

    public void limpiar() {
        txtnombrepuesto.setText("");
        txttipopuesto.setText("");
        txtdescripcion.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnombrepuesto = new javax.swing.JTextField();
        txttipopuesto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablapuesto = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblcod = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cabina.png"))); // NOI18N
        jLabel1.setText("PUESTO");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setText("Código Puesto:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setText("Nombre de Puesto:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setText("Tipo de Puesto: ");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setText("Descripción:");

        txtnombrepuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombrepuestoActionPerformed(evt);
            }
        });

        txttipopuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttipopuestoActionPerformed(evt);
            }
        });

        txtdescripcion.setColumns(20);
        txtdescripcion.setRows(5);
        jScrollPane1.setViewportView(txtdescripcion);

        tablapuesto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CODIGO", "NOMBRE", "TIPO DE PUESTO", "DESCRIPCION"
            }
        ));
        jScrollPane2.setViewportView(tablapuesto);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disco-flexible.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar (1).png"))); // NOI18N
        jButton2.setText("MODIFICAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-eliminar (1).png"))); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/curriculum.png"))); // NOI18N
        jButton4.setText("REPORTE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lOGO1.png"))); // NOI18N

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/confirmacion.png"))); // NOI18N
        jButton5.setText("Confirmar Modificar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtnombrepuesto)
                                                .addComponent(txttipopuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                            .addComponent(lblcod, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(200, 200, 200))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(33, 33, 33)
                                .addComponent(jButton3)
                                .addGap(27, 27, 27)
                                .addComponent(jButton4)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5)))
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(168, 168, 168)
                        .addComponent(jLabel6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblcod, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnombrepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txttipopuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );

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

    private void txtnombrepuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombrepuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombrepuestoActionPerformed

    private void txttipopuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttipopuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttipopuestoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        crearPuestos(base);
        base.close();    // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        modificarPuesto(base);
        base.close();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        cargarTabla(base);

        base.close();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        confirmarModificacion(base);

        base.close();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String codigoEliminar = JOptionPane.showInputDialog("Ingrese el código del departamento a eliminar");
        boolean encontrado = false;

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        Query query = base.query();
        query.constrain(Puesto.class);
        query.descend("Codigo_puesto").constrain(codigoEliminar);

        ObjectSet<Puesto> result = query.execute();
        cargarTabla(base);

        if (result.size() > 0) {
            encontrado = true;

            int resul = JOptionPane.showConfirmDialog(null, "Deseas eliminar los datos del Puesto", "Confirmacion", JOptionPane.YES_NO_OPTION);

            if (resul == JOptionPane.YES_OPTION) {
                for (Puesto puestoBD : result) {

                    base.delete(puestoBD);
                    JOptionPane.showMessageDialog(null, "Se están borrando los datos del Puesto");
                    cargarTabla(base);
                }
            } else if (resul == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Datos del Puesto no eliminados");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el código");
            cargarTabla(base);
        }

        base.close();

    }//GEN-LAST:event_jButton3ActionPerformed

    // Función para realizar la modificación
    public void cargarTabla(ObjectContainer base) {
        DefaultTableModel model = (DefaultTableModel) tablapuesto.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        ObjectSet<Puesto> result = base.queryByExample(new Puesto());

        while (result.hasNext()) {
            Puesto puesto = result.next();
            Object[] row = {
                puesto.getCodigo_puesto(),
                puesto.getNombrePuesto(),
                puesto.getTipo_puesto(),
                puesto.getDescripcionPuesto()

            };
            model.addRow(row);
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblcod;
    private javax.swing.JTable tablapuesto;
    private javax.swing.JTextArea txtdescripcion;
    private javax.swing.JTextField txtnombrepuesto;
    private javax.swing.JTextField txttipopuesto;
    // End of variables declaration//GEN-END:variables

}
