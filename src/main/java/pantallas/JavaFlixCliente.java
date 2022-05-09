package pantallas;

import bibliotecas.BusquedaOrdenacion;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import static javaflix.JavaFlixMain.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import objetos.Capitulo;
import objetos.Cliente;
import objetos.Contenido;
import objetos.Pelicula;
import objetos.Serie;
import objetos.Suscripcion.TipoSuscripcion;

/**
 * Pantalla de cliente
 * 
 * 4 paneles:
 *    panelContBuscado para búsqueda realizada por usuario
 *    panelMostrarContenido para contenido seleccionado por usuario
 *    panelPresentacion para muestra predeterminada de 12 contenidos
 *    panelConfiguracion para configuración
 *   
 * @author Raul
 */
public class JavaFlixCliente extends javax.swing.JFrame {
        
    // Contenido que se imprimirá en el panel
    private Contenido contenidoMostrar;
    
    // Índices del último contenido que se muestra en las listas
    private int iFavs, iNovs, iRecs;
    
    // Almaceno los Nombres, los Iconos, y los Botones en grupos
    private JLabel[] favs = new JLabel[4], recoms = new JLabel[4], novs = new JLabel[4];
    private JLabel[] nomFavs = new JLabel[4], nomRecoms = new JLabel[4], nomNovs = new JLabel[4];
    private JButton[] botFavs = new JButton[4], botRecs = new JButton[4], botNovs = new JButton[4];
    
    // Auxiliar
    private Image portada;
    
    // Lista de los contenidos que se muestran al buscar
    private ArrayList<Contenido> contenidoMostrarBusqueda;
    
    /**
     * Constructor
     */
    public JavaFlixCliente() {
        
        initComponents();
        this.getContentPane().setBackground(getBackground());
        this.setLocationRelativeTo(null);
        this.setTitle("JavaFlix");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        this.setPreferredSize(d);
        ImageIcon icono = new ImageIcon("img/imgdisenno/logo.jpg");
        setIconImage(icono.getImage());
        panelMostrarContenido.setVisible(false);
        panelContBuscado.setVisible(false);
        panelConfiguracion.setVisible(false);
        noEncontrado.setVisible(false);
        
        buscar.setIcon(new ImageIcon("img/imgdisenno/lupa.png"));
        configuracion.setIcon(new ImageIcon("img/imgdisenno/config.png"));
        inicio.setIcon(new ImageIcon("img/imgdisenno/home.png"));
        cerrarSesion.setIcon(new ImageIcon("img/imgdisenno/cerrarSesion.png"));
        
        Image flecha = new ImageIcon("img/imgdisenno/flechaIzq.png").getImage();
        izqFav.setIcon(new ImageIcon(flecha.getScaledInstance(40, 80, Image.SCALE_SMOOTH)));
        izqNov.setIcon(new ImageIcon(flecha.getScaledInstance(40, 80, Image.SCALE_SMOOTH)));
        izqRec.setIcon(new ImageIcon(flecha.getScaledInstance(40, 80, Image.SCALE_SMOOTH)));
        
        flecha = new ImageIcon("img/imgdisenno/flechaDer.png").getImage();
        derFav.setIcon(new ImageIcon(flecha.getScaledInstance(40, 80, Image.SCALE_SMOOTH)));
        derNov.setIcon(new ImageIcon(flecha.getScaledInstance(40, 80, Image.SCALE_SMOOTH)));
        derRec.setIcon(new ImageIcon(flecha.getScaledInstance(40, 80, Image.SCALE_SMOOTH)));
        
        
        // Establecemos los campos de configuración
        nombre.setText(((Cliente)usuario).getNombre());
        DNI.setText(((Cliente)usuario).getDNI());
        correo.setText(((Cliente)usuario).getCorreo());
        clave.setText(((Cliente)usuario).getClave());
        repClave.setText(((Cliente)usuario).getClave());
        tarjetaCredito.setText(((Cliente)usuario).getTarjetaCredito().getNumero());
        mes.setText(String.valueOf(((Cliente)usuario).getTarjetaCredito().getFechaCaducidad().getMonthValue()));
        anno.setText(String.valueOf(((Cliente)usuario).getTarjetaCredito().getFechaCaducidad().getYear()));
        switch (suscripciones.get(usuario.getCorreo()).getTipo()) {
            case BASICO:
                basica.setSelected(true);
                break;
            case ESTANDAR:
                estandar.setSelected(true);
                break;
            case PREMIUM:
                premium.setSelected(true);
                break;
        }
        
        // Meto cada elemento en su lista
        favs[0] = fav1;
        favs[1] = fav2;
        favs[2] = fav3;
        favs[3] = fav4;
        recoms[0] = recom1;
        recoms[1] = recom2;
        recoms[2] = recom3;
        recoms[3] = recom4;
        novs[0] = nov1;
        novs[1] = nov2;
        novs[2] = nov3;
        novs[3] = nov4;
        nomFavs[0] = nomFav1;
        nomFavs[1] = nomFav2;
        nomFavs[2] = nomFav3;
        nomFavs[3] = nomFav4;
        nomRecoms[0] = nomRecom1;
        nomRecoms[1] = nomRecom2;
        nomRecoms[2] = nomRecom3;
        nomRecoms[3] = nomRecom4;
        nomNovs[0] = nomNov1;
        nomNovs[1] = nomNov2;
        nomNovs[2] = nomNov3;
        nomNovs[3] = nomNov4;
        botFavs[0] = botFav1;
        botFavs[1] = botFav2;
        botFavs[2] = botFav3;
        botFavs[3] = botFav4;
        botRecs[0] = botRec1;
        botRecs[1] = botRec2;
        botRecs[2] = botRec3;
        botRecs[3] = botRec4;
        botNovs[0] = botNov1;
        botNovs[1] = botNov2;
        botNovs[2] = botNov3;
        botNovs[3] = botNov4;
        
        //Inicializo los índices a 0
        iFavs = 0;
        iNovs = 0;
        iRecs = 0;
        
        // Carga los 4 primeros contenidos
        actualizarFavs();
        actualizarNovs();
        actualizarRecs();
        
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        panelMostrarContenido = new javax.swing.JPanel();
        imagen = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sinopsis = new javax.swing.JTextArea();
        titulo = new javax.swing.JLabel();
        genDurAnno = new javax.swing.JLabel();
        director = new javax.swing.JLabel();
        actores = new javax.swing.JLabel();
        calificacion = new javax.swing.JComboBox<>();
        favoritos = new javax.swing.JButton();
        temporadas = new javax.swing.JComboBox<>();
        verCaps = new javax.swing.JButton();
        volver = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        panelContBuscado = new javax.swing.JPanel();
        imagenBusq1 = new javax.swing.JLabel();
        imagenBusq3 = new javax.swing.JLabel();
        imagenBusq2 = new javax.swing.JLabel();
        titulo1 = new javax.swing.JButton();
        titulo2 = new javax.swing.JButton();
        titulo3 = new javax.swing.JButton();
        bajar = new javax.swing.JButton();
        actores3 = new javax.swing.JLabel();
        genAnnoVal1 = new javax.swing.JLabel();
        genAnnoVal2 = new javax.swing.JLabel();
        genAnnoVal3 = new javax.swing.JLabel();
        actores1 = new javax.swing.JLabel();
        actores2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelSinopsis1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        sinopsis1 = new javax.swing.JTextArea();
        panelSinopsis2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sinopsis2 = new javax.swing.JTextArea();
        panelSinopsis3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        sinopsis3 = new javax.swing.JTextArea();
        panelConfiguracion = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        DNI = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        correo = new javax.swing.JTextField();
        repClave = new javax.swing.JPasswordField();
        jLabel18 = new javax.swing.JLabel();
        clave = new javax.swing.JPasswordField();
        jLabel19 = new javax.swing.JLabel();
        anno = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        tarjetaCredito = new javax.swing.JTextField();
        mes = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        basica = new javax.swing.JRadioButton();
        estandar = new javax.swing.JRadioButton();
        premium = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        aplicarCambiosUsuario = new javax.swing.JButton();
        panelPresentacion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nomNov4 = new javax.swing.JLabel();
        fav2 = new javax.swing.JLabel();
        fav1 = new javax.swing.JLabel();
        fav4 = new javax.swing.JLabel();
        fav3 = new javax.swing.JLabel();
        recom2 = new javax.swing.JLabel();
        recom1 = new javax.swing.JLabel();
        recom4 = new javax.swing.JLabel();
        recom3 = new javax.swing.JLabel();
        nov3 = new javax.swing.JLabel();
        nov2 = new javax.swing.JLabel();
        nov4 = new javax.swing.JLabel();
        nov1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nomNov1 = new javax.swing.JLabel();
        nomNov2 = new javax.swing.JLabel();
        nomNov3 = new javax.swing.JLabel();
        nomRecom4 = new javax.swing.JLabel();
        nomRecom1 = new javax.swing.JLabel();
        nomRecom2 = new javax.swing.JLabel();
        nomRecom3 = new javax.swing.JLabel();
        nomFav4 = new javax.swing.JLabel();
        nomFav1 = new javax.swing.JLabel();
        nomFav2 = new javax.swing.JLabel();
        nomFav3 = new javax.swing.JLabel();
        derNov = new javax.swing.JButton();
        derFav = new javax.swing.JButton();
        derRec = new javax.swing.JButton();
        izqNov = new javax.swing.JButton();
        izqFav = new javax.swing.JButton();
        izqRec = new javax.swing.JButton();
        botFav1 = new javax.swing.JButton();
        botFav4 = new javax.swing.JButton();
        botFav2 = new javax.swing.JButton();
        botFav3 = new javax.swing.JButton();
        botRec1 = new javax.swing.JButton();
        botRec4 = new javax.swing.JButton();
        botRec2 = new javax.swing.JButton();
        botRec3 = new javax.swing.JButton();
        botNov1 = new javax.swing.JButton();
        botNov4 = new javax.swing.JButton();
        botNov2 = new javax.swing.JButton();
        botNov3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        cerrarSesion = new javax.swing.JButton();
        configuracion = new javax.swing.JButton();
        inicio = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        panelBusqueda = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        buscar = new javax.swing.JButton();
        buscador = new javax.swing.JTextField();
        annoBuscar = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        genero = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        actoresBuscar = new javax.swing.JRadioButton();
        tipoBusqueda = new javax.swing.JComboBox<>();
        noEncontrado = new javax.swing.JLabel();
        palabrasClave = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1500, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMostrarContenido.setBackground(new java.awt.Color(31, 31, 31));
        panelMostrarContenido.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(74, 1, 1)));
        panelMostrarContenido.setForeground(new java.awt.Color(255, 255, 255));
        panelMostrarContenido.setMaximumSize(new java.awt.Dimension(671, 658));
        panelMostrarContenido.setMinimumSize(new java.awt.Dimension(671, 658));
        panelMostrarContenido.setPreferredSize(new java.awt.Dimension(671, 658));
        panelMostrarContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imagen.setBackground(new java.awt.Color(0, 0, 0));
        imagen.setForeground(new java.awt.Color(255, 255, 255));
        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelMostrarContenido.add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 290, 180));

        sinopsis.setEditable(false);
        sinopsis.setBackground(new java.awt.Color(0, 0, 0));
        sinopsis.setColumns(50);
        sinopsis.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        sinopsis.setForeground(new java.awt.Color(255, 255, 255));
        sinopsis.setLineWrap(true);
        sinopsis.setRows(9);
        sinopsis.setWrapStyleWord(true);
        sinopsis.setAlignmentX(10.0F);
        sinopsis.setAlignmentY(10.0F);
        sinopsis.setBorder(null);
        sinopsis.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sinopsis.setFocusable(false);
        jScrollPane1.setViewportView(sinopsis);

        panelMostrarContenido.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 570, 170));

        titulo.setBackground(new java.awt.Color(0, 0, 0));
        titulo.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelMostrarContenido.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 260, 50));

        genDurAnno.setBackground(new java.awt.Color(0, 0, 0));
        genDurAnno.setForeground(new java.awt.Color(77, 75, 75));
        genDurAnno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelMostrarContenido.add(genDurAnno, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 260, 30));

        director.setBackground(new java.awt.Color(0, 0, 0));
        director.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        director.setForeground(new java.awt.Color(255, 255, 255));
        director.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelMostrarContenido.add(director, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 280, 30));

        actores.setBackground(new java.awt.Color(0, 0, 0));
        actores.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        actores.setForeground(new java.awt.Color(255, 255, 255));
        actores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelMostrarContenido.add(actores, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 570, 60));

        calificacion.setBackground(new java.awt.Color(255, 255, 255));
        calificacion.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        calificacion.setForeground(new java.awt.Color(0, 0, 0));
        calificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No calificado", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        calificacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        calificacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        calificacion.setLightWeightPopupEnabled(false);
        panelMostrarContenido.add(calificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 150, 40));

        favoritos.setBackground(new java.awt.Color(255, 0, 0));
        favoritos.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        favoritos.setForeground(new java.awt.Color(255, 255, 255));
        favoritos.setText("AÑADIR A FAVORITOS");
        favoritos.setBorderPainted(false);
        favoritos.setContentAreaFilled(false);
        favoritos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        favoritos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoritosActionPerformed(evt);
            }
        });
        panelMostrarContenido.add(favoritos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 550, 190, 40));

        temporadas.setBackground(new java.awt.Color(255, 255, 255));
        temporadas.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        temporadas.setForeground(new java.awt.Color(0, 0, 0));
        temporadas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        temporadas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        temporadas.setLightWeightPopupEnabled(false);
        panelMostrarContenido.add(temporadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 120, 30));

        verCaps.setBackground(new java.awt.Color(255, 0, 0));
        verCaps.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        verCaps.setForeground(new java.awt.Color(255, 255, 255));
        verCaps.setText("CAPITULOS");
        verCaps.setBorderPainted(false);
        verCaps.setContentAreaFilled(false);
        verCaps.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verCaps.setDefaultCapable(false);
        verCaps.setOpaque(true);
        verCaps.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                verCapsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                verCapsMouseExited(evt);
            }
        });
        verCaps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verCapsActionPerformed(evt);
            }
        });
        panelMostrarContenido.add(verCaps, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 120, 30));

        volver.setBackground(new java.awt.Color(255, 0, 0));
        volver.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        volver.setForeground(new java.awt.Color(255, 255, 255));
        volver.setText("VOLVER");
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volver.setDefaultCapable(false);
        volver.setOpaque(true);
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
        panelMostrarContenido.add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 550, 150, 40));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("JAVAFLIX");
        panelMostrarContenido.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 160, 50));

        getContentPane().add(panelMostrarContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 670, 690));

        panelContBuscado.setBackground(new java.awt.Color(0, 0, 0));
        panelContBuscado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imagenBusq1.setText("Foto1");
        panelContBuscado.add(imagenBusq1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 300, 180));

        imagenBusq3.setText("Foto3");
        panelContBuscado.add(imagenBusq3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 300, 180));

        imagenBusq2.setText("Foto2");
        panelContBuscado.add(imagenBusq2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 300, 180));

        titulo1.setBackground(new java.awt.Color(0, 0, 0));
        titulo1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        titulo1.setForeground(new java.awt.Color(255, 255, 255));
        titulo1.setText("Titulo1");
        titulo1.setBorderPainted(false);
        titulo1.setContentAreaFilled(false);
        titulo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        titulo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                titulo1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                titulo1MouseExited(evt);
            }
        });
        titulo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titulo1ActionPerformed(evt);
            }
        });
        panelContBuscado.add(titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 810, 40));

        titulo2.setBackground(new java.awt.Color(0, 0, 0));
        titulo2.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        titulo2.setForeground(new java.awt.Color(255, 255, 255));
        titulo2.setText("Titulo2");
        titulo2.setBorderPainted(false);
        titulo2.setContentAreaFilled(false);
        titulo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                titulo2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                titulo2MouseExited(evt);
            }
        });
        titulo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titulo2ActionPerformed(evt);
            }
        });
        panelContBuscado.add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 810, 40));

        titulo3.setBackground(new java.awt.Color(0, 0, 0));
        titulo3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        titulo3.setForeground(new java.awt.Color(255, 255, 255));
        titulo3.setText("Titulo3");
        titulo3.setBorderPainted(false);
        titulo3.setContentAreaFilled(false);
        titulo3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        titulo3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                titulo3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                titulo3MouseExited(evt);
            }
        });
        titulo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titulo3ActionPerformed(evt);
            }
        });
        panelContBuscado.add(titulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 460, 810, 40));

        bajar.setBackground(new java.awt.Color(0, 0, 0));
        bajar.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        bajar.setForeground(new java.awt.Color(255, 255, 255));
        bajar.setText("BAJAR");
        bajar.setBorderPainted(false);
        bajar.setContentAreaFilled(false);
        bajar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bajar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajarActionPerformed(evt);
            }
        });
        panelContBuscado.add(bajar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 660, 1210, 40));

        actores3.setBackground(new java.awt.Color(0, 0, 0));
        actores3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        actores3.setForeground(new java.awt.Color(255, 255, 255));
        actores3.setText("actores3");
        panelContBuscado.add(actores3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 520, 780, 40));

        genAnnoVal1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        genAnnoVal1.setForeground(new java.awt.Color(84, 84, 84));
        genAnnoVal1.setText("genAnnoVal1");
        panelContBuscado.add(genAnnoVal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 780, 20));

        genAnnoVal2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        genAnnoVal2.setForeground(new java.awt.Color(84, 84, 84));
        genAnnoVal2.setText("genAnnoVal2");
        panelContBuscado.add(genAnnoVal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 780, 20));

        genAnnoVal3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        genAnnoVal3.setForeground(new java.awt.Color(84, 84, 84));
        genAnnoVal3.setText("genAnnoVal3");
        panelContBuscado.add(genAnnoVal3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, 780, 20));

        actores1.setBackground(new java.awt.Color(0, 0, 0));
        actores1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        actores1.setForeground(new java.awt.Color(255, 255, 255));
        actores1.setText("actores1");
        panelContBuscado.add(actores1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 780, 40));

        actores2.setBackground(new java.awt.Color(0, 0, 0));
        actores2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        actores2.setForeground(new java.awt.Color(255, 255, 255));
        actores2.setText("actores2");
        panelContBuscado.add(actores2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, 780, 40));

        jLabel10.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("JAVAFLIX");
        panelContBuscado.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, 160, 50));

        panelSinopsis1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sinopsis1.setBackground(new java.awt.Color(0, 0, 0));
        sinopsis1.setColumns(20);
        sinopsis1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        sinopsis1.setForeground(new java.awt.Color(255, 255, 255));
        sinopsis1.setLineWrap(true);
        sinopsis1.setRows(4);
        sinopsis1.setWrapStyleWord(true);
        sinopsis1.setAlignmentX(3.0F);
        sinopsis1.setAlignmentY(3.0F);
        sinopsis1.setBorder(null);
        sinopsis1.setCaretColor(new java.awt.Color(0, 0, 0));
        sinopsis1.setFocusable(false);
        jScrollPane4.setViewportView(sinopsis1);

        panelSinopsis1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 80));

        panelContBuscado.add(panelSinopsis1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 790, 80));

        sinopsis2.setBackground(new java.awt.Color(0, 0, 0));
        sinopsis2.setColumns(20);
        sinopsis2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        sinopsis2.setForeground(new java.awt.Color(255, 255, 255));
        sinopsis2.setLineWrap(true);
        sinopsis2.setRows(4);
        sinopsis2.setWrapStyleWord(true);
        sinopsis2.setAlignmentX(3.0F);
        sinopsis2.setAlignmentY(3.0F);
        sinopsis2.setBorder(null);
        sinopsis2.setFocusable(false);
        jScrollPane2.setViewportView(sinopsis2);

        javax.swing.GroupLayout panelSinopsis2Layout = new javax.swing.GroupLayout(panelSinopsis2);
        panelSinopsis2.setLayout(panelSinopsis2Layout);
        panelSinopsis2Layout.setHorizontalGroup(
            panelSinopsis2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSinopsis2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelSinopsis2Layout.setVerticalGroup(
            panelSinopsis2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSinopsis2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelContBuscado.add(panelSinopsis2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, 790, 80));

        sinopsis3.setBackground(new java.awt.Color(0, 0, 0));
        sinopsis3.setColumns(20);
        sinopsis3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        sinopsis3.setForeground(new java.awt.Color(255, 255, 255));
        sinopsis3.setLineWrap(true);
        sinopsis3.setRows(4);
        sinopsis3.setWrapStyleWord(true);
        sinopsis3.setAlignmentX(3.0F);
        sinopsis3.setAlignmentY(3.0F);
        sinopsis3.setBorder(null);
        sinopsis3.setFocusable(false);
        jScrollPane3.setViewportView(sinopsis3);

        javax.swing.GroupLayout panelSinopsis3Layout = new javax.swing.GroupLayout(panelSinopsis3);
        panelSinopsis3.setLayout(panelSinopsis3Layout);
        panelSinopsis3Layout.setHorizontalGroup(
            panelSinopsis3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSinopsis3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelSinopsis3Layout.setVerticalGroup(
            panelSinopsis3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSinopsis3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelContBuscado.add(panelSinopsis3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, 790, 80));

        getContentPane().add(panelContBuscado, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 80, 1250, 720));

        panelConfiguracion.setBackground(new java.awt.Color(0, 0, 0));
        panelConfiguracion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelConfiguracion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 56)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("JAVAFLIX");
        panelConfiguracion.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 30, -1, -1));

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("DNI:");
        panelConfiguracion.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 160, 60, -1));

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ADMINISTRACIÓN DE USUARIO");
        panelConfiguracion.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 520, -1));

        DNI.setBackground(new java.awt.Color(255, 255, 255));
        DNI.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        DNI.setForeground(new java.awt.Color(0, 0, 0));
        DNI.setEnabled(false);
        panelConfiguracion.add(DNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 150, 320, 50));

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Repita contraseña:");
        panelConfiguracion.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, 230, -1));

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nombre:");
        panelConfiguracion.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 110, -1));

        nombre.setBackground(new java.awt.Color(255, 255, 255));
        nombre.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        nombre.setForeground(new java.awt.Color(0, 0, 0));
        nombre.setEnabled(false);
        panelConfiguracion.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 370, 50));

        jLabel17.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Correo:");
        panelConfiguracion.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 90, -1));

        correo.setBackground(new java.awt.Color(255, 255, 255));
        correo.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        correo.setForeground(new java.awt.Color(0, 0, 0));
        correo.setEnabled(false);
        panelConfiguracion.add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 370, 50));

        repClave.setBackground(new java.awt.Color(255, 255, 255));
        repClave.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        repClave.setForeground(new java.awt.Color(0, 0, 0));
        panelConfiguracion.add(repClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 310, 320, 50));

        jLabel18.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Cambiar Contraseña:");
        panelConfiguracion.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 240, -1));

        clave.setBackground(new java.awt.Color(255, 255, 255));
        clave.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        clave.setForeground(new java.awt.Color(0, 0, 0));
        panelConfiguracion.add(clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 230, 320, 50));

        jLabel19.setForeground(new java.awt.Color(166, 166, 166));
        jLabel19.setText("____________________________________________________________________________________________________________________________________________________________________________________________________");
        panelConfiguracion.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 1240, 20));

        anno.setBackground(new java.awt.Color(255, 255, 255));
        anno.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        anno.setForeground(new java.awt.Color(0, 0, 0));
        anno.setEnabled(false);
        panelConfiguracion.add(anno, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 440, 60, 50));

        jLabel21.setFont(new java.awt.Font("Cambria", 1, 48)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("/");
        panelConfiguracion.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 430, 30, -1));

        tarjetaCredito.setBackground(new java.awt.Color(255, 255, 255));
        tarjetaCredito.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tarjetaCredito.setForeground(new java.awt.Color(0, 0, 0));
        tarjetaCredito.setEnabled(false);
        panelConfiguracion.add(tarjetaCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 370, 50));

        mes.setBackground(new java.awt.Color(255, 255, 255));
        mes.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        mes.setForeground(new java.awt.Color(0, 0, 0));
        mes.setEnabled(false);
        panelConfiguracion.add(mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 440, 60, 50));

        jLabel22.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Fecha de caducidad:");
        panelConfiguracion.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 450, 240, -1));

        jLabel23.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Advertencia: El cambio en el tipo de suscripción será instantáneo, e iniciará una nueva suscripción anulándose la anterior y durando un mes, pagando el correspondiente precio");
        panelConfiguracion.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 1190, -1));

        jLabel24.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Tarjeta de crédito:");
        panelConfiguracion.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 210, -1));

        buttonGroup2.add(basica);
        basica.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        basica.setForeground(new java.awt.Color(255, 255, 255));
        basica.setText("BÁSICA 8.99 €");
        basica.setContentAreaFilled(false);
        basica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelConfiguracion.add(basica, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 530, -1, 30));

        buttonGroup2.add(estandar);
        estandar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        estandar.setForeground(new java.awt.Color(255, 255, 255));
        estandar.setText("ESTÁNDAR 11.99 €");
        estandar.setContentAreaFilled(false);
        estandar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelConfiguracion.add(estandar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 530, -1, -1));

        buttonGroup2.add(premium);
        premium.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        premium.setForeground(new java.awt.Color(255, 255, 255));
        premium.setText("PREMIUM 15.99 €");
        premium.setContentAreaFilled(false);
        premium.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelConfiguracion.add(premium, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 530, -1, -1));

        jLabel25.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Cambiar tipo de suscripción:");
        panelConfiguracion.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 320, -1));

        aplicarCambiosUsuario.setBackground(new java.awt.Color(255, 0, 0));
        aplicarCambiosUsuario.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        aplicarCambiosUsuario.setForeground(new java.awt.Color(255, 255, 255));
        aplicarCambiosUsuario.setText("APLICAR CAMBIOS");
        aplicarCambiosUsuario.setBorderPainted(false);
        aplicarCambiosUsuario.setContentAreaFilled(false);
        aplicarCambiosUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aplicarCambiosUsuario.setOpaque(true);
        aplicarCambiosUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aplicarCambiosUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aplicarCambiosUsuarioMouseExited(evt);
            }
        });
        aplicarCambiosUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplicarCambiosUsuarioActionPerformed(evt);
            }
        });
        panelConfiguracion.add(aplicarCambiosUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 630, 370, 50));

        getContentPane().add(panelConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 1250, 720));

        panelPresentacion.setBackground(new java.awt.Color(0, 0, 0));
        panelPresentacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelPresentacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tus Favoritos:");
        panelPresentacion.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, 30));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Recomendados:");
        panelPresentacion.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, -1, -1));

        nomNov4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomNov4.setForeground(new java.awt.Color(255, 255, 255));
        nomNov4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelPresentacion.add(nomNov4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 640, 210, 30));

        fav2.setBackground(new java.awt.Color(0, 0, 0));
        fav2.setForeground(new java.awt.Color(255, 255, 255));
        fav2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fav2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(fav2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 210, 120));

        fav1.setBackground(new java.awt.Color(0, 0, 0));
        fav1.setForeground(new java.awt.Color(255, 255, 255));
        fav1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fav1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(fav1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 210, 120));

        fav4.setBackground(new java.awt.Color(0, 0, 0));
        fav4.setForeground(new java.awt.Color(255, 255, 255));
        fav4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fav4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(fav4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 210, 120));

        fav3.setBackground(new java.awt.Color(0, 0, 0));
        fav3.setForeground(new java.awt.Color(255, 255, 255));
        fav3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fav3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(fav3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 210, 120));

        recom2.setBackground(new java.awt.Color(0, 0, 0));
        recom2.setForeground(new java.awt.Color(255, 255, 255));
        recom2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recom2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(recom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 290, 210, 120));

        recom1.setBackground(new java.awt.Color(0, 0, 0));
        recom1.setForeground(new java.awt.Color(255, 255, 255));
        recom1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recom1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(recom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 210, 120));

        recom4.setBackground(new java.awt.Color(0, 0, 0));
        recom4.setForeground(new java.awt.Color(255, 255, 255));
        recom4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recom4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(recom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 290, 210, 120));

        recom3.setBackground(new java.awt.Color(0, 0, 0));
        recom3.setForeground(new java.awt.Color(255, 255, 255));
        recom3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recom3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(recom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 290, 210, 120));

        nov3.setBackground(new java.awt.Color(0, 0, 0));
        nov3.setForeground(new java.awt.Color(255, 255, 255));
        nov3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nov3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nov3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 510, 210, 120));

        nov2.setBackground(new java.awt.Color(0, 0, 0));
        nov2.setForeground(new java.awt.Color(255, 255, 255));
        nov2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nov2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nov2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, 210, 120));

        nov4.setBackground(new java.awt.Color(0, 0, 0));
        nov4.setForeground(new java.awt.Color(255, 255, 255));
        nov4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nov4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nov4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 510, 210, 120));

        nov1.setBackground(new java.awt.Color(0, 0, 0));
        nov1.setForeground(new java.awt.Color(255, 255, 255));
        nov1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nov1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nov1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 210, 120));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Novedades:");
        panelPresentacion.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, -1, -1));

        nomNov1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomNov1.setForeground(new java.awt.Color(255, 255, 255));
        nomNov1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomNov1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nomNov1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 640, 210, 30));

        nomNov2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomNov2.setForeground(new java.awt.Color(255, 255, 255));
        nomNov2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomNov2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nomNov2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 640, 210, 30));

        nomNov3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomNov3.setForeground(new java.awt.Color(255, 255, 255));
        nomNov3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomNov3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nomNov3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 640, 210, 30));

        nomRecom4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomRecom4.setForeground(new java.awt.Color(255, 255, 255));
        nomRecom4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomRecom4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nomRecom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 420, 210, 30));

        nomRecom1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomRecom1.setForeground(new java.awt.Color(255, 255, 255));
        nomRecom1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomRecom1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nomRecom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 210, 30));

        nomRecom2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomRecom2.setForeground(new java.awt.Color(255, 255, 255));
        nomRecom2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomRecom2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nomRecom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 420, 210, 30));

        nomRecom3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomRecom3.setForeground(new java.awt.Color(255, 255, 255));
        nomRecom3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomRecom3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nomRecom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 420, 210, 30));

        nomFav4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomFav4.setForeground(new java.awt.Color(255, 255, 255));
        nomFav4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomFav4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nomFav4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 210, 210, 30));

        nomFav1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomFav1.setForeground(new java.awt.Color(255, 255, 255));
        nomFav1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomFav1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nomFav1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 210, 30));

        nomFav2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomFav2.setForeground(new java.awt.Color(255, 255, 255));
        nomFav2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomFav2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nomFav2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, 210, 30));

        nomFav3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        nomFav3.setForeground(new java.awt.Color(255, 255, 255));
        nomFav3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomFav3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPresentacion.add(nomFav3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 210, 30));

        derNov.setBackground(new java.awt.Color(0, 0, 0));
        derNov.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        derNov.setForeground(new java.awt.Color(255, 255, 255));
        derNov.setBorderPainted(false);
        derNov.setContentAreaFilled(false);
        derNov.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        derNov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                derNovActionPerformed(evt);
            }
        });
        panelPresentacion.add(derNov, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 550, 40, 80));

        derFav.setBackground(new java.awt.Color(0, 0, 0));
        derFav.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        derFav.setForeground(new java.awt.Color(255, 255, 255));
        derFav.setBorderPainted(false);
        derFav.setContentAreaFilled(false);
        derFav.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        derFav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                derFavActionPerformed(evt);
            }
        });
        panelPresentacion.add(derFav, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 110, 40, 80));

        derRec.setBackground(new java.awt.Color(0, 0, 0));
        derRec.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        derRec.setForeground(new java.awt.Color(255, 255, 255));
        derRec.setBorderPainted(false);
        derRec.setContentAreaFilled(false);
        derRec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        derRec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                derRecActionPerformed(evt);
            }
        });
        panelPresentacion.add(derRec, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 320, 40, 80));

        izqNov.setBackground(new java.awt.Color(0, 0, 0));
        izqNov.setFont(new java.awt.Font("Cambria", 1, 48)); // NOI18N
        izqNov.setForeground(new java.awt.Color(87, 87, 87));
        izqNov.setBorderPainted(false);
        izqNov.setContentAreaFilled(false);
        izqNov.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        izqNov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izqNovActionPerformed(evt);
            }
        });
        panelPresentacion.add(izqNov, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 540, 40, 80));

        izqFav.setBackground(new java.awt.Color(0, 0, 0));
        izqFav.setFont(new java.awt.Font("Cambria", 1, 48)); // NOI18N
        izqFav.setForeground(new java.awt.Color(87, 87, 87));
        izqFav.setBorderPainted(false);
        izqFav.setContentAreaFilled(false);
        izqFav.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        izqFav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izqFavActionPerformed(evt);
            }
        });
        panelPresentacion.add(izqFav, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 40, 80));

        izqRec.setBackground(new java.awt.Color(0, 0, 0));
        izqRec.setFont(new java.awt.Font("Cambria", 1, 48)); // NOI18N
        izqRec.setForeground(new java.awt.Color(87, 87, 87));
        izqRec.setBorderPainted(false);
        izqRec.setContentAreaFilled(false);
        izqRec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        izqRec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izqRecActionPerformed(evt);
            }
        });
        panelPresentacion.add(izqRec, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 40, 80));

        botFav1.setBackground(new java.awt.Color(0, 0, 0));
        botFav1.setForeground(new java.awt.Color(0, 0, 0));
        botFav1.setBorderPainted(false);
        botFav1.setContentAreaFilled(false);
        botFav1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botFav1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botFav1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botFav1MouseExited(evt);
            }
        });
        botFav1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botFav1ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botFav1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 210, 160));

        botFav4.setBackground(new java.awt.Color(0, 0, 0));
        botFav4.setForeground(new java.awt.Color(0, 0, 0));
        botFav4.setBorderPainted(false);
        botFav4.setContentAreaFilled(false);
        botFav4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botFav4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botFav4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botFav4MouseExited(evt);
            }
        });
        botFav4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botFav4ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botFav4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 210, 160));

        botFav2.setBackground(new java.awt.Color(0, 0, 0));
        botFav2.setForeground(new java.awt.Color(0, 0, 0));
        botFav2.setBorderPainted(false);
        botFav2.setContentAreaFilled(false);
        botFav2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botFav2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botFav2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botFav2MouseExited(evt);
            }
        });
        botFav2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botFav2ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botFav2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 210, 160));

        botFav3.setBackground(new java.awt.Color(0, 0, 0));
        botFav3.setForeground(new java.awt.Color(0, 0, 0));
        botFav3.setBorderPainted(false);
        botFav3.setContentAreaFilled(false);
        botFav3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botFav3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botFav3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botFav3MouseExited(evt);
            }
        });
        botFav3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botFav3ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botFav3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 210, 160));

        botRec1.setBackground(new java.awt.Color(0, 0, 0));
        botRec1.setForeground(new java.awt.Color(0, 0, 0));
        botRec1.setBorderPainted(false);
        botRec1.setContentAreaFilled(false);
        botRec1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botRec1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botRec1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botRec1MouseExited(evt);
            }
        });
        botRec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botRec1ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botRec1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 210, 160));

        botRec4.setBackground(new java.awt.Color(0, 0, 0));
        botRec4.setForeground(new java.awt.Color(0, 0, 0));
        botRec4.setBorderPainted(false);
        botRec4.setContentAreaFilled(false);
        botRec4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botRec4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botRec4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botRec4MouseExited(evt);
            }
        });
        botRec4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botRec4ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botRec4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 290, 210, 160));

        botRec2.setBackground(new java.awt.Color(0, 0, 0));
        botRec2.setForeground(new java.awt.Color(0, 0, 0));
        botRec2.setBorderPainted(false);
        botRec2.setContentAreaFilled(false);
        botRec2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botRec2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botRec2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botRec2MouseExited(evt);
            }
        });
        botRec2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botRec2ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botRec2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 290, 210, 160));

        botRec3.setBackground(new java.awt.Color(0, 0, 0));
        botRec3.setForeground(new java.awt.Color(0, 0, 0));
        botRec3.setBorderPainted(false);
        botRec3.setContentAreaFilled(false);
        botRec3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botRec3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botRec3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botRec3MouseExited(evt);
            }
        });
        botRec3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botRec3ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botRec3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 290, 210, 160));

        botNov1.setBackground(new java.awt.Color(0, 0, 0));
        botNov1.setForeground(new java.awt.Color(0, 0, 0));
        botNov1.setBorderPainted(false);
        botNov1.setContentAreaFilled(false);
        botNov1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botNov1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botNov1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botNov1MouseExited(evt);
            }
        });
        botNov1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botNov1ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botNov1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 210, 160));

        botNov4.setBackground(new java.awt.Color(0, 0, 0));
        botNov4.setForeground(new java.awt.Color(0, 0, 0));
        botNov4.setBorderPainted(false);
        botNov4.setContentAreaFilled(false);
        botNov4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botNov4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botNov4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botNov4MouseExited(evt);
            }
        });
        botNov4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botNov4ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botNov4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 510, 210, 160));

        botNov2.setBackground(new java.awt.Color(0, 0, 0));
        botNov2.setForeground(new java.awt.Color(0, 0, 0));
        botNov2.setBorderPainted(false);
        botNov2.setContentAreaFilled(false);
        botNov2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botNov2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botNov2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botNov2MouseExited(evt);
            }
        });
        botNov2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botNov2ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botNov2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, 210, 160));

        botNov3.setBackground(new java.awt.Color(0, 0, 0));
        botNov3.setForeground(new java.awt.Color(0, 0, 0));
        botNov3.setBorderPainted(false);
        botNov3.setContentAreaFilled(false);
        botNov3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botNov3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botNov3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botNov3MouseExited(evt);
            }
        });
        botNov3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botNov3ActionPerformed(evt);
            }
        });
        panelPresentacion.add(botNov3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 510, 210, 160));

        jLabel11.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("JAVAFLIX");
        panelPresentacion.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 10, 160, 50));

        getContentPane().add(panelPresentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, 720));

        panelMenu.setBackground(new java.awt.Color(28, 28, 28));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cerrarSesion.setBackground(new java.awt.Color(28, 28, 28));
        cerrarSesion.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        cerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        cerrarSesion.setText("CERRAR SESIÓN");
        cerrarSesion.setBorderPainted(false);
        cerrarSesion.setContentAreaFilled(false);
        cerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cerrarSesion.setDefaultCapable(false);
        cerrarSesion.setOpaque(true);
        cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionActionPerformed(evt);
            }
        });
        panelMenu.add(cerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 190, 50));

        configuracion.setBackground(new java.awt.Color(28, 28, 28));
        configuracion.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        configuracion.setForeground(new java.awt.Color(255, 255, 255));
        configuracion.setText("CONFIGURACIÓN");
        configuracion.setBorderPainted(false);
        configuracion.setContentAreaFilled(false);
        configuracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        configuracion.setDefaultCapable(false);
        configuracion.setOpaque(true);
        configuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracionActionPerformed(evt);
            }
        });
        panelMenu.add(configuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 190, 50));

        inicio.setBackground(new java.awt.Color(28, 28, 28));
        inicio.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        inicio.setForeground(new java.awt.Color(255, 255, 255));
        inicio.setText("INICIO");
        inicio.setBorderPainted(false);
        inicio.setContentAreaFilled(false);
        inicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inicio.setDefaultCapable(false);
        inicio.setOpaque(true);
        inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioActionPerformed(evt);
            }
        });
        panelMenu.add(inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 190, 50));

        jLabel5.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("JAVAFLIX");
        panelMenu.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 160, 50));

        getContentPane().add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 250, 720));

        panelBusqueda.setBackground(new java.awt.Color(28, 28, 28));
        panelBusqueda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setForeground(new java.awt.Color(166, 166, 166));
        jLabel3.setText("__________________________________________________________________");
        panelBusqueda.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 370, 20));

        buscar.setBackground(new java.awt.Color(28, 28, 28));
        buscar.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        buscar.setForeground(new java.awt.Color(255, 255, 255));
        buscar.setBorderPainted(false);
        buscar.setContentAreaFilled(false);
        buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscar.setDefaultCapable(false);
        buscar.setOpaque(true);
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        panelBusqueda.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 40, 30));

        buscador.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        buscador.setForeground(new java.awt.Color(166, 166, 166));
        buscador.setText("Introduce la búsqueda");
        buscador.setBorder(null);
        buscador.setOpaque(false);
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
        panelBusqueda.add(buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 340, 40));

        annoBuscar.setBackground(new java.awt.Color(255, 255, 255));
        annoBuscar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        annoBuscar.setForeground(new java.awt.Color(0, 0, 0));
        annoBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950" }));
        panelBusqueda.add(annoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 140, 40));

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Género:");
        panelBusqueda.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 30, -1, -1));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tipo de Contenido:");
        panelBusqueda.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, -1, -1));

        genero.setBackground(new java.awt.Color(255, 255, 255));
        genero.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        genero.setForeground(new java.awt.Color(0, 0, 0));
        genero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "Acción", "Aventura", "Catástrofe", "Ciencia Ficción", "Comedia", "Drama", "Fantasía", "Musicales", "Suspense", "Terror" }));
        panelBusqueda.add(genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, 140, 40));

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Año:");
        panelBusqueda.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 30, -1, -1));

        actoresBuscar.setBackground(new java.awt.Color(0, 0, 0));
        actoresBuscar.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        actoresBuscar.setForeground(new java.awt.Color(255, 255, 255));
        actoresBuscar.setText("Actores");
        actoresBuscar.setContentAreaFilled(false);
        actoresBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelBusqueda.add(actoresBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 10, -1, -1));

        tipoBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        tipoBusqueda.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tipoBusqueda.setForeground(new java.awt.Color(0, 0, 0));
        tipoBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "Serie", "Pelicula" }));
        panelBusqueda.add(tipoBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 170, 40));

        noEncontrado.setBackground(new java.awt.Color(255, 0, 0));
        noEncontrado.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        noEncontrado.setForeground(new java.awt.Color(255, 0, 0));
        noEncontrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noEncontrado.setText("No se han encontrado coincidencias");
        noEncontrado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelBusqueda.add(noEncontrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 300, 30));

        palabrasClave.setBackground(new java.awt.Color(0, 0, 0));
        palabrasClave.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        palabrasClave.setForeground(new java.awt.Color(255, 255, 255));
        palabrasClave.setText("Palabras clave");
        palabrasClave.setContentAreaFilled(false);
        palabrasClave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelBusqueda.add(palabrasClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 40, -1, 30));

        getContentPane().add(panelBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Realiza búsqueda de contenidos
    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        

        contenidoMostrarBusqueda = new ArrayList<Contenido>();

        contenidoMostrarBusqueda = BusquedaOrdenacion.buscarContenido((String)tipoBusqueda.getSelectedItem(), actoresBuscar.isSelected(),
            buscador.getText(), (String)genero.getSelectedItem(), (String)annoBuscar.getSelectedItem(), palabrasClave.isSelected(), contenidos);

        // Hace invisible el panel del contenido si está visible
        panelMostrarContenido.setVisible(false);

        // Si tiene contenidos los muestra ordenados
        if (!contenidoMostrarBusqueda.isEmpty()) {
            panelContBuscado.setVisible(true);
            panelPresentacion.setVisible(false);
            panelConfiguracion.setVisible(false);
            noEncontrado.setVisible(false);
            actualizarContenidosBusqueda(true);

        // Si no indica que no ha encontrado
        } else noEncontrado.setVisible(true);
        
    }//GEN-LAST:event_buscarActionPerformed

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed
        InicioSesion inicioSesion = new InicioSesion();
        inicioSesion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cerrarSesionActionPerformed
    
    // Muestra panel de configuración
    private void configuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracionActionPerformed
        panelConfiguracion.setVisible(true);
        panelMostrarContenido.setVisible(false);
        panelPresentacion.setVisible(false);
        panelContBuscado.setVisible(false);
    }//GEN-LAST:event_configuracionActionPerformed

    // Muestra panel presentación
    private void inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioActionPerformed
        
        // Visualiza el panel de inicio de cliente y invisibiliza los otros dos
        panelContBuscado.setVisible(false);
        panelPresentacion.setVisible(true);
        panelMostrarContenido.setVisible(false);
        panelConfiguracion.setVisible(false);
        noEncontrado.setVisible(false);
    }//GEN-LAST:event_inicioActionPerformed

    // Realizar búsqueda y visibilizar los paneles correspondientes
    private void buscadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorKeyTyped
        if (buscador.getText().equals("Introduzca su búsqueda") || buscador.getText().equals("")) {
            panelContBuscado.setVisible(false);
            panelMostrarContenido.setVisible(false);
            panelPresentacion.setVisible(true);
            panelConfiguracion.setVisible(false);
        }
    }//GEN-LAST:event_buscadorKeyTyped

    // El siguiente bloque de métodos actualiza las listas de la pantalla de inicio
    
    private void derNovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_derNovActionPerformed
        if (contenidos.size() > iNovs) {
            actualizarNovs();
        }
    }//GEN-LAST:event_derNovActionPerformed
    
    private void derFavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_derFavActionPerformed
        if (((Cliente)usuario).getPreferencias().size() > iFavs) {
            actualizarFavs();
        }
    }//GEN-LAST:event_derFavActionPerformed

    private void derRecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_derRecActionPerformed
        if (contenidos.size() - ((Cliente)usuario).getPreferencias().size() > iRecs) {
            actualizarRecs();
        }
    }//GEN-LAST:event_derRecActionPerformed

    private void izqNovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izqNovActionPerformed
        if (iNovs - 5 >= 0) {
            iNovs = (iNovs - 5) / 4 * 4;
            actualizarNovs();
        }
    }//GEN-LAST:event_izqNovActionPerformed

    private void izqFavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izqFavActionPerformed
        if (iFavs - 5 >= 0) {
            iFavs = (iFavs - 5) / 4 * 4;
            actualizarFavs();
        }
    }//GEN-LAST:event_izqFavActionPerformed

    private void izqRecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izqRecActionPerformed
        if (iRecs - 5 >= 0) {
            iRecs = (iRecs - 5) / 4 * 4;
            actualizarRecs();
        }
    }//GEN-LAST:event_izqRecActionPerformed
    
    // El siguiente bloque abre el contenido correspondiente
    
    private void botFav1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botFav1ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomFavs[0].getText()));
    }//GEN-LAST:event_botFav1ActionPerformed

    private void botFav4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botFav4ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomFavs[3].getText()));
    }//GEN-LAST:event_botFav4ActionPerformed

    private void botFav2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botFav2ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomFavs[1].getText()));
    }//GEN-LAST:event_botFav2ActionPerformed

    private void botFav3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botFav3ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomFavs[2].getText()));
    }//GEN-LAST:event_botFav3ActionPerformed

    private void botRec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botRec1ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomRecoms[0].getText()));
    }//GEN-LAST:event_botRec1ActionPerformed

    private void botRec4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botRec4ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomRecoms[3].getText()));
    }//GEN-LAST:event_botRec4ActionPerformed

    private void botRec2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botRec2ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomRecoms[1].getText()));
    }//GEN-LAST:event_botRec2ActionPerformed

    private void botRec3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botRec3ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomRecoms[2].getText()));
    }//GEN-LAST:event_botRec3ActionPerformed

    private void botNov1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botNov1ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomNovs[0].getText()));
    }//GEN-LAST:event_botNov1ActionPerformed

    private void botNov4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botNov4ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomNovs[3].getText()));
    }//GEN-LAST:event_botNov4ActionPerformed

    private void botNov2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botNov2ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomNovs[1].getText()));
    }//GEN-LAST:event_botNov2ActionPerformed

    private void botNov3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botNov3ActionPerformed
        abrirContenido(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nomNovs[2].getText()));
    }//GEN-LAST:event_botNov3ActionPerformed
    
    // Añade o borra de favoritos el contenido
    private void favoritosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoritosActionPerformed
        if (((Cliente)usuario).getPreferencias().contains(contenidoMostrar.getTitulo())) {
            ((Cliente)usuario).getPreferencias().remove(contenidoMostrar.getTitulo());
            favoritos.setOpaque(false);
        } else {
            ((Cliente)usuario).addPreferencia(contenidoMostrar);
            favoritos.setOpaque(true);
        }
        archivo.guardarArchivo("usuarios.dat", usuarios);
    }//GEN-LAST:event_favoritosActionPerformed
    
    // Muestra Capítulos de Temporada

    private void verCapsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verCapsActionPerformed
        int temporada = temporadas.getSelectedIndex();
        String capitulos = "";
        for (Capitulo capitulo : ((Serie)contenidoMostrar).getTemporadas().get(temporada)) {
            capitulos += "Nombre: " + capitulo.getNombre() + "   |   Duración: " + String.valueOf(capitulo.getDuracion()) + "\n";
        }
        JOptionPane.showMessageDialog(this, capitulos, "Temporada " + (temporada+1), JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_verCapsActionPerformed

    // Vuelve a panelPresentación guardando los datos
    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        (contenidoMostrar).addValoracion(usuario.getCorreo(), (String) calificacion.getSelectedItem());
        archivo.guardarArchivo("contenidos.dat", contenidos);
        iFavs = 0;
        iRecs = 0;
        actualizarRecs();
        actualizarFavs();
        panelMostrarContenido.setVisible(false);
        panelPresentacion.setVisible(true);
        temporadas.removeAllItems();
    }//GEN-LAST:event_volverActionPerformed

    // Actualizacion de botones
    
    private void verCapsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verCapsMouseEntered
        verCaps.setBackground(new java.awt.Color(94,10,10));
    }//GEN-LAST:event_verCapsMouseEntered

    private void verCapsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verCapsMouseExited
        verCaps.setBackground(new java.awt.Color(255,0,0));
    }//GEN-LAST:event_verCapsMouseExited

    private void volverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseEntered
        volver.setBackground(new java.awt.Color(94,10,10));
    }//GEN-LAST:event_volverMouseEntered

    private void volverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseExited
        volver.setBackground(new java.awt.Color(255,0,0));
    }//GEN-LAST:event_volverMouseExited

    private void aplicarCambiosUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aplicarCambiosUsuarioMouseEntered
        // Botón rojo oscuro
        aplicarCambiosUsuario.setBackground(new java.awt.Color(94, 10, 10));
    }//GEN-LAST:event_aplicarCambiosUsuarioMouseEntered

    private void aplicarCambiosUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aplicarCambiosUsuarioMouseExited
        // Botón rojo
        aplicarCambiosUsuario.setBackground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_aplicarCambiosUsuarioMouseExited
    
    // Aplica los cambios de configuración al usuario
    private void aplicarCambiosUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aplicarCambiosUsuarioActionPerformed

        if (clave.getText().equals(repClave.getText()) && clave.getText().length() >= 5 && !clave.getText().equals(usuario.getClave())) {
            
            // Coge tipo de suscripción actual
            TipoSuscripcion tipoActual = suscripciones.get(usuario.getCorreo()).getTipo();

            if (basica.isSelected() && !tipoActual.equals(TipoSuscripcion.BASICO)) {
                suscripciones.remove(usuario.getCorreo());
                ((Cliente)usuario).suscribirse(TipoSuscripcion.BASICO, this);

            } else if (estandar.isSelected() && !tipoActual.equals(TipoSuscripcion.ESTANDAR)) {
                suscripciones.remove(usuario.getCorreo());
                ((Cliente)usuario).suscribirse(TipoSuscripcion.ESTANDAR, this);

            } else if (premium.isSelected() && !tipoActual.equals(TipoSuscripcion.PREMIUM)) {
                suscripciones.remove(usuario.getCorreo());
                ((Cliente)usuario).suscribirse(TipoSuscripcion.PREMIUM, this);
            } else {
                
            }
            if (!usuario.getClave().equals(clave.getText())) {
                usuario.setClave(clave.getText());
                JOptionPane.showMessageDialog(this, "La contraseña se ha actualizado", "Actualización de contraseña", JOptionPane.INFORMATION_MESSAGE);
            }
            archivo.guardarArchivo("usuarios.dat", usuarios);
        } else JOptionPane.showMessageDialog(this, "No se han actualizado los datos. Las contraseñas no coinciden o la longitud"
                + "es menor a 5", "Error", JOptionPane.ERROR_MESSAGE);
        

    }//GEN-LAST:event_aplicarCambiosUsuarioActionPerformed
    
    // Actualización de elementos gráficos
    private void buscadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buscadorFocusLost
        if (buscador.getText().equals(""))
            buscador.setText("Introduce la búsqueda");
    }//GEN-LAST:event_buscadorFocusLost

    private void buscadorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buscadorFocusGained
        if (buscador.getText().equals("Introduce la búsqueda"))
            buscador.setText("");
    }//GEN-LAST:event_buscadorFocusGained

    private void botFav1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botFav1MouseEntered
        Font font = nomFav1.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomFav1.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botFav1MouseEntered

    private void botFav1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botFav1MouseExited
        nomFav1.setFont(new java.awt.Font("Cambria", 1, 14)); 
        
    }//GEN-LAST:event_botFav1MouseExited

    private void botFav2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botFav2MouseEntered
        Font font = nomFav2.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomFav2.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botFav2MouseEntered

    private void botFav2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botFav2MouseExited
        nomFav2.setFont(new java.awt.Font("Cambria", 1, 14));
    }//GEN-LAST:event_botFav2MouseExited

    private void botFav3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botFav3MouseEntered
        Font font = nomFav3.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomFav3.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botFav3MouseEntered

    private void botFav3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botFav3MouseExited
        nomFav3.setFont(new java.awt.Font("Cambria", 1, 14));
    }//GEN-LAST:event_botFav3MouseExited

    private void botFav4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botFav4MouseEntered
        Font font = nomFav4.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomFav4.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botFav4MouseEntered

    private void botFav4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botFav4MouseExited
        nomFav4.setFont(new java.awt.Font("Cambria", 1, 14));
    }//GEN-LAST:event_botFav4MouseExited

    private void botRec1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botRec1MouseExited
        nomRecom1.setFont(new java.awt.Font("Cambria", 1, 14));
    }//GEN-LAST:event_botRec1MouseExited

    private void botRec1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botRec1MouseEntered
        Font font = nomRecom1.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomRecom1.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botRec1MouseEntered

    private void botRec2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botRec2MouseEntered
        Font font = nomRecom2.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomRecom2.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botRec2MouseEntered

    private void botRec2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botRec2MouseExited
        nomRecom2.setFont(new java.awt.Font("Cambria", 1, 14));
    }//GEN-LAST:event_botRec2MouseExited

    private void botRec3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botRec3MouseEntered
        Font font = nomRecom3.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomRecom3.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botRec3MouseEntered

    private void botRec3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botRec3MouseExited
        nomRecom3.setFont(new java.awt.Font("Cambria", 1, 14));
    }//GEN-LAST:event_botRec3MouseExited

    private void botRec4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botRec4MouseEntered
        Font font = nomRecom4.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomRecom4.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botRec4MouseEntered

    private void botRec4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botRec4MouseExited
        nomRecom4.setFont(new java.awt.Font("Cambria", 1, 14));
    }//GEN-LAST:event_botRec4MouseExited

    private void botNov1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botNov1MouseEntered
        Font font = nomNov1.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomNov1.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botNov1MouseEntered

    private void botNov1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botNov1MouseExited
        nomNov1.setFont(new java.awt.Font("Cambria", 1, 14));
    }//GEN-LAST:event_botNov1MouseExited

    private void botNov2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botNov2MouseEntered
        Font font = nomNov2.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomNov2.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botNov2MouseEntered

    private void botNov2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botNov2MouseExited
        nomNov2.setFont(new java.awt.Font("Cambria", 1, 14));
    }//GEN-LAST:event_botNov2MouseExited

    private void botNov3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botNov3MouseEntered
        Font font = nomNov3.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomNov3.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botNov3MouseEntered

    private void botNov3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botNov3MouseExited
        nomNov3.setFont(new java.awt.Font("Cambria", 1, 14));
    }//GEN-LAST:event_botNov3MouseExited

    private void botNov4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botNov4MouseEntered
        Font font = nomNov4.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nomNov4.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_botNov4MouseEntered

    private void botNov4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botNov4MouseExited
        nomNov4.setFont(new java.awt.Font("Cambria", 1, 14));
    }//GEN-LAST:event_botNov4MouseExited
    
    // Carga 3 nuevos contenidos en el panel contBuscado
    private void bajarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajarActionPerformed
        // Actualiza contenido panelContenidoBuscado
        actualizarContenidosBusqueda(false);
    }//GEN-LAST:event_bajarActionPerformed
    
    // Abre contenido 3 de contBuscado
    private void titulo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titulo3ActionPerformed
        // Abre tercer contenido panelContenidoBuscado
        Contenido contenido = BusquedaOrdenacion.buscarContenidoPorTitulo(contenidoMostrarBusqueda, titulo3.getText());
        abrirContenido(contenido);
        panelContBuscado.setVisible(false);
    }//GEN-LAST:event_titulo3ActionPerformed

    private void titulo3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titulo3MouseExited
        titulo3.setFont(new java.awt.Font("Cambria", 1, 18));
    }//GEN-LAST:event_titulo3MouseExited
    
    private void titulo3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titulo3MouseEntered
        Font font = titulo3.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        titulo3.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_titulo3MouseEntered

    // Abre contenido 2 de contBuscado
    private void titulo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titulo2ActionPerformed
        // Abre segundo contenido panelContenidoBuscado
        Contenido contenido = BusquedaOrdenacion.buscarContenidoPorTitulo(contenidoMostrarBusqueda, titulo2.getText());
        abrirContenido(contenido);
        panelContBuscado.setVisible(false);
    }//GEN-LAST:event_titulo2ActionPerformed

    private void titulo2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titulo2MouseExited
        titulo2.setFont(new java.awt.Font("Cambria", 1, 18));
    }//GEN-LAST:event_titulo2MouseExited

    private void titulo2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titulo2MouseEntered
        Font font = titulo2.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        titulo2.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_titulo2MouseEntered

    // Abre contenido 1 de contBuscado
    private void titulo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titulo1ActionPerformed
        // Abre primer contenido panelContenidoBuscado
        Contenido contenido = BusquedaOrdenacion.buscarContenidoPorTitulo(contenidoMostrarBusqueda, titulo1.getText());
        abrirContenido(contenido);
        panelContBuscado.setVisible(false);
    }//GEN-LAST:event_titulo1ActionPerformed

    private void titulo1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titulo1MouseExited
        titulo1.setFont(new java.awt.Font("Cambria", 1, 18));
    }//GEN-LAST:event_titulo1MouseExited

    private void titulo1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titulo1MouseEntered
        Font font = titulo1.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        titulo1.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_titulo1MouseEntered

    
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
            java.util.logging.Logger.getLogger(JavaFlixCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JavaFlixCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JavaFlixCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JavaFlixCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new JavaFlixCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DNI;
    private javax.swing.JLabel actores;
    private javax.swing.JLabel actores1;
    private javax.swing.JLabel actores2;
    private javax.swing.JLabel actores3;
    private javax.swing.JRadioButton actoresBuscar;
    private javax.swing.JTextField anno;
    private javax.swing.JComboBox<String> annoBuscar;
    private javax.swing.JButton aplicarCambiosUsuario;
    private javax.swing.JButton bajar;
    private javax.swing.JRadioButton basica;
    private javax.swing.JButton botFav1;
    private javax.swing.JButton botFav2;
    private javax.swing.JButton botFav3;
    private javax.swing.JButton botFav4;
    private javax.swing.JButton botNov1;
    private javax.swing.JButton botNov2;
    private javax.swing.JButton botNov3;
    private javax.swing.JButton botNov4;
    private javax.swing.JButton botRec1;
    private javax.swing.JButton botRec2;
    private javax.swing.JButton botRec3;
    private javax.swing.JButton botRec4;
    private javax.swing.JTextField buscador;
    private javax.swing.JButton buscar;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> calificacion;
    private javax.swing.JButton cerrarSesion;
    private javax.swing.JPasswordField clave;
    private javax.swing.JButton configuracion;
    private javax.swing.JTextField correo;
    private javax.swing.JButton derFav;
    private javax.swing.JButton derNov;
    private javax.swing.JButton derRec;
    private javax.swing.JLabel director;
    private javax.swing.JRadioButton estandar;
    private javax.swing.JLabel fav1;
    private javax.swing.JLabel fav2;
    private javax.swing.JLabel fav3;
    private javax.swing.JLabel fav4;
    private javax.swing.JButton favoritos;
    private javax.swing.JLabel genAnnoVal1;
    private javax.swing.JLabel genAnnoVal2;
    private javax.swing.JLabel genAnnoVal3;
    private javax.swing.JLabel genDurAnno;
    private javax.swing.JComboBox<String> genero;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel imagenBusq1;
    private javax.swing.JLabel imagenBusq2;
    private javax.swing.JLabel imagenBusq3;
    private javax.swing.JButton inicio;
    private javax.swing.JButton izqFav;
    private javax.swing.JButton izqNov;
    private javax.swing.JButton izqRec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField mes;
    private javax.swing.JLabel noEncontrado;
    private javax.swing.JLabel nomFav1;
    private javax.swing.JLabel nomFav2;
    private javax.swing.JLabel nomFav3;
    private javax.swing.JLabel nomFav4;
    private javax.swing.JLabel nomNov1;
    private javax.swing.JLabel nomNov2;
    private javax.swing.JLabel nomNov3;
    private javax.swing.JLabel nomNov4;
    private javax.swing.JLabel nomRecom1;
    private javax.swing.JLabel nomRecom2;
    private javax.swing.JLabel nomRecom3;
    private javax.swing.JLabel nomRecom4;
    private javax.swing.JTextField nombre;
    private javax.swing.JLabel nov1;
    private javax.swing.JLabel nov2;
    private javax.swing.JLabel nov3;
    private javax.swing.JLabel nov4;
    private javax.swing.JRadioButton palabrasClave;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JPanel panelConfiguracion;
    private javax.swing.JPanel panelContBuscado;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelMostrarContenido;
    private javax.swing.JPanel panelPresentacion;
    private javax.swing.JPanel panelSinopsis1;
    private javax.swing.JPanel panelSinopsis2;
    private javax.swing.JPanel panelSinopsis3;
    private javax.swing.JRadioButton premium;
    private javax.swing.JLabel recom1;
    private javax.swing.JLabel recom2;
    private javax.swing.JLabel recom3;
    private javax.swing.JLabel recom4;
    private javax.swing.JPasswordField repClave;
    private javax.swing.JTextArea sinopsis;
    private javax.swing.JTextArea sinopsis1;
    private javax.swing.JTextArea sinopsis2;
    private javax.swing.JTextArea sinopsis3;
    private javax.swing.JTextField tarjetaCredito;
    private javax.swing.JComboBox<String> temporadas;
    private javax.swing.JComboBox<String> tipoBusqueda;
    private javax.swing.JLabel titulo;
    private javax.swing.JButton titulo1;
    private javax.swing.JButton titulo2;
    private javax.swing.JButton titulo3;
    private javax.swing.JButton verCaps;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables

    
    /**
     * Abre panel de Serie o Pelicula
     * 
     * @param contenido contenido a mostrar
     */
    private void abrirContenido(Contenido contenido) {
        try {
            contenidoMostrar = contenido;
            actualizarPanel((Pelicula)contenidoMostrar);
            panelPresentacion.setVisible(false);
            panelMostrarContenido.setVisible(true);
        } catch (Exception e) {}
        try {
            contenidoMostrar = contenido;
            actualizarPanel((Serie)contenidoMostrar);
            panelPresentacion.setVisible(false);
            panelMostrarContenido.setVisible(true);
        } catch (Exception e) {}
    }
    
    /**
     * Prepara el panel de la Pelicula
     * 
     * @param pelicula pelicula mostrar
     */
    private void actualizarPanel(Pelicula pelicula) {
        temporadas.setVisible(false);
        verCaps.setVisible(false);
        genDurAnno.setVisible(true);
        portada = new ImageIcon(pelicula.getImagenPortada()).getImage();
        imagen.setIcon(new ImageIcon(portada.getScaledInstance(290, 180, 100)));
        titulo.setText("<html>" + pelicula.getTitulo() + "</html>");
        director.setText(pelicula.getDirector() + "   |   Puntuación: " + pelicula.getValoracion());
        genDurAnno.setText(pelicula.getGenero() + "   |   " + ((Pelicula)pelicula).getAnno()
                + "   |   " + (pelicula.getDuracion() + " min"));
        sinopsis.setText(pelicula.getSinopsis());
        actores.setText("<html>" + pelicula.actoresToString() + "</html>");
        favoritos.setOpaque(((Cliente)usuario).getPreferencias().contains(pelicula.getTitulo()));
        if (pelicula.getValoraciones().get(usuario.getCorreo()) != null) {
            calificacion.setSelectedItem(pelicula.getValoraciones().get(usuario.getCorreo()));
        } else calificacion.setSelectedIndex(0);
    }
    
    /**
     * Prepara el panel de la Serie
     * 
     * @param serie serie mostrar
     */
    private void actualizarPanel(Serie serie) {
        genDurAnno.setVisible(false);
        temporadas.setVisible(true);
        verCaps.setVisible(true);
        for (int i = 0 ; i < serie.getTemporadas().size() ; i++) {
            temporadas.addItem("Temporada " + (i + 1));
        }
        temporadas.setSelectedIndex(0);
        portada = new ImageIcon(serie.getImagenPortada()).getImage();
        imagen.setIcon(new ImageIcon(portada.getScaledInstance(290, 180, 100)));
        titulo.setText("<html>" + serie.getTitulo() + "</html>");
        
        //En este caso no hay director, por lo que reutilizo el label para mostrar genero, año y valoracion
        director.setText(serie.getGenero() + "   |   " + serie.getAnno() + "   |   Puntuación: " + serie.getValoracion());
        
        sinopsis.setText(serie.getSinopsis());
        actores.setText("<html>" + serie.actoresToString() + "</html>");
        favoritos.setOpaque(((Cliente)usuario).getPreferencias().contains(serie.getTitulo()));
        if (serie.getValoraciones().get(usuario.getCorreo()) != null) {
            calificacion.setSelectedItem(serie.getValoraciones().get(usuario.getCorreo()));
        } else calificacion.setSelectedIndex(0);
       

    }
    
    /**
     * Actualiza presentación de favoritos
     */
    private void actualizarFavs() {
        
        ArrayList<String> contenidoFavs = (ArrayList<String>)((Cliente)usuario).getPreferencias().clone();
        Collections.reverse(contenidoFavs);
        
        Image portada;
        int iMaxF = iFavs + 4;
        int i = 0;
        Contenido contenido;
        String nombreContenido;
        // Inivisibilizamos todo
        favs[0].setVisible(false);
        favs[1].setVisible(false);
        favs[2].setVisible(false);
        favs[3].setVisible(false);
        nomFavs[0].setVisible(false);
        nomFavs[1].setVisible(false);
        nomFavs[2].setVisible(false);
        nomFavs[3].setVisible(false);
        botFavs[0].setVisible(false);
        botFavs[1].setVisible(false);
        botFavs[2].setVisible(false);
        botFavs[3].setVisible(false);
        
        while (iFavs < iMaxF && iFavs < ((Cliente)usuario).getPreferencias().size()) {
            
            // Carga contenidos de lista de favoritos uno a uno hasta indice 4 o no haya mas
            nombreContenido = contenidoFavs.get(iFavs);
            contenido = BusquedaOrdenacion.buscarContenidoPorTitulo(contenidos, nombreContenido);
            portada = new ImageIcon(contenido.getImagenPortada()).getImage();
            favs[i].setIcon(new ImageIcon(portada.getScaledInstance(210, 120, 100)));
            nomFavs[i].setText(contenido.getTitulo());
            favs[i].setVisible(true);
            nomFavs[i].setVisible(true);
            botFavs[i].setVisible(true);
            iFavs++;
            i++;
        }

    }
    
    /**
     * Actualiza presentación de novedades
     */
    private void actualizarNovs() {
        
        ArrayList<Contenido> contenidoNovs = BusquedaOrdenacion.ordenarContenidos(BusquedaOrdenacion.TipoOrdenacionContenido.ANNO, contenidos);       
        
        Image portada;
        int i = 0;
        Contenido contenido;
        int iMaxN = iNovs + 4;
        novs[0].setVisible(false);
        novs[1].setVisible(false);
        novs[2].setVisible(false);
        novs[3].setVisible(false);
        nomNovs[0].setVisible(false);
        nomNovs[1].setVisible(false);
        nomNovs[2].setVisible(false);
        nomNovs[3].setVisible(false);
        botNovs[0].setVisible(false);
        botNovs[1].setVisible(false);
        botNovs[2].setVisible(false);
        botNovs[3].setVisible(false);
        
        while (iNovs < iMaxN && iNovs < contenidos.size()) {
            contenido = contenidoNovs.get(iNovs);
            portada = new ImageIcon(contenido.getImagenPortada()).getImage();
            novs[i].setIcon(new ImageIcon(portada.getScaledInstance(210, 120, 100)));
            nomNovs[i].setText(contenido.getTitulo());
            novs[i].setVisible(true);
            nomNovs[i].setVisible(true);
            botNovs[i].setVisible(true);
            iNovs++;
            i++;
        }

    }
    
    /**
     * Actualiza presentación de recomendados  
     */
    private void actualizarRecs() {
        
        ArrayList<Contenido> contenidoRecs = BusquedaOrdenacion.listaRecomendados(contenidos, ((Cliente)usuario).getPreferencias());
        
        Image portada;
        Contenido contenido;
        int i = 0;
        int iMaxR = iRecs + 4;
        recoms[0].setVisible(false);
        recoms[1].setVisible(false);
        recoms[2].setVisible(false);
        recoms[3].setVisible(false);
        nomRecoms[0].setVisible(false);
        nomRecoms[1].setVisible(false);
        nomRecoms[2].setVisible(false);
        nomRecoms[3].setVisible(false);
        botRecs[0].setVisible(false);
        botRecs[1].setVisible(false);
        botRecs[2].setVisible(false);
        botRecs[3].setVisible(false);
        
        while (iRecs < iMaxR && iRecs < contenidoRecs.size()) {
            contenido = contenidoRecs.get(iRecs);
            portada = new ImageIcon(contenido.getImagenPortada()).getImage();
            recoms[i].setIcon(new ImageIcon(portada.getScaledInstance(210, 120, 100)));
            nomRecoms[i].setText(contenido.getTitulo());
            recoms[i].setVisible(true);
            nomRecoms[i].setVisible(true);
            botRecs[i].setVisible(true);
            iRecs++;
            i++;
        }
    }
    
    /**
     * Busca las coincidencias con los criterios y los actualiza
     * 
     * @param buscar si le ha dado al botón buscar
     */
    private void actualizarContenidosBusqueda(boolean buscar) {
        Image portada;
        Contenido contenido;
        int i = 0;
        if (imagenBusq3.getIcon() != null && !buscar) {
            i = contenidoMostrarBusqueda.indexOf(BusquedaOrdenacion.buscarContenidoPorTitulo(contenidoMostrarBusqueda, titulo3.getText())) + 1;
            if (i >= contenidoMostrarBusqueda.size()) i = 0;
        }
        // Quitamos todos los datos
        imagenBusq1.setIcon(null);
        imagenBusq2.setIcon(null);
        imagenBusq3.setIcon(null);
        titulo1.setVisible(false);
        titulo2.setVisible(false);
        titulo3.setVisible(false);
        genAnnoVal1.setText(null);
        genAnnoVal2.setText(null);
        genAnnoVal3.setText(null);
        actores1.setText(null);
        actores2.setText(null);
        actores3.setText(null);
        panelSinopsis1.setVisible(false);
        panelSinopsis2.setVisible(false);
        panelSinopsis3.setVisible(false);
        
        try {
            // Rellenamos 1 a 1 hasta que dé fallo (Significa que no hay más contenido)
            contenido = contenidoMostrarBusqueda.get(i);
            portada = new ImageIcon(contenido.getImagenPortada()).getImage();
            imagenBusq1.setIcon(new ImageIcon(portada.getScaledInstance(300, 180, 100)));
            titulo1.setText(contenido.getTitulo());
            titulo1.setVisible(true);
            genAnnoVal1.setText(contenido.getGenero() + "   |   " + contenido.getAnno() + "   |   Valoración: " + contenido.getValoracion());
            actores1.setText(contenido.actoresToString());
            sinopsis1.setText(contenido.getSinopsis());
            panelSinopsis1.setVisible(true);
            i++;
            contenido = contenidoMostrarBusqueda.get(i);
            portada = new ImageIcon(contenido.getImagenPortada()).getImage();
            imagenBusq2.setIcon(new ImageIcon(portada.getScaledInstance(300, 180, 100)));
            titulo2.setText(contenido.getTitulo());
            titulo2.setVisible(true);
            genAnnoVal2.setText(contenido.getGenero() + "   |   " + contenido.getAnno() + "   |   Valoración: " + contenido.getValoracion());
            actores2.setText(contenido.actoresToString());
            sinopsis2.setText(contenido.getSinopsis());
            panelSinopsis2.setVisible(true);
            i++;
            contenido = contenidoMostrarBusqueda.get(i);
            portada = new ImageIcon(contenido.getImagenPortada()).getImage();
            imagenBusq3.setIcon(new ImageIcon(portada.getScaledInstance(300, 180, 100)));
            titulo3.setText(contenido.getTitulo());
            titulo3.setVisible(true);
            genAnnoVal3.setText(contenido.getGenero() + "   |   " + contenido.getAnno() + "   |   Valoración: " + contenido.getValoracion());
            actores3.setText(contenido.actoresToString());
            sinopsis3.setText(contenido.getSinopsis());
            panelSinopsis3.setVisible(true);
        } catch (Exception e) {}
    }
    
        
}
    

