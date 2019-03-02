package model.data_structures;

//import com.opencsv.CSVReader;

public class Node<T> {
	
	private T dato;
	private Node<T> siguiente;
	private Node<T> anterior;
	
	public Node (T dato){
		this.dato = dato;
	}
	
	public T darDato() {
		return dato;
	}
	
	public Node<T> anterior(){
		return anterior;
	}
	
	public Node<T> siguiente(){
		return siguiente;
	}
	
	// Hace los dos links al tiempo, sin entrar en un loop. Implica que cada nodo solo puede pertenecer 
	// a una lista.
	public void asignarSiguiente(Node<T> siguiente) {
		if (this.siguiente == siguiente) return;
		
		Node<T> siguienteAntiguo = this.siguiente; //TODO problematic?
		if (siguienteAntiguo != null)
			siguienteAntiguo.quitarAnterior();
		
		this.siguiente = siguiente;
		
		if (siguiente != null)
			siguiente.asignarAnterior(this);
	}
	
	// Hace los dos links al tiempo, sin entrar en un loop. Implica que cada nodo solo puede pertenecer 
	// a una lista.	
	public void asignarAnterior(Node<T> anterior) {
		if (this.anterior == anterior) return;
		
		Node<T> anteriorAntiguo = this.anterior; //TODO problematic?
		if (anteriorAntiguo != null)
			anteriorAntiguo.quitarSiguiente();
		
		this.anterior = anterior;
		
		if (anterior != null)
			anterior.asignarSiguiente(this);
	}
	
	public void quitarAnterior() {
		Node<T> anteriorAntiguo = this.anterior;
		this.anterior = null;
		
		if (anteriorAntiguo != null)
			anteriorAntiguo.asignarSiguiente(null);
	}
	
	public void quitarSiguiente() {
		Node<T> siguienteAntiguo = this.siguiente;
		this.siguiente = null;
		
		if (siguienteAntiguo != null)
			siguienteAntiguo.asignarAnterior(null);
	}
}
