package dynamicFunction;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FuncNetwork funcNetwork = new FuncNetwork(50000, 1000, 1000);
		funcNetwork.func(12.26222222222244);
		System.out.println();
		String s = Long.toBinaryString(11);
		System.out.println(s);
	}

}
