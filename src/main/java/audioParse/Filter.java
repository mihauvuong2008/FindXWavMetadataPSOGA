package audioParse;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;

public class Filter {

	public ArrayList<Double> filter(ArrayList<Double> smoothData, int filterRange, Display display) {
		ArrayList<Double> filterData = new ArrayList<>();
		for (int i = 0; i < smoothData.size(); i++) {
			if (i % filterRange == 0) {
				filterData.add(smoothData.get(i));
			}
		}
		System.out.println("smoothData: " + smoothData.size() + ", filterData: " + filterData.size());
		return filterData;
	}

}
