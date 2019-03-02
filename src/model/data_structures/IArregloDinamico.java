package model.data_structures;

public interface IArregloDinamico<T extends Comparable<T>> extends Iterable<T>{

	/**
	 * Retornar el numero de elementos en el arreglo
	 * @return
	 */
	int darTamano( );
	
	/**
	 * Retornar el elemento en la posicion i
	 * @param i posicion de consulta
	 * @return elemento de consulta. null si no hay elemento en posicion.
	 */
	T darObjeto( int i );

	/**
	 * Agregar un dato de forma compacta (en la primera casilla disponible) 
	 * Caso Especial: Si el arreglo esta lleno debe aumentarse su capacidad, agregar el nuevo dato y deben quedar multiples casillas disponibles para futuros nuevos datos.
	 * @param dato nuevo elemento
	 * @throws IllegalArgumentException si el dato es nulo
	 */
	public void agregar( T dato );
	
	/**
	 * Cambiar el dato en la posicion dada 
	 * @param i posicion del arreglo a modificar
	 */
	public void cambiarEnPos( int i , T dato);
		
	/**
	 * Buscar un dato en el arreglo.
	 * @param dato Objeto de busqueda en el arreglo
	 * @return elemento encontrado en el arreglo (si existe). -1 si no se encontro el dato.
	 */
	int buscar(T dato);
	
	/**
	 * Eliminar la primera instancia de un dato en el arreglo.
	 * Los datos restantes quedan "compactos" desde la posicion 0.
	 * @param dato Objeto de eliminacion en el arreglo
	 * @return dato eliminado. Null si no se encontro el elemento
	 */
	T eliminar( T dato );
	
	/**
	 * Eliminar el dato en la posicion i del arreglo.
	 * Los datos restantes deben quedar "compactos" desde la posicion 0.
	 * @param i Posicion del dato
	 * @return dato eliminado
	 */
	T eliminarEnPos( int i );
}