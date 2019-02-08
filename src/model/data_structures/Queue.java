/**
 * 
 */
package model.data_structures;

import java.util.Iterator;

/**
 * @author cohnan
 *
 */
public class Queue<T> implements IQueue<T> {
	/*
	 * Variables
	 */
	Nodo<T> first;
	int size;
	
	/*
	 * Constructor
	 */
	public Queue() {
		size = 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void enqueue(T t) {
		// TODO Auto-generated method stub
		
		size++;
		
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		
		size--;
		return null;
	}
}
