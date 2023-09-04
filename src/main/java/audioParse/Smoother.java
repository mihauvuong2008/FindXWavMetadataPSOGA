package audioParse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Smoother {

	ArrayList<ArrayList<String>> rawdata;

	public Smoother() {
		rawdata = new ArrayList<>();
	}

	public ArrayList<ArrayList<Double>> smooth(String rawFile) {
		if (rawFile == null)
			return null;
		try {
			File myObj = new File(rawFile);
			Scanner myReader = new Scanner(myObj);
			String data = "";
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
			}

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
			ArrayList<ArrayList<Integer>> _rawdata = new ArrayList<>();
			for (ArrayList<String> rawsub : rawdata) {
				ArrayList<Integer> _rawsub = new ArrayList<>();
				SmoothGate smoothGate = new SmoothGate();
				System.out.println(rawsub.size());
				smoothGate.size = rawsub.size();
				smoothGate.fakeAverAgesize = smoothGate.size / 9 + 1;
				double max = Double.MIN_VALUE;
				double min = Double.MAX_VALUE;
				for (String string : rawsub) {
					if (string.equals(""))
						continue;
					int ele = Integer.valueOf(string);
					_rawsub.add(ele);
					smoothGate.total += ele;
					double absele = Math.abs(ele);
					if (max < absele) {
						max = absele;
					}
					int maxgatesize = smoothGate.max.size();
					double currfakeMaxave = currAve(smoothGate.max);
					if (maxgatesize == 0 || absele > currfakeMaxave) {
						push(smoothGate.max, absele, smoothGate.fakeAverAgesize);
					}
					double currfakeMinave = currAve(smoothGate.min);
					int mingatesize = smoothGate.min.size();
					if (mingatesize == 0 || absele < currfakeMinave) {
						push(smoothGate.min, absele, smoothGate.fakeAverAgesize);
					}
				}
				_rawdata.add(_rawsub);
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

			ArrayList<ArrayList<Double>> smooth = new ArrayList<>();
			int size = _rawdata.size();
			Random ran = new Random();
			for (int i = 0; i < size; i++) {
				ArrayList<Double> subSmooth = new ArrayList<>();
				int subSize = _rawdata.get(i).size();
				for (int j = 0; j < subSize; j++) {
					double ele = _rawdata.get(i).get(j) / gate.get(i).Avermax;
					if (ele > 1)
						ele = 1;
					if (ele < -1)
						ele = -1;
					// remove Negative
					ele = Math.pow(ele * 0.3 + 0.6, 4);
					// smooth
					ele = ele * (ran.nextDouble() * 0.1 + 0.9);
					subSmooth.add(ele);
					System.out.println(ele + ", gate.get(i).Avermax: " + gate.get(i).Avermax);
				}
				smooth.add(subSmooth);
			}
			return smooth;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	private double currAve(ArrayList<Double> arr) {
		if (arr.size() == 0)
			return 0;
		double total = 0;
		for (Double el : arr) {
			total += el;
		}
		return total / arr.size();
	}

	private void push(ArrayList<Double> arr, double ele, double fakeAverAgesize) {
		arr.add(ele);
		int size = arr.size();
		if (Double.compare(size, fakeAverAgesize) > 0) {
			arr.remove(0);
		}
	}

	private class SmoothGate {
		public double total;
		public int size;
		public double average;
		public int fakeAverAgesize;
		public double Avermax;
		public double Avermin;
		public ArrayList<Double> max = new ArrayList<>();
		public ArrayList<Double> min = new ArrayList<>();
	}
}
