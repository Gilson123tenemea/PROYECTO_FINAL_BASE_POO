/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Encuesta;
import Clases.Evento;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CRUD_Encuesta extends javax.swing.JPanel {

    private TableRowSorter trs;

    public CRUD_Encuesta() {
        initComponents();
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        cargarTabla(base);

        base.close();
        cargar();
    }

    public void crearEncuesta(ObjectContainer base) {
        try {
            // Obtener el último código de Encuesta en la base de datos
            Query query = base.query();
            query.constrain(Encuesta.class);
            query.descend("cod_encuesta").orderDescending();
            ObjectSet<Encuesta> result = query.execute();

            int ultimoCodigo = 1; // Por defecto, si no hay registros previos
            if (!result.isEmpty()) {
                Encuesta ultimaEncuesta = result.next();
                // Obtener la parte numérica del código y convertir a entero
                String ultimoCodigoStr = ultimaEncuesta.getCod_encuesta().replaceAll("[^0-9]", "");
                ultimoCodigo = Integer.parseInt(ultimoCodigoStr) + 1;
            }

            // Formatear el código con ceros a la izquierda
            String nuevoCodigo = String.format("ENC-%03d", ultimoCodigo);
            txtcodigopersonal.setText(nuevoCodigo);

            // Verificar si ya existe una Encuesta con el mismo código
            result = base.queryByExample(new Encuesta(nuevoCodigo, null, null, null, null, null, null, null, null, null, null));

            if (!result.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ya existe una Encuesta con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el código del evento seleccionado en el ComboBox
            String codigoEvento = obtenerCodigoEventoSeleccionado();

            // Crear objeto Encuesta y almacenar en la base de datos
            Encuesta nuevaEncuesta = new Encuesta(
                    nuevoCodigo,
                    jTextField1.getText().trim(),
                    codigoEvento, // Utiliza solo el código del evento
                    jTextArea1.getText().trim(),
                    jDateChooser1.getDate(),
                    jDateChooser2.getDate(),
                    jTextField2.getText().trim(),
                    jTextField3.getText().trim(),
                    jTextField4.getText().trim(),
                    jTextField5.getText().trim(),
                    jTextField6.getText().trim()
            );

            base.store(nuevaEncuesta);

            JOptionPane.showMessageDialog(this, "Encuesta creada exitosamente");
            limpiar();
            cargarTabla(base);
        } finally {
            base.close();
        }
    }

    private String obtenerCodigoEventoSeleccionado() {
        String eventoSeleccionado = cboxevento.getSelectedItem().toString();

        // Asumiendo que el código del evento está al principio del string antes del espacio
        String[] partes = eventoSeleccionado.split(" ");

        if (partes.length > 0) {
            return partes[0];
        } else {
            return "";  // Ajusta esto según la estructura real de tu ComboBox
        }
    }

    public void cargarTabla(ObjectContainer base) {

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        ObjectSet<Encuesta> result = base.queryByExample(new Encuesta());

        while (result.hasNext()) {
            Encuesta personal1 = result.next();

            Object[] row = {
                personal1.getCod_encuesta(),
                personal1.getNombre_encuesta(),
                personal1.getTipo_evento(),
                personal1.getDescrpcion_encuesta(),
                personal1.getFecha_inicio(),
                personal1.getFecha_fin(),
                personal1.getPregunta1(),
                personal1.getPregunta2(),
                personal1.getPregunta3(),
                personal1.getPregunta4(),
                personal1.getPregunta5()

            };
            model.addRow(row);
        }

    }

    public void ActualizarDatos(ObjectContainer base) {
        // Verificar si todos los campos están llenos
        if (jTextField1.getText().trim().isEmpty() || jTextField2.getText().trim().isEmpty()
                || cboxevento.getSelectedItem() == null || jTextField3.getText().trim().isEmpty() || jTextField4.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Por favor llene en el campo del Codigo para la Modificacion", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Encuesta micasa = new Encuesta(txtcodigopersonal.getText().trim(), null, null, null, null, null, null, null, null, null, null);

            ObjectSet res = base.get(micasa);
            Encuesta micasita = (Encuesta) res.next();
            micasita.setNombre_encuesta(jTextField1.getText().trim());
            micasita.setDescrpcion_encuesta(jTextArea1.getText().trim());
            micasita.setPregunta1(jTextField2.getText().trim());
            micasita.setPregunta2(jTextField3.getText().trim());
            micasita.setPregunta3(jTextField4.getText().trim());
            micasita.setPregunta4(jTextField5.getText().trim());
            micasita.setPregunta5(jTextField6.getText().trim());
            micasita.setFecha_inicio(jDateChooser1.getDate());
            micasita.setFecha_fin(jDateChooser2.getDate());

            micasita.setTipo_evento(cboxevento.getSelectedItem().toString());

            base.set(micasita);

            JOptionPane.showMessageDialog(this, "Modificación exitosa");
            limpiar();

        } finally {
            base.close();
        }
    }

    public void limpiar() {
        txtcodigopersonal.setText("");
        jTextField1.setText("");
        jTextArea1.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField2.setText("");
        cboxevento.setSelectedItem("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);

    }

    private void buscarActividad(ObjectContainer base) {
        String codigoBusqueda = JOptionPane.showInputDialog(this, "Ingrese el código de la Encuesta a buscar:", "Buscar Actividad", JOptionPane.QUESTION_MESSAGE);

        if (codigoBusqueda != null && !codigoBusqueda.isEmpty()) {
            ObjectSet<Encuesta> result = base.queryByExample(new Encuesta(codigoBusqueda, null, null, null, null, null, null, null, null, null, null));

            if (!result.isEmpty()) {
                Encuesta actividadEncontrada = result.next();
                cargarDatosActividad(actividadEncontrada);
                limpiarTabla();
                cargarTabla(base, actividadEncontrada);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna actividad con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        base.close();
    }

    private void cargarDatosActividad(Encuesta actividad) {
        txtcodigopersonal.setText(actividad.getCod_encuesta());
        jTextField1.setText(actividad.getNombre_encuesta());
        jTextArea1.setText(actividad.getDescrpcion_encuesta());
        jTextField2.setText(actividad.getPregunta1());
        jTextField3.setText(actividad.getPregunta2());
        jTextField4.setText(actividad.getPregunta3());
        jTextField5.setText(actividad.getPregunta4());
        jTextField6.setText(actividad.getPregunta5());
        jDateChooser1.setDate(actividad.getFecha_inicio());
        jDateChooser1.setDate(actividad.getFecha_fin());

        cboxevento.setSelectedItem(actividad.getTipo_evento());

    }

    private void cargarTabla(ObjectContainer base, Encuesta actividadFiltrada) {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        Object[] row = {
            actividadFiltrada.getCod_encuesta(),
            actividadFiltrada.getNombre_encuesta(),
            actividadFiltrada.getTipo_evento(),
            actividadFiltrada.getDescrpcion_encuesta(),
            actividadFiltrada.getFecha_inicio(),
            actividadFiltrada.getFecha_fin(),
            actividadFiltrada.getPregunta1(),
            actividadFiltrada.getPregunta2(),
            actividadFiltrada.getPregunta3(),
            actividadFiltrada.getPregunta4(),
            actividadFiltrada.getPregunta5(),};
        model.addRow(row);

        base.close();
    }

    private void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
    }

    public void cargar() {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        try {
            cboxevento.removeAllItems();
            Query query = base.query();
            query.constrain(Evento.class);

            ObjectSet<Evento> eventos = query.execute();

            cboxevento.addItem("Seleccione");
            while (eventos.hasNext()) {
                Evento tipoEvento = eventos.next();
                cboxevento.addItem(tipoEvento.toString());
            }
        } finally {
            base.close();
        }
    }

    private void mostrarDatosEventos(ObjectContainer bases) {
        try {
            Object selectedItem = cboxevento.getSelectedItem();

            if (selectedItem != null) {
                String cedulaSeleccionada = selectedItem.toString();

                Query query = bases.query();
                query.constrain(Evento.class);
                query.descend("cod_evento").constrain(cedulaSeleccionada);
                ObjectSet<Evento> result = query.execute();

                if (!result.isEmpty()) {
                    Evento patro = result.next();

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                    String mensaje = "Nombre: " + patro.getNombre() + "\n"
                            + "Descripcion: " + patro.getDescripcion() + "\n"
                            + "Codigo de Patrocinador: " + patro.getCodigo_patrocinador() + "\n"
                            + "Codigo de Agenda: " + patro.getCodigo_agenda() + "\n"
                            + "Tipo: " + patro.getTipo() + "\n"
                            + "Fecha de Inicio: " + formatoFecha.format(patro.getFecha_inicio()) + "\n"
                            + "Fecha de Fin: " + formatoFecha.format(patro.getFecha_fin()) + "\n"
                            + "Hora de Inicio: " + patro.getHora_inicio() + "\n"
                            + "Hora Fin: " + patro.getHora_fin();

                    JOptionPane.showMessageDialog(this, mensaje, "Datos del Tipo de Evento", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró un Tipo de Evento con el código seleccionado.", "Patrocinador no encontrado", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún código.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al mostrar datos del Tipo de Evento.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            bases.close();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        cboxevento = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        txtcodigopersonal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        cboxbusqueda = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(926, 926));
        jPanel3.setPreferredSize(new java.awt.Dimension(923, 650));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 250, -1));

        cboxevento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboxeventoMouseClicked(evt);
            }
        });
        jPanel3.add(cboxevento, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 180, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/votacion-en-linea.png"))); // NOI18N
        jLabel1.setText("ENCUESTA");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        jLabel2.setText("Código Encuesta:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));
        jPanel3.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, 240, -1));

        jLabel3.setText("Descripción: ");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabel4.setText("Tipo de Evento: ");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));
        jPanel3.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 240, -1));

        jLabel5.setText("Preguntas del Evento: ");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        jLabel6.setText("Fecha Inicio:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, 30));

        jLabel7.setText(" Fecha Fin:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, -1, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lOGO1.png"))); // NOI18N
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, -1, 150));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo Encuesta", "Nombre Encuesta", "Tipo Evento", "Descripcion", "Fecha Inicio", "Fecha Fin", "Pregunta 1", "Pregunta 2", "Pregunta 3", "Pregunta 4", "Pregunta 5"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 600, 860, 150));

        jLabel9.setText("Pregunta 1");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, -1, -1));

        jLabel10.setText("Pregunta 2");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, -1, -1));

        jLabel11.setText("Pregunta 3");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, -1, -1));

        jLabel12.setText("Pregunta 4");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, -1, -1));

        jLabel13.setText("Pregunta 5");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 460, -1, -1));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 540, -1));

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 540, -1));

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 540, -1));

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 540, -1));

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, 540, -1));
        jPanel3.add(txtcodigopersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 180, 20));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar (1).png"))); // NOI18N
        jButton2.setText("MODIFICAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 540, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-eliminar (1).png"))); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 540, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/curriculum.png"))); // NOI18N
        jButton4.setText("REPORTE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 540, -1, -1));

        jLabel14.setText("Nombre Encuesta");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 180, -1));

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda.png"))); // NOI18N
        jButton12.setBorder(null);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/hpermetropia.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, -1, -1));

        cboxbusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Nombre" }));
        cboxbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxbusquedaActionPerformed(evt);
            }
        });
        jPanel3.add(cboxbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, -1, -1));

        jLabel15.setText("Buscar por:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 250, 70, 20));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/secretario.png"))); // NOI18N
        jButton6.setText("LIMPIAR CAMPOS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 540, 170, 40));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 927, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 777, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboxeventoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboxeventoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxeventoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        crearEncuesta(base);
        base.close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        ActualizarDatos(base);
        base.close();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String codigoEliminar = JOptionPane.showInputDialog("Ingrese el código de la Encuesta a eliminar");
        boolean encontrado = false;

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        Query query = base.query();
        query.constrain(Encuesta.class);
        query.descend("Cod_encuesta").constrain(codigoEliminar);

        ObjectSet<Encuesta> result = query.execute();
        cargarTabla(base);

        if (result.size() > 0) {
            encontrado = true;

            int resul = JOptionPane.showConfirmDialog(null, "Deseas eliminar los datos de la Encuesta ", "Confirmacion", JOptionPane.YES_NO_OPTION);

            if (resul == JOptionPane.YES_OPTION) {
                for (Encuesta vacacionalDB : result) {
                    // Eliminar la Casa Vacacional de la base de datos db4o
                    base.delete(vacacionalDB);
                    JOptionPane.showMessageDialog(null, "Se están borrando los datos de la Encuesta");
                    cargarTabla(base);
                }
            } else if (resul == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Datos de la Encuesta no eliminados");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el código");
            cargarTabla(base);
        }

        base.close();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        cargarTabla(base);

        base.close();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        buscarActividad(base);
        base.close();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ObjectContainer bases = Db4o.openFile(Inicio.direccion);
        mostrarDatosEventos(bases);
        bases.close();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("nombre")) {

            jTextField1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (jTextField1.getText());

                    jTextField1.setText(cadena);
                    Filtro();

                }

            });

        }

        trs = new TableRowSorter(jTable2.getModel());
        jTable2.setRowSorter(trs);

        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && jTextField1.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            jTextField1.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            jTextField1.setText(jTextField1.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (jTextField1.getText().length() > 19) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField1KeyTyped

    private void cboxbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxbusquedaActionPerformed

        String criterioSeleccionado = cboxbusqueda.getSelectedItem().toString();
        habilitarCamposBusqueda(criterioSeleccionado);
    }//GEN-LAST:event_cboxbusquedaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cboxevento.setEnabled(true);
        jTextArea1.setEnabled(true);
        jDateChooser1.setEnabled(true);
        jDateChooser2.setEnabled(true);
        jTextField2.setEnabled(true);
        jTextField3.setEnabled(true);
        jTextField4.setEnabled(true);
        jTextField5.setEnabled(true);
        jTextField6.setEnabled(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && jTextField2.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            jTextField2.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            jTextField2.setText(jTextField2.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (jTextField2.getText().length() > 400) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && jTextField3.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            jTextField3.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            jTextField3.setText(jTextField3.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (jTextField3.getText().length() > 400) {
            evt.consume();
        } // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && jTextField4.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            jTextField4.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            jTextField4.setText(jTextField4.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (jTextField4.getText().length() > 19) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && jTextField5.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            jTextField5.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            jTextField5.setText(jTextField5.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (jTextField5.getText().length() > 19) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && jTextField6.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            jTextField6.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            jTextField6.setText(jTextField6.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (jTextField6.getText().length() > 19) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6KeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboxbusqueda;
    private javax.swing.JComboBox<String> cboxevento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel txtcodigopersonal;
    // End of variables declaration//GEN-END:variables

    public void Filtro() {

        if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("nombre")) {
            int Columnastabla = 1;
            trs.setRowFilter(RowFilter.regexFilter(jTextField1.getText().trim(), Columnastabla));
        }
    }

    private void habilitarCamposBusqueda(String criterioSeleccionado) {
        deshabilitarParametros();
        if (criterioSeleccionado.equals("nombre")) {
            jTextField1.setEnabled(true);
        }
    }

    public void deshabilitarParametros() {
        cboxevento.setEnabled(false);
        jTextArea1.setEnabled(false);
        jDateChooser1.setEnabled(false);
        jDateChooser2.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jTextField5.setEnabled(false);
        jTextField6.setEnabled(false);

    }

}
