import tad.ListaEnlazada;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Samu
 */
public class ListaEnlazadaTest {
    
    private ListaEnlazada le;

    public ListaEnlazadaTest() {
    }

    @Before
    public void setUp() {
        le = new ListaEnlazada();
    }

    @After
    public void tearDown() {
        le = null;
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void size() {
        boolean vacia = le.size() == 0;
        le.append(0);
        le.append(1);
        assertTrue(le.size() == 2 && vacia);
        
    }
    @Test
    public void append(){
        le.append(0);
        le.append(1);
        assertTrue(le.size()==2 && le.getElementAt(0)==0 && le.getElementAt(1)==1);
    }
    
    @Test
    public void remove(){
        le.append(0);
        le.append(1);
        le.append(2);
        le.remove(1);
        assertTrue("Tamanho incorrecto. Debiera ser 2 y es "+le.size(),le.size()==2);
        int e = le.getElementAt(1);
        assertTrue("Elemento en 1 debiera ser 2 y es "+e,e==2);
        
    }
    
    @Test
    public void getElementAt(){
        le.append(0);
        le.append(1);
        le.append(2);
        try{
            int e0 = le.getElementAt(0);
            int e1 = le.getElementAt(1);
            int e2 = le.getElementAt(2);
            assertTrue(e0==0&&e1==1&&e2==2);
        }catch(IndexOutOfBoundsException e){
            fail();
        }
        
    }
    
    @Test
    public void getElementAt_IndexOutOfBoundsException(){
        try{
            le.getElementAt(10);
            fail();
        }catch(IndexOutOfBoundsException e){
            assert(true);
        }
        
    }

    @Test
    public void inset_true(){
        le.append(0);
        le.append(1);
        le.append(2);
        
        boolean r = le.insert(6, 1);
        boolean datos = le.getElementAt(0) == 0 
                && le.getElementAt(1)== 6 
                && le.getElementAt(2)== 1
                && le.getElementAt(3) == 2;
        assertTrue(r && datos && le.size() == 4);
    }
    
    @Test
    public void next(){
        le.append(0);
        le.append(1);
        le.append(2);
        int cont = 0;
        while(le.hasNext()){
            assertEquals(le.next(), cont);
            cont++;
        }
    }
    
}
