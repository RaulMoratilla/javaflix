package objetos;

import java.util.HashMap;
import pantallas.CrearModificarPelicula;

/**
 * Clase Película
 * 
 * @author Raul
 */
public class Pelicula extends Contenido {

    /**
     * Constructor de película
     * 
     * @param duracion valor de duracion
     * @param director valor de director
     * @param titulo valor de titulo
     * @param sinopsis valor de sinopsis
     * @param genero valor de genero
     * @param anno valor de anno
     * @param actores valor de actores
     * @param imagenPortada alor de imagenPortada
     */
    public Pelicula(int duracion, String director, String titulo, String sinopsis, String genero, String anno, String[] actores, String imagenPortada) {
        super(titulo, sinopsis, genero, anno, actores, imagenPortada);
        this.duracion = duracion;
        this.director = director;
    }
    
    public Pelicula(int duracion, String director, String titulo, String sinopsis, String genero, String anno, String[] actores, String imagenPortada, HashMap<String, String> valoraciones) {
        super(titulo, sinopsis, genero, anno, actores, imagenPortada, valoraciones);
        this.duracion = duracion;
        this.director = director;
    }

    private int duracion;

    /**
     * Get the value of duracion
     *
     * @return the value of duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Set the value of duracion
     *
     * @param duracion new value of duracion
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    private String director;

    /**
     * Get the value of director
     *
     * @return the value of director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Set the value of director
     *
     * @param director new value of director
     */
    public void setDirector(String director) {
        this.director = director;
    }
    
    /**
     * Abre pantalla de crear/modificar película
     */
    @Override
    public void crearModificarContenido() {
        CrearModificarPelicula crearPelicula = new CrearModificarPelicula();
        crearPelicula.setVisible(true);
    }
    
    @Override
    public String toString() {
        return "Pelicula   |   " + titulo + "   |   Valoración: " + getValoracion() +  "   |   " + genero + "   |   "
                + anno + "   |   Actores: " + actoresToString() + "   |   Director: "
                + director + "   |   Duración: " + duracion + " min";
    }

    
    
}
