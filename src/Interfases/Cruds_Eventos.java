/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Departamento;
import Clases.Evento;
import Clases.Patrocinador;
import Clases.Tipo_evento;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author eliza
 */
public class Cruds_Eventos extends javax.swing.JPanel {

    /**
     * Creates new form Cruds_Eventos
     */
    String horai = " ", horafinal = " ", cod = " ";

    public Cruds_Eventos() {
        initComponents();
//        cargar();
        txtinicio.setVisible(false);
        txtfinal.setVisible(false);
        btnmodificar.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jdtinicio = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txadescripcion = new javax.swing.JTextArea();
        btnguardar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        cbxubicacion = new javax.swing.JComboBox<>();
        tmreloj = new com.raven.swing.TimePicker();
        btnin = new javax.swing.JButton();
        btnfin = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtinicio = new javax.swing.JTextField();
        txtfinal = new javax.swing.JTextField();
        cbxtipo = new javax.swing.JComboBox<>();
        cboxpatrocinador = new javax.swing.JComboBox<>();
        lblcod = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/presentacion.png"))); // NOI18N
        jLabel1.setText("EVENTOS");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setText("Código Evento: ");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setText("Nombre: ");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setText("Tipo de Evento: ");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setText("Fecha de Inicio: ");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setText("Fecha de Fin:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel9.setText("Descripción:");

        txadescripcion.setColumns(20);
        txadescripcion.setRows(5);
        jScrollPane1.setViewportView(txadescripcion);

        btnguardar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible.png"))); // NOI18N
        btnguardar.setText("GUARDAR");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnmodificar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar (1).png"))); // NOI18N
        btnmodificar.setText("MODIFICAR");
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/busqueda.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel14.setText("Código Ubicación:");

        cbxubicacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cuenca", "Loja", "Machala", "Quito", "Sucumbios", "Morona Santiago" }));

        tmreloj.setForeground(new java.awt.Color(51, 102, 255));
        tmreloj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tmrelojMouseClicked(evt);
            }
        });

        btnin.setText("Hora de inicio");
        btnin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninActionPerformed(evt);
            }
        });

        btnfin.setText("Hora de fin");
        btnfin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfinActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setText("Establece la hora");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel8.setText("Patrocinador");

        txtinicio.setEditable(false);
        txtinicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtinicioActionPerformed(evt);
            }
        });

        txtfinal.setEditable(false);
        txtfinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfinalActionPerformed(evt);
            }
        });

        cbxtipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxtipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxtipoMouseClicked(evt);
            }
        });

        cboxpatrocinador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboxpatrocinador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboxpatrocinadorMouseClicked(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/hpermetropia.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jLabel7)
                            .addGap(17, 17, 17))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel8))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdtinicio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(88, 88, 88)
                                        .addComponent(lblcod, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cboxpatrocinador, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbxubicacion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxtipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                                .addGap(56, 56, 56)
                                .addComponent(tmreloj, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnfin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnin, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(txtfinal)
                            .addComponent(txtinicio))
                        .addGap(0, 65, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnguardar)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(btnmodificar)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtinicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnin)
                        .addGap(32, 32, 32)
                        .addComponent(txtfinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnfin)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jButton4)
                                    .addComponent(lblcod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(cboxpatrocinador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jButton1)))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cbxtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jdtinicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addComponent(tmreloj, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)))
                .addComponent(jLabel9)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnguardar)
                    .addComponent(btnmodificar))
                .addGap(85, 85, 85))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        // TODO add your handling code here:

        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        ActualizarDatos(base);

        base.close();
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:

        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        CrearEvento(base);
        base.close();

    }//GEN-LAST:event_btnguardarActionPerformed

    public void cargar(ObjectContainer base) {

        try {
            cbxtipo.removeAllItems();
            Query query = base.query();
            query.constrain(Tipo_evento.class);

            ObjectSet<Tipo_evento> evento1 = query.execute();

            while (evento1.hasNext()) {

                Tipo_evento mie = evento1.next();
                System.out.println("tipo:" + mie.getNombre());
                cbxtipo.addItem(mie.getNombre());
                cod = mie.getCodigo_tipo();

            }

        } finally {

            base.close();

        }
    }

    public void CrearEvento(ObjectContainer bd) {
        try {

            ObjectSet<Evento> resul = bd.queryByExample(new Evento(null, null, null, null, null, null, null, null, null, null));
            int ultimoCodigo = resul.size() + 1;

            // Formatear el código con ceros a la izquierda
            String cod = String.format("%03d", ultimoCodigo);
            lblcod.setText(cod);

            // Verificar si ya existe una casa con el mismo código
            resul = bd.queryByExample(new Evento(cod, null, null, null, null, null, null, null, null, null));

            Evento evento1 = new Evento(cod, txtnombre.getText().trim(), txadescripcion.getText().trim(), cboxpatrocinador.getSelectedItem().toString(), null, jdtinicio.getDate(), jDateChooser2.getDate(), horai, horafinal, cbxtipo.getSelectedItem().toString());
            bd.store(evento1);

            JOptionPane.showMessageDialog(null, "Se ha guardado el evento exitosamente");

            limpiar();
        } finally {
            bd.close();
        }
    }

    public void ActualizarDatos(ObjectContainer base) {

        Evento miagente = new Evento(cod, null, null, null, null, null, null, null, null, null);

        ObjectSet res = base.get(miagente);
        Evento mievento1 = (Evento) res.next();
        mievento1.setNombre(txtnombre.getText().trim());
        mievento1.setDescripcion(txadescripcion.getText().trim());
        mievento1.setTipo(cbxtipo.getSelectedItem().toString());
        mievento1.setCodigo_patrocinador(cboxpatrocinador.getSelectedItem().toString());
        mievento1.setFecha_inicio(jdtinicio.getDate());
        mievento1.setHora_inicio(horai);

        mievento1.setFecha_fin(jDateChooser2.getDate());
        mievento1.setHora_fin(horafinal);

        base.set(mievento1);

        JOptionPane.showMessageDialog(this, "Modificacion exitosa");

        limpiar();

    }
    private void tmrelojMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmrelojMouseClicked

    }//GEN-LAST:event_tmrelojMouseClicked

    public void limpiar() {
        lblcod.setText(" ");
        txtnombre.setText(" ");
        txadescripcion.setText(" ");
        jdtinicio.setDate(null);
        jDateChooser2.setDate(null);
        tmreloj.setToolTipText("12:00 AM");
    }
    private void btninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninActionPerformed
        // TODO add your handling code here:

        horai = tmreloj.getSelectedTime();
        JOptionPane.showMessageDialog(null, " Hora de Inicio" + horai);


    }//GEN-LAST:event_btninActionPerformed

    private void btnfinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfinActionPerformed

        horafinal = tmreloj.getSelectedTime();
        JOptionPane.showMessageDialog(null, " El evento se  acabara a las :" + horafinal);
    }//GEN-LAST:event_btnfinActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        txtinicio.setVisible(true);
        txtfinal.setVisible(true);

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        String nombre = " ", tipo = " ", descripcion = " ", patrocinador = " ", horain = " ", horaf = " ";
        Date fechai = null, fechaf = null;
        int ed = 0;
        Query query = base.query();
        query
                .constrain(Evento.class
                );
        query.descend(
                "cod_evento").constrain(cod);
        ObjectSet<Evento> result = query.execute();

        if (!result.isEmpty()) {

            for (Evento evento1 : result) {
                nombre = evento1.getNombre();
                descripcion = evento1.getDescripcion();
                patrocinador = evento1.getCodigo_patrocinador();
                fechai = evento1.getFecha_inicio();
                fechaf = evento1.getFecha_fin();

                horain = evento1.getHora_inicio();
                horaf = evento1.getHora_fin();

            }
            txtnombre.setText(nombre.trim());
            txadescripcion.setText(descripcion.trim());

            jdtinicio.setDate(fechai);
            jDateChooser2.setDate(fechaf);
            txtinicio.setText(horain);
            txtfinal.setText(horaf);

            Deshabilitar();

            int res = JOptionPane.showConfirmDialog(null, "Desea modificar los datos del evento", "confirmacion", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {

                btnmodificar.setEnabled(true);
                habilitar();
            } else if (res == JOptionPane.NO_OPTION) {
                limpiar();
                JOptionPane.showMessageDialog(null, "Se cancelo la modificacion de datos");
            }

            // lblcod.setText(cod.trim());
        } else {

            JOptionPane.showMessageDialog(null, "No se encontró ningún evento con el codigo ingresado");

        }

        base.close();
    }//GEN-LAST:event_jButton4ActionPerformed

    public void Deshabilitar() {
        cboxpatrocinador.setEnabled(false);
        txadescripcion.setEnabled(false);
        txtnombre.setEnabled(false);
        cbxtipo.setEnabled(false);
        jdtinicio.setEnabled(false);
        jDateChooser2.setEnabled(false);
        btnin.setEnabled(false);
        btnfin.setEnabled(false);
        cbxubicacion.setEnabled(false);
    }

    public void habilitar() {
        cboxpatrocinador.setEnabled(true);
        txadescripcion.setEnabled(true);
        txtnombre.setEnabled(true);
        cbxtipo.setEnabled(true);
        jdtinicio.setEnabled(true);
        jDateChooser2.setEnabled(true);
        btnin.setEnabled(true);
        btnfin.setEnabled(true);
        cbxubicacion.setEnabled(true);

    }
    private void txtinicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtinicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtinicioActionPerformed

    private void txtfinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfinalActionPerformed

    private void cbxtipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxtipoMouseClicked
        // TODO add your handling code here:
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        cargar(base);
        base.close();
    }//GEN-LAST:event_cbxtipoMouseClicked

    private void cboxpatrocinadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboxpatrocinadorMouseClicked
        cargarPatrocinadores();        // TODO add your handling code here:
    }//GEN-LAST:event_cboxpatrocinadorMouseClicked
    private void mostrarDatosPatrocinadores(ObjectContainer bases) {
        try {
            Object selectedItem = cboxpatrocinador.getSelectedItem();

            if (selectedItem != null) {
                String cedulaSeleccionada = selectedItem.toString();

                Query query = bases.query();
                query.constrain(Patrocinador.class);
                query.descend("codigo_patri").constrain(cedulaSeleccionada);
                ObjectSet<Patrocinador> result = query.execute();

                if (!result.isEmpty()) {
                    Patrocinador patro = result.next();
                    String mensaje = "Cédula: " + patro.getCedula() + "\n"
                            + "Nombre: " + patro.getNombre() + "\n"
                            + "Apellido: " + patro.getApellido() + "\n"
                            + "Telefono: " + patro.getTelefono() + "\n"
                            + "Email: " + patro.getCorreo() + "\n"
                            + "Direccion: " + patro.getDireccion() + "\n"
                            + "Genero: " + patro.getGenero() + "\n"
                            + "Fecha de Nacimiento: " + patro.getFecchaNaci() + "\n"
                            + "Redes Sociales: " + patro.getRedes_sociales() + "\n"
                            + "Descripcion: " + patro.getDescripcion_p();

                    JOptionPane.showMessageDialog(this, mensaje, "Datos del Patrocinador", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró un Patrocinador con la cédula seleccionada.", "Patrocinador no encontrado", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún código.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al mostrar datos del Patrocinador.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            bases.close();
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ObjectContainer bases = Db4o.openFile(Inicio.direccion);
        mostrarDatosPatrocinadores(bases);
        bases.close();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    public void cargarPatrocinadores() {
        ObjectContainer Base = Db4o.openFile(Inicio.direccion);
        cboxpatrocinador.removeAllItems();
        Query query = Base.query();
        query.constrain(Patrocinador.class);

        ObjectSet<Patrocinador> propi = query.execute();

        if (propi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No existen Patrocinadores", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            while (propi.hasNext()) {
                Patrocinador pro = propi.next();
                cboxpatrocinador.addItem(pro.getCodigo_patri());
            }
        }
        Base.close();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnfin;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnin;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JComboBox<String> cboxpatrocinador;
    private javax.swing.JComboBox<String> cbxtipo;
    private javax.swing.JComboBox<String> cbxubicacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdtinicio;
    private javax.swing.JLabel lblcod;
    private com.raven.swing.TimePicker tmreloj;
    private javax.swing.JTextArea txadescripcion;
    private javax.swing.JTextField txtfinal;
    private javax.swing.JTextField txtinicio;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
