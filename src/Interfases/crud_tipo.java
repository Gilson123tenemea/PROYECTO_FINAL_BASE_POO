/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Evento;
import Clases.Imagen;
import Clases.Proceso;
import Clases.Tipo_evento;
import Clases.imgTabla;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo.User
 */
public class crud_tipo extends javax.swing.JPanel {

    String ruta = "";

    Imagen p;
    Proceso rp;
    Evento e;
    Tipo_evento t;

    int clic_tabla;

    /**
     * Creates new form crud_tipo
     */
    public crud_tipo() {
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
        txttipo = new javax.swing.JTextField();
        pnlimagen = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnseleccionar = new javax.swing.JButton();
        tbtipo = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "         EVENTOS MUNICIPALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txttipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttipoActionPerformed(evt);
            }
        });
        jPanel1.add(txttipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 180, -1));

        javax.swing.GroupLayout pnlimagenLayout = new javax.swing.GroupLayout(pnlimagen);
        pnlimagen.setLayout(pnlimagenLayout);
        pnlimagenLayout.setHorizontalGroup(
            pnlimagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        pnlimagenLayout.setVerticalGroup(
            pnlimagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        jPanel1.add(pnlimagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 290, 250));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("INGRESA EL TIPO DE EVENTO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 260, -1));

        btnseleccionar.setText("SELECCIONAR IMAGEN ");
        btnseleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnseleccionarActionPerformed(evt);
            }
        });
        jPanel1.add(btnseleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbtipo.setViewportView(jTable1);

        jPanel1.add(tbtipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 480, 370));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, -1, -1));

        jButton2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar (1).png"))); // NOI18N
        jButton2.setText("MODIFICAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, -1, -1));

        jButton3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-eliminar (1).png"))); // NOI18N
        jButton3.setText("ELIMINAR");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 470, -1, -1));

        jButton5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/curriculum.png"))); // NOI18N
        jButton5.setText("REPORTE");
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnseleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnseleccionarActionPerformed
        // TODO add your handling code here:

        try {
            cargar();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, " no se selecciono una imagen ");
        }
    }//GEN-LAST:event_btnseleccionarActionPerformed

    public void listarRegistro() {
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dt.addColumn("Evento N°");
        dt.addColumn("tipo_n° ");
        dt.addColumn("nombre");
        dt.addColumn("imagen n°");
        dt.addColumn("Imagen");
        dt.addColumn("Nombre de Imagen");
        dt.addColumn("ruta");

        jTable1.setDefaultRenderer(Object.class, new imgTabla());

        Object fila[] = new Object[dt.getColumnCount()];
        for (int i = 0; i < rp.cantidadRegistro(); i++) {
            p = rp.obtenerRegistro(i);
            fila[0] = t.getCodigo_evento();
            fila[1] = t.getCodigo_tipo();
            fila[2] = t.getNombre();
            fila[3] = p.getId_imagen();
            try {
                byte[] bi = p.getData();
                BufferedImage image = null;
                InputStream in = new ByteArrayInputStream(bi);
                image = ImageIO.read(in);

                ImageIcon img = new ImageIcon(image.getScaledInstance(60, 60, 0));

                fila[4] = new JLabel(img);

            } catch (Exception e) {
                fila[4] = " no imagen";

            }

            fila[5] = p.getNombreArchivo();
            fila[6] = p.getRuta();

            dt.addRow(fila);
        }
        jTable1.setModel(dt);
        jTable1.setRowHeight(60);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        
        /*
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        crearTipo(base);
        listarRegistro();

        base.close();
*/


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txttipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttipoActionPerformed

    public byte[] leerFoto(ObjectContainer base) {
        try {
            byte[] icono = new byte[(int) ruta.length()];
            InputStream input = new FileInputStream(ruta);
            input.read(icono);
            return icono;
        } catch (Exception ex) {
            return null;
        }
    }

    public byte[] leerFoto2(int codigo) {
        p = rp.obtenerRegistro(codigo);
        try {
            return p.getData();
        } catch (Exception ex) {
            return null;
        }
    }

    public void crearTipo(ObjectContainer base) {

        try {
            Imagen im = new Imagen(leerFoto(base), ruta, null, ruta);
            Tipo_evento tip = new Tipo_evento(null, txttipo.getText().trim(), null);

            base.store(tip);
            base.store(im);
            
            JOptionPane.showMessageDialog(null,"Se ha creado el evento exitosamente");

        } finally {
            base.close();
        }

    }

    public String cargar() {

        Dimension d = pnlimagen.size();
        int alto = d.height;
        int ancho = d.width;

        ruta = ObtenerImagen();
        ImageIcon icon = new ImageIcon(ruta);
        Image img = icon.getImage();
        Image nueva = img.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);
        Icon nuevoIcon = new ImageIcon(nueva);

        JLabel label = new JLabel();
        label.setIcon(nuevoIcon);
        pnlimagen.removeAll();

        label.setBounds(0, 0, ancho, alto);
        pnlimagen.add(label);
        repaint();

        return ruta;
    }

    public String ObtenerImagen() {
        JFileChooser archivo = new JFileChooser();
        int resultado = archivo.showOpenDialog(this);
        if (resultado != JFileChooser.CANCEL_OPTION) {
            return archivo.getSelectedFile().getAbsolutePath().replace("\\", "/");
        }
        return null;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnseleccionar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel pnlimagen;
    private javax.swing.JScrollPane tbtipo;
    private javax.swing.JTextField txttipo;
    // End of variables declaration//GEN-END:variables
}
