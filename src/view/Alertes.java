package view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import view.interfaces.IView;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

/**
 * View for the Group "Alertes"
 * Shows a message in it's textbox
 * @author manga
 *
 */
public class Alertes extends Group implements IView {
	private Text txtDisplayer;

	/**
	 * Default constructor
	 * @param parent
	 * @param style
	 */
	public Alertes(Composite parent, int style) {
		super(parent, style);
		setEnabled(false);
		setText("Alertes");
		setLayout(new FormLayout());
		
		txtDisplayer = new Text(this, SWT.BORDER);
		FormData fd_txtDisplayer = new FormData();
		fd_txtDisplayer.bottom = new FormAttachment(100, -6);
		fd_txtDisplayer.left = new FormAttachment(0, 6);
		fd_txtDisplayer.right = new FormAttachment(0, 487);
		txtDisplayer.setLayoutData(fd_txtDisplayer);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * Writes a message in the textbox
	 * @param message -> message to write
	 */
	public void writeMessage(final String message) {
		this.txtDisplayer.setText(message);	
		
	}

	@Override
	public void enableAll() {
		this.setEnabled(true);
		//this.txtDisplayer.setEnabled(false);
		
	}

	@Override
	public void disableAll() {
		//this.txtDisplayer.setEnabled(false);
		this.txtDisplayer.setEnabled(false);
	}
	
	
}
