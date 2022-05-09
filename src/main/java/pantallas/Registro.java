package pantallas;

import static bibliotecas.BibValidar.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import static javaflix.JavaFlixMain.archivo;
import static javaflix.JavaFlixMain.usuarios;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import objetos.Cliente;
import objetos.Suscripcion.TipoSuscripcion;
import objetos.TarjetaCredito;
import objetos.Usuario;

/**
 * Pantalla de registro
 * 
 * @author Raul
 */
public class Registro extends javax.swing.JFrame {
    
    //Comparador por correo
    Comparator CorreoPerComp = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Usuario usu1 = (Usuario) o1;
            Usuario usu2 = (Usuario) o2;
            return (usu1.getCorreo().toLowerCase()).compareTo(usu2.getCorreo().toLowerCase());
        } 
    };

    // Comparador por DNI
    Comparator DNIPerComp = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            try {
                Cliente cli1 = (Cliente) o1;
                Cliente cli2 = (Cliente) o2;
                return cli1.getDNI().toUpperCase().compareTo(cli2.getDNI().toUpperCase());
            } catch (Exception e) {return -1;}
        }
    };
    
    /**
     * Constructor
     */
    public Registro() {
        
        initComponents();
        this.getContentPane().setBackground(getBackground());
        this.setLocationRelativeTo(null);
        this.setTitle("JavaFlix. Registro.");
        ImageIcon icono = new ImageIcon("img/imgdisenno/logo.jpg");
        setIconImage(icono.getImage());
        
        // Invisibiliza todos los errores
        errorNombre.setVisible(false);
        errorApellido1.setVisible(false);
        errorApellido2.setVisible(false);
        errorDNIExiste.setVisible(false);
        errorCorreoExiste.setVisible(false);
        errorCorreo.setVisible(false);
        errorDNI.setVisible(false);
        errorClave.setVisible(false);
        errorClaveRepe.setVisible(false);
        errorTarjeta.setVisible(false);
        
    }

    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        apellido2 = new javax.swing.JTextField();
        apellido1 = new javax.swing.JTextField();
        clave = new javax.swing.JPasswordField();
        correo = new javax.swing.JTextField();
        claveRepetida = new javax.swing.JPasswordField();
        registrarse = new javax.swing.JButton();
        annoCaduca = new javax.swing.JComboBox<>();
        mesCaduca = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        errorNombre = new javax.swing.JLabel();
        errorApellido1 = new javax.swing.JLabel();
        errorApellido2 = new javax.swing.JLabel();
        errorDNIExiste = new javax.swing.JLabel();
        errorCorreoExiste = new javax.swing.JLabel();
        errorClave = new javax.swing.JLabel();
        errorClaveRepe = new javax.swing.JLabel();
        errorCorreo = new javax.swing.JLabel();
        errorDNI = new javax.swing.JLabel();
        errorTarjeta = new javax.swing.JLabel();
        volver = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        DNI = new javax.swing.JTextField();
        numTarjeta = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        basica = new javax.swing.JRadioButton();
        estandar = new javax.swing.JRadioButton();
        premium = new javax.swing.JRadioButton();
        noSuscribirse = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(911, 620));
        setMinimumSize(new java.awt.Dimension(911, 620));
        setPreferredSize(new java.awt.Dimension(911, 620));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Correo electrónico:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, -1));

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Segundo apellido:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 170, -1));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DNI:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 41, -1));

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Contraseña:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, -1));

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Primer apellido:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Repita su contraseña:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, -1, -1));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Número de tarjeta de crédito:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, 270, -1));

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fecha de caducidad:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, 200, 20));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 326, 137, -1));

        nombre.setBackground(new java.awt.Color(255, 255, 255));
        nombre.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        nombre.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 230, 40));

        apellido2.setBackground(new java.awt.Color(255, 255, 255));
        apellido2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        apellido2.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(apellido2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 230, 40));

        apellido1.setBackground(new java.awt.Color(255, 255, 255));
        apellido1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        apellido1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(apellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 230, 40));

        clave.setBackground(new java.awt.Color(255, 255, 255));
        clave.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        clave.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 230, 40));

        correo.setBackground(new java.awt.Color(255, 255, 255));
        correo.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        correo.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 230, 40));

        claveRepetida.setBackground(new java.awt.Color(255, 255, 255));
        claveRepetida.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        claveRepetida.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(claveRepetida, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 230, 40));

        registrarse.setBackground(new java.awt.Color(255, 0, 0));
        registrarse.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        registrarse.setForeground(new java.awt.Color(255, 255, 255));
        registrarse.setText("Registrarse y Pagar");
        registrarse.setBorderPainted(false);
        registrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registrarseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registrarseMouseExited(evt);
            }
        });
        registrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarseActionPerformed(evt);
            }
        });
        getContentPane().add(registrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 500, 230, 50));

        annoCaduca.setBackground(new java.awt.Color(255, 255, 255));
        annoCaduca.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        annoCaduca.setForeground(new java.awt.Color(0, 0, 0));
        annoCaduca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "00" }));
        annoCaduca.setAutoscrolls(true);
        annoCaduca.setOpaque(false);
        getContentPane().add(annoCaduca, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 400, 100, 40));

        mesCaduca.setBackground(new java.awt.Color(255, 255, 255));
        mesCaduca.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        mesCaduca.setForeground(new java.awt.Color(0, 0, 0));
        mesCaduca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesCaduca.setAutoscrolls(true);
        mesCaduca.setOpaque(false);
        getContentPane().add(mesCaduca, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 400, 100, 40));

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel12.setText("/");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 400, 20, 40));

        jLabel14.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Mes:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 370, 110, 30));

        errorNombre.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        errorNombre.setForeground(new java.awt.Color(255, 0, 0));
        errorNombre.setText("Solo letras, espacios o guiones");
        getContentPane().add(errorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        errorApellido1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        errorApellido1.setForeground(new java.awt.Color(255, 0, 0));
        errorApellido1.setText("Solo letras, espacios o guiones");
        getContentPane().add(errorApellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, -1, -1));

        errorApellido2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        errorApellido2.setForeground(new java.awt.Color(255, 0, 0));
        errorApellido2.setText("Solo letras, espacios o guiones");
        getContentPane().add(errorApellido2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, -1, -1));

        errorDNIExiste.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        errorDNIExiste.setForeground(new java.awt.Color(255, 0, 0));
        errorDNIExiste.setText("El DNI ya está registrado");
        getContentPane().add(errorDNIExiste, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, -1, -1));

        errorCorreoExiste.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        errorCorreoExiste.setForeground(new java.awt.Color(255, 0, 0));
        errorCorreoExiste.setText("El correo ya está registrado");
        getContentPane().add(errorCorreoExiste, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        errorClave.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        errorClave.setForeground(new java.awt.Color(255, 0, 0));
        errorClave.setText("La contraseña es demasiado corta");
        getContentPane().add(errorClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, -1));

        errorClaveRepe.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        errorClaveRepe.setForeground(new java.awt.Color(255, 0, 0));
        errorClaveRepe.setText("Las contraseñas no coinciden");
        getContentPane().add(errorClaveRepe, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, -1, 20));

        errorCorreo.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        errorCorreo.setForeground(new java.awt.Color(255, 0, 0));
        errorCorreo.setText("Formato del correo no válido");
        getContentPane().add(errorCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        errorDNI.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        errorDNI.setForeground(new java.awt.Color(255, 0, 0));
        errorDNI.setText("El DNI está mal escrito");
        getContentPane().add(errorDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, -1, -1));

        errorTarjeta.setBackground(new java.awt.Color(0, 0, 0));
        errorTarjeta.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        errorTarjeta.setForeground(new java.awt.Color(255, 0, 0));
        errorTarjeta.setText("Rellene el número de tarjeta correctamente");
        getContentPane().add(errorTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 310, 310, 20));

        volver.setBackground(new java.awt.Color(255, 0, 0));
        volver.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        volver.setForeground(new java.awt.Color(255, 255, 255));
        volver.setText("Volver");
        volver.setBorderPainted(false);
        volver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                volverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                volverMouseExited(evt);
            }
        });
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        getContentPane().add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 230, 50));

        jLabel15.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Año");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 370, 110, 30));

        DNI.setBackground(new java.awt.Color(255, 255, 255));
        DNI.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        DNI.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(DNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 230, 40));

        numTarjeta.setBackground(new java.awt.Color(255, 255, 255));
        numTarjeta.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        numTarjeta.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(numTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 230, 40));

        jLabel11.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 56)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("JAVAFLIX");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 910, -1));

        buttonGroup1.add(basica);
        basica.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        basica.setForeground(new java.awt.Color(255, 255, 255));
        basica.setText("BÁSICA 8.99 €");
        basica.setContentAreaFilled(false);
        basica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(basica, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, -1, -1));

        buttonGroup1.add(estandar);
        estandar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        estandar.setForeground(new java.awt.Color(255, 255, 255));
        estandar.setText("ESTÁNDAR 11.99 €");
        estandar.setContentAreaFilled(false);
        estandar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(estandar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 460, -1, -1));

        buttonGroup1.add(premium);
        premium.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        premium.setForeground(new java.awt.Color(255, 255, 255));
        premium.setText("PREMIUM 15.99 €");
        premium.setContentAreaFilled(false);
        premium.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(premium, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 460, -1, -1));

        buttonGroup1.add(noSuscribirse);
        noSuscribirse.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        noSuscribirse.setForeground(new java.awt.Color(255, 255, 255));
        noSuscribirse.setSelected(true);
        noSuscribirse.setText("No suscribirse");
        noSuscribirse.setContentAreaFilled(false);
        noSuscribirse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(noSuscribirse, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 460, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Registra al cliente
    private void registrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarseActionPerformed
                  
        if (comprobarErrores()) {
            Cliente cliente = crearCliente();
            usuarios.add(cliente);
            
            archivo.guardarArchivo("usuarios.dat", usuarios);
            
            JOptionPane.showMessageDialog(this, "El registro se ha completado correctamente", "Registro completado", JOptionPane.INFORMATION_MESSAGE);
            
            if (basica.isSelected()) {
                cliente.suscribirse(TipoSuscripcion.BASICO, this);
            } else if (estandar.isSelected()){
                cliente.suscribirse(TipoSuscripcion.ESTANDAR, this);
            } else if (premium.isSelected()) {
                cliente.suscribirse(TipoSuscripcion.PREMIUM, this);
            }            
            
            // Abre pantalla de inicio sesión
            InicioSesion inicioSesion = new InicioSesion();
            inicioSesion.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_registrarseActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        
        // Abre pantalla inicio sesión
        InicioSesion inicioSesion = new InicioSesion();
        inicioSesion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void volverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseEntered
        // Botón rojo oscuro
        volver.setBackground(new java.awt.Color(94,10,10));
    }//GEN-LAST:event_volverMouseEntered

    private void registrarseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrarseMouseEntered
        // Botón rojo oscuro
        registrarse.setBackground(new java.awt.Color(94,10,10));
    }//GEN-LAST:event_registrarseMouseEntered

    private void volverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseExited
        // Botón rojo
        volver.setBackground(new java.awt.Color(255,0,0));
    }//GEN-LAST:event_volverMouseExited

    private void registrarseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrarseMouseExited
        // Botón rojo
        registrarse.setBackground(new java.awt.Color(255,0,0));
    }//GEN-LAST:event_registrarseMouseExited

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
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DNI;
    private javax.swing.JComboBox<String> annoCaduca;
    private javax.swing.JTextField apellido1;
    private javax.swing.JTextField apellido2;
    private javax.swing.JRadioButton basica;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPasswordField clave;
    private javax.swing.JPasswordField claveRepetida;
    private javax.swing.JTextField correo;
    private javax.swing.JLabel errorApellido1;
    private javax.swing.JLabel errorApellido2;
    private javax.swing.JLabel errorClave;
    private javax.swing.JLabel errorClaveRepe;
    private javax.swing.JLabel errorCorreo;
    private javax.swing.JLabel errorCorreoExiste;
    private javax.swing.JLabel errorDNI;
    private javax.swing.JLabel errorDNIExiste;
    private javax.swing.JLabel errorNombre;
    private javax.swing.JLabel errorTarjeta;
    private javax.swing.JRadioButton estandar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JComboBox<String> mesCaduca;
    private javax.swing.JRadioButton noSuscribirse;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField numTarjeta;
    private javax.swing.JRadioButton premium;
    private javax.swing.JButton registrarse;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables

    // Mis métodos
    
    /**
     * Comprueba si hay errores en la entrada de datos del usuario
     * 
     * @return no hay errores (true, false)
     */
    public boolean comprobarErrores() {
        
        actualizarErrores();
                
        //Si no hay errores devuelve true     
        return !errorNombre.isVisible() && !errorApellido1.isVisible() && !errorApellido2.isVisible() &&
                !errorDNI.isVisible() && !errorCorreo.isVisible() && !errorClave.isVisible() && !errorClaveRepe.isVisible() &&
                !errorDNIExiste.isVisible() && !errorCorreoExiste.isVisible() && !errorTarjeta.isVisible();     
    }
    
    /**
     * Actualiza los mensajes de error
     */
    public void actualizarErrores() {
        // Cliente auxiliar para ver si el DNI o el correo están cogidos
        Cliente cliAux = new Cliente(DNI.getText().toUpperCase(), "", new TarjetaCredito("45", LocalDate.of(2000,2,2), 0), correo.getText().toLowerCase(), "");

        //Comprueba todos los errores posibles en la entrada de datos
        errorNombre.setVisible(!esNombre(nombre.getText().toLowerCase()));
        errorApellido1.setVisible(!esNombre(apellido2.getText().toLowerCase()));
        errorApellido2.setVisible(!esNombre(apellido1.getText().toLowerCase()));
        errorDNI.setVisible(!esDNI(DNI.getText().toUpperCase()));
        errorCorreo.setVisible(!esCorreo(correo.getText().toLowerCase()));
        errorClave.setVisible(clave.getText().length() <= 4);
        errorClaveRepe.setVisible(!(clave.getText().equals(claveRepetida.getText())));
        Collections.sort(usuarios, CorreoPerComp);
        errorCorreoExiste.setVisible(Collections.binarySearch(usuarios, cliAux, CorreoPerComp) >= 0);
        Collections.sort(usuarios, DNIPerComp);
        errorDNIExiste.setVisible(Collections.binarySearch(usuarios, cliAux, DNIPerComp) >= 0);
        errorTarjeta.setVisible(!esTarjeta(numTarjeta.getText()));
    }
    
    /**
     * Crea cliente
     * 
     * @return cliente creado
     */
    public Cliente crearCliente() {
        LocalDate fechaCaduca = LocalDate.of(Integer.parseInt(String.valueOf(annoCaduca.getSelectedItem())) + 2000, Integer.parseInt(String.valueOf(mesCaduca.getSelectedItem())), 1);
        TarjetaCredito tarjeta = new TarjetaCredito(numTarjeta.getText(), fechaCaduca, Math.random() * 2000.0 + 150.0);
        return new Cliente(DNI.getText().toUpperCase(), nombre.getText() + ' ' + apellido1.getText() + ' ' + apellido2.getText(), tarjeta, correo.getText().toLowerCase(), clave.getText());
    }
}
