package dynamicFunction;

import java.util.ArrayList;

public class RealNode {
	ArrayList<Association> connectToMe;
	ArrayList<Association> connectFromMe;
	double sumIntput = 0;
	double output = 0;

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

	public void setSumIntput(double sumIntput) {
		this.sumIntput = sumIntput;
	}

	public double getOutput() {
		return output;
	}

	public void setOutput(double output) {
		this.output = output;
	}

	public double sum() {
		for (Association association : connectToMe) {
			sumIntput += association.getWeight() * association.getSourceNode().getOutput();
		}
		return sumIntput;
	}

	public double tranfer() {
		output = 1 / (1 + Math.pow(Math.E, -1 * sumIntput));
		return output;
	}

}
