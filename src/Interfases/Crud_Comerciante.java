/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Comerciantes;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ADMIN_01
 */
public class Crud_Comerciante extends javax.swing.JPanel {

    private TableRowSorter trs;

    /**
     * Creates new form Crud_Comerciante
     */
    public Crud_Comerciante() {
        initComponents();

        cargarTabla();
    }

    private void cargarTabla() {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos
        ObjectSet<Comerciantes> result = base.queryByExample(new Comerciantes());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        while (result.hasNext()) {
            Comerciantes comerciante = result.next();

            Object[] row = {
                comerciante.getCedula(),
                comerciante.getApellido(),
                comerciante.getNombre(),
                comerciante.getCorreo(),
                comerciante.getTelefono(),
                comerciante.getDireccion(),
                comerciante.getTipo_Comercio(),
                comerciante.getGenero(),
                comerciante.getFecchaNaci() != null ? sdf.format(comerciante.getFecchaNaci()) : null,
                comerciante.getCodigo_participante(),
                comerciante.getProductos_c(),
                comerciante.getServicio_c(),
                comerciante.getCodigo_puesto(),
                comerciante.getCelular()
            };
            model.addRow(row);
        }

        base.close();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        REPORTE = new javax.swing.JButton();
        cbxbusqueda = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtconsulta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reunion (1).png"))); // NOI18N
        jLabel1.setText("REGISTRO DEL COMERCIANTE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(0, 153, 153));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 270, 10));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cedula", "Nombre", "Apellido", "Email", "Telefono", "Direccion", "Tipo.Comercio", "Genero", "F.Nacimiento", "Codigo.Comerciente", "Productos", "Servicios", "Codigo.Puesto", "Celular"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 900, 410));

        REPORTE.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        REPORTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/curriculum.png"))); // NOI18N
        REPORTE.setText("REPORTE");
        REPORTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REPORTEActionPerformed(evt);
            }
        });
        jPanel1.add(REPORTE, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, -1, -1));

        cbxbusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Cedula", "Nombre" }));
        jPanel1.add(cbxbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 140, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Buscar por:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 170, 20));

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
        jPanel1.add(txtconsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 180, -1));

        jButton1.setText("Rechazar");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 110, -1));

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void REPORTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REPORTEActionPerformed

    }//GEN-LAST:event_REPORTEActionPerformed

    private void txtconsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtconsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtconsultaActionPerformed

    public void Filtro() {

        if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("cedula")) {
            int Columnastabla = 0;
            trs.setRowFilter(RowFilter.regexFilter(txtconsulta.getText().trim(), Columnastabla));

        } else if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("nombre")) {
            int Columnastabla = 1;
            trs.setRowFilter(RowFilter.regexFilter(txtconsulta.getText().trim(), Columnastabla));

        }
    }

    private void txtconsultaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtconsultaKeyTyped
        // TODO add your handling code here:

        if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("cedula")) {

            txtconsulta.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (txtconsulta.getText());

                    txtconsulta.setText(cadena);
                    Filtro();

                }

            });

        } else if (cbxbusqueda.getSelectedItem().toString().equalsIgnoreCase("nombre")) {
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

        char letra = evt.getKeyChar();

// Verificar si es una letra o un número y si es la primera letra
        if ((Character.isLetter(letra) || Character.isDigit(letra)) && txtconsulta.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtconsulta.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isDigit(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra, número o espacio y agregar al texto en minúscula
            txtconsulta.setText(txtconsulta.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtconsulta.getText().length() > 19) {
            evt.consume();
        }


    }//GEN-LAST:event_txtconsultaKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            String nombre = (String) jTable1.getModel().getValueAt(selectedRow, 1);
            String apellido = (String) jTable1.getModel().getValueAt(selectedRow, 2);

            String Correodestino = (String) jTable1.getModel().getValueAt(selectedRow, 3);

            try {
                enviargmail(Correodestino, nombre, apellido);

            } catch (MessagingException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void enviargmail(String Correodestino, String nombre, String apellido) throws AddressException, MessagingException {

        String correoo = "eventosmunicipalessacy@gmail.com";
        String contra = "tunb mkea icns uyrz";
        Properties p = new Properties();

        p.put("mail.smtp.host", "smtp.gmail.com");
        p.setProperty("mail.smtp.starttls.enable", "true");

        p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        p.setProperty("mail.smtp.port", "587");
        p.setProperty("mail.smtp.user", correoo);
        p.setProperty("mail.smtp.auth", "true");

        Session s = Session.getDefaultInstance(p);

        MimeMessage mensaje = new MimeMessage(s);
        mensaje.setFrom(new InternetAddress(correoo));
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(Correodestino));
        mensaje.setSubject("Aceptacion de puesto");
        mensaje.setText("Estimado  " + nombre + " " + apellido + "\n Hemos analizado su solicitud y llegamos a la conclusion de otorgarle los debidos permisos" + "\n Por favor acerquese a las oficinas del municipio en su zona .");

        Transport t = s.getTransport("smtp");
        t.connect(correoo, contra);
        t.sendMessage(mensaje, mensaje.getAllRecipients());
        t.close();

        JOptionPane.showMessageDialog(null, "Mensaje de aceptacion enviada");

        boolean aceptacion = true;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton REPORTE;
    private javax.swing.JComboBox<String> cbxbusqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtconsulta;
    // End of variables declaration//GEN-END:variables
}
