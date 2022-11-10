package controllers;


import eventInterfaces.IOnClickAccept;
import eventInterfaces.IOnClickRestablir;
import models.Habitatge;
import view.DadesHabitatge;

/**
 * Controller for the input DadesHabitatge
 * @author manga
 *
 */
public class DadesHabitatgeController extends ControllerBase implements IOnClickAccept, IOnClickRestablir{
	
	/**
	 * Posible error fields
	 * @author manga
	 *
	 */
	public enum ErrorFields{
		PREU_HABITATGE, ESTALVIS, TIPUS, NONE
	}
	
	private final static String  ERROR_NO_NUMBER = "La quantitat introduïda no és un número";
	private final static String ERROR_NO_ESTALVIS_SUFICIENTS = "La quantitat d'estalvis ha de ser major o igual al 20% del preu de l'habitatge";
	private final static String ERROR_EMPTY_FIELD = "El camp %s está buit";
	private final static String DADES_CORRECTES = "Dades introduides correctament";
	private final static String ERROR_TYPE = "El tipus d'habitatge és desconegut";
	private final static double PERCENTATGE = 20;
	
	private DadesHabitatge view;	 
	
	public DadesHabitatgeController(final MainController controller, final DadesHabitatge view) {
		super(controller);
		this.view = view;
		this.view.fetchTipusHabitatge(Habitatge.TIPUS_HABITATGE);
	}		

	
	/**
	 * Check the data and if it's incorrect returns the field in wich the error is
	 * @param preu -> price
	 * @param estalvis -> savings 
	 * @param tipus -> type
	 * @return -> error field, none if there's no error
	 */
	private ErrorFields checkData(String preu, String estalvis, String tipus) {
		// evitamos errores de coma
		preu = preu.replace(",", ".");
		estalvis = estalvis.replace(",", ".");
		
		ErrorFields temp = checkEmptyValues(preu, estalvis);		
		
		if(temp != ErrorFields.NONE) {
			return temp;
		}
		
		if(checkPreu(preu) == false) {
			return ErrorFields.PREU_HABITATGE;
		}else if (checkEstalvis(estalvis) == false) {
			return ErrorFields.ESTALVIS;
		}	
		
		if(checkTipus(tipus) == false) {
			return ErrorFields.TIPUS;
		}
		
		//double percentatge = controller.getHabitatge().getPreu() * PERCENTATGE;
		if(PERCENTATGE> (controller.getHabitatge().getEstalvis()/controller.getHabitatge().getPreu()) * 100) {
			controller.writeMessage(ERROR_NO_ESTALVIS_SUFICIENTS);
			return ErrorFields.ESTALVIS;
		}
		
		controller.getHabitatge().setTipus(tipus);
		
		
		return ErrorFields.NONE;
	}
	
	/**
	 * Checks that the "preu" field is a double and if that's the case
	 * puts the price into {@link Habitatge.setPreu()}
	 * @param preu -> price to check
	 * @return -> None if it correct, ERROR_NO_NUMBER if it is not a double
	 */
	private boolean checkPreu(final String preu) {
		double price;
		try {
			price = Double.valueOf(preu);
			
		}catch (NumberFormatException e) {
			controller.writeMessage(ERROR_NO_NUMBER);
			return false;
		}
		
		controller.getHabitatge().setPreu(price);		
		return true;
		
	}
	
	/**
	 * Checks that the "estalvis" field is a double and if that's the case
	 * puts the price into {@link Habitatge.setEstalvis()}
	 * @param preu -> price to check
	 * @return -> None if it correct, ERROR_NO_NUMBER if it is not a double
	 */
	private boolean checkEstalvis(final String estalvis) {
		double savings;
		try {
			savings = Double.valueOf(estalvis);
			
		}catch (NumberFormatException e) {
			controller.writeMessage(ERROR_NO_NUMBER);
			return false;
		}
		
		controller.getHabitatge().setEstalvis(savings);		
		return true;
		
	}
	
	private boolean checkTipus(final String tipus) {
		for(String t : Habitatge.TIPUS_HABITATGE) {
			if(t.equals(tipus)) {
				return true;
			}
		}
		
		controller.writeMessage(ERROR_TYPE);
		return false;
	}
	
	/**
	 * Check for empty strings.
	 * @param preu 
	 * @param estalvis
	 * @return ErrorFields.PREU_HABITATGE if "preu" is empty
	 * 		   ErrorFields.ESTALVIS if "estalvis" is empty
	 * 		   ErrorFields.NONE if there are none empty strings
	 */
	private ErrorFields checkEmptyValues(String preu, String estalvis) {
		if(preu == null || preu == "") {
			controller.writeMessage(String.format(ERROR_EMPTY_FIELD, "preu"));
			return ErrorFields.PREU_HABITATGE;
		}else if (estalvis == null || estalvis == "") {
			controller.writeMessage(String.format(ERROR_EMPTY_FIELD, "estalvis"));
			return ErrorFields.ESTALVIS;
		}
		
		return ErrorFields.NONE;
	}
	
	/**
	 * enables the view
	 */
	public void enableAll() {
		this.view.enableAll();
	}
	
	/**
	 * disables the view
	 */
	public void disableAll() {
		this.view.disableAll();
	}
	

	
	@Override
	public void onClickAccept(final String preu, final String estalvis, final String tipus) {
		
		ErrorFields error = this.checkData(preu, estalvis, tipus);
		
		if(error != ErrorFields.NONE) {
			this.view.focusError(error);
		}else {
			
			controller.enableHipoteca();		
			this.disableAll();
			controller.writeMessage(DADES_CORRECTES);
		}
	}


	@Override
	public void OnClickRestablir() {
		this.enableAll();
		
	}

	@Override
	public void onLoadedController() {
		this.view.subscribeToOnClick(this);
		//this.controller.getHipCont().subscribeToOnClickRestablir(this);
		
	}


	
	
	
	
	
}
