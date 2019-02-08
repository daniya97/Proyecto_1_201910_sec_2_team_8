package model.data_structures;
/**
 * Representación de un contenedor de información
 */
public class Nodo<K> {
	

	
	/**
	 * Lista encadenada sencilla cada nodo conoce el siguiente
	 */
	private Nodo<K> siguiente;
	
	/**
	 * Objeto (información) que almacena el nodo
	 */
	private K objeto;
	
	
	/**
	 * Constructor
	 */
	public Nodo(K pObjeto){
		objeto = pObjeto;
		siguiente = null;
	}
	
	
	/**
	 * Método para saber el siguiente nodo
	 */
	public Nodo<K> darSiguiente(){
		return siguiente;
	}
	
	/**
	 * Método para cambiar el siguiente nodo del nodo actual
	 */
	public void cambiarSiguiente(Nodo<K> pNuevoSiguiente){
		siguiente = pNuevoSiguiente;
	}
	
	/**
	 * Retorna el objeto que alamcena el nodo
	 */
	public K darObjeto(){
		return objeto;
	}
	

	
}
