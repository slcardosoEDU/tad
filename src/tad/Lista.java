package tad;


/**
 *
 * @author Samu
 */
public interface Lista {
    
    public boolean insert(int elemento, int posicion);
    public void append(int elemento);
    public boolean remove(int posicion);
    public int getElementAt(int posicion) throws IndexOutOfBoundsException;
    public int size();
    public int next() throws IndexOutOfBoundsException;
    public boolean hasNext();
    public void reset();
    public void destruir();
    
}
