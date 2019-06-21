public class FindMax {
	public static void main(String[] args) {
		int[] numbers = new int[]{1,2,12,22,61,14,23,44,94,100};
		findmax(numbers);
	}
	public static void findmax(int[] numbers) {
		int max = 0;
		for (int i = 0; i<numbers.length; i++) {
			if (numbers[i] > max) {
				max = numbers[i];
			}
		} 
		System.out.println(max);
	}
}