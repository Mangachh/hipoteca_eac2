package controllers;

/**
 * Base class for the controllers
 * @author manga
 *
 */
public abstract class ControllerBase {
	
	/**
	 * The main controller
	 */
	protected MainController controller;
	
	/**
	 * Constructor
	 * @param controller -> main controller of the app
	 */
	public ControllerBase(final MainController controller) {
		this.controller = controller;
	}
	
	/**
	 * called when the main controller loaded all the resources
	 */
	public abstract void onLoadedController();
	
	
}
