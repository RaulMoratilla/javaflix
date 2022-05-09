package objetos;

import java.io.Serializable;
import java.util.ArrayList;
import static javaflix.JavaFlixMain.archivo;
import static javaflix.JavaFlixMain.contenidos;
import static javaflix.JavaFlixMain.suscripciones;
import static javaflix.JavaFlixMain.usuarios;
import pantallas.JavaFlixAdministrador;

/**
 * Clase Administrador
 * 
 * @author Raul
 */
public class Administrador extends Usuario implements Serializable {

    
    /**
     * Constructor de Administrador
     * 
     * @param correo value of correo
     * @param clave value of correo
     */
    public Administrador(String correo, String clave) {
        super(correo, clave);
    }
    
    /*
     * 
     * @return toString
     */
    @Override
    public String toString() {
        return "Administrador{" + '}';
    }
    
    /**
     * Abrir pantalla de administrador
     */
    @Override
    public void abrirPantalla() {
        JavaFlixAdministrador pantallaAdmin = new JavaFlixAdministrador();
        pantallaAdmin.setVisible(true);
    }
    
    /**
     * Añade contenido a contenidos
     * 
     * @param contenido contenido a añadir
     * @param contenidos lista en la que añadirlo
     */
    public void crearContenido(Contenido contenido, ArrayList<Contenido> contenidos) {
        contenidos.add(contenido);
    }
    
    /**
     * Borra contenido de lista de contenidos y de listas de preferencias
     * 
     * @param contenido contenido a borrar
     * @param contenidos lista en la que borrarlo
     */
    public void borrarContenido(Contenido contenido, ArrayList<Contenido> contenidos) {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) archivo.leerArchivo("usuarios.dat");
        int indice = contenidos.indexOf(contenido);
        for (Usuario usuario : usuarios) {
            try {
                if (((Cliente)usuario).getPreferencias().contains(contenido.titulo))
                    ((Cliente)usuario).getPreferencias().remove(contenido.titulo);
                archivo.guardarArchivo("usuarios.dat", usuarios);
            // El administrador no tiene lista de preferencias
            } catch (ClassCastException e) {}
        }
        contenidos.remove(indice);
    }
    
    /**
     * Modifica película
     * 
     * @param pelicula valor de pelicula
     * @param duracion nuevo valor de duracion
     * @param director nuevo valor de director
     * @param titulo nuevo valor de titulo
     * @param sinopsis nuevo valor de sinopsis
     * @param genero nuevo valor de genero
     * @param anno nuevo valor de anno
     * @param actores nuevo valor de actores
     * @param imagenPortada nuevo valor de imagenPortada
     */
    public void modificarContenido(Pelicula pelicula, int duracion, String director, String titulo, String sinopsis, String genero, String anno, String[] actores, String imagenPortada) {
        pelicula.setDuracion(duracion);
        pelicula.setDirector(director);
        pelicula.setTitulo(titulo);
        pelicula.setSinopsis(sinopsis);
        pelicula.setActores(actores);
        pelicula.setGenero(genero);
        pelicula.setAnno(anno);
        pelicula.setImagenPortada(imagenPortada);
    }
    
    /**
     * Modifica serie
     * 
     * @param serie valor de serie
     * @param temporadas nuevo valor de of temporadas
     * @param titulo nuevo valor de of titulo
     * @param sinopsis nuevo valor de of sinopsis
     * @param genero nuevo valor de of genero
     * @param anno nuevo valor de of anno
     * @param actores nuevo valor de of actores
     * @param imagenPortada nuevo valor de imagenPortada
     */
    public void modificarContenido(Serie serie, ArrayList<ArrayList<Capitulo>> temporadas, String titulo, String sinopsis, String genero, String anno, String[] actores, String imagenPortada) {
        serie.setTemporadas(temporadas);
        serie.setTitulo(titulo);
        serie.setSinopsis(sinopsis);
        serie.setActores(actores);
        serie.setGenero(genero);
        serie.setAnno(anno);
        serie.setImagenPortada(imagenPortada);
    }
    
    /**
     * Borra usuario de la lista y sus calificaciones de los contenidos
     * 
     * @param indice indice del usuario
     * @param usuariosMostrar lista de usuarios
     */
    public void borrarUsuario(int indice, ArrayList<Usuario> usuariosMostrar) {
        Usuario usuarioSeleccionadoMuestra = usuariosMostrar.get(indice);
        Usuario usuarioSeleccionado = usuarios.get(usuarios.indexOf(usuarioSeleccionadoMuestra));

        // Borra de la lista de contenidos las valoraciones del usuario
        contenidos = (ArrayList<Contenido>) archivo.leerArchivo("contenidos.dat");
        for (Contenido contenido : contenidos) {
            if (contenido.getValoraciones().keySet().contains(usuarioSeleccionado.getCorreo()))
                contenido.getValoraciones().remove(usuarioSeleccionado.getCorreo());
        }

        // Elimina usuario y suscripción
        usuarios.remove(usuarioSeleccionado);
        suscripciones.remove(usuarioSeleccionado.getCorreo());

        // Guarda archivos
        archivo.guardarArchivo("contenidos.dat", contenidos);
        archivo.guardarArchivo("usuarios.dat", usuarios);
        archivo.guardarArchivo("suscripciones.dat", suscripciones);
    }
    
    /**
     * Borra la suscripción del usuario de la lista de suscripciones
     * 
     * @param indice indice del usuario
     * @param usuariosMostrar lista de usuarios
     */
    public void cancelarSuscripcion(int indice, ArrayList<Usuario> usuariosMostrar) {
        // Obtiene el usuario que se ha seleccionado
        Usuario usuarioSeleccionadoMuestra = usuariosMostrar.get(indice);

        // Obtiene el usuario seleccionado en la lista de usuarios real
        Usuario usuarioSeleccionado = usuarios.get(usuarios.indexOf(usuarioSeleccionadoMuestra));

        // Borra la suscripción
        suscripciones.remove(usuarioSeleccionado.getCorreo());
        archivo.guardarArchivo("suscripciones.dat", suscripciones);
    }
    
}
