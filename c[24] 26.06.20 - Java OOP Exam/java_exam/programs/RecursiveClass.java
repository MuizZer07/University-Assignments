public class RecursiveClass {
	
	public static void recursiveCountUp(int low, int high) {
		if(low > high) return;
		System.out.println(low);
		recursiveCountUp(low + 1 , high);
	}

	public static void countUp(int n) {
		recursiveCountUp(1 , n);
	}

	public static void main(String agrs[]) {
		recursiveCountUp(2, 4);
		countUp(4);
	}
}

