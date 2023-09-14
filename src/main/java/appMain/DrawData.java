package appMain;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ga_training.GATrainer;
import ga_training.aiEvolution.ValueHands;

import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Rectangle;

public class DrawData extends Shell {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			DrawData shell = new DrawData(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private GATrainer trainer;

	/**
	 * Create the shell.
	 * 
	 * @param display
	 * @param trainer
	 */

	public DrawData(Display display) {
		super(display, SWT.SHELL_TRIM | SWT.ON_TOP);
		addPaintListener(new PaintListener() {
			PaintEvent ee;
			Rectangle clientArea;

			public void paintControl(PaintEvent e) {
				ee = e;
				clientArea = getShell().getClientArea();

				ArrayList<Double> data = trainer.getMetadata();
				int step = clientArea.width / (2 * data.size()) + 1;
				int ZeroPointY = 150;
				if (trainer.getSolution() == null)
					return;
				double[] a = trainer.getSolution().getResult();
				int i = 0;

				for (int j = 0; j < data.size() - 1; j++) {
					ee.gc.drawLine(j * step, (int) (ValueHands.y(a, j) * step) + ZeroPointY, (j + 1) * step,
							(int) (ValueHands.y(a, j + 1) * step) + ZeroPointY);
					ee.gc.drawLine(j * step, (int) (data.get(j) * step) + ZeroPointY, (j + 1) * step,
							(int) (data.get(j + 1) * step) + ZeroPointY);
					i++;
				}

				for (int k = i; k < 200; k++) {
					ee.gc.drawLine(k * step, (int) (ValueHands.y(a, k) * step) + ZeroPointY, (k + 1) * step,
							(int) (ValueHands.y(a, k + 1) * step) + ZeroPointY);
				}

//				e.gc.drawLine(0, 0, clientArea.width, clientArea.height);
			}
		});
		createContents();
	}

	public GATrainer getTrainer() {
		return trainer;
	}

	public void setTrainer(GATrainer trainer) {
		this.trainer = trainer;
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Data Draw");
		setSize(900, 580);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
