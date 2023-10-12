package FirstTaskCode.test.util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import org.junit.Assert.*;

import customExceptions.HashIsEmptyException;
import customExceptions.NonExistentKeyException;
import model.PriorityLevel;
import model.Task;
import util.HashNode;
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
    public void testHashtableInsert() throws HashIsEmptyException, NonExistentKeyException{
        Task t= setUp1();
        hash.insertElement( 2, t);
        assertEquals(t, hash.searchElement(2).getValue());
    }


}
