package model.vo;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations {


	/**
	 * Atributos de la infracci�n
	 */
	private String iD;
	private String location;
	private int streetsegID;
	private double totalPaid;
	private double penalty1;
	private double penalty2;
	private boolean accidentIndicator;
	private Calendar ticketIssueDate;
	private String violationCode;
	private String violationDesc;
	private int fineAmount;


	/**
	 * Constructor. Recibe los argumentos de la infracci�n a trav�s del archivo CSV
	 */
	public VOMovingViolations(String[] linea){

		iD = linea[0];
		location = linea[2];
		streetsegID = Integer.parseInt(linea[3]);
		totalPaid = Double.parseDouble(linea[9]);
		penalty1 = Double.parseDouble(linea[10]);
		if (!linea[11].equals("")) penalty2 = Double.parseDouble(linea[11]);
		else penalty2 = 0;
		if 		(linea[12].equalsIgnoreCase("Yes")) accidentIndicator = true;
		else if (linea[12].equalsIgnoreCase("No"))	accidentIndicator = false;
		else throw new IllegalArgumentException("El indicador de accidente no tiene un valor reconocible.");
		String auxiliar =  linea[13];
		ticketIssueDate = Calendar.getInstance();
		ticketIssueDate.set(Integer.parseInt(auxiliar.substring(0, 4)), Integer.parseInt(auxiliar.substring(5, 7)), Integer.parseInt(auxiliar.substring(8,10)),Integer.parseInt( auxiliar.substring(11,13)),Integer.parseInt(auxiliar.substring(14,16)));
		violationCode = linea[14];
		violationDesc = linea[15];
		fineAmount = Integer.parseInt(linea[8]);
	}

	/**
	 * @return id - Identificador único de la infracción
	 */
	public String objectId() {
		return iD;
	}	


	/**
	 * @return location - Direcci�n en formato de texto.
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * @return streetsgeID
	 */
	public int getStreetsegID() {
		return streetsegID;
	}

	/**
	 * @return date - Fecha cuando se puso la infracción .
	 */
	public Calendar getTicketIssueDate() {
		return ticketIssueDate;
	}

	/**
	 * @return totalPaid - Cuanto dinero efectivamente pag� el que recibi� la infracci�n en USD.
	 */
	public double getTotalPaid() {
		return totalPaid;
	}
	
	/**
	 * @return penalty1 - 
	 */
	public double getPenalty1() {
		return penalty1;
	}
	
	/**
	 * @return penalty2 - 
	 */
	public double getPenalty2() {
		return penalty2;
	}

	/**
	 * @return accidentIndicator - Si hubo un accidente o no.
	 */
	public boolean getAccidentIndicator() {
		return accidentIndicator;
	}

	/**
	 * @return description - Descripci�n textual de la infracción.
	 */
	public String  getViolationDescription() {
		return violationDesc;
	}

	public String  getViolationCode() {
		return violationCode;
	}

	public int getFineAmount() {
		return fineAmount;
	}
	
	/**
	 * For comparations
	 */
	
	public static class ObjectIDOrder implements Comparator<VOMovingViolations> {

		@Override
		public int compare(VOMovingViolations arg0, VOMovingViolations arg1) {
			
			return 0;
		}
		
	}
	
	public static class TicketIssueOrder implements Comparator<VOMovingViolations> {

		@Override
		public int compare(VOMovingViolations arg0, VOMovingViolations arg1) {
			
			return 0;
		}
		
	}
	
	public static class TimeOrder implements Comparator<VOMovingViolations> {

		@Override
		public int compare(VOMovingViolations arg0, VOMovingViolations arg1) {
			
			return 0;
		}
		
	}
	
	public static class ViolationCodeOrder implements Comparator<VOMovingViolations> {

		@Override
		public int compare(VOMovingViolations arg0, VOMovingViolations arg1) {
			
			return 0;
		}
		
	}
	
	public static class StreetsgeIDDateOrder implements Comparator<VOMovingViolations> {

		@Override
		public int compare(VOMovingViolations arg0, VOMovingViolations arg1) {
			
			return 0;
		}
		
	}
	
	
	public static class TotalPaidOrder implements Comparator<VOMovingViolations> {

		@Override
		public int compare(VOMovingViolations arg0, VOMovingViolations arg1) {
			
			return 0;
		}
		
	}
	
	public static class ViolationDescOrder implements Comparator<VOMovingViolations> {

		@Override
		public int compare(VOMovingViolations arg0, VOMovingViolations arg1) {
			
			return 0;
		}
		
	}
}
