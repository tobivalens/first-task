package util;

import customExceptions.NonExistentKeyException;

public class HashNode<K,V extends Comparable<V>> {

	private K key; 
	private V value;
	private HashNode<K, V> next;
	private HashNode<K, V> prev;
	
	public HashNode(K key, V value) {
		
		this.key = key;
		this.value = value;
		this.next = null;
		this.prev = null;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public HashNode<K, V> getNext() {
		return next;
	}

	public void setNext(HashNode<K, V> next) {
		this.next = next;
	}

	public HashNode<K, V> getPrev() {
		return prev;
	}

	public void setPrev(HashNode<K, V> prev) {
		this.prev = prev;
	}

	public void add(HashNode<K, V> nextNode) {
		if(next == null) {
			next = nextNode;
		}
		else {
			next.add(nextNode);
		}
	}

	public void removeLast() {
		if(next.getNext() == null) {
			next = null;
		}else {
			next.removeLast();
		}
	}
	
	public HashNode<K, V> getObjet(V value) throws NonExistentKeyException{
		if(next != null) {
			if(next.value.compareTo(value)== 0) {
				return next;
			}else {
				return next.getObjet(value);
			}
		}
		else {
			throw new NonExistentKeyException("");
		}
	}
}