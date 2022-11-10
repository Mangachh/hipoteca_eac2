package main;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controllers.MainController;

import view.DadesHabitatge;
import org.eclipse.swt.SWT;
import view.DadesHipoteca;
import view.Resultats;
import view.Alertes;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.graphics.Point;

/**
 * Class for the app. It contains the main window
 * @author manga
 *
 */
public class App {
	
	protected Shell shlClculHipotecaMensual;
	
	// wiews
	private DadesHabitatge dashDadesHabitatge;
	private DadesHipoteca dashDadesHipoteca;
	private Resultats dashResultats;
	private Alertes dashAlertes;
	
	/**
	 * The main controller
	 */
	private MainController controller;
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			App window = new App();
			window.open();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlClculHipotecaMensual.open();
		shlClculHipotecaMensual.layout();
		
		// create the main controller and pass all the views
		controller = new MainController(this.dashDadesHabitatge, this.dashDadesHipoteca, this.dashResultats, this.dashAlertes);
		
		while (!shlClculHipotecaMensual.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlClculHipotecaMensual = new Shell();
		shlClculHipotecaMensual.setMinimumSize(new Point(545, 717));
		shlClculHipotecaMensual.setMaximumSize(new Point(545, 717));
		shlClculHipotecaMensual.setSize(545, 717);
		shlClculHipotecaMensual.setText("Càlcul Hipoteca Mensual per Lluís Cobos Aumatell");
		shlClculHipotecaMensual.setLayout(null);
		
		dashDadesHabitatge = new DadesHabitatge(shlClculHipotecaMensual, SWT.NONE);
		dashDadesHabitatge.setBounds(10, 10, 507, 182);
		
		dashDadesHipoteca = new DadesHipoteca(shlClculHipotecaMensual, SWT.NONE);
		dashDadesHipoteca.setBounds(10, 208, 507, 174);
		
		dashResultats = new Resultats(shlClculHipotecaMensual, SWT.NONE);
		dashResultats.setBounds(10, 388, 507, 210);
		RowLayout rowLayout = (RowLayout) dashResultats.getLayout();
		rowLayout.fill = true;
		
		dashAlertes = new Alertes(shlClculHipotecaMensual, SWT.NONE);
		dashAlertes.setBounds(10, 605, 507, 55);

	}
}
