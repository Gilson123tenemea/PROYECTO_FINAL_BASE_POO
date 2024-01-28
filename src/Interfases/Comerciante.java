/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Comerciantes;
import Clases.Puesto;
import Clases.Tipo_Comercio;
import Clases.Validaciones;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import com.db4o.Db4o;
import java.time.LocalDate;
import java.util.Calendar;

/**
 *
 * @author ADMIN_01
 */
public class Comerciante extends javax.swing.JFrame {

    public static ArrayList<Comerciantes> listaagentes = new ArrayList<>();

    public static ArrayList<Comerciantes> codigoseliminados = new ArrayList<>();
    String sexo;

    public Comerciante() {
        initComponents();
        Agrupar();
        setLocationRelativeTo(null);
        ObjectContainer bases = Db4o.openFile(Inicio.direccion);
        cargarTipoComercio(bases);
        cargarPuesto();
        bases.close();

    }

    public void Agrupar() {

        ButtonGroup mibuton = new ButtonGroup();
        mibuton.add(rbnMasculino);
        mibuton.add(rbnFemenino);

    }

    public void crearOrganizador(ObjectContainer base) {
        try {
            // Validar campos antes de continuar
            if (!validarCampos()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el último código
            ObjectSet<Comerciantes> result = base.queryByExample(new Comerciantes());
            int ultimoCodigo = result.size() + 1;

            // Formatear el código con ceros a la izquierda
            String cod = String.format("%03d", ultimoCodigo);
            lblCodigoComerciante.setText("COM-" + cod);

            String seleccion = null;
            Date nacimiento = null;

            // Establecer formato
            Date fecha = jDateFecha.getDate();

            if (fecha != null) {
                seleccion = new SimpleDateFormat("dd/MM/yyyy").format(fecha);
            } else {
                System.out.println("Fecha no seleccionada");
            }

            // Convertir el String seleccionado a Date
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                nacimiento = format.parse(seleccion);

                // Validar edad (mayor a 18 años)
                if (!esMayorDeEdad(nacimiento)) {
                    JOptionPane.showMessageDialog(this, "El organizador debe ser mayor de 18 años.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            String sexo = " ";
            if (rbnMasculino.isSelected()) {
                sexo = "Femenino";
            } else if (rbnFemenino.isSelected()) {
                sexo = "Masculino";
            }

            // Incremental code
            // Crear una instancia de Comerciantes con la cédula para verificar la existencia
            Comerciantes exampleComerciante = new Comerciantes(null, null, null, null, null, txtCedula.getText().trim(), null, null, null, null, null, null, null, null);

            // Consultar la base de datos para verificar la existencia de registros
            ObjectSet<Comerciantes> existingRecords = base.queryByExample(exampleComerciante);

            if (!existingRecords.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ya existe un comerciante con la cédula ingresada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Resto del código...
            // Crear y almacenar el organizador
            Comerciantes miorganizador = new Comerciantes(
                    lblCodigoComerciante.getText().trim(),
                    txtTipoComercio.getSelectedItem().toString(),
                    txtProductosC.getText().trim(),
                    txtServiciosC.getText().trim(),
                    cboCodigoPuesto.getSelectedItem().toString(),
                    txtCedula.getText().trim(),
                    txtNombre.getText().trim(),
                    txtApellido.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtEmail.getText().trim(),
                    txtDireccion.getText().trim(),
                    txtCelular.getText().trim(),
                    nacimiento,
                    sexo
            );

            base.store(miorganizador);

            JOptionPane.showMessageDialog(null, "Solicitud Enviada");
        } finally {
            base.close();
        }

        txtCedula.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtTipoComercio.setSelectedItem("");
        rbnMasculino.setSelected(false);
        rbnFemenino.setSelected(false);
        jDateFecha.setDate(null);
        lblCodigoComerciante.setText("");
        txtProductosC.setText("");
        txtServiciosC.setText("");
        cboCodigoPuesto.setSelectedItem("");
        txtCelular.setText("");
    }

    public void cargarPuesto() {

        ObjectContainer Base = Db4o.openFile(Inicio.direccion);

        cboCodigoPuesto.removeAllItems();
        Query query = Base.query();
        query.constrain(Puesto.class);

        ObjectSet<Puesto> puesto = query.execute();

        if (puesto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No existen Puestos Disponibles", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            while (puesto.hasNext()) {
                Puesto pu = puesto.next();
                cboCodigoPuesto.addItem(pu.getNombrePuesto());
            }
        }
        Base.close();
    }

    private void mostrarDatosPuestoSeleccionado(ObjectContainer bases) {
        String nombreSeleccionada = cboCodigoPuesto.getSelectedItem().toString();
        Query query = bases.query();
        query.constrain(Puesto.class);

        query.descend("Codigo_puesto").constrain(nombreSeleccionada);
        ObjectSet<Puesto> result = query.execute();

        if (!result.isEmpty()) {
            Puesto pues = result.next();
            String mensaje = "Nombre: " + pues.getNombrePuesto() + "\n"
                    + "Tipo: " + pues.getTipo_puesto() + "\n"
                    + "Descripcion: " + pues.getDescripcionPuesto();

            JOptionPane.showMessageDialog(this, mensaje, "Datos del Puesto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No hay puestos con información que mostrar.", "Puestos no encontrados", JOptionPane.WARNING_MESSAGE);
        }
        bases.close();
    }

    public void cargarTipoComercio(ObjectContainer Base) {
        txtTipoComercio.removeAllItems();  // Asegúrate de limpiar el ComboBox correcto
        Query query = Base.query();
        query.constrain(Tipo_Comercio.class);

        ObjectSet<Tipo_Comercio> comercio = query.execute();

        if (comercio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No existen Tipos de comercios ingresados", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            while (comercio.hasNext()) {
                Tipo_Comercio com = comercio.next();
                txtTipoComercio.addItem(com.getNombre());
            }
        }
        Base.close();
    }

    public boolean validarCampos() {
        Validaciones miValidaciones = new Validaciones();
        boolean ban_confirmar = true;

        if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cédula del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.validarCedula(txtCedula.getText())) {
            JOptionPane.showMessageDialog(this, "Cédula incorrecta. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.ValidarNomApe(txtNombre.getText())) {
            JOptionPane.showMessageDialog(this, "Nombre incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el apellido del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.ValidarNomApe(txtApellido.getText())) {
            JOptionPane.showMessageDialog(this, "Apellido incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el correo del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.ValidarCorreo(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Correo incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        // Validar otros campos aquí...
        if (txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el celular del cliente");
            ban_confirmar = false;
        } else if (!miValidaciones.validarCedula(txtTelefono.getText())) {
            JOptionPane.showMessageDialog(this, "Celular incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }
        if (jDateFecha.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ingrese una Fecha");
            ban_confirmar = false;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaComoCadena = sdf.format(jDateFecha.getDate());

            if (!miValidaciones.validarFecha(fechaComoCadena)) {
                JOptionPane.showMessageDialog(this, "Fecha incorrecta. Ingrese de nuevo");
                ban_confirmar = false;
            }
        }
        return ban_confirmar;
    }

    public void limpiarCampos() {
        txtCedula.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtTipoComercio.setSelectedItem("");
        rbnMasculino.setSelected(false);
        rbnFemenino.setSelected(false);
        jDateFecha.setDate(null);
        lblCodigoComerciante.setText("");
        txtProductosC.setText("");
        txtServiciosC.setText("");
        cboCodigoPuesto.setSelectedItem("");
        txtCelular.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgSexo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtTelefono = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtServiciosC = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtProductosC = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cboCodigoPuesto = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        rbnMasculino = new javax.swing.JRadioButton();
        rbnFemenino = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jDateFecha = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        btnVer = new javax.swing.JButton();
        lblCodigoComerciante = new javax.swing.JLabel();
        txtTipoComercio = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Cedula:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setText("Apellido:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setText("Email:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setText("Telefono:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setText("Tipo Comercio:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setText("Productos:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel8.setText("Servicios:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, -1, -1));
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 170, -1));

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 170, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 170, -1));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 170, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/REGRESAR.jpg"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 60, 50));

        jPanel2.setBackground(new java.awt.Color(0, 102, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 320, 10, 300));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 610, 360, 10));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lOGO1.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 450, -1, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel10.setText("REGISTRO DEL COMERCIANTE");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 153, 153));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 370, 10));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 170, -1));

        txtServiciosC.setColumns(20);
        txtServiciosC.setRows(5);
        txtServiciosC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtServiciosCKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(txtServiciosC);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 220, -1));

        txtProductosC.setColumns(20);
        txtProductosC.setRows(5);
        txtProductosC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductosCKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtProductosC);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 220, -1));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disco-flexible.png"))); // NOI18N
        btnGuardar.setText("SOLICITAR PUESTO");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 510, 190, 50));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel11.setText("Tipo de Puesto: ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, -1, -1));

        cboCodigoPuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboCodigoPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCodigoPuestoActionPerformed(evt);
            }
        });
        jPanel1.add(cboCodigoPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 370, 160, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel12.setText("Género:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, -1, -1));

        rbnMasculino.setText("Masculino");
        rbnMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnMasculinoActionPerformed(evt);
            }
        });
        jPanel1.add(rbnMasculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, -1, -1));

        rbnFemenino.setText("Femenino");
        jPanel1.add(rbnFemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, -1, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel13.setText("Código Comerciante: ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel14.setText("Fecha de Nacimiento: ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, -1, -1));
        jPanel1.add(jDateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 170, -1));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel15.setText("Dirección:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 294, -1, 20));

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 170, -1));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel16.setText("Celular:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 420, -1, -1));
        jPanel1.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 160, -1));

        btnVer.setBackground(new java.awt.Color(255, 255, 255));
        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/hpermetropia.png"))); // NOI18N
        btnVer.setBorder(null);
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });
        jPanel1.add(btnVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 360, 40, 40));
        jPanel1.add(lblCodigoComerciante, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 150, 20));

        txtTipoComercio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jPanel1.add(txtTipoComercio, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 170, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/hpermetropia.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbnMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnMasculinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbnMasculinoActionPerformed
// Método para validar si la fecha de nacimiento indica que la persona es mayor de 18 años

    // Método para validar si la fecha de nacimiento indica que la persona es mayor de 18 años
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
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Inicio le = new Inicio();
        le.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed

        ObjectContainer bases = Db4o.openFile(Inicio.direccion);
        mostrarDatosPuestoSeleccionado(bases);
        bases.close();
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        ObjectContainer bases = Db4o.openFile(Inicio.direccion);
        crearOrganizador(bases);
        bases.close();


    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ObjectContainer bases = Db4o.openFile(Inicio.direccion);
        mostrarDatosTipoComercio(bases);
        bases.close();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cboCodigoPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCodigoPuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCodigoPuestoActionPerformed

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txtApellido.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtApellido.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txtApellido.setText(txtApellido.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtApellido.getText().length() > 19) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
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
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txtDireccion.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtDireccion.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txtDireccion.setText(txtDireccion.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtDireccion.getText().length() > 19) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtProductosCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductosCKeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txtProductosC.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtProductosC.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txtProductosC.setText(txtProductosC.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtProductosC.getText().length() > 200) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductosCKeyTyped

    private void txtServiciosCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServiciosCKeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txtServiciosC.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtServiciosC.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txtServiciosC.setText(txtServiciosC.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtServiciosC.getText().length() > 250) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServiciosCKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Comerciante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comerciante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comerciante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comerciante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Comerciante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgSexo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> cboCodigoPuesto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCodigoComerciante;
    private javax.swing.JRadioButton rbnFemenino;
    private javax.swing.JRadioButton rbnMasculino;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtProductosC;
    private javax.swing.JTextArea txtServiciosC;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JComboBox<String> txtTipoComercio;
    // End of variables declaration//GEN-END:variables

    private void mostrarDatosTipoComercio(ObjectContainer bases) {
        String nombreSeleccionada = txtTipoComercio.getSelectedItem().toString();
        Query query = bases.query();
        query.constrain(Tipo_Comercio.class);

        query.descend("Cod_tipocomercion").constrain(nombreSeleccionada);
        ObjectSet<Tipo_Comercio> result = query.execute();

        if (!result.isEmpty()) {
            Tipo_Comercio pues = result.next();
            String mensaje = "Nombre: " + pues.getNombre() + "\n"
                    + "Descripcion: " + pues.getDescripcion();

            JOptionPane.showMessageDialog(this, mensaje, "Datos del Tipo de Comercio", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No hay puestos con información que mostrar.", "Tipos de Comercios no encontrados", JOptionPane.WARNING_MESSAGE);
        }
        bases.close();
    }

}
