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
		fail();
	}
	
	/**
	 * Prueba el metodo push
	 */
	@Test
	void testPush() {
		fail();
	}
	
	/**
	 * Prueba el metodo pop
	 */
	@Test
	void testPop() {
		fail();
	}
}
