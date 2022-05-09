package objetos;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Clase Contenido
 * 
 * @author Raul
 */
public abstract class Contenido implements Serializable {

    /**
     * Constructor de contenido
     * 
     * @param titulo valor de titulo
     * @param sinopsis valor de nombre
     * @param genero valor de genero
     * @param anno valor de anno
     * @param actores valor de actores
     * @param imagenPortada imagenPortada
     */
    public Contenido(String titulo, String sinopsis, String genero, String anno, String[] actores, String imagenPortada) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.genero = genero;
        this.anno = anno;
        this.actores = actores;
        this.imagenPortada = imagenPortada;
        this.valoraciones = new HashMap<>();
    }
    
   /**
    * Constructor de contenido con valoraciones
    * 
    * @param titulo
    * @param sinopsis
    * @param genero
    * @param anno
    * @param actores
    * @param imagenPortada
    * @param valoraciones 
    */ 
    public Contenido(String titulo, String sinopsis, String genero, String anno, String[] actores, String imagenPortada, HashMap<String, String> valoraciones) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.genero = genero;
        this.anno = anno;
        this.actores = actores;
        this.imagenPortada = imagenPortada;
        this.valoraciones = valoraciones;
    }
    
    private HashMap<String, String> valoraciones;

    /**
     * Get the value of valoracion
     *
     * @return the value of valoracion
     */
    public HashMap<String, String> getValoraciones() {
        return valoraciones;
    }

    /**
     * Set the value of valoracion
     *
     * @param valoraciones new value of valoraciones
     */
    public void setValoraciones(HashMap<String, String> valoraciones) {
        this.valoraciones = valoraciones;
    }
    
    /**
     * Añade valoración a contenido
     * 
     * @param correo valor de correo del usuario
     * @param nota valor de nota
     */
    public void addValoracion(String correo, String nota) {
        if (nota != null) {
            valoraciones.put(correo, nota);
        } else valoraciones.put(correo, "No calificado");
    }
    
    /**
     * Devuelve la valoración media
     * 
     * @return valoración media 
     */
    public float getValoracion() {
        float media = 0;
        int nVotos = 0;
        for (String nota : valoraciones.values()) {
            if (!nota.equals("No calificado")) {
                media += Integer.parseInt(nota);
                nVotos++;
            }
        }
        if (nVotos != 0) return media / nVotos;
        else return 0; 
    }

    
    protected String titulo;

    /**
     * Get the value of titulo
     *
     * @return the value of titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Set the value of titulo
     *
     * @param titulo new value of titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    protected  String sinopsis;

    /**
     * Get the value of sinopsis
     *
     * @return the value of sinopsis
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * Set the value of sinopsis
     *
     * @param sinopsis new value of sinopsis
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    
    protected String genero;

    /**
     * Get the value of genero
     *
     * @return the value of genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Set the value of genero
     *
     * @param genero new value of genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    protected String anno;

    /**
     * Get the value of anno
     *
     * @return the value of anno
     */
    public String getAnno() {
        return anno;
    }

    /**
     * Set the value of anno
     *
     * @param anno new value of anno
     */
    public void setAnno(String anno) {
        this.anno = anno;
    }

    protected String[] actores;

    /**
     * Get the value of actores
     *
     * @return the value of actores
     */
    public String[]  getActores() {
        return actores;
    }

    /**
     * Set the value of actores
     *
     * @param actores new value of actores
     */
    public void setActores(String[] actores) {
        this.actores = actores;
    }

    protected String imagenPortada;

    /**
     * Get the value of imagenPortada
     *
     * @return the value of imagenPortada
     */
    public String getImagenPortada() {
        return imagenPortada;
    }

    /**
     * Set the value of imagenPortada
     *
     * @param imagenPortada new value of imagenPortada
     */
    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }
    
    public String actoresToString() {
        String actoresImprimir = "";
        for (String actor : actores) actoresImprimir += actor + ", ";
        return actoresImprimir.substring(0, actoresImprimir.length() - 2);
    }
    
    /**
     * Abre pantalla crear/modificar contenido
     */
    public abstract void crearModificarContenido();
    
    @Override
    public String toString() {
        return "Contenido{" + "titulo=" + titulo + ", sinopsis=" + sinopsis + ", genero=" + genero + ", anno=" + anno + ", actores=" + actores + ", imagenPortada=" + imagenPortada + '}';
    }
    
    
    
}
