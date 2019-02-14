package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

import com.opencsv.CSVReader;


import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;

	/**
	 * Cola donde se van a cargar los datos de los archivos
	 */
	private IQueue<VOMovingViolations> movingViolationsQueue;

	/**
	 * Pila donde se van a cargar los datos de los archivos
	 */
	private IStack<VOMovingViolations> movingViolationsStack;


	public Controller() {
		view = new MovingViolationsManagerView();

		//TODO, inicializar la pila y la cola
		movingViolationsQueue = null;
		movingViolationsStack = null;
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
		try {

			CSVReader readerJan = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_January_2018.csv"));
			CSVReader readerFeb = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_February_2018.csv"));
			CSVReader readerMar = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_March_2018.csv"));
			CSVReader readerApr = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_April_2018.csv"));
			CSVReader readerMay = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_May_2018.csv"));
			CSVReader readerJun = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_June_2018.csv"));
			CSVReader readerJul = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_July_2018.csv"));
			CSVReader readerAgu = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_August_2018.csv"));
			CSVReader readerSep = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_September_2018.csv"));
			CSVReader readerOct = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_October_2018.csv"));
			CSVReader readerNov = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_November_2018.csv"));
			CSVReader readerDec = new CSVReader(new FileReader("."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_December_2018.csv"));


			if(n == 1)
			{
				loadMovingViolations(readerJan, readerFeb, readerMar, readerApr);
			}
			else if(n == 2)
			{
				loadMovingViolations(readerMay, readerJun, readerJul, readerAgu);
			}
			else if(n == 3){

				loadMovingViolations(readerSep,readerOct,readerNov,readerDec);
			}
			else
			{
				//handle
			}




		} catch (FileNotFoundException e) {




			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	/**
	 * Carga la informacion sobre infracciones de los archivos a una pila y una cola ordenadas por fecha.
	 */
	public void loadMovingViolations(CSVReader reader1, CSVReader reader2, CSVReader reader3, CSVReader reader4) {


		try {

			movingViolationsStack = new Stack<VOMovingViolations>();
			movingViolationsQueue = new Queue<VOMovingViolations>();

			VOMovingViolations infraccion;

			boolean primeraFila = true;
			boolean primeraFila2 = true;
			boolean primeraFila3 = true;
			boolean primeraFila4 = true;
			
			int contador1 = 0;
			int contador2 = 0;
			int contador3 = 0;
			int contador4 = 0;
			int suma = 0;

			for (String[] row : reader1) {

				if(primeraFila){
					primeraFila = false;
				}
				else{
					infraccion = new VOMovingViolations(row);
					movingViolationsQueue.enqueue(infraccion);
					movingViolationsStack.push(infraccion);
					contador1++;
				}
			}
			for (String[] row : reader2) {

				if(primeraFila2){
					primeraFila2 = false;
				}
				else{
					infraccion = new VOMovingViolations(row);
					movingViolationsQueue.enqueue(infraccion);
					movingViolationsStack.push(infraccion);
					contador2++;
				}
			}
			for (String[] row : reader3) {

				if(primeraFila3){
					primeraFila3 = false;
				}
				else{
					infraccion = new VOMovingViolations(row);
					movingViolationsQueue.enqueue(infraccion);
					movingViolationsStack.push(infraccion);
					contador3++;
				}
			}
			for (String[] row : reader4) {

				if(primeraFila4){
					primeraFila4 = false;
				}
				else{
					infraccion = new VOMovingViolations(row);
					movingViolationsQueue.enqueue(infraccion);
					movingViolationsStack.push(infraccion);
					contador4++;
				}
			}

			suma = contador1+ contador2+contador3+contador4;
			
			System.out.println("  ----------Informaci�n Sobre la Carga------------------  ");
			System.out.println("Infracciones Mes 1: "+contador1);
			System.out.println("Infracciones Mes 2: " + contador2);
			System.out.println("Infracciones Mes 3: " + contador3);
			System.out.println("Infracciones Mes 4: " + contador4);
			System.out.println("Total Infracciones Cuatrisemetre: "+ suma);
			
			
			
		} finally{
			if (reader1 != null) {
				try {
					reader1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader2 != null) {
				try {
					reader2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader3 != null) {
				try {
					reader3.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader4 != null) {
				try {
					reader4.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	
	
	public IStack <VOMovingViolations> verificarObjectIDRepetidos(){
		
		IStack<VOMovingViolations> respuesta = new Stack<>();


		Nodo<VOMovingViolations> actual = movingViolationsStack.darPrimero();
		Nodo<VOMovingViolations> auxiliar =null;
		while(actual != null){
			
			auxiliar = actual.darSiguiente();
			while(auxiliar!=null)
			{
				if(actual.darObjeto().objectId().equals(auxiliar.darObjeto().objectId())){
					respuesta.push(actual.darObjeto());
					respuesta.push(auxiliar.darObjeto());
				}
				
				auxiliar = auxiliar.darSiguiente();
			}
			
		actual = actual.darSiguiente();
			
		}
		
		return respuesta;
		
	}
	
	
	public IQueue<VOMovingViolations> consultarInfracciones(int mes1, int dia1, int hora1,int min1, int mes2, int dia2, int hora2, int min2){
		
		Queue<VOMovingViolations> respuesta = new Queue<VOMovingViolations>();
		Nodo<VOMovingViolations> actual = movingViolationsStack.darPrimero();

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar Fechaobjeto = Calendar.getInstance();
		
		c1.set(2018, mes1, dia1, hora1, min1);
		c1.set(2018, mes2, dia2, hora2, min2);
		
		while(actual != null){
			
			Fechaobjeto = actual.darObjeto().getTicketIssueDate();
			
			if(Fechaobjeto.compareTo(c1)>0 && Fechaobjeto.compareTo(c2)<0){
				respuesta.enqueue(actual.darObjeto());
			}
			
			actual = actual.darSiguiente();
		}
		
		return respuesta;
		
	}
	
	public void fineAmtPromedio (String violationCode){
		
		Nodo<VOMovingViolations> actual = movingViolationsStack.darPrimero();
		int sumaAccidente = 0;
		int sumaNoAccidente = 0;
		int contadorAccidente = 0;
		int contadorNoAccidente = 0;
		double respuestaAccidente = 0;
		double respuestaNoAccidente = 0;
		
		
		while(actual != null){
			
			if(actual.darObjeto().getAccidentIndicator()){
				sumaAccidente+=actual.darObjeto().getFineAmount();
				contadorAccidente++;
			}
			else
			{
				sumaNoAccidente+=actual.darObjeto().getFineAmount();
				contadorNoAccidente++;
			}
			actual = actual.darSiguiente();
		}
		
		respuestaAccidente = sumaAccidente/contadorAccidente;
		respuestaNoAccidente = sumaNoAccidente/contadorNoAccidente;
		
		
		
		System.out.println("----------Promedio Fine Amount ------------");
		System.out.println("Promedio  Fine Amount Accidentes: " + respuestaAccidente);
		System.out.println("Promedio  Fine Amount No Accidentes: " + respuestaNoAccidente);
	}


	
	
	
}
