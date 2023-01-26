package tad;

/**
 *
 * @author Samuel Loureiro Cardoso
 */
public class ListaEnlazada implements Lista {

    private Nodo cabecera;
    private Nodo actual;
    private int size;

    public ListaEnlazada() {
        cabecera = null;
        actual = null;
        size = 0;
    }

    @Override
    public boolean insert(int elemento, int posicion) {
        Nodo nuevo;
        boolean toret = true;
        if (posicion == 0) {
            nuevo = new Nodo(elemento, cabecera);
            cabecera = nuevo;
        } else {
            try {
                Nodo anterior = getNodoAtIndex(posicion - 1);
                nuevo = new Nodo(elemento, anterior.next());
                anterior.setNext(nuevo);

            } catch (IndexOutOfBoundsException e) {
                toret = false;
            }
        }
        if (toret) {
            size++;
        }
        return toret;
    }

    @Override
    public void append(int elemento) {
        Nodo nuevo = new Nodo(elemento);
        if (cabecera == null) {
            cabecera = nuevo;
        } else {
            Nodo aux = cabecera;
            while (aux.hasNext()) {
                aux = aux.next();
            }
            aux.setNext(nuevo);
        }
        size++;
    }

    @Override
    public boolean remove(int posicion) {
        boolean toret = true;
        //Si es el primero
        if (posicion == 0) {
            try {
                cabecera = cabecera.next();
            } catch (NullPointerException e) {
                toret = false;
            }
        } else {
            try {
                Nodo anterior = getNodoAtIndex(posicion - 1);
                Nodo siguiente = anterior.next().next();
                anterior.setNext(siguiente);

            } catch (IndexOutOfBoundsException e) {
                toret = false;
            }
        }
        
        if(toret){
            size--;
        }
        return toret;

    }

    @Override
    public int getElementAt(int posicion) throws IndexOutOfBoundsException {
        return getNodoAtIndex(posicion).getDato();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int next() throws IndexOutOfBoundsException {
         if(actual==null){
             actual = cabecera;
         }else{
             actual = actual.next();
         }
         if(actual==null){
             throw new IndexOutOfBoundsException();
         }
         return actual.getDato();
    }

    @Override
    public boolean hasNext() {
        if(actual==null){
            return cabecera !=null;
        }
        return actual.hasNext();
    }

    @Override
    public void reset() {
        actual = null;
    }

    private Nodo getNodoAtIndex(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return cabecera;
        }
        Nodo actual = cabecera;
        for (int i = 1; i <= index; i++) {
            actual = actual.next();
        }
        return actual;
    }
    
    @Override
    public void destruir() {
        cabecera = null;
        actual = null;
        size = 0;
    }

}
