package pantallas;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import static javaflix.JavaFlixMain.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import objetos.*;
import objetos.Suscripcion.TipoSuscripcion;



/**
 * Pantalla de Inicio de Sesión
 * 
 * @author Raul
 */
public class InicioSesion extends javax.swing.JFrame {

    private int posUsuario;
    // Para saber qué usuario está iniciando sesión
    private Comparator CorreoPerComp = new Comparator() {
            public int compare(Object o1, Object o2) {
                Usuario usu1 = (Usuario) o1;
                Usuario usu2 = (Usuario) o2;
                return usu1.getCorreo().compareTo(usu2.getCorreo());
            }
        };
    
    /**
     * Constructor
     */
    public InicioSesion() {
        initComponents();
        this.getContentPane().setBackground(getBackground());
        this.setLocationRelativeTo(null);
        this.setTitle("JavaFlix");
        ImageIcon icono = new ImageIcon("img/imgdisenno/logo.jpg");
        setIconImage(icono.getImage());
        noSuscribir.setSelected(true);
        // Panel por si el usuario no está sucrito
        panelSuscripcion.setVisible(false);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        panelSuscripcion = new javax.swing.JPanel();
        susBasica = new javax.swing.JRadioButton();
        susEstandar = new javax.swing.JRadioButton();
        susPremium = new javax.swing.JRadioButton();
        noSuscribir = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        seleccionarSuscripcion = new javax.swing.JButton();
        panelInicioSesion = new javax.swing.JPanel();
        correo = new javax.swing.JTextField();
        etCorreo = new javax.swing.JLabel();
        etContrasenna = new javax.swing.JLabel();
        clave = new javax.swing.JPasswordField();
        botAceptar = new javax.swing.JButton();
        botRegistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaFlix");
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(450, 600));
        setMinimumSize(new java.awt.Dimension(450, 600));
        setPreferredSize(new java.awt.Dimension(450, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 56)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("JAVAFLIX");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 50, 450, 110);

        panelSuscripcion.setBackground(new java.awt.Color(71, 71, 71));
        panelSuscripcion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(susBasica);
        susBasica.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        susBasica.setForeground(new java.awt.Color(255, 255, 255));
        susBasica.setText("Suscripción Básica --> 8.99 €");
        susBasica.setContentAreaFilled(false);
        panelSuscripcion.add(susBasica, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        buttonGroup1.add(susEstandar);
        susEstandar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        susEstandar.setForeground(new java.awt.Color(255, 255, 255));
        susEstandar.setText("Suscripción Estándar --> 11.99 €");
        susEstandar.setContentAreaFilled(false);
        panelSuscripcion.add(susEstandar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        buttonGroup1.add(susPremium);
        susPremium.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        susPremium.setForeground(new java.awt.Color(255, 255, 255));
        susPremium.setText("Suscripción Premium --> 15.99 €");
        susPremium.setContentAreaFilled(false);
        panelSuscripcion.add(susPremium, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        buttonGroup1.add(noSuscribir);
        noSuscribir.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        noSuscribir.setForeground(new java.awt.Color(255, 255, 255));
        noSuscribir.setText("No Suscribirse");
        noSuscribir.setContentAreaFilled(false);
        panelSuscripcion.add(noSuscribir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Escoja el tipo de suscripción:");
        panelSuscripcion.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        seleccionarSuscripcion.setBackground(new java.awt.Color(255, 0, 0));
        seleccionarSuscripcion.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        seleccionarSuscripcion.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarSuscripcion.setText("Aceptar");
        seleccionarSuscripcion.setBorderPainted(false);
        seleccionarSuscripcion.setContentAreaFilled(false);
        seleccionarSuscripcion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seleccionarSuscripcion.setOpaque(true);
        seleccionarSuscripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                seleccionarSuscripcionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                seleccionarSuscripcionMouseExited(evt);
            }
        });
        seleccionarSuscripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarSuscripcionActionPerformed(evt);
            }
        });
        panelSuscripcion.add(seleccionarSuscripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 130, -1));

        getContentPane().add(panelSuscripcion);
        panelSuscripcion.setBounds(50, 160, 350, 290);

        panelInicioSesion.setBackground(new java.awt.Color(0, 0, 0));
        panelInicioSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        correo.setBackground(new java.awt.Color(255, 255, 255));
        correo.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        correo.setForeground(new java.awt.Color(0, 0, 0));
        correo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                correoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                correoFocusLost(evt);
            }
        });
        panelInicioSesion.add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 240, 50));

        etCorreo.setBackground(new java.awt.Color(0, 0, 0));
        etCorreo.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        etCorreo.setForeground(new java.awt.Color(255, 255, 255));
        etCorreo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etCorreo.setText("Correo electrónico:");
        etCorreo.setToolTipText("");
        panelInicioSesion.add(etCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 190, 20));

        etContrasenna.setBackground(new java.awt.Color(0, 0, 0));
        etContrasenna.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        etContrasenna.setForeground(new java.awt.Color(255, 255, 255));
        etContrasenna.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etContrasenna.setText("Contraseña:");
        panelInicioSesion.add(etContrasenna, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 120, 20));

        clave.setBackground(new java.awt.Color(255, 255, 255));
        clave.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        clave.setForeground(new java.awt.Color(87, 87, 87));
        clave.setText("contraseña");
        clave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                claveFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                claveFocusLost(evt);
            }
        });
        panelInicioSesion.add(clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 240, 50));

        botAceptar.setBackground(new java.awt.Color(255, 0, 0));
        botAceptar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        botAceptar.setForeground(new java.awt.Color(255, 255, 255));
        botAceptar.setText("ACEPTAR");
        botAceptar.setBorderPainted(false);
        botAceptar.setContentAreaFilled(false);
        botAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botAceptar.setOpaque(true);
        botAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botAceptarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botAceptarMouseExited(evt);
            }
        });
        botAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botAceptarActionPerformed(evt);
            }
        });
        panelInicioSesion.add(botAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 260, 40));

        botRegistrar.setBackground(new java.awt.Color(0, 0, 0));
        botRegistrar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        botRegistrar.setForeground(new java.awt.Color(22, 133, 245));
        botRegistrar.setText("¿Aún no estás registrado? Regístrate aquí");
        botRegistrar.setToolTipText("");
        botRegistrar.setBorder(null);
        botRegistrar.setBorderPainted(false);
        botRegistrar.setContentAreaFilled(false);
        botRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botRegistrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botRegistrarMouseExited(evt);
            }
        });
        botRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botRegistrarActionPerformed(evt);
            }
        });
        panelInicioSesion.add(botRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 250, 20));

        getContentPane().add(panelInicioSesion);
        panelInicioSesion.setBounds(0, 0, 450, 580);

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botRegistrarActionPerformed
        Registro registro = new Registro();
        registro.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botRegistrarActionPerformed

    private void botRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botRegistrarMouseExited
        // Fuente Normal
        botRegistrar.setFont(new java.awt.Font("Calibri", 0, 14));
    }//GEN-LAST:event_botRegistrarMouseExited

    private void botRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botRegistrarMouseEntered
        // Subrayar
        Font font = botRegistrar.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        botRegistrar.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botRegistrarMouseEntered

    private void botAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botAceptarActionPerformed
        
        // Busca posición del usuario en el array
        Collections.sort(usuarios, CorreoPerComp);
        Cliente cliAux = new Cliente("", "", new TarjetaCredito("45", LocalDate.of(2000,2,2), 0), correo.getText().toLowerCase(), "");
        posUsuario = Collections.binarySearch(usuarios, cliAux, CorreoPerComp);
        
        //Comprueba si el usuario es administrador
        boolean admin = correo.getText().toLowerCase().equals("admin@javaflix.com");
        
        // Intenta iniciar sesión si encuentra correo
        if (posUsuario >= 0) {
            usuario = usuarios.get(posUsuario);
            iniciarSesion(usuario, admin);
        } else JOptionPane.showMessageDialog(this, "El correo escrito no existe", "ERROR", JOptionPane.ERROR_MESSAGE, null);
        
        
    }//GEN-LAST:event_botAceptarActionPerformed

    private void botAceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botAceptarMouseExited
        // Botón color rojo
        botAceptar.setBackground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_botAceptarMouseExited

    private void botAceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botAceptarMouseEntered
        // Botón color rojo oscuro
        botAceptar.setBackground(new java.awt.Color(94, 10, 10));
    }//GEN-LAST:event_botAceptarMouseEntered
    
    // Transforma el jRadioButton elegido en TipoSuscripcion
    private void seleccionarSuscripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarSuscripcionActionPerformed
        
        // Coge tipo de suscripción elegido
        
        if (susBasica.isSelected()) {
            ((Cliente)usuario).suscribirse(TipoSuscripcion.BASICO, this);
            
        } else if (susEstandar.isSelected()) {
            ((Cliente)usuario).suscribirse(TipoSuscripcion.ESTANDAR, this);
            
        } else if (susPremium.isSelected()) {
            ((Cliente)usuario).suscribirse(TipoSuscripcion.PREMIUM, this);
        }

        panelSuscripcion.setVisible(false);
        panelInicioSesion.setVisible(true);
            
    }//GEN-LAST:event_seleccionarSuscripcionActionPerformed

    private void seleccionarSuscripcionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarSuscripcionMouseExited
        // Botón rojo
        seleccionarSuscripcion.setBackground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_seleccionarSuscripcionMouseExited

    private void seleccionarSuscripcionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarSuscripcionMouseEntered
        // Botón rojo oscuro
        seleccionarSuscripcion.setBackground(new java.awt.Color(94, 10, 10));
    }//GEN-LAST:event_seleccionarSuscripcionMouseEntered

    private void correoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_correoFocusLost
        if (correo.getText().equals("")) {
            correo.setText("Correo Electrónico");
            correo.setForeground(new java.awt.Color(87,87,87));}
    }//GEN-LAST:event_correoFocusLost

    private void claveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_claveFocusLost
        if (clave.getText().equals("")) {
            clave.setText("contraseña");
            clave.setForeground(new java.awt.Color(87,87,87));}
    }//GEN-LAST:event_claveFocusLost

    private void correoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_correoFocusGained
        if (correo.getText().equals("Correo Electrónico")) {
            correo.setText("");
            correo.setForeground(new java.awt.Color(0,0,0));}
    }//GEN-LAST:event_correoFocusGained

    private void claveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_claveFocusGained
        if (clave.getText().equals("contraseña")) {
            clave.setText("");
            clave.setForeground(new java.awt.Color(0,0,0));}
    }//GEN-LAST:event_claveFocusGained
    
    
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
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botAceptar;
    private javax.swing.JButton botRegistrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPasswordField clave;
    private javax.swing.JTextField correo;
    private javax.swing.JLabel etContrasenna;
    private javax.swing.JLabel etCorreo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton noSuscribir;
    private javax.swing.JPanel panelInicioSesion;
    private javax.swing.JPanel panelSuscripcion;
    private javax.swing.JButton seleccionarSuscripcion;
    private javax.swing.JRadioButton susBasica;
    private javax.swing.JRadioButton susEstandar;
    private javax.swing.JRadioButton susPremium;
    // End of variables declaration//GEN-END:variables

    
    // Mis métodos
    
    /**
     * Inicia sesión
     * 
     * @param usuario usuario que inicia sesion
     * @param admin true si es administrador
     */
    private void iniciarSesion(Usuario usuario, boolean admin) {
        
        // Si correo = clave 
        if (usuarios.get(posUsuario).getClave().equals(clave.getText())) {
            
            // Si es admin o está suscrito inicia sesión
            if (admin || suscripciones.containsKey(usuario.getCorreo()) &&
                ChronoUnit.DAYS.between(suscripciones.get(usuario.getCorreo()).getFecha(), LocalDate.now()) < 30) {
                usuario.abrirPantalla();
                this.dispose();
                
            // Si no está suscrito pregunta por suscribirse
            } else {
                String[] opciones = {"Contratar Servicio", "Cerrar"};
                int eleccion = JOptionPane.showOptionDialog(this, "Error, el usuario no tiene contratado el servicio", "ERROR", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE, null, opciones, opciones[1]);
                if (eleccion == 0) {
                    panelSuscripcion.setVisible(true);
                    panelInicioSesion.setVisible(false);
                }
            }
            
        // Si no coincide mensaje de error
        } else {
            JOptionPane.showMessageDialog(this, "El correo no coincide con la contraseña", "ERROR", JOptionPane.ERROR_MESSAGE, null);
        }
    }

}
