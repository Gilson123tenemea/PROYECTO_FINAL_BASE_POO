/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Asistencia;
import Clases.Evento;
import Clases.Tipo_evento;
import static Interfases.Menu_Cliente.codigotipo;
import static Interfases.Menu_Cliente.tip;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Eventos extends javax.swing.JFrame {

    private List<JButton> botones;
    private int indice;
    private boolean asistenciaConfirmada = false;
    private boolean encuestaRealizada = false;
    private boolean calificacionRealizada = false;
    byte[] foto1;

    String tipo, nombre, codigo;

    public Eventos(String tipo) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.tipo = tipo;
        String tip = tipo.substring(0, 7);
        indice = 0;
        botones = new ArrayList<>();
        ObtenerEvento(tip.toLowerCase());

        txtNombre.setText(Inicio.nombre);
        txtApellido.setText(Inicio.apellido);
        extraerfechaactu();

        Date fecha = new Date();
        LocalDate fechaLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("Fecha actual: " + fechaLocal);
    }

    public void extraerfechaactu() {
        LocalDate fechaFinEvento = LocalDate.of(2024, 12, 31);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaFinEvento.format(formato);
        txtFechaactual.setText(fechaFormateada);

        LocalDate fechaActual = LocalDate.now();
        if (fechaFinEvento.isBefore(fechaActual)) {
            System.out.println("La fecha fin ya pasó.");
        } else {
            System.out.println("La fecha fin aún no ha pasado.");
        }

    }

    private void setButtonColor(JButton button, Color color) {
        button.setOpaque(true);
        button.setBackground(color);
        button.setForeground(Color.white);
    }

    public void ObtenerEvento(String tipo) {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        LocalDate fechaActual = LocalDate.now();

        try {
            Query query = base.query();
            query.constrain(Evento.class);
            query.descend("tipo").constrain(tipo);
            ObjectSet<Evento> result = query.execute();

            if (result.size() == 0) {
                JOptionPane.showMessageDialog(null, "No existen eventos vinculados al tipo de evento");
                Eventos.this.dispose();
            }

            if (!result.isEmpty()) {
                for (Evento tipoevento1 : result) {
                    String nom = tipoevento1.getNombre();
                    String cod = tipoevento1.getCod_evento();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaf = tipoevento1.getFecha_inicio();
                    String fechai = formato.format(fechaf);

                    String horaini = tipoevento1.getHora_inicio();
                    Date fechafin = tipoevento1.getFecha_fin();
                    String horafi = tipoevento1.getHora_fin();

                    String fechafi = formato.format(fechafin);

                    JButton boton = new JButton(tipoevento1.getCod_evento() + " " + tipoevento1.getNombre());
                    boton.setSize(200, 200);
                    LocalDate fechaFinLocal = fechafin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (fechaFinLocal.isBefore(fechaActual)) {
                        boton.setBackground(Color.GREEN);
                    } else {

                    }

                    byte[] foto = tipoevento1.getData();
                    if (foto != null) {
                        ImageIcon iconoOriginal = new ImageIcon(foto);
                        int nuevaAnchura = 200;
                        int nuevaAltura = -1;
                        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                                nuevaAnchura, nuevaAltura, Image.SCALE_SMOOTH);
                        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

                        boton.setIcon(iconoEscalado);
                        panel.add(boton);

                        boton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JButton btn1 = new JButton("Quiero asistir");
                                btn1.setBackground(Color.red);
                                btn1.setForeground(Color.white);

                                JButton btn2 = new JButton("Encuesta");
                                btn2.setBackground(Color.yellow);
                                btn2.setForeground(Color.white);

                                JButton btn3 = new JButton("Califica el evento");
                                btn3.setBackground(Color.green);
                                btn3.setForeground(Color.white);

                                JButton btn4 = new JButton("Mi blog");
                                btn4.setBackground(Color.orange);
                                btn4.setForeground(Color.white);

                                String[] arreglo = {"Quiero asistir", "Encuesta", "Califica el evento", "Mi blog"};

                                // Construir el mensaje con formato
                                StringBuilder mensaje = new StringBuilder();
                                mensaje.append("Evento: ").append(nom).append("\n");
                                mensaje.append("Fecha de inicio: ").append(fechai).append("\n");
                                mensaje.append("Fecha de fin: ").append(fechafi).append("\n");
                                mensaje.append("Hora de inicio: ").append(horaini).append("\n");
                                mensaje.append("Hora final: ").append(horafi).append("\n");
                                UIManager.put("Button.background", Color.YELLOW);
                                int opcion = JOptionPane.showOptionDialog(
                                        null,
                                        mensaje.toString(),
                                        "Escoje un botón...",
                                        0,
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        arreglo,
                                        arreglo[0] 
                                );

                                UIManager.put("Button.background", UIManager.get("OptionPane.background"));

                                if (opcion >= 0) {
                                    switch (opcion) {
                                        case 0:
                                            if (!asistenciaConfirmada) {
                                                asistenciaConfirmada = true;
                                                boolean asistir = true;
                                                String publico = Inicio.nombre + " " + Inicio.apellido;
                                                ConfirmarAsistencia(asistir, nom, publico);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Ya has confirmado asistencia para este evento.");
                                            }
                                            break;
                                        case 1:
                                            if (!encuestaRealizada) {
                                                encuestaRealizada = true;
                                                Eventos.this.dispose();
                                                Encuesta_Pregunt let = new Encuesta_Pregunt(cod);
                                                let.setVisible(true);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Ya has realizado la encuesta para este evento.");
                                            }
                                            break;
                                        case 2:
                                            if (!calificacionRealizada) {
                                                calificacionRealizada = true;
                                                Eventos.this.dispose();
                                                Calificar cal = new Calificar(cod);
                                                cal.setVisible(true);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Ya has realizado la calificación para este evento.");
                                            }
                                            break;
                                        case 3:
                                            Eventos.this.dispose();
                                            Blog_publico blog = new Blog_publico(cod);
                                            blog.setVisible(true);
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(null, "Escoja una opcion");
                                    }
                                } else {

                                }
                            }
                        });
                    }
                }
                panel.updateUI();
            }
        } finally {
            this.dispose();
            base.close();
        }
    }

    public void ConfirmarAsistencia(boolean asistencia, String nombreEvento, String publi) {
        if (asistencia) {
            ObjectContainer base = Db4o.openFile(Inicio.direccion);

            Query query = base.query();
            query.constrain(Asistencia.class
            );
            query.descend("cod_aistencia").orderDescending();
            ObjectSet<Asistencia> result = query.execute();

            int ultimoCodigo = 1;
            if (!result.isEmpty()) {
                Asistencia ultimoAsistencia = result.next();

                try {
                    ultimoCodigo = Integer.parseInt(ultimoAsistencia.getCod_aistencia().substring(4)) + 1;
                } catch (NumberFormatException e) {
                    System.err.println("Error al convertir el código a un entero: " + e.getMessage());
                }
            }

            String nuevoCodigo = String.format("ASI-%03d", ultimoCodigo);
            Asistencia nuevaAsistencia = new Asistencia(nuevoCodigo, nombreEvento, publi);

            base.store(nuevaAsistencia);
            JOptionPane.showMessageDialog(null,
                    "<html><style>body {color: green; font-size: 15px;}</style>"
                    + "<body>Le esperamos en el evento !</body></html>");

            System.out.println("Asistencia confirmada: " + asistencia + ", Evento: " + nombreEvento + ", Cliente: " + publi);

            base.commit();
            base.close();
        }
    }

//    private void mostrarInformacionEvento(Evento evento) {
//        String mensaje = "Nombre4: " + evento.getNombre() + "\n"
//                + "Descripción: " + evento.getDescripcion() + "\n"
//                + "Fecha de inicio: " + evento.getFecha_inicio() + "\n"
//                + "Fecha de fin: " + evento.getFecha_fin() + "\n"
//                + "Hora de inicio: " + evento.getHora_inicio() + "\n"
//                + "Hora de fin: " + evento.getHora_fin() + "\n"
//                + "Precio: " + evento.getPrecio() + "\n"
//                + "Número de puestos: " + evento.getNum_puestos();
//
//        // Agregar un botón personalizado
//        Object[] options = {"Quiero asistir", "Cancelar"};
//        int choice = JOptionPane.showOptionDialog(
//                null,
//                mensaje,
//                "Información del evento",
//                JOptionPane.YES_NO_OPTION,
//                JOptionPane.INFORMATION_MESSAGE,
//                null,
//                options,
//                options[0]
//        );
//
//        // Manejar la elección del usuario
//        if (choice == JOptionPane.YES_OPTION) {
//            JOptionPane.showMessageDialog(null, "¡Les esperamos en el evento!");
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JLabel();
        txtApellido = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtFechaactual = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenu6.setText("jMenu6");

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setLayout(new java.awt.GridLayout(0, 3));
        jScrollPane1.setViewportView(panel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 940, 530));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paso-a-paso.png"))); // NOI18N
        jButton1.setText("Eventos Próximos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 590, 160, 40));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/no.png"))); // NOI18N
        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 590, 160, 40));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 580, 10, 70));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 10, 20));

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 640, 160, 10));

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 640, 160, 10));

        txtNombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre.setText("jLabel1");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, -1, 20));

        txtApellido.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(255, 255, 255));
        txtApellido.setText("jLabel2");
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, -1, 20));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EVENTOS DISPONIBLES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 170, -1));
        jPanel1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 220, 10));

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 10, 20));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BIENVENIDO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, -1, -1));

        txtFechaactual.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtFechaactual.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtFechaactual, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 130, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
        Menu_Cliente men = new Menu_Cliente();
        men.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel panel;
    private javax.swing.JLabel txtApellido;
    private javax.swing.JLabel txtFechaactual;
    private javax.swing.JLabel txtNombre;
    // End of variables declaration//GEN-END:variables
}
