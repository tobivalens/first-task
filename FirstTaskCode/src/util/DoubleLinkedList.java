package util;

public class DoubleLinkedList<T> {

    DoubleNode<T> first;
    DoubleNode<T> last;
    int numberOfElements;

    public DoubleLinkedList() {
        first = null;
        last = null;
        numberOfElements = 0;
    }

    public boolean isEmpty(){
        return (first == null);
    }

    public void addFirst(T value) {
        DoubleNode<T> newNode = new DoubleNode<>(value);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setNext(first);
            first.setPrevious(newNode);
            first = newNode;
        }
        numberOfElements++;
    }

    public void addLast(T value) {
        DoubleNode<T> newNode = new DoubleNode<>(value);
        if (last == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setPrevious(last);
            last.setNext(newNode);
            last = newNode;
        }
        numberOfElements++;
    }

    public DoubleNode<T> findNode(int index) {
        DoubleNode<T> currentNode = first;
        if (index >= 0 && index < numberOfElements) {
            int localIndex = 0;
            while (localIndex < index) {
                currentNode = currentNode.getNext();
                localIndex++;
            }
        }
        return currentNode;
    }

    public void delete(T value) {
        DoubleNode<T> currentNode = first;
        while (currentNode != null) {
            if (currentNode.getValue().equals(value)) {
                if (currentNode == first) {
                    first = currentNode.getNext();
                    if (first != null) {
                        first.setPrevious(null);
                    } else {
                        last = null;
                    }
                } else if (currentNode == last) {
                    last = currentNode.getPrevious();
                    if (last != null) {
                        last.setNext(null);
                    } else {
                        first = null;
                    }
                } else {
                    DoubleNode<T> previous = currentNode.getPrevious();
                    DoubleNode<T> next = currentNode.getNext();
                    previous.setNext(next);
                    next.setPrevious(previous);
                }
                numberOfElements--;
                return;
            }
            currentNode = currentNode.getNext();
        }
    }

    public T deleteFirst() {
        if (first != null) {
            T deletedValue = first.getValue();
            if (first == last) {
                first = null;
                last = null;
            } else {
                first = first.getNext();
                first.setPrevious(null);
            }
            numberOfElements--;
            return deletedValue;
        }
        return null;
    }

    public T deleteLast() {
        if (last != null) {
            T deletedValue = last.getValue();
            if (first == last) {
                first = null;
                last = null;
            } else {
                last = last.getPrevious();
                last.setNext(null);
            }
            numberOfElements--;
            return deletedValue;
        }
        return null;
    }

    public void modifyContent(int index, T value) {
        if (index >= 0 && index < numberOfElements) {
            DoubleNode<T> currentNode = first;
            int localIndex = 0;
            while (localIndex < index) {
                currentNode = currentNode.getNext();
                localIndex++;
            }
            if (currentNode != null) {
                currentNode.setValue(value);
            }
        }
    }

    public String toString() {
        String message = "";
        DoubleNode<T> currentNode = first;
        while (currentNode != null) {
            message += currentNode.toString() + "\n";
            currentNode = currentNode.getNext();
        }
        return message;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public DoubleNode<T> getFirst() {
        return first;
    }

    public void setFirst(DoubleNode<T> first) {
        this.first = first;
    }

    public DoubleNode<T> getLast() {
        return last;
    }

    public void setLast(DoubleNode<T> last) {
        this.last = last;
    }
}