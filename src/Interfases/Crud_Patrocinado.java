package Interfases;

import Clases.Evento;
import Clases.Patrocinador;
import Clases.Validaciones;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Crud_Patrocinado extends javax.swing.JPanel {

    String sexo;
    private TableRowSorter trs;

    public Crud_Patrocinado() {
        initComponents();
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        cargarTablaReporte(base);
        base.close();
        Agrupar();
    }

    public void Agrupar() {
        ButtonGroup mibuton = new ButtonGroup();
        mibuton.add(masculino);
        mibuton.add(femenino);
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

    public void crearPatrocinador(ObjectContainer Base) {

        if (!validarCampos()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Verificar si todos los campos están llenos
        if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty()
                || txtCelular.getText().trim().isEmpty() || (masculino.getText().trim().isEmpty() || femenino.getText().trim().isEmpty()) || txtDescripcion.getText().trim().isEmpty()
                || txtRedes.getText().trim().isEmpty() || Date_patro.getDate().toString().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos antes de ingresar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar campo de radio buttons
        if (!validarRadioButton(masculino, femenino)) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una opción en el grupo de género", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            ObjectSet<Patrocinador> resul = Base.queryByExample(new Patrocinador(null, null, null, null, null, null, null, null, null, null, null, null));
            int ultimoCodigo = resul.size() + 1;

            // Formatear el código con ceros a la izquierda
            String cod = String.format("PAT-%03d", ultimoCodigo);
            codig_patrio.setText(cod);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
            String fechaNacimiento = (Date_patro.getDate() != null) ? sdf.format(Date_patro.getDate()) : "";

            // Verificar si ya existe una casa con el mismo código
            resul = Base.queryByExample(new Patrocinador(null, null, null, txtCedula.getText().trim(), null, null, null, null, null, null, null, null));

            if (!resul.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ya existe un Patrocinador con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar edad (mayor a 18 años)
            if (!esMayorDeEdad1(Date_patro.getDate())) {
                JOptionPane.showMessageDialog(this, "El Patrocinador debe ser mayor de 18 años.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Crear objeto CasaVacacional y almacenar en la base de datos
            Patrocinador casa1 = new Patrocinador(
                    codig_patrio.getText().trim(),
                    txtDescripcion.getText().trim(),
                    txtRedes.getText().trim(),
                    txtCedula.getText().trim(),
                    txtNombre.getText().trim(),
                    txtApellido.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtEmail.getText().trim(),
                    txtDireccion.getText().trim(),
                    txtCelular.getText().trim(),
                    Date_patro.getDate(),
                    (masculino.isSelected()) ? "Masculino" : "Femenino"
            );

            Base.store(casa1);

            JOptionPane.showMessageDialog(this, "Patrocinador creado exitosamente");
            limpiarCamposPatrocinador();
        } finally {
            Base.close();
        }
    }

    private boolean validarRadioButton(JRadioButton... buttons) {
        // Verificar si al menos uno de los radio buttons está seleccionado
        for (JRadioButton button : buttons) {
            if (button.isSelected()) {
                return true;  // Al menos uno está seleccionado, retorno exitoso
            }
        }
        return false;  // Ninguno está seleccionado
    }

    private boolean existeCedula(ObjectContainer base, String cedula) {
        // Realizar una consulta para verificar si la cédula ya existe en la base de datos
        ObjectSet<Patrocinador> result = base.queryByExample(new Patrocinador(null, null, null, cedula, null, null, null, null, null, null, null, null));

        return !result.isEmpty();
    }

    private void limpiarCamposPatrocinador() {
        codig_patrio.setText("");
        txtDescripcion.setText("");
        txtRedes.setText("");
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
        txtCelular.setText("");
        Date_patro.setDate(null);
        masculino.setSelected(false);
        femenino.setSelected(false);
    }

    private void cargarTabla(ObjectContainer base, Patrocinador actividadFiltrada) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Object[] row = {
            actividadFiltrada.getCedula(),
            actividadFiltrada.getNombre(),
            actividadFiltrada.getApellido(),
            actividadFiltrada.getTelefono(),
            actividadFiltrada.getCorreo(),
            actividadFiltrada.getDireccion(),
            actividadFiltrada.getCelular(),
            actividadFiltrada.getGenero(),
            actividadFiltrada.getCodigo_patri(),
            actividadFiltrada.getDescripcion_p(),
            actividadFiltrada.getRedes_sociales(),
            actividadFiltrada.getFecchaNaci() != null ? sdf.format(actividadFiltrada.getFecchaNaci()) : null,};
        model.addRow(row);

    }

    // Método para cargar la tabla de Patrocinadores desde la base de datos
    private void cargarTablaReporte(ObjectContainer base) {
        // Limpiar el modelo actual de la tabla
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        // Formato para la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Crear y ejecutar la Query
            Query query = base.query();
            query.constrain(Patrocinador.class);
            query.descend("cedula").orderAscending();
            ObjectSet<Patrocinador> result = query.execute();

            // Recorrer los resultados y agregar filas a la tabla
            while (result.hasNext()) {
                Patrocinador patrocinador = result.next();

                Object[] row = {
                    patrocinador.getCedula(),
                    patrocinador.getNombre(),
                    patrocinador.getApellido(),
                    patrocinador.getTelefono(),
                    patrocinador.getCorreo(),
                    patrocinador.getDireccion(),
                    patrocinador.getCelular(),
                    patrocinador.getGenero(),
                    patrocinador.getCodigo_patri(),
                    patrocinador.getDescripcion_p(),
                    patrocinador.getRedes_sociales(),
                    (patrocinador.getFecchaNaci() != null) ? sdf.format(patrocinador.getFecchaNaci()) : ""
                };

                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar la tabla de Patrocinadores", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deshabilitarParametros() {
        codig_patrio.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtRedes.setEnabled(false);
        txtCedula.setEnabled(false);
        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtEmail.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtCelular.setEnabled(false);
        Date_patro.setEnabled(false);
        masculino.setEnabled(false);
        femenino.setEnabled(false);

    }

    private void habilitarCamposBusqueda(String criterioSeleccionado) {

        // Deshabilitar todos los campos de búsqueda
        deshabilitarParametros();
        // ...

        // Habilitar el campo de búsqueda correspondiente al criterio seleccionado
        if (criterioSeleccionado.equals("Cedula")) {
            txtCedula.setEnabled(true);
            limpiarCamposPatrocinador();
        } else if (criterioSeleccionado.equals("Nombre")) {
            txtNombre.setEnabled(true);
            limpiarCamposPatrocinador();
        } else if (criterioSeleccionado.equals("Apellido")) {
            txtApellido.setEnabled(true);
            limpiarCamposPatrocinador();
        } else if (criterioSeleccionado.equals("Seleccione")) {
            codig_patrio.setEnabled(true);
            txtDescripcion.setEnabled(true);
            txtRedes.setEnabled(true);
            txtCedula.setEnabled(true);
            txtNombre.setEnabled(true);
            txtApellido.setEnabled(true);
            txtTelefono.setEnabled(true);
            txtEmail.setEnabled(true);
            txtDireccion.setEnabled(true);
            txtCelular.setEnabled(true);
            Date_patro.setEnabled(true);
            masculino.setEnabled(true);
            femenino.setEnabled(true);

        }

    }

//    private void buscarPatrocinadorPorCedula(String cedulaBusqueda, ObjectContainer base) {
//
//        if (cedulaBusqueda != null && !cedulaBusqueda.isEmpty()) {
//            ObjectSet<Patrocinador> result = base.queryByExample(new Patrocinador(null, null, null, cedulaBusqueda, null, null, null, null, null, null, null, null));
//
//            if (!result.isEmpty()) {
//                Patrocinador patrocinadorEncontrado = result.next();
//                cargarDatosPatrocinador(patrocinadorEncontrado);
//                limpiarTablaPatrocinador();
//                cargarTabla(base, patrocinadorEncontrado);
//            } else {
//                JOptionPane.showMessageDialog(this, "No se encontró ningún patrocinador con la cédula ingresada.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
    // Método para cargar datos del Patrocinador en los campos de texto
    private void cargarDatosPatrocinador(Patrocinador patrocinador) {
        txtCedula.setText(patrocinador.getCedula());
        txtNombre.setText(patrocinador.getNombre());
        txtApellido.setText(patrocinador.getApellido());
        txtTelefono.setText(patrocinador.getTelefono());
        txtEmail.setText(patrocinador.getCorreo());
        txtDireccion.setText(patrocinador.getDireccion());
        txtCelular.setText(patrocinador.getCelular());
        codig_patrio.setText(patrocinador.getCodigo_patri());
        txtDescripcion.setText(patrocinador.getDescripcion_p());
        txtRedes.setText(patrocinador.getRedes_sociales());
        Date_patro.setDate(patrocinador.getFecchaNaci());

        // Seleccionar el género correspondiente
        if ("Masculino".equals(patrocinador.getGenero())) {
            masculino.setSelected(true);
            femenino.setSelected(false);
        } else if ("Femenino".equals(patrocinador.getGenero())) {
            femenino.setSelected(true);
            masculino.setSelected(false);
        }

        // Otras asignaciones de campos si es necesario
    }

    private void limpiarTablaPatrocinador() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    }

    public void ActualizarDatos(ObjectContainer base) {
        // Verificar si todos los campos están llenos
        try {
            if (!validarCampos()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (masculino.isSelected()) {
                sexo = "Masculino";
            } else if (femenino.isSelected()) {
                sexo = "Femenino";
            }

            // Obtener valores de los campos
            Patrocinador patro = new Patrocinador(null, null, null, txtCedula.getText(), null, null, null, null, null, null, null, null);

            ObjectSet res = base.get(patro);
            Patrocinador micasita = (Patrocinador) res.next();

            // Validar edad (mayor a 18 años)
            if (!esMayorDeEdad1(Date_patro.getDate())) {
                JOptionPane.showMessageDialog(this, "El personal debe ser mayor de 18 años.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Resto del código para actualizar otros campos del personal...
            micasita.setNombre(txtNombre.getText().trim());
            micasita.setApellido(txtApellido.getText().trim());
            micasita.setTelefono(txtTelefono.getText().trim());
            micasita.setCorreo(txtEmail.getText().trim());
            micasita.setDescripcion_p(txtDescripcion.getText().trim());
            micasita.setDireccion(txtDireccion.getText().trim());
            micasita.setCodigo_patri(codig_patrio.getText().trim());
            micasita.setCelular(txtCelular.getText().trim());
            micasita.setFecchaNaci(Date_patro.getDate());
            micasita.setRedes_sociales(txtRedes.getText().trim());
            micasita.setGenero(sexo);

            base.set(micasita);

            JOptionPane.showMessageDialog(this, "Modificación exitosa");
            limpiarCamposPatrocinador();
            cargarTablaReporte(base);
        } finally {
            base.close();
        }
    }

// Método para validar si la fecha de nacimiento indica que la persona es mayor de 18 años
    private boolean esMayorDeEdad1(Date fechaNacimiento) {
        if (fechaNacimiento == null) {
            System.out.println("Fecha de nacimiento es nula");
            return false;
        }

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Convertir la fecha de nacimiento a LocalDate
        LocalDate fechaNac = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Calcular la diferencia en años
        int edad = Period.between(fechaNac, fechaActual).getYears();

        // Verificar si la persona tiene al menos 18 años
        return edad >= 18;
    }

    public boolean validarCampos() {
        Validaciones miValidaciones = new Validaciones();
        boolean ban_confirmar = true;

        if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cédula del patrocinador");
            ban_confirmar = false;
        } else if (!miValidaciones.validarCedula(txtCedula.getText())) {
            JOptionPane.showMessageDialog(this, "Cédula incorrecta. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del patrocinador");
            ban_confirmar = false;
        } else if (!miValidaciones.ValidarNomApe(txtNombre.getText())) {
            JOptionPane.showMessageDialog(this, "Nombre incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el apellido del patrocinador");
            ban_confirmar = false;
        } else if (!miValidaciones.ValidarNomApe(txtApellido.getText())) {
            JOptionPane.showMessageDialog(this, "Apellido incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la Descripcion del patrocinador");
            ban_confirmar = false;
        } else if (!miValidaciones.validarDireccion(txtDescripcion.getText())) {
            JOptionPane.showMessageDialog(this, "Descripcion incorrecta. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtRedes.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese las Redes Sociales del patrocinador");
            ban_confirmar = false;
        } else if (!miValidaciones.validarDireccion(txtRedes.getText())) {
            JOptionPane.showMessageDialog(this, "Red Social incorrecta. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la Direccion del patrocinador");
            ban_confirmar = false;
        } else if (!miValidaciones.ValidarNomApe(txtDireccion.getText())) {
            JOptionPane.showMessageDialog(this, "Direccion incorrecta. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el correo del patrocinador");
            ban_confirmar = false;
        } else if (!miValidaciones.ValidarCorreo(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Correo incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        // Validar otros campos aquí...
        if (txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el Telefono del patrocinador");
            ban_confirmar = false;
        } else if (!miValidaciones.validarTelefono(txtTelefono.getText())) {
            JOptionPane.showMessageDialog(this, "Telefono incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }

        if (txtCelular.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el Celular del patrocinador");
            ban_confirmar = false;
        } else if (!miValidaciones.validarCedula(txtCelular.getText())) {
            JOptionPane.showMessageDialog(this, "Celular incorrecto. Ingrese de nuevo");
            ban_confirmar = false;
        }
        if (Date_patro.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ingrese una Fecha");
            ban_confirmar = false;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaComoCadena = sdf.format(Date_patro.getDate());

            if (!miValidaciones.validarFecha(fechaComoCadena)) {
                JOptionPane.showMessageDialog(this, "Fecha incorrecta. Ingrese de nuevo");
                ban_confirmar = false;
            }
        }

        return ban_confirmar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Date_patro = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        masculino = new javax.swing.JRadioButton();
        femenino = new javax.swing.JRadioButton();
        txtRedes = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        codig_patrio = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        cboxbusqueda = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        jTextField3.setText("jTextField3");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Cédula: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Nombre: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Apellido:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Teléfono: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Email: ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("Género: ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Fecha de Nacimiento: ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, -1));
        jPanel1.add(Date_patro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 180, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Codigo Patrocinador:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Descripcion:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, -1, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("Redes Sociales:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, -1, -1));

        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 130, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 130, -1));

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 130, -1));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 130, -1));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 130, -1));

        masculino.setText("Masculino");
        jPanel1.add(masculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, -1, -1));

        femenino.setText("Femenino");
        jPanel1.add(femenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, -1, -1));
        jPanel1.add(txtRedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 190, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/humano.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, -1, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 360, 50));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setText("Direccion:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setText("Celular:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 130, -1));
        jPanel1.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 130, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.setToolTipText("GUARDAR PATROCINADOR EN LA BASE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 120, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar (1).png"))); // NOI18N
        jButton2.setText("MODIFICAR");
        jButton2.setToolTipText("MODIFICAR EL PATROCINADOR DE LA BASE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 130, 40));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-eliminar (1).png"))); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.setToolTipText("ELIMINAR EL PATROCINADOR DE BASE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 390, 130, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cédula ", "Nombre", "Apellido", "Teléfono", "Email", "Dirección", "Celular", "Género", "Código ", "Descripción", "Redes Sociales", "Fecha de Nacimiento"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 870, 170));

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel9.setText("REGISTRO DEL PATROCINADOR");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 153, 153));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 220, 10));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        jButton4.setText("REPORTE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 390, -1, 40));
        jPanel1.add(codig_patrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 130, 30));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, 30));

        cboxbusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Cedula", "Nombre", "Apellido" }));
        cboxbusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboxbusquedaMouseClicked(evt);
            }
        });
        cboxbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxbusquedaActionPerformed(evt);
            }
        });
        jPanel1.add(cboxbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, 130, -1));

        jLabel12.setText("Filtro de busqueda");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 320, -1, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, 160, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        ActualizarDatos(base);
        base.close();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        crearPatrocinador(base);
        base.close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        ObjectContainer base = Db4o.openFile(Inicio.direccion);

        String nombre = "", apellido = "", email = "", telefono = "", codigo = "", genero = "";
        Date fecha = null;
        int edad = 0;

        Query query = base.query();
        query.constrain(Patrocinador.class);
        query.descend("cedula").constrain(txtCedula.getText().trim());

        ObjectSet<Patrocinador> result = query.execute();

        if (!result.isEmpty()) {
            // Si se encontraron resultados, cargarlos en la tabla
            //String[] columnNames = {"CEDULA", "NOMBRE", "APELLIDO", "EMAIL", "TELEFONO", "OCUPACION", "GENERO", "CODIGO", "F.NACIMIENTO"};
            Object[][] data = new Object[result.size()][12];
            int i = 0;

            for (Patrocinador propietario : result) {
                data[i][0] = propietario.getCedula();
                data[i][1] = propietario.getNombre();
                data[i][2] = propietario.getApellido();
                data[i][3] = propietario.getCorreo();
                data[i][4] = propietario.getTelefono();
                data[i][5] = propietario.getDireccion();
                data[i][6] = propietario.getGenero();
                data[i][7] = propietario.getCelular();
                data[i][8] = propietario.getCodigo_patri();
                data[i][9] = propietario.getDescripcion_p();
                data[i][10] = propietario.getRedes_sociales();
                data[i][11] = propietario.getFecchaNaci();
                i++;
            }

            //DefaultTableModel model = new DefaultTableModel(data, columnNames);
            //// jTable1.setModel(model);
            jTable1.repaint();

            // Obtener el primer resultado para mostrar los datos en los campos
            Patrocinador primerResultado = result.get(0);
            txtNombre.setText(primerResultado.getNombre().trim());
            txtApellido.setText(primerResultado.getApellido().trim());
            txtEmail.setText(primerResultado.getCorreo().trim());
            txtTelefono.setText(primerResultado.getTelefono().trim());
            genero = primerResultado.getGenero();
            if (genero.equalsIgnoreCase("Masculino")) {
                masculino.setSelected(true);
            } else if (genero.equalsIgnoreCase("Femenino")) {
                femenino.setSelected(true);
            }
            txtDireccion.setText(primerResultado.getDireccion().trim());
            txtCelular.setText(primerResultado.getCelular().trim());
            Date_patro.setDate(primerResultado.getFecchaNaci());
            codig_patrio.setText(primerResultado.getCodigo_patri().trim());
            txtDescripcion.setText(primerResultado.getDescripcion_p().trim());
            txtRedes.setText(primerResultado.getRedes_sociales().trim());

        } else {
            // Si no se encontraron resultados, mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, "No se encontró ningún propietario con la cedula ingresada");
        }

        base.close();


    }//GEN-LAST:event_jButton5ActionPerformed

    public void limpiar() {

        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCelular.setText("");
        Date_patro.setDate(null); // Limpiar la fecha
        codig_patrio.setText("");
        txtDescripcion.setText("");
        txtRedes.setText("");
    }


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        cargarTablaReporte(base);
        base.close();
    }//GEN-LAST:event_jButton4ActionPerformed


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        ObjectContainer base = Db4o.openFile(Inicio.direccion);
        String codigopro = codig_patrio.getText().trim();

        try {
            // Verificar si el propietario tiene una Casa Vacacional asociada
            Evento casaAsociada = new Evento(null, null, null, codigopro, null, null, null, null, null, null, null, 0.0, 0, null);
            ObjectSet resultCasa = base.get(casaAsociada);

            if (resultCasa.size() > 0) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar el Patrocinador porque tiene una Evento Asociado", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Buscar y mostrar datos del Propietario
            Query queryPropietario = base.query();
            queryPropietario.constrain(Patrocinador.class);
            queryPropietario.descend("codigo_patri").constrain(codigopro);

            ObjectSet<Patrocinador> resultPropietario = queryPropietario.execute();

            if (resultPropietario.size() > 0) {
                //String[] columnNames = {"Cedula", "Nombre", "Apellido", "Email", "Telefono", "Ocupacion", "Genero", "Codigo", "FGénero"};
                Object[][] data = new Object[resultPropietario.size()][12];

                int i = 0;
                for (Patrocinador propie : resultPropietario) {
                    data[i][0] = propie.getCedula();
                    data[i][1] = propie.getNombre();
                    data[i][2] = propie.getApellido();
                    data[i][3] = propie.getCorreo();
                    data[i][4] = propie.getTelefono();
                    data[i][5] = propie.getDireccion();
                    data[i][6] = propie.getGenero();
                    data[i][7] = propie.getCelular();
                    data[i][8] = propie.getCodigo_patri();
                    data[i][9] = propie.getDescripcion_p();
                    data[i][10] = propie.getRedes_sociales();
                    data[i][11] = propie.getFecchaNaci();
                    i++;
                }

                // DefaultTableModel model = new DefaultTableModel(data, columnNames);
                //jTable1.setModel(model);
                // Preguntar al usuario si desea eliminar al Propietario
                int resul = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar los datos del Propietario?", "Confirmacion", JOptionPane.YES_NO_OPTION);

                if (resul == JOptionPane.YES_OPTION) {
                    // Eliminar al Propietario
                    for (Patrocinador PORBD : resultPropietario) {
                        base.delete(PORBD);
                        JOptionPane.showMessageDialog(null, "Se están borrando los datos del Propietario");
                    }
                } else if (resul == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Datos del Propietario no eliminados");
                }

                // Limpiar la tabla después de la eliminación
                vaciarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el Propietario con la cédula ingresada", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejar la excepción de manera adecuada
        } finally {
            base.close();
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    public void Filtro() {

        if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Cedula")) {
            int Columnastabla = 0;
            trs.setRowFilter(RowFilter.regexFilter(txtCedula.getText().trim(), Columnastabla));

        } else if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Nombre")) {
            int Columnastabla = 1;
            trs.setRowFilter(RowFilter.regexFilter(txtNombre.getText().trim(), Columnastabla));

        } else if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Apellido")) {
            int Columnastabla = 2;
            trs.setRowFilter(RowFilter.regexFilter(txtApellido.getText().trim(), Columnastabla));

        }
    }


    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Cedula")) {

            txtCedula.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (txtCedula.getText());

                    txtCedula.setText(cadena);
                    Filtro();

                }
            });
        }
        trs = new TableRowSorter(jTable1.getModel());
        jTable1.setRowSorter(trs);
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Nombre")) {

            txtNombre.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (txtNombre.getText());

                    txtNombre.setText(cadena);
                    Filtro();

                }

            });

        }

        trs = new TableRowSorter(jTable1.getModel());
        jTable1.setRowSorter(trs);

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


    }//GEN-LAST:event_txtNombreKeyTyped

    private void cboxbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxbusquedaActionPerformed

        // Obtener el criterio seleccionado del JComboBox
        String criterioSeleccionado = cboxbusqueda.getSelectedItem().toString();

        // Habilitar o deshabilitar los campos de búsqueda según el criterio seleccionado
        habilitarCamposBusqueda(criterioSeleccionado);
    }//GEN-LAST:event_cboxbusquedaActionPerformed

    private void cboxbusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboxbusquedaMouseClicked


    }//GEN-LAST:event_cboxbusquedaMouseClicked

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        if (cboxbusqueda.getSelectedItem().toString().equalsIgnoreCase("Apellido")) {

            txtApellido.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent e) {

                    String cadena = (txtApellido.getText());

                    txtApellido.setText(cadena);
                    Filtro();

                }
            });
        }
        trs = new TableRowSorter(jTable1.getModel());
        jTable1.setRowSorter(trs);

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

    }//GEN-LAST:event_txtApellidoKeyTyped

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
        if (txtDireccion.getText().length() > 50) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
char letra = evt.getKeyChar();

// Verificar si es una letra y si es la primera letra
        if (Character.isLetter(letra) && txtDescripcion.getText().trim().isEmpty()) {
            // Convertir la letra a mayúscula y agregarla al texto existente
            txtDescripcion.setText(String.valueOf(Character.toUpperCase(letra)));
            evt.consume();  // Consumir el evento para evitar que la letra original se muestre
        } else if (Character.isLetter(letra) || Character.isSpaceChar(letra)) {
            // Verificar si es letra o espacio y agregar al texto en minúscula
            txtDescripcion.setText(txtDescripcion.getText() + Character.toLowerCase(letra));
            evt.consume();
        } else {
            evt.consume();
        }

// Limitar la longitud del texto a 20 caracteres
        if (txtDescripcion.getText().length() > 50) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionKeyTyped
    public void vaciarTabla() {
        // Obtenemos el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

        // Borramos las filas antiguas del modelo de tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date_patro;
    private javax.swing.JComboBox<String> cboxbusqueda;
    private javax.swing.JLabel codig_patrio;
    private javax.swing.JRadioButton femenino;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JRadioButton masculino;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRedes;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
