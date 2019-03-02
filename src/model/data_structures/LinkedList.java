package model.data_structures;

import java.util.Iterator;

public class LinkedList<T> implements ILinkedList<T> {

	private Node<T> primero;
	private Node<T> ultimo;
	private Node<T> actual;
	private Integer tamano;
	
	public LinkedList () { //TODO puedo empezar sin informacion?
		primero = null;
		ultimo = null;
		actual = primero;
		tamano = 0;
	}
	
	@Override
	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return tieneSiguiente();
            }

            @Override
            public T next() {
                return avanzar();
            }
        };
        return it;
		
	}

	@Override
	public Integer darTamano() {
		return tamano;
	}


	@Override
	public void anadir(T elemento) {
		if (tamano == 0) {
			primero = new Node<T>(elemento);
			tamano = 1;
			ultimo = primero;
			actual = primero;
			return;
		}
		
		Node<T> ultimoViejo = ultimo;
		ultimo = new Node<T>(elemento);
		ultimoViejo.asignarSiguiente(ultimo);
		//System.out.println("Se ha annadido el nodo con elemento: " + ultimo.darDato()); // TEST
		//System.out.println("El dato que anteriormente era el ultimo es ahora: " + ultimoViejo.darDato()); // TEST
		// ultimo.asignarAnterior(ultimoViejo);
		
		tamano++;
	}


	@Override
	// Debe ser posible implementar este metodo con eliminarEn, pero este lo implemente primero
	public void eliminar(T dato) {
		if (tamano == 0) return;
		
		Node<T> nodo = primero;
		
		for (int i = 0; i < tamano; i++) { // El dato puede que este mas de una vez
			if (nodo.darDato().equals(dato)) { //TODO No se si solo queremos borrar cuando el dato sea el mismo objeto
				if (tamano == 1) {
					primero = null;
					ultimo = null;
					tamano = 0;
					return;
				} // Entonces el tamano es al menos 2
				else if (nodo == primero) {
					if (actual == primero) actual = primero.siguiente();
					primero = primero.siguiente();
					primero.quitarAnterior();
					System.out.print("Este es el caso, nodo == primero");
				}
				else if(nodo == ultimo) {
					if (actual == ultimo) actual = ultimo.anterior();
					ultimo = ultimo.anterior();
					ultimo.quitarSiguiente();
				} else { // Aqui se llega solo si el tamano es al menos 3 y el nodo a eliminar no es un extremo
					Node<T> nodoAnt = nodo.anterior();
					if (actual == nodo) actual = nodo.siguiente();
					nodoAnt.asignarSiguiente(nodo.siguiente()); //TODO problematic?
					nodo = nodoAnt; //TODO problematic?
				}
				tamano--; //TODO Check if this works with the for loop
			}
			
			nodo = nodo.siguiente();
		}
	}


	@Override
	public T recuperarEnPos(int i) {
		if (i >= tamano || i < 0) throw new IllegalArgumentException("No es posible encontrar esa posicion");
		
		Node<T> nodo = primero;
		for (int k = 0; k < i; k++) {
			nodo = nodo.siguiente();
		}
		
		return nodo.darDato();
	}


	@Override
	public void reiniciarRecorrido() {
		actual = primero;
	}


	@Override
	public T darActual() {
		if (actual == null) throw new UnsupportedOperationException("El elemento no existe.");
		return actual.darDato();
	}


	@Override
	public T avanzar() {
		if (!tieneSiguiente()) throw new UnsupportedOperationException("No hay elemento al cual avanzar");
		actual = actual.siguiente();
		return actual.darDato();
	}


	@Override
	public T retroceder() {
		if (!tieneAnterior()) throw new UnsupportedOperationException("No hay elemento al cual retroceder");
		actual = actual.anterior();
		return actual.darDato();
	}
	
	@Override
	public boolean tieneSiguiente() {
		return actual.siguiente() != null;
	}
	
	@Override
	public boolean tieneAnterior() {
		return actual.anterior() != null;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	

}
