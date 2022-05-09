package bibliotecas;

/**
 * Biblioteca para validar datos introducidos por usuario
 * 
 * @author Raul
 */
public class BibValidar {
    
    /**
     * Valida DNI
     * 
     * @param DNI valor de DNI
     * @return esDNI
     */
    public static boolean esDNI(String DNI) {
        char[] letras = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        if (DNI.length() != 9) return false;
        String numDNI = "";
        for (int i = 0 ; i < 8 && i < DNI.length() ; i++) {
            if (DNI.charAt(i) == ' ') return false;
            numDNI += DNI.charAt(i);
        }
        try {
            return letras[Integer.parseInt(numDNI) % 23] == DNI.charAt(8);
        } catch (Exception e) {return false;}
    }
    
    /**
     * Valida correo
     * 
     * @param correo valor de correo
     * @return esCorreo
     */
    public static boolean esCorreo(String correo) {
        int posArroba = 0, posPunto = 0;
        for (int i = 0 ; i < correo.length() && correo.charAt(i) != '@'; i++) {
            if (correo.charAt(i) != '.' && !(correo.charAt(i) >= '0' && correo.charAt(i) <= '9') && !(correo.charAt(i) >= 'a' && correo.charAt(i) <= 'z')) return false;
            posArroba = i + 1;
        }
        if (posArroba == 0 || posArroba == correo.length()) return false;
        
        for (int i = posArroba + 1 ; i < correo.length() && correo.charAt(i) != '.'; i++) {  
            if ((correo.charAt(i) < 'a' || correo.charAt(i) > 'z') && correo.charAt(i) != '.') return false;
            posPunto = i + 1;
        }
        if (posPunto == posArroba + 1 || posPunto == correo.length() || posPunto == correo.length() - 1) return false;
        for (int i = posPunto + 1 ; i < correo.length(); i++) {  
            if (correo.charAt(i) < 'a' || correo.charAt(i) > 'z') return false;
        } 
        return true;
    }
    
    /**
     * Valida nombre
     * 
     * @param nombre valor de nombre
     * @return esNombre
     */
    public static boolean esNombre(String nombre) {
        if (nombre.length() == 0) return false;
        for (int i = 0 ; i < nombre.length() ; i++) {
            if ((nombre.charAt(i) < 'a' || nombre.charAt(i) > 'z') && nombre.charAt(i) != 'á'
                    && nombre.charAt(i) != 'é' && nombre.charAt(i) != 'í' && nombre.charAt(i) != 'ó'
                    && nombre.charAt(i) != 'ú' && nombre.charAt(i) != 'ñ' && nombre.charAt(i) != 'ü'
                    && nombre.charAt(i) != ' ' && nombre.charAt(i) != '-') return false;
        }
        
        return true;
    }
    
    /**
     * Valida tarjeta
     * 
     * @param tarjeta valor de tarjeta
     * @return esTarjeta
     */
    public static boolean esTarjeta(String tarjeta) {
        for (int i = 0 ; i < tarjeta.length() ; i++) {
            if (!(tarjeta.charAt(i) >= '0' && tarjeta.charAt(i) <= '9')) return false;
        }
        return tarjeta.length() == 16;
    }   
    
}
