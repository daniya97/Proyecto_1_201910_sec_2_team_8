package model.data_structures;

import java.util.Iterator;

public class Stack <T> implements IStack<T> { // Sebastian: creo que es necesario agregar <I> luego de Stack. Edit: al corregir push, si se vuelve necesario

	
	private Nodo<T> first; 	// Cabeza de la pila
	private int size;		// Tamano de la pila
	
	
	public Stack(){
		size = 0;
		first = null;
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
		
		if (first == null){ 
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public void push(T t) {  // Sebastian: como estaba era posible agregar cualquier Object, a pesar de que solo queremos agregar objetos de tipo T
		
		Nodo<T> nuevo = new Nodo<>(t);
		
		
		if(size == 0){
			first = nuevo;
		}
		else
		{
			nuevo.cambiarSiguiente(first);
			first = nuevo;
		}
		
		size ++;
		
	}

	@Override
	public T pop() {
		
		if(size == 0){
			return null;
		}
		else
		{
			size -= 1;
			Nodo<T> auxiliar = first;
			first = first.darSiguiente();
			return auxiliar.darObjeto();
		}
	}
	

}
