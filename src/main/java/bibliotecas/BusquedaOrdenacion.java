package bibliotecas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import objetos.Cliente;
import objetos.Contenido;
import objetos.Usuario;

/**
 * Biblioteca para buscar y ordenar contenidos y usuarios
 * 
 * @author Raul
 */
public class BusquedaOrdenacion {
    
    /**
     * Busca usuario por nombre DNI o correo y lo ordena
     * 
     * @param usuarios lista de usuarios
     * @param buscador buscador
     * @return lista de usuarios con coincidencias
     */
    public static ArrayList<Usuario> buscarUsuario(ArrayList<Usuario> usuarios, String buscador) {
        
        String buscadorReal;
        
        if (buscador.equals("Introduce la búsqueda")) buscadorReal = "";
        else buscadorReal = buscador;
        
        ArrayList<Usuario> usuariosMostrar = (ArrayList<Usuario>) usuarios.clone();
        
        usuariosMostrar = (ArrayList<Usuario>) usuariosMostrar.stream()
                .filter(v -> ((Cliente)v).getNombre().toLowerCase().contains(buscadorReal.toLowerCase()) ||
                ((Cliente)v).getDNI().toLowerCase().contains(buscadorReal.toLowerCase()) ||
                ((Cliente)v).getCorreo().toLowerCase().contains(buscadorReal.toLowerCase()))
                .collect(Collectors.toList()); 
               
        return usuariosMostrar;
        
    }
    
    // Comparador de contenidos por titulo
    private static final Comparator TituloContPerComp = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Contenido con1 = (Contenido) o1;
                Contenido con2 = (Contenido) o2;
                return (con1.getTitulo().toLowerCase()).compareTo(con2.getTitulo().toLowerCase());
            } 
        };
    
    // Comparador de contenido con un titulo
    private static final Comparator TituloPerComp = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Contenido con = (Contenido) o1;
                String str = (String) o2;
                return (con.getTitulo().toLowerCase()).compareTo(str.toLowerCase());
            } 
        };
    
    /**
     * Busca contenido según un título
     * 
     * @param contenidos lista de contenidos
     * @param titulo valor de titulo
     * @return contenido buscaado
     */
    public static Contenido buscarContenidoPorTitulo(ArrayList<Contenido> contenidos, String titulo) {
        ArrayList<Contenido> contenidosOrdenados = (ArrayList<Contenido>) contenidos.clone();
        contenidosOrdenados.sort(TituloContPerComp);
        return contenidosOrdenados.get(Collections.binarySearch(contenidosOrdenados, titulo, TituloPerComp));
    }
    
    /**
     * Crea lista de contenidos con coincidencias con argumentos
     * 
     * @param tipo tipo de contenido buscado
     * @param actores es búsqueda por actores
     * @param buscador valor de buscador
     * @param genero valor de genero
     * @param anno valor de anno
     * @param palabrasClave es búsqueda por palabras clave
     * @param contenidos lista de contenidos
     * @return lista de contenidos con coincidencias
     */
    public static ArrayList<Contenido> buscarContenido(String tipo, boolean actores, String buscador,
            String genero, String anno, boolean palabrasClave, ArrayList<Contenido> contenidos) {
        
        ArrayList<Contenido> contenidoMostrar = (ArrayList<Contenido>) contenidos.clone();
        String[] buscar;
        if (buscador.equals("Introduce la búsqueda")) buscador = "";
        
        if (!tipo.equals("Cualquiera")) {
        
            contenidoMostrar = (ArrayList<Contenido>) contenidoMostrar.stream()
                .filter(v -> v.getClass().getSimpleName().equals(tipo))
                .collect(Collectors.toList());
        
        }
        
        if (!genero.equals("Cualquiera")) {

            contenidoMostrar = (ArrayList<Contenido>) contenidoMostrar.stream()
                .filter(v -> v.getGenero().equals(genero))
                .collect(Collectors.toList());


        }
        
        if (!anno.equals("Cualquiera")) {

            contenidoMostrar = (ArrayList<Contenido>) contenidoMostrar.stream()
                .filter(v -> v.getAnno().equals(anno))
                .collect(Collectors.toList());

        }
       
        if (palabrasClave) {
            buscar = buscador.split(" ");
        } else {
            buscar = new String[1];
            buscar[0] = buscador;
        }
        
        ArrayList<Contenido> contenidoDefinitivo = new ArrayList<Contenido>();
        
        if (actores) {
            
            for (String palabra : buscar) {
                
                contenidoDefinitivo.addAll((ArrayList<Contenido>) contenidoMostrar.stream()
                    .filter(v -> v.actoresToString().toLowerCase().contains(palabra.toLowerCase())
                            || v.getSinopsis().toLowerCase().contains(palabra.toLowerCase())
                            || v.getTitulo().toLowerCase().contains(palabra.toLowerCase()))
                    .collect(Collectors.toList()));
            }

        } else {
            
            for (String palabra : buscar) {
                
                contenidoDefinitivo.addAll((ArrayList<Contenido>) contenidoMostrar.stream()
                    .filter(v -> v.getSinopsis().toLowerCase().contains(palabra.toLowerCase()) || v.getTitulo().toLowerCase().contains(palabra.toLowerCase()))
                    .collect(Collectors.toList())); 
            }
        }
        
        Set quitarDuplicados = new HashSet(contenidoDefinitivo);
        contenidoDefinitivo.clear();
        contenidoDefinitivo.addAll(quitarDuplicados);
        
        Comparator<Contenido> comparadorMultiple = Comparator.comparing(Contenido::getAnno).thenComparing(Comparator.comparing(Contenido::getValoracion));
        contenidoDefinitivo = (ArrayList<Contenido>) contenidoDefinitivo.stream().sorted(comparadorMultiple).collect(Collectors.toList());
        
        Collections.reverse(contenidoDefinitivo);
        
        return contenidoDefinitivo;
        
    }
    
    /**
     * Tipo de la busqueda
     */
    public enum TipoOrdenacionContenido {TITULO, ANNO, VALORACION};
    
    /**
     * Ordena contenidos según tipo
     * 
     * @param tipo tipo de ordenación
     * @param contenidos lista de contenidos
     * @return contenidos ordenados
     */
    public static ArrayList<Contenido> ordenarContenidos(TipoOrdenacionContenido tipo, ArrayList<Contenido> contenidos) {
        
        ArrayList<Contenido> contenidoMostrar = new ArrayList<Contenido>();
        Comparator<Contenido> comparadorMultiple;
        switch (tipo) {
            case TITULO:
                comparadorMultiple = Comparator.comparing(Contenido::getTitulo);
                contenidoMostrar = (ArrayList<Contenido>) contenidos.stream().sorted(comparadorMultiple).collect(Collectors.toList());
                break;
            case ANNO:
                comparadorMultiple = Comparator.comparing(Contenido::getAnno);
                contenidoMostrar = (ArrayList<Contenido>) contenidos.stream().sorted(comparadorMultiple).collect(Collectors.toList());    
                break;
            case VALORACION:
                comparadorMultiple = Comparator.comparing(Contenido::getValoracion);
                contenidoMostrar = (ArrayList<Contenido>) contenidos.stream().sorted(comparadorMultiple).collect(Collectors.toList());    
                break;
                
        }
        
        Collections.reverse(contenidoMostrar);
       
        return contenidoMostrar;
    }
    
    /**
     * Crea lista de recomendados del usuario
     * 
     * @param contenidos lista de contenidos
     * @param favoritos lista de favoritos del usuario
     * @return lista de recomendados
     */
    public static ArrayList<Contenido> listaRecomendados(ArrayList<Contenido> contenidos, ArrayList<String> favoritos) {
        ArrayList<Contenido> recomendados;
        
            recomendados = (ArrayList<Contenido>) contenidos.stream()
                .filter(v -> !favoritos.contains(v.getTitulo()))
                .collect(Collectors.toList());
            
        Comparator<Contenido> comparadorMultiple = Comparator.comparing(Contenido::getValoracion);
                recomendados = (ArrayList<Contenido>) recomendados.stream().sorted(comparadorMultiple).collect(Collectors.toList()); 
                
        Collections.reverse(recomendados);
                
        return recomendados;
    }
    
    
}
