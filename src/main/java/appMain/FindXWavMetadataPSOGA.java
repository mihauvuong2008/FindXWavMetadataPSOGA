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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;

public class FindXWavMetadataPSOGA {

	protected Shell shlAudiopaser;
	private Text text;
	private Text text_1;
	private Text text_2;
	static AudioMetadataMaker audioMetadataMaker;
	MessageBox msg;
	private Text text_rawdata;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FindXWavMetadataPSOGA window = new FindXWavMetadataPSOGA();
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
		shlAudiopaser.setImage(SWTResourceManager.getImage(FindXWavMetadataPSOGA.class,
				"/org/apache/log4j/lf5/viewer/images/channelexplorer_satellite.gif"));
		shlAudiopaser.setSize(700, 430);
		shlAudiopaser.setText("AudioPaser");
		shlAudiopaser.setLayout(new GridLayout(5, false));
		msg = new MessageBox(shlAudiopaser, SWT.OK);

		Label lblInput = new Label(shlAudiopaser, SWT.NONE);
		lblInput.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInput.setText("input: ");

		text = new Text(shlAudiopaser, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

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

		Button btnAudioToMetadata = new Button(shlAudiopaser, SWT.NONE);
		btnAudioToMetadata.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2));
		btnAudioToMetadata.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				audioMetadataMaker.builder(msg);
				String path = audioMetadataMaker.getOutputFiles();
				if (path != null) {
					text_rawdata.setText(path);
				}

			}
		});
		btnAudioToMetadata.setText("Start parse Audio ToPlainText");

		Label lblOutput = new Label(shlAudiopaser, SWT.NONE);
		lblOutput.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOutput.setText("output: ");

		text_1 = new Text(shlAudiopaser, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

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

		Label lblRawDataPath = new Label(shlAudiopaser, SWT.NONE);
		lblRawDataPath.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRawDataPath.setText("raw data path: ");

		text_rawdata = new Text(shlAudiopaser, SWT.BORDER);
		text_rawdata.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Button btnSetPath = new Button(shlAudiopaser, SWT.NONE);
		btnSetPath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String path = audioMetadataMaker.getInputRawfile(Display.getDefault());
				if (path != null) {
					text_rawdata.setText(path);
				}
			}
		});
		btnSetPath.setText("Set Available Plained text");

		Label lblSmoothLevel = new Label(shlAudiopaser, SWT.NONE);
		lblSmoothLevel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSmoothLevel.setText("Smooth level: ");

		Spinner spinner = new Spinner(shlAudiopaser, SWT.BORDER);
		spinner.setMinimum(1);
		spinner.setSelection(10);
		new Label(shlAudiopaser, SWT.NONE);

		Button btnSmoothMetadata = new Button(shlAudiopaser, SWT.NONE);
		btnSmoothMetadata.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnSmoothMetadata.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rawFile = audioMetadataMaker.getRawPathfile();
				if (rawFile != null) {
					System.out.println("rawFile: " + rawFile);
					try {
						audioMetadataMaker.smooth(rawFile, Display.getDefault());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSmoothMetadata.setText("Metadata Smooth");

		Label label = new Label(shlAudiopaser, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 5, 1));

		Label lblFilteLevel = new Label(shlAudiopaser, SWT.NONE);
		lblFilteLevel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFilteLevel.setText("Filter level: ");

		final Spinner spinner_1 = new Spinner(shlAudiopaser, SWT.BORDER);
		spinner_1.setMinimum(1);
		spinner_1.setSelection(30);
		new Label(shlAudiopaser, SWT.NONE);

		Button btnMetadataFilter = new Button(shlAudiopaser, SWT.NONE);
		btnMetadataFilter.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnMetadataFilter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int filterRange = spinner_1.getSelection();
				try {
					audioMetadataMaker.filter(filterRange, Display.getDefault());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMetadataFilter.setText("Metadata Filter");

		Label lblChooseMetadaOutput = new Label(shlAudiopaser, SWT.NONE);
		lblChooseMetadaOutput.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChooseMetadaOutput.setText("Choose Metada Output: ");

		text_2 = new Text(shlAudiopaser, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Button btnChooseMetadaOutput = new Button(shlAudiopaser, SWT.NONE);
		btnChooseMetadaOutput.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnChooseMetadaOutput.setText("Choose File and save");
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);

		Button btnFindX = new Button(shlAudiopaser, SWT.NONE);
		btnFindX.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (audioMetadataMaker.getMetadata() == null) {
					msg.setText("no founded metada!");
					msg.open();
				}
				FindZeroPRLMainWindow findZeroPRLMainWindow = new FindZeroPRLMainWindow();
				findZeroPRLMainWindow.setTrainData(audioMetadataMaker.getMetadata());
				findZeroPRLMainWindow.open();
			}
		});
		btnFindX.setText("Find X");
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);

		Button btnShowXBy = new Button(shlAudiopaser, SWT.NONE);
		btnShowXBy.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnShowXBy.setText("Show X by Graph");
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);
		new Label(shlAudiopaser, SWT.NONE);

		Composite composite = new Composite(shlAudiopaser, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 5, 1));

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
