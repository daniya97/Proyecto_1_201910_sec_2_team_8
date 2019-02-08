package model.vo;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations {


	/**
	 * Atributos de la infracción
	 */
	private String iD;
	private String location;
	private int totalPaid;
	private String accidentIndicator;
	private String ticketIssueDate;
	private String violationCode;
	private String violationDesc;
	
	
	/**
	 * Constructor. Recibe los argumentos de la infracción a través del archivo CSV
	 */
	public VOMovingViolations(String[] linea){
	
	
		iD = linea[0];
		location = linea[2];
		totalPaid = Integer.parseInt(linea[9]);
		accidentIndicator = linea[12];
		ticketIssueDate = linea[13];
		violationCode = linea[14];
		violationDesc = linea[15];
		
	}
	
	
	
	
	/**
	 * @return id - Identificador Ãºnico de la infracciÃ³n
	 */
	public String objectId() {
		// TODO Auto-generated method stub
		return iD;
	}	
	
	
	/**
	 * @return location - Dirección en formato de texto.
	 */
	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	/**
	 * @return date - Fecha cuando se puso la infracciÃ³n .
	 */
	public String getTicketIssueDate() {
		// TODO Auto-generated method stub
		return ticketIssueDate;
	}
	
	/**
	 * @return totalPaid - Cuanto dinero efectivamente pagó el que recibió la infracción en USD.
	 */
	public int getTotalPaid() {
		// TODO Auto-generated method stub
		return totalPaid;
	}
	
	/**
	 * @return accidentIndicator - Si hubo un accidente o no.
	 */
	public String  getAccidentIndicator() {
		// TODO Auto-generated method stub
		return accidentIndicator;
	}
		
	/**
	 * @return description - Descripción textual de la infracciÃ³n.
	 */
	public String  getViolationDescription() {
		// TODO Auto-generated method stub
		return violationDesc;
	}
	
	public String  getViolationCode() {
		// TODO Auto-generated method stub
		return violationCode;
	}
}
