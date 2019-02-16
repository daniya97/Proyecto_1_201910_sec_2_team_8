package model.vo;

import java.util.Calendar;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations {

	private static final String OBJECTID = "OBJECTID";
	//private static final String ROW_= "ROW_";
	private static final String LOCATION = "LOCATION";
	//private static final String ADDRESS_ID = "ADDRESS_ID";
	private static final String STREETSEGID = "STREETSEGID";
	//private static final String XCOORD = "XCOORD";
	//private static final String YCOORD = "YCOORD";
	private static final String TICKETTYPE = "TICKETTYPE";
	private static final String FINEAMT = "FINEAMT";
	private static final String TOTALPAID = "TOTALPAID";
	private static final String PENALTY1 = "PENALTY1";
	private static final String PENALTY2 = "PENALTY2";
	private static final String ACCIDENTINDICATOR = "ACCIDENTINDICATOR";
	//private static final String AGENCYID = "AGENCYID";
	private static final String TICKETISSUEDATE = "TICKETISSUEDATE";
	private static final String VIOLATIONCODE = "VIOLATIONCODE";
	private static final String VIOLATIONDESC = "VIOLATIONDESC";	
	//private static final String ROW_ID = "ROW_ID";
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
	public VOMovingViolations(String[] headers, String[] linea){
		String campo;
		
		iD = linea[Arrays.binarySearch(headers, OBJECTID)];
		
		location = linea[Arrays.binarySearch(headers, LOCATION)];
		
		campo = linea[Arrays.binarySearch(headers, STREETSEGID)];
		if (!campo.equals("")) streetsegID = Integer.parseInt(campo);
		else streetsegID = -1;
		
		totalPaid = Double.parseDouble(linea[Arrays.binarySearch(headers, TOTALPAID)]);
		
		campo = linea[Arrays.binarySearch(headers, PENALTY1)];
		if (!campo.equals("")) penalty1 = Double.parseDouble(campo);
		else penalty1 = 0;
		
		campo = linea[Arrays.binarySearch(headers, PENALTY2)];
		if (!campo.equals("")) penalty2 = Double.parseDouble(campo);
		else penalty2 = 0;
		
		campo = linea[Arrays.binarySearch(headers, ACCIDENTINDICATOR)];
		if 		(campo.equalsIgnoreCase("Yes")) accidentIndicator = true;
		else if (campo.equalsIgnoreCase("No"))	accidentIndicator = false;
		else throw new IllegalArgumentException("El indicador de accidente no tiene un valor reconocible.");
		
		campo = linea[Arrays.binarySearch(headers, TICKETISSUEDATE)];
		ticketIssueDate = Calendar.getInstance();
		ticketIssueDate.set(Integer.parseInt(campo.substring(0, 4)), Integer.parseInt(campo.substring(5, 7)), Integer.parseInt(campo.substring(8,10)),Integer.parseInt(campo.substring(11,13)),Integer.parseInt(campo.substring(14,16)));
		
		violationCode = linea[Arrays.binarySearch(headers, VIOLATIONCODE)];
		
		violationDesc = linea[Arrays.binarySearch(headers, VIOLATIONDESC)];
		
		fineAmount = Integer.parseInt(linea[Arrays.binarySearch(headers, FINEAMT)]);
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
