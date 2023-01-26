/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import tad.ListaContigua;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Samu
 */
public class ListaContiguaTest {

    private ListaContigua lc;

    public ListaContiguaTest() {
    }

    @Before
    public void setUp() {
        lc = new ListaContigua();
    }

    @After
    public void tearDown() {
        lc = null;
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void size() {
        boolean vacia = lc.size() == 0;
        lc.append(0);
        lc.append(1);
        assertTrue(lc.size() == 2 && vacia);
        
    }
    @Test
    public void append(){
        lc.append(0);
        lc.append(1);
        assertTrue(lc.size()==2 && lc.getElementAt(0)==0 && lc.getElementAt(1)==1);
    }
    
    @Test
    public void remove(){
        lc.append(0);
        lc.append(1);
        lc.append(2);
        lc.remove(1);
        assertTrue("Tamanho incorrecto. Debiera ser 2 y es "+lc.size(),lc.size()==2);
        int e = lc.getElementAt(1);
        assertTrue("Elemento en 1 debiera ser 2 y es "+e,e==2);
        
    }
    
    @Test
    public void getElementAt(){
        lc.append(0);
        lc.append(1);
        lc.append(2);
        try{
            int e0 = lc.getElementAt(0);
            int e1 = lc.getElementAt(1);
            int e2 = lc.getElementAt(2);
            assertTrue(e0==0&&e1==1&&e2==2);
        }catch(IndexOutOfBoundsException e){
            fail();
        }
        
    }
    
    @Test
    public void getElementAt_IndexOutOfBoundsException(){
        try{
            lc.getElementAt(10);
            fail();
        }catch(IndexOutOfBoundsException e){
            assert(true);
        }
        
    }

    @Test
    public void inset_true(){
        lc.append(0);
        lc.append(1);
        lc.append(2);
        
        boolean r = lc.insert(6, 1);
        boolean datos = lc.getElementAt(0) == 0 
                && lc.getElementAt(1)== 6 
                && lc.getElementAt(2)== 1
                && lc.getElementAt(3) == 2;
        assertTrue(r && datos && lc.size() == 4);
    }
    
    @Test
    public void next(){
        lc.append(0);
        lc.append(1);
        lc.append(2);
        int cont = 0;
        while(lc.hasNext()){
            assertEquals(lc.next(), cont);
            cont++;
        }
    }
    
}
