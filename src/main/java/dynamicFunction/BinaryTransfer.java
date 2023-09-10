package dynamicFunction;

import java.util.Arrays;
import java.util.regex.Pattern;

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
		double[] last = Arrays.copyOfRange(output, floatingPoint, len);
		double[] first = Arrays.copyOfRange(output, 0, floatingPoint);

		long _last = convertFromBinaryToIntDec(last);
		long _first = convertFromBinaryToIntDec(first);
		String fusion = _first + "." + _last;
		double rs = (output[len + 1] > 0.5 ? 1 : -1) * Double.valueOf(fusion);
		return rs;
	}

	static public double[] toBinary(double num, int size) {
		double Negative = (num > 0 ? 1 : -1);
		String _num = String.valueOf(num);
		String[] part = _num.split(Pattern.quote("."));
		if (part.length <= 1) {
			double[] __num = decToBinary((int) num, size - 2);
			double[] rs = new double[size];
			System.arraycopy(__num, 0, rs, rs.length - __num.length - 2, __num.length);
			rs[rs.length - 2] = __num.length / (__num.length + 2);
			rs[rs.length - 1] = Negative;
			return rs;
		}
		String first = part[0];
		int lastLen = part[1].length();
		lastLen = (lastLen > 9 ? 9 : lastLen);
		String last = part[1].substring(0, lastLen);
		double[] _first = decToBinary(Integer.valueOf(first), size);
		int _lastlength = size - _first.length - 2;
		int max = (int) Math.pow(2, _lastlength) - 1;
		System.out.println("max: " + max);
		if (Integer.valueOf(last) > max) {
			boolean found = false;
			boolean rounding = false;
			String e = "";
			String[] arr = last.split(Pattern.quote(""));
			for (int i = 0; i < arr.length - 1; i++) {
				e += arr[i];
				int curr = Integer.valueOf(e);
				int cmp = Integer.valueOf(e + "" + arr[i + 1]);
				int next = Integer.valueOf(arr[i + 1]);
				if (next >= 5) {
					curr++;
					rounding = true;
				}
				if (cmp > max) {
					last = "" + curr;
					found = true;
					break;
				}
				if (rounding) {
					curr--;
					rounding = false;
				}
			}
			if (!found)
				last = String.valueOf(max);
		}

		System.out.println("last: " + last);
		double[] _last = decToBinary(Integer.valueOf(last), _lastlength);
		System.out.println("_last length: " + _last.length);
		double FloatingPoint = (double) _first.length / (double) (size - 2);
		System.out.println("FloatingPoint: " + FloatingPoint);
		System.out.println("first: " + first + ", size: " + size);
		System.out.println("last: " + last);
		for (int i = 0; i < _first.length; i++) {
			System.out.print((int) _first[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < _last.length; i++) {
			System.out.print((int) _last[i] + " ");
		}
		System.out.println();
		double[] rs = new double[size];
		int dest1 = rs.length - (_first.length + _last.length - 4);
		System.out.println(dest1 + " " + rs.length + " " + _first.length + " " + _last.length);
		System.arraycopy(_first, 0, rs, 0, _first.length);
		System.arraycopy(_last, 0, rs, size - 2 - _last.length, _last.length);
		rs[rs.length - 2] = FloatingPoint;
		rs[rs.length - 1] = Negative;
		for (int i = 0; i < rs.length; i++) {
			System.out.print(rs[i] + " ");
		}
		return rs;
	}

	public static double[] decToBinary(int no, int LenOfGen) {
		int i = 0, temp[] = new int[LenOfGen];
		double binary[];
		while (no > 0) {
			temp[i++] = (int) (no % 2);
			no /= 2;
		}
		binary = new double[i];
		int k = 0;
		for (int j = i - 1; j >= 0; j--) {
			binary[k++] = temp[j];
		}
		return binary;
	}

}
