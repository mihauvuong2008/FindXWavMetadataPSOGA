package audioParse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class FileIOManager {
	ArrayList<String> files = new ArrayList<>();

	public ArrayList<String> chooseFile(Display display) {
		final Shell shell = new Shell(display);

		FileDialog dlg = new FileDialog(shell, SWT.MULTI);
		if (dlg.open() != null) {
			String[] names = dlg.getFileNames();
			for (int i = 0, n = names.length; i < n; i++) {
				StringBuffer buf = new StringBuffer(dlg.getFilterPath());
				if (buf.charAt(buf.length() - 1) != File.separatorChar)
					buf.append(File.separatorChar);
				buf.append(names[i]);
				files.add(dlg.getFilterPath() + "\\" + names[i]);
			}
		}
		System.out.println(files);
		shell.dispose();
		return files;
	}

	public String chooseSingleFile(Display display) {
		final Shell shell = new Shell(display);

		ArrayList<String> files = new ArrayList<>();
		FileDialog dlg = new FileDialog(shell, SWT.OPEN);
		if (dlg.open() != null) {
			String[] names = dlg.getFileNames();
			for (int i = 0, n = names.length; i < n; i++) {
				StringBuffer buf = new StringBuffer(dlg.getFilterPath());
				if (buf.charAt(buf.length() - 1) != File.separatorChar)
					buf.append(File.separatorChar);
				buf.append(names[i]);
				files.add(dlg.getFilterPath() + "\\" + names[i]);
			}
		}
		System.out.println(files);
		shell.dispose();
		if (files.size() > 0)
			return files.get(0);
		return null;
	}

	public String chooseFolder(Display display) {
		final Shell shell = new Shell(display);
		DirectoryDialog dialog = new DirectoryDialog(shell);
		dialog.setFilterPath("D:\\test"); // Windows specific
		String path = dialog.open();
		System.out.println(path);
		shell.dispose();
		return path;
	}

	public String write(int bytesWritten, String outputFile, boolean appendflag) throws IOException {
		FileWriter myWriter = new FileWriter(outputFile, appendflag);
		myWriter.write(bytesWritten + ",");
		myWriter.close();
		return outputFile;
	}

	public void write(String string, String outputFile, boolean appendflag) throws IOException {
		FileWriter myWriter = new FileWriter(outputFile, appendflag);
		myWriter.write(string + ".");
		myWriter.close();
	}

	public String createFile(String outputDir, String fileName) throws IOException {
		String rs = outputDir + "\\" + fileName + ".txt";
		FileWriter myWriter = new FileWriter(rs);
		myWriter.close();
		return rs;
	}

	public void writeline(String string, String outputFileFullName, boolean appendflag) throws IOException {
		FileWriter myWriter = new FileWriter(outputFileFullName, appendflag);
		myWriter.write(string);
		myWriter.write(System.getProperty("line.separator"));
		myWriter.close();
	}

	public ArrayList<Double> readLineSmoothData(String smootherDataFile) {
		try {
			ArrayList<Double> rs = new ArrayList<>();
			@SuppressWarnings("resource")
			BufferedReader bufferreader = new BufferedReader(new FileReader(smootherDataFile));
			String line;
			while ((line = bufferreader.readLine()) != null) {
				rs.add(Double.valueOf(line));
			}
			return rs;
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
