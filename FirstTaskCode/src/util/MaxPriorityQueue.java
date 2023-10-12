package util;

import customExceptions.HeapFullException;
import customExceptions.PriorityQueueIsEmptyException;

public class MaxPriorityQueue<T extends Comparable<T>> {

    private MaxHeap<T> heap;

    public MaxPriorityQueue(int maxsize) {
        heap = new MaxHeap<T>();
    }

    public T extractMax() throws PriorityQueueIsEmptyException {
        if (heap.getSize() <= 0)
            throw new PriorityQueueIsEmptyException("The max priority queue is empty");
        else {
            T maxVal = heap.getElement(1);
            heap.setElement(1, heap.getElement(heap.getSize()));
            heap.remove(heap.getSize());
            return maxVal;
        }
    }

    public void insert(T element) throws HeapFullException {
        heap.insert(element);
    }

    public boolean isEmpty() {
        return heap.getSize() == 0;
    }

    public T maximum() throws PriorityQueueIsEmptyException {
        if (heap.getSize() <= 0)
            throw new PriorityQueueIsEmptyException("Priority queue is empty");
        else
            return heap.getElement(1);
    }

    public MaxHeap<T> getHeap() {
        return heap;
    }

    public String printHeap(){
        String msg = "";
        for(int i = 0; i < heap.getHeap().length; i++){
            if(heap.getHeap()[i] != null){
                msg += heap.getHeap()[i].toString();
            }
        }
        return msg;
    }
}