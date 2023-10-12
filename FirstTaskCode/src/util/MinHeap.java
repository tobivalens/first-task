package util;

import customExceptions.HeapFullException;

public class MinHeap<T extends Comparable<T>> {
    
    public final int MAX_SIZE = 20;
    private T[] heap;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public MinHeap() {
        this.currentSize = 0;
        heap = (T[]) new Comparable[MAX_SIZE];
    }

    public void insert(T element) throws HeapFullException {
        if (currentSize == heap.length - 1) {
            throw new HeapFullException("The heap is full. Can't add more elements.");
        }
        heap[++currentSize] = element;
        int current = currentSize;
        while (current > 1 && heap[current].compareTo(heap[parent(current)]) < 0) {
            switchPlaces(current, parent(current));
            current = parent(current);
        }
    }
    

    private void minHeapify(int pos) {
        int smallest;
        int l = leftChild(pos);
        int r = rightChild(pos);
        if (l <= currentSize && heap[l].compareTo(heap[pos]) < 0)
            smallest = l;
        else
            smallest = pos;
        if (r <= currentSize && heap[r].compareTo(heap[smallest]) < 0)
            smallest = r;
        if (smallest != pos) {
            switchPlaces(pos, smallest);
            minHeapify(smallest);
        }
    }

    public void buildMinHeap() {
        for (int i = currentSize / 2; i >= 1; i--)
            minHeapify(i);
    }

    public void heapsort() {
        int tmp = currentSize;
        for (int i = currentSize; i >= 2; i--) {
            switchPlaces(1, i);
            currentSize--;
            minHeapify(1);
        }
        currentSize = tmp;
    }

    public T remove(int pos) {
        T extracted = heap[pos];
        heap[pos] = heap[currentSize--];
        minHeapify(pos);
        return extracted;
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

    public int getCurrentSize() {
        return this.currentSize;
    }

    public T get(int pos) {
        return heap[pos];
    }

    public void set(int pos, T element) {
        heap[pos] = element;
    }
}