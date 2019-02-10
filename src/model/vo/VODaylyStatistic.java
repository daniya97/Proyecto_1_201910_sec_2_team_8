package model.vo;

import model.data_structures.Queue;
import model.data_structures.Stack;

public class VODaylyStatistic {
	
private String dia;

private int numeroAccidentes;

private int numeroInfracciones;

private int totalFineAmt;


public VODaylyStatistic(Queue<VOMovingViolations> cola, String fecha){
	
	dia = fecha;			
	numeroInfracciones = cola.size();
	
	int contador = 0;
	int suma = 0;
	for (VOMovingViolations actual:cola) {
		if(actual.getAccidentIndicator().equals("Yes")){
			contador ++;
		}
	
		suma = suma + actual.getFineAmount();
		
	}
	
	numeroAccidentes = contador;
	totalFineAmt = suma;
	
	
}

public String darDia(){
	return dia;
}

public int darNumeroAccidentes(){
	return numeroAccidentes;
}

public int numeroInfracciones(){
	return numeroInfracciones;
}


public int totalFineAmount(){
	return totalFineAmt;
}


	
	
	
	
	
	
	
	//TODO
}
