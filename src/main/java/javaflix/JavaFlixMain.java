package javaflix;

import bibliotecas.BibGestionDatos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import objetos.Contenido;
import objetos.Suscripcion;
import objetos.Usuario;
import pantallas.InicioSesion;

/**
 * Ejecuta el programa e inicializa variables
 * 
 * @author Raul
 */
public class JavaFlixMain {
    
    public static BibGestionDatos archivo = new BibGestionDatos();
    public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    public static ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
    public static Usuario usuario;
    public static HashMap<String,Suscripcion> suscripciones = new HashMap<String,Suscripcion>();

    public static void main(String[] args) throws IOException {

        usuarios = (ArrayList<Usuario>) archivo.leerArchivo("usuarios.dat");
        suscripciones = (HashMap<String,Suscripcion>) archivo.leerArchivo("suscripciones.dat");
        contenidos = (ArrayList<Contenido>) archivo.leerArchivo("contenidos.dat");

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {}
        
        InicioSesion inicioSesion = new InicioSesion();
        inicioSesion.setVisible(true);

        
    }
}

