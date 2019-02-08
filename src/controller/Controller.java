package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.CSVReader;

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
	private Queue<VOMovingViolations> movingViolationsQueue;
	
	/**
	 * Pila donde se van a cargar los datos de los archivos
	 */
	private Stack<VOMovingViolations> movingViolationsStack;


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
					this.loadMovingViolations();
					break;
					
				case 2:
					IQueue<VODaylyStatistic> dailyStatistics = this.getDailyStatistics();
					view.printDailyStatistics(dailyStatistics);
					break;
					
				case 3:
					view.printMensage("Ingrese el número de infracciones a buscar");
					int n = sc.nextInt();

					IStack<VOMovingViolations> violations = this.nLastAccidents(n);
					view.printMovingViolations(violations);
					break;
											
				case 4:	
					fin=true;
					sc.close();
					break;
			}
		}
	}

	

	public void loadMovingViolations() {
		CSVReader readerJan = null;
		CSVReader readerFeb = null;
		try {
			readerJan = new CSVReader(new FileReader("data/Moving_Violations_Issued_in_January_2018_ordered.csv"));
			readerFeb = new CSVReader(new FileReader("data/Moving_Violations_Issued_in_January_2018_ordered.csv"));
			
			movingViolationsStack = new Stack<VOMovingViolations>();
			movingViolationsQueue = new Queue<VOMovingViolations>();
			
			VOMovingViolations infraccion;
			System.out.println(readerJan.readNext()[0]); // Handle header
			System.out.println(readerFeb.readNext()[0]); // Handle header
			
		    for (String[] row : readerJan) {
		    	infraccion = new VOMovingViolations(row);
		    	movingViolationsQueue.enqueue(infraccion);
		    	movingViolationsStack.push(infraccion);
		    	//System.out.println("Se ha annadido la fila identificada por " + row[0] + "\n"); // TEST
		    }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	public IQueue <VODaylyStatistic> getDailyStatistics () {
		// TODO
		return null;
	}
	
	public IStack <VOMovingViolations> nLastAccidents(int n) {
		// TODO
		return null;
	}
}
