package FirstTaskCode.test;

import customExceptions.HeapFullException;
import customExceptions.ObjectNotFoundException;
import customExceptions.PriorityQueueIsEmptyException;
import customExceptions.QueueIsEmptyException;
import model.Action;
import model.ActionType;
import model.PriorityLevel;
import model.Task;
import org.junit.Test;
import util.MaxHeap;
import util.Stack;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class TestMaxHeap {

    MaxHeap<Task> priority;

    public void setUp(){
        priority= new MaxHeap<>(20);
    }

    public Task setUp2() throws HeapFullException {
        priority= new MaxHeap<>(20);
        Task x= new Task("Task1", "nada", 50, new GregorianCalendar(2024, 5, 02),
                PriorityLevel.PRIORITY);
        return x;
    }

    public void setUp3() throws HeapFullException {
        priority= new MaxHeap<>(20);
        Task x= new Task("Task1", "null", 50, new GregorianCalendar(2023, 05, 27),
                PriorityLevel.PRIORITY);
        priority.insert(x);
        Task x1= new Task("Task2", "null", 23, new GregorianCalendar(2024, 04, 2),
                PriorityLevel.PRIORITY);
        priority.insert(x1);
    }

    public void setUp4() throws HeapFullException {
        priority= new MaxHeap<>(20);
        Task x= new Task("1", "null", 50, new GregorianCalendar(2023, 05, 27),
                PriorityLevel.PRIORITY);


        Task x1= new Task("2", "null", 23, new GregorianCalendar(2024, 04, 2),
                PriorityLevel.PRIORITY);


        Task x2= new Task("3", "null", 15, new GregorianCalendar(2024, 05, 2),
                PriorityLevel.PRIORITY);


        Task x3= new Task("4", "null", 31, new GregorianCalendar(2024, 06, 2),
                PriorityLevel.PRIORITY);

        priority.insert(x3);
        priority.insert(x);
        priority.insert(x1);
        priority.insert(x2);
    }


    @Test
    public void maxHeapifyTest() throws HeapFullException {
        setUp4();
        priority.maxHeapify(0);
        assertEquals(50,priority.getMax().getKey());

    }

    @Test
    public void testInsert() throws HeapFullException {
        Task x= setUp2();
        priority.insert(x);
        assertEquals(x,priority.getMax());
        assertEquals(1,priority.getSize());
    }

    @Test
    public void testExtractMax() throws QueueIsEmptyException, HeapFullException, PriorityQueueIsEmptyException {
        setUp3();
        Task z= priority.extractMax();
        assertEquals(50,z.getKey());
    }

    @Test
    public void testRemove() throws HeapFullException, ObjectNotFoundException {
        setUp3();
        Task maxBeforeRemoval = priority.getMax();
        priority.remove(0);
        assertNotEquals(maxBeforeRemoval, priority.getMax());
    }

    @Test
    public void testGetIndexForAnObject() throws HeapFullException {
        Task t=setUp2();
        priority.insert(t);
        int index= priority.getIndexForAnObject(t);
        assertEquals(0,index);
    }

    @Test
    public void testIsEmpty(){
        setUp();
        assertTrue(priority.isEmpty());
    }

    @Test
    public void testPrint() throws HeapFullException {
        Task k=setUp2();
        priority.insert(k);
        String expected= "Name: " + k.getName() + ", Description: " + k.getDescription() + ", Limit Date: " + k.getLimitDateString() + ", Priority Level: "
                + k.getPriorityLevel() + "\n";;
        assertEquals(expected, priority.printHeap());
    }


}
