package view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;


import controllers.DadesHabitatgeController;
import eventInterfaces.IOnClickAccept;
import view.interfaces.IView;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * View designed to enter theHabitatge data
 * @author manga
 *
 */
public class DadesHabitatge extends Group implements IView{
	private Composite cmpDades;
	private Composite cmdButtons;
	private Label lblPreuHabitatge;
	private Label lblEstalvis;
	private Label lblTipusResidencia;
	private Label lblEuroPreu;
	private Label lblEuroEstalvis;
	private Combo cmbTipusResidencia;
	private Text txtPreuHabitatge;
	private Text txtEstalvis;
	private Button btnAcceptar;
	
	/**
	 * Event List.
	 */
	private Set<IOnClickAccept> eventList;	
	

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DadesHabitatge(Composite parent, int style) {
		super(parent, SWT.SHADOW_ETCHED_IN);
		setText("Dades Habitatge");
		setLayout(new GridLayout(2, false));
		
		cmpDades = new Composite(this, SWT.NONE);
		cmpDades.setLayout(new GridLayout(3, false));
		GridData gd_cmpDades = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cmpDades.heightHint = 149;
		gd_cmpDades.widthHint = 324;
		cmpDades.setLayoutData(gd_cmpDades);
		
		lblPreuHabitatge = new Label(cmpDades, SWT.NONE);
		lblPreuHabitatge.setText("Preu Habitatge");
		
		txtPreuHabitatge = new Text(cmpDades, SWT.BORDER);
		txtPreuHabitatge.setToolTipText("Preu de l'habitatge a comprar");
		txtPreuHabitatge.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblEuroPreu = new Label(cmpDades, SWT.NONE);
		lblEuroPreu.setText("€");
		
		lblEstalvis = new Label(cmpDades, SWT.NONE);
		lblEstalvis.setText("Estalvis aportats");
		
		txtEstalvis = new Text(cmpDades, SWT.BORDER);
		txtEstalvis.setToolTipText("Estalvis aportats per la persona que vol comprar l'habitatge");
		txtEstalvis.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblEuroEstalvis = new Label(cmpDades, SWT.NONE);
		lblEuroEstalvis.setText("€");
		
		lblTipusResidencia = new Label(cmpDades, SWT.NONE);
		lblTipusResidencia.setText("Tipus residència");
		
		cmbTipusResidencia = new Combo(cmpDades, SWT.NONE);
		cmbTipusResidencia.setToolTipText("El tipus de residencia de l'habitatge a comprar");
		cmbTipusResidencia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(cmpDades, SWT.NONE);
		
		cmdButtons = new Composite(this, SWT.NONE);
		GridData gd_cmdButtons = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cmdButtons.heightHint = 68;
		gd_cmdButtons.widthHint = 127;
		cmdButtons.setLayoutData(gd_cmdButtons);
		
		btnAcceptar = new Button(cmdButtons, SWT.NONE);
		
		// subscribe to button click event
		btnAcceptar.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				// metemos el codigo que nos interesa
				onClickEvent();
			}
		});
		
		
		btnAcceptar.setBounds(27, 10, 90, 30);
		btnAcceptar.setText("ACCEPTAR");
		
		this.eventList = new HashSet<IOnClickAccept>();

	}
	
	/**
	 * Called to focus an error. Focus the error textbox and selects all the text if any
	 * @param errorField -> the field that contains the error
	 */
	public void focusError(DadesHabitatgeController.ErrorFields errorField) {
		
		switch(errorField){
			case PREU_HABITATGE:
				focusText(txtPreuHabitatge);				
				break;
			case ESTALVIS:
				focusText(txtEstalvis);
				break;
			case TIPUS:
				 focusCombo(cmbTipusResidencia);
			case NONE:
				return;
		}
		
	}
	
	private void focusText(final Text text) {
		text.forceFocus();
		text.selectAll();
	}
	
	private void focusCombo(final Combo combo) {
		combo.forceFocus();
		//combo.
	}
	
	/**
	 * Fetch the combobox with the desired data and puts
	 * the tipus[0] as index
	 * @param tipus
	 */
	public void fetchTipusHabitatge(final String[] tipus) {
			
		this.cmbTipusResidencia.setItems(tipus);
		
		try {
			this.cmbTipusResidencia.setText(this.cmbTipusResidencia.getItem(0));
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * event raised when the button {@link Button.btnAcceptar} is pressed
	 * 
	 */
	private void onClickEvent() {
		this.eventList.stream().forEach(e -> e.onClickAccept(this.txtPreuHabitatge.getText(), this.txtEstalvis.getText(), this.cmbTipusResidencia.getText()));
		
	}	
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	@Override
	public void disableAll() {
		this.setEnabled(false);
		this.txtEstalvis.setEditable(false);
		this.txtPreuHabitatge.setEditable(false);
		
	}
	
	@Override
	public void enableAll() {
		this.setEnabled(true);
		this.txtEstalvis.setEditable(true);
		this.txtPreuHabitatge.setEditable(true);
	}
	
	/**
	 * Method to subscribe to OnClickEvent if the subscriptor
	 * doesn't exist.
	 * @param subscriptor -> the event subscriptor
	 */
	public void subscribeToOnClick(final IOnClickAccept subscriptor) {
		if(this.eventList.contains(subscriptor) == false) {
			this.eventList.add(subscriptor);
		}
	}
	
	/**
	 * Method to unsubscribe to OnClickEvent
	 * @param subscriptor -> the event subscriptor
	 */
	public void unsubscribeToOnClick(final IOnClickAccept subscriptor) {
		if(this.eventList.contains(subscriptor)) {
			this.eventList.remove(subscriptor);
		}
	}
}
