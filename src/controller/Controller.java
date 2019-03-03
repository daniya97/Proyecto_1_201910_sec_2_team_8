package controller;

import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;

import java.time.*;
import java.time.format.*;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import model.util.Sort;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.IArregloDinamico;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.data_structures.ArregloDinamico;

import model.vo.VOMovingViolations;
import model.vo.VODaylyStatistic;
import model.vo.VOViolationCode;

import view.MovingViolationsManagerView;

public class Controller {
	/*
	 * Atributos
	 */
	/**
	 * Objeto de la Vista
	 */
	private MovingViolationsManagerView view;
	
	/**
	 * Lista donde se van a cargar los datos de los archivos
	 */
	private static IArregloDinamico<VOMovingViolations> movingVOLista;
	/**
	 * Numero actual del cuatrimestre cargado
	 */
	private static int cuatrimestreCargado = -1;
	
	/*
	 * Constructor
	 */
	public Controller() {
		view = new MovingViolationsManagerView();
	}

	/*
	 * Metodos
	 */
	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		Controller controller = new Controller();
		int option = -1;
		boolean numeroEncontrado = false;
		
		while(!fin)
		{
			view.printMenu();
			// Para tener que reiniciar el programa si no se da una opcion valida
			while (!numeroEncontrado){
				try {
					option = sc.nextInt();
					numeroEncontrado = true;
				} catch (InputMismatchException e) {
					System.out.println("Esa no es una opcion valida");
					view.printMenu();
					sc = new Scanner(System.in);
				}
			} numeroEncontrado = false;
					
			try { // Este try se usa para no tener que reiniciar el programa en caso de que 
				  // ocurra un error pequenio al ejecutar como ingresar mal la fecha  
			
			switch(option)
			{
			case 0:
				view.printMessage("Ingrese el cuatrimestre (1, 2 o 3)");
				int numeroCuatrimestre = sc.nextInt();
				IQueue<Integer> resultados0 = loadMovingViolations(numeroCuatrimestre);
				view.printMovingViolationsLoadInfo(resultados0);
				break;
			case 1:
				IQueue<VOMovingViolations> resultados1 = verifyObjectIDIsUnique();
				view.printMovingViolationsReq1(resultados1);
				break;

			case 2:

				view.printMessage("Ingrese la fecha con hora inicial (Ej : 01/04/2018T04:40:00)");
				LocalDateTime fechaInicialReq2A = convertirFecha_Hora_LDT(sc.next());

				view.printMessage("Ingrese la fecha con hora final (Ej : 01/04/2018T04:40:00)");
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

				view.printMessage("Ingrese la fecha inicial (Ej : 28/03/2018)");
				LocalDate fechaInicialReq4A = convertirFecha(sc.next());

				view.printMessage("Ingrese la fecha final (Ej : 28/03/2018)");
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
				double[] resultados10 = controller.percentWithAccidentsByHour();
				view.printMovingViolationsByHourReq10(resultados10);
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
				double[] resultados12 = controller.accumulatedDebtByMonth();
				view.printTotalDebtbyMonthReq12(resultados12);
				break;

			case 13:	
				fin=true;
				sc.close();
				break;
			}
			} catch(Exception e) { // Si ocurrio un error al ejecutar algun metodo
				e.printStackTrace(); System.out.println("Ocurrio un error. Se recomienda reiniciar el programa.");}
		}
	}

	public IQueue<Integer> loadMovingViolations(int n)
	{
		IQueue<Integer> numeroDeCargas = new Queue<>();
		if(n == 1)
		{
			numeroDeCargas = loadMovingViolations(new String[] {"Moving_Violations_Issued_in_January_2018.csv", 
					    	     "Moving_Violations_Issued_in_February_2018.csv",
					    	     "Moving_Violations_Issued_in_March_2018.csv",
					    	     "Moving_Violations_Issued_in_April_2018.csv"
					    	     });
			cuatrimestreCargado = 1;
		}
		else if(n == 2)
		{
			numeroDeCargas = loadMovingViolations(new String[] {"Moving_Violations_Issued_in_May_2018.csv", 
								 "Moving_Violations_Issued_in_June_2018.csv",
								 "Moving_Violations_Issued_in_July_2018.csv",
								 "Moving_Violations_Issued_in_August_2018.csv"
								 });
			cuatrimestreCargado = 2;
		}
		else if(n == 3){
			numeroDeCargas = loadMovingViolations(new String[] {"Moving_Violations_Issued_in_September_2018.csv", 
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
		return numeroDeCargas;
	}

	/**
	 * Carga la informacion sobre infracciones de los archivos a una pila y una cola ordenadas por fecha.
	 */
	private IQueue<Integer> loadMovingViolations(String[] movingViolationsFilePaths){
		CSVReader reader = null;
		IQueue<Integer> numeroDeCargas =new Queue<>();
		
		int contadorInf; // Cuenta numero de infracciones en cada archivo
		try {
			movingVOLista = new ArregloDinamico<VOMovingViolations>(500000);

			for (String filePath : movingViolationsFilePaths) {
				reader = new CSVReader(new FileReader("data/"+filePath));
				
				contadorInf = 0;
				// Deduce las posiciones de las columnas que se reconocen de acuerdo al header
				String[] headers = reader.readNext();
				int[] posiciones = new int[VOMovingViolations.EXPECTEDHEADERS.length];
				for (int i = 0; i < VOMovingViolations.EXPECTEDHEADERS.length; i++) {
					posiciones[i] = buscarArray(headers, VOMovingViolations.EXPECTEDHEADERS[i]);
				}
				
				// Lee linea a linea el archivo para crear las infracciones y cargarlas a la lista
				for (String[] row : reader) {
					movingVOLista.agregar(new VOMovingViolations(posiciones, row));
					contadorInf += 1;
				}
				// Se agrega el numero de infracciones cargadas en este archivo al resultado 
				numeroDeCargas.enqueue(contadorInf);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}

		}
		return numeroDeCargas;
	}

	public IQueue <VODaylyStatistic> getDailyStatistics () {
		return null;
	}

	public IStack <VOMovingViolations> nLastAccidents(int n) {
		return null;
	}

	public IQueue<VOMovingViolations> verifyObjectIDIsUnique() {

		//Se ordena el arreglo din�mico por ObjectID
		Sort.ordenarShellSort(movingVOLista, new VOMovingViolations.ObjectIDOrder());
		String actual = null;
		String anterior = null;
		boolean yaIncluido = false;
		Queue<VOMovingViolations> repetidos = new Queue<>();
		
		//Se recorren las infracciones
		for(VOMovingViolations s: movingVOLista){
			
			//Se toma el objeto actual
			actual = s.objectId();
			//En caso de que se repita el ObjID y no se haya incluido, se agrega a la cola
			if(actual.equals(anterior)){
				if(!yaIncluido){
				repetidos.enqueue(s);
				yaIncluido = true;
				}
			}
			else{
				yaIncluido = false;
			}
			
			//El actual queda como anterior
			anterior = actual;
		}

		//Se devuelve una cola con los repetidos
		return repetidos;

	}

	public IQueue<VOMovingViolations> getMovingViolationsInRange(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {

		IQueue<VOMovingViolations> respuesta = new Queue<>();
		
		//Se ordenan las infracciones de acuerdo a la fecha en la que fueron impuestas
		Sort.ordenarShellSort(movingVOLista, new VOMovingViolations.TicketIssueOrder());


		//Para todas las infracciones, en caso de encontrarse entre la fechaInicial y fechaFinal
		//se agregan a la Cola
		for(VOMovingViolations s: movingVOLista){
			if(s.getTicketIssueDate().compareTo(fechaInicial)>=0){
				if(s.getTicketIssueDate().compareTo(fechaFinal)<=0){
					respuesta.enqueue(s);
				}
				else{
					return respuesta;
				}
			}
		}
		//Se retorna la cola con las infracciones en el rango (inclusivo)
		return respuesta;
	}

	public double[] avgFineAmountByViolationCode(String violationCode3) {
		int suma1 = 0;
		int suma2 = 0;
		int contador1 = 0;
		int contador2 = 0;
		
		//Se recorren todas las infracciones del cuatrimestre
		for(VOMovingViolations s: movingVOLista){
			if(s.getViolationCode().equals(violationCode3)){
				
				//En caso de que no hubo accidente
				if(!s.getAccidentIndicator()){
					suma1 += s.getFineAmount();
					contador1++;
				}
				//en caso de que s� hubo accidente
				else{
					suma2+=s.getFineAmount();
					contador2++;
				}
			}
		}
		//Se devuellve el promedio
		return new double [] {suma1 != 0? suma1/contador1:0 , suma2 != 0? suma2/contador2:0};
	}

	public IStack<VOMovingViolations> getMovingViolationsAtAddressInRange(String addressId,
			LocalDate fechaInicial, LocalDate fechaFinal) {
		
		ArregloDinamico<VOMovingViolations> respuesta = new ArregloDinamico<>();
		IStack<VOMovingViolations> resultado = new Stack<>();
		
		//En caso de que la infracci�n coincida con el addressID y este en el rango solicitado se agrega al arreglo din�mico
		for(VOMovingViolations s: movingVOLista){
			if(s.getAddressID().equals(addressId) && s.getTicketIssueDate().toLocalDate().compareTo(fechaInicial)>=0 && s.getTicketIssueDate().toLocalDate().compareTo(fechaFinal)<=0){
				respuesta.agregar(s);
			}
		}
		
		//Se ordena el arreglo ascendentemente
		Sort.ordenarShellSort(respuesta,new VOMovingViolations.StreetsgeIDDateOrder());
		
		//Se agregan los elementos del arreglo a una pila
		//Se logra el orden descentemente por StreetseIdDate
		for(VOMovingViolations s: respuesta){
			resultado.push(s);
		}
		
		return resultado;
	}

	public IQueue<VOViolationCode> violationCodesByFineAmt(double limiteInf5, double limiteSup5) {
		// Ordena los datos por codigo de violacion
		Sort.ordenarShellSort(movingVOLista, new VOMovingViolations.ViolationCodeOrder());

		// Cola de tuplas a retornar
		Queue<VOViolationCode> colaTuplas = new Queue<VOViolationCode>(); 
		Iterator<VOMovingViolations> iterador = movingVOLista.iterator();

		// Si no hay datos, entonces retorna una cola vacia
		if (!iterador.hasNext()) return colaTuplas;

		// Como los datos estan ordenados, tomamos una infraccion de referencia para comparar con
		// los datos inmediatamente siguientes
		VOMovingViolations infrRevisar = iterador.next();
		String codigoRef = infrRevisar.getViolationCode();
		// variables para hallar el promedio
		int contadorIgs = 1;
		double sumaActual = infrRevisar.getFineAmount();
		double promedio;

		while (iterador.hasNext()) {
			infrRevisar = iterador.next();

			if (codigoRef.equals(infrRevisar.getViolationCode())) {
				// Actualiza contadores
				contadorIgs += 1;
				sumaActual += infrRevisar.getFineAmount();
			} else {
				// Agrega el VOCOde que esta revisando a la cola
				promedio = sumaActual/contadorIgs; 
				if (limiteInf5 <= promedio && promedio <= limiteSup5) {
					colaTuplas.enqueue(new VOViolationCode(codigoRef, promedio));
				}
				// Reestablece referencias
				codigoRef = infrRevisar.getViolationCode();
				contadorIgs = 1;
				sumaActual = infrRevisar.getFineAmount();
			}
		}
		// Agregar la ultima referencia
		promedio = sumaActual/contadorIgs; 
		if (limiteInf5 <= promedio && promedio <= limiteSup5) {
			colaTuplas.enqueue(new VOViolationCode(codigoRef, promedio));
		}

		return colaTuplas;
	}

	public IStack<VOMovingViolations> getMovingViolationsByTotalPaid(double limiteInf6, double limiteSup6,
			boolean ascendente6) {
		// Lista ordenable con la respuesta (que tendra menos datos que la lista total)
		IArregloDinamico<VOMovingViolations> listaResp = new ArregloDinamico<VOMovingViolations>();

		for (VOMovingViolations infraccion : movingVOLista) {
			if (limiteInf6 <= infraccion.getTotalPaid() && infraccion.getTotalPaid() <= limiteSup6) {
				listaResp.agregar(infraccion);
			}
		}
		// Ordena los datos a devolver
		if (ascendente6) {
			Sort.ordenarShellSort(listaResp, new VOMovingViolations.TicketIssueOrder());
		} else {
			Sort.ordenarShellSort(listaResp, new VOMovingViolations.TicketIssueOrder().reversed());
		}
		
		// Copiar datos ordenados empezando en el final para transferir el orden actual a la pila
		// Y para minimizar el costo de la operacion de borrado dada la implementacion de borrar
		IStack<VOMovingViolations> pilaResp = new Stack<>();
		int n = listaResp.darTamano();
		for (int i = n - 1; i >= 0; i--) {
			pilaResp.push(listaResp.eliminarEnPos(i));
		}

		return pilaResp;
	}

	public IQueue<VOMovingViolations> getMovingViolationsByHour(int horaInicial7, int horaFinal7) {
		// Conversion de horas a formato util
		LocalTime horaIn = LocalTime.of(horaInicial7, 0);
		LocalTime horaFin = LocalTime.of(horaFinal7, 0);

		ArregloDinamico<VOMovingViolations> arregloInf = new ArregloDinamico<>();

		LocalTime hora;
		for (VOMovingViolations infraccion : movingVOLista) {
			hora = infraccion.getTicketIssueDate().toLocalTime();
			if (horaIn.compareTo(hora) <= 0 && hora.compareTo(horaFin) <= 0) {
				arregloInf.agregar(infraccion);
			}
		}

		// Order resultados (~12 veces menos datos)
		Sort.ordenarShellSort(arregloInf, new VOMovingViolations.ViolationDescOrder());

		// Pasar datos a una cola
		Queue<VOMovingViolations> colaInf = new Queue<>();
		for (VOMovingViolations infraccion : arregloInf) colaInf.enqueue(infraccion);
		return colaInf;
	}

	public double[] avgAndStdDevFineAmtOfMovingViolation(String violationCode8) {
		// Extraccion de datos necesarios
		double sumaFA = 0;
		int contadorFA = 0;
		// (Este arreglo no es necesario. Se hace de esta manera para usar herramientas del curso 
		// Puede simplemente iterarse una vez la lista y un contador adicional para la suma de los
		// cuadrados de los fineamounts
		ArregloDinamico<Integer> valoresFA = new ArregloDinamico<>();
		
		for (VOMovingViolations infraccion : movingVOLista) {
			if (infraccion.getViolationCode().equals(violationCode8)) {
				sumaFA += infraccion.getFineAmount();
				contadorFA += 1;
				valoresFA.agregar(infraccion.getFineAmount());
			}
		}
		// Calculos
		if (contadorFA == 0) return new double [] {0.0, 0.0};
		
		double promedio = sumaFA/contadorFA;
		double var = 0;
		for (double fa : valoresFA) {
			var += (fa - promedio)*(fa - promedio)/contadorFA;
		}

		return new double [] {promedio , Math.sqrt(var)};
	}

	public int countMovingViolationsInHourRange(int horaInicial9, int horaFinal9) {

		int contador = 0;
		for(VOMovingViolations s: movingVOLista){
			if(s.getTicketIssueDate().getHour()>=horaInicial9 && s.getTicketIssueDate().getHour()<=horaFinal9 ) contador++;
		}
		
		return contador;
	}

	public double totalDebt(LocalDate fechaInicial11, LocalDate fechaFinal11) {
		double deudaAcum = 0;
		LocalDate fechaAct;
		for (VOMovingViolations infraccion : movingVOLista) {
			fechaAct = infraccion.getTicketIssueDate().toLocalDate();
			if (fechaInicial11.compareTo(fechaAct) <= 0 && fechaAct.compareTo(fechaFinal11) <= 0) {
				deudaAcum += (infraccion.getTotalPaid() - infraccion.getFineAmount() - 
						infraccion.getPenalty1() - infraccion.getPenalty2());
			}
		}

		return deudaAcum;
	}
	
	private double[] percentWithAccidentsByHour() {
		
		double[] infraccionesByHour = new double[24]; // Se inicializan en 0's
		double[] accidentesByHour = new double[24];
		int horaActual;
		for (VOMovingViolations infraccion : movingVOLista) {
			horaActual = infraccion.getTicketIssueDate().getHour();
			
			infraccionesByHour[horaActual] += 1;
			
			if (infraccion.getAccidentIndicator()) accidentesByHour[horaActual] += 1;
		}
		
		// Convertir accidentes por hora en porcentajes
		for (int i = 0; i < accidentesByHour.length; i++) {
			accidentesByHour[i] = (100.*accidentesByHour[i]) / infraccionesByHour[i];
		}
		
		return accidentesByHour;
	}
	
	private double[] accumulatedDebtByMonth() {
		double[] deudasByMonth = new double[] {0., 0., 0., 0.};
		int mesAct;
		double deudaAdicional = 0;
		
		// Agregar la deuda de cada infraccion a la deuda total de cada mes
		for (VOMovingViolations infraccion : movingVOLista) {
			mesAct = infraccion.getTicketIssueDate().getMonthValue() - (cuatrimestreCargado-1)*4;
			deudaAdicional = (infraccion.getTotalPaid() - infraccion.getFineAmount() - 
					infraccion.getPenalty1() - infraccion.getPenalty2());
			
			deudasByMonth[mesAct - 1] += deudaAdicional;
		}
		
		// Acumular las deudas en el mismo arreglo
		for (int i = 1; i < deudasByMonth.length; i++) {
			deudasByMonth[i] += deudasByMonth[i-1];
		}
		
		return deudasByMonth;
	}
	
	/**
	 * Metodo para buscar strings en un array de strings, usado para deducir la posicion
	 * de las columnas esperadas en cada archivo.
	 * @param array
	 * @param string
	 * @return
	 */
	private int buscarArray(String[] array, String string) {
		int i = 0;

		while (i < array.length) {
			if (array[i].equalsIgnoreCase(string)) return i;
			i += 1;
		}
		return -1;
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
		return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss"));

    }
}

