/**
 * 
 */
package data_structures;

import static org.junit.Assert.*;

import java.util.Iterator;


import junit.framework.TestCase;
import model.data_structures.ArregloDinamico;

/**
 * @author cohnan daniya97
 *
 */
public class ArregloDinamicoTest extends TestCase{
	/*
	 * Atributos 
	 */
	private ArregloDinamico<String> arreglo;

	/*
	 * Escenarios
	 */
	// Arreglo con n elementos
	private void setUpEscenario(int n, int max) {
		if (max == 0) 
			arreglo = new ArregloDinamico<String>();
		else
			arreglo = new ArregloDinamico<String>(max);
		for (int i = 0; i < n; i++) {
			arreglo.agregar("Elemento " + i);
		}
	}

	/*
	 * Metodos para Pruebas
	 */
	/**
	 * Prueba el constructor
	 */
	public void testArregloDinamico() {
		for (int n = 0; n < 10; n++) {
			setUpEscenario(n, 0);
			assertTrue("El arreglo deberia tener tamano " + n, arreglo.darTamano() == n);
			setUpEscenario(n, 10);
			assertTrue("El arreglo deberia tener tamano " + n, arreglo.darTamano() == n);
		}
	}

	/**
	 * Prueba el metodo iterator()
	 */
	public void testIterator() {
		setUpEscenario(20, 0);

		int i = 0;
		for(String dato: arreglo) {
			assertTrue("El elemento siguiente no es identificado correctamente", dato.equals("Elemento " + i));
			i += 1;
		}
		assertTrue("El iterador deberia identificar y devolver " + 20 + " elementos", i == 20);
	}

	/**
	 * Prueba el metodo darTamano()
	 */
	public void testSize() {
		for (int i = 0; i <= 10; i++) {
				setUpEscenario(i, 0);
				assertTrue("El arreglo deberia ser " + i, arreglo.darTamano() == i);
		}
	}

	/**
	 * Prueba el metodo agregar
	 */
	public void testAgregar() {
		for (int n = 0; n <= 1; n++) {
			if 		(n == 0) {
				setUpEscenario(0, 0);
				arreglo.agregar("Nuevo elemento");
				Iterator<String> it = arreglo.iterator();
				assertTrue("Deberia tener elementos sobre los cuales iterar.", it.hasNext());
				String dato = it.next();
				assertTrue("El primero dato deberia ser el recien anadido", dato.equals("Nuevo elemento"));
				assertTrue("NO deberia tener elementos sobre los cuales iterar.", !it.hasNext());
			}
			else if (n == 5) {
				setUpEscenario(5, 0);
				arreglo.agregar("Nuevo elemento");
				assertTrue("El arreglo deberia tener " + (n+1) + " elementos.", arreglo.darTamano() == (n+1));
				
				Iterator<String> it = arreglo.iterator();				
				String dato = it.next();
				for (int j = 0; j < n; j++) {
					assertTrue("El " + j + "-esimo elemento deberia ser: Elemento " + j, arreglo.darObjeto(j).equals("Elemento " + j));
					dato = it.next();
				}
				assertTrue("El ultimo dato deberia ser el recien anadido", dato.equals("Nuevo elemento"));				
				
			}

		}
	}

	/**
	 * Prueba el metodo pop
	 */
	public void testEliminarEnPos() {
		for (int n = 0; n <= 8; n++) {
			setUpEscenario(n, 0);
			String dato;
			int[] posiciones = new int[] {7, 3, 0}; // Orden descendente necesario
			int i;
			int eliminados = 0;
			for (int k = 0; k < posiciones.length; k++) {
				i = posiciones[k];
				if (i < n) {
					dato = arreglo.eliminarEnPos(i);
					eliminados += 1;
					assertTrue("El dato no es el correcto.", dato.equals("Elemento " + (i)));
					assertTrue("El tamano del arreglo deberia ser " + (n-eliminados), arreglo.darTamano() == (n-eliminados));
					if (i != n-1) {
						assertTrue("El elemento en la posicion " + i + " deberia ser Elemento " + (i+1), arreglo.darObjeto(i).equals("Elemento " + (i+1)));
					}
				}
			}
		}
	}
}
