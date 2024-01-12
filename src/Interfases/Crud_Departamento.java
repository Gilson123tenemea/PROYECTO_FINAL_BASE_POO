/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Departamento;
import Clases.Organizador;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eliza
 */
public class Crud_Departamento extends javax.swing.JPanel {

    public static ArrayList<Departamento> listadepartamento = new ArrayList<>();

    public static ArrayList<Departamento> codigoseliminados = new ArrayList<>();

    public Crud_Departamento() {
        initComponents();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreDepartamento = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDatos = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        lblIdDepartamento = new javax.swing.JLabel();
        btnConfirmarModificacion = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/escritorio (1).png"))); // NOI18N
        jLabel1.setText("DEPARTAMENTOS");

        jLabel2.setText("Id Departamento: ");

        jLabel3.setText("Nombre de Departamento: ");

        jLabel4.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lOGO1.png"))); // NOI18N

        jTableDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id Departamento", "Nombre de Departamento", "Descripción"
            }
        ));
        jScrollPane2.setViewportView(jTableDatos);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disco-flexible.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar (1).png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-eliminar (1).png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/curriculum.png"))); // NOI18N
        btnReporte.setText("REPORTE");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        btnConfirmarModificacion.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnConfirmarModificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/confirmacion.png"))); // NOI18N
        btnConfirmarModificacion.setText("Confirmar Modificar");
        btnConfirmarModificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarModificacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblIdDepartamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNombreDepartamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addComponent(btnReporte)
                .addGap(18, 18, 18)
                .addComponent(btnConfirmarModificacion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(lblIdDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1)))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombreDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnReporte)
                    .addComponent(btnConfirmarModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        modificarDepartamento(base);
        base.close();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        crearDepartamento(base);
        base.close();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        // TODO add your handling code here:
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        cargarTabla(base);

        base.close();
    }//GEN-LAST:event_btnReporteActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        String codigoEliminar = JOptionPane.showInputDialog("Ingrese el código del departamento a eliminar");
        boolean encontrado = false;

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        Query query = base.query();
        query.constrain(Departamento.class);
        query.descend("id_departamento").constrain(codigoEliminar);

        ObjectSet<Departamento> result = query.execute();
        cargarTabla(base);

        if (result.size() > 0) {
            encontrado = true;

            int resul = JOptionPane.showConfirmDialog(null, "Deseas eliminar los datos del Departamento", "Confirmacion", JOptionPane.YES_NO_OPTION);

            if (resul == JOptionPane.YES_OPTION) {
                for (Departamento departamentoDB : result) {
                    // Eliminar la Casa Vacacional de la base de datos db4o
                    base.delete(departamentoDB);
                    JOptionPane.showMessageDialog(null, "Se están borrando los datos del Departamento");
                    cargarTabla(base);
                }
            } else if (resul == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Datos del Departamento no eliminados");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el código");
            cargarTabla(base);
        }

        base.close();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnConfirmarModificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarModificacionActionPerformed
        // TODO add your handling code here:
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        confirmarModificacion(base);

        base.close();
    }//GEN-LAST:event_btnConfirmarModificacionActionPerformed

    public void modificarDepartamento(ObjectContainer base) {
        int filaSeleccionada = jTableDatos.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener el código del departamento seleccionado en la tabla
            String codigoModificar = jTableDatos.getValueAt(filaSeleccionada, 0).toString();

            try {
                // Realizar la búsqueda en la base de datos
                Query query = base.query();
                query.constrain(Departamento.class);
                query.descend("id_departamento").constrain(codigoModificar);
                ObjectSet<Departamento> result = query.execute();

                if (!result.isEmpty()) {
                    // Solo tomará el primer resultado si hay más de uno (debería haber solo uno)
                    Departamento depar = result.get(0);

                    // Cargar los datos del departamento en los campos correspondientes
                    lblIdDepartamento.setEnabled(false);
                    lblIdDepartamento.setText(depar.getId_departamento());
                    txtNombreDepartamento.setText(depar.getNombre());
                    txtDescripcion.setText(depar.getDescripcion());
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el departamento en la base de datos.");
                }
            } finally {
                // No cierres la base de datos aquí; déjalo abierto para que puedas usarlo en el método que llama a modificarDepartamento
            }
        } else {
            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila.");
        }
    }

    public void confirmarModificacion(ObjectContainer base) {
        int filaSeleccionada = jTableDatos.getSelectedRow();

        if (filaSeleccionada != -1) {
            String codigoModificar = jTableDatos.getValueAt(filaSeleccionada, 0).toString();

            try {
                Query query = base.query();
                query.constrain(Departamento.class);
                query.descend("id_departamento").constrain(codigoModificar);
                ObjectSet<Departamento> result = query.execute();

                if (!result.isEmpty()) {
                    Departamento departamento = result.get(0);

                    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de modificar el departamento?", "Confirmación", JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        // Modificar los datos del departamento en la base de datos
                        lblIdDepartamento.setEnabled(false);
                        departamento.setNombre(txtNombreDepartamento.getText());
                        departamento.setDescripcion(txtDescripcion.getText());

                        base.store(departamento); // Actualizar el objeto en la base de datos

                        JOptionPane.showMessageDialog(null, "Departamento modificado correctamente");
                        RefrescarTabla(base);
                        limpiar();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el departamento en la base de datos.");
                }
            } finally {
                // No cierres la base de datos aquí; déjalo abierto para que puedas usarlo en otras partes del código
            }
        } else {
            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila.");
        }
    }

    public void RefrescarTabla(ObjectContainer base) {
        DefaultTableModel model = (DefaultTableModel) jTableDatos.getModel();
        model.setRowCount(0);

        Query query = base.query();
        query.constrain(Departamento.class);
        ObjectSet<Departamento> result = query.execute();

        for (Departamento departamento : result) {
            // Crear un arreglo para los datos de cada departamento
            Object[] a = new Object[3];
            // Obtener los datos del departamento actual y agregarlos al arreglo
            a[0] = departamento.getId_departamento();
            a[1] = departamento.getNombre();
            a[2] = departamento.getDescripcion();
            // Agregar el arreglo de datos como una fila en el modelo de la tabla
            model.addRow(a);
        }
        // No es necesario establecer el modelo nuevamente en la tabla
    }

    public void crearDepartamento(ObjectContainer Base) {
        // Verificar si todos los campos están llenos
        if (txtNombreDepartamento.getText().trim().isEmpty() || txtDescripcion.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos antes de ingresar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            ObjectSet<Departamento> resul = Base.queryByExample(new Departamento(null, null, null));
            int ultimoCodigo = resul.size() + 1;

            // Formatear el código con ceros a la izquierda
            String cod = String.format("%03d", ultimoCodigo);
            lblIdDepartamento.setText(cod);

            // Verificar si ya existe una casa con el mismo código
            resul = Base.queryByExample(new Departamento(cod, null, null));

            if (!resul.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ya existe una casa con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear objeto CasaVacacional y almacenar en la base de datos
            Departamento casa1 = new Departamento(lblIdDepartamento.getText().trim(), txtNombreDepartamento.getText().trim(), txtDescripcion.getText().trim());

            Base.store(casa1);

            JOptionPane.showMessageDialog(this, "Casa creada exitosamente");
            limpiar();
            cargarTabla(Base);
        } finally {
            Base.close();
        }
    }

    public void limpiar() {
        txtNombreDepartamento.setText("");
        txtDescripcion.setText("");

    }

    public void cargarTabla(ObjectContainer base) {

        DefaultTableModel model = (DefaultTableModel) jTableDatos.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        ObjectSet<Departamento> result = base.queryByExample(new Departamento());

        while (result.hasNext()) {
            Departamento departamento = result.next();

            Object[] row = {
                departamento.getId_departamento(),
                departamento.getNombre(),
                departamento.getDescripcion(),};
            model.addRow(row);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmarModificacion;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableDatos;
    private javax.swing.JLabel lblIdDepartamento;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombreDepartamento;
    // End of variables declaration//GEN-END:variables
}
