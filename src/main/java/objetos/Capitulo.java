package objetos;

import java.io.Serializable;

/**
 * Clase Capítulo
 * 
 * @author Raul
 */
public class Capitulo implements Serializable {

    /**
     * Constructor de Capitulo
     * 
     * @param nombre value of nombre
     * @param duracion value of duracion
     */
    public Capitulo(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }
    
    private String nombre;

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Capítulo: " + nombre + ", " + duracion + " minutos";
    }


    
    
}
