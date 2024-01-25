/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.ImageTableCellRenderer;
import Clases.Tipo_evento;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo.User
 */
public class crud_tipo extends javax.swing.JPanel {

    String ruta = "";
    int longitudBytes;
    byte[] foto;

    /**
     * Creates new form crud_tipo
     */
    public crud_tipo() {
        initComponents();
        jButton1.setEnabled(false);

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
        txttipo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnseleccionar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        fotolbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtipo = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "         EVENTOS MUNICIPALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txttipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttipoActionPerformed(evt);
            }
        });
        txttipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttipoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttipoKeyTyped(evt);
            }
        });
        jPanel1.add(txttipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 180, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("INGRESA EL TIPO DE EVENTO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 260, -1));

        btnseleccionar.setText("SELECCIONAR IMAGEN ");
        btnseleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnseleccionarActionPerformed(evt);
            }
        });
        jPanel1.add(btnseleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, -1, -1));

        fotolbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        fotolbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fotolblMouseClicked(evt);
            }
        });
        fotolbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fotolblKeyReleased(evt);
            }
        });
        jPanel1.add(fotolbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 250, 230));

        tbtipo.setFont(new java.awt.Font("Raanana", 0, 14)); // NOI18N
        tbtipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "CODIGO", "NOMBRE", "IMAGEN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbtipo.setEnabled(false);
        tbtipo.setRowHeight(100);
        tbtipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtipoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbtipo);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 500, 360));

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

            JFileChooser se = new JFileChooser();
            se.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int estado = se.showOpenDialog(null);
            if (estado == JFileChooser.APPROVE_OPTION) {
                try {
                    File archivo = se.getSelectedFile();
                    this.longitudBytes = (int) archivo.length();

                    // Leer la imagen seleccionada como bytes
                    byte[] buffer = new byte[this.longitudBytes];
                    try ( FileInputStream fis = new FileInputStream(archivo)) {
                        fis.read(buffer);
                    }

                    // Mostrar la imagen en el JLabel
                    Image icono = ImageIO.read(archivo).getScaledInstance(fotolbl.getWidth(), fotolbl.getHeight(), Image.SCALE_DEFAULT);
                    fotolbl.setIcon(new ImageIcon(icono));
                    fotolbl.updateUI();

                    // Asignar los bytes de la imagen a la variable "foto"
                    foto = buffer;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, " No se selecciono una imagen ");
        }
    }//GEN-LAST:event_btnseleccionarActionPerformed


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        crearImagen(base);

        base.close();

        cargarTabla();


    }//GEN-LAST:event_jButton1ActionPerformed

    public void cargarTabla() {

        ObjectContainer BaseD = Db4o.openFile(Inicio.direccion);

        Tipo_evento ima = new Tipo_evento(null, null, null, null);
        ObjectSet result = BaseD.get(ima);
        mostrarDatos(result);

        BaseD.close();
    }

    public void mostrarDatos(ObjectSet result) {
        Object[][] matrizExposicion = new Object[result.size()][3];
        if (result.size() == 0) {
            JOptionPane.showMessageDialog(null, "El tipo de evento no existe");
        } else {
            for (int i = 0; i < result.size(); i++) {
                Tipo_evento im = (Tipo_evento) result.get(i);
                matrizExposicion[i][0] = im.getCodigo_tipo();
                matrizExposicion[i][1] = im.getNombre();

                // Convertir el arreglo de bytes (foto) a un ImageIcon
                byte[] fotoBytes = im.getData();
                if (fotoBytes != null) {
                    ImageIcon icono = new ImageIcon(fotoBytes);
                    matrizExposicion[i][2] = icono;
                } else {
                    matrizExposicion[i][2] = null;
                }
            }

            // Configurar el modelo de la tabla con los datos y títulos de columna
            tbtipo.setModel(new javax.swing.table.DefaultTableModel(
                    matrizExposicion,
                    new String[]{"Codigo", "Nombre", "Foto"}
            ));

            // Asignar el renderer personalizado a la columna de la foto (columna 3)
            tbtipo.getColumnModel().getColumn(2).setCellRenderer(new ImageTableCellRenderer());
        }
    }


    private void txttipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttipoActionPerformed

    public void crearImagen(ObjectContainer base) {

        try {

            ObjectSet<Tipo_evento> resul = base.queryByExample(new Tipo_evento(null, null, null, null));
            int ultimoCodigo = resul.size() + 1;

            // Formatear el código con ceros a la izquierda
            String cod = String.format("%03d", ultimoCodigo);
            // lblcod.setText(cod);

            // Verificar si ya existe una casa con el mismo código
            resul = base.queryByExample(new Tipo_evento(cod.toLowerCase(), null, null, null));

            validar();
            
             ObjectSet<Tipo_evento> result = base.queryByExample(new Tipo_evento(null, txttipo.getText().trim().toLowerCase(), null, null));

            if (!result.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Este tipo de evento ya existe,ingresa uno nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            
            Tipo_evento im = new Tipo_evento(cod.toLowerCase(), txttipo.getText().trim().toLowerCase(), null, foto);
            base.store(im);
            JOptionPane.showMessageDialog(null, " Se guardo exitosamente");

            // Limpiar el JLabel (establecer su icono en un icono vacío)
            fotolbl.setIcon(new ImageIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)));
            txttipo.setText(" ");

        } finally {
            base.close();
        }
    }

    public void validar() {

        if (!txttipo.getText().trim().toLowerCase().isEmpty() && fotolbl.getIcon() != null) {
            jButton1.setEnabled(true);
        } else {
            jButton1.setEnabled(false);

        }
    }
    private void fotolblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fotolblMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_fotolblMouseClicked

    private void txttipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttipoKeyTyped
        // TODO add your handling code here:

        char letra = evt.getKeyChar();
        if (Character.isDigit(letra)) {
            evt.consume();
        }

        if (txttipo.getText().trim().length() > 19) {
            evt.consume();
        }

    }//GEN-LAST:event_txttipoKeyTyped

    private void tbtipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtipoMouseClicked
        // TODO add your handling code here:
        tbtipo.setEnabled(false);
    }//GEN-LAST:event_tbtipoMouseClicked

    private void txttipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttipoKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txttipoKeyReleased

    private void fotolblKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fotolblKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_fotolblKeyReleased

    public void asignarVariables(ObjectContainer BaseD) {
        seleccionarImagen();
        // nombre_exposicion = nombretxt.getText();
        // descripcion_exposicion = descripciontxt.getText();
    }

    /*
    public byte[] leerFoto2(int codigo) {
        p = rp.obtenerRegistro(codigo);
        try {
            return p.getData();
        } catch (Exception ex) {
            return null;
        }
    }

     */
    private byte[] obtenerBytesImagen() {
        JFileChooser se = new JFileChooser();
        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = se.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                File archivo = se.getSelectedFile();
                this.longitudBytes = (int) archivo.length();

                // Leer la imagen seleccionada como bytes
                byte[] buffer = new byte[this.longitudBytes];
                try ( FileInputStream fis = new FileInputStream(archivo)) {
                    fis.read(buffer);
                }

                // Mostrar la imagen en un JLabel (opcional, solo para visualización)
                Image icono = ImageIO.read(archivo).getScaledInstance(fotolbl.getWidth(), fotolbl.getHeight(), Image.SCALE_DEFAULT);
                fotolbl.setIcon(new ImageIcon(icono));
                fotolbl.updateUI();

                return buffer;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

// Método que invoca obtenerBytesImagen() y asigna el arreglo de bytes a la variable "foto"
    public void seleccionarImagen() {
        foto = obtenerBytesImagen();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnseleccionar;
    private javax.swing.JLabel fotolbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbtipo;
    private javax.swing.JTextField txttipo;
    // End of variables declaration//GEN-END:variables
}
