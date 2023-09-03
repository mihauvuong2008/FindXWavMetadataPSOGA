package audioParse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Smoother {

	ArrayList<ArrayList<String>> rawdata;

	public Smoother() {
		rawdata = new ArrayList<>();
	}

	public void smooth(String rawFile) {
		if (rawFile == null)
			return;
		try {
			File myObj = new File(rawFile);
			Scanner myReader = new Scanner(myObj);
			String data = "";
			int i = 0;
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
				i++;
			}

			System.out.println("i: " + i + " - " + data);
			myReader.close();
			String[] arr = data.split(",");
			List<String> al = Arrays.asList(arr);
			ArrayList<String> sub = new ArrayList<>();
			for (String string : al) {
				if (string.contains(".")) {
					String replace = string.replace(".", "");
					sub.add(replace);
					rawdata.add(sub);
					sub = new ArrayList<>();
					continue;
				}
				sub.add(string);
			}

			System.out.println("rawdata: " + rawdata.size());
			ArrayList<SmoothGate> gate = new ArrayList<>();
			for (ArrayList<String> rawsub : rawdata) {
				SmoothGate smoothGate = new SmoothGate();
				System.out.println(rawsub.size());
				smoothGate.size = rawsub.size();
				smoothGate.fakeAverAgesize = smoothGate.size / 3;
				int max = -1 * Integer.MAX_VALUE;
				int min = Integer.MAX_VALUE;
				for (String string : rawsub) {
					int ele = Integer.valueOf(string);
					smoothGate.total += ele;
					if (max < ele) {
						max = ele;
						push(smoothGate.max, ele, smoothGate.fakeAverAgesize);
					}
					if (min > ele) {
						min = ele;
						push(smoothGate.min, ele, smoothGate.fakeAverAgesize);
					}
				}

				smoothGate.average = smoothGate.total / smoothGate.size;
				int total = 0;
				for (double m : smoothGate.max) {
					total += m;
				}
				smoothGate.Avermax = total / smoothGate.fakeAverAgesize;
				total = 0;
				for (double m : smoothGate.min) {
					total += m;
				}
				smoothGate.Avermin = total / smoothGate.fakeAverAgesize;

				gate.add(smoothGate);
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	private void push(ArrayList<Double> arr, double ele, double fakeAverAgesize) {
		arr.add(ele);
		if (arr.size() > fakeAverAgesize) {
			arr.remove(0);
		}
	}

	static class SmoothGate {
		double total;
		double size;
		double average;
		double fakeAverAgesize;
		double Avermax;
		double Avermin;
		ArrayList<Double> max;
		ArrayList<Double> min;
	}
}
