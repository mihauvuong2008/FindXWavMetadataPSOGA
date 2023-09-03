package audioParse;

import java.io.File;
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

	public String chooseFolder(Display display) {
		final Shell shell = new Shell(display);
		DirectoryDialog dialog = new DirectoryDialog(shell);
		dialog.setFilterPath("D:\\test"); // Windows specific
		String path = dialog.open();
		System.out.println(path);
		shell.dispose();
		return path;
	}

	public void write(int bytesWritten, String outputFile, boolean appendflag) throws IOException {
		FileWriter myWriter = new FileWriter(outputFile + "\\metadata.txt", appendflag);
		myWriter.write(bytesWritten + ",");
		myWriter.close();
	}

	public void write(String string, String outputFile, boolean appendflag) throws IOException {
		FileWriter myWriter = new FileWriter(outputFile + "\\metadata.txt", appendflag);
		myWriter.write(string + ".");
		myWriter.close();
	}
}
