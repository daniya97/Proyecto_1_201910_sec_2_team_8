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
	private Nodo<T> first;
	private int size;
	
	/*
	 * Constructor
	 */
	public Queue() {
		size = 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private Nodo<T> current = first;
			
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				T dato = current.darObjeto();
				current = current.darSiguiente();
				return dato;
			}
		};
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void enqueue(T t) {
		
		
		Nodo<T> nuevo = new Nodo<>(t);
		//Daniel: Tener en cuenta el caso en el que la lista este vac�a
		if(size == 0){
			first = nuevo;
		}
		else{
			nuevo.cambiarSiguiente(first);
			first = nuevo;
		}
		
		size=size +1;

	}

	@Override
	public T dequeue() {
		
		
		if (size == 0) return null;
		if(size == 1){
			size --;
			Nodo<T> auxiliar = first;
			first = null;
			return auxiliar.darObjeto();
		}
		
		//Daniel: Toca considerar el caso en el que s�lo haya un elemento
		
		
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
