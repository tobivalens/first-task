package util;

public class Queue<T> {

    private DoubleLinkedList<T> list;

    public Queue() {
        list = new DoubleLinkedList<>();
    }

    public void enQueue(T newElement) {
        list.addLast(newElement);
    }

    public void deQueue() {
        T removedElement = list.deleteFirst();
        if (removedElement == null) {
            // La cola está vacía, puedes manejar este caso si es necesario
            // Por ejemplo, lanzando una excepción o mostrando un mensaje.
        }
    }

    public DoubleNode<T> getFront() {
        return list.isEmpty() ? null : list.findNode(0);
    }

    public int getSize() {
        return list.getNumberOfElements();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}