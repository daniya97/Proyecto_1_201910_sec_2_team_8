package model.data_structures;

/**
 * Abstract Data Type for a linked list of generic objects
 * This ADT should contain the basic operations to manage a list
 * add, addAtEnd, AddAtK, getElement, getCurrentElement, getSize, delete, deleteAtK
 * next, previous
 * @param <T>
 */
public interface ILinkedList<T> extends Iterable<T> {

	Integer darTamano();
	
	void anadir(T elemento);
	
	void eliminar(T dato);
	
	T recuperarEnPos(int i);
	
	void reiniciarRecorrido( );
	
	T darActual( );
	
	T avanzar( );
	
	T retroceder( );
	
	boolean tieneSiguiente();
	
	boolean tieneAnterior();

}
