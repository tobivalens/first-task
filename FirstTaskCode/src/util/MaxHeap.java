package util;

import customExceptions.HeapFullException;
import customExceptions.ObjectNotFoundException;
import customExceptions.PriorityQueueIsEmptyException;
import customExceptions.QueueIsEmptyException;

public class MaxHeap<T extends Comparable<T>>{

    private int size;
    private T[] heap;

    public MaxHeap(int maxsize){
        this.heap = (T[]) new Comparable[maxsize];
        this.size = 0;
    }

    public void maxHeapify(int i){
        int l = leftChild(i);
        int r = rightChild(i);
        int largest;
        if(l < size && heap[l].compareTo(heap[i]) > 0){
            largest = l;
        }
        else{
            largest = i;
        }
        if(r < size && heap[r].compareTo(heap[largest]) > 0){
            largest = r;
        }
        if(largest != i){
            swap(i, largest);
            maxHeapify(largest);
        }
    }

    public boolean isEmpty() {
        return heap[0] == null;
    }

    public T[] getHeap(){
        return heap;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int heapSize){
        this.size = heapSize;
    }

    public void insert(T element) throws HeapFullException {
        if (size >= heap.length) {
            throw new HeapFullException("The heap is full");
        }
        heap[size] = element;
        int current = size;
        while (current > 0 && heap[current].compareTo(heap[parent(current)]) > 0) {
            swap(current, parent(current));
            current = parent(current);
        }
        this.size++;
    }    

    public T extractMax() throws PriorityQueueIsEmptyException {
        if(size < 1){
            throw new PriorityQueueIsEmptyException("The heap is empty");
        }
        T max = getMax();
        heap[0] = heap[size - 1];
        heap[size] = null;
        size--;
        maxHeapify(0);
        return max;
    }

    public T getMax() {
        return heap[0];
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int parent(int index) {
        return index / 2;
    }

    private int leftChild(int i){
        return 2 * i + 1;
    }

    private int rightChild(int i){
        return 2 * i + 2;
    }

    public void remove(int index) throws ObjectNotFoundException{
        if(index < 0 || index >= size){
            throw new ObjectNotFoundException("The index is not valid");
        }
        else{
            if(size == 1){
                heap[0] = null;
            }
            else{
                heap[index] = heap[size-1];
                heap[--size] = null;
                maxHeapify(index);
            }
        }
    }
    
    public int getIndexForAnObject(T element){
        for(int i = 0; i < heap.length; i++){
            if(heap[i] != null){
                if(heap[i].equals(element)){
                    return i;
                }
            }
        }
        return -1;
    }

    public String printHeap(){
        String msg = "";
        for(int i = 0; i < heap.length; i++){
            if(heap[i] != null){
                msg += heap[i].toString();
            }
        }
        return msg;
    }
}