package objetos;

import java.io.Serializable;

/**
 * Clase Usuario
 * 
 * @author Raul
 */
public abstract class Usuario implements Serializable {

    /**
     * Constructor de usuario
     * 
     * @param correo valor de correo
     * @param clave valor de clave
     */
    public Usuario(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }
    
    protected String correo;

    /**
     * Get the value of correo
     *
     * @return the value of correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Set the value of correo
     *
     * @param correo new value of correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    protected String clave;

    /**
     * Get the value of clave
     *
     * @return the value of clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * Set the value of clave
     *
     * @param clave new value of clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Abre pantalla del usuario
     */
    public abstract void abrirPantalla();
    
    @Override
    public String toString() {
        return "Usuario{" + "correo=" + correo + ", clave=" + clave + '}';
    }
    
    
}
