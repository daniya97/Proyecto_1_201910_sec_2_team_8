package model.data_structures;

import java.util.Iterator;

public class Stack <T> implements IStack {

	
	private Nodo<T> first;
	int size;
	
	
	public Stack(){
		size = 0;
	}
	
	
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		if (first.equals(null)){
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
	public void push(Object t) {
		
		Nodo<T> nuevo = new Nodo(t);
		
		
		if(size == 0){
			first = nuevo;
		}
		else
		{
			nuevo.cambiarSiguiente(first);
			first = nuevo;
		}
		
		size ++;
		// TODO Auto-generated method stub
		
	}

	@Override
	public T pop() {
		
		if(size == 0){
			return null;
		}
		else
		{
			Nodo<T> auxiliar = first;
			first = first.darSiguiente();
			return auxiliar.darObjeto();
		}
		
		
		
		// TODO Auto-generated method stub
	}
	

}
