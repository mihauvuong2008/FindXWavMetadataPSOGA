package dynamicFunction;

import java.util.Arrays;

public final class BinaryTransfer {

	public static long convertFromBinaryToIntDec(double[] output) {
		long j = 0;
		int len = output.length;
		int _len = len - 1;
		for (int i = 0; i < len; i++) {
			if (output[i] >= 0.5) {
				j += Math.pow(2, _len - i);
			}
		}
		return j;
	}

	public final static double convertFromBinaryToFloatingPointNegativeDec(double[] output) {
		int len = output.length - 2;
		int floatingPoint = (int) (output[len] * len);
		double[] last = Arrays.copyOfRange(output, 0, floatingPoint);
		double[] first = Arrays.copyOfRange(output, floatingPoint, len);

		long _last = convertFromBinaryToIntDec(last);
		long _first = convertFromBinaryToIntDec(first);
		String fusion = _first + "." + _last;
		double rs = (output[len + 1] > 0.5 ? 1 : -1) * Double.valueOf(fusion);
		return rs;
	}

	static public double[] toBinary(double num, int size) {
		String Negative = (num > 0 ? "1" : "-1");
		String _num = String.valueOf(num);
		String[] part = _num.split(".");
		String first = part[0];
		String last = part[1];
		double FloatingPoint = first.length() / (_num.length() - 2);
		int[] _first = toBin(Integer.valueOf(first), size);
		int[] _last = toBin(Integer.valueOf(last), size);
		double[] rs = new double[first.length() + last.length() + 2];
		System.arraycopy(rs, 0, _first, 0, _first.length);
		System.arraycopy(rs, _first.length, _last, 0, _last.length);
		rs[rs.length - 2] = FloatingPoint;
		rs[rs.length - 1] = Double.valueOf(Negative);
		return rs;
	}

	static public int[] toBin(int num, int size) {
		int[] ret = new int[size];
		for (int i = size, p = 0; i >= 0; i--, p++) {
			ret[i] = (num / 2 * p) % 2;
		}
		return ret;
	}
}
