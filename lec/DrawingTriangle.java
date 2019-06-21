public class DrawingTriangle {
	public static void main(String[] args) {
		Draw(15);
	}
	public static void Draw(int N) {
		String star = "*";
		while (N > 0) {
			System.out.println(star);
			star = star + "*";
			N --;
		}
	}
}