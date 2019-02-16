package model.data_structures;

import java.util.Comparator;

public interface IStack<T> extends Iterable<T>{
	
	/**
	 * Retorna true si la Pila esta vacia
	 * @return true si la Pila esta vacia, false de lo contrario
	 */
	public boolean isEmpty();
	
	/**
	 * Retorna el numero de elementos contenidos
	 * @return el numero de elemntos contenidos
	 */
	public int size();
	
	/**
	 * Inserta un nuevo elemento en la Pila
	 * @param t el nuevo elemento que se va ha agregar
	 */
	public void push(T t);
	
	/**
	 * Quita y retorna el elemento agregado más recientemente
	 * @return el elemento agregado más recientemente
	 */
	public T pop();	
	
	public void sort(Stack<T> pila, Comparator<T> comparador, boolean descendente);
}
