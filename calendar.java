import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;


public class calendar extends Composite {
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public calendar(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Composite composite = new Composite(this, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 55);
		fd_composite.left = new FormAttachment(0);
		fd_composite.right = new FormAttachment(100);
		composite.setLayoutData(fd_composite);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		fd_composite.bottom = new FormAttachment(100, -182);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.top = new FormAttachment(composite, 6);
		
		Button btnDentist = new Button(composite, SWT.NONE);
		btnDentist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		btnDentist.setBounds(0, 0, 450, 64);
		btnDentist.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnDentist.setText("DENTIST");
		fd_composite_1.left = new FormAttachment(0);
		fd_composite_1.right = new FormAttachment(100);
		composite_1.setLayoutData(fd_composite_1);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		fd_composite_1.bottom = new FormAttachment(composite_2, -22);
		
		Button btnHygienist = new Button(composite_1, SWT.NONE);
		btnHygienist.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnHygienist.setBounds(0, 10, 450, 64);
		btnHygienist.setText("HYGIENIST");
		FormData fd_composite_2 = new FormData();
		fd_composite_2.top = new FormAttachment(100, -90);
		fd_composite_2.left = new FormAttachment(0);
		fd_composite_2.right = new FormAttachment(100);
		fd_composite_2.bottom = new FormAttachment(100, -26);
		composite_2.setLayoutData(fd_composite_2);
		
		Button btnBoth = new Button(composite_2, SWT.NONE);
		btnBoth.setBounds(0, 0, 450, 64);
		btnBoth.setText("BOTH");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
