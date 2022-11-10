package models;

/**
 * Model for the habitatge.
 * By using this model we can perform the calculations easily
 * @author manga
 *
 */
public class Habitatge {
	
	public static final String[] TIPUS_HABITATGE = {"Habitatge Habitual", "Segona residència"}; 
	public static final int HAB_HABITUAL_INDEX = 0;
	public static final int HAB_SEC_INDEX = 1;
	
	private String tipus;
	private double estalvis;
	private double preu;
	
	public Habitatge() {}
	
	
	public Habitatge(String tipus, double estalvis, double preu) {
		this.tipus = tipus;
		this.estalvis = estalvis;
		this.preu = preu;
	}
	
	
	public String getTipus() {
		return tipus;
	}
	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	public double getEstalvis() {
		return estalvis;
	}
	public void setEstalvis(double estalvis) {
		this.estalvis = estalvis;
	}
	public double getPreu() {
		return preu;
	}
	public void setPreu(double preu) {
		this.preu = preu;
	}
	
	
}
