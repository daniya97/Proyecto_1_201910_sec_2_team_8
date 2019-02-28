package model.util;
import java.lang.Math;
import java.util.Comparator;
import java.util.Random;

import model.data_structures.IQueue;
import model.data_structures.Queue;
import model.vo.VOMovingViolations;

public class Sort {
	
	/*
	 * **********************************************************************************************
	 * *********************************** SHELL SORT ***********************************************
	 * **********************************************************************************************
	 */
	/**
	 * Ordenar datos aplicando el algoritmo ShellSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */

	public static void ordenarShellSort( Comparable[ ] datos ) {
		// Esta es una implementacion hecha con el proposito de entender shellsort, que permite
		// el uso de diferentes secuencias con facilidad. La unica diferencia en uso de recursos
		// de esta implementacion es la creacion de la secuencia y su guardado en memoria, algo minimo.
		int[] secuencia = giveSequence(datos.length);
		
		for (int h : secuencia) hsort(datos, h);
	}
	
	private static void hsort(Comparable[] datos, int h) {
		int posOfInserting;
		for (int i = 0; i < h; i += 1) {
			// insertion-sort el h-subarray que empieza en i
			for (int j = i + h; j < datos.length ; j += h) {
				posOfInserting = j;
				while ((posOfInserting - h)>= 0 && less(datos[posOfInserting], datos[posOfInserting-h])) {
					exchange(datos, posOfInserting, posOfInserting-h);
					posOfInserting -= h;
				}
			}
		}
	}
	
	private static int[] giveSequence(int n) {
		// Usamos la secuencia 1, 4, 13, ...
		if (n < 4) return new int[] {1};
		// Esto asegura que para el h maximo, cada subarray que ea h-sorted tenga longitud al menos 3
		int k = (int)(Math.log(2*n + 1)/Math.log(3) - 1);
		int[] lista = new int[k];
		for (int i = 0; i < k; i++) lista[i] = (int)((Math.pow(3, k-i) - 1)/2);
		
		return lista;
	}
	

	
	/*
	 * **********************************************************************************************
	 */
	
	/*
	 * **********************************************************************************************
	 * *********************************** MERGE SORT ***********************************************
	 * **********************************************************************************************
	 */
	/**
	 * Ordenar datos aplicando el algoritmo MergeSort
	 * @param movingViolationsQueue - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public static void ordenarMergeSort(Queue<VOMovingViolations> datos, Comparator comparator ) {
		
		//Bottom - Up
		System.out.println(datos.size());
		int N = datos.size();
		//Se crea un arreglo auxiliar
		Queue auxiliar = new Queue<VOMovingViolations>();
		Queue auxiliar2 = new Queue<VOMovingViolations>();
		for (int i = 1; i < N; i = i+i) {
			for (int min = 0; min < N-i; min+=i+i) {
				merge(datos, auxiliar,auxiliar2, min, min+i-1, Math.min(min+i+i-1, N-1), comparator);
			}
		}

		// TODO implementar el algoritmo MergeSort
	}

	
	private static void merge(IQueue<VOMovingViolations> datos, IQueue<VOMovingViolations> auxiliar, IQueue<VOMovingViolations> auxiliar2, int min, int med, int max, Comparator comparator){
		//Se copia el arreglo
		for( int k = min; k<=med; k++){
			auxiliar.enqueue(datos.dequeue());
		}
		for( int k = med+1; k<=max; k++){
			auxiliar2.enqueue(datos.dequeue());
		}
		
		VOMovingViolations i = (VOMovingViolations) auxiliar.dequeue();
		VOMovingViolations j = (VOMovingViolations)auxiliar2.dequeue();
		
		for(int k = min; k<= max;k++){
			
			if (i == null) {
				datos.enqueue(j);
				j = auxiliar2.dequeue();
			}
			else if(j == null){
				datos.enqueue(i);
				i = auxiliar.dequeue();
			}
			else if(less(comparator,i,j)){
				datos.enqueue(i);
				i = auxiliar.dequeue();
			}
			else{
				datos.enqueue(j);
				j = auxiliar2.dequeue();
			}
		}
		
		
		
		
	}
	/*
	 * **********************************************************************************************
	 */
	
	/*
	 * **********************************************************************************************
	 * *********************************** QUICK SORT ***********************************************
	 * **********************************************************************************************
	 */
	/**
	 * Ordenar datos aplicando el algoritmo QuickSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public static void ordenarQuickSort( Comparable[ ] datos ) {
		// Desordenar
		
		StdRandom.shuffle(datos);
		ordenarQuickSort(datos, 0, datos.length-1);
	}
	
	private static void ordenarQuickSort( Comparable[ ] datos, int min, int max) {
		if (min >= max) return;
		
		Comparable ref = datos[min];
		
		// Partir array en dato de referencia, datos menores o iguales, y datos mayores
		int indLastLeq = min;
		int indFirstGr = max + 1;
		while (true) {
			// Encontrar la posicion actual donde termina la particion de numeros menores o iguales
			while (indLastLeq < max && datos[indLastLeq + 1].compareTo(ref) <= 0) indLastLeq += 1;
			if (indLastLeq + 1 == indFirstGr) break;
			
			// Encontrar la posicion actual donde empieza la particion de numeros mayores
			while (indFirstGr > (min+1) && datos[indFirstGr - 1].compareTo(ref) > 0) indFirstGr -= 1;
			if (indLastLeq + 1 == indFirstGr) break;
			
			// En caso de no cruzarse las dos partes del array, hacer el intercambio de los
			// elementos que interrumpen las 2 partes del array
			exchange(datos, indLastLeq + 1, indFirstGr - 1);
			indLastLeq += 1;
			indFirstGr -= 1;
		}
		// Dejar elemento de referencia en su posicion final
		exchange(datos, min, indLastLeq);
		
		// Ordenar las 2 partes restantes
		ordenarQuickSort(datos, min, indLastLeq - 1);
		ordenarQuickSort(datos, indFirstGr, max);
	}
	/*
	 * **********************************************************************************************
	 */
	
	/**
	 * Comparar 2 objetos usando la comparacion "natural" de su clase
	 * @param v primer objeto de comparacion
	 * @param w segundo objeto de comparacion
	 * @return true si v es menor que w usando el metodo compareTo. false en caso contrario.
	 */
	private static boolean less(Comparator c, Object v, Object w)
	{
		return c.compare(v, w) < 0;
	}
	
	/**
	 * Intercambiar los datos de las posicion i y j
	 * @param datos contenedor de datos
	 * @param i posicion del 1er elemento a intercambiar
	 * @param j posicion del 2o elemento a intercambiar
	 */
	private static void exchange( Comparable[] datos, int i, int j)
	{
		Comparable temp = datos[i];
		datos[i] = datos[j];
		datos[j] = temp;
	}
	
	public static boolean isSorted(Comparable[] datos) {
		for (int i = 0; i < datos.length-1; i++)
			if (less(datos[i+1], datos[i])) return false;
		return true;
	}

}
