package objetos;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase TarjetaCredito
 * 
 * @author Raul
 */
public class TarjetaCredito implements Serializable {

    /**
     * Constructor tarjeta de crédito
     * 
     * @param numero valor de numero
     * @param fechaCaducidad valor de fechaCaducidad
     * @param saldo valor de saldo
     */
    public TarjetaCredito(String numero, LocalDate fechaCaducidad, double saldo) {
        this.numero = numero;
        this.fechaCaducidad = fechaCaducidad;
        this.saldo = Math.floor(saldo * 100) / 100;
    }
    
    private String numero;

    /**
     * Get the value of numero
     *
     * @return the value of numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Set the value of numero
     *
     * @param numero new value of numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    private LocalDate fechaCaducidad;

    /**
     * Get the value of fechaCaducidad
     *
     * @return the value of fechaCaducidad
     */
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * Set the value of fechaCaducidad
     *
     * @param fechaCaducidad new value of fechaCaducidad
     */
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    private double saldo;

    /**
     * Get the value of saldo
     *
     * @return the value of saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Set the value of saldo
     *
     * @param saldo new value of saldo
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    /**
     * Efectua la compra
     * 
     * @param precio valor de precio
     */
    public void efectuarCompra(double precio) {
        this.saldo -= precio;
    }

    @Override
    public String toString() {
        return  numero + "/" + fechaCaducidad.getYear() + "/" + fechaCaducidad.getMonthValue() + "/" + saldo;
    }
    
    
    
    
}
