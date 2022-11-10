package view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridLayout;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import controllers.DadesHipotecaController;
import eventInterfaces.IOnClickCalcular;
import eventInterfaces.IOnClickRestablir;
import view.interfaces.IView;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * View for the input of the Hipoteca Data
 * @author manga
 *
 */
public class DadesHipoteca extends Group implements IView{
	private Composite composite;
	private Composite composite_1;
	private Label lblDescompte;
	private Label lblEdat;
	private Label lblAnys;
	private Label lblEuribor;
	private Label lblPercentatge;
	private Combo cmbDescompte;
	private Text txtEdat;
	private Text txtAnys;
	private Text txtPercentatge;
	private Button btnCalcular;
	private Button btnRestablir;
	
	/**
	 * Events
	 */
	private Set<IOnClickCalcular> calcularEventList;
	private Set<IOnClickRestablir> restablirEventList;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DadesHipoteca(Composite parent, int style) {
		super(parent, style);
		setEnabled(false);
		setText("Dades Hipoteca");
		setLayout(new GridLayout(2, false));
		
		composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 141;
		gd_composite.widthHint = 320;
		composite.setLayoutData(gd_composite);
		
		lblDescompte = new Label(composite, SWT.NONE);
		lblDescompte.setText("Descompte");
		
		cmbDescompte = new Combo(composite, SWT.NONE);
		cmbDescompte.setToolTipText("Descompte a aplicar, CAP per defecte");
		cmbDescompte.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		lblEdat = new Label(composite, SWT.NONE);
		lblEdat.setText("Edat Client");
		
		txtEdat = new Text(composite, SWT.BORDER);
		txtEdat.setToolTipText("Edat del client que vol comprar l'habitatge");
		txtEdat.setEditable(false);
		txtEdat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		lblAnys = new Label(composite, SWT.NONE);
		lblAnys.setText("Anys Hipoteca");
		
		txtAnys = new Text(composite, SWT.BORDER);
		txtAnys.setToolTipText("Anys de l'hipoteca a contractar. Ha de ser un nombre enter");
		txtAnys.setEditable(false);
		txtAnys.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		lblEuribor = new Label(composite, SWT.NONE);
		lblEuribor.setText("Euribor Actual");
		
		txtPercentatge = new Text(composite, SWT.BORDER);
		txtPercentatge.setToolTipText("Euribor a aplicar a les mensualites no fixes");
		txtPercentatge.setEditable(false);
		txtPercentatge.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblPercentatge = new Label(composite, SWT.NONE);
		lblPercentatge.setText("%");
		
		composite_1 = new Composite(this, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.verticalSpacing = 12;
		composite_1.setLayout(gl_composite_1);
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.heightHint = 131;
		gd_composite_1.widthHint = 137;
		composite_1.setLayoutData(gd_composite_1);
		
		btnCalcular = new Button(composite_1, SWT.NONE);
		
		// subscribe to onclick button Calcular
		btnCalcular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClickCalcular();
			}
		});
		btnCalcular.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		btnCalcular.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnCalcular.setText("CALCULAR");
		
		btnRestablir = new Button(composite_1, SWT.NONE);
		
		// subscribe to onclick button Calcular
		btnRestablir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClickRestablir();
			}
		});
		
		btnRestablir.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnRestablir.setText("RESTABLIR");
		
		calcularEventList = new HashSet<IOnClickCalcular>();
		restablirEventList = new HashSet<IOnClickRestablir>();

	}
	
	/**
	 * Event raised when the {@link Button.btnCalcular} is pressed
	 */
	private void onClickCalcular() {
		calcularEventList.stream().forEach(e -> e.OnClickCalcular(this.cmbDescompte.getText(), 
																  this.txtEdat.getText(), 
																  this.txtAnys.getText(),
																  this.txtPercentatge.getText().replace(",", ".")));
	}
	
	/**
	 * Event raised when the {@linkplain Button.btnRestablir} is pressed
	 */
	private void onClickRestablir() {
		restablirEventList.stream().forEach(e -> e.OnClickRestablir());
	}
	
	@Override
	public void disableAll() {
		this.setEnabled(false);
		this.txtAnys.setEditable(false);
		this.txtEdat.setEditable(false);
		this.txtPercentatge.setEditable(false);
		this.cmbDescompte.setText("");
	}
	
	@Override
	public void enableAll() {
		this.setEnabled(true);
		this.txtAnys.setEditable(true);
		this.txtEdat.setEditable(true);
		this.txtPercentatge.setEditable(true);
	}
	
	public void fetchBonificacions(final String[] bonificacions) {
		this.cmbDescompte.setItems(bonificacions);
		this.cmbDescompte.setText(this.cmbDescompte.getItem(0));
	}
	
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * Reset's the combobox and textbox
	 */
	public void reset() {
		this.txtAnys.setText("");
		this.txtEdat.setText("");
		this.txtPercentatge.setText("");
	}
	
	
	/**
	 * Subscribes to event {@link onClickCalcular}
	 * @param subscriptor -> subscriptr
	 */
	public void subscribeToOnClickCalcular(final IOnClickCalcular subscriptor) {
		this.calcularEventList.add(subscriptor);
	}
	
	/**
	 * UnSubscribes to event {@link onClickCalcular}
	 * @param subscriptor -> subscriptr
	 */
	public void unsubscribeToOnClickCalcuar(final IOnClickCalcular subscriptor) {
		this.calcularEventList.remove(subscriptor);
	}
	
	/**
	 * Subscribes to event {@link onClickRestablir}
	 * @param subscriptor -> subscriptr
	 */
	public void subscribeToOnClickRestablir(final IOnClickRestablir subscriptor) {
		this.restablirEventList.add(subscriptor);
	}
	
	/**
	 * UnSubscribes to event {@link onClickRestablir}
	 * @param subscriptor -> subscriptr
	 */
	public void unsubscribeToOnClickRestablir(final IOnClickRestablir subscriptor) {
		this.restablirEventList.remove(subscriptor);
	}
	
	/**
	 * Focus the textbox if it contains and error and select the text if any
	 * @param errorField -> the field that has an error
	 */
	public void focusError(DadesHipotecaController.ErrorFields errorField) {		
		switch(errorField){
		case EDAT:
			focusText(this.txtEdat);
			break;
		case ANYS_HIPOTECA:
			focusText(this.txtAnys);
			break;
		case EURIBOR:
			focusText(this.txtPercentatge);
			break;
		case DESCOMPTE:
			focusCombo(this.cmbDescompte);
		case NONE:
			return;
		}			
	}
	
	/**
	 * Focus the text control
	 * @param text
	 */
	private void focusText(final Text text) {
		text.forceFocus();
		text.selectAll();
	}
	
	/**
	 * Focus the combo control
	 * @param combo
	 */
	private void focusCombo(final Combo combo) {
		combo.forceFocus();
	}
	
	

}
