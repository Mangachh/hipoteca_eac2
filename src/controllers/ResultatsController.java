package controllers;

import models.Hipoteca;
import view.Resultats;

public class ResultatsController extends ControllerBase {
	private Resultats view;
	
	// bonificacions
	private double BON_CAP = 0;
	private double BON_FUNCIONARI = -1;
	private double BON_MENOR_35 = -0.5;
	private double BON_COL_ESPECIAL = -0.75;
	
	
	
	private double INTERES_FIXA_NO_BON = 2.95;
	private double INTERES_FIXA_BON = 2.55;
	
	// oju estas dos hay que sumar el porcentage del euribor
	private double INTERES_VAR_NO_BON = 1.24;
	private double INTERES_VAR_BON = 0.6;
	
	public ResultatsController(final MainController controller, final Resultats view) {
		super(controller);
		this.view = view;
	}
	
	/**
	 * Calculates the hipoteca
	 */
	public void calcularHipoteca() {
		Hipoteca hipo = this.controller.getHipoteca();
		double bonificacio = this.calculateBonificacio(hipo.getDescompte());
		
		double menFixeNo = calculateMensualitat(INTERES_FIXA_NO_BON + bonificacio, hipo.getAnysHipoteca());
		double menFixeBon = calculateMensualitat(INTERES_FIXA_BON + bonificacio, hipo.getAnysHipoteca());
		double menVarNo = calculateMensualitat(INTERES_VAR_NO_BON + hipo.getEuribor() + bonificacio, hipo.getAnysHipoteca());
		double menVarBon = calculateMensualitat(INTERES_VAR_BON + hipo.getEuribor() + bonificacio, hipo.getAnysHipoteca());
		
		this.view.printResultats(menFixeNo, menFixeBon, menVarNo, menVarBon);
	}
	
	/**
	 * Calculates the "interes" to pay foreach year.
	 * @param interes 
	 * @param anys
	 * @return
	 */
	private double calculateAnys(double interes, int anys) {	
		
		double tempA = (1 + ((interes / 100) / 12));
		double tempB = 1 - Math.pow(tempA, -(anys*12));				
		
		return tempB / ((interes / 100) /12);		
	}	
	
	/**
	 * Calculates the "mensualitat", checks for interes==0
	 * @param interes
	 * @param anys
	 * @return
	 */
	private double calculateMensualitat(double interes, int anys) {	
		
		if(interes != 0) {
			double a = this.calculateAnys(interes, anys);
			return (controller.getHabitatge().getPreu() - controller.getHabitatge().getEstalvis())/a;
		}else {
			return (controller.getHabitatge().getPreu() - controller.getHabitatge().getEstalvis()) / (anys*12);
		}
				
	}
	
	/**
	 * 
	 * @param bonificacio
	 * @return
	 */
	private double calculateBonificacio(final String bonificacio) {
		if(bonificacio.equals(Hipoteca.BONIFICACIONS[Hipoteca.BON_COL_ESPECIAL_INDEX])) {
			return this.BON_COL_ESPECIAL;
		}else if(bonificacio.equals(Hipoteca.BONIFICACIONS[Hipoteca.BON_FUNCIONARI_INDEX])) {
			return this.BON_FUNCIONARI;
		}else if(bonificacio.equals(Hipoteca.BONIFICACIONS[Hipoteca.BON_MAJ_35_INDEX])) {
			return this.BON_MENOR_35;
		}
		
		return this.BON_CAP;
	}
	
	public void reset() {
		this.view.reset();
	}


	@Override
	public void onLoadedController() {
		// TODO Auto-generated method stub
		
	}
	

}
