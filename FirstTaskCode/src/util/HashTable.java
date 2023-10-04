package util;

import customExceptions.HashIsEmptyException;
import customExceptions.NonExistentKeyException;

public class HashTable<K ,V extends Comparable<V>> {

	public final int HASH_SIZE = 10;
	private HashNode<K, V>[] hashList;
	private int size; 
	
	public HashTable() {
		this.size = 0;
		hashList = new HashNode[HASH_SIZE];
	}

	public int hashFunction(K key) {
		int hashCode = key.hashCode();
		int index = Math.floorMod(hashCode, HASH_SIZE);
		return index;
	}

	public boolean isEmpty() {
		boolean isEmpty = true;
		if(size != 0){
			isEmpty = false;
		}
		return isEmpty;
	}

	public int getSize() {
		return size;
	}
		
	public void insertElement(K key, V value) {
		
		int index = hashFunction(key);
		HashNode<K, V> newNode = new HashNode<K, V>(key, value);
		if(hashList[index]!= null) {
			hashList[index].add(newNode);
		}
		else {
			hashList[index] = newNode;
			size++;
		}
	}

	public HashNode<K, V> searchElement(K key, V value) throws HashIsEmptyException, NonExistentKeyException{
		
		int index = hashFunction(key);

		if(isEmpty() == true){
			throw new HashIsEmptyException("Gracias por: ");
		}
		else{
			if(hashList[index]== null) {
			throw new NonExistentKeyException("the object whit the key: "+ key + " non Exist" );
			}
			else if(hashList[index].getNext() == null && hashList[index].getValue().compareTo(value) == 0) {
				return hashList[index];
			}
			else if(hashList[index].getNext() != null && hashList[index].getValue().compareTo(value) == 0) {
				return hashList[index];
			}
			else {
				return hashList[index].getObjet(value);
			}
		}
	}

	public void deleteElement(K key) throws HashIsEmptyException, NonExistentKeyException {
		
		int index = hashFunction(key);
		
		if(isEmpty() == true) {
			throw new HashIsEmptyException("");
		}
		else {
			if(hashList[index] == null) {
				throw new NonExistentKeyException("");
			}
			else {
				if(hashList[index].getNext() == null) {
					hashList[index] = null;
					size--; 
				}
				else {
					hashList[index].removeLast(); 
				}
			}
		}	
	}
}