package FirstTaskCode.test;
import org.junit.Test;

import java.util.GregorianCalendar;
import org.junit.Assert.*;

import customExceptions.HashIsEmptyException;
import customExceptions.NonExistentKeyException;
import model.PriorityLevel;
import model.Task;
import util.HashTable;

import static org.junit.Assert.*;

public class TestHashtable{

    HashTable<Integer,Task> hash;

    public Task setUp1(){
        hash= new HashTable<>();
        Task x= new Task("Task1", "Hard", 50,
                new GregorianCalendar(2023, 4, 29), PriorityLevel.PRIORITY);
        return x;

    }
    public void setUp2(){

    }
    public void setUp3(){
        hash= new HashTable<>();
    }

    @Test
    public void testHashtableInsert() throws HashIsEmptyException, NonExistentKeyException{
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

    @Test
    public void testRestore() throws HashIsEmptyException, NonExistentKeyException {
        setUp3();
        Task task = new Task("id1", "description1", 1, null, null);
        hash.insertElement(1, task);
        hash.deleteElement(1);
        assertTrue(hash.isEmpty());
        assertThrows(HashIsEmptyException.class, () -> {
            hash.restoreElement(1, task);
            assertEquals(task, hash.searchElement(1).getValue());;});
    }

    @Test
    public void testSearch() throws HashIsEmptyException, NonExistentKeyException {
        Task t = setUp1();
        hash.insertElement( t.getKey(), t);
        assertEquals(t, hash.searchElement(t.getKey()).getValue());

    }




}
