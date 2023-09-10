package genetoPhenotypic;

import java.util.ArrayList;
import java.util.Arrays;

public class BinnaryGentoPhenotypic {
	public int miniDecimal_to_binary(double i) {
		return (int) (i + 0.5);
	}

	public int binary_to_Decimal(int[] array) {
		int decimal = 0;
		int size = array.length;
		for (int i = 0; i < size; i++) {
			decimal = decimal << 1 | array[i];
			// System.out.println("decimal: " + decimal);
		}
		return decimal;
	}

	public int binary_to_Decimal(double[] array) {
		int decimal = 0;
		int size = array.length;
		for (int i = 0; i < size; i++) {
			decimal = decimal << 1 | (array[i] >= 0.5 ? 1 : 0);
		}
		return decimal;
	}

	public static int printBinaryform(int number) {
		int remainder;

		if (number <= 1) {
			System.out.print(number);
			return number; // KICK OUT OF THE RECURSION
		}

		remainder = number % 2;
		printBinaryform(number >> 1);
		return remainder;
	}

	static public int[] toBin(int num) {
		int[] ret = new int[8];
		for (int i = 7, p = 0; i >= 0; i--, p++) {
			ret[i] = (num / 2 * p) % 2;
		}
		return ret;
	}

	public static double convertFromBinaryToDec(double[] output) {
		long j = 0;
		int len = output.length;
		int _len = len - 1;
		for (int i = 0; i < len; i++) {
			if (output[i] >= 0.5) {
				j += Math.pow(2, _len - i);
			}
		}
		return j / 1000000000;
	}

	public final static double convertFromBinaryToNegativeDec___BASIC(double[] output) {
		long /** more digital resource */
		j = 0;
		int len = output.length - 1;
		int _len = len - 1;
		for (int i = 0; i < len; i++) {
			if (output[i] >= 0.5) {
				j += Math.pow(2, _len - i);
			}
		}
		int negative = output[len] > 0.5 ? 1 : -1;
		return negative * ((double) j / 1000000000);
	}

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

	public final static double[] convertFromBinaryToArrDec(int numOfParam, double[] output) {
		int len = output.length;
		int _len = len / numOfParam;
		double[] rs = new double[numOfParam];
		for (int i = 0; i < numOfParam; i++) {
			double[] arr = Arrays.copyOfRange(output, i * _len, (i + 1) * _len);
			rs[i] = convertFromBinaryToFloatingPointNegativeDec(arr);
//			System.out.println("arr: " + arr.length + ", len: " + len + ", rs[i]: " + rs[i]);
		}
		return rs;
	}

	public static double[] DecToBinary(int no, int LenOfGen) {
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