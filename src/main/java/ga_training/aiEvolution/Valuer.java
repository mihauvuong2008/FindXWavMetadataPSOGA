package ga_training.aiEvolution;

import java.util.ArrayList;

import appMain.FindXWavInout;
import ga_training.GENE;
import genetoPhenotypic.BinnaryGentoPhenotypic;

public class Valuer {

	int numOfParam;
	private double valueLevel = 0; // luong gia cang cao cang gan loi giai
	private double upgrade[]; // reinforcement learning
	private long upgradeLen = 1;

	public Valuer(int numOfParam) {
		super();
		this.numOfParam = numOfParam;
		this.upgrade = new double[numOfParam];
	}

	public double getValueLevel() {
		return valueLevel;
	}

	public void setValueLevel(double valueLevel) {
		this.valueLevel = valueLevel;
	}

	public double[] getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(double upgrade[]) {
		this.upgrade = upgrade;
		int len = getNumLen(upgrade[0]);
		upgradeLen = (long) Math.pow(10, len);
		System.out.println("upgradeLen: " + upgradeLen);
	}

	private int getNumLen(double number) {
		String value = String.valueOf(number);
		int count = 0;
		for (char c : value.toCharArray()) {
			if (c != '.') {
				count++;
			}
		}
		return count;
	}

	public void setUpgradeLen(long upgradeLen) {
		this.upgradeLen = upgradeLen;
	}

	public long getUpgradeLen() {
		return upgradeLen;
	}

	public final double getValue(GENE g, ArrayList<Double> metadata) {
		float[] dNAres = BinnaryGentoPhenotypic.convertFromBinaryToArrDec(numOfParam, g.getGene());
		double[] x = new double[numOfParam];
		for (int i = 0; i < dNAres.length; i++) {
			x[i] = FindXWavInout.getUpgradedx(upgrade[i], upgradeLen, dNAres[i]);
//			System.out.println("x[" + i + "]: " + x[i] + ", dNAres[" + i + "]: " + dNAres[i]);
		}
		return FindXWavInout.getTotalError(metadata, getValueLevel(), x);
	}

	public final double getValue(double upgrade, GENE g) {
		double dNAres = BinnaryGentoPhenotypic.convertFromBinaryToFloatingPointNegativeDec(g.getGene());
		double x = FindXWavInout.getUpgradedx(upgrade, upgradeLen, dNAres);
		double y = FindXWavInout.y(x);
		return FindXWavInout.getUpgradedy(getValueLevel(), y);
	}

	public final double getpartnerValue(double x) {
		double y = FindXWavInout.y(x);
		return FindXWavInout.getUpgradedy(getValueLevel(), y);
	}

	@SuppressWarnings("unused")
	private final double valueBuilder(double y) {
		return Math.pow(1 / (0.01 + Math.abs(y)), /* change (remake) digital resource */getValueLevel());// luong gia
																											// cang cao
		// cang gan loi giai
	}
}
