package objetos;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase Suscripcion
 * 
 * @author Raul
 */
public class Suscripcion implements Serializable{
    
    /**
     * Tipos de suscripción
     */
    public enum TipoSuscripcion {BASICO, ESTANDAR, PREMIUM}
    
    /**
     * Constructor suscripción
     * 
     * @param tipo valor de tipo
     * @param cliente valor de cliente
     * @param fecha valor de fecha
     */
    public Suscripcion(TipoSuscripcion tipo, Cliente cliente, LocalDate fecha) {
        this.tipo = tipo;
        this.cliente = cliente;
        this.fecha = fecha;
    }

    
    private TipoSuscripcion tipo;

    /**
     * Get the value of tipo
     *
     * @return the value of tipo
     */
    public TipoSuscripcion getTipo() {
        return tipo;
    }

    /**
     * Set the value of tipo
     *
     * @param tipo new value of tipo
     */
    public void setTipo(TipoSuscripcion tipo) {
        this.tipo = tipo;
    }

    
    private Cliente cliente;

    /**
     * Get the value of cliente
     *
     * @return the value of cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Set the value of cliente
     *
     * @param cliente new value of cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private LocalDate fecha;

    /**
     * Get the value of fecha
     *
     * @return the value of fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Set the value of fecha
     *
     * @param fecha new value of fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Suscripcion{" + "tipo=" + tipo + ", cliente=" + cliente + ", fecha=" + fecha + '}';
    }
        
    
}
