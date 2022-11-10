package controllers;

import eventInterfaces.IOnClickAccept;
import eventInterfaces.IOnClickCalcular;
import eventInterfaces.IOnClickRestablir;
import models.Habitatge;
import models.Hipoteca;
import view.DadesHipoteca;

/**
 * Controller for the Hipoteca
 * @author manga
 *
 */
public class DadesHipotecaController extends ControllerBase implements IOnClickCalcular, IOnClickRestablir, IOnClickAccept {
	
	
	private final int EDAT_MIN = 18;
	private final int EDAT_MAX = 100;
	private final int MAX_ANYS_HIPOTECA = 75;
	private final int MAX_ANYS_HIP_HABITUAL = 30;
	private final int MAX_ANYS_HIP_SECONDARIA = 25;
	
	//messages
	private final static String FIELD_NO_INT = "El camp ha de ser un enter";
	private final static String FIELD_NO_DOUBLE = "El camp ha de ser un número";
	private final static String FIELD_EMPTY = "El camp \"%s\" está buit";
	private final static String ERROR_TYPE = "El tipus de Descompte és incorrecte";
	private final static String EDAT_NO_BET = "El camp edat ha d'estar comprés entre %d i %d";
	private final static String ANYS_POSITIU = "Els anys d'hipoteca han de ser positius";
	private final static String DADES_CORRECTES = "Dades introduïdes correctament";
	private final static String HIP_TOO_LONG = "La suma d'edat + anys ha de ser menor o igual a 75 ";
	private final static String HIP_TOO_LONG_HAB = "L'hipoteca \"%s\" ha de ser com a màxim %d anys";
	
	private DadesHipoteca view;	
	
	
	public enum ErrorFields{
		EDAT, ANYS_HIPOTECA, EURIBOR, DESCOMPTE, NONE
	}
	
	public DadesHipotecaController(final MainController controller, final DadesHipoteca view) {
		super(controller);
		this.view = view;
		// this.view.fetchBonificacions(Hipoteca.BONIFICACIONS);			
	}
	
	/**
	 * Checks for errors in the fields
	 * @param edat
	 * @param anysHipoteca
	 * @param euribor
	 * @return ErrorFields
	 */
	private ErrorFields checkData(final String descompte, final String edat, final String anysHipoteca, final String euribor) {
		
		Hipoteca hip = controller.getHipoteca();
		
		// checks de nulls
		if(checkNullString(descompte)) {
			controller.writeMessage(String.format(FIELD_EMPTY, "Descompte"));
			return ErrorFields.DESCOMPTE;
		}else if(checkNullString(edat)) {
			controller.writeMessage(String.format(FIELD_EMPTY, "Edat"));
			return ErrorFields.EDAT;
		}else if(checkNullString(anysHipoteca)){
			controller.writeMessage(String.format(FIELD_EMPTY, "Anys Hipoteca"));
			return ErrorFields.ANYS_HIPOTECA;
		}else if(checkNullString(euribor)) {
			controller.writeMessage(String.format(FIELD_EMPTY, "Euribor"));
			return ErrorFields.EURIBOR;
		}
		
		// check tipus
		if(checkDiscount(descompte) == false) {
			return ErrorFields.DESCOMPTE;
		}
		
		// cheks números
		if(checkEdat(edat) == false) {
			return ErrorFields.EDAT;
		}else if(checkAnys(anysHipoteca, hip) == false) {
			return ErrorFields.ANYS_HIPOTECA;
		}else if(checkEuribor(euribor, hip) == false) {
			return ErrorFields.EURIBOR;
		}
		
		// check de vivienda
		if(this.checkHabitatgeAnys(hip.getAnysHipoteca(), controller.getHabitatge().getTipus()) == false) {
			return ErrorFields.ANYS_HIPOTECA;
		}
		
		// checks de edat + hipoteca > 75
		if(this.checkEdatAnysHipoteca(hip.getAnysHipoteca(), hip.getEdat()) == false) {
			controller.writeMessage(HIP_TOO_LONG);
			return ErrorFields.ANYS_HIPOTECA;
		}
		
		controller.writeMessage(DADES_CORRECTES);
		return ErrorFields.NONE;
	}
	
	
	private boolean checkDiscount(final String tipus) {
		for(String t : Hipoteca.BONIFICACIONS) {
			if(tipus.equals(t)) {
				return true;
			}
		}
		
		controller.writeMessage(ERROR_TYPE);
		return false;
	}
	
	private boolean checkHabitatgeAnys(int anysHipoteca, final String tipus) {
		if(tipus.equals(Habitatge.TIPUS_HABITATGE[Habitatge.HAB_HABITUAL_INDEX]) &&
			anysHipoteca > MAX_ANYS_HIP_HABITUAL) {
			controller.writeMessage(String.format(HIP_TOO_LONG_HAB, Habitatge.TIPUS_HABITATGE[Habitatge.HAB_HABITUAL_INDEX], MAX_ANYS_HIP_HABITUAL));
			return false;
		}else if(tipus.equals(Habitatge.TIPUS_HABITATGE[Habitatge.HAB_SEC_INDEX]) &&
				anysHipoteca > MAX_ANYS_HIP_SECONDARIA) {
			controller.writeMessage(String.format(HIP_TOO_LONG_HAB, Habitatge.TIPUS_HABITATGE[Habitatge.HAB_HABITUAL_INDEX], MAX_ANYS_HIP_SECONDARIA));
			return false;
		}
		
		return true;
	}
	
	private boolean checkEdatAnysHipoteca(int edat, int anysHipoteca) {
		return edat + anysHipoteca <= MAX_ANYS_HIPOTECA;
	}
	
	private boolean checkEdat(final String edat) {
		int ed;
		
		try {
			ed = Integer.parseInt(edat);
		}catch(NumberFormatException e) {
			controller.writeMessage(FIELD_NO_INT);
			return false;
		}
		
		// miramos si es positivo y menor de 100
		if(ed >= EDAT_MIN && ed < EDAT_MAX) {
			this.controller.getHipoteca().setEdat(ed);
			return true;
		}		
		
		controller.writeMessage(String.format(EDAT_NO_BET, EDAT_MIN, EDAT_MAX));
		return false;
	}
	
	private boolean checkAnys(final String anys, final Hipoteca hip) {
		int an;
		
		try {
			an = Integer.parseInt(anys);
		}catch(NumberFormatException e) {
			controller.writeMessage(FIELD_NO_INT);
			return false;
		}
		
		// miramos si es positivo y menor de 100
		if(an > 0) {
			hip.setAnysHipoteca(an);
			return true;
		}
		
		controller.writeMessage(ANYS_POSITIU);
		return false;
	}
	
	private boolean checkEuribor(final String euribor, final Hipoteca hip) {
		double eur;

		try {
			eur = Double.parseDouble(euribor);
		}catch(NumberFormatException e) {
			controller.writeMessage(FIELD_NO_DOUBLE);
			return false;
		}
		
		hip.setEuribor(eur);
		return true;
		
	}	
	
	private boolean checkNullString(final String toCheck) {
		return (toCheck == null || toCheck == "");
	}
	
	
	
	
	// Enables-disables
	
	public void enableAll() {
		this.view.enableAll();		
		this.view.fetchBonificacions(Hipoteca.BONIFICACIONS);
	}
	
	public void disableAll() {
		this.view.disableAll();
	}
	
	
	// Event subscriptors
	
	public void subscribeToOnClickRestablir(IOnClickRestablir subscriptor) {
		this.view.subscribeToOnClickRestablir(subscriptor);
	}
	
	public void unsubscribeToOnClickRestablir(IOnClickRestablir subscriptor) {
		this.view.unsubscribeToOnClickRestablir(subscriptor);
	}
	
	public void subscribeToOnClickCalcular(IOnClickCalcular subscriptor) {
		this.view.subscribeToOnClickCalcular(subscriptor);
	}
	
	public void unsubscribeToOnClickCalcular(IOnClickCalcular subscriptor) {
		this.view.unsubscribeToOnClickCalcuar(subscriptor);
	}
	
	
	// Event methods!!
	
	@Override
	public void OnClickCalcular(String descompte, String edad, String anysHipoteca, String euribor) {
		ErrorFields error = this.checkData(descompte, edad, anysHipoteca, euribor);
		
		if(error != ErrorFields.NONE) {
			// swtich de la view
			this.view.focusError(error);
			return;
		}
		
		controller.getHipoteca().setDescompte(descompte);		
		controller.calcularHipoteca();
	}
	
	@Override
	public void OnClickRestablir() {
		// TODO Auto-generated method stub
		this.view.reset();
		this.disableAll();
		
		this.controller.enableHabitage();
		this.controller.resetResultats();
		this.controller.writeMessage("Dades Restablertes");
		
	}

	@Override
	public void onClickAccept(String preu, String estalvis, String tipus) {
		this.enableAll();		
	}

	@Override
	public void onLoadedController() {
		// subscribimos al evento calcular
		this.view.subscribeToOnClickCalcular(this);
				
		// suscribimos al evento restablir
		this.view.subscribeToOnClickRestablir(this);
				
		// suscibimos al evento acceptar del controlador dadeshabitatge
		//this.controller.getHabCont().subscribeToOnClickAccept(this);
		
	}
}
