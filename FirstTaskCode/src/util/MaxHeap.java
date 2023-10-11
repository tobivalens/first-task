package util;

import customExceptions.HeapFullException;
import customExceptions.ObjectNotFoundException;

public class MaxHeap<T extends Comparable<T>>{
    
    public final int MAX_SIZE = 200;
    private T[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public MaxHeap() {
        this.size = 0;
        heap = (T[]) new Comparable[MAX_SIZE];
    }

    public void insert(T element) throws HeapFullException {
        if(size == heap.length - 1) {
            throw new HeapFullException("The heap is full. Can't add more elements.");
        }
        heap[++size] = element;
        int current = size;
        while(current > 1 && heap[current].compareTo(heap[parent(current)]) > 0) {
            switchPlaces(current, parent(current));
            current = parent(current);
        }
    }

    private void maxHeapify(int pos) {
        int largest;
        int left = leftChild(pos);
        int right = rightChild(pos);
        if(left <= size && heap[left].compareTo(heap[pos]) > 0){
            largest = left;
        }
        else{
            largest = pos;
        }
        if(right <= size && heap[right].compareTo(heap[largest]) > 0){
            largest = right;
        }
        if(largest != pos){
            switchPlaces(pos, largest);
            maxHeapify(largest);
        }
    }

    public void buildMaxHeap() {
        for(int i = size / 2; i >= 1; i--){
            maxHeapify(i);
        }
    }

    public void heapsort() {
        int tmp = size;
        for(int i = size; i >= 2; i--){
            switchPlaces(1, i);
            size--;
            maxHeapify(1);
        }
        size = tmp;
    }

    public T remove(int pos) {
        T extracted = heap[pos];
        heap[pos] = heap[size--];
        maxHeapify(pos);
        return extracted;
    }

    public int getIndexForAnObject(T object) throws ObjectNotFoundException{
        for(int i = 0; i < heap.length; i++){
            if(heap[i] != null){
                if(heap[i].equals(object)){
                    return i;
                }
            }
        }
        throw new ObjectNotFoundException("Couldn't find the object");
    }
    
    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private void switchPlaces(int fpos, int spos) {
        T tmp;
        tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    public int getSize() {
        return this.size;
    }
    
    public T getElement(int pos) {
        return heap[pos];
    }

    public void setElement(int pos, T element) {
        heap[pos] = element;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public T[] getHeap() {
        return heap;
    }
}