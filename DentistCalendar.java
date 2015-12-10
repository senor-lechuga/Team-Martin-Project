import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class DentistCalendar extends Composite {
private final int MAX_CLICKS = 3;
private int clickCounter = 0;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DentistCalendar(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setToolTipText("9-9:20");
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 20);
		fd_composite.right = new FormAttachment(0, 229);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new GridLayout(3, false));
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new GridLayout(3, false));
		FormData fd_composite_1 = new FormData();
		fd_composite_1.top = new FormAttachment(composite, 2);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button button_4 = new Button(composite, SWT.CHECK);
		GridData gd_button_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_4.heightHint = 13;
		button_4.setLayoutData(gd_button_4);
		button_4.setText("9:00-9:20");
		fd_composite_1.left = new FormAttachment(0);
		fd_composite_1.right = new FormAttachment(100);
		composite_1.setLayoutData(fd_composite_1);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		fd_composite_1.bottom = new FormAttachment(composite_2, -2);
		composite_2.setLayout(new GridLayout(3, false));
		FormData fd_composite_2 = new FormData();
		fd_composite_2.left = new FormAttachment(0);
		fd_composite_2.right = new FormAttachment(100);
		fd_composite_2.bottom = new FormAttachment(100, -602);
		fd_composite_2.top = new FormAttachment(0, 44);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Button button = new Button(composite_1, SWT.CHECK);
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.heightHint = 14;
		button.setLayoutData(gd_button);
		button.setText("9:20-9:40");
		composite_2.setLayoutData(fd_composite_2);
		
		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayout(new GridLayout(3, false));
		FormData fd_composite_3 = new FormData();
		fd_composite_3.left = new FormAttachment(0);
		fd_composite_3.right = new FormAttachment(100);
		fd_composite_3.bottom = new FormAttachment(composite_2, 21, SWT.BOTTOM);
		fd_composite_3.top = new FormAttachment(composite_2, 1);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		
		Button button_1 = new Button(composite_2, SWT.CHECK);
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.heightHint = 14;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("9:40-10:00");
		composite_3.setLayoutData(fd_composite_3);
		
		Composite composite_4 = new Composite(this, SWT.NONE);
		composite_4.setLayout(new GridLayout(3, false));
		FormData fd_composite_4 = new FormData();
		fd_composite_4.left = new FormAttachment(0);
		fd_composite_4.right = new FormAttachment(100);
		fd_composite_4.bottom = new FormAttachment(composite_3, 24, SWT.BOTTOM);
		fd_composite_4.top = new FormAttachment(composite_3, 4);
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		Button button_2 = new Button(composite_3, SWT.CHECK);
		GridData gd_button_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_2.heightHint = 16;
		button_2.setLayoutData(gd_button_2);
		button_2.setText("10:00-10:20");
		composite_4.setLayoutData(fd_composite_4);
		
		Composite composite_5 = new Composite(this, SWT.NONE);
		composite_5.setLayout(new GridLayout(3, false));
		FormData fd_composite_5 = new FormData();
		fd_composite_5.left = new FormAttachment(0);
		fd_composite_5.right = new FormAttachment(100);
		fd_composite_5.bottom = new FormAttachment(composite_4, 22, SWT.BOTTOM);
		fd_composite_5.top = new FormAttachment(composite_4, 2);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		
		Button button_3 = new Button(composite_4, SWT.CHECK);
		GridData gd_button_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_3.heightHint = 14;
		button_3.setLayoutData(gd_button_3);
		button_3.setText("10:20-10:40");
		composite_5.setLayoutData(fd_composite_5);
		
		Composite composite_6 = new Composite(this, SWT.NONE);
		composite_6.setLayout(new GridLayout(3, false));
		FormData fd_composite_6 = new FormData();
		fd_composite_6.left = new FormAttachment(0);
		fd_composite_6.right = new FormAttachment(100);
		fd_composite_6.bottom = new FormAttachment(composite_5, 23, SWT.BOTTOM);
		fd_composite_6.top = new FormAttachment(composite_5, 3);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		
		Button btnCheckButton = new Button(composite_5, SWT.CHECK);
		GridData gd_btnCheckButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnCheckButton.heightHint = 15;
		btnCheckButton.setLayoutData(gd_btnCheckButton);
		btnCheckButton.setText("10:40-11:00");
		composite_6.setLayoutData(fd_composite_6);
		
		Composite composite_7 = new Composite(this, SWT.NONE);
		composite_7.setLayout(new GridLayout(3, false));
		FormData fd_composite_7 = new FormData();
		fd_composite_7.left = new FormAttachment(0);
		fd_composite_7.right = new FormAttachment(100);
		fd_composite_7.bottom = new FormAttachment(composite_6, 22, SWT.BOTTOM);
		fd_composite_7.top = new FormAttachment(composite_6, 2);
		new Label(composite_6, SWT.NONE);
		new Label(composite_6, SWT.NONE);
		
		Button button_6 = new Button(composite_6, SWT.CHECK);
		GridData gd_button_6 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_6.heightHint = 13;
		button_6.setLayoutData(gd_button_6);
		button_6.setText("11:00-11:20");
		composite_7.setLayoutData(fd_composite_7);
		
		Composite composite_8 = new Composite(this, SWT.NONE);
		composite_8.setLayout(new GridLayout(3, false));
		FormData fd_composite_8 = new FormData();
		fd_composite_8.left = new FormAttachment(0);
		fd_composite_8.right = new FormAttachment(100);
		fd_composite_8.bottom = new FormAttachment(composite_7, 23, SWT.BOTTOM);
		fd_composite_8.top = new FormAttachment(composite_7, 3);
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);
		
		Button button_7 = new Button(composite_7, SWT.CHECK);
		GridData gd_button_7 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_7.heightHint = 11;
		button_7.setLayoutData(gd_button_7);
		button_7.setText("11:20-11:40");
		composite_8.setLayoutData(fd_composite_8);
		
		Composite composite_9 = new Composite(this, SWT.NONE);
		composite_9.setLayout(new GridLayout(3, false));
		FormData fd_composite_9 = new FormData();
		fd_composite_9.left = new FormAttachment(0);
		fd_composite_9.right = new FormAttachment(100);
		fd_composite_9.bottom = new FormAttachment(composite_8, 23, SWT.BOTTOM);
		fd_composite_9.top = new FormAttachment(composite_8, 3);
		new Label(composite_8, SWT.NONE);
		new Label(composite_8, SWT.NONE);
		
		Button button_8 = new Button(composite_8, SWT.CHECK);
		GridData gd_button_8 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_8.heightHint = 13;
		button_8.setLayoutData(gd_button_8);
		button_8.setText("11:40-12:00");
		composite_9.setLayoutData(fd_composite_9);
		
		Composite composite_10 = new Composite(this, SWT.NONE);
		composite_10.setLayout(new GridLayout(3, false));
		FormData fd_composite_10 = new FormData();
		fd_composite_10.left = new FormAttachment(0);
		fd_composite_10.right = new FormAttachment(100);
		fd_composite_10.bottom = new FormAttachment(composite_9, 22, SWT.BOTTOM);
		fd_composite_10.top = new FormAttachment(composite_9, 2);
		new Label(composite_9, SWT.NONE);
		new Label(composite_9, SWT.NONE);
		
		Button button_9 = new Button(composite_9, SWT.CHECK);
		GridData gd_button_9 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_9.heightHint = 12;
		button_9.setLayoutData(gd_button_9);
		button_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_9.setText("12:00-12:20");
		composite_10.setLayoutData(fd_composite_10);
		
		Composite composite_11 = new Composite(this, SWT.NONE);
		composite_11.setLayout(new GridLayout(3, false));
		FormData fd_composite_11 = new FormData();
		fd_composite_11.left = new FormAttachment(0);
		fd_composite_11.right = new FormAttachment(100);
		fd_composite_11.bottom = new FormAttachment(composite_10, 21, SWT.BOTTOM);
		fd_composite_11.top = new FormAttachment(composite_10, 1);
		new Label(composite_10, SWT.NONE);
		new Label(composite_10, SWT.NONE);
		
		Button button_10 = new Button(composite_10, SWT.CHECK);
		GridData gd_button_10 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_10.heightHint = 13;
		button_10.setLayoutData(gd_button_10);
		button_10.setText("12:20-12:40");
		composite_11.setLayoutData(fd_composite_11);
		
		Composite composite_12 = new Composite(this, SWT.NONE);
		composite_12.setLayout(new GridLayout(3, false));
		FormData fd_composite_12 = new FormData();
		fd_composite_12.left = new FormAttachment(0);
		fd_composite_12.right = new FormAttachment(100);
		fd_composite_12.bottom = new FormAttachment(composite_11, 22, SWT.BOTTOM);
		fd_composite_12.top = new FormAttachment(composite_11, 2);
		new Label(composite_11, SWT.NONE);
		new Label(composite_11, SWT.NONE);
		
		Button button_11 = new Button(composite_11, SWT.CHECK);
		GridData gd_button_11 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_11.heightHint = 14;
		button_11.setLayoutData(gd_button_11);
		button_11.setText("12:40-13:00");
		composite_12.setLayoutData(fd_composite_12);
		
		Composite composite_13 = new Composite(this, SWT.NONE);
		composite_13.setLayout(new GridLayout(3, false));
		FormData fd_composite_13 = new FormData();
		fd_composite_13.left = new FormAttachment(0);
		fd_composite_13.right = new FormAttachment(100);
		fd_composite_13.bottom = new FormAttachment(composite_12, 23, SWT.BOTTOM);
		fd_composite_13.top = new FormAttachment(composite_12, 3);
		new Label(composite_12, SWT.NONE);
		new Label(composite_12, SWT.NONE);
		
		Button button_12 = new Button(composite_12, SWT.CHECK);
		GridData gd_button_12 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_12.heightHint = 14;
		button_12.setLayoutData(gd_button_12);
		button_12.setText("13:00-13:20");
		composite_13.setLayoutData(fd_composite_13);
		
		Composite composite_14 = new Composite(this, SWT.NONE);
		composite_14.setLayout(new GridLayout(3, false));
		FormData fd_composite_14 = new FormData();
		fd_composite_14.left = new FormAttachment(0);
		fd_composite_14.right = new FormAttachment(100);
		fd_composite_14.bottom = new FormAttachment(composite_13, 23, SWT.BOTTOM);
		fd_composite_14.top = new FormAttachment(composite_13, 3);
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		
		Button button_13 = new Button(composite_13, SWT.CHECK);
		GridData gd_button_13 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_13.heightHint = 13;
		button_13.setLayoutData(gd_button_13);
		button_13.setText("13:20-13:40");
		composite_14.setLayoutData(fd_composite_14);
		
		Composite composite_15 = new Composite(this, SWT.NONE);
		composite_15.setLayout(new GridLayout(3, false));
		FormData fd_composite_15 = new FormData();
		fd_composite_15.left = new FormAttachment(0);
		fd_composite_15.right = new FormAttachment(100);
		fd_composite_15.bottom = new FormAttachment(composite_14, 23, SWT.BOTTOM);
		fd_composite_15.top = new FormAttachment(composite_14, 3);
		new Label(composite_14, SWT.NONE);
		new Label(composite_14, SWT.NONE);
		
		Button button_14 = new Button(composite_14, SWT.CHECK);
		GridData gd_button_14 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_14.heightHint = 13;
		button_14.setLayoutData(gd_button_14);
		button_14.setText("13:40-14:00");
		composite_15.setLayoutData(fd_composite_15);
		
		Composite composite_16 = new Composite(this, SWT.NONE);
		composite_16.setLayout(new GridLayout(3, false));
		FormData fd_composite_16 = new FormData();
		fd_composite_16.left = new FormAttachment(0);
		fd_composite_16.right = new FormAttachment(100);
		fd_composite_16.bottom = new FormAttachment(composite_15, 24, SWT.BOTTOM);
		fd_composite_16.top = new FormAttachment(composite_15, 4);
		new Label(composite_15, SWT.NONE);
		new Label(composite_15, SWT.NONE);
		
		Button button_15 = new Button(composite_15, SWT.CHECK);
		GridData gd_button_15 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_15.heightHint = 14;
		button_15.setLayoutData(gd_button_15);
		button_15.setText("14:00-14:20");
		composite_16.setLayoutData(fd_composite_16);
		
		Composite composite_17 = new Composite(this, SWT.NONE);
		composite_17.setLayout(new GridLayout(3, false));
		FormData fd_composite_17 = new FormData();
		fd_composite_17.left = new FormAttachment(0);
		fd_composite_17.right = new FormAttachment(100);
		fd_composite_17.bottom = new FormAttachment(composite_16, 22, SWT.BOTTOM);
		fd_composite_17.top = new FormAttachment(composite_16, 2);
		new Label(composite_16, SWT.NONE);
		new Label(composite_16, SWT.NONE);
		
		Button button_16 = new Button(composite_16, SWT.CHECK);
		GridData gd_button_16 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_16.heightHint = 12;
		button_16.setLayoutData(gd_button_16);
		button_16.setText("14:20-14:40");
		composite_17.setLayoutData(fd_composite_17);
		
		Composite composite_18 = new Composite(this, SWT.NONE);
		composite_18.setLayout(new GridLayout(3, false));
		FormData fd_composite_18 = new FormData();
		fd_composite_18.left = new FormAttachment(0);
		fd_composite_18.right = new FormAttachment(100);
		fd_composite_18.bottom = new FormAttachment(composite_17, 23, SWT.BOTTOM);
		fd_composite_18.top = new FormAttachment(composite_17, 3);
		new Label(composite_17, SWT.NONE);
		new Label(composite_17, SWT.NONE);
		
		Button button_17 = new Button(composite_17, SWT.CHECK);
		GridData gd_button_17 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_17.heightHint = 13;
		button_17.setLayoutData(gd_button_17);
		button_17.setText("14:40-15:00");
		composite_18.setLayoutData(fd_composite_18);
		
		Composite composite_19 = new Composite(this, SWT.NONE);
		composite_19.setLayout(new GridLayout(3, false));
		FormData fd_composite_19 = new FormData();
		fd_composite_19.left = new FormAttachment(0);
		fd_composite_19.right = new FormAttachment(100);
		fd_composite_19.bottom = new FormAttachment(composite_18, 23, SWT.BOTTOM);
		fd_composite_19.top = new FormAttachment(composite_18, 3);
		new Label(composite_18, SWT.NONE);
		new Label(composite_18, SWT.NONE);
		
		Button button_18 = new Button(composite_18, SWT.CHECK);
		GridData gd_button_18 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_18.heightHint = 14;
		button_18.setLayoutData(gd_button_18);
		button_18.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_18.setText("15:00-15:20");
		composite_19.setLayoutData(fd_composite_19);
		
		Composite composite_20 = new Composite(this, SWT.NONE);
		composite_20.setLayout(new GridLayout(3, false));
		FormData fd_composite_20 = new FormData();
		fd_composite_20.left = new FormAttachment(0);
		fd_composite_20.right = new FormAttachment(100);
		fd_composite_20.bottom = new FormAttachment(composite_19, 24, SWT.BOTTOM);
		fd_composite_20.top = new FormAttachment(composite_19, 4);
		new Label(composite_19, SWT.NONE);
		new Label(composite_19, SWT.NONE);
		
		Button button_19 = new Button(composite_19, SWT.CHECK);
		GridData gd_button_19 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_19.heightHint = 14;
		button_19.setLayoutData(gd_button_19);
		button_19.setText("15:20-15:40");
		composite_20.setLayoutData(fd_composite_20);
		
		Composite composite_21 = new Composite(this, SWT.NONE);
		composite_21.setLayout(new GridLayout(3, false));
		FormData fd_composite_21 = new FormData();
		fd_composite_21.left = new FormAttachment(0);
		fd_composite_21.right = new FormAttachment(100);
		fd_composite_21.bottom = new FormAttachment(composite_20, 24, SWT.BOTTOM);
		fd_composite_21.top = new FormAttachment(composite_20, 4);
		new Label(composite_20, SWT.NONE);
		new Label(composite_20, SWT.NONE);
		
		Button button_20 = new Button(composite_20, SWT.CHECK);
		GridData gd_button_20 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_20.heightHint = 13;
		button_20.setLayoutData(gd_button_20);
		button_20.setText("15:40-16:00");
		composite_21.setLayoutData(fd_composite_21);
		
		Composite composite_22 = new Composite(this, SWT.NONE);
		composite_22.setLayout(new GridLayout(3, false));
		FormData fd_composite_22 = new FormData();
		fd_composite_22.bottom = new FormAttachment(composite_21, 26, SWT.BOTTOM);
		fd_composite_22.top = new FormAttachment(composite_21, 6);
		new Label(composite_21, SWT.NONE);
		new Label(composite_21, SWT.NONE);
		
		Button button_21 = new Button(composite_21, SWT.CHECK);
		GridData gd_button_21 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_21.heightHint = 15;
		button_21.setLayoutData(gd_button_21);
		button_21.setText("16:00-16:20");
		fd_composite_22.left = new FormAttachment(composite, 0, SWT.LEFT);
		fd_composite_22.right = new FormAttachment(composite, 0, SWT.RIGHT);
		composite_22.setLayoutData(fd_composite_22);
		
		Composite composite_23 = new Composite(this, SWT.NONE);
		composite_23.setLayout(new GridLayout(3, false));
		FormData fd_composite_23 = new FormData();
		fd_composite_23.top = new FormAttachment(composite_22, 5);
		new Label(composite_22, SWT.NONE);
		new Label(composite_22, SWT.NONE);
		
		Button button_22 = new Button(composite_22, SWT.CHECK);
		GridData gd_button_22 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_22.heightHint = 12;
		button_22.setLayoutData(gd_button_22);
		button_22.setText("16:20-16:40");
		fd_composite_23.left = new FormAttachment(0);
		fd_composite_23.right = new FormAttachment(100);
		composite_23.setLayoutData(fd_composite_23);
		
		Composite composite_24 = new Composite(this, SWT.NONE);
		fd_composite_23.bottom = new FormAttachment(composite_24, -6);
		new Label(composite_23, SWT.NONE);
		new Label(composite_23, SWT.NONE);
		
		Button button_23 = new Button(composite_23, SWT.CHECK);
		GridData gd_button_23 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_23.heightHint = 13;
		button_23.setLayoutData(gd_button_23);
		button_23.setText("16:40-17:00");
		composite_24.setLayout(new GridLayout(3, false));
		FormData fd_composite_24 = new FormData();
		fd_composite_24.left = new FormAttachment(0);
		fd_composite_24.right = new FormAttachment(100);
		fd_composite_24.bottom = new FormAttachment(100, -93);
		fd_composite_24.top = new FormAttachment(0, 553);
		composite_24.setLayoutData(fd_composite_24);
		new Label(composite_24, SWT.NONE);
		new Label(composite_24, SWT.NONE);
		
		Button button_24 = new Button(composite_24, SWT.CHECK);
		GridData gd_button_24 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_24.heightHint = 12;
		button_24.setLayoutData(gd_button_24);
		button_24.setText("Check Button");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
