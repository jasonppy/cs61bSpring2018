public class LaunchTest {
	public static void main(String[] args) {
		Test poppa = new Test();
		poppa.bark();
		// Test.bark() this line doesn't work since bark is a non-static method
		poppa.runFast(); // However, this line works even through runFast is a static method and poppa is an instance of Test
		Test.runFast();
	}
}