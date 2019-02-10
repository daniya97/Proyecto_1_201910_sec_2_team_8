/**
 * 
 */
package model.data_structures;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

/**
 * @author cohnan
 *
 */
class TestStack {
	/*
	 * Atributos 
	 */
	private Stack<String> pila;
	
	/*
	 * Escenarios
	 */
	// Pila vacia
	private void setUpEscenario0() {
		pila = new Stack<String>();
	}
	
	// Pila con 1 elemento
	private void setUpEscenario1() {
		pila = new Stack<String>();
		pila.push("Elemento 1");
	}
	
	// Pila con 2 elementos
	private void setUpEscenario2() {
		pila = new Stack<String>();
		pila.push("Elemento 1");
		pila.push("Elemento 2");
	}
	
	/*
	 * Metodos para Pruebas
	 */
	/**
	 * Prueba el constructor
	 */
	@Test
	void testStack() {
		setUpEscenario0();
		assertTrue(pila.isEmpty(), "La pila deberia estar vacia.");
		assertTrue(pila.size() == 0, "La pila deberia tener tamano 0.");
		assertTrue(pila.pop() == null, "La pila, al hacer pop, deberia retornal null");
	}
	
	/**
	 * Prueba el metodo iterator()
	 */
	@Test
	void testIterator() {
		setUpEscenario2();
		
		int i = 0;
		//for(String dato : pila.iterator()) {
		Iterator<String> iterador = pila.iterator();
		String dato;
		while (iterador.hasNext()) {
			dato = iterador.next();
			assertTrue(dato.equals("Elemento " + (2-i)), "El elemento siguiente no es identificado correctamente");
			i += 1;
		}
		assertTrue(i == 2, "El iterador deberia identificar y devolver 2 elementos");
	}
	
	/**
	 * Prueba el metodo isEmpty()
	 */
	@Test
	void testIsEmpty() {
		for (int i = 0; i <= 2; i++) {
			if 		(i == 0) {
				setUpEscenario0();
				assertTrue(pila.isEmpty(), "La pila deberia estar vacia.");
			}
			else if (i == 1) {
				setUpEscenario1();
				assertTrue(!pila.isEmpty(), "La pila deberia NO deberia vacia.");
			}
			else if (i == 2) {
				setUpEscenario2();
				assertTrue(!pila.isEmpty(), "La pila deberia NO deberia vacia.");
			}
			
		}
	}
	
	/**
	 * Prueba el metodo size
	 */
	@Test
	void testSize() {
		for (int i = 0; i <= 2; i++) {
			if 		(i == 0) {
				setUpEscenario0();
				assertTrue(pila.size() == 0, "La pila deberia estar vacia.");
			}
			else if (i == 1) {
				setUpEscenario1();
				assertTrue(pila.size() == 1, "La pila deberia deberia tener 1 elemento.");
			}
			else if (i == 2) {
				setUpEscenario2();
				assertTrue(pila.size() == 2, "La pila deberia deberia tener 2 elementos.");
			}
			
		}
	}
	
	/**
	 * Prueba el metodo push
	 */
	@Test
	void testPush() {
		for (int i = 0; i <= 1; i++) {
			if 		(i == 0) {
				setUpEscenario0();
				pila.push("Nuevo elemento");
				Iterator<String> it = pila.iterator();
				assertTrue(it.hasNext(), "Deberia tener elementos sobre los cuales iterar.");
				String dato = it.next();
				assertTrue(dato.equals("Nuevo elemento"), "El primero dato deberia ser el recien anadido");
				assertTrue(pila.size() == 1, "La pila deberia tener 1 elemento.");
			}
			else if (i == 1) {
				setUpEscenario2();
				pila.push("Nuevo elemento");
				Iterator<String> it = pila.iterator();
				assertTrue(it.hasNext(), "Deberia tener elementos sobre los cuales iterar.");
				String dato = it.next();
				assertTrue(dato.equals("Nuevo elemento"), "El primero dato deberia ser el recien anadido");				
				assertTrue(pila.size() == 3, "La pila deberia tener 3 elementos.");
			}
			
		}
	}
	
	/**
	 * Prueba el metodo pop
	 */
	@Test
	void testPop() {
		for (int i = 0; i <= 2; i++) {
			if 		(i == 0) {
				setUpEscenario0();
				pila.pop();
				assertTrue(pila.size() == 0, "La pila deberia seguir de tamano 0.");
			}
			else if (i == 1) {
				setUpEscenario1();
				String dato = pila.pop();
				assertTrue(pila.isEmpty(), "La pila deberia deberia estar vacia.");
				assertTrue(dato.equals("Elemento 1"), "El elemento eliminado no es el esperado.");
			}
			else if (i == 2) {
				setUpEscenario2();
				String dato = pila.pop();
				assertTrue(pila.size() == 1, "La pila deberia deberia tener 1 elementos.");
				assertTrue(dato.equals("Elemento 2"), "El elemento eliminado no es el esperado.");
			}
			
		}
	}
}
