package FirstTaskCode.test;
import customExceptions.StackIsEmptyException;
import model.Action;
import model.ActionType;
import model.PriorityLevel;
import model.Task;
import org.junit.Test;
import util.Queue;
import util.Stack;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;
public class TestStack{

    Stack<Action> stack;

    public void setUp1(){
        stack= new Stack<>();
    }
    public void setUp2(){
        stack= new Stack<>();
        Action lastAction = new Action(ActionType.MODIFY, null, null);
        stack.push(lastAction);
    }
    void setUp3(){
        stack= new Stack<>();
        Action Action1 = new Action(ActionType.MODIFY, null, null);
        stack.push(Action1);
        Action Action2 = new Action(ActionType.ADD, null, null);
        stack.push(Action2);
    }
    @Test
    public void testPush() throws StackIsEmptyException {
        setUp1();
        Action lastAction = new Action(ActionType.MODIFY, null, null);
        stack.push(lastAction);
        assertEquals(lastAction, stack.top().getValue());
        assertEquals(1, stack.getSize());
    }

    @Test
    public void testPop() throws StackIsEmptyException {
        setUp2();
        stack.pop();
        assertTrue(stack.isEmpty());
        assertEquals(0,stack.getSize());
    }

    @Test
    public void testTop() throws StackIsEmptyException {
        setUp3();
        Action x= stack.top().getValue();
        Action Action1 = new Action(ActionType.ADD, null, null);
        stack.push(Action1);
        assertEquals(Action1.getActionType(),x.getActionType());
        assertEquals(Action1.getTask(),x.getTask());
        assertEquals(Action1.getOriginalTask(), x.getOriginalTask());
    }

    @Test
    public void testSize() throws StackIsEmptyException {
        setUp3();
        assertEquals(2,stack.getSize());
        stack.pop();
        assertEquals(1,stack.getSize());
    }

    @Test
    public void isEmpty(){
        setUp1();
        assertTrue(stack.isEmpty());
    }



}
