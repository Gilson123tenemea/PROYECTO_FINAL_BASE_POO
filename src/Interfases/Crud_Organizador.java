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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Crud_Organizador extends javax.swing.JPanel {

    public static ArrayList<Organizador> listaagentes = new ArrayList<>();

    public static ArrayList<Organizador> codigoseliminados = new ArrayList<>();

    Boolean primeraMayusculaIngresada;

    public Crud_Organizador() {
        initComponents();
        btncrear.setEnabled(false);

        agrupar();

        // spnedad.setModel(new SpinnerNumberModel(0, 0, 60, 1));
    }

    public void agrupar() {

        ButtonGroup botones = new ButtonGroup();
        botones.add(rbnmasculino);
        botones.add(rbnfemenino);
    }

    private boolean esMayorDeEdad(Date fechaNacimiento) {
        Calendar calNacimiento = Calendar.getInstance();
        calNacimiento.setTime(fechaNacimiento);

        // Obtener la fecha actual
        Calendar calActual = Calendar.getInstance();

        // Calcular la diferencia en años
        int edad = calActual.get(Calendar.YEAR) - calNacimiento.get(Calendar.YEAR);

        // Verificar si ya ha pasado su cumpleaños de este año
        if (calActual.get(Calendar.DAY_OF_YEAR) < calNacimiento.get(Calendar.DAY_OF_YEAR)) {
            edad--;
        }

        // Verificar si la persona tiene al menos 18 años
        return edad >= 18;
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btncrear = new javax.swing.JButton();
        rbnmasculino = new javax.swing.JRadioButton();
        rbnfemenino = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        lblcorreo = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        txtcedula = new javax.swing.JTextField();
        lblapellido = new javax.swing.JLabel();
        lbltelefono = new javax.swing.JLabel();
        lblcelular = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblpresupuesto = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        lbldireccion = new javax.swing.JLabel();
        lblcedula = new javax.swing.JLabel();
        lblcod = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        txtcelular = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        lblgenero = new javax.swing.JLabel();
        txtpresupuesto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 12, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gestion-del-tiempo.png"))); // NOI18N
        jLabel1.setText("ORGANIZADORES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        jLabel2.setText("CEDULA:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        jLabel3.setText("NOMBRE:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));

        jLabel4.setText("APELLDO:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        jLabel5.setText("CELULAR:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, -1, -1));

        jLabel7.setText("DIRECCION:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, -1, 20));

        jLabel11.setText("TELEFONO");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, -1, -1));

        btncrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disco-flexible.png"))); // NOI18N
        btncrear.setText("CREAR");
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblcorreo.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 150, 20));

        lblnombre.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 150, 20));

        txtcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcedulaKeyTyped(evt);
            }
        });
        jPanel2.add(txtcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 160, 30));

        lblapellido.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 150, 20));

        lbltelefono.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lbltelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 150, 20));

        lblcelular.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblcelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 150, 20));

        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 170, 20));

        txtcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcorreoKeyReleased(evt);
            }
        });
        jPanel2.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 180, -1));

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 330, 180, -1));

        jLabel15.setText("FECHA DE NACIMIENTO:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, -1, 20));

        jLabel16.setText("PRESUPUESTO ANUAL:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, -1, 20));

        jLabel10.setText("CORREO:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, -1, 50));

        lblpresupuesto.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblpresupuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 180, 20));

        lblfecha.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, 180, 20));

        lbldireccion.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lbldireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 150, 20));

        lblcedula.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 150, 20));
        jPanel2.add(lblcod, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 140, 30));

        jLabel9.setText("GENERO:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 60, 40));

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });
        jPanel2.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 160, 30));

        txtapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellidoActionPerformed(evt);
            }
        });
        txtapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtapellidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidoKeyTyped(evt);
            }
        });
        jPanel2.add(txtapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 160, 30));

        txtcelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcelularActionPerformed(evt);
            }
        });
        txtcelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcelularKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcelularKeyTyped(evt);
            }
        });
        jPanel2.add(txtcelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 160, 30));

        txttelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoActionPerformed(evt);
            }
        });
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });
        jPanel2.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 160, 30));

        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdireccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });
        jPanel2.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 160, 30));

        lblgenero.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblgenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 160, 20));

        txtpresupuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpresupuestoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpresupuestoKeyTyped(evt);
            }
        });
        jPanel2.add(txtpresupuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 280, 180, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo Oficila de Eventos.PNG"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 190, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 920, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btncrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearActionPerformed
        // TODO add your handling code here:

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        crearOrganizador(base);
        base.close();

    }//GEN-LAST:event_btncrearActionPerformed

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

    private void txtcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyTyped
        char c = evt.getKeyChar();
        // Verificar si es un número y si la longitud actual es menor que 10
        if (!Character.isDigit(c) || txtcedula.getText().length() >= 10) {
            // Si no es un número o la longitud es mayor o igual a 10, se consume el evento para evitar que se agregue al campo de texto
            evt.consume();
        }               // TODO add your handling code here:
    }//GEN-LAST:event_txtcedulaKeyTyped

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
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoKeyTyped

    private void txtcelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcelularKeyTyped
        char c = evt.getKeyChar();
        // Verificar si es un número y si la longitud actual es menor que 10
        if (!Character.isDigit(c) || txtcelular.getText().length() >= 10) {
            // Si no es un número o la longitud es mayor o igual a 10, se consume el evento para evitar que se agregue al campo de texto
            evt.consume();
        }            // TODO add your handling code here:
    }//GEN-LAST:event_txtcelularKeyTyped

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
        char c = evt.getKeyChar();
        // Verificar si es un número y si la longitud actual es menor que 10
        if (!Character.isDigit(c) || txttelefono.getText().length() >= 7) {
            // Si no es un número o la longitud es mayor o igual a 10, se consume el evento para evitar que se agregue al campo de texto
            evt.consume();
        }            // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoKeyTyped

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

    private void txtpresupuestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpresupuestoKeyTyped
        char c = evt.getKeyChar();
        // Verificar si es un número y si la longitud actual es menor que 10
        if (!Character.isDigit(c) || txtpresupuesto.getText().length() >= 10) {
            // Si no es un número o la longitud es mayor o igual a 10, se consume el evento para evitar que se agregue al campo de texto
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_txtpresupuestoKeyTyped

    private void txtapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoActionPerformed

    private void txtcelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcelularActionPerformed

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoActionPerformed

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionActionPerformed

    public static boolean esNumeroDecimal(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void crearOrganizador(ObjectContainer base) {
        try {
            if (!validarCampos()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //ublic Organizador(String cod_organizador, String usuario, String contraseña, double Presupuesto, String cedula, String nombre, String apellido, String telefono, String correo, String direccion, String celular, Date fecchaNaci, String genero) {
            // Verificar si ya existe un Organizador con la misma cédula
            ObjectSet<Organizador> resul = base.queryByExample(new Organizador(null, null, null, 0.0, txtcedula.getText().trim(), null, null, null, null, null, null, null, null));
            if (!resul.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ya existe un organizador con la cédula ingresada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el último código
            resul = base.queryByExample(new Organizador(null, null, null, 0.0, null, null, null, null, null, null, null, null, null));
            int ultimoCodigo = resul.size() + 1;

            // Formatear el código con ceros a la izquierda
            String cod = String.format("%03d", ultimoCodigo);
            lblcod.setText(cod);

            // Obtener la información de género y fecha de nacimiento
            String sexo = rbnfemenino.isSelected() ? "Femenino" : "Masculino";

            Date fechaNacimiento = jDateChooser1.getDate();
            if (!esMayorDeEdad(fechaNacimiento)) {
                JOptionPane.showMessageDialog(this, "Debe ser mayor de 18 años para registrarse como organizador.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar presupuesto
            double presupuesto = 0.0;
            String valorIngresado = txtpresupuesto.getText().trim();

            if (!esNumeroDecimal(valorIngresado)) {
                JOptionPane.showMessageDialog(this, "El presupuesto ingresado no es un número decimal válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                presupuesto = Double.valueOf(valorIngresado);
            }

            // Resto del código...
            Validar();

            //ublic Organizador(String cod_organizador, String usuario, String contraseña, double Presupuesto, String cedula, String nombre, String apellido, String telefono, String correo, String direccion, String celular, Date fecchaNaci, String genero) {
            Organizador miorganizador = new Organizador(cod, null, null, presupuesto, txtcedula.getText().trim(), txtnombre.getText().trim(), txtapellido.getText().trim(), txttelefono.getText().trim(), txtcorreo.getText().trim(), txtdireccion.getText().trim(), txtcelular.getText().trim(), fechaNacimiento, sexo);

            base.store(miorganizador);

            JOptionPane.showMessageDialog(null, "Se guardaron los datos correctamente.");
        } finally {
            base.close();
        }

        // spnedad.setValue(0);
        txtcorreo.setText("".trim());
        jDateChooser1.setDate(null);
        txtcedula.setText("".trim());
        txtnombre.setText("".trim());
        txtapellido.setText("".trim());
        txtcelular.setText("".trim());
        txttelefono.setText("".trim());
        lblcod.setText(" ".trim());
        txtdireccion.setText("".trim());
        txtpresupuesto.setText("".trim());
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncrear;
    private javax.swing.ButtonGroup buttonGroup1;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblapellido;
    private javax.swing.JLabel lblcedula;
    private javax.swing.JLabel lblcelular;
    private javax.swing.JLabel lblcod;
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
