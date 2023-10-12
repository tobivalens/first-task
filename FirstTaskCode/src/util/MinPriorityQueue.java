package util;

import customExceptions.HeapFullException;
import customExceptions.PriorityQueueIsEmptyException;

public class MinPriorityQueue<T extends Comparable<T>>  {

	private MinHeap<T> heap;

	public MinPriorityQueue() {
		heap = new MinHeap<>();
	}

	public T extractMin() throws PriorityQueueIsEmptyException {
		if (heap.getCurrentSize() <= 0)
			throw new PriorityQueueIsEmptyException("Priority queue is empty");
		else {
			T minVal = heap.get(1);
			heap.set(1, heap.get(heap.getCurrentSize()));  
			heap.remove(heap.getCurrentSize());
			return minVal;
		}
	}

	public void insert(T element) throws HeapFullException {
		heap.insert(element);       
	}

	public boolean isEmpty() {
		return heap.getCurrentSize() == 0;
	}

	public T minimum() throws PriorityQueueIsEmptyException {
		if (heap.getCurrentSize() <= 0)
			throw new PriorityQueueIsEmptyException("Priority queue is empty");
		else
			return heap.get(1);
	}	
}