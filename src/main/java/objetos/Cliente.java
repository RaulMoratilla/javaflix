package objetos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import static javaflix.JavaFlixMain.archivo;
import static javaflix.JavaFlixMain.suscripciones;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import objetos.Suscripcion.TipoSuscripcion;
import pantallas.JavaFlixCliente;

/**
 * Clase cliente
 * 
 * @author Raul
 */
public class Cliente extends Usuario implements Serializable {

    /**
     * Constructor Cliente
     * 
     * @param DNI valor de DNI
     * @param nombre valor de nombre
     * @param tarjetaCredito valor de tarjetaCredito 
     * @param correo valor de correo
     * @param clave  valor de clave
     */
    public Cliente(String DNI, String nombre, TarjetaCredito tarjetaCredito, String correo, String clave) {
        super(correo, clave);
        this.DNI = DNI;
        this.nombre = nombre;
        this.tarjetaCredito = tarjetaCredito;
        this.preferencias = new ArrayList<>();
    }

    /**
     * Constructor cliente con preferencias
     * 
     * @param DNI valor de DNI
     * @param nombre valor de nombre
     * @param tarjetaCredito valor de tarjetaCredito
     * @param preferencias valor de preferencias
     * @param correo valor de correo
     * @param clave valor de clave
     */
    public Cliente(String DNI, String nombre, TarjetaCredito tarjetaCredito, ArrayList<String> preferencias, String correo, String clave) {
        super(correo, clave);
        this.DNI = DNI;
        this.nombre = nombre;
        this.tarjetaCredito = tarjetaCredito;
        this.preferencias = preferencias;
    }
    
    
    
    private String DNI;

    /**
     * Get the value of DNI
     *
     * @return the value of DNI
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * Set the value of DNI
     *
     * @param DNI new value of DNI
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    
    private String nombre;

    /**
     * Get the value of Nombre
     *
     * @return the value of Nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of Nombre
     *
     * @param nombre new value of Nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    private TarjetaCredito tarjetaCredito;

    /**
     * Get the value of tarjetaCredito
     *
     * @return the value of tarjetaCredito
     */
    public TarjetaCredito getTarjetaCredito() {
        return tarjetaCredito;
    }

    /**
     * Set the value of tarjetaCredito
     *
     * @param tarjetaCredito new value of tarjetaCredito
     */
    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }
    
    private ArrayList<String> preferencias;

    /**
     * Get the value of preferencias
     *
     * @return the value of preferencias
     */
    public ArrayList<String> getPreferencias() {
        return preferencias;
    }

    /**
     * Set the value of preferencias
     *
     * @param preferencias new value of preferencias
     */
    public void setPreferencias(ArrayList<String> preferencias) {
        this.preferencias = preferencias;
    }
    
    /**
     * Abre pantalla de cliente
     */
    @Override
    public void abrirPantalla() {
        JavaFlixCliente pantallaCliente = new JavaFlixCliente();
        pantallaCliente.setVisible(true);
    }
    
    /**
     * Añade preferencia
     * 
     * @param contenido valor de contenido añadir
     */
    public void addPreferencia(Contenido contenido) {
        preferencias.add(contenido.getTitulo());
    }
    
    /**
     * Suscribe al usuario
     * 
     * @param tipo tipo de suscripción
     * @param pantalla pantalla en el que sacar mensaje
     */
    public void suscribirse(TipoSuscripcion tipo, JFrame pantalla) {
        if (LocalDate.now().isBefore(tarjetaCredito.getFechaCaducidad())) {
            boolean comprado = false;
            switch (tipo) {
                case BASICO:
                    if (tarjetaCredito.getSaldo() >= 8.99) {
                        tarjetaCredito.efectuarCompra(8.99);
                        comprado = true;
                    }
                    break;
                case ESTANDAR:
                    if (tarjetaCredito.getSaldo() >= 11.99) {
                        tarjetaCredito.efectuarCompra(11.99);
                        comprado = true;
                    }
                    break;
                case PREMIUM:
                    if (tarjetaCredito.getSaldo() >= 15.99) {
                        tarjetaCredito.efectuarCompra(15.99);
                        comprado = true;
                    }
                    break;
            }
            if (comprado) {   
                archivo.crearSuscripcion(pantalla, tipo, this);
            } else JOptionPane.showMessageDialog(pantalla, "No tiene suficiente saldo", "Error", JOptionPane.ERROR_MESSAGE);;
        } else JOptionPane.showMessageDialog(pantalla, "La tarjeta está caducada", "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public String toString() {

        boolean encontrado = suscripciones.containsKey(correo);
        if (encontrado) {
            Suscripcion suscripcion = suscripciones.get(correo);
            return DNI + "   |   " + nombre + "   |   " + correo +  "   |   Clave: " + 
                    clave + "   |   Tarjeta: "  + tarjetaCredito.getNumero() + "   |   Saldo: "
                    + tarjetaCredito.getSaldo() + "   |   " + suscripcion.getTipo() + "   |   Fecha de suscripción: "
                    + suscripcion.getFecha().getDayOfMonth() + ", " + suscripcion.getFecha().getMonth()
                    + ", " + suscripcion.getFecha().getYear();
            
        } else return DNI + "   |   " + nombre + "   |   " + correo + "   |   Clave: " + clave +
                "   |   Tarjeta: "  + tarjetaCredito.getNumero() + "   |   Saldo: " + tarjetaCredito.getSaldo() + "   |   No Suscrito";
    }
    
}
