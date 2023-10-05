package util;

import customExceptions.HashIsEmptyException;
import customExceptions.NonExistentKeyException;

public class HashTable<K extends Comparable<K>,V extends Comparable<V>> {

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

	public HashNode<K, V> searchElement(K key) throws HashIsEmptyException, NonExistentKeyException {
		int index = hashFunction(key);
		if(isEmpty()){
			throw new HashIsEmptyException("The hash table is empty");
		}
		else{
			if(hashList[index] == null){
				throw new NonExistentKeyException("The object with the key provided doesn't exist");
			}
			else if(hashList[index].getKey().compareTo(key) == 0){
				return hashList[index];
			}
			else{
				return hashList[index].getObject(key);
			}
		}
	}

	public void deleteElement(K key) throws HashIsEmptyException, NonExistentKeyException {
		int index = hashFunction(key);
		if(isEmpty() == true) {
			throw new HashIsEmptyException("The hash table is empty");
		}
		else{
			if(hashList[index] == null){
				throw new NonExistentKeyException("The object with the key provided doesn't exist");
			}
			else{
				if(hashList[index].getNext() == null) {
					hashList[index] = null;
					size--; 
				}
				else{
					hashList[index].removeElement(key);
				}
			}
		}	
	}

	public String print(){

		String msg = "";
		for(int i = 0; i < hashList.length; i++){
			if(hashList[i] != null){
				if(hashList[i].getNext() == null){
					if(hashList[i].getStatus().equals(HashNodeStatus.ACTIVE)){
						msg += "Key: " + hashList[i].getKey() + ", "+ hashList[i].getValue().toString();
					}
				}
				else{
					msg += hashList[i].print();
				}
			}
		}
		return msg;
	}
}