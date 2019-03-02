package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

import com.opencsv.CSVReader;


import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.util.Sort;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;
import model.vo.VOMovingViolations.ObjectIDOrder;
import model.vo.VOViolationCode;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;

	/**
	 * Cola donde se van a cargar los datos de los archivos
	 */
	private Queue<VOMovingViolations> movingViolationsQueue;
	private int cuatrimestreCargado = -1;

	/**
	 * Pila donde se van a cargar los datos de los archivos
	 */
	//private IStack<VOMovingViolations> movingViolationsStack;


	public Controller() {
		view = new MovingViolationsManagerView();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		Controller controller = new Controller();

		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 0:
				view.printMessage("Ingrese el cuatrimestre (1, 2 o 3)");
				int numeroCuatrimestre = sc.nextInt();
				controller.loadMovingViolations(numeroCuatrimestre);/*
				view.printMessage("Elija un cuatrisemestre (1: Enero, Febrero, Marzo, Abril - 2: Mayo, Junio, Julio, Agosto- 3: Septiembre, Octubre, Noviembre y Diciembre)");
				int s = sc.nextInt();
				this.elegirCuatriSemestre(s);
				*/
				System.out.println(movingViolationsQueue.size());
				break;
			case 1:
				boolean isUnique = verifyObjectIDIsUnique();
				view.printMessage("El objectId es �nico: " + isUnique);
				break;
				
			case 2:
				
				view.printMessage("Ingrese la fecha con hora inicial (Ej : 28/03/2017T15:30:20)");
				LocalDateTime fechaInicialReq2A = convertirFecha_Hora_LDT(sc.next());
				
				view.printMessage("Ingrese la fecha con hora final (Ej : 28/03/2017T15:30:20)");
				LocalDateTime fechaFinalReq2A = convertirFecha_Hora_LDT(sc.next());
				
				IQueue<VOMovingViolations> resultados2 = controller.getMovingViolationsInRange(fechaInicialReq2A, fechaFinalReq2A);
				
				view.printMovingViolationsReq2(resultados2);
				
				break;
				
			case 3:
				
				view.printMessage("Ingrese el VIOLATIONCODE (Ej : T210)");
				String violationCode3 = sc.next();
				
				double [] promedios3 = controller.avgFineAmountByViolationCode(violationCode3);
				
				view.printMessage("FINEAMT promedio sin accidente: " + promedios3[0] + ", con accidente:" + promedios3[1]);
				break;
					
				
			case 4:
				
				view.printMessage("Ingrese el ADDRESS_ID");
				String addressId4 = sc.next();

				view.printMessage("Ingrese la fecha con hora inicial (Ej : 28/03/2017)");
				LocalDate fechaInicialReq4A = convertirFecha(sc.next());
				
				view.printMessage("Ingrese la fecha con hora final (Ej : 28/03/2017)");
				LocalDate fechaFinalReq4A = convertirFecha(sc.next());
				
				IStack<VOMovingViolations> resultados4 = controller.getMovingViolationsAtAddressInRange(addressId4, fechaInicialReq4A, fechaFinalReq4A);
				
				view.printMovingViolationsReq4(resultados4);
				
				break;
				
			case 5:
				view.printMessage("Ingrese el limite inferior de FINEAMT  (Ej: 50)");
				double limiteInf5 = sc.nextDouble();
				
				view.printMessage("Ingrese el limite superior de FINEAMT  (Ej: 50)");
				double limiteSup5 = sc.nextDouble();
				
				IQueue<VOViolationCode> violationCodes = controller.violationCodesByFineAmt(limiteInf5, limiteSup5);
				view.printViolationCodesReq5(violationCodes);
				break;
			
			case 6:
				
				view.printMessage("Ingrese el limite inferior de TOTALPAID (Ej: 200)");
				double limiteInf6 = sc.nextDouble();
				
				view.printMessage("Ingrese el limite superior de TOTALPAID (Ej: 200)");
				double limiteSup6 = sc.nextDouble();
				
				view.printMessage("Ordenar Ascendentmente: (Ej: true)");
				boolean ascendente6 = sc.nextBoolean();				
				
				IStack<VOMovingViolations> resultados6 = controller.getMovingViolationsByTotalPaid(limiteInf6, limiteSup6, ascendente6);
				view.printMovingViolationReq6(resultados6);
				break;
				
			case 7:
				
				view.printMessage("Ingrese la hora inicial (Ej: 23)");
				int horaInicial7 = sc.nextInt();
				
				view.printMessage("Ingrese la hora final (Ej: 23)");
				int horaFinal7 = sc.nextInt();
				
				IQueue<VOMovingViolations> resultados7 = controller.getMovingViolationsByHour(horaInicial7, horaFinal7);
				view.printMovingViolationsReq7(resultados7);
				break;
				
			case 8:
				
				view.printMessage("Ingrese el VIOLATIONCODE (Ej : T210)");
				String violationCode8 = sc.next();
				
				double [] resultado8 = controller.avgAndStdDevFineAmtOfMovingViolation(violationCode8);
				
				view.printMessage("FINEAMT promedio: " + resultado8[0] + ", desviación estandar:" + resultado8[1]);
				break;
				
			case 9:
				
				view.printMessage("Ingrese la hora inicial (Ej: 23)");
				int horaInicial9 = sc.nextInt();
				
				view.printMessage("Ingrese la hora final (Ej: 23)");
				int horaFinal9 = sc.nextInt();
				
				int resultado9 = controller.countMovingViolationsInHourRange(horaInicial9, horaFinal9);
				
				view.printMessage("Número de infracciones: " + resultado9);
				break;
			
			case 10:
				view.printMovingViolationsByHourReq10();
				break;
				
			case 11:
				view.printMessage("Ingrese la fecha inicial (Ej : 28/03/2017)");
				LocalDate fechaInicial11 = convertirFecha(sc.next());
				
				view.printMessage("Ingrese la fecha final (Ej : 28/03/2017)");
				LocalDate fechaFinal11 = convertirFecha(sc.next());
				
				double resultados11 = controller.totalDebt(fechaInicial11, fechaFinal11);
				view.printMessage("Deuda total "+ resultados11);
				break;
			
			case 12:	
				view.printTotalDebtbyMonthReq12();
				
				break;
				
			case 13:	
				fin=true;
				sc.close();
				break;
		}
		}
	}




	public void loadMovingViolations(int n)
	{
		if(n == 1)
		{
			loadMovingViolations(new String[] {"Moving_Violations_Issued_in_January_2018.csv", 
					    	     "Moving_Violations_Issued_in_February_2018.csv",
					    	     "Moving_Violations_Issued_in_March_2018.csv",
					    	     "Moving_Violations_Issued_in_Abril_2018.csv"
					    	     });
			cuatrimestreCargado = 1;
		}
		else if(n == 2)
		{
			loadMovingViolations(new String[] {"Moving_Violations_Issued_in_May_2018.csv", 
								 "Moving_Violations_Issued_in_June_2018.csv",
								 "Moving_Violations_Issued_in_July_2018.csv",
								 "Moving_Violations_Issued_in_August_2018.csv"
								 });
			cuatrimestreCargado = 2;
		}
		else if(n == 3){
			loadMovingViolations(new String[] {"Moving_Violations_Issued_in_September_2018.csv", 
		    	     			 "Moving_Violations_Issued_in_October_2018.csv",
		    	     			 "Moving_Violations_Issued_in_November_2018.csv",
		    	     			 "Moving_Violations_Issued_in_December_2018.csv"
		    	     			 });
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
	private void loadMovingViolations(String[] movingViolationsFilePaths){
		CSVReader reader = null;
		int[] contadores = new int[movingViolationsFilePaths.length];
		int fileCounter = 0;
		try {
			movingViolationsQueue = new Queue<VOMovingViolations>();
			
			for (String filePath : movingViolationsFilePaths) {
				reader = new CSVReader(new FileReader("data/"+filePath));
				
				 
				String[] headers = reader.readNext();
				//System.out.println("." + headers[0] + ".");
				int[] posiciones = new int[VOMovingViolations.EXPECTEDHEADERS.length];
				for (int i = 0; i < VOMovingViolations.EXPECTEDHEADERS.length; i++) {
					posiciones[i] = buscarArray(headers, VOMovingViolations.EXPECTEDHEADERS[i]);
				}
				
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
			if (array[i].equals(string)) {
				//System.out.println(i);
				//System.out.println(array[i] + "  " + string );
				return i;
			}
			i += 1;
		}
		//System.out.println("."+string+".");
		//System.out.println(-1);
		return -1;
	}

	public IQueue <VODaylyStatistic> getDailyStatistics () {
		return null;
	}
	
	public IStack <VOMovingViolations> nLastAccidents(int n) {
		return null;
	}

	public boolean verifyObjectIDIsUnique() {
		
		int contador = 0;
		boolean respuesta = true;	
		System.out.println(movingViolationsQueue.size());
		Sort.ordenarMergeSort(movingViolationsQueue, VOMovingViolations.ObjectIDOrder);
		String actual = null;
		String anterior = null;
		Queue<VOMovingViolations> repetidos = new Queue<>();
		Queue<VOMovingViolations> Norepetidos = new Queue<>();
		
		for(VOMovingViolations s: movingViolationsQueue){
			actual = s.objectId();
			if(actual.equals(anterior)){
				repetidos.enqueue(s);
				respuesta = false;
			}
			else
			{
				Norepetidos.enqueue(s);
			}
			anterior = actual;
				
		}
		
		
		if(!respuesta){
			for(VOMovingViolations s: repetidos){
				System.out.println(s.objectId());
			}
			
		}
		
		return respuesta;
	}

	public IQueue<VOMovingViolations> getMovingViolationsInRange(LocalDateTime fechaInicial,
			LocalDateTime fechaFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	public double[] avgFineAmountByViolationCode(String violationCode3) {
		return new double [] {0.0 , 0.0};
	}

	public IStack<VOMovingViolations> getMovingViolationsAtAddressInRange(String addressId,
			LocalDate fechaInicial, LocalDate fechaFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	public IQueue<VOViolationCode> violationCodesByFineAmt(double limiteInf5, double limiteSup5) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStack<VOMovingViolations> getMovingViolationsByTotalPaid(double limiteInf6, double limiteSup6,
			boolean ascendente6) {
		// TODO Auto-generated method stub
		return null;
	}

	public IQueue<VOMovingViolations> getMovingViolationsByHour(int horaInicial7, int horaFinal7) {
		// TODO Auto-generated method stub
		return null;
	}

	public double[] avgAndStdDevFineAmtOfMovingViolation(String violationCode8) {
		// TODO Auto-generated method stub
		return new double [] {0.0 , 0.0};
	}

	public int countMovingViolationsInHourRange(int horaInicial9, int horaFinal9) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double totalDebt(LocalDate fechaInicial11, LocalDate fechaFinal11) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	/**
	 * Convertir fecha a un objeto LocalDate
	 * @param fecha fecha en formato dd/mm/aaaa con dd para dia, mm para mes y aaaa para agno
	 * @return objeto LD con fecha
	 */
	private static LocalDate convertirFecha(String fecha)
	{
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	
	/**
	 * Convertir fecha y hora a un objeto LocalDateTime
	 * @param fecha fecha en formato yyyy-MM-dd'T'HH:mm:ss'.000Z' con dd para dia, mm para mes y yyy para agno, HH para hora, mm para minutos y ss para segundos
	 * @return objeto LDT con fecha y hora integrados
	 */
	private static LocalDateTime convertirFecha_Hora_LDT(String fechaHora)
    {
                   return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
    }
	
	
	
}
