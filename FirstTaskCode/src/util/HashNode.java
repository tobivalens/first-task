package util;

import customExceptions.NonExistentKeyException;

public class HashNode<K extends Comparable<K>,V extends Comparable<V>> {

	private K key; 
	private V value;
	private HashNode<K, V> next;
	private HashNode<K, V> previous;
	private HashNodeStatus status;
	
	public HashNode(K key, V value) {
		
		this.key = key;
		this.value = value;
		this.next = null;
		this.previous = null;
		this.status = HashNodeStatus.ACTIVE;
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

	public HashNode<K, V> getPrevious() {
		return previous;
	}

	public void setPrevious(HashNode<K, V> prev) {
		this.previous = prev;
	}

	public void add(HashNode<K, V> nextNode) {
		if(next == null) {
			next = nextNode;
		}
		else {
			next.add(nextNode);
		}
	}

	public void removeElement(K key){

		if(next.getKey().equals(key)){
			next.setStatus(HashNodeStatus.DELETED);
		}
		else{
			if(next.getNext() != null){
				next.getNext().removeElement(key);
			}
		}
	}
	
	public HashNode<K, V> getObject(K key) throws NonExistentKeyException{
		if(next != null){
			if(next.key.compareTo(key) == 0){
				return next;
			}
			else{
				return next.getObject(key);
			}
		}
		else {
			throw new NonExistentKeyException("");
		}
	}

	public HashNodeStatus getStatus() {
		return status;
	}

	public void setStatus(HashNodeStatus status) {
		this.status = status;
	}

    public String print(){
		String msg = "";
		if(status.equals(HashNodeStatus.ACTIVE)){
			if(value != null){
				msg += "Key: " + key + ", " + value.toString();
			}
		}
		if(next != null){
			msg += next.print();
		}
		return msg;
    }
}