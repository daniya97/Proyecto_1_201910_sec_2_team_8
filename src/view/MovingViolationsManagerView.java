package view;

import model.data_structures.IQueue;
import model.data_structures.IStack;

import model.vo.VOMovingViolations;

public class MovingViolationsManagerView 
{
	public MovingViolationsManagerView() {
		
	}
	
	public void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Proyecto 1----------------------");
		System.out.println("1. Cargar los datos de las infracciones en movimiento");
//		System.out.println("2. Dar estadisticas diarias de las infracciones");
//		System.out.println("3. Dar ultimos n infracciones que terminaron en accidente");
//		System.out.println("4. Salir");
        System.out.println("Digite el n�mero de opci�n para ejecutar la tarea, luego presione enter: (Ej., 1):");

	}
	
	public void printVerificacion(IStack<VOMovingViolations> verificacion) {
	
		for (VOMovingViolations repetidos : verificacion) 
		{
			System.out.println(repetidos.objectId());
		}
		System.out.println("Se encontraron "+ verificacion.size());
	}
	
	public void printMovingViolations(IStack<VOMovingViolations> violations) {
		System.out.println("Se encontraron "+ violations.size() + " elementos");
		for (VOMovingViolations violation : violations) 
		{
			System.out.println(violation.objectId() + "\t" 
								+ violation.getTicketIssueDate() + "\t" 
								+ violation.getLocation()+ "\t" 
								+ violation.getViolationDescription());
		}
	}
	
	public void printMensage(String mensaje) {
		System.out.println(mensaje);
	}
}
