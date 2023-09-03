package audioParse;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;

import java.io.File;
import java.io.FileInputStream;

public class AudioParser {

	public void parseToPlainText(boolean appendflag, FileIOManager fileIOManager, String inputFiles, String outputFile)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// Name string uses relative addressing, assumes the resource is
		// located in "audio" child folder of folder holding this class.
		File file = new File(inputFiles);
		// The wav file named above was obtained from
		// https://freesound.org/people/Robinhood76/sounds/371535/
		// and matches the audioFormat.
		AudioInputStream audioInputStream = getAudioInputStream(file);

		AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
		AudioFormat audioFormat = fileFormat.getFormat();
		Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
		final int bufferSize = 2200; // in Byte
		sourceDataLine.open(audioFormat, bufferSize);

		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		sourceDataLine.start();

		while ((bytesRead = audioInputStream.read(buffer)) != -1) {
//			sourceDataLine.write(buffer, 0, bytesRead); play uncomment here
			fileIOManager.write(buffer[0], outputFile, appendflag);
		}

		fileIOManager.write(".", outputFile, appendflag);
		sourceDataLine.drain();
		// release resources
		sourceDataLine.close();
		audioInputStream.close();
	}

	public AudioInputStream getAudioInputStream(File file) throws UnsupportedAudioFileException, IOException {

		FileInputStream fis = new FileInputStream(file); // throws IOException
		AudioFileFormat fileFormat = null;
		// part of fix for 4325421
		try {
			fileFormat = AudioSystem.getAudioFileFormat(file);
		} finally {
			if (fileFormat == null) {
				fis.close();
			}
		}
		return new AudioInputStream(fis, fileFormat.getFormat(), fileFormat.getFrameLength());
	}
}
