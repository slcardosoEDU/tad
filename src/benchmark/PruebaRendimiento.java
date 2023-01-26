/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package benchmark;

import java.util.Arrays;
import java.util.Random;
import tad.Lista;
import tad.ListaEnlazada;
import tad.ListaContigua;

/**
 * Clase que permite probar el rendimiento de las estructuras dinámicas
 * {@link ListaContigua} y {@link ListaEnlazada}.
 * Es importante tener en cuenta que el rendimiento es muy dependiente de la 
 * máquina de prueba, del sistema operativo y del resto de procesos que se estén
 * ejecutando en el momento actual.
 * @author Samuel Loureiro Cardoso
 */
public class PruebaRendimiento {

    /**
     * Cantidad de veces que se probará cada caso de prueba.
     */
    public final static int MUESTREO = 30;
    /**
     * Incremento de datos en cada caso de prueba.
     */
    public final static int AVANCE = 1000;

    /**
     * Enumerado que define los diferentes tipos de operación con una lista.
     */
    public static enum operacion {
        INSERT, APPEND, REMOVE, GET, RECORRER
    };

    public static void main(String[] args) {
        long[]  resultado;
        Lista l;
        //INSERT
        System.out.println("INSERT");
        l = new ListaEnlazada();
        resultado = benchmark(operacion.INSERT, l, 20);
        System.out.println(formatOutput(resultado));
        
        l = new ListaContigua();
        resultado = benchmark(operacion.INSERT, l, 20);
        System.out.println(formatOutput(resultado));
        
        //APPEND
        System.out.println("APPEND");
        l = new ListaEnlazada();
        resultado = benchmark(operacion.APPEND, l, 20);
        System.out.println(formatOutput(resultado));
        
        l = new ListaContigua();
        resultado = benchmark(operacion.APPEND, l, 20);
        System.out.println(formatOutput(resultado));
        
        //REMOVE
        System.out.println("REMOVE");
        l = new ListaEnlazada();
        resultado = benchmark(operacion.REMOVE, l, 20);
        System.out.println(formatOutput(resultado));
        
        l = new ListaContigua();
        resultado = benchmark(operacion.REMOVE, l, 20);
        System.out.println(formatOutput(resultado));
        
        //GET
        System.out.println("GET");
        l = new ListaEnlazada();
        resultado = benchmark(operacion.GET, l, 20);
        System.out.println(formatOutput(resultado));
        
        l = new ListaContigua();
        resultado = benchmark(operacion.GET, l, 20);
        System.out.println(formatOutput(resultado));
        
        //RECORRER
        System.out.println("RECORRER");
        l = new ListaEnlazada();
        resultado = benchmark(operacion.RECORRER, l, 20);
        System.out.println(formatOutput(resultado));
        
        l = new ListaContigua();
        resultado = benchmark(operacion.RECORRER, l, 20);
        System.out.println(formatOutput(resultado));

    
        
    }

    /**
     * Prueba una de las operaciones de la lista tantas veces como 
     * se indique en <em>nPruebas</em>.
     * Cada caso de prueba incrementará el volumen de datos de la lista el 
     * número de veces indicado en {@link #AVANCE}<br>
     * Cada caso de prueba se realizará un número de veces determinado por la 
     * constante {@link #MUESTREO}. De este modo puede calcularse con más
     * precisión el tiempo medio de ejecución.
     * Las operaciones permitidas son: insert, append, remove, get y recorrer.
     * Estas operaciones están definidas en el enumerado {@link operacion}
     * @param o Operación a probar definida en el enumerado {@link operacion}.
     * @param l Lista a probar
     * @param nPruebas Incremento de datos en cada prueba. Cada prueba aumentará
     *              el tamaño de la lista en VECES.
     * @return Array con los tiempos de ejecución medios de cada caso de prueba
     *         expresado en microsegundos. La posición 0 se corresponde con el
     *         caso de prueba 1 ({@link #AVANCE} datos) y la posción 
     *         <em>nPruebas - 1</em> se corresponde con el último caso de prueba
     *         ({@link #AVANCE} x <em>nPruebas</em> datos).
     * @see #AVANCE
     * @see #MUESTREO
     * @see operacion
     */
    public static long[] benchmark(operacion o, Lista l, int nPruebas) {
        long[][] matriz = new long[MUESTREO][nPruebas];
        for (int i = 0; i < MUESTREO; i++) {
            
            for (int j = 0; j < nPruebas; j++) {
                l.destruir();
                getLista(l, AVANCE*(j+1));
                long ns = System.nanoTime();
                switch (o) {
                    case INSERT -> l.insert(666, (int) (l.size() / 2));
                    case APPEND -> l.append(666);
                    case REMOVE -> l.remove((int) (l.size() / 2));
                    case GET -> l.getElementAt((int) (l.size() / 2));
                    case RECORRER -> {
                        while (l.hasNext()) {
                            l.next();
                        }
                    }
                }
                ns = System.nanoTime() - ns;
                matriz[i][j] = ns/1000;
            }
        }

        return media(matriz);
    }

    /**
     * Calcula la media truncada (20%) para una tabla de casos de prueba.
     * @param muestra Tabla de casos de prueba [muestras][casos_prueba].
     * @return media truncada de los casos de prueba obtenidos en cada muestra.
     */
    public static long[] media(long[][] muestra){
        long[] toret = new long[muestra[0].length];
        //Calculo media
        for(int i=0;i<muestra[0].length;i++){
            long [] aux = new long[muestra.length];
            for (int j = 0; j <muestra.length ; j++) {
               //Obtengo todas las muestras de una dimension.
                aux[j] = muestra[j][i];
            }
            //Ordeno las muestras
            Arrays.sort(aux);
            //Trunco el 20% 
            int truncar = (int)(aux.length * 0.2);
            for(int k = truncar; k<aux.length-truncar;k++){
                //Introduzco suma en toret
                toret[i] += aux[k];
            }
            //Media
            toret[i] = (long)(toret[i]/(aux.length-(2*truncar)));
            
        }
        
        return toret;
    }

    /**
     * Carga <em>n</em> datos (números enteros) aleatorios en la lista <em>l</em>
     * @param l Lista donde se cargarán los datos.
     * @param n Cantidad de datos a generar.
     */
    public static void getLista(Lista l, int n) {
        Random r = new Random(n);
        for (int i = 0; i < n; i++) {
            l.append(r.nextInt());
        }
    }

    /**
     * Rellena la lista <em>l</em> con <em>n</em> ceros.
     * @param l Lista donde se cargarán los datos.
     * @param n Cantidad de ceros a cargar en la lista..
     */
    public static void getListaZero(Lista l, int n) {
       
        for (int i = 0; i < n; i++) {
            l.append(0);
        }
    }
    
    /**
     * Formatea un array de casos de prueba de modo que cada caso de prueba se
     * separe del anterior con una tabulación en una misma línea.
     * Este método es util para copia a mano los resultados y copiarlos en una
     * hoja de cálculo.
     * @param cp Array de tiempo de ejecución de n casos de prueba.
     * @return 
     */
    public static String formatOutput(long[] cp){
        String s="";
        for(long n : cp){
            s+=n+"\t";
        }
        return s;
    }

}
