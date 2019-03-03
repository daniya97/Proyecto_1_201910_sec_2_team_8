/**
 * 
 */
package model.data_structures;

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
	private final int numeroEscenarios = 10;
	private final int tamanoMax = 100;

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
		for (int n = 0; n < numeroEscenarios; n++) {
			setUpEscenario(n, 0);
			assertTrue("El arreglo deberia tener tamano " + n, arreglo.darTamano() == n);
			setUpEscenario(n, tamanoMax);
			assertTrue("El arreglo deberia tener tamano " + n, arreglo.darTamano() == n);
		}
	}

	/**
	 * Prueba el metodo iterator()
	 */
	public void testIterator() {
		int n = 20;
		setUpEscenario(n, 0);

		int i = 0;
		for(String dato: arreglo) {
			assertTrue("Escenario: " +  n + ". El elemento siguiente no es identificado correctamente", dato.equals("Elemento " + i));
			i += 1;
		}
		assertTrue("El iterador deberia identificar y devolver " + n + " elementos", i == n);
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
		int nAgregados = 3;
		
		for (int n = 0; n <= numeroEscenarios; n++) {
			setUpEscenario(n, 0);
			for (int i = 0; i < nAgregados; i++) arreglo.agregar("Nuevo elemento " + i);
			assertTrue("Escenario: " + n + ". El arreglo deberia tener " + (n + nAgregados) + " elementos.", arreglo.darTamano() == (n + nAgregados));
			
			//assertTrue("Deberia tener elementos sobre los cuales iterar.", it.hasNext());
			
			int i = 0;
			for (String dato : arreglo) {
				if (i < n) {
					assertTrue("Escenario: " + n + ". El " + i + "-esimo elemento deberia ser: Elemento " + i
							+ ", pero se obtuvo " + dato, arreglo.darObjeto(i).equals("Elemento " + i));
					i++;
				}
				else if (n <= i &&  i < n + nAgregados) {
					assertTrue("Escenario: " + n + ". El dato esperado era: " + "Nuevo elemento " + (i-n)
							+ ", pero se obtuvo " + dato, dato.equals("Nuevo elemento " + (i-n)));
					i++;
				}
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
