package dynamicFunction;

import java.util.ArrayList;

public class RealNode {
	public int ID = 0;
	private ArrayList<Association> connectToMe;
	private ArrayList<Association> connectFromMe;
	private double sumIntput = 0;
	private double output = 0;

	public RealNode(int ID) {
		this.ID = ID;
		connectToMe = new ArrayList<>();
		connectFromMe = new ArrayList<>();
	}

	public ArrayList<Association> getConnectToMe() {
		return connectToMe;
	}

	public void setConnectToMe(ArrayList<Association> connectToMe) {
		this.connectToMe = connectToMe;
	}

	public ArrayList<Association> getConnectFromMe() {
		return connectFromMe;
	}

	public void setConnectFromMe(ArrayList<Association> connectFromMe) {
		this.connectFromMe = connectFromMe;
	}

	public double getSumIntput() {
		return sumIntput;
	}

	public double getOutput() {
		return output;
	}

	public double sum(double rawSum) {
		return sumIntput = rawSum;
	}

	public double sum() {
		sumIntput = 0;
		for (Association association : getConnectToMe()) {
			sumIntput += association.getWeight() * association.getSourceNode().getOutput();
		}
		return sumIntput;
	}

	public double tranfer() {
//		output = (Math.pow(Math.E, sumIntput) - Math.pow(Math.E, -1 * sumIntput))
//				/ (Math.pow(Math.E, sumIntput) + Math.pow(Math.E, -1 * sumIntput));
		output = (sumIntput / (1 + Math.abs(sumIntput)) + 1) / 2;
		return output;
	}

}
