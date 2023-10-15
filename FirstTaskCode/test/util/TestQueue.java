package FirstTaskCode.test;
import customExceptions.HashIsEmptyException;
import customExceptions.QueueIsEmptyException;
import model.PriorityLevel;
import model.Task;
import org.junit.Test;
import util.Queue;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class TestQueue {
    Queue<Task> tail;

    void setUp1(){
        tail= new Queue<>();
    }

    void setUp2(){
        tail= new Queue<>();
        Task x= new Task("Task1", "Hard", 50, new GregorianCalendar(2023, 05, 04),
                PriorityLevel.NON_PRIORITY);
        tail.enQueue(x);
    }

    void setUp3(){
        tail= new Queue<>();
        Task x= new Task("Task1", "hard", 50, new GregorianCalendar(2024, 04, 01),
                PriorityLevel.NON_PRIORITY);
        tail.enQueue(x);
        Task x1= new Task("Task2", "easy", 32, new GregorianCalendar(2024, 05, 02),
                PriorityLevel.NON_PRIORITY);
        tail.enQueue(x1);
    }

    @Test
    public void testEnque() throws QueueIsEmptyException {
        setUp1();
        Task x= new Task("null", "null", 50,
                new GregorianCalendar(0, 0, 0), PriorityLevel.NON_PRIORITY);
        tail.enQueue(x);
        assertEquals(x, tail.front().getValue());
        assertEquals(1, tail.getSize());
    }

    @Test
    public void testDeque() throws QueueIsEmptyException {
        setUp2();
        tail.deQueue();
        assertTrue(tail.isEmpty());
        assertEquals(0,tail.getSize());

    }

    @Test
    public void testFront() throws QueueIsEmptyException {
        setUp3();
        int t = tail.front().getValue().getKey();
        Task x= new Task("null", "null", 50,
                new GregorianCalendar(0, 0, 0), PriorityLevel.NON_PRIORITY);
        assertEquals(x.getKey(), t);
    }

    @Test
    public void testEmpty(){
        setUp1();
        assertTrue(tail.isEmpty());
    }

    @Test
    public void testSize() throws QueueIsEmptyException {
        setUp3();
        assertEquals(2,tail.getSize());
        tail.deQueue();
        assertEquals(1,tail.getSize());
    }




}
