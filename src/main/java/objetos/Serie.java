package objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import pantallas.CrearModificarSerie;

/**
 * Clase Serie
 * 
 * @author Raul
 */
public class Serie extends Contenido implements Serializable {

    /**
     * Constructor serie
     * 
     * @param temporadas valor de temporadas
     * @param titulo valor de titulo
     * @param sinopsis valor de sinopsis
     * @param genero valor de genero
     * @param anno valor de anno
     * @param actores valor de actores
     * @param imagenPortada valor de imagenPortada
     */
    public Serie(ArrayList<ArrayList<Capitulo>> temporadas, String titulo, String sinopsis, String genero, String anno, String[] actores, String imagenPortada) {
        super(titulo, sinopsis, genero, anno, actores, imagenPortada);
        this.temporadas = temporadas;
    }
    public Serie(ArrayList<ArrayList<Capitulo>> temporadas, String titulo, String sinopsis, String genero, String anno, String[] actores, String imagenPortada, HashMap<String, String> valoraciones) {
        super(titulo, sinopsis, genero, anno, actores, imagenPortada, valoraciones);
        this.temporadas = temporadas;
    }
    
    private ArrayList<ArrayList<Capitulo>> temporadas;

    /**
     * Get the value of temporadas
     *
     * @return the value of temporadas
     */
    public ArrayList<ArrayList<Capitulo>> getTemporadas() {
        return temporadas;
    }
    
    /**
     * Set the value of temporadas
     *
     * @param temporadas new value of temporadas
     */
    public void setTemporadas(ArrayList<ArrayList<Capitulo>> temporadas) {
        this.temporadas = temporadas;
    }
    
    /**
     * Añade temporada a temporadas
     * 
     * @param temporada valor de temporada
     */
    public void addTemporada(ArrayList<Capitulo> temporada) {
        temporadas.add(temporada);
    }

    @Override
    public String toString() {
        return "Serie   |   " + titulo + "   |   Valoracion: " + getValoracion() + "   |   " + genero + "   |   " + anno + "   |   Actores: " + actoresToString();
    }
    
    /**
     * Abre pantalla de crear/modificar serie
     */
    @Override
    public void crearModificarContenido() {
        
        CrearModificarSerie crearSerie = new CrearModificarSerie();
        crearSerie.setVisible(true);
        
    }
    
}
