package dynamicFunction;

import java.util.ArrayList;

public class funcNetwork {
	ArrayList<RealNode> inputLayer;
	ArrayList<RealNode> outLayer;
	MapLayer mapLayer;
	ArrayList<Association> mapLiner;
	int totalConnection;
	int lenOfInput;
	int lenOfOut;
	int lenOfMapLiner;

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

	public funcNetwork(int totalConnection, int maxInput, int maxOutput) {
		super();
		this.totalConnection = totalConnection;
		lenOfInput = (int) (Math.log(maxInput) / Math.log(2) + (1 - Double.MIN_VALUE));
		lenOfOut = (int) (Math.log(maxOutput) / Math.log(2) + (1 - Double.MIN_VALUE));
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

		double rs = 0;
		return rs;
	}
}
