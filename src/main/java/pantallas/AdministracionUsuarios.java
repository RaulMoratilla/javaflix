package pantallas;

import bibliotecas.BusquedaOrdenacion;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static javaflix.JavaFlixMain.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import objetos.*;

/**
 * Pantalla de administración de usuarios
 * 
 * @author Raul
 */
public class AdministracionUsuarios extends javax.swing.JFrame {
        
    // Usuario seleccionado por el administrador
    static Usuario usuarioSeleccionado;
    
    //Todos los usuarios
    private ArrayList<Usuario> usuariosMostrar;
    
    //Lista de clientes
    private ArrayList<Usuario> clientes;
    
    /**
     * Constructor
     */
    public AdministracionUsuarios() {
        
        initComponents();
        this.getContentPane().setBackground(getBackground());
        this.setLocationRelativeTo(null);
        this.setTitle("JavaFlix. Aministrador");
        ImageIcon icono = new ImageIcon("img/imgdisenno/logo.jpg");
        setIconImage(icono.getImage());
        clientes = new ArrayList<Usuario>();
        actualizarUsuarios();
        cargarUsuarios(clientes);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botVolver = new javax.swing.JButton();
        botCancelarSuscripcion = new javax.swing.JButton();
        botBorrarUsuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaUsuarios = new javax.swing.JList<>();
        buscador = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(1200, 850));
        setMinimumSize(new java.awt.Dimension(1200, 850));
        setPreferredSize(new java.awt.Dimension(1200, 850));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botVolver.setBackground(new java.awt.Color(255, 0, 0));
        botVolver.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        botVolver.setForeground(new java.awt.Color(255, 255, 255));
        botVolver.setText("Volver");
        botVolver.setBorderPainted(false);
        botVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botVolverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botVolverMouseExited(evt);
            }
        });
        botVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botVolverActionPerformed(evt);
            }
        });
        getContentPane().add(botVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 650, 330, 100));

        botCancelarSuscripcion.setBackground(new java.awt.Color(255, 0, 0));
        botCancelarSuscripcion.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        botCancelarSuscripcion.setForeground(new java.awt.Color(255, 255, 255));
        botCancelarSuscripcion.setText("Cancelar Suscripción");
        botCancelarSuscripcion.setBorderPainted(false);
        botCancelarSuscripcion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botCancelarSuscripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botCancelarSuscripcionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botCancelarSuscripcionMouseExited(evt);
            }
        });
        botCancelarSuscripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCancelarSuscripcionActionPerformed(evt);
            }
        });
        getContentPane().add(botCancelarSuscripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 650, 330, 100));

        botBorrarUsuario.setBackground(new java.awt.Color(255, 0, 0));
        botBorrarUsuario.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        botBorrarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        botBorrarUsuario.setText("Borrar Usuario");
        botBorrarUsuario.setBorderPainted(false);
        botBorrarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botBorrarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botBorrarUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botBorrarUsuarioMouseExited(evt);
            }
        });
        botBorrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botBorrarUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(botBorrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, 330, 100));

        listaUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        listaUsuarios.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.lightGray));
        listaUsuarios.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        listaUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        listaUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listaUsuarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 1070, 470));

        buscador.setBackground(new java.awt.Color(255, 255, 255));
        buscador.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        buscador.setForeground(new java.awt.Color(87, 87, 87));
        buscador.setText("Introduce la búsqueda");
        buscador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                buscadorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                buscadorFocusLost(evt);
            }
        });
        buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscadorKeyTyped(evt);
            }
        });
        getContentPane().add(buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 350, 50));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Buscador:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 64)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("JAVAFLIX");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 30, 290, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botVolverActionPerformed

        this.dispose();
        JavaFlixAdministrador jFA = new JavaFlixAdministrador();
        jFA.setVisible(true);
        
    }//GEN-LAST:event_botVolverActionPerformed

    private void botCancelarSuscripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCancelarSuscripcionActionPerformed

            
        // Indice del usuario en el JList
        int indiceJList = listaUsuarios.getSelectedIndex();
        if (indiceJList != -1) {
            
            // Pregunta para asegurar
            String[] opciones = {"Sí", "No"};
            int eleccion = JOptionPane.showOptionDialog(this, "¿Está seguro de que quiere cancelar la suscripción del cliente? Esta acción es irreversible",
                "Confirme la acción", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);
            
            // Si quiere eliminarlo
            if (eleccion == 0) {
                ((Administrador)usuario).cancelarSuscripcion(indiceJList, usuariosMostrar);
                
                // Actualiza el JList
                actualizarUsuarios();
                cargarUsuarios(usuariosMostrar);
            }
        } else JOptionPane.showMessageDialog(this, "No ha seleccionado ningún cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_botCancelarSuscripcionActionPerformed

    private void botBorrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botBorrarUsuarioActionPerformed
         
        int indiceJList = listaUsuarios.getSelectedIndex();
        if (indiceJList != -1) {
            
            // Pregunta para asegurar
            String[] opciones = {"Sí", "No"};
            int eleccion = JOptionPane.showOptionDialog(this, "¿Está seguro de que quiere borrar el usuario? La acción es irreversible",
                    "Confirme la acción", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);
            if (eleccion == 0) {
                ((Administrador)usuario).borrarUsuario(indiceJList, usuariosMostrar);
                
                // Actualiza el JList
                actualizarUsuarios();
                cargarUsuarios(usuariosMostrar);
            }
            
        } else JOptionPane.showMessageDialog(this, "Ningún usuario seleccionado", "ERROR", JOptionPane.ERROR_MESSAGE);
 
    }//GEN-LAST:event_botBorrarUsuarioActionPerformed
    
    // Actualiza la búsqueda de usuarios automáticamente
    private void buscadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorKeyTyped
        usuariosMostrar = BusquedaOrdenacion.buscarUsuario(clientes, buscador.getText());
        cargarUsuarios(usuariosMostrar);
    }//GEN-LAST:event_buscadorKeyTyped

    private void botBorrarUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botBorrarUsuarioMouseEntered
        // Botón rojo oscuro
        botBorrarUsuario.setBackground(new java.awt.Color(94, 10, 10));
    }//GEN-LAST:event_botBorrarUsuarioMouseEntered

    private void botBorrarUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botBorrarUsuarioMouseExited
        // Botón rojo
        botBorrarUsuario.setBackground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_botBorrarUsuarioMouseExited

    private void botCancelarSuscripcionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botCancelarSuscripcionMouseEntered
        // Botón rojo oscuro
        botCancelarSuscripcion.setBackground(new java.awt.Color(94, 10, 10));
    }//GEN-LAST:event_botCancelarSuscripcionMouseEntered

    private void botCancelarSuscripcionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botCancelarSuscripcionMouseExited
        // Botón rojo
        botCancelarSuscripcion.setBackground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_botCancelarSuscripcionMouseExited

    private void botVolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botVolverMouseEntered
        // Botón rojo oscuro
        botVolver.setBackground(new java.awt.Color(94, 10, 10));
    }//GEN-LAST:event_botVolverMouseEntered

    private void botVolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botVolverMouseExited
        // Botón rojo
        botVolver.setBackground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_botVolverMouseExited

    private void buscadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buscadorFocusLost
        if (buscador.getText().equals("")) {
            buscador.setText("Introduce la búsqueda");
            buscador.setForeground(new java.awt.Color(87,87,87));}
    }//GEN-LAST:event_buscadorFocusLost

    private void buscadorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buscadorFocusGained
        if (buscador.getText().equals("Introduce la búsqueda")) {
            buscador.setText("");
            buscador.setForeground(new java.awt.Color(0,0,0));}
    }//GEN-LAST:event_buscadorFocusGained

    
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
            java.util.logging.Logger.getLogger(AdministracionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministracionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministracionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministracionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new AdministracionUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botBorrarUsuario;
    private javax.swing.JButton botCancelarSuscripcion;
    private javax.swing.JButton botVolver;
    private javax.swing.JTextField buscador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaUsuarios;
    // End of variables declaration//GEN-END:variables

    // Mis métodos
    

    // Lee los usuarios del archivo

    private void actualizarUsuarios() {

        usuarios = (ArrayList<Usuario>) archivo.leerArchivo("usuarios.dat");
    
        clientes = (ArrayList<Usuario>) usuarios.stream()
                .filter(v -> v.getClass().getSimpleName().equals("Cliente"))
                .collect(Collectors.toList());
        usuariosMostrar = clientes;
    }
    

      
    // Carga los usuarios en el JList
    private void cargarUsuarios(List<Usuario> usuarios) {
        
        String[] usuariosLista = new String[usuarios.size()];
        for (int i = 0 ; i < usuariosLista.length ; i++) {
            usuariosLista[i] = usuarios.get(i).toString();
        }
        listaUsuarios.setListData(usuariosLista);
        
    }

}
