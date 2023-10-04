package util;

public class MinPriorityQueue<T extends Comparable<T>>  {

	private MinHeap<T> heap;

	public MinPriorityQueue() {
		heap = new MinHeap<>();
	}

	public T extractMin() {
		if (heap.getSize() <= 0)
			return null;
		else {
			T minVal = heap.get(1);
			heap.set(1, heap.get(heap.getSize()));  
			heap.remove(heap.getSize());
			return minVal;
		}
	}

	public void insert(T element) {
		heap.insert(element);       
	}

	public boolean isEmpty() {
		return heap.getSize() == 0;
	}

	public T minimum() {
		if (heap.getSize() <= 0)
			return null;
		else
			return heap.get(1);
	}	
}