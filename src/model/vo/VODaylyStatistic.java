package model.vo;

import model.data_structures.Queue;

/**
 * Objeto que guarda la informacion relevante sobre las infracciones cometidas en un dia particular:
 * fecha del dÃ­a, nÃºmero de	accidentes,	nÃºmero de infracciones y suma total de FINEAMT de las infracciones ese dÃ­a.
 */
public class VODaylyStatistic {

	/**
	 * Guarda el día
	 */
	private String dia;

	/**
	 * Atributo del número de accidentes cometidos en el día
	 */
	private int numeroAccidentes;


	/**
	 * Atributo del número de infracciones cometidos en el día
	 */
	private int numeroInfracciones;


	/**
	 * Atributo del total fine Amount en el día
	 */
	private int totalFineAmt;


	/**
	 * Constructor
	 */
	public VODaylyStatistic(Queue<VOMovingViolations> cola, String fecha){

		dia = fecha;			
		numeroInfracciones = cola.size();

		int contador = 0;
		int suma = 0;
		for (VOMovingViolations actual:cola) {
			if(actual.getAccidentIndicator()){
				contador ++;
			}

			suma = suma + actual.getFineAmount();

		}

		numeroAccidentes = contador;
		totalFineAmt = suma;

	}

	/**
	 * @return El día de las estadísticas
	 */
	public String darDia(){
		return dia;
	}

	/**
	 * @return el número de accidentes
	 */
	public int darNumeroAccidentes(){
		return numeroAccidentes;
	}

	/**
	 * @return El número de infracciones
	 */
	public int numeroInfracciones(){
		return numeroInfracciones;
	}

	/**
	 * @return El total monto de las multas
	 */
	public int totalFineAmount(){
		return totalFineAmt;
	}

}
