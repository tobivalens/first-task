package util;

import customExceptions.StackIsEmptyException;

public class Stack<T> {

    private DoubleLinkedList<T> list;

    public Stack() {
        list = new DoubleLinkedList<>();
    }

    public void push(T newElement) {
        list.addLast(newElement);
    }

    public void pop() throws StackIsEmptyException {
        T removedElement = list.deleteLast();
        if (removedElement == null) {
            throw new StackIsEmptyException("The stack is empty");
        }
    }

    public DoubleNode<T> top() throws StackIsEmptyException{
        if(list.isEmpty() == true){
            throw new StackIsEmptyException("The stack is empty");
        }
        else{
            return list.getLast();
        }
    }

    public int getSize() {
        return list.getNumberOfElements();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
