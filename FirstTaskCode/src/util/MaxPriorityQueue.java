package util;

public class MaxPriorityQueue<T extends Comparable<T>>  {

    private MaxHeap<T> heap;

    public MaxPriorityQueue(int maxsize) {
        heap = new MaxHeap<>(maxsize);
    }

    public T extractMax() {
        if (heap.getSize() <= 0)
            return null;
        else {
            T maxVal = heap.get(1);
            heap.set(1, heap.get(heap.getSize()));
            heap.remove(heap.getSize());
            return maxVal;
        }
    }

    public void insert(T element) {
        heap.insert(element);
    }

    public boolean isEmpty() {
        return heap.getSize() == 0;
    }

    public T maximum() {
        if (heap.getSize() <= 0)
            return null;
        else
            return heap.get(1);
    }
}
