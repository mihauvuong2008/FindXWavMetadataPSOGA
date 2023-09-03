package audioParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.eclipse.swt.widgets.Display;

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

	public void builder() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioParser audioParser = new AudioParser();
		boolean flag = false;
		for (String object : inputFiles) {
			flag = true;
			audioParser.parseToPlainText(flag, object, outputFiles);
		}

	}

}
