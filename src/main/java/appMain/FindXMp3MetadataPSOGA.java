package appMain;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import audioParse.AudioMetadataMaker;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;

public class FindXMp3MetadataPSOGA {

	protected Shell shlAudiopaser;
	private Text text;
	private Text text_1;
	private Text text_2;
	static AudioMetadataMaker audioMetadataMaker;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FindXMp3MetadataPSOGA window = new FindXMp3MetadataPSOGA();
			audioMetadataMaker = new AudioMetadataMaker();
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
		shlAudiopaser.open();
		shlAudiopaser.layout();
		while (!shlAudiopaser.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAudiopaser = new Shell();
		shlAudiopaser.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				System.exit(0);
			}
		});
		shlAudiopaser.setImage(SWTResourceManager.getImage(FindXMp3MetadataPSOGA.class,
				"/org/apache/log4j/lf5/viewer/images/channelexplorer_satellite.gif"));
		shlAudiopaser.setSize(628, 347);
		shlAudiopaser.setText("AudioPaser");
		shlAudiopaser.setLayout(new GridLayout(3, false));

		Label lblInput = new Label(shlAudiopaser, SWT.NONE);
		lblInput.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInput.setText("input: ");

		text = new Text(shlAudiopaser, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnChooseFile = new Button(shlAudiopaser, SWT.NONE);
		btnChooseFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("");
				ArrayList<String> dir = audioMetadataMaker.getInputAudio(Display.getDefault());
				String show = "";
				int i = 0;
				for (String string : dir) {
					show += ((i == 0 ? "" : ", ") + string);
					i++;
				}
				text.setText(show);
			}
		});
		btnChooseFile.setText("Choose File");

		Label lblOutput = new Label(shlAudiopaser, SWT.NONE);
		lblOutput.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOutput.setText("output: ");

		text_1 = new Text(shlAudiopaser, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnChooseF = new Button(shlAudiopaser, SWT.NONE);
		btnChooseF.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_1.setText("");
				String dir = audioMetadataMaker.getaudioOutputDiretory(Display.getDefault());
				text_1.setText(dir);
			}
		});
		btnChooseF.setText("Choose File");
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);

		Button btnAudioToMetadata = new Button(shlAudiopaser, SWT.NONE);
		btnAudioToMetadata.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					audioMetadataMaker.builder();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAudioToMetadata.setText("Audio to Metadata");

		Label lblSmoothLevel = new Label(shlAudiopaser, SWT.NONE);
		lblSmoothLevel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSmoothLevel.setText("Smooth level: ");

		Spinner spinner = new Spinner(shlAudiopaser, SWT.BORDER);

		Button btnSmoothMetadata = new Button(shlAudiopaser, SWT.NONE);
		btnSmoothMetadata.setText("Metadata smoother");

		Label lblChooseMetadaOutput = new Label(shlAudiopaser, SWT.NONE);
		lblChooseMetadaOutput.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChooseMetadaOutput.setText("Choose Metada Output: ");

		text_2 = new Text(shlAudiopaser, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnChooseMetadaOutput = new Button(shlAudiopaser, SWT.NONE);
		btnChooseMetadaOutput.setText("Choose File");

		Label lblFilteLevel = new Label(shlAudiopaser, SWT.NONE);
		lblFilteLevel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFilteLevel.setText("Filter level: ");

		Spinner spinner_1 = new Spinner(shlAudiopaser, SWT.BORDER);

		Button btnMetadataFilter = new Button(shlAudiopaser, SWT.NONE);
		btnMetadataFilter.setText("Metadata Filter");
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);

		Button btnFindX = new Button(shlAudiopaser, SWT.NONE);
		btnFindX.setText("Find X");
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);

		Button btnShowXBy = new Button(shlAudiopaser, SWT.NONE);
		btnShowXBy.setText("Show X by Graph");
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);

		Composite composite = new Composite(shlAudiopaser, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		GridData gd_btnCancel = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnCancel.widthHint = 80;
		btnCancel.setLayoutData(gd_btnCancel);
		btnCancel.setText("Cancel");

	}
}
