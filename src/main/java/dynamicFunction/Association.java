package dynamicFunction;

public class Association {
	private RealNode sourceNode;
	private RealNode destNode;
	private double weight = 1d;

	public RealNode getSourceNode() {
		return sourceNode;
	}

	public void setSourceNode(RealNode sourceNode) {
		this.sourceNode = sourceNode;
	}

	public RealNode getDestNode() {
		return destNode;
	}

	public void setDestNode(RealNode destNode) {
		this.destNode = destNode;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
