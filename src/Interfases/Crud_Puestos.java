/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Comerciantes;
import Clases.Departamento;
import Clases.Puesto;
import Clases.Validaciones;
import base.ImpresionDeReportes;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author eliza
 */
public class Crud_Puestos extends javax.swing.JPanel {

    public static ArrayList<Puesto> listapuesto = new ArrayList<>();

    public static ArrayList<Puesto> codigoseliminados = new ArrayList<>();
    private TableRowSorter trs;

    public Crud_Puestos() {
        initComponents();
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        cargarTabla(base);

        base.close();
    }

    public void crearPuestos(ObjectContainer base) {

        if (!validarCampos()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Verificar si todos los campos están llenos
        if (txtnombrepuesto.getText().trim().isEmpty()
                || txttipopuesto.getText().trim().isEmpty()
                || txtdescripcion.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos antes de ingresar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Query query = base.query();
            query.constrain(Puesto.class);
            query.descend("Codigo_puesto").orderDescending();
            ObjectSet<Puesto> result = query.execute();

            int ultimoCodigo = 1;
            if (!result.isEmpty()) {
                Puesto ultimoPuesto = result.next();
                ultimoCodigo = Integer.parseInt(ultimoPuesto.getCodigo_puesto().substring(4)) + 1;
            }

            // Formatear el código con ceros a la izquierda
            String nuevoCodigo = String.format("PUE-%03d", ultimoCodigo);
            lblcod.setText(nuevoCodigo);

            // Verificar si ya existe un Puesto con el mismo código
            result = base.queryByExample(new Puesto(nuevoCodigo, null, null, null));

            if (!result.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ya existe un Puesto con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear objeto Puesto y almacenar en la base de datos
            Puesto puesto1 = new Puesto(nuevoCodigo, txtnombrepuesto.getText().trim(), txtdescripcion.getText().trim(), txttipopuesto.getText().trim());
            base.store(puesto1);

            JOptionPane.showMessageDialog(this, "Puesto creado exitosamente");
            limpiar();
            cargarTabla(base);
        } finally {
            base.close();
        }
    }

    public void ActualizarDatos(ObjectContainer base) {
        if (txtnombrepuesto.getText().trim().isEmpty() || txttipopuesto.getText().trim().isEmpty() || txtdescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor llene en el campo del Codigo para la Modificacion", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Puesto miubi = new Puesto(lblcod.getText().trim(), null, null, null);
            ObjectSet res = base.get(miubi);
            Puesto miubipersonal = (Puesto) res.next();
            miubipersonal.setNombrePuesto(txtnombrepuesto.getText().trim());
            miubipersonal.setTipo_puesto(txttipopuesto.getText().trim());

            miubipersonal.setDescripcionPuesto(txtdescripcion.getText().trim());

            base.set(miubipersonal);

            JOptionPane.showMessageDialog(this, "Modificación exitosa");
            cargarTabla(base);
            limpiar();

        } finally {
            base.close();
        }

    }

//    public void modificarPuesto(ObjectContainer base) {
//        int filaSeleccionada = tablapuesto.getSelectedRow();
//
//        if (filaSeleccionada != -1) {
//
//            String codigoModificar = tablapuesto.getValueAt(filaSeleccionada, 0).toString();
//
//            try {
//
//                Query query = base.query();
//                query.constrain(Puesto.class);
//                query.descend("Codigo_puesto").constrain(codigoModificar);
//                ObjectSet<Puesto> result = query.execute();
//
//                if (!result.isEmpty()) {
//
//                    Puesto puesto = result.get(0);
//
//                    lblcod.setEnabled(false);
//                    lblcod.setText(puesto.getCodigo_puesto());
//                    txtnombrepuesto.setText(puesto.getNombrePuesto());
//                    txttipopuesto.setText(puesto.getTipo_puesto());
//                    txtdescripcion.setText(puesto.getDescripcionPuesto());
//                } else {
//                    JOptionPane.showMessageDialog(null, "No se encontró del Puesto en la base de datos.");
//                }
//            } finally {
//                // No cierres la base de datos aquí; déjalo abierto para que puedas usarlo en el método que llama a modificarDepartamento
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila.");
//        }
//    }
    public boolean validarCampos() {
        Validaciones miValidaciones = new Validaciones();
        boolean ban_confirmar = true;

        if (txtnombrepuesto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del Puesto");
            ban_confirmar = false;
        } else if (!miValidaciones.validarDireccion(txtnombrepuesto.getText())) {
            JOptionPane.showMessageDialog(this, "Nombre del Puesto incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txttipopuesto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el Tipo de Puesto");
            ban_confirmar = false;
        } else if (!miValidaciones.validarDireccion(txttipopuesto.getText())) {
            JOptionPane.showMessageDialog(this, "Tipo de Puesto incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtdescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la Descripcion del Puesto");
            ban_confirmar = false;
        } else if (!miValidaciones.validarDireccion(txtdescripcion.getText())) {
            JOptionPane.showMessageDialog(this, "Descripcion del Puesto incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        return ban_confirmar;
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
        btnBuscar = new javax.swing.JButton();
        cboxbusqueda = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cabina.png"))); // NOI18N
        jLabel1.setText("PUESTO");
        jLabel1.setToolTipText("");

        jLabel2.setText("Código Puesto:");

        jLabel3.setText("Nombre de Puesto:");

        jLabel4.setText("Tipo de Puesto: ");

        jLabel5.setText("Descripción:");

        txtnombrepuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombrepuestoActionPerformed(evt);
            }
        });
        txtnombrepuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombrepuestoKeyTyped(evt);
            }
        });

        txttipopuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttipopuestoActionPerformed(evt);
            }
        });
        txttipopuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttipopuestoKeyTyped(evt);
            }
        });

        txtdescripcion.setColumns(20);
        txtdescripcion.setRows(5);
        txtdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescripcionKeyTyped(evt);
            }
        });
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
        jButton1.setToolTipText("GUARDA LA INFORMACION EN LA BASE DATOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar (1).png"))); // NOI18N
        jButton2.setText("MODIFICAR");
        jButton2.setToolTipText("ESTE BOTON SIRVE PARA SELECCIONAR LA FILA EN LA TABLA A MODIFICAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-eliminar (1).png"))); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.setToolTipText("ELIMINA EL PUESTO ESPECIFICO SEGUN SUS REQUERIMIENTOS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/curriculum.png"))); // NOI18N
        jButton4.setText("REPORTE");
        jButton4.setToolTipText("MUESTRA LOS DATOS QUE EXISTEN EN LA TABLA");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lOGO1.png"))); // NOI18N

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda.png"))); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.setToolTipText("ESTE BOTON SIRVE PARA CARGAR LOS DATOS PARA PODER MODIFICAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        cboxbusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Nombre", "Tipo" }));
        cboxbusqueda.setToolTipText("SELECCIONA LO QUE QUIERES BUSCAR ESPECIFICAMENTE");
        cboxbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxbusquedaActionPerformed(evt);
            }
        });

        jLabel10.setText("Filtro de Busqueda:");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar (1).png"))); // NOI18N
        jButton6.setText("LIMPIAR CAMPOS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtnombrepuesto)
                                        .addComponent(txttipopuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblcod, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(238, 238, 238)
                                        .addComponent(jLabel10)))))
                        .addGap(0, 56, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(168, 168, 168))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cboxbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6)
                            .addComponent(jLabel6))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addGap(153, 153, 153))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(lblcod, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboxbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnombrepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txttipopuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(btnBuscar))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        ActualizarDatos(base);
        base.close();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        cargarTabla(base);
        base.close();

        ImpresionDeReportes reporte = new ImpresionDeReportes(Login_Organizador.organizador);
        reporte.impresionPuesto();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String codigoEliminar = JOptionPane.showInputDialog("Ingrese el código del departamento a eliminar");
        boolean encontrado = false;
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        try {

            Comerciantes actividadAsociada = new Comerciantes(null, null, null, null, codigoEliminar, null, null, null, null, null, null, null, null, null, null);
            ObjectSet resultActividad = base.get(actividadAsociada);

            if (resultActividad.size() > 0) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar este Puesto porque está asociado a un Comerciante", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            base.close();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void buscarActividad(ObjectContainer base) {
        String codigoBusqueda = JOptionPane.showInputDialog(this, "Ingrese el código del Puesto a buscar:", "Buscar Tipo de Departamento", JOptionPane.QUESTION_MESSAGE);

        if (codigoBusqueda != null && !codigoBusqueda.isEmpty()) {
            ObjectSet<Puesto> result = base.queryByExample(new Puesto(codigoBusqueda, null, null, null));

            if (!result.isEmpty()) {
                Puesto ubicacionEncontrada = result.next();
                lblcod.setText(ubicacionEncontrada.getCodigo_puesto());
                txtnombrepuesto.setText(ubicacionEncontrada.getNombrePuesto());
                txttipopuesto.setText(ubicacionEncontrada.getTipo_puesto());
                txtdescripcion.setText(ubicacionEncontrada.getDescripcionPuesto());
                limpiarTabla();
                DefaultTableModel model = (DefaultTableModel) tablapuesto.getModel();

                Object[] row = {
                    ubicacionEncontrada.getCodigo_puesto(),
                    ubicacionEncontrada.getNombrePuesto(),
                    ubicacionEncontrada.getTipo_puesto(),
                    ubicacionEncontrada.getDescripcionPuesto(),};
                model.addRow(row);
                tablapuesto.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningun Puesto con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        base.close();
    }
//
//    public void buscarActividad(ObjectContainer base) {
//        String cedulaBuscada = JOptionPane.showInputDialog("Ingrese el código del Puesto a Buscar:");
//
//        if (cedulaBuscada != null && !cedulaBuscada.trim().isEmpty()) {
//
//            String nombre = " ", tipo = " ", descripcion = " ", cod = " ";
//
//            Query query = base.query();
//            query.constrain(Puesto.class);
//            query.descend("Codigo_puesto").constrain(cedulaBuscada.trim());
//            ObjectSet<Puesto> result = query.execute();
//
//            DefaultTableModel model = (DefaultTableModel) tablapuesto.getModel();
//            model.setRowCount(0);  // Limpiar la tabla antes de agregar la nueva fila
//
//            if (!result.isEmpty()) {
//                for (Puesto puesto : result) {
//                    nombre = puesto.getNombrePuesto();
//                    tipo = puesto.getTipo_puesto();
//                    descripcion = puesto.getDescripcionPuesto();
//
//                    // Agregar la fila a la tabla
//                    model.addRow(new Object[]{cedulaBuscada.trim(), nombre.trim(), tipo.trim(), descripcion.trim()});
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "No se encontró ningún Puesto con el código ingresado", "Error", JOptionPane.ERROR_MESSAGE);
//                cargarTabla(base);
//            }
//
//            base.close();
//        } else {
//            JOptionPane.showMessageDialog(null, "El código ingresado no es válido", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        buscarActividad(base);
        base.close();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed
    public void Filtro() {

        if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("nombre")) {
            int Columnastabla = 1;
            trs.setRowFilter(RowFilter.regexFilter(txtnombrepuesto.getText().trim(), Columnastabla));

        } else if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("tipo")) {
            int Columnastabla = 2;
            trs.setRowFilter(RowFilter.regexFilter(txttipopuesto.getText().trim(), Columnastabla));

        }
    }
    private void txtnombrepuestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombrepuestoKeyTyped
        if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("nombre")) {

            txtnombrepuesto.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (txtnombrepuesto.getText());

                    txtnombrepuesto.setText(cadena);
                    Filtro();

                }

            });

        }

//         else if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("tipo")) {
//            txttipopuesto.addKeyListener(new KeyAdapter() {
//                @Override
//                public void keyReleased(final KeyEvent e) {
//
//                    String cadena = (txttipopuesto.getText());
//
//                    txttipopuesto.setText(cadena);
//                    Filtro();
//
//                }
//
//            });
//
//        }
//
        trs = new TableRowSorter(tablapuesto.getModel());
        tablapuesto.setRowSorter(trs);

        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txtnombrepuesto.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtnombrepuesto.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txtnombrepuesto.setText(txtnombrepuesto.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtnombrepuesto.getText().length() > 19) {
            evt.consume();
        }
// TODO add your handling code here:
    }//GEN-LAST:event_txtnombrepuestoKeyTyped
    public void deshabilitarParametros() {
        txtnombrepuesto.setEnabled(false);
        txttipopuesto.setEnabled(false);
        txtdescripcion.setEnabled(false);

    }

    public void limpiarcampos() {
        txtnombrepuesto.setEnabled(true);
        txttipopuesto.setEnabled(true);
        txtdescripcion.setEnabled(true);

    }

    private void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) tablapuesto.getModel();
        model.setRowCount(0);
    }

    private void txttipopuestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttipopuestoKeyTyped
        if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("tipo")) {
            txttipopuesto.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (txttipopuesto.getText());

                    txttipopuesto.setText(cadena);
                    Filtro();

                }

            });

        }

        trs = new TableRowSorter(tablapuesto.getModel());
        tablapuesto.setRowSorter(trs);

        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txttipopuesto.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txttipopuesto.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txttipopuesto.setText(txttipopuesto.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txttipopuesto.getText().length() > 19) {
            evt.consume();
        }
// TODO add your handling code here:
    }//GEN-LAST:event_txttipopuestoKeyTyped
    private void habilitarCamposBusqueda(String criterioSeleccionado) {
        // Deshabilitar todos los campos de búsqueda
        deshabilitarParametros();
        // ...

        // Habilitar el campo de búsqueda correspondiente al criterio seleccionado
        if (criterioSeleccionado.equals("Nombre")) {
            txtnombrepuesto.setEnabled(true);
        } else if (criterioSeleccionado.equals("Tipo")) {
            txttipopuesto.setEnabled(true);
        } else if (criterioSeleccionado.equals("Seleccione")) {
            txttipopuesto.setEnabled(true);
            txtnombrepuesto.setEnabled(true);
            txtdescripcion.setEnabled(true);// ...
        }
    }
    private void cboxbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxbusquedaActionPerformed
        // Obtener el criterio seleccionado del JComboBox
        String criterioSeleccionado = cboxbusqueda.getSelectedItem().toString();

        // Habilitar o deshabilitar los campos de búsqueda según el criterio seleccionado
        habilitarCamposBusqueda(criterioSeleccionado);        // TODO add your handling code here:
    }//GEN-LAST:event_cboxbusquedaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        limpiarcampos();// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra, número o caracter especial y si es la primera letra
        if ((Character.isLetter(letra) || Character.isDigit(letra) || esCaracterEspecial(letra)) && txtdescripcion.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtdescripcion.setText(String.valueOf(letra));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetterOrDigit(letra) || Character.isSpaceChar(letra) || esCaracterEspecial(letra)) {
            // Verificar si es letra, número, espacio o caracter especial y agregar al texto en minúscula
            txtdescripcion.setText(txtdescripcion.getText() + letra);
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtdescripcion.getText().length() > 500) {
            evt.consume();
        }
        // Método para verificar si es un carácter especial

        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionKeyTyped

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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cboxbusqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
private boolean esCaracterEspecial(char caracter) {
        // Puedes ajustar esta lógica según los caracteres especiales que quieras permitir
        return "!@#$%^&*()_-+=<>?/".indexOf(caracter) != -1;
    }
}
