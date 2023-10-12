package FirstTaskCode.test.util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.util.GregorianCalendar;
import org.junit.Assert.*;

import customExceptions.HashIsEmptyException;
import customExceptions.NonExistentKeyException;
import model.PriorityLevel;
import model.Task;
import util.HashTable;


public class TestHashtable{

   HashTable<Integer,Task> hash;

    public Task setUp1(){
     hash= new HashTable<>();
     Task x= new Task("null", "null", 50,
      new GregorianCalendar(0, 0, 0), PriorityLevel.PRIORITY);
      return x;
      
    }
    public void setUp2(){

        
    }
    public void setUp3(){
        
    }

    @Test
    public void testHashtableInsertAndSearchElement() throws HashIsEmptyException, NonExistentKeyException{
        Task t= setUp1();
        hash.insertElement( 50, t);
        assertEquals(t, hash.searchElement(50).getValue());
    }

    @Test
    public void testHashtableDelete() throws HashIsEmptyException, NonExistentKeyException{
        Task t = setUp1();
        hash.insertElement(50, t);
        hash.insertElement(32,new Task("null1", "null1", 32,
         new GregorianCalendar(0, 0, 0), PriorityLevel.PRIORITY) );
         hash.deleteElement(50);
        assertThrows(NonExistentKeyException.class, () -> {
          assertNotNull(hash.searchElement(50));});
    }
   


}
