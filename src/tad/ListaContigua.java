package tad;


import java.util.Arrays;

/**
 *
 * @author Samuel Loureiro Cardoso
 */
public class ListaContigua implements Lista{

    private int[] datos;
    private int size;
    //Para implementar iterador.
    private int actual;

    /**
     * Crea una lista vacia.
     */
    public ListaContigua() {
        this.datos = new int[0];
        this.actual = -1;
        this.size = 0;
    }
    
    
    @Override
    public boolean insert(int elemento, int posicion) {
        if(posicion>size || posicion<0){
            return false;
        }
        datos = Arrays.copyOf(datos, size+1);
        size++;
        for(int i = size-1; i>posicion; i--){
            datos[i] = datos[i-1];
        }
        datos[posicion] = elemento;
        return true;
    }

    @Override
    public void append(int elemento) {
        datos = Arrays.copyOf(datos, size+1);
        size++;
        datos[size-1] = elemento;
    }

    @Override
    public boolean remove(int posicion) {
        if(posicion>size || posicion<0){
            return false;
        }
        int[]nuevo = new int[size-1];
        int j = 0;
        for(int i=0;i<size;i++){
            if(i==posicion)
                continue;
            nuevo[j] = datos[i];
            j++;
        }
        datos = nuevo;
        size--;
        return true;
    }

    @Override
    public int getElementAt(int posicion) throws IndexOutOfBoundsException {
        if(posicion>=size){
            throw new IndexOutOfBoundsException("Elemento fuera de rango de la lista. Pruebe a restablecer el iterador con el método reset().");
        }
        return datos[posicion];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int next() throws IndexOutOfBoundsException {
        actual++;
        if(actual>=size){
            throw new IndexOutOfBoundsException("Elemento fuera de rango de la lista. Pruebe a restablecer el iterador con el método reset().");
        }
        return datos[actual];
    }

    @Override
    public boolean hasNext() {
        return actual+1 < size;
    }

    @Override
    public void reset() {
        actual = -1;
    }

    @Override
    public void destruir() {
        this.datos = new int[0];
        this.actual = -1;
        this.size = 0;
    }
    
    
}
