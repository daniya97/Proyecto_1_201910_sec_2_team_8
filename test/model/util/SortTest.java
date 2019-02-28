package model.util;

import java.util.Arrays;

import junit.framework.TestCase;

public class SortTest extends TestCase{
	/*
	 * Constantes
	 */
	// Tamano de la muestra
	private final int N = 1000000;
	// Numero de escenarios de prueba
	private final int totalEscenarios = 3;
	
	/*
	 * Atributos
	 */
	// Muestra de datos a ordenar
	Comparable[] datos;
	
	/**
	 * Establece los escenarios de prueba
	 * @param n Numero de escenario de prueba a configurar
	 */
	private void setUpEscenario(int n) {
		if (n >= totalEscenarios) throw new IllegalArgumentException("No hay tantos escenarios");
		datos = new Comparable[N];
		switch(n) {
		// Escenario: muestra ya ordenada
		case 0:
			for(int i = 0; i < N; i++) datos[i] = (Comparable) i;
			break;
		// Escenarios: muestra totalmente desordenada
		case 1:
			for(int i = 0; i < N; i++) datos[i] = (Comparable) (N-1-i);
		// Escenario: muestra aleatoria
		case 2:
			for(int i = 0; i < N; i++) datos[i] = (Comparable) Math.random();
		}
	}
	
	/**
	 * Prueba de ShellSort
	 */
	public void testShellSort() {
		Comparable[] datosOrdenados;
				
		for (int n = 0; n < totalEscenarios; n++) {
			setUpEscenario(n);
				
			datosOrdenados = Arrays.copyOf(datos, datos.length);
			Arrays.sort(datosOrdenados);
			Sort.ordenarShellSort(datos);
			
			for (int i = 0; i < datos.length; i++) {
				assertTrue(datos[i].equals(datosOrdenados[i]));
			}
		}
	}
	
	/**
	 * Prueba de MergeSort
	 */
	public void testMergeSort() {
		Comparable[] datosOrdenados;
				
		for (int n = 0; n < totalEscenarios; n++) {
			setUpEscenario(n);
				
			datosOrdenados = Arrays.copyOf(datos, datos.length);
			Arrays.sort(datosOrdenados);
			Sort.ordenarMergeSort(datos);
			
			for (int i = 0; i < datos.length; i++) {
				assertTrue(datos[i].equals(datosOrdenados[i]));
			}
		}
	}
	
	/**
	 * Prueba de QuickSort
	 */
	public void testQuickSort() {
		Comparable[] datosOrdenados;
				
		for (int n = 0; n < totalEscenarios; n++) {
			setUpEscenario(n);
				
			datosOrdenados = Arrays.copyOf(datos, datos.length);
			Arrays.sort(datosOrdenados);
			Sort.ordenarQuickSort(datos);
			
			for (int i = 0; i < datos.length; i++) {
				assertTrue(datos[i].equals(datosOrdenados[i]));
			}
		}
	}
}
