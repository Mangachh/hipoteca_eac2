package controllers;

import view.Alertes;

/**
 * The controller for the alert
 * @author manga
 *
 */
public class AlertaController extends ControllerBase{
	private Alertes alert;
	
	public AlertaController(final MainController controller, final Alertes alt) {
		super(controller);
		this.alert = alt;
	}
	
	/**
	 * Writes a message
	 * @param message
	 */
	public void writeMessage(final String message) {
		this.alert.writeMessage(message);
	}

	@Override
	public void onLoadedController() {
		// TODO Auto-generated method stub
		
	}
	
}
