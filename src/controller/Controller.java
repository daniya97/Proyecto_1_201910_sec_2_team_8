package controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

import com.opencsv.CSVReader;


import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.Queue;
import model.vo.VOMovingViolations;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;

	/**
	 * Cola donde se van a cargar los datos de los archivos
	 */
	private IQueue<VOMovingViolations> movingViolationsQueue;
	private int cuatrimestreCargado = -1;

	/**
	 * Pila donde se van a cargar los datos de los archivos
	 */
	//private IStack<VOMovingViolations> movingViolationsStack;


	public Controller() {
		view = new MovingViolationsManagerView();

		// Inicializar la pila y la cola
		movingViolationsQueue = null;
		//movingViolationsStack = null;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin = false;

		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 1:
				view.printMensage("Elija un cuatrisemestre (1: Enero, Febrero, Marzo, Abril - 2: Mayo, Junio, Julio, Agosto- 3: Septiembre, Octubre, Noviembre y Diciembre)");
				int s = sc.nextInt();
				this.elegirCuatriSemestre(s);
				break;

			case 2:
				IStack<VOMovingViolations> verificacion = this.verificarObjectIDRepetidos();
				if(verificacion.size() == 0)
				{
					view.printMensage("No hay elementos con OBJECTID repetido");
				}
				else
				{
					view.printVerificacion(verificacion);
				}
				
				break;

			case 3:
				view.printMensage("Ingrese el número de infracciones a buscar");
				int n = sc.nextInt();
				break;

			case 4:	
				fin=true;
				sc.close();
				break;
			}
		}
	}

	public void elegirCuatriSemestre(int n)
	{
		if(n == 1)
		{
			loadMovingViolations(new String[] {"Moving_Violations_Issued_in_January_2018.csv", 
					    	     "Moving_Violations_Issued_in_February_2018.csv",
					    	     "Moving_Violations_Issued_in_March_2018.csv",
					    	     "Moving_Violations_Issued_in_April_2018.csv"});
			cuatrimestreCargado = 1;
		}
		else if(n == 2)
		{
			loadMovingViolations(new String[] {"Moving_Violations_Issued_in_May_2018.csv", 
								 "Moving_Violations_Issued_in_June_2018.csv",
								 "Moving_Violations_Issued_in_July_2018.csv",
								 "Moving_Violations_Issued_in_August_2018.csv"});
			cuatrimestreCargado = 2;
		}
		else if(n == 3){
			loadMovingViolations(new String[] {"Moving_Violations_Issued_in_September_2018.csv", 
		    	     			 "Moving_Violations_Issued_in_October_2018.csv",
		    	     			 "Moving_Violations_Issued_in_November_2018.csv",
		    	     			 "Moving_Violations_Issued_in_December_2018.csv"});
			cuatrimestreCargado = 3;
		}
		else
		{
			throw new IllegalArgumentException("No existe ese cuatrimestre en un annio.");
		}	
	}

	/**
	 * Carga la informacion sobre infracciones de los archivos a una pila y una cola ordenadas por fecha.
	 */
	public void loadMovingViolations(String[] movingViolationsFilePaths){
		CSVReader reader = null;
		// Contadores de infracciones en cada mes del cuatrimestre
		int[] contadores = new int[movingViolationsFilePaths.length];
		int fileCounter = 0;
		try {
			movingViolationsQueue = new Queue<VOMovingViolations>();
			
			for (String filePath : movingViolationsFilePaths) {
				reader = new CSVReader(new FileReader("data/"+filePath));
				String[] headers = reader.readNext();
				// Array con la posicion de cada header conocido (e.g. LOCATION) dentro del header del archivo
				// Esto permite que el archivo tenga otras columnas y se encuentren en otro orden y aun asi
				// el programa funcione.
				int[] posiciones = new int[VOMovingViolations.EXPECTEDHEADERS.length];
				for (int i = 0; i < VOMovingViolations.EXPECTEDHEADERS.length; i++) {
					posiciones[i] = buscarArray(headers, VOMovingViolations.EXPECTEDHEADERS[i]);
				}
				// Carga de las infracciones a la cola, teniendo en cuenta el formato del archivo
				contadores[fileCounter] = 0;
			    for (String[] row : reader) {
			    	movingViolationsQueue.enqueue(new VOMovingViolations(posiciones, row));
			    	contadores[fileCounter] += 1;
			    }
			    fileCounter += 1;
			}
			
			int suma = 0;
			for (int contador : contadores) suma += contador;
			System.out.println("  ----------Informaci�n Sobre la Carga------------------  ");
			for (int i = 0; i < contadores.length; i++) {
				System.out.println("Infracciones Mes " + (i+1)+": " + contadores[i]);
			}
			System.out.println("Total Infracciones Cuatrisemetre: " + suma);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
		
	private int buscarArray(String[] array, String string) {
		int i = 0;
		
		while (i < array.length) {
			if (array[i].equals(string)) return i;
			i += 1;
		}
		return -1;
	}

	public IStack <VOMovingViolations> verificarObjectIDRepetidos(){

		return null;
	}
	
	
	public IQueue<VOMovingViolations> consultarInfracciones(int mes1, int dia1, int hora1,int min1, int mes2, int dia2, int hora2, int min2){
		// TODO esto parece que tendra un Off By One error
		Queue<VOMovingViolations> respuesta = new Queue<VOMovingViolations>();
		Iterator<VOMovingViolations> iterador = movingViolationsQueue.iterator();
		VOMovingViolations actual = iterador.next();

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar Fechaobjeto = Calendar.getInstance();
		
		c1.set(2018, mes1, dia1, hora1, min1);
		c1.set(2018, mes2, dia2, hora2, min2);
		
		while(iterador.hasNext()){
			
			Fechaobjeto = actual.getTicketIssueDate();
			
			if(Fechaobjeto.compareTo(c1)>0 && Fechaobjeto.compareTo(c2)<0){
				respuesta.enqueue(actual);
			}
			
			actual = iterador.next();
		}
		
		return respuesta;
		
	}
	
	public void fineAmtPromedio (String violationCode){
		// TODO esto parece que tendra un Off By One error
		Iterator<VOMovingViolations> iterador = movingViolationsQueue.iterator();
		VOMovingViolations actual = iterador.next();
		int sumaAccidente = 0;
		int sumaNoAccidente = 0;
		int contadorAccidente = 0;
		int contadorNoAccidente = 0;
		double respuestaAccidente = 0;
		double respuestaNoAccidente = 0;
		
		
		while(iterador.hasNext()){
			
			if(actual.getAccidentIndicator()){
				sumaAccidente+=actual.getFineAmount();
				contadorAccidente++;
			}
			else
			{
				sumaNoAccidente+=actual.getFineAmount();
				contadorNoAccidente++;
			}
			actual = iterador.next();
		}
		
		respuestaAccidente = sumaAccidente/contadorAccidente;
		respuestaNoAccidente = sumaNoAccidente/contadorNoAccidente;
		
		System.out.println("----------Promedio Fine Amount ------------");
		System.out.println("Promedio  Fine Amount Accidentes: " + respuestaAccidente);
		System.out.println("Promedio  Fine Amount No Accidentes: " + respuestaNoAccidente);
	}	
}