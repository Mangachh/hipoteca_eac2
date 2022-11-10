package view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;

import view.interfaces.IView;

import org.eclipse.swt.layout.GridData;

public class Resultats extends Group implements IView {
	private Text txtFixaNoBon;
	private Text txtFixaBon;
	private Text txtVarNoBon;
	private Text txtVarBon;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Resultats(Composite parent, int style) {
		super(parent, style);
		setEnabled(false);
		setLayout(new RowLayout(SWT.VERTICAL));
		
		Label lblPossiblesHipoteques = new Label(this, SWT.CENTER);
		lblPossiblesHipoteques.setLayoutData(new RowData(488, SWT.DEFAULT));
		lblPossiblesHipoteques.setAlignment(SWT.CENTER);
		lblPossiblesHipoteques.setText("POSSIBLES HIPOTEQUES");
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		composite.setLayoutData(new RowData(489, 158));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Fixa sense modificar");
		new Label(composite, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("Fixa modificada");
		new Label(composite, SWT.NONE);
		
		txtFixaNoBon = new Text(composite, SWT.BORDER);
		txtFixaNoBon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("€/mes");
		
		txtFixaBon = new Text(composite, SWT.BORDER);
		txtFixaBon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setText("€/mes");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setText("Variable sense modificar");
		new Label(composite, SWT.NONE);
		
		Label lblNewLabel_5 = new Label(composite, SWT.NONE);
		lblNewLabel_5.setText("Variable modificada");
		new Label(composite, SWT.NONE);
		
		txtVarNoBon = new Text(composite, SWT.BORDER);
		txtVarNoBon.setTextDirection(-33554432);
		txtVarNoBon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_6 = new Label(composite, SWT.NONE);
		lblNewLabel_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_6.setText("€/mes");
		
		txtVarBon = new Text(composite, SWT.BORDER);
		txtVarBon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_7 = new Label(composite, SWT.NONE);
		lblNewLabel_7.setText("€/mes");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void printResultats(double fixa_no, double fixa_bon, double var_no, double var_bon) {
		this.txtFixaNoBon.setText(String.format("%.2f", fixa_no));
		this.txtFixaBon.setText(String.format("%.2f", fixa_bon));
		this.txtVarNoBon.setText(String.format("%.2f", var_no));
		this.txtVarBon.setText(String.format("%.2f", var_bon));
	}
	
	public void reset() {
		this.txtFixaNoBon.setText("");
		this.txtFixaBon.setText("");
		this.txtVarNoBon.setText("");
		this.txtVarBon.setText("");
	}

	@Override
	public void enableAll() {
		this.setEnabled(true);
		
	}

	@Override
	public void disableAll() {
		this.setEnabled(false);
		
	}

}
