package controller;

import java.util.Scanner;

import model.data_structures.IQueue;
import model.data_structures.IStack;
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
		// TODO
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
