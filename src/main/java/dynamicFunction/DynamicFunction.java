package dynamicFunction;

import java.util.ArrayList;

public class DynamicFunction {
	private ArrayList<RealNode> inputLayer;
	private ArrayList<RealNode> outputLayer;
	private BodyLayer bodyLayer;
	private ArrayList<Association> mapLine;
	private int totalConnection;
	private int lenOfInput;
	private int lenOfOut;
	private int lenOfMapLiner;
	private int maxInput;
	private int maxOutput;

	public DynamicFunction(int maxConnection, int maxInput, int maxOutput) {
		super();
		this.maxInput = maxInput;
		this.maxOutput = maxOutput;
		this.totalConnection = maxConnection;
		lenOfInput = (int) (Math.log10(maxInput) / Math.log10(2) + (1 - Double.MIN_VALUE) + 2/* Negative and point */);
		lenOfOut = (int) (Math.log10(maxOutput) / Math.log10(2) + (1 - Double.MIN_VALUE));
		lenOfMapLiner = (int) getLen() + 1;
//		System.out.println("lenOfMapLiner: " + lenOfMapLiner);
		if (lenOfMapLiner < 2)
			lenOfMapLiner = 2;
		inputLayer = new ArrayList<>();
		outputLayer = new ArrayList<>();
		mapLine = new ArrayList<>();
		bodyLayer = new BodyLayer(lenOfMapLiner);

		int id = 0;
		for (int i = 0; i < lenOfInput; i++) {
			inputLayer.add(new RealNode(id));
			id++;
		}
		for (int i = 0; i < lenOfOut; i++) {
			outputLayer.add(new RealNode(id));
			id++;
		}
		for (int i = 0; i < bodyLayer.sizeOfMapLiner; i++) {
			bodyLayer.mapLayer1.add(new RealNode(id));
			id++;
			bodyLayer.mapLayer2.add(new RealNode(id));
			id++;
		}

		bodyLayer.setupMap();
		System.out.println("mapLine: " + mapLine.size());
	}

	private double getLen() {
		int b = (lenOfInput + lenOfOut);
		double delta = Math.pow(b, 2) + 4 * totalConnection;
		if (delta < 0)
			return 1;
		return (-b + Math.pow(delta, 0.5));
	}
//=========================================================

	// ==============================================================
	public double networkFunc(double x) {
		if (x > maxInput) {
			return maxOutput + 1;
		}

		double[] _input = BinaryTransfer.toBinary(x, lenOfInput);
//		System.out.println("in:");
//		for (double d : _input) {
//			System.out.print(d + " ");
//		}
//		System.out.println();
		double e = BinaryTransfer.convertFromBinaryToFloatingPointNegativeDec(_input);
//		System.out.println();
//		System.out.println("convertFromBinaryToFloatingPointNegativeDec.toBinary: " + e);

		addToInputLayer(_input);
		propagation();
		double rs = getResult();
//		System.out.println("getResult: " + rs);
		return rs;
	}

	public ArrayList<RealNode> getInputLayer() {
		return inputLayer;
	}

	public void setInputLayer(ArrayList<RealNode> inputLayer) {
		this.inputLayer = inputLayer;
	}

	public ArrayList<RealNode> getOutputLayer() {
		return outputLayer;
	}

	public void setOutputLayer(ArrayList<RealNode> outputLayer) {
		this.outputLayer = outputLayer;
	}

	public BodyLayer getBodyLayer() {
		return bodyLayer;
	}

	public void setBodyLayer(BodyLayer bodyLayer) {
		this.bodyLayer = bodyLayer;
	}

	public ArrayList<Association> getMapLine() {
		return mapLine;
	}

	public void setMapLine(ArrayList<Association> mapLine) {
		this.mapLine = mapLine;
	}

	private double getResult() {
		double[] _rs = new double[outputLayer.size()];
//		System.out.println("out:");
		for (int i = 0; i < outputLayer.size(); i++) {
			RealNode realNode = outputLayer.get(i);
			realNode.sum();
			realNode.tranfer();
			_rs[i] = realNode.getOutput();
//			System.out.println("3realNode: " + realNode.getOutput());
		}
//		System.out.println();
		return BinaryTransfer.convertFromBinaryToFloatingPointNegativeDec(_rs);
	}

	private void propagation() {

		for (int i = 0; i < bodyLayer.mapLayer1.size(); i++) {
			RealNode realNode = bodyLayer.mapLayer1.get(i);
			double s = realNode.sum();
			realNode.tranfer();
//			System.out.println("1realNode: " + s + " " + realNode.getOutput());
		}

		for (int i = 0; i < bodyLayer.mapLayer2.size(); i++) {
			RealNode realNode = bodyLayer.mapLayer2.get(i);
			realNode.sum();
			realNode.tranfer();
//			System.out.println("2realNode: " + realNode.getOutput());
		}
	}

	private void addToInputLayer(double[] _input) {
		for (int i = 0; i < _input.length; i++) {
			RealNode realNode = inputLayer.get(i);
			double s = realNode.sum(_input[i]);
			realNode.tranfer();
//			System.out.println("realNode0: " + s + "| " + realNode.getOutput());
		}
	}

	public void setWeight(double[] arr) {
//		System.out.println("mapLine: " + mapLine.size() + ", arr: " + arr.length);
		int size = mapLine.size();
		for (int i = 0; i < size; i++) {
			mapLine.get(i).setWeight(arr[i]);
		}
	}

	class BodyLayer {
		public int sizeOfMapLiner;
		public ArrayList<RealNode> mapLayer1;
		public ArrayList<RealNode> mapLayer2;

		public BodyLayer(int lenOfBodyLayer) {
			sizeOfMapLiner = lenOfBodyLayer;
			mapLayer1 = new ArrayList<>();
			mapLayer2 = new ArrayList<>();
		}

		void setupMap() {

			for (RealNode realNode : mapLayer1) {
				for (RealNode sourceNode : inputLayer) {
					Association association = new Association();
					association.setSourceNode(sourceNode);
					association.setDestNode(realNode);
					sourceNode.getConnectFromMe().add(association);
					realNode.getConnectToMe().add(association);
					mapLine.add(association);
				}
				for (RealNode realNode2 : mapLayer2) {
					Association association = new Association();
					association.setSourceNode(realNode);
					association.setDestNode(realNode2);
					realNode.getConnectFromMe().add(association);
					realNode2.getConnectToMe().add(association);
					mapLine.add(association);
				}
			}

			for (RealNode realNode2 : mapLayer2) {
				for (RealNode destNode : outputLayer) {
					Association association = new Association();
					association.setSourceNode(realNode2);
					association.setDestNode(destNode);
					realNode2.getConnectFromMe().add(association);
					destNode.getConnectToMe().add(association);
					mapLine.add(association);
				}
			}
		}
	}
}
