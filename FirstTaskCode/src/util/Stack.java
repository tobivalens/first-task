package util;

public class Stack<T> {

    private DoubleLinkedList<T> list;

    public Stack() {
        list = new DoubleLinkedList<>();
    }

    public void push(T newElement) {
        list.addFirst(newElement);
    }

    public void pop() {
        T removedElement = list.deleteFirst();
        if (removedElement == null) {
            // La pila está vacía, puedes manejar este caso si es necesario
            // Por ejemplo, lanzando una excepción o mostrando un mensaje.
        }
    }

    public DoubleNode<T> topElement() {
        return list.isEmpty() ? null : list.findNode(0);
    }

    public int getSize() {
        return list.getNumberOfElements();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
