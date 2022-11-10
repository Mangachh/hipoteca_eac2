package models;


/**
 * Model for the "Hipoteca"
 * @author Mangachh
 *
 */
public class Hipoteca {
	
	public static final String[] BONIFICACIONS = {"CAP", "< 35 anys", "Col.lectiu especial", "Funcionari"};
	public static final int BON_CAP_INDEX = 0;
	public static final int BON_MAJ_35_INDEX = 1;
	public static final int BON_COL_ESPECIAL_INDEX = 2;
	public static final int BON_FUNCIONARI_INDEX = 3;
	
	
	private String descompte;
	private int edat;
	private int anysHipoteca;
	private double euribor;
	
	public Hipoteca() {
		
	}	
	
	public Hipoteca(String descompte, int edat, int anysHipoteca, double euribor) {
		this.descompte = descompte;
		this.edat = edat;
		this.anysHipoteca = anysHipoteca;
		this.euribor = euribor;
	}
	
	
	public String getDescompte() {
		return descompte;
	}
	public void setDescompte(String descompte) {
		this.descompte = descompte;
	}
	public int getEdat() {
		return edat;
	}
	public void setEdat(int edat) {
		this.edat = edat;
	}
	public int getAnysHipoteca() {
		return anysHipoteca;
	}
	public void setAnysHipoteca(int anysHipoteca) {
		this.anysHipoteca = anysHipoteca;
	}
	public double getEuribor() {
		return euribor;
	}
	public void setEuribor(double euribor) {
		this.euribor = euribor;
	}
	
	
}
