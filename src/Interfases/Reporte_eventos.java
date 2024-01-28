/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Encuesta;
import Clases.Evento;
import Clases.ImageTableCellRenderer;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Lenovo.User
 */
public class Reporte_eventos extends javax.swing.JPanel {

    private TableRowSorter trs;

    /**
     * Creates new form Reporte_eventos
     */
    public Reporte_eventos() {
        initComponents();
        cargarTabla();

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
        jLabel1 = new javax.swing.JLabel();
        txtconsulta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cbxbusqueda = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Buscar por :");

        txtconsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtconsultaMouseClicked(evt);
            }
        });
        txtconsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtconsultaActionPerformed(evt);
            }
        });
        txtconsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtconsultaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtconsultaKeyTyped(evt);
            }
        });

        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("VER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cbxbusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Nombre", "Tipo" }));
        cbxbusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxbusquedaMouseClicked(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Raanana", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CODIGO", "NOMBRE", "DESCRIPCION", "TIPO", "PATROCINADOR", "FECHA DE INICIO", "HORA DE INICIO", "FECHA FINAL", "HORA FINAL", "Num puestos", "Precio", "IMAGEN"
            }
        ));
        jTable1.setRowHeight(100);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 880, 540));

        jButton3.setText("VOLVER");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 137, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            String Codigo = (String) jTable1.getModel().getValueAt(selectedRow, 0);
            ObjectContainer base = Db4o.openFile(Inicio.direccion);

            try {

                Encuesta actividadAsociada = new Encuesta(null, null, Codigo, null, null, null, null, null, null, null, null);
                ObjectSet resultActividad = base.get(actividadAsociada);

                if (resultActividad.size() > 0) {
                    JOptionPane.showMessageDialog(this, "No se puede eliminar este Evento porque está asociado a un Encuesta", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Query query = base.query();
                query.constrain(Evento.class);
                query.descend("cod_evento").constrain(Codigo);

                ObjectSet<Evento> result = query.execute();

                int resul = JOptionPane.showConfirmDialog(null, "Deseas eliminar algun evento", "Confirmacio", JOptionPane.YES_NO_OPTION);

                if (resul == JOptionPane.YES_OPTION) {
                    for (Evento evento1 : result) {

                        // Eliminar el agente de la base de datos db4o
                        base.delete(evento1);
                        JOptionPane.showMessageDialog(null, "Se estan borrando los datos del evento");

                    }
                } else if (resul == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Nose ha eliminado el veneto");
                }

                txtconsulta.setText(" ");
                vaciarTabla();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                base.close();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtconsultaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtconsultaKeyPressed
        // TODO add your handling code here:


    }//GEN-LAST:event_txtconsultaKeyPressed

    public void Filtro() {

        if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("nombre")) {
            int Columnastabla = 1;
            trs.setRowFilter(RowFilter.regexFilter(txtconsulta.getText().trim(), Columnastabla));

        } else if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("tipo")) {
            int Columnastabla = 3;
            trs.setRowFilter(RowFilter.regexFilter(txtconsulta.getText().trim(), Columnastabla));

        }
    }
    private void txtconsultaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtconsultaKeyTyped
        // TODO add your handling code here:

        if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("nombre")) {

            txtconsulta.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (txtconsulta.getText());

                    txtconsulta.setText(cadena);
                    Filtro();

                }

            });

        } else if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("tipo")) {
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            String Codigo = (String) jTable1.getModel().getValueAt(selectedRow, 0);
            String nombre = (String) jTable1.getModel().getValueAt(selectedRow, 1);
            String descripcion = (String) jTable1.getModel().getValueAt(selectedRow, 2);
            String tipo = (String) jTable1.getModel().getValueAt(selectedRow, 3);
            String patrocinador = (String) jTable1.getModel().getValueAt(selectedRow, 4);
            String Fechai = (String) jTable1.getModel().getValueAt(selectedRow, 5);
            String horai = (String) jTable1.getModel().getValueAt(selectedRow, 6);

            String fechaf = (String) jTable1.getModel().getValueAt(selectedRow, 7);
            String horaf = (String) jTable1.getModel().getValueAt(selectedRow, 8);
            int cantidad = (int) jTable1.getModel().getValueAt(selectedRow, 9);

            double precio = (double) jTable1.getModel().getValueAt(selectedRow, 10);
            ImageIcon icono = (ImageIcon) jTable1.getModel().getValueAt(selectedRow, 11);

            JLabel imageLabel = new JLabel(icono);
            JPanel panel = new JPanel();
            panel.add(imageLabel);

            JOptionPane.showMessageDialog(null, new Object[]{"Codigo del evento: " + Codigo, "Nombre del evento: " + nombre, "Descripcion: " + descripcion, "Tipo de evento: "
                + tipo, "Patrocinador: " + patrocinador, "Fecha de inicio: " + Fechai, "Hora de inicio: " + horai,
                "Fecha final: " + fechaf, "hora final: " + horaf,"Cantidad de Puestos"+cantidad,"Precio del evento"+precio, panel}, "Event Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila");
        }

//        int selectedRow = jTable1.getSelectedRow();
//        if (selectedRow != -1) {
//            String Codigo = (String) jTable1.getModel().getValueAt(selectedRow, 0);
//            String nombre = (String) jTable1.getModel().getValueAt(selectedRow, 1);
//            String descripcion = (String) jTable1.getModel().getValueAt(selectedRow, 2);
//            String tipo = (String) jTable1.getModel().getValueAt(selectedRow, 3);
//            String patrocinador = (String) jTable1.getModel().getValueAt(selectedRow, 4);
//            String Fechai = (String) jTable1.getModel().getValueAt(selectedRow, 5);
//            String horai = (String) jTable1.getModel().getValueAt(selectedRow, 6);
//
//            String fechaf = (String) jTable1.getModel().getValueAt(selectedRow, 7);
//            String horaf = (String) jTable1.getModel().getValueAt(selectedRow, 8);
//            ImageIcon icono = (ImageIcon) jTable1.getModel().getValueAt(selectedRow, 9);
//            JLabel image = new JLabel(icono);
//
//            JOptionPane.showMessageDialog(null, "Codigo del evento: " + Codigo + "\nNombre del evento: " + nombre + "\nDescripcion: " + descripcion + "\nTipo de evento: "
//                    + tipo + "\nPatrocinador: " + patrocinador + "\nFecha de inicio: " + Fechai + "\nHora de inicio: " + horai + 
//                    "\nFecha final: " + fechaf + "\nhora final: " + horaf + "\nImagen: \n" + icono);
//        } else {
//            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila");
//        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbxbusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxbusquedaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cbxbusquedaMouseClicked

    private void txtconsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtconsultaMouseClicked
        // TODO add your handling code here:
        cargarTabla();
    }//GEN-LAST:event_txtconsultaMouseClicked

    private void txtconsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtconsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtconsultaActionPerformed

    public void cargarTabla() {

        ObjectContainer BaseD = Db4o.openFile(Inicio.direccion);

        Evento ima = new Evento(null, null, null, null, null, null, null, null, null, null, null, 0.0, 0);
        ObjectSet result = BaseD.get(ima);
        CargarDatos(result);

        BaseD.close();
    }

    public void CargarDatos(ObjectSet result) {
        Object[][] data = new Object[result.size()][12];
        if (result.size() == 0) {
            JOptionPane.showMessageDialog(null, "El evento no existe");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (int i = 0; i < result.size(); i++) {
                Evento im = (Evento) result.get(i);
                data[i][0] = im.getCod_evento();
                data[i][1] = im.getNombre();
                data[i][2] = im.getDescripcion();
                data[i][3] = im.getTipo();

                data[i][4] = im.getCodigo_patrocinador();
                data[i][5] = im.getFecha_inicio() != null ? sdf.format(im.getFecha_inicio()) : null;
                data[i][6] = im.getHora_inicio();
                data[i][7] = im.getFecha_fin() != null ? sdf.format(im.getFecha_fin()) : null;
                data[i][8] = im.getHora_fin();
                data[i][9] = im.getNum_puestos();
                data[i][10] = im.getPrecio();

                byte[] fotoBytes = im.getData();
                if (fotoBytes != null) {
                    ImageIcon icono = new ImageIcon(fotoBytes);
                    data[i][11] = icono;
                } else {
                    data[i][11] = null;
                }

                // Configurar el modelo de la tabla con los datos y títulos de columna
                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        data,
                        new String[]{"CODIGO", "NOMBRE", "DESCRIPCION", "TIPO DE EVENTO", "PATROCINADOR", "FECHA DE INICIO", "HORA DE INICIO", "FECHA FINAL", "HORA FINAL", "NUM PUESTOS", "PRECIO", "IMAGEN"}
                ));

                // Asignar el renderer personalizado a la columna de la foto (columna 3)
                jTable1.getColumnModel().getColumn(11).setCellRenderer(new ImageTableCellRenderer());
            }
        }

    }

    public void vaciarTabla() {
        // Obtenemos el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

        // Borramos las filas antiguas del modelo de tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxbusqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtconsulta;
    // End of variables declaration//GEN-END:variables
}
