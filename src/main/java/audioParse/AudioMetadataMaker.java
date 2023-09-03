package audioParse;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class AudioMetadataMaker {
	FileIOManager fileIOManager;
	AudioParser audioParser;
	Filter filter;
	Smoother smoother;
	ArrayList<String> inputFiles;
	private String outputFiles;

	public AudioMetadataMaker() {
		fileIOManager = new FileIOManager();
		audioParser = new AudioParser();
		filter = new Filter();
		smoother = new Smoother();
	}

	public FileIOManager getFileIOManager() {
		return fileIOManager;
	}

	public AudioParser getAudioParser() {
		return audioParser;
	}

	public Filter getFilter() {
		return filter;
	}

	public Smoother getSmoother() {
		return smoother;
	}

	public ArrayList<String> getInputAudio(Display display) {
		return inputFiles = fileIOManager.chooseFile(display);
	}

	public String getaudioOutputDiretory(Display display) {
		return outputFiles = fileIOManager.chooseFolder(display);
	}

	public void builder(final Shell shell) {

		Thread thread = new Thread() {
			public void run() {
				for (String object : inputFiles) {
					AudioParser audioParser = new AudioParser();
					boolean appendflag = false;
					appendflag = true;
					try {
						audioParser.parseToPlainText(appendflag, fileIOManager, object, outputFiles);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING | SWT.ABORT | SWT.RETRY | SWT.IGNORE);
				msg.setMessage("build complete");
				msg.open();
			}
		};
		thread.start();

	}

}
