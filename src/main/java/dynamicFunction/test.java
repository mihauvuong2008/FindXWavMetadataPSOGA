package dynamicFunction;

import java.util.Random;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		funcNetwork.setWeight(arr);
//		System.out.println();
//		String s = Long.toBinaryString(11);

		Random r = new Random();
		DynamicFunction funcNetwork = new DynamicFunction(30, 40, 100);
		System.out.println();
		int size = funcNetwork.getMapLine().size();
		System.out.println("size: " + size);
		double[] arr = new double[size];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (r.nextBoolean() ? 1 : -1) * r.nextInt(1000) * r.nextDouble();
		}

		funcNetwork.setWeight(arr);
		double rs = funcNetwork.networkFunc(-12.26622222222244);
		System.out.println(rs);
		rs = funcNetwork.networkFunc(12.26622222222244);
		System.out.println(rs);
	}

}
