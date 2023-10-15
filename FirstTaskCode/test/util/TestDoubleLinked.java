package FirstTaskCode.test;

import model.Task;
import org.junit.Test;
import util.DoubleLinkedList;
import util.DoubleNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestDoubleLinked {
    DoubleLinkedList<Integer> listDob;

    public void setUp1(){
        listDob=new DoubleLinkedList<>();
    }
    public void setUp2(){
        listDob=new DoubleLinkedList<>();
        listDob.addFirst(6);
        listDob.addFirst(8);
        listDob.addFirst(15);

    }
    public void setUp3(){

        listDob=new DoubleLinkedList<>();
        listDob.addLast(6);
        listDob.addLast(8);
        listDob.addLast(15);

    }

    @Test
    public void testAddFirst(){
        setUp2();
        int num = listDob.getFirst().getValue();
        assertEquals(15,num);
    }

    @Test
    public void testAddLast(){
        setUp3();
        int num = listDob.getLast().getValue();
        assertEquals(15,num);
    }
    @Test
    public void testFindNode(){
        setUp3();
        DoubleNode num= listDob.findNode(2);
        int ind= (int) num.getValue();
        assertEquals(15,ind);
    }

    @Test
    public void testDeleteFirst(){
        setUp3();
        int first= listDob.getFirst().getValue();
        listDob.deleteFirst();
        assertNotEquals(first,listDob.findNode(first));
    }
    @Test
    public void testDelete(){
        setUp3();
        int img= listDob.findNode(1).getValue();
        listDob.delete(1);
        assertNotEquals(img,listDob.findNode(1));
    }

    @Test
    public void testDeleteLast(){
        setUp3();
        int last= listDob.getLast().getValue();
        listDob.deleteLast();
        assertNotEquals(last,listDob.findNode(last));
    }

    @Test
    public void testModifyContent(){
        setUp3();
        listDob.modifyContent(1,54);
        int ind= listDob.findNode(1).getValue();
        assertEquals(54,ind);
    }



}
