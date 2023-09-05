package appMain;

import java.util.ArrayList;

public final class FindXWavInout {
	private static final double MAX = Double.MAX_VALUE;

	public FindXWavInout() {
		// TODO Auto-generated constructor stub
	}

	public final static double y(double x) {
		return (Math.pow(x, 2) + Math.pow(Math.E, Math.pow(x, 2 * x)) + x * Math.pow(4, x)) / (2 * x + 1)
				+ (2 * x + Math.pow(3, 2 * x) - 1) / (5 * Math.pow(x, 3) + Math.log10(x) - 1);
	}

	public static double y(double GAcons, int i) {
//		return (Math.pow(x, 2) + Math.pow(Math.E, Math.pow(x, 2 * x)) + x * Math.pow(4, x)) / (2 * x + 1)
//				+ (2 * x + Math.pow(3, 2 * x) - 1) / (5 * Math.pow(x, 3) + Math.log10(x) - 1);

//		return 2 * Math.pow(x, 5) + 3 * Math.pow(x, 4) - 8 * Math.pow(x, 3) + 2 * Math.pow(x, 2) + 1;
		double function = GAcons
				* Math.sin(GAcons * Math.sin(i) * i + GAcons + GAcons * Math.sin(i + Math.sin(i) * i) + GAcons)
				* Math.sin(i)
				+ GAcons * i
						* Math.cos(
								GAcons * Math.cos(i) * i + GAcons * i * Math.cos(i + GAcons * Math.cos(i + GAcons) * i))
						* GAcons * Math.cos(i);

		return function;
	}

	public final static double getUpgradedx(double upgrade, long upgradeLen, double dNAres) {
//		if (upgradeLen == 0) {
//			return dNAres;
//		}
		return upgrade + (double) dNAres / upgradeLen;
	}

	public final static double getUpgradedy(double valueLevel, double y) {
		if (Double.isNaN(y)) {
			y = MAX;
		}
		double rs = 1 / (0.01 + (Math.pow(Math.abs(y), valueLevel)));
		return rs;// luong gia cang cao cang gan loi giai

	}

	public static double getTotalError(ArrayList<Double> _metadata, double valueLevel, double x) {
		int len = _metadata.size();
		double totalErr = 0;
		for (int i = 0; i < len; i++) {
			double err = _metadata.get(i) - y(x, i);
			totalErr += err * err;
		}
		double remakeTotalErr = Math.pow(totalErr, 0.015625);
		double rs = 1 / (0.01 + remakeTotalErr);
		return rs;
	}

//	double y = Math.pow(Math.abs(-1 * Math.pow(x, 4) + 6 * Math.pow(x, 3) - 3 * Math.pow(x, 2) + 3 * x + 1),
//			getValueLevel());

}
