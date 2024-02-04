/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Organizador;
import Clases.Validaciones;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Crud_ModOrganizador extends javax.swing.JPanel {

    boolean primeraMayusculaIngresada;
    String fecha;

    public Crud_ModOrganizador() {
        initComponents();
        deshabilitarCampos();

        btncrear.setEnabled(false);

        agrupar();

        // spnedad.setModel(new SpinnerNumberModel(0, 0, 60, 1));
    }

    public void agrupar() {

        ButtonGroup botones = new ButtonGroup();
        botones.add(rbnmasculino);
        botones.add(rbnfemenino);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtcedula = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtcelular = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtpresupuesto = new javax.swing.JTextField();
        btncrear = new javax.swing.JButton();
        rbnmasculino = new javax.swing.JRadioButton();
        rbnfemenino = new javax.swing.JRadioButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        lblcorreo = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        lblapellido = new javax.swing.JLabel();
        lblcelular = new javax.swing.JLabel();
        lbltelefono = new javax.swing.JLabel();
        lbldireccion = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblpresupuesto = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        lblgenero = new javax.swing.JLabel();
        lblcedula = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 12, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gestion-del-tiempo.png"))); // NOI18N
        jLabel1.setText("ORGANIZADORES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        jLabel2.setText("CEDULA:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        txtcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcedulaKeyTyped(evt);
            }
        });
        jPanel1.add(txtcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 157, 140, -1));

        jLabel3.setText("NOMBRE:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 204, 140, -1));

        txtapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtapellidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidoKeyTyped(evt);
            }
        });
        jPanel1.add(txtapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 252, 140, -1));

        jLabel4.setText("APELLDO:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        jLabel5.setText("CELULAR:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, -1, -1));

        txtcelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcelularKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcelularKeyTyped(evt);
            }
        });
        jPanel1.add(txtcelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 301, 140, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo Oficila de Eventos.PNG"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 180, -1));

        jLabel7.setText("DIRECCION:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, -1, 20));

        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdireccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 400, 140, -1));

        jLabel9.setText("GENERO:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, -1, -1));

        jLabel10.setText("CORREO:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 204, -1, 30));

        txtcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcorreoKeyReleased(evt);
            }
        });
        jPanel1.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 160, -1));

        jLabel11.setText("TELEFONO");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, -1, -1));

        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 353, 140, -1));

        jLabel15.setText("FECHA DE NACIMIENTO");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, -1, 20));

        txtpresupuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpresupuestoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpresupuestoKeyTyped(evt);
            }
        });
        jPanel1.add(txtpresupuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 160, -1));

        btncrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disco-flexible.png"))); // NOI18N
        btncrear.setText("GUARDAR");
        btncrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncrearActionPerformed(evt);
            }
        });
        jPanel1.add(btncrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 520, -1, -1));

        rbnmasculino.setText("Masculino");
        rbnmasculino.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbnmasculinoStateChanged(evt);
            }
        });
        jPanel1.add(rbnmasculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, -1, -1));

        rbnfemenino.setText("Femenino");
        rbnfemenino.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbnfemeninoStateChanged(evt);
            }
        });
        jPanel1.add(rbnfemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, -1, -1));

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 290, 160, -1));

        jLabel16.setText("PRESUPUESTO:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, -1, 20));

        lblcorreo.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lblcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 230, 140, -1));

        lblnombre.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lblnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 140, -1));

        lblapellido.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lblapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 140, -1));

        lblcelular.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lblcelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 140, -1));

        lbltelefono.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lbltelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 140, -1));

        lbldireccion.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lbldireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 140, -1));

        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 140, -1));

        lblpresupuesto.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lblpresupuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, 140, -1));

        lblfecha.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lblfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 370, 140, -1));

        lblgenero.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lblgenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, 140, -1));

        lblcedula.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lblcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 140, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btncrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearActionPerformed
        // TODO add your handling code here:

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        ActualizarOrganizador(base);
        base.close();

    }//GEN-LAST:event_btncrearActionPerformed

    public void inahabilitarDatos() {

    }
    private void txtcedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyReleased
        Validar();
    }//GEN-LAST:event_txtcedulaKeyReleased

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        Validar();
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtapellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidoKeyReleased
        Validar();

    }//GEN-LAST:event_txtapellidoKeyReleased

    private void txtcelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcelularKeyReleased
        Validar();

    }//GEN-LAST:event_txtcelularKeyReleased

    private void txttelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyReleased
        // TODO add your  code here:
        Validar();

    }//GEN-LAST:event_txttelefonoKeyReleased

    private void txtdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyReleased
        // TODO add your handling code here:
        Validar();

    }//GEN-LAST:event_txtdireccionKeyReleased

    private void txtcorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoKeyReleased
        // TODO add your handling code here:
        Validar();

    }//GEN-LAST:event_txtcorreoKeyReleased

    private void txtpresupuestoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpresupuestoKeyReleased
        // TODO add your handling code here:
        Validar();

    }//GEN-LAST:event_txtpresupuestoKeyReleased

    private void rbnmasculinoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbnmasculinoStateChanged
        // TODO add your handling code here:
        Validar();

    }//GEN-LAST:event_rbnmasculinoStateChanged

    private void rbnfemeninoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbnfemeninoStateChanged
        // TODO add your handling code here:
        Validar();

    }//GEN-LAST:event_rbnfemeninoStateChanged

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        Validar();

    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        String nombre = " ", apellido = " ", celular = " ", telefono = " ", direccion = " ", correo = " ", codigo = "";
        double presupuesto = 0.0;
        Date fecha = null;

        String genero = "";  // Asegúrate de tener declarada la variable "genero"
        String cod = "";     // Asegúrate de tener declarada la variable "cod"

        Query query = base.query();
        query.constrain(Organizador.class);
        query.descend("cedula").constrain(txtcedula.getText().trim());
        ObjectSet<Organizador> result = query.execute();

        if (!result.isEmpty()) {
            for (Organizador organizador : result) {
                nombre = organizador.getNombre();
                apellido = organizador.getApellido();
                celular = organizador.getCelular();
                telefono = organizador.getTelefono();
                direccion = organizador.getDireccion();

                correo = organizador.getCorreo();
                cod = organizador.getCod_organizador();
                presupuesto = organizador.getPresupuesto();
                genero = organizador.getGenero();
                fecha = organizador.getFecchaNaci();
            }

            txtnombre.setText(nombre.trim());
            txttelefono.setText(telefono.trim());
            txtapellido.setText(apellido.trim());
            txtcorreo.setText(correo.trim());
            txtdireccion.setText(direccion.trim());
            txtcelular.setText(celular.trim());

            if (genero.equalsIgnoreCase("Masculino")) {
                rbnmasculino.setSelected(true);
            } else if (genero.equalsIgnoreCase("Femenino")) {
                rbnfemenino.setSelected(true);
            }

            String presupuesto1 = String.valueOf(presupuesto);
            txtpresupuesto.setText(presupuesto1);

            // Formatear la fecha usando SimpleDateFormat
            String fechaFormateada = (fecha != null) ? sdf.format(fecha) : "";
            try {
                jDateChooser1.setDate(sdf.parse(fechaFormateada));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int resultado = JOptionPane.showConfirmDialog(null, "Desea modificar los datos", "confirmacion", JOptionPane.YES_NO_OPTION);

            if (resultado == JOptionPane.YES_OPTION) {
                habilitarCampos();
            } else if (resultado == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Modificación cancelada");
                deshabilitarCampos();
                btncrear.setEnabled(false);
            }

            // Resto del código...
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún organizador con la cédula ingresada");
        }
        base.close();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyTyped
        char c = evt.getKeyChar();
        // Verificar si es un número y si la longitud actual es menor que 10
        if (!Character.isDigit(c) || txtcedula.getText().length() >= 10) {
            // Si no es un número o la longitud es mayor o igual a 10, se consume el evento para evitar que se agregue al campo de texto
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_txtcedulaKeyTyped

    private void txtcelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcelularKeyTyped
        char c = evt.getKeyChar();
        // Verificar si es un número y si la longitud actual es menor que 10
        if (!Character.isDigit(c) || txtcelular.getText().length() >= 10) {
            // Si no es un número o la longitud es mayor o igual a 10, se consume el evento para evitar que se agregue al campo de texto
            evt.consume();
        }      // TODO add your handling code here:
    }//GEN-LAST:event_txtcelularKeyTyped

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
        char c = evt.getKeyChar();
        // Verificar si es un número y si la longitud actual es menor que 10
        if (!Character.isDigit(c) || txttelefono.getText().length() >= 10) {
            // Si no es un número o la longitud es mayor o igual a 10, se consume el evento para evitar que se agregue al campo de texto
            evt.consume();
        }            // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtpresupuestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpresupuestoKeyTyped
        char c = evt.getKeyChar();
        // Verificar si es un número y si la longitud actual es menor que 10
        if (!Character.isDigit(c) || txtpresupuesto.getText().length() >= 10) {
            // Si no es un número o la longitud es mayor o igual a 10, se consume el evento para evitar que se agregue al campo de texto
            evt.consume();
        }            // TODO add your handling code here:
    }//GEN-LAST:event_txtpresupuestoKeyTyped

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txtnombre.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtnombre.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txtnombre.setText(txtnombre.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtnombre.getText().length() > 19) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidoKeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txtapellido.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtapellido.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txtapellido.setText(txtapellido.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtapellido.getText().length() > 19) {
            evt.consume();
        }

    }//GEN-LAST:event_txtapellidoKeyTyped

    private void txtdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txtdireccion.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtdireccion.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txtdireccion.setText(txtdireccion.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtdireccion.getText().length() > 19) {
            evt.consume();
        }

    }//GEN-LAST:event_txtdireccionKeyTyped

    public static boolean esNumeroDecimal(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void ActualizarOrganizador(ObjectContainer base) {

        if (!validarCampos()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double presupuesto = 0.0;
        String valorIngresado = txtpresupuesto.getText().trim();

        if (esNumeroDecimal(valorIngresado)) {
            presupuesto = Double.valueOf(valorIngresado);
        } else {
            System.out.println("Error: El valor ingresado no es un número decimal.");
        }
        String seleccion = " ";

        Date fecha = jDateChooser1.getDate();

        if (fecha != null) {
            seleccion = new SimpleDateFormat("dd/MM/yyyy").format(fecha);
        } else {
            System.out.println("Fecha no seleccionada");
        }

        String sexo = " ";
        if (rbnfemenino.isSelected()) {
            sexo = "Femenino";

        } else if (rbnmasculino.isSelected()) {
            sexo = "Masculino";
        }

        Organizador miorganizador = new Organizador(null, null, null, 0.0, null, null, null, txtcedula.getText().trim(), null, null, null, null, null, null, null, null);

        ObjectSet res = base.get(miorganizador);
        Organizador organizador1 = (Organizador) res.next();
        organizador1.setNombre(txtnombre.getText().trim());
        organizador1.setApellido(txtapellido.getText().trim());
        organizador1.setCorreo(txtcorreo.getText().trim());
        organizador1.setGenero(sexo.trim());
        organizador1.setTelefono(txttelefono.getText().trim());

        organizador1.setFecchaNaci(jDateChooser1.getDate());

        organizador1.setCelular(txtcelular.getText().trim());

        organizador1.setDireccion(txtdireccion.getText().trim());
        organizador1.setPresupuesto(presupuesto);

        organizador1.setGenero(sexo.trim());

        base.set(organizador1);
        JOptionPane.showMessageDialog(null, "Modificacion exitosa");

        // spnedad.setValue(0);
        txtcorreo.setText("");
        jDateChooser1.setDate(null);
        txtcedula.setText("");
        txtnombre.setText("");
        txtapellido.setText("");
        txtcelular.setText("");
        txttelefono.setText("");
        txtdireccion.setText("");
        txtpresupuesto.setText("");
        rbnfemenino.setSelected(false);
        rbnmasculino.setSelected(false);

    }

    public void Validar() {

        if (txtcedula.getText().trim().isEmpty()) {
            lblcedula.setText("Campo obligatorio");
        } else {
            lblcedula.setText("");
        }

        if (txtcorreo.getText().trim().isEmpty()) {

            lblcorreo.setText("Campo obligatorio");
        } else {
            lblcorreo.setText(" ");
        }
        if (txtnombre.getText().trim().isEmpty()) {
            lblnombre.setText("Campo obligatorio");
        } else {
            lblnombre.setText("");
        }
        if (txtapellido.getText().trim().isEmpty()) {
            lblapellido.setText("Campo obligatorio");
        } else {
            lblapellido.setText(" ");
        }
        if (txtcelular.getText().trim().isEmpty()) {
            lblcelular.setText("Campo obligatorio");

        } else {
            lblcelular.setText("");

        }
        if (txttelefono.getText().trim().isEmpty()) {
            lbltelefono.setText("Campo obligatorio");

        } else {
            lbltelefono.setText("");

        }
        if (txtdireccion.getText().trim().isEmpty()) {
            lbldireccion.setText("Campo obligatorio");

        } else {
            lbldireccion.setText("");
        }
        if (txtpresupuesto.getText().trim().isEmpty()) {
            lblpresupuesto.setText("Campo obligatorio");

        } else {
            lblpresupuesto.setText("");
        }
        if (jDateChooser1.getDate() == null) {
            lblfecha.setText("Campo requerido");

        } else {
            lblfecha.setText(" ");
        }

        if (rbnfemenino.isSelected()) {
            lblgenero.setText(" ");
        } else if (rbnmasculino.isSelected()) {
            lblgenero.setText(" ");

        } else if (!rbnfemenino.isSelected() || !rbnmasculino.isSelected()) {
            lblgenero.setText("Campo requerido");
        }

        if (jDateChooser1.getDate() == null || txtpresupuesto.getText().trim().isEmpty() || txtdireccion.getText().trim().isEmpty() || txttelefono.getText().trim().isEmpty() || txtcelular.getText().trim().isEmpty()
                || (!rbnfemenino.isSelected() && !rbnmasculino.isSelected()) || txtapellido.getText().trim().isEmpty() || txtnombre.getText().trim().isEmpty() || txtcedula.getText().trim().isEmpty() || txtcorreo.getText().trim().isEmpty()) {
            btncrear.setEnabled(false);

        } else {
            btncrear.setEnabled(true);
        }
    }

    public void deshabilitarCampos() {

        txtnombre.setEnabled(false);
        txtapellido.setEnabled(false);
        txtcelular.setEnabled(false);
        txttelefono.setEnabled(false);
        txtdireccion.setEnabled(false);
        txtcorreo.setEnabled(false);
        txtpresupuesto.setEnabled(false);
        rbnfemenino.setEnabled(false);
        rbnmasculino.setEnabled(false);
        jDateChooser1.setEnabled(false);
    }

    public void habilitarCampos() {

        txtnombre.setEnabled(true);
        txtapellido.setEnabled(true);
        txtcelular.setEnabled(true);
        txttelefono.setEnabled(true);
        txtdireccion.setEnabled(true);
        txtcorreo.setEnabled(true);
        txtpresupuesto.setEnabled(true);
        rbnfemenino.setEnabled(true);
        rbnmasculino.setEnabled(true);
        jDateChooser1.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncrear;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblapellido;
    private javax.swing.JLabel lblcedula;
    private javax.swing.JLabel lblcelular;
    private javax.swing.JLabel lblcorreo;
    private javax.swing.JLabel lbldireccion;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lblgenero;
    private javax.swing.JLabel lblnombre;
    private javax.swing.JLabel lblpresupuesto;
    private javax.swing.JLabel lbltelefono;
    private javax.swing.JRadioButton rbnfemenino;
    private javax.swing.JRadioButton rbnmasculino;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtcedula;
    private javax.swing.JTextField txtcelular;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtpresupuesto;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables

    public boolean validarCampos() {
        Validaciones miValidaciones = new Validaciones();
        boolean ban_confirmar = true;

        if (txtcedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cédula del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.validarCedula(txtcedula.getText())) {
            JOptionPane.showMessageDialog(this, "Cédula incorrecta. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtnombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.ValidarNomApe(txtnombre.getText())) {
            JOptionPane.showMessageDialog(this, "Nombre incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtapellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el apellido del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.ValidarNomApe(txtapellido.getText())) {
            JOptionPane.showMessageDialog(this, "Apellido incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtcorreo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el correo del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.ValidarCorreo(txtcorreo.getText())) {
            JOptionPane.showMessageDialog(this, "Correo incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        // Validar otros campos aquí...
        if (txtcelular.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el celular del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.validarCedula(txtcelular.getText())) {
            JOptionPane.showMessageDialog(this, "Celular incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txttelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el Telefono del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.validarTelefono(txttelefono.getText())) {
            JOptionPane.showMessageDialog(this, "Telefono incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }
        if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ingrese una Fecha");
            ban_confirmar = false;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaComoCadena = sdf.format(jDateChooser1.getDate());

            if (!miValidaciones.validarFecha(fechaComoCadena)) {
                JOptionPane.showMessageDialog(this, "Fecha incorrecta. Ingrese de nuevo");
                ban_confirmar = false;
            }
        }

        if (txtdireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.ValidarNomApe(txtdireccion.getText())) {
            JOptionPane.showMessageDialog(this, "Nombre incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }
        return ban_confirmar;
    }

}
