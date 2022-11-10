package controllers;

import models.Habitatge;
import models.Hipoteca;
import view.Alertes;
import view.DadesHabitatge;
import view.DadesHipoteca;
import view.Resultats;

/**
 * Main controller class
 * To keep things easy this controller has the models {@link Habitatge} and {@link Hipoteca}
 * and methods to enable/disable different views. The flow is:
 * disableX -> calls to Controller X -> Controller X disables view X
 * 
 * @author manga
 *
 */
public class MainController {
	
	private DadesHabitatgeController habCont;
	private DadesHipotecaController hipCont;
	private ResultatsController resCont;
	private AlertaController alert;
	
	private Habitatge habitatge;
	private Hipoteca hipoteca;
	
	public MainController(final DadesHabitatge viewHab, final DadesHipoteca viewHipo, final Resultats resView, final Alertes alertView) {
		this.habCont = new DadesHabitatgeController(this, viewHab);
		this.hipCont = new DadesHipotecaController(this, viewHipo);
		this.resCont = new ResultatsController(this, resView);		
		this.alert = new AlertaController(this, alertView);
		
		this.habitatge = new Habitatge();
		this.hipoteca = new Hipoteca();
		
		// when everything is loaded, call the controllers onLoaded
		this.onLoadedController();
	}
	
	private void onLoadedController() {
		this.habCont.onLoadedController();
		this.hipCont.onLoadedController();
		this.resCont.onLoadedController();
		this.alert.onLoadedController();
	}
	
	public void writeMessage(final String message) {
		alert.writeMessage(message);
	}
	
	public void enableHipoteca() {
		this.hipCont.enableAll();
	}
	
	public void disableHipoteca() {
		this.hipCont.disableAll();
	}
	
	public void enableHabitage() {
		this.habCont.enableAll();
	}
	
	public void disableHabitatge() {
		this.habCont.disableAll();
	}
	
	public void calcularHipoteca() {
		this.resCont.calcularHipoteca();
	}
	
	public void resetResultats() {
		this.resCont.reset();
	}

	public DadesHabitatgeController getHabCont() {
		return habCont;
	}

	public void setHabCont(DadesHabitatgeController habCont) {
		this.habCont = habCont;
	}

	public DadesHipotecaController getHipCont() {
		return hipCont;
	}

	public void setHipCont(DadesHipotecaController hipCont) {
		this.hipCont = hipCont;
	}

	public AlertaController getAlert() {
		return alert;
	}

	public void setAlert(AlertaController alert) {
		this.alert = alert;
	}

	public Habitatge getHabitatge() {
		return habitatge;
	}

	public void setHabitatge(Habitatge habitatge) {
		this.habitatge = habitatge;
	}

	public Hipoteca getHipoteca() {
		return hipoteca;
	}

	public void setHipoteca(Hipoteca hipoteca) {
		this.hipoteca = hipoteca;
	}
	
	

}
