package util;

public class MinHeap<T extends Comparable<T>> {
    
    public final int MAX_SIZE = 20;
    private T[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public MinHeap() {
        this.size = 0;
        heap = (T[]) new Comparable[MAX_SIZE];
    }

    public void insert(T element) {
        heap[++size] = element;
        int current = size;
        while (current > 1 && heap[current].compareTo(heap[parent(current)]) < 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    private void minHeapify(int pos) {
        int smallest;
        int l = leftChild(pos);
        int r = rightChild(pos);
        if (l <= size && heap[l].compareTo(heap[pos]) < 0)
            smallest = l;
        else
            smallest = pos;
        if (r <= size && heap[r].compareTo(heap[smallest]) < 0)
            smallest = r;
        if (smallest != pos) {
            swap(pos, smallest);
            minHeapify(smallest);
        }
    }

    public void buildMinHeap() {
        for (int i = size / 2; i >= 1; i--)
            minHeapify(i);
    }

    public void heapsort() {
        int tmp = size;
        for (int i = size; i >= 2; i--) {
            swap(1, i);
            size--;
            minHeapify(1);
        }
        size = tmp;
    }

    public T remove(int pos) {
        T extracted = heap[pos];
        heap[pos] = heap[size--];
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

    private void swap(int fpos, int spos) {
        T tmp;
        tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    public int getSize() {
        return this.size;
    }

    public T get(int pos) {
        return heap[pos];
    }

    public void set(int pos, T element) {
        heap[pos] = element;
    }
}