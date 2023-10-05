package util;

import customExceptions.QueueIsEmptyException;

public class Queue<T> {

    private DoubleLinkedList<T> list;

    public Queue() {
        list = new DoubleLinkedList<>();
    }

    public void enQueue(T newElement) {
        list.addLast(newElement);
    }

    public void deQueue() throws QueueIsEmptyException {
        T removedElement = list.deleteFirst();
        if (removedElement == null) {
            throw new QueueIsEmptyException("The queue is empty");
        }
    }

    public DoubleNode<T> front() throws QueueIsEmptyException{
        if(list.isEmpty() == true){
            throw new QueueIsEmptyException("The queue is empty");
        }
        else{
            return list.getFirst();
        }
    }

    public int getSize() {
        return list.getNumberOfElements();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public DoubleLinkedList<T> getList() {
        return list;
    }
}