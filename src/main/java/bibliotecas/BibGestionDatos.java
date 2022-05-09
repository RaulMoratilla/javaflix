package bibliotecas;

import java.io.*;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaflix.JavaFlixMain.archivo;
import static javaflix.JavaFlixMain.suscripciones;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import objetos.*;
import objetos.Suscripcion.TipoSuscripcion;

/**
 * Biblioteca para serialización, creación de tickets de compra, suscripciones
 * y trasladar imagenes
 * 
 * @author Raul
 */
public class BibGestionDatos {
    
    /**
     * Guarda objetoGuardar en la ruta archivo
     * 
     * @param archivo ruta del archivo
     * @param objetoGuardar objeto a guardar
     */
    public void guardarArchivo(String archivo, Object objetoGuardar) {
        
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objetoGuardar);
            fos.close();
        } catch (Exception e) {System.out.println("Error al guardar el archivo" + archivo);}
    }
    
    /**
     * Lee objeto
     * 
     * @param archivo ruta del archivo
     * @return objeto (hacer cast)
     */
    public Object leerArchivo(String archivo) {
        
        Object archivoLeido = new Object();
        
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            archivoLeido = (Object) ois.readObject();
            fis.close();
        } catch (Exception e) {System.out.println("Error en la lectura del archivo " + archivo);}

        return archivoLeido;
    }
    
    /**
     * Mueve imagen de rutaInicio a rutaDestino
     * 
     * @param rutaInicio ruta en la que está el archivo
     * @param rutaDestino ruta a la que mover el archivo
     */
    public void moverImagen(String rutaInicio, String rutaDestino) {
        File imagenAbs = new File(rutaInicio);
        File imagenRel= new File(rutaDestino);
        try {
            Files.copy(imagenAbs.toPath(),imagenRel.toPath(), REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(BibGestionDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Crea ticket (.txt) de la suscripción
     * 
     * @param cliente valor de cliente
     * @param tipo valor de tipo
     * @param fecha valor de fecha de compra
     */
    private void crearSuscripcionTxt(Cliente cliente, TipoSuscripcion tipo, LocalDate fecha) {
        
        File archivo;
        PrintWriter escribir;
        
        archivo = new File("suscripciones/" + cliente.getDNI() + ".txt");
        
        try {
            archivo.createNewFile();

        } catch (IOException e) {}

        try {

            escribir = new PrintWriter(archivo, "utf-8");
            try {
                escribir.println("******TICKET DE SUSCRIPCIÓN******");
                escribir.println("\nNombre cliente: " + cliente.getNombre());
                escribir.println("DNI: " + cliente.getDNI());
                escribir.println("Correo: " + cliente.getCorreo());
                escribir.println("Tarjeta: " + cliente.getTarjetaCredito().getNumero());
                escribir.println("Fecha de compra: " + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear());
                escribir.println("Tipo de suscripción: " + tipo.toString());
                escribir.println("Vencimiento de la suscripción: " + fecha.getDayOfMonth() + "/" + fecha.plusMonths(1).getMonthValue() + "/" + fecha.getYear());

            } catch (Exception e) {}

            escribir.close();

            } catch (Exception e) {}
            
    }
    
    /**
     * Crea suscripción y ticket
     * 
     * @param pantalla valor de pantalla
     * @param tipo valor de tipo
     * @param cliente valor de cliente
     */
    public void crearSuscripcion(JFrame pantalla, TipoSuscripcion tipo, Cliente cliente) {
        LocalDate diaCompra = LocalDate.now();
        try {
            suscripciones.put(cliente.getCorreo(), new Suscripcion(tipo, cliente, diaCompra));
            archivo.crearSuscripcionTxt(cliente, tipo, diaCompra);
            JOptionPane.showMessageDialog(pantalla,"La suscripción se ha registrado", "SUSCRIPCIÓN REGISTRADA", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pantalla,"No se ha completado la suscripción", "SUSCRIPCIÓN NO REGISTRADA", JOptionPane.INFORMATION_MESSAGE);
        }
        
        archivo.guardarArchivo("suscripciones.dat", suscripciones);
    }
    
}
