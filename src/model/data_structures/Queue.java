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
		return 0;
	}

	@Override
	public void enqueue(T t) {
		Nodo<T> nuevo = new Nodo<>(t);
		nuevo.cambiarSiguiente(first);
		first = nuevo;
		size++;
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		if (size == 0) return null;
		Nodo<T> penultimoViejo = first;
		
		while(penultimoViejo.darSiguiente().darSiguiente() != null) {
			penultimoViejo = penultimoViejo.darSiguiente();
		}
		
		Nodo<T> ultimoViejo = penultimoViejo.darSiguiente();
		penultimoViejo.cambiarSiguiente(null);
		size--;
		return ultimoViejo.darObjeto();
	}
}
