package dynamicFunction;

import java.util.ArrayList;

public class FuncNetwork {
	ArrayList<RealNode> inputLayer;
	ArrayList<RealNode> outLayer;
	MapLayer mapLayer;
	ArrayList<Association> mapLiner;
	int totalConnection;
	int lenOfInput;
	int lenOfOut;
	int lenOfMapLiner;
	private int maxInput;
	private int maxOutput;

	class MapLayer {
		public int sizeOfMapLiner;
		public ArrayList<RealNode> mapLayer1;
		public ArrayList<RealNode> mapLayer2;
		public ArrayList<Association> inLine;
		public ArrayList<Association> outLine;

		public MapLayer(int lenOfMapLiner) {
			sizeOfMapLiner = lenOfMapLiner / 2;
			mapLayer1 = new ArrayList<>();
			mapLayer2 = new ArrayList<>();
			inLine = new ArrayList<>();
			outLine = new ArrayList<>();
			mapLiner = new ArrayList<>();
		}

		void setupMap() {
			for (RealNode realNode1 : mapLayer1) {
				for (Association association1 : inLine) {
					association1.setDestNode(realNode1);
				}
				for (RealNode realNode2 : mapLayer2) {
					for (Association association1 : outLine) {
						association1.setSourceNode(realNode2);
					}
					Association association = new Association();
					association.setSourceNode(realNode1);
					association.setDestNode(realNode2);
					mapLiner.add(association);
				}
			}
		}
	}

	public FuncNetwork(int totalConnection, int maxInput, int maxOutput) {
		super();
		this.maxInput = maxInput;
		this.maxOutput = maxOutput;
		this.totalConnection = totalConnection;
		lenOfInput = (int) (Math.log10(maxInput) / Math.log10(2) + (1 - Double.MIN_VALUE) + 2/* Negative and point */);
		lenOfOut = (int) (Math.log10(maxOutput) / Math.log10(2) + (1 - Double.MIN_VALUE));
		lenOfMapLiner = totalConnection / (lenOfInput + lenOfOut);
		inputLayer = new ArrayList<>();
		outLayer = new ArrayList<>();
		mapLayer = new MapLayer(lenOfMapLiner);
		for (int i = 0; i < lenOfInput; i++) {
			inputLayer.add(new RealNode());
		}
		for (int i = 0; i < lenOfOut; i++) {
			outLayer.add(new RealNode());
		}
		for (int i = 0; i < mapLayer.sizeOfMapLiner; i++) {
			mapLayer.mapLayer1.add(new RealNode());
			mapLayer.mapLayer2.add(new RealNode());
		}
		ArrayList<Association> setup = new ArrayList<>();
		for (RealNode realNode : inputLayer) {
			Association association = new Association();
			association.setSourceNode(realNode);
			setup.add(association);
			mapLiner.add(association);
		}
		mapLayer.inLine = setup;
		setup = new ArrayList<>();
		for (RealNode realNode : outLayer) {
			Association association = new Association();
			association.setDestNode(realNode);
			setup.add(association);
			mapLiner.add(association);
		}
		mapLayer.outLine = setup;
		mapLayer.setupMap();
	}

	public double func(double x) {
		if (x > maxInput) {
			return maxOutput + 1;
		}
		double[] _input = BinaryTransfer.toBinary(x, lenOfInput);
		System.out.println("BinaryTransfer.toBinary: ");
		for (double d : _input) {
			System.out.print(" " + (int) d);
		}

		double e = BinaryTransfer.convertFromBinaryToFloatingPointNegativeDec(_input);
		System.out.println();
		System.out.println("convertFromBinaryToFloatingPointNegativeDec.toBinary: " + e);
		double rs = 0;
		return rs;
	}
}
