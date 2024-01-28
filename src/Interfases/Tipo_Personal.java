/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Personal;
import Clases.Tipos_Personales;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ADMIN_01
 */
public class Tipo_Personal extends javax.swing.JPanel {

    private TableRowSorter trs;

    public Tipo_Personal() {
        initComponents();
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        cargarTabla(base);

        base.close();
    }

    public void crearTipoPersona(ObjectContainer base) {
        if (txtDescripcion.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos antes de ingresar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Query query = base.query();
            query.constrain(Tipos_Personales.class);
            query.descend("id_tip_peronal").orderDescending();
            ObjectSet<Tipos_Personales> result = query.execute();

            int ultimoCodigo = 1;
            if (!result.isEmpty()) {
                Tipos_Personales ultimaUbicacion = result.next();
                ultimoCodigo = Integer.parseInt(ultimaUbicacion.getId_tip_peronal().substring(4)) + 1;
            }

            String nuevoCodigo = String.format("TPP-%03d", ultimoCodigo);
            txtcodigo.setText(nuevoCodigo);

            result = base.queryByExample(new Tipos_Personales(nuevoCodigo, null, null));

            if (!result.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ya existe un tipo de personal con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Tipos_Personales nuevopersonal = new Tipos_Personales(nuevoCodigo, txtNombre.getText().trim(), txtDescripcion.getText().trim());
            base.store(nuevopersonal);

            JOptionPane.showMessageDialog(this, "Tipo de Personal creada exitosamente");
            limpiar();
            cargarTabla(base);
        } finally {
            base.close();
        }
    }

    public void limpiar() {
        txtcodigo.setText("");
        txtNombre.setText("");
        txtDescripcion.setText(" ");
    }

    public void cargarTabla(ObjectContainer base) {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        ObjectSet<Tipos_Personales> result = base.queryByExample(new Tipos_Personales());

        while (result.hasNext()) {
            Tipos_Personales personal1 = result.next();

            Object[] row = {
                personal1.getId_tip_peronal(),
                personal1.getNombre(),
                personal1.getDescripcion(),};
            model.addRow(row);
        }

    }

    private void buscarPersonal(ObjectContainer base) {
        String codigoBusqueda = JOptionPane.showInputDialog(this, "Ingrese el código del Tipo de Personal a buscar:", "Buscar Tipo de Personal", JOptionPane.QUESTION_MESSAGE);

        if (codigoBusqueda != null && !codigoBusqueda.isEmpty()) {
            ObjectSet<Tipos_Personales> result = base.queryByExample(new Tipos_Personales(codigoBusqueda, null, null));

            if (!result.isEmpty()) {
                Tipos_Personales ubicacionEncontrada = result.next();
                txtcodigo.setText(ubicacionEncontrada.getId_tip_peronal());
                txtNombre.setText(ubicacionEncontrada.getNombre());
                txtDescripcion.setText(ubicacionEncontrada.getDescripcion());
                limpiarTablaPersonal();
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

                Object[] row = {
                    ubicacionEncontrada.getId_tip_peronal(),
                    ubicacionEncontrada.getNombre(),
                    ubicacionEncontrada.getDescripcion(),};
                model.addRow(row);
                jTable1.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningun Tipo de Personal con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        base.close();
    }

    public void deshabilitarParametros() {
        txtcodigo.setEnabled(false);
        txtNombre.setEnabled(false);
        txtDescripcion.setEnabled(false);
    }

    private void habilitarCamposBusqueda(String criterioSeleccionado) {

        // Deshabilitar todos los campos de búsqueda
        deshabilitarParametros();
        // ...

        // Habilitar el campo de búsqueda correspondiente al criterio seleccionado
        if (criterioSeleccionado.equals("Nombre")) {
            txtNombre.setEnabled(true);
            limpiarCamposPatrocinador();

        } else if (criterioSeleccionado.equals("Seleccione")) {
            txtcodigo.setEnabled(true);
            txtNombre.setEnabled(true);
            txtDescripcion.setEnabled(true);

        }

    }

    private void limpiarCamposPatrocinador() {

        txtcodigo.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");

    }

    public void Filtro() {

        if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Nombre")) {
            int Columnastabla = 1;
            trs.setRowFilter(RowFilter.regexFilter(txtNombre.getText().trim(), Columnastabla));

        }
    }

    private void limpiarTablaPersonal() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    }

    private void cargarDatosPersonal(Tipos_Personales TipPer) {
        txtcodigo.setText(TipPer.getId_tip_peronal());
        txtNombre.setText(TipPer.getNombre());
        txtDescripcion.setText(TipPer.getDescripcion());
    }

    public void cargarTablaPersonal(ObjectContainer base) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        ObjectSet<Tipos_Personales> result = base.queryByExample(new Tipos_Personales());

        while (result.hasNext()) {
            Tipos_Personales tiper = result.next();

            Object[] row = {
                tiper.getId_tip_peronal(),
                tiper.getNombre(),
                tiper.getDescripcion()
            };
            model.addRow(row);
        }
        base.close();
    }

    public void ActualizarDatos(ObjectContainer base) {
        if (txtNombre.getText().trim().isEmpty() || txtDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor llene en el campo del Codigo para la Modificacion", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Tipos_Personales miubi = new Tipos_Personales(txtcodigo.getText().trim(), null, null);
            ObjectSet res = base.get(miubi);
            Tipos_Personales miubipersonal = (Tipos_Personales) res.next();
            miubipersonal.setNombre(txtNombre.getText().trim());
            miubipersonal.setDescripcion(txtDescripcion.getText().trim());

            base.set(miubipersonal);

            JOptionPane.showMessageDialog(this, "Modificación exitosa");
            cargarTablaPersonal(base);
            limpiar();

        } finally {
            base.close();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtcodigo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cboxbusqueda = new javax.swing.JComboBox<>();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Codigo:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        jLabel3.setText("Descripcion:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 150, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Descripcion"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 880, 180));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disco-flexible.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.setToolTipText("GUARDAR TIPO DE PERSONAL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda.png"))); // NOI18N
        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 110, 40));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar (1).png"))); // NOI18N
        jButton3.setText("MODIFICAR");
        jButton3.setToolTipText("MODIFICAR TIPO DE PERSONAL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-eliminar (1).png"))); // NOI18N
        jButton4.setText("ELIMINAR");
        jButton4.setToolTipText("ELIMINAR TIPO DE PERSONAL");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 380, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        jButton5.setText("REPORTE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 380, -1, -1));
        jPanel1.add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 120, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/TipPersonal.jpg"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 350, 270));

        jSeparator1.setForeground(new java.awt.Color(0, 153, 153));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 300, 20));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("TIPO DE PERSONAL");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 190, 10));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/secretario.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jLabel12.setText("Filtro de busqueda");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, -1, -1));

        cboxbusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Nombre" }));
        cboxbusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboxbusquedaMouseClicked(evt);
            }
        });
        cboxbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxbusquedaActionPerformed(evt);
            }
        });
        jPanel1.add(cboxbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 130, -1));

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        String codigoEliminar = JOptionPane.showInputDialog("Ingrese el código del Tipo de Personal");

        try {
            
            Personal actividadAsociada = new Personal(null, codigoEliminar, null, null, null,null,null,null,null);
            ObjectSet resultActividad = base.get(actividadAsociada);

            if (resultActividad.size() > 0) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar este Tipo Personal porque está asociado a un Personal", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Query querypersonal = base.query();
            querypersonal.constrain(Personal.class);
            querypersonal.descend("id_tip_peronal").constrain(codigoEliminar);

            ObjectSet<Personal> resultCasa = querypersonal.execute();

            if (resultCasa.size() > 0) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar el Tipo de Personal porque tiene a un personal asociada", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Query queryUbicacion = base.query();
            queryUbicacion.constrain(Tipos_Personales.class);
            queryUbicacion.descend("id_tip_peronal").constrain(codigoEliminar);

            ObjectSet<Tipos_Personales> resultUbicacion = queryUbicacion.execute();

            if (resultUbicacion.size() > 0) {
                int resul = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar los datos del Tipo de Personal?", "Confirmación", JOptionPane.YES_NO_OPTION);

                if (resul == JOptionPane.YES_OPTION) {
                    for (Tipos_Personales tiperosnal : resultUbicacion) {
                        base.delete(tiperosnal);
                        JOptionPane.showMessageDialog(null, "Se han borrado los datos del Tipo de Personal");
                        cargarTabla(base);
                    }
                } else if (resul == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Datos del Tipo de Personal no eliminados");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el código");
            }

            limpiar();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cargarTabla(base);
            base.close();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        ActualizarDatos(base);
        base.close();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        buscarPersonal(base);
        base.close();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        crearTipoPersona(base);
        base.close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboxbusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboxbusquedaMouseClicked

    }//GEN-LAST:event_cboxbusquedaMouseClicked

    private void cboxbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxbusquedaActionPerformed

        // Obtener el criterio seleccionado del JComboBox
        String criterioSeleccionado = cboxbusqueda.getSelectedItem().toString();

        // Habilitar o deshabilitar los campos de búsqueda según el criterio seleccionado
        habilitarCamposBusqueda(criterioSeleccionado);
    }//GEN-LAST:event_cboxbusquedaActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Nombre")) {

            txtNombre.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (txtNombre.getText());

                    txtNombre.setText(cadena);
                    Filtro();

                }

            });

        }

        trs = new TableRowSorter(jTable1.getModel());
        jTable1.setRowSorter(trs);

        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txtNombre.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtNombre.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txtNombre.setText(txtNombre.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtNombre.getText().length() > 19) {
            evt.consume();
        }

    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txtDescripcion.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtDescripcion.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txtDescripcion.setText(txtDescripcion.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtDescripcion.getText().length() > 100) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboxbusqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel txtcodigo;
    // End of variables declaration//GEN-END:variables
}
