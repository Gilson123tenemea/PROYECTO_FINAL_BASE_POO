/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfases;

import Clases.Evento;
import Clases.Tipo_evento;
import static Interfases.Menu_Cliente.codigotipo;
import static Interfases.Menu_Cliente.tip;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN_01
 */
public class Eventos extends javax.swing.JFrame {

    private List<JButton> botones;
    private int indice;

    byte[] foto1;

    String tipo, nombre, codigo;

    public Eventos(String tipo) {
        initComponents();
        this.tipo = tipo;
        String tip = tipo.substring(0, 7);
        indice = 0;
        botones = new ArrayList<>();
        ObtenerEvento(tip.toLowerCase());

        txtNombre.setText(Inicio.nombre);
        //txtApellido.setText(Inicio.apellido);

    }

    public void ObtenerEvento(String tipo) {
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        Query query = base.query();
        query.constrain(Evento.class);
        query.descend("tipo").constrain(tipo);
        ObjectSet<Evento> result = query.execute();

        if (result.size() == 0) {
            JOptionPane.showMessageDialog(null, "No existen eventos vinculados al tipo de evento");
            Eventos.this.dispose();

            base.close();

        }

        if (!result.isEmpty()) {
            int indice = 0;
            for (Evento tipoevento1 : result) {
                String nom = tipoevento1.getNombre();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaf = tipoevento1.getFecha_inicio();
                String fechai = formato.format(fechaf);

                String horaini = tipoevento1.getHora_inicio();
                Date fechafin = tipoevento1.getFecha_fin();
                String horafi = tipoevento1.getHora_fin();

                String fechafi = formato.format(fechafin);

                System.out.println("Adding button for evento: " + tipoevento1.getCod_evento() + " " + tipoevento1.getNombre());
                byte[] foto = tipoevento1.getData();
                if (foto != null) {
                    ImageIcon iconoOriginal = new ImageIcon(foto);
                    int nuevaAnchura = 200;
                    int nuevaAltura = -1;
                    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                            nuevaAnchura, nuevaAltura, Image.SCALE_SMOOTH);
                    ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

                    JButton boton = new JButton(tipoevento1.getCod_evento() + " " + tipoevento1.getNombre());
                    boton.setSize(200, 200);
                    boton.setIcon(iconoEscalado);
                    panel.add(boton);
                    indice++;
                    boton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Mostrar JOptionPane con la información del evento
//                            mostrarInformacionEvento(tipoevento1);

                            String[] arreglo = {"Quiero asisitir", "encuesta", "Califica el evento", "Mi blog"};
                            int opcion = JOptionPane.showOptionDialog(null, new Object[]{"Evento:\n " + nom + "Fecha de inicio\n: " + fechai + "Fecha de fin: \n" + fechafi + "hora de inicio: \n" + horaini + "hora final:\n " + horafi}, "Escoje un boton..", 0, JOptionPane.QUESTION_MESSAGE, null, arreglo, "Quiero asisitir");

                            System.out.println(opcion);
                            System.out.println(arreglo[opcion]);

                            switch (opcion) {
                                case 0:

                                    boolean asistir = true;

                                    
                                    String publico=Inicio.nombre+" "+Inicio.apellido;
                                    ConfirmarAsistencia(asistir,nom,publico);

                                    break;
                                case 1:

                                    break;
                                case 2:
                                    Eventos.this.dispose();

                                    Calificar cal = new Calificar();
                                    cal.setVisible(true);
                                    break;
                                case 3:

                                    Eventos.this.dispose();

                                    Blog_publico blog = new Blog_publico();
                                    blog.setVisible(true);
                                    System.out.println("holaa");
                                    break;

                            }

                        }
                    });

                }
            }
            panel.updateUI();

        }

        // Cerrar la interfaz actual (this)
        this.dispose();
        base.close();

        // Crear y mostrar la nueva interfaz
        Menu_Cliente men = new Menu_Cliente();
        men.setVisible(false);
    }

    public void ConfirmarAsistencia(boolean asistencia,String nombreEvento,String publi) {
        
        
        if (asistencia==true) {
            
            System.out.println(asistencia+nombreEvento+publi);
            
            // aqui se crea la asistencia db4o con los datos ya extraidos
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JLabel();
        txtApellido = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 940, 560));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paso-a-paso.png"))); // NOI18N
        jButton1.setText("Eentos Proximos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, -1, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calificacion-de-estrellas.png"))); // NOI18N
        jButton2.setText("Calificacion");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 620, 140, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda-de-datos.png"))); // NOI18N
        jButton3.setText("Mi Blog");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 620, 140, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mapa.png"))); // NOI18N
        jButton4.setText("Ubicacion");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 620, 140, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/no.png"))); // NOI18N
        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 620, 90, 30));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 610, 10, 60));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 610, 10, 60));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 610, 10, 60));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 10, 20));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 660, 100, 10));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 660, 160, 10));

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 660, 160, 10));

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 660, 160, 10));

        jSeparator10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 660, 160, 10));

        txtNombre.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        txtNombre.setText("jLabel1");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, -1, 20));

        txtApellido.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        txtApellido.setText("jLabel2");
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, -1, 20));

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel1.setText("EVENTOS DISPONIBLES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 240, -1));
        jPanel1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 220, 10));

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 610, 10, 60));

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 10, 20));

        jLabel2.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel2.setText("Bienvenido");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, -1, -1));

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();

        // Crear y mostrar la nueva interfaz
        Menu_Cliente men = new Menu_Cliente();
        men.setVisible(true); // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel panel;
    private javax.swing.JLabel txtApellido;
    private javax.swing.JLabel txtNombre;
    // End of variables declaration//GEN-END:variables
}
