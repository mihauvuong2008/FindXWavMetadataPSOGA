package audioParse;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

public class AudioMetadataMaker {
	FileIOManager fileIOManager;
	AudioParser audioParser;
	Filter filter;
	Smoother smoother;
	ArrayList<String> inputFiles;
	private String outputDir;
	private String RawPathfile;

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

	public String getOutputFiles() {
		return outputDir;
	}

	public ArrayList<String> getInputAudio(Display display) {
		return inputFiles = fileIOManager.chooseFile(display);
	}

	public String getaudioOutputDiretory(Display display) {
		return outputDir = fileIOManager.chooseFolder(display);
	}

	public void builder(final MessageBox msg) {

		Thread thread = new Thread() {
			boolean appendflag = false;

			public void run() {
				if (inputFiles != null) {
					for (String inputFile : inputFiles) {
						try {
							RawPathfile = audioParser.parseToPlainText(appendflag, fileIOManager, inputFile, outputDir);
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						appendflag = true;
						System.out.println("complete...");
					}
					msg.setMessage("build complete");
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							msg.open();
						}
					});
				}
			}
		};
		thread.start();

	}

	public String getInputRawfile(Display display) {
		return RawPathfile = fileIOManager.chooseSingleFile(display);
	}

	public String getRawPathfile() {
		return RawPathfile;
	}

}
