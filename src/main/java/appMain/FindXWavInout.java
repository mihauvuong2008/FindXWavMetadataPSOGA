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

	public static double y(double[] a, int x) {

//		System.out.println("x[1] : " + x[1] + ", x[2] : " + x[2] + ", x[3] : " + x[3] + ", x[4] : " + x[4] + ", x[5] : "
//				+ x[5] + ", x[6] : " + x[6] + ", x[7] : " + x[7] + ", x[8] : " + x[8] + ", x[9] : " + x[9] + ", x[0] : "
//				+ x[0]);
//		return (Math.pow(x, 2) + Math.pow(Math.E, Math.pow(x, 2 * x)) + x * Math.pow(4, x)) / (2 * x + 1)
//				+ (2 * x + Math.pow(3, 2 * x) - 1) / (5 * Math.pow(x, 3) + Math.log10(x) - 1);

//		return 2 * Math.pow(x, 5) + 3 * Math.pow(x, 4) - 8 * Math.pow(x, 3) + 2 * Math.pow(x, 2) + 1;
//		double function = a[1] * Math.sin(a[2] * Math.sin(x) * x + a[4]) + a[6] * Math.sin(a[5] * x)
//				+ a[7] * Math.sin(a[8] * Math.sin(a[0] * x) + a[9]) + 10 * a[3];
		double function = a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7] + a[8] + a[9];
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

	public static double getTotalError(ArrayList<Double> _metadata, double valueLevel, double[] a) {
		int len = _metadata.size();
		double totalErr = 0;
		for (int i = 0; i < len; i++) {
			double err = Math.abs(_metadata.get(i) - y(a, i));
			totalErr += err;
		}
//		double rs = 1 / (0.01 + totalErr);// (0.01 + totalErr);z
		double rs = 1 / (0.01 + y(a, 0));// (0.01 + totalErr);z
		return rs;
	}

//	double y = Math.pow(Math.abs(-1 * Math.pow(x, 4) + 6 * Math.pow(x, 3) - 3 * Math.pow(x, 2) + 3 * x + 1),
//			getValueLevel());

}
