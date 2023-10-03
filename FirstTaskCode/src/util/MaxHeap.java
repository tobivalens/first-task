package util;

public class MaxHeap<T extends Comparable<T>> {
    private T[] Heap;
    private int size;
    private int maxsize;

    @SuppressWarnings("unchecked")
    public MaxHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = (T[]) new Comparable[maxsize];
    }

    public MaxHeap(T[] array, int maxsize) {
        this.maxsize = maxsize;
        this.size = array.length;
        Heap = (T[]) new Comparable[maxsize];
        for (int i = 0; i < array.length; i++)
            Heap[i + 1] = array[i];
        buildMaxHeap();
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
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    @SuppressWarnings("unchecked")
    private void maxHeapify(int pos) {
        int largest;
        int l = leftChild(pos);
        int r = rightChild(pos);
        if (l <= size && Heap[l].compareTo(Heap[pos]) > 0)
            largest = l;
        else
            largest = pos;
        if (r <= size && Heap[r].compareTo(Heap[largest]) > 0)
            largest = r;
        if (largest != pos) {
            swap(pos, largest);
            maxHeapify(largest);
        }
    }

    public int getSize() {
        return this.size;
    }

    @SuppressWarnings("unchecked")
    public void insert(T element) {
        Heap[++size] = element;
        int current = size;
        while (current > 1 && Heap[current].compareTo(Heap[parent(current)]) > 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void print() {
        System.out.print("[");
        for (int i = 1; i <= size; i++) {
            if (i == size)
                System.out.print(Heap[i]);
            else
                System.out.print(Heap[i] + ", ");
        }
        System.out.println("]");
    }

    public void buildMaxHeap() {
        for (int i = size / 2; i >= 1; i--)
            maxHeapify(i);
    }

    public T get(int pos) {
        return Heap[pos];
    }

    public void set(int pos, T element) {
        Heap[pos] = element;
    }

    public T remove(int pos) {
        T extracted = Heap[pos];
        Heap[pos] = Heap[size--];
        maxHeapify(pos);
        return extracted;
    }

    public void heapsort() {
        int tmp = size;
        for (int i = size; i >= 2; i--) {
            swap(1, i);
            size--;
            maxHeapify(1);
        }
        size = tmp;
    }
}
