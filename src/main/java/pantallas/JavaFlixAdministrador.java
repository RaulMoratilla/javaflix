package pantallas;

import javax.swing.ImageIcon;

/**
 * Pantalla de administrador
 * 
 * @author Raul
 */
public class JavaFlixAdministrador extends javax.swing.JFrame {
           
    /**
     * Constructor
     */
    public JavaFlixAdministrador() {
        
        initComponents();
        this.getContentPane().setBackground(getBackground());
        this.setLocationRelativeTo(null);
        this.setTitle("JavaFlix. Aministrador");
        ImageIcon icono = new ImageIcon("img/imgdisenno/logo.jpg");
        setIconImage(icono.getImage());

    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        cerrarSesion = new javax.swing.JButton();
        gestionUsuarios = new javax.swing.JButton();
        gestionContenidos = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(1200, 850));
        setMinimumSize(new java.awt.Dimension(1200, 850));
        setPreferredSize(new java.awt.Dimension(1200, 850));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cerrarSesion.setBackground(new java.awt.Color(255, 0, 0));
        cerrarSesion.setFont(new java.awt.Font("Cambria", 1, 48)); // NOI18N
        cerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.setBorderPainted(false);
        cerrarSesion.setContentAreaFilled(false);
        cerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cerrarSesion.setOpaque(true);
        cerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cerrarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cerrarSesionMouseExited(evt);
            }
        });
        cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionActionPerformed(evt);
            }
        });
        getContentPane().add(cerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 490, 500, 190));

        gestionUsuarios.setBackground(new java.awt.Color(255, 0, 0));
        gestionUsuarios.setFont(new java.awt.Font("Cambria", 1, 48)); // NOI18N
        gestionUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        gestionUsuarios.setText("Gestión Usuarios");
        gestionUsuarios.setBorderPainted(false);
        gestionUsuarios.setContentAreaFilled(false);
        gestionUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gestionUsuarios.setOpaque(true);
        gestionUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gestionUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                gestionUsuariosMouseExited(evt);
            }
        });
        gestionUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionUsuariosActionPerformed(evt);
            }
        });
        getContentPane().add(gestionUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 490, 190));

        gestionContenidos.setBackground(new java.awt.Color(255, 0, 0));
        gestionContenidos.setFont(new java.awt.Font("Cambria", 1, 48)); // NOI18N
        gestionContenidos.setForeground(new java.awt.Color(255, 255, 255));
        gestionContenidos.setText("Gestión Contenidos");
        gestionContenidos.setBorderPainted(false);
        gestionContenidos.setContentAreaFilled(false);
        gestionContenidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gestionContenidos.setOpaque(true);
        gestionContenidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gestionContenidosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                gestionContenidosMouseExited(evt);
            }
        });
        gestionContenidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionContenidosActionPerformed(evt);
            }
        });
        getContentPane().add(gestionContenidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 490, 190));

        jLabel12.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 104)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("JAVAFLIX");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 570, 220));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed
        InicioSesion inicioSesion = new InicioSesion();
        inicioSesion.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_cerrarSesionActionPerformed

    private void cerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarSesionMouseEntered
        // Botón rojo oscuro
        cerrarSesion.setBackground(new java.awt.Color(94, 10, 10));
    }//GEN-LAST:event_cerrarSesionMouseEntered

    private void cerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarSesionMouseExited
        // Botón rojo
        cerrarSesion.setBackground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_cerrarSesionMouseExited

    private void gestionUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gestionUsuariosMouseEntered
        // Botón rojo oscuro
        gestionUsuarios.setBackground(new java.awt.Color(94, 10, 10));
    }//GEN-LAST:event_gestionUsuariosMouseEntered

    private void gestionUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gestionUsuariosMouseExited
        // Botón rojo
        gestionUsuarios.setBackground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_gestionUsuariosMouseExited

    private void gestionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionUsuariosActionPerformed
        AdministracionUsuarios adminUsuarios = new AdministracionUsuarios();
        adminUsuarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_gestionUsuariosActionPerformed

    private void gestionContenidosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gestionContenidosMouseEntered
        // Botón rojo oscuro
        gestionContenidos.setBackground(new java.awt.Color(94, 10, 10));
    }//GEN-LAST:event_gestionContenidosMouseEntered

    private void gestionContenidosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gestionContenidosMouseExited
        // Botón rojo
        gestionContenidos.setBackground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_gestionContenidosMouseExited

    private void gestionContenidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionContenidosActionPerformed
        AdministracionContenidos adminContenidos = new AdministracionContenidos();
        adminContenidos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_gestionContenidosActionPerformed

    
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
            java.util.logging.Logger.getLogger(JavaFlixAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JavaFlixAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JavaFlixAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JavaFlixAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JavaFlixAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cerrarSesion;
    private javax.swing.JButton gestionContenidos;
    private javax.swing.JButton gestionUsuarios;
    private javax.swing.JLabel jLabel12;
    // End of variables declaration//GEN-END:variables
}
